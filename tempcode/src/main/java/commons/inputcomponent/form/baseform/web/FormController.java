package commons.inputcomponent.form.baseform.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.form.baseform.service.IBaseFormService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Fieldset;
import com.tjhq.commons.inputcomponent.po.Form;
import com.tjhq.commons.inputcomponent.po.FormItem;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.inputcomponent.utils.TableUtil;
import com.tjhq.commons.inputcomponent.utils.ToolUtil;
import com.tjhq.commons.setting.external.po.RECDetailPO;
import com.tjhq.commons.setting.external.po.RECPO;
import com.tjhq.commons.setting.external.service.IExpProjectSetInfoService;

@Controller("formController")
@RequestMapping(value = "/component/form")
public class FormController {
	
	@Resource
	private IBaseFormService baseFormService;
	
	@Resource
	private IExpProjectSetInfoService expProjectSetInfoService;
	
	public IBaseFormService getBaseFormService() {
		return baseFormService;
	}

	public void setBaseFormService(IBaseFormService baseFormService) {
		this.baseFormService = baseFormService;
	}
	
	public IExpProjectSetInfoService getExpProjectSetInfoService() {
		return expProjectSetInfoService;
	}

	public void setExpProjectSetInfoService(
			IExpProjectSetInfoService expProjectSetInfoService) {
		this.expProjectSetInfoService = expProjectSetInfoService;
	}

	/**
	 * 获取Form定义
	 * @param typeID
	 * @param processID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getForm")
	@ResponseBody
	public Object getForm(String tableID, String typeID, String processID) {
		Form form = new Form();
		ResultPO resultPO = null;
		UserDTO user = null;
		
		try {
            user = SecureUtil.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
            resultPO = new ResultPO(ExceptionCode.ERR_00000, ExceptionCode.ERR_00000 + ":加载用户信息出错!");
            return resultPO;
        }

        if (user == null) {
            resultPO = new ResultPO(ExceptionCode.ERR_00000, ExceptionCode.ERR_00000 + ":加载用户信息出错!");
            return resultPO;
        }
		
		
		form.setTableID(tableID);
		try {
            baseFormService.initStructure(form, user.getGuid());
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
            return resultPO;
        }
        
		// 此处业务根据情况自定义
            try {
                setFormDefine(form, typeID, processID);
                resultPO = new ResultPO(form);
            } catch (ServiceException e) {
                e.printStackTrace();
                resultPO = new ResultPO(e.getCode(),e.getErrorMessage());
            }
		return resultPO;
	}

	/**
	 * 获取Form数据
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getData")
	@ResponseBody
	public Object getData(@RequestBody Form form) {
	    ResultPO resultPO = null;
	    
	    try {
            resultPO = new ResultPO(getBaseFormService().getData(form));
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        
		return resultPO;
	}
	
	/**
	 * 保存form数据
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="saveData")
	public @ResponseBody Object saveData(@RequestBody Form form) {
	    ResultPO resultPO = null;
	    
	    try {
            getBaseFormService().saveData(form);
            resultPO = new ResultPO("数据保存成功!");
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
	    
		return resultPO;
	}
	
	/**
	 * 取单页面定义
	 * 
	 * @param form
	 * @param typeID
	 * @param processID
	 */
	protected void setFormDefine(Form form, String typeID, String processID) throws ServiceException{
		// 取Form定义
		Map<String, String> getSetRECListParams = new HashMap<String, String>();
		getSetRECListParams.put("objectid", typeID);
		getSetRECListParams.put("tableid", form.getTableID());
		getSetRECListParams.put("processId", processID);
		List<RECPO> recPoList = expProjectSetInfoService
				.getSetRECList(getSetRECListParams);
		if(recPoList == null || recPoList.size() == 0){
		    throw new ServiceException(ExceptionCode.COM_00001,"未进行单记录表的form设置！",false);
		}
		RECPO recPO = recPoList.get(0);
		List<RECDetailPO> details = expProjectSetInfoService.getSetRECDetailList(
				recPO.getRECID()).get("columns");

		form.setColumnSize(Integer.parseInt(recPO.getSHOWCOLS()));
		form.setLabelWidth(Integer.parseInt(recPO.getTITLEWIDTH()));

		Map<String, FormItem> formItems = new HashMap<String, FormItem>();
		for (Column column : TableUtil.getLeafColumnList(form)) {
			FormItem item = (FormItem) column;
			formItems.put(item.getColumnID(), item);
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
	
}
