package commons.setting.input.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.inputcomponent.form.baseform.service.IBaseFormService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Fieldset;
import com.tjhq.commons.inputcomponent.po.Form;
import com.tjhq.commons.inputcomponent.po.FormItem;
import com.tjhq.commons.inputcomponent.utils.TableUtil;
import com.tjhq.commons.inputcomponent.utils.ToolUtil;
import com.tjhq.commons.setting.external.po.RECDetailPO;
import com.tjhq.commons.setting.external.po.RECPO;
import com.tjhq.commons.setting.input.service.ISinRecService;
import com.tjhq.commons.utils.UserUtil;

/**
 * 单记录表设置
 */
@Controller
@RequestMapping(value = "/commons/setting/input/sinRec")
public class SingleRecordController {
	@Resource
	private IDictTFactorService dictTFactorService;
	/*@Resource
	private IDictTSuitService dictTSuitService;*/
	@Resource
	private ISinRecService sinRecService;
	@Resource
	private IBaseFormService baseFormService;

	@RequestMapping(value="")
	public String page() {
		return "/commons/setting/input/singleRecord";
	}
	//----------- 详细设置 功能	
	private static String SHOWCOLS="2";  //默认设置为2列。
	private static String TITLEWIDTH="150"; // 列宽默认为150
	private static String GROUPFLAG ="0"; //分组标志 0 ：未分组。
	private static String LABLETEXTALIGN = "0";//默认label右对齐

