package commons.dict.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.dict.external.po.DictTAppregisterPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.dict.service.IDictTModelRelaService;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.web.ConverTables;
/**
 * 表关联关系设置
 * @author bizaiyi
 * Apr 23, 2015 10:43:59 AM
 * DictTModelRelaController.java
 */
@Controller
@RequestMapping(value = "/commons/dict/dicttrelation")
public class DictTModelRelaController {
	private static String returnURL = "commons/dict/";

	@Resource
	private IDictTSuitService dictTSuitService;
	@Resource
	private IDictTModelRelaService dictTModelRelaService;
	
	/**
	 * 指定页面
	 * @return
	 */
	@RequestMapping(value = "")
	public String page(/*HttpServletRequest request*/) {
		try{
			//request.setAttribute("appid", request.getParameter("appid"));
			return returnURL + "dictTModelRela";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * @param tableId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="suitTree")
	@ResponseBody
	public Object getSuitTree(String tableId/*, String appID*/) throws Exception{
		try {
			/*if(null==appID || ("").equals(appID)){
				appID = "BGT";
			}*/

			List<DictTAppregisterPO> dictTAppregisterPOList = dictTSuitService.getAllDictTAppregister();
			List<DictTSuitPO> suitList = new ArrayList<DictTSuitPO>();
			
			//引用 获取 所有套表及下属表
			for(DictTAppregisterPO appPO : dictTAppregisterPOList){
				DictTSuitPO suitPO = new DictTSuitPO();
				suitPO.setSuitid(appPO.getAppid());
				suitPO.setSuitname(appPO.getAppname());
				suitPO.setSuperid("0");
				suitPO.setIsleaf("1");
				suitPO.setLevelno(0);
				suitPO.setAppid(appPO.getAppid());
				suitList.add(suitPO);
				suitList.addAll(dictTSuitService.getDictTSuits(appPO.getAppid(), "0", true));
			}
			
			//List<DictTSuitPO> suitList = dictTSuitService.getDictTSuits(appID, "0", true);
			
			List<TreeNode> treeList = new ArrayList<TreeNode> ();
			List<String> subTableList = null;
			
			if(tableId!=null && !tableId.trim().equals("")){
				subTableList = dictTModelRelaService.selectDictTModelRelaSubSet(tableId);
			}
			treeList = treeData(treeList,suitList,subTableList);
			
			return treeList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	 private List<TreeNode> treeData(List<TreeNode> treeList,List <DictTSuitPO>list, List<String> subTableList) throws Exception{	
		for(DictTSuitPO suit : list){
			TreeNode tree = new TreeNode();
			tree.setId(suit.getSuitid());
			tree.setName(suit.getSuitname());
			String superId = suit.getSuperid();
			if("0".equals(superId)){
				tree.setPId(suit.getAppid());
			}else{
				tree.setPId(suit.getSuperid());
			}
			tree.setIsLeaf(suit.getIsleaf());
			tree.setLevelNo(suit.getLevelno());
			tree.setAppID(suit.getAppid());
			tree.setIsParent("true");
			tree.setOpen(true);					
			treeList.add(tree);
			//物理表
			List<DictTModelPO> modelList = suit.getDictTModelList();
		
			if(modelList!=null&&modelList.size()>0){
			    for(DictTModelPO model:modelList){
			    	TreeNode child = new TreeNode();
					child.setId(model.getTableid());
					child.setName(model.getName());
					child.setPId(model.getSuitid());
					child.setIsLeaf(suit.getIsleaf());
					child.setDealType(model.getDealtype()); //表处理类型
					child.setDealName(model.getDealName());
					child.setTableType(model.getTabletype()); //表类型
					child.setAppID(model.getAppid());
					child.setIsParent("false");		
					if(subTableList!=null && subTableList.size()>0 && subTableList.contains(model.getTableid())){
						child.setChecked("true");
					}
					treeList.add(child);
				}	
			}
			List<DictTSuitPO> suitList = suit.getDictTSuitList();
			if(ConverTables.isNotNullList(suitList)){					
				treeData(treeList,suitList,subTableList);					
			}
		}
		return treeList;
	 }

	@RequestMapping(value="saveTableRelation")
	@ResponseBody
	public String saveTableRelation(HttpServletRequest request) throws Exception{
		try {
			String tableId = request.getParameter("tableId");
			String subTableIds = request.getParameter("subTableIds");
            String[] subTableIdArray = subTableIds.split(",");
			if(subTableIds==null || "".equals(subTableIds.trim())){
			    subTableIdArray = new String[0];
			}
			
			List<Map<String,String>> params = new ArrayList<Map<String,String>>();
			for(int i=0;i<subTableIdArray.length;i++){
				Map<String,String> map = new HashMap<String,String>();
				map.put("tableId", tableId);
				map.put("subTableId", subTableIdArray[i]);
				params.add(map);
			}
			if(params.size()==0){
			    dictTModelRelaService.deleteDictTModelRelaByTableId(tableId);
			}else{
	            dictTModelRelaService.insertDictTModelRela(params);
			}
			
			return "保存成功！";
		} catch (Exception e) {
			e.printStackTrace();
			return "保存失败："+e.getMessage();
		}
	}
}