	/**
	 * 获取Form定义
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getForm")
	public @ResponseBody
	Form getForm(String tableID) throws Exception {
		String userID = UserUtil.getUserInfo().getGuid(); 
		Form form = new Form();
		form.setTableID(tableID);
		baseFormService.initStructure(form, userID);
		form.setVisiable(true);
		setFormDefine(form);
		return form;
	}
	/**
	 * 取单页面定义
	 * 
	 * @param form
	 */
	private void setFormDefine(Form form) {
		// 取Form定义
		List<RECPO> recPoList = sinRecService.getSetRECList(form.getTableID());
		if(recPoList==null || recPoList.size()==0){
			throw new RuntimeException("整表设置数据为空！");
		}
		RECPO recPO = recPoList.get(0);
		Map<String, List<RECDetailPO>>  recDetailPOListMap = sinRecService.getSetRECDetailList(recPO.getRECID());
		List<RECDetailPO> details = recDetailPOListMap.get("columns");
		//List<RECDetailPO> fieldsetList =  recDetailPOListMap.get("fieldset");
		//details.addAll(fieldsetList);
		
		form.setColumnSize(Integer.parseInt(recPO.getSHOWCOLS()));
		form.setLabelWidth(Integer.parseInt(recPO.getTITLEWIDTH()));
		form.setAbsolutePosition(Integer.parseInt(recPO.getABSOLUTEPOSITION()));
		form.setLableTextAlign(recPO.getLABLETEXTALIGN());

		Map<String, FormItem> formItems = new HashMap<String, FormItem>();
		for (Column column : TableUtil.getLeafColumnList(form)) {
			FormItem item = (FormItem) column;
			formItems.put(item.getColumnID(), item);
		}

        if(details==null){
            return;
        }
		if(details!=null && details.size()==0){
			throw new RuntimeException("列表数据为空！");
		}
		for (RECDetailPO detail : details) {
			setFormItem(form, formItems, null, detail);
		}
		
		for (Map.Entry<String, FormItem> entry : formItems.entrySet()) {
			entry.getValue().setVisible(false);
			form.getFormItemList().add(entry.getValue());
		}
	}
	private void setFormItem(Form form, Map<String, FormItem> formItems,
			Fieldset parentFieldset, RECDetailPO detail) {
		if ("1".equals(detail.getIsgroupctrl())) {// 如果是分组框
			Fieldset fieldset = new Fieldset();
			fieldset.setColSpan(detail.getColspan());
			fieldset.setName(detail.getCtrlname());
			fieldset.setOrderId(detail.getOrderid());
			fieldset.setRowSpan(detail.getRowspan());
			fieldset.setLeftcols(detail.getLEFTCOLS());
			fieldset.setToprows(detail.getTOPROWS());
			fieldset.setTopgroups(detail.getTOPGROUPS());
			fieldset.setBorder(detail.getBORDER());
			fieldset.setVisiable(ToolUtil.toBoolean(detail.getIsshow()));
			form.getFormItemList().add(fieldset);
			if (detail.getListDetail() == null
					|| detail.getListDetail().size() == 0)
				return;
			for (RECDetailPO po : detail.getListDetail()) {
				setFormItem(form, formItems, fieldset, po);
			}
		} else {
			FormItem item = formItems.get(detail.getCtrlid());
			if(item==null){
				System.out.println();
				return;
			}
			formItems.remove(detail.getCtrlid());
			item.setRowSpan(detail.getRowspan());
			item.setColSpan(detail.getColspan());
			if (item.isVisible()) {
				item.setVisible(ToolUtil.toBoolean(detail.getIsshow()));
			}

			if (parentFieldset != null) {
				parentFieldset.getFormItemList().add(item);
			} else {
				form.getFormItemList().add(item);
			}

		}
	}
	/**
	 * 单页面整体设置
	 * @param objectid 
	 * @param tableid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getProjREC")
	@ResponseBody
	public String getProjREC(@RequestParam(value = "tableid") String tableid) throws Exception {
		List<RECPO> list = sinRecService.getSetRECList(tableid);
		Map<String, Object> map = new HashMap<String, Object>();
		map = new HashMap<String, Object>();
		if (list.size() > 0) {
			RECPO po = (RECPO) list.get(0);
			map.put("showcols", po.getSHOWCOLS());
			map.put("titlewidth", po.getTITLEWIDTH());
			map.put("sinrecPosition", po.getABSOLUTEPOSITION());
			map.put("lableTextAlign", po.getLABLETEXTALIGN());
			map.put("recid", po.getRECID());
			map.put("tableid", tableid);
		} else {
			map.put("showcols", SHOWCOLS);
			map.put("titlewidth", TITLEWIDTH);
			map.put("lableTextAlign", LABLETEXTALIGN);
			map.put("recid", "");
			map.put("tableid", tableid);
		}
		return (new ObjectMapper()).writeValueAsString(map);
	}
	/**
	 * 保存 单页面详细设置
	 * @param businessObjForm
	 * @param objectid   采集类型collTypeID
	 * @param tableid    表
	 * @param showcols   总列数
	 * @param titlewidth 列宽
	 * @param recid 主键 || 外键
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "savePageColumn")
	@ResponseBody
	public String saveBusinessObjForm(@RequestParam(value = "grid") String grid, 
			@RequestParam(value = "tableid") String tableid,
			@RequestParam(value = "recid",required=false) String recid , 
			@RequestParam(value = "showcols") String showcols,
			@RequestParam(value = "sinrecPosition") String sinrecPosition,
			@RequestParam(value = "lableTextAlign") String lableTextAlign,
			@RequestParam(value = "titlewidth") String titlewidth) throws Exception {
        String is_success = "{\"flag\":\"true\"}";
        try{ 
            Map<String, Object> map = new HashMap<String, Object>();
            //保存主表
            map.put( "showcols", Integer.parseInt( showcols ) );
            map.put( "titlewidth", Integer.parseInt( titlewidth )  );
            map.put( "sinrecPosition", Integer.parseInt( sinrecPosition )  );
            map.put( "lableTextAlign", lableTextAlign );
            map.put( "tableid", tableid );
            map.put( "recid", recid );
            map.put( "grid", grid );
            map.put("groupFlag", GROUPFLAG);
            
            recid = sinRecService.saveSingleRecord(map);
            is_success = "{\"flag\":\"true\",\"recid\":\""+recid+"\"}";
        }catch (Exception e) {
			e.printStackTrace();
			is_success = "{\"flag\":\"false\"}";
		}
		return is_success;
	}
	/**
	 * 获取表中列树
	 * @param tableid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getInputColumnTree")
	@ResponseBody
	public Map<String,Object> getInputColumnTree(@RequestParam(value = "tableid") String tableid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<DictTFactorPO> columnList = dictTFactorService.getDictTFactorsByTableId(tableid);
		map.put("column", columnList);
		return map;
	}
}
