package commons.setting.dataaudit.auditrule.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.dao.UtilsMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.setting.dataaudit.auditrule.dao.AuditRuleDefXmlDAO;
import com.tjhq.commons.setting.dataaudit.auditrule.po.AuditRuleColPO;
import com.tjhq.commons.setting.dataaudit.auditrule.service.IAuditRuleDefXmlService;
import com.tjhq.commons.setting.formula.service.IFormulaService;
import com.tjhq.commons.setting.input.po.TreeNode;

/**
 *@desc:审核规则xml导入导出
 *@author： wxn
 *@date:2016-2-29下午2:14:23
 */
@Service
@Transactional(readOnly=true)
public class AuditRuleDefXmlService implements IAuditRuleDefXmlService {
	
	@Resource
	private AuditRuleDefXmlDAO auditRuleDefXmlDAO;
	
	@Resource
	UtilsMapper utilsMapper;
	
	@Resource
	private IDictTFactorService dictTFactorService; // 列的接口
	
	@Resource
	private IDictTModelcodeService modelCodeService;
	
	@Resource
	private IFormulaService formulaService;
	
	@Resource
	private IDictTModelService dictTModelService; // 列的接口

	@Override
	public List<Map<String, String>> findAuditData(Map<String, Object> paraMap)
			throws ServiceException {
		return auditRuleDefXmlDAO.findAuditData(paraMap);
	}

	@Override
	public List<AuditRuleColPO> findAuditColList() {
		List<AuditRuleColPO> dataList = new ArrayList<AuditRuleColPO>();
		dataList.add(new AuditRuleColPO("审核规则ID","CHECKID",4,false));
		dataList.add(new AuditRuleColPO("公式名称","DEFNAME",4,false));
		dataList.add(new AuditRuleColPO("左表ID","LMODELID",4,false));
		dataList.add(new AuditRuleColPO("右表ID","RMODELID",4,false));
		dataList.add(new AuditRuleColPO("列表达式(左)","LCOMPCOL",5,true));
		dataList.add(new AuditRuleColPO("列表达式(右)","RCOMPCOL",5,true));
		dataList.add(new AuditRuleColPO("左条件","LQUERY",5,true));
		dataList.add(new AuditRuleColPO("右条件","RQUERY",5,true));
		dataList.add(new AuditRuleColPO("左分组","LEFTGROUP",4,false));
		dataList.add(new AuditRuleColPO("右分组","RIGHTGROUP",4,false));
		dataList.add(new AuditRuleColPO("左值描述","LDESC",4,true));
		dataList.add(new AuditRuleColPO("右值描述","RDESC",4,true));
		
		dataList.add(new AuditRuleColPO("PUBGROUP","PUBGROUP",4,false));
		dataList.add(new AuditRuleColPO("允许误差","ERRORDEF",2,false));
		dataList.add(new AuditRuleColPO("序号","SERID",1,false));
		dataList.add(new AuditRuleColPO("关系符","RELATYPE",4,false));
		dataList.add(new AuditRuleColPO("错误信息显示","SHOWTEXT",5,true));
		
		dataList.add(new AuditRuleColPO("审核类型","CHECKTYPE",4,false));
		dataList.add(new AuditRuleColPO("解析后的sql","CHECKSQL",5,true));
		dataList.add(new AuditRuleColPO("审核分类","CHECKSORTID",4,"BGT_T_CHECKSORT",false));
		dataList.add(new AuditRuleColPO("业务系统ID","APPID",4,"APPID",false));
		dataList.add(new AuditRuleColPO("创建级次","CREATEBGTLEVEL",1,false));
		return dataList;
	}

	@Override
	public String getSysDate() throws ServiceException {
		
		return auditRuleDefXmlDAO.findSysDate();
	}

	@Override
	public boolean isExistCheckID(Map<String, Object> paraMap)
			throws ServiceException {
		int n =auditRuleDefXmlDAO.findCountByCheckID(paraMap);
		return n>0?true:false;
	}

	@Override
	public boolean isExistCheckSortID(Map<String, Object> paraMap)
			throws ServiceException {
		int n =auditRuleDefXmlDAO.findCountByCheckSortID(paraMap);
		return n>0?true:false;
	}
	
	/**
	 * 验证导入数据
	 */
	@Override
	public boolean verifyData(Map<String, Object> paraMap)
			throws  ServiceException {
//		if(isExistCheckID(paraMap)){
//			throw new ServiceException("","审核规则ID重复 规则名称="+(String)paraMap.get("DEFNAME"),false);
//		}
		if(!isExistCheckSortID(paraMap)){
			throw new ServiceException(ExceptionCode.AUD_24212,"审核分类不存在  审核分类的ID="+(String)paraMap.get("CHECKSORTID"),false);
		}
		String tableID=(String)paraMap.get("LMODELID");
		if(!isExistTable(tableID)){
			throw new ServiceException(ExceptionCode.AUD_24205,"左表不存在  tableID="+(String)paraMap.get("LMODELID"),false);
		}
		tableID=(String)paraMap.get("RMODELID");
		if(null!=tableID&&!"".equals(tableID)&&!isExistTable(tableID)){
			throw new ServiceException(ExceptionCode.AUD_24205,"右表不存在  tableID="+(String)paraMap.get("RMODELID"),false);
		}
		
		//验证列是否存在
		checkColExists(paraMap);
		
		return true;
	}
    /**
     * 验证列是否存在
     * @param paraMap
     * @throws Exception,ServiceException 
     */
	private void checkColExists(Map<String, Object> paraMap) throws ServiceException {
		String lTableID=(String)paraMap.get("LMODELID");
		String RTableID=(String)paraMap.get("RMODELID");
		String checkType=(String)paraMap.get("CHECKTYPE");
		Map<String,DictTFactorPO> colMap=new HashMap<String,DictTFactorPO>();
		List<DictTFactorPO> factorList=dictTFactorService.getDictTFactorByTableidAndType(lTableID,"1");
		for(DictTFactorPO AuditRuleColPO:factorList){
			colMap.put(AuditRuleColPO.getColumnid(), AuditRuleColPO);
		}
		if(null!=RTableID && !"".equals(RTableID)){
			 factorList=dictTFactorService.getDictTFactorByTableidAndType(RTableID,"1");
				for(DictTFactorPO AuditRuleColPO:factorList){
					colMap.put(AuditRuleColPO.getColumnid(), AuditRuleColPO);
				}
		}
		//不是自定义审核需要验证表达式等
		if(!"2".equals(checkType)){
			//验证表达式
			String lCompcol =(String)paraMap.get("LCOMPCOL");
			if(null!=lCompcol && !"".equals(lCompcol)){
				parseAndCheckCol(lCompcol,colMap);
			}
			String RCompcol =(String)paraMap.get("RCOMPCOL");
			if(null!=RCompcol && !"".equals(RCompcol)){
				parseAndCheckCol(RCompcol,colMap);
			}
			
			//验证条件
			String LQuery =(String)paraMap.get("LQUERY");
			if(null!=LQuery && !"".equals(LQuery)){
				parseAndCheckCol(LQuery,colMap);
			}
			
			String RQuery =(String)paraMap.get("RQUERY");
			if(null!=RQuery && !"".equals(RQuery)){
				parseAndCheckCol(RCompcol,colMap);
			}
			
			//验证分组
			String leftGroup =(String)paraMap.get("LEFTGROUP");
			if(null!=leftGroup && !"".equals(leftGroup)){
				parseAndCheckColID(leftGroup,colMap);
			}
			
			//验证分组
			String rightGroup =(String)paraMap.get("RIGHTGROUP");
			if(null!=rightGroup && !"".equals(rightGroup)){
				parseAndCheckColID(rightGroup,colMap);
			}

		}
		
		//验证sql是否能正常执行
		String checkSql=(String)paraMap.get("CHECKSQL");
		if(checkSql.indexOf("@WHERE@")>0){
			 checkSql=checkSql.replaceAll("@WHERE@", " and 1=2 ");
		}
		
		try {
			formulaService.callErrorMessage(checkSql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.AUD_24208,"checkSQL:"+e.getMessage(),false);
		} 
		
	}
	
	/**
	* @Title: parseAndCheckCol
	* @Description: 解析并验证列信息
	* @param @param parsecol
	* @param @param colMap
	* @param @throws ServiceException
	* @return void
	* @throws
	 */
	private void parseAndCheckCol(String parsecol,Map<String,DictTFactorPO> colMap) throws ServiceException {
		String tempStr=parseAndCheckColRF(parsecol);
		parseAndCheckColID(tempStr,colMap);
	}
	/**
	 * 验证REF是否存在 ref 代码表 是否存在 使用的代码值 是否存在
	 * @param lCompcol
	 * @return
	 * @throws ServiceException
	 */
	private String parseAndCheckColRF(String lCompcol) throws ServiceException {
		Pattern p = Pattern.compile("RF\\(.+?\\)\\.\\{.+?\\}");
		StringBuffer finalResult = new StringBuffer(50);
		if (lCompcol.indexOf(Constants.RF_START) != -1) {// 如果存在 则开始解析
			Matcher m = p.matcher(lCompcol);
			while (m.find()) {
				String tempStr = lCompcol.substring(m.start(), m.end());
				//列的id
				String tableId = tempStr.substring(tempStr.indexOf(Constants.RF_START) + 3,tempStr.indexOf(Constants.RF_END));
				//列的代码值
				String colMatchStr = tempStr.replace(Constants.RF_START, "")
											.replace(Constants.RF_END, "")
											.replace(tableId, "")
											.replace(Constants.COMCOL_START, "")
											.replace(Constants.COMCOL_END, "");
				String csDbTableName = "";
				//获取列对应的代码表信息
				DictTModelcodePO mode = modelCodeService.getDictTModelcodePOByID(tableId.trim());
				// 查询表的物理名称
				if (mode != null) {
					csDbTableName = mode.getDbtablename();
					colMatchStr = colMatchStr.replace("'", "");
					String[] idList = colMatchStr.split(",");
					List<TreeNode> nodes = new ArrayList<TreeNode>();

					try {
						//获取代码表的代码
						nodes = formulaService.getModelCodeDataByCsid(csDbTableName);
					} catch (Exception e) {
						e.printStackTrace();
						throw new ServiceException(ExceptionCode.AUD_24209, "引用的代码表没有找到", false);
					}
					//代码是否存在域代码表中
					if (nodes != null && nodes.size() > 0) {
						for (String id : idList) {
							boolean existFlag = false;
							for (TreeNode node : nodes) {
								if (node.getId().equals(id)) {
									existFlag = true;
									break;
								}
							}
							if (!existFlag) {
								throw new ServiceException(ExceptionCode.AUD_24206, "代码表 "+ csDbTableName + " 中找不到此值 guid=" + id + "", false);
							}

						}
					} else {
						// throw 抛出异常
					}

				} else {
					throw new ServiceException(ExceptionCode.AUD_24209, "代码表未找到  tableID=" + tableId+ "", false);
				}
				//验证无问题
				m.appendReplacement(finalResult, "");
			}

			m.appendTail(finalResult);
			return finalResult.toString();
		} else {
			return lCompcol;
		}
	}
	/**
	 * 验证列ID是否存在
	 * @param lCompcol
	 * @return
	 * @throws ServiceException
	 */
	private void parseAndCheckColID(String lCompcol,Map<String,DictTFactorPO> colMap) throws ServiceException {
		Pattern p = Pattern.compile(Constants.COLUMN_START + ".+?"+ Constants.COLUMN_END);
		StringBuffer finalResult = new StringBuffer(50);
		if (lCompcol.indexOf(Constants.COLUMN_START) != -1) {// 如果存在 则开始解析
			Matcher m = p.matcher(lCompcol);
			while (m.find()) {
				String tempStr = lCompcol.substring(m.start(), m.end());
				String colID = tempStr.replace(Constants.COLUMN_START, "").replace(Constants.COLUMN_END, "");
				if (!colMap.containsKey(colID.trim())) {
					throw new ServiceException(ExceptionCode.AUD_24210, "列id不存在   colID=" + colID,false);
				}
				m.appendReplacement(finalResult, "");
			}
			m.appendTail(finalResult);
		}
	}

	@Override
	public boolean isExistTable(String tableID)
			throws ServiceException {
		int n=auditRuleDefXmlDAO.findCountTableID(tableID);
		return n>0?true:false;
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void saveOrUpdateAuditData(Map<String, Object> paraMap)
			throws ServiceException {
		int n=auditRuleDefXmlDAO.findCountByCheckID(paraMap);
		if(n==0){
			//paraMap.put("CHECKID", utilsMapper.getDBUniqueID());
			auditRuleDefXmlDAO.insertAuditData(paraMap);
		}else{
			//验证财政级次
			Integer createBgtLevel=auditRuleDefXmlDAO.findCreateBgtLevelByCheckID(paraMap);
			Integer userBgtLevel=(Integer)paraMap.get("userBgtLevel");
			if(createBgtLevel<userBgtLevel){
				throw new ServiceException(ExceptionCode.AUD_24213,"上级下发的审核规则【"+paraMap.get("DEFNAME")+"】 不允许覆盖导入",false);
			}
			auditRuleDefXmlDAO.updateAuditData(paraMap);

		}
			
		
	}

	@Override
	/**
	 * 查询审核分类数据
	 */
	public List<Map<String, String>> findAuditSortDataByExp(
			Map<String, Object> paraMap) throws ServiceException {
		return auditRuleDefXmlDAO.findAuditSortDataByExp(paraMap);
	}

	@Override
	/**
	 * 分类数据
	 */
	public List<AuditRuleColPO> findAuditSortColList() {
		List<AuditRuleColPO> dataList = new ArrayList<AuditRuleColPO>();
		dataList.add(new AuditRuleColPO("审核分类ID","CHECKSORTID",4,false));
		dataList.add(new AuditRuleColPO("审核分类名称","CHECKSORTNAME",4,true));
		dataList.add(new AuditRuleColPO("末级标志","ENDFLAG",4,false));
		dataList.add(new AuditRuleColPO("SUPERID","SUPERID",3,false));
		dataList.add(new AuditRuleColPO("序号","LVLID",4,false));
		dataList.add(new AuditRuleColPO("备注","REMARK",4,true));
		dataList.add(new AuditRuleColPO("全路径","FULLPATH",4,true,true));
		dataList.add(new AuditRuleColPO("层级","RLEVEL",1,false,true));
		dataList.add(new AuditRuleColPO("业务系统ID","APPID",4,"APPID",false));
		return dataList;
	}

	@Override
	public List<Map<String, String>> findAuditSortDataFromRoot(
			Map<String, Object> paraMap) throws ServiceException {
		return auditRuleDefXmlDAO.findAuditSortDataFromRoot(paraMap);
	}
	
	/**
	 * 解析导入xml文件
	 */
	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void impXmlData(HashMap<String, Object> paramMap, Element root)
			throws ServiceException {
		//解析审核规则分类信息
		List<Map<String,Object>> auditSortList=parseAuditSortData(paramMap, root);
		//检索所有的审核分类
		List<Map<String,String>> targetSortList = findAuditSortDataFromRoot(paramMap);
		//合并审核分类
		Map<String,String> guidMap=margeAuditSort(auditSortList,targetSortList);
		//导入视图对应物理表
		impViewToTables(root);
		//导入录入对象对应审核对象
		impInputObjToAuditObj(root);
		
		//解析并导入审核规则
		List<AuditRuleColPO> colList=findAuditColList();
		List<Element> nodeList=root.elements("AuditRule");
		for(Element ele:nodeList){
			//解析xml数据
			Map<String,Object> auditRuleData=this.parseXmlData(ele,colList);
			auditRuleData.put("USERDISTRICTID", paramMap.get("USERDISTRICTID"));
			auditRuleData.put("APPID", paramMap.get("appID"));
			String checkSortID=(String)auditRuleData.get("CHECKSORTID");
			auditRuleData.put("CHECKSORTID", guidMap.get(checkSortID));
			verifyData(auditRuleData);
			auditRuleData.put("userBgtLevel", paramMap.get("userBgtLevel"));
			saveOrUpdateAuditData(auditRuleData);
		}
		
		auditRuleDefXmlDAO.clearAuditData();
	}
	
	/**
	* @Title: impInputObjToAuditObj
	* @Description: 导入录入对象对应审核对象
	* @param @param root
	* @param @throws ServiceException
	* @return void
	* @throws
	 */
	private void impInputObjToAuditObj(Element root) throws ServiceException {
		List<AuditRuleColPO> colList=findInputObjToAuditObjColList();
		List<Element> nodeList =root.elements("InputObjToAuditObjs");
		if(null!=nodeList&&nodeList.size()>0){
			List<Element> childs=nodeList.get(0).selectNodes("InputObjToAuditObj");
			if(null!=childs&&childs.size()>0){
				for(Element ele:childs){
					Map<String,Object> inputObjToAuditObjData=this.parseXmlData(ele,colList);
					String inputObjID=(String)inputObjToAuditObjData.get("INPUTTABLEID");
					if(tableIsExists(inputObjID)){
						//表存在 判断是否 关系是否存在
						List dataList=auditRuleDefXmlDAO.findByInputObj(inputObjID);
						if(null==dataList||dataList.size()==0){//如果没有设置过，就插入
							auditRuleDefXmlDAO.insertInputObjToAuditObj(inputObjToAuditObjData);
						}
					}else{
						
						throw new ServiceException(ExceptionCode.ERR_00000,"录入对象ID"+inputObjID+" 在目标库中不存在",false);
					}
				}
				
			}
			
		}

	}
    //判断表是否存在
	private boolean tableIsExists(String tableID) {
		return null==dictTModelService.getDictTModelByID(tableID, false)?false:true;
	}
	
	/**
	* @Title: impViewToTables
	* @Description: 导入视图对应物理表
	* @param @param root
	* @param @throws ServiceException
	* @return void
	* @throws
	 */
	private void impViewToTables(Element root) throws ServiceException {
		List<AuditRuleColPO> colList=findViewToTableColList();
		List<Element> nodeList =root.elements("ViewToTables");
		if(null!=nodeList&&nodeList.size()>0){
			List<Element> childs=nodeList.get(0).selectNodes("ViewToTable");
			if(null!=childs&&childs.size()>0){
				for(Element ele:childs){
					Map<String,Object> viewToTableData=this.parseXmlData(ele,colList);
					String viewID=(String)viewToTableData.get("VIEWID");//视图ID
					String tableID=(String)viewToTableData.get("TABLEID");//表Id
					if(tableIsExists(tableID)){
						//表存在 判断是否 关系是否存在
						List dataList=auditRuleDefXmlDAO.findViewToTableByViewID(viewID);
						if(null==dataList||dataList.size()==0){//如果没有设置过，就插入
							auditRuleDefXmlDAO.insertViewToTable(viewToTableData);
						}
					}else{
						throw new ServiceException(ExceptionCode.ERR_00000,"物理表ID"+tableID+" 在目标库中不存在",false);
					}

				}
				
			}
			
		}
	}

	/**
	 * 同步审核分类
	 * @param auditSortList
	 * @throws ServiceException 
	 */
	private Map<String,String>  margeAuditSort(List<Map<String, Object>> auditSortList,List<Map<String, String>> targetAuditSortList) throws ServiceException {
	 Map<String,Map<String,String>> targetMap=new HashMap<String,Map<String,String>>();
	 Map<String,String> guidMap=new HashMap<String,String>();
	 for(Map<String,String> eleData:targetAuditSortList){
		 targetMap.put(eleData.get("FULLPATH"), eleData);
	 }
	 
	 for(Map<String, Object> srcEle:auditSortList){
		 Map<String, String> targetEle=targetMap.get(srcEle.get("FULLPATH"));
		 if(null==targetEle){//不存在 
			String newGuid= utilsMapper.getDBUniqueID();
			guidMap.put((String)srcEle.get("CHECKSORTID"), newGuid);
			srcEle.put("CHECKSORTID", newGuid);
			String superID=(String)srcEle.get("SUPERID");
			if(null!=superID && !"".equals(superID)){
				srcEle.put("SUPERID", guidMap.get(srcEle.get("SUPERID")));
				auditRuleDefXmlDAO.saveAuditSort(srcEle);
				auditRuleDefXmlDAO.updateLevelHasSuperID(srcEle);
			}else{
				auditRuleDefXmlDAO.saveAuditSort(srcEle);
				auditRuleDefXmlDAO.updateLevelNullSuperID(srcEle);
			}
			
		 }else{//存在
			 guidMap.put((String)srcEle.get("CHECKSORTID"), targetEle.get("CHECKSORTID"));
			 String engFlagSrc= (String)srcEle.get("ENDFLAG");
			 String targetTag=targetEle.get("ENDFLAG");
			 if("1".equals(engFlagSrc) && "0".equals(targetTag)){
				 throw new ServiceException("","已存在分类 "+(String)srcEle.get("CHECKSORTNAME")+" 不是末级节点,不能挂审核定义，请修改审核分类的名称",false);
			 }
		 }
	 }
	 return guidMap;
	}

	/**
	* @Title: parseAuditSortData
	* @Description: 解析审核规则分类数据
	* @param @param paramMap
	* @param @param root
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	 */
	private List<Map<String,Object>> parseAuditSortData(HashMap<String, Object> paramMap, Element root){
		List<Element> sortNodes=root.selectSingleNode("AuditSorts").selectNodes("AuditSort");
		List<Map<String,Object>> sortList =new ArrayList<Map<String,Object>>();
		if(null!=sortNodes && sortNodes.size()>0){
			List<AuditRuleColPO> colList=findAuditSortColList();
			for(Element ele:sortNodes){
				sortList.add(parseXmlData(ele,colList));
			}
		}
		return sortList;
	}

	/**
	* @Title: parseXmlData
	* @Description: 解析xml数据 到 AuditRuleColPO list
	* @param @param element
	* @param @param colList
	* @param @return
	* @return Map<String,Object>
	* @throws
	 */
	private Map<String,Object> parseXmlData(Element element, List<AuditRuleColPO> colList) {
		Map<String,Object>  dataMap=new HashMap<String,Object>();
		for(AuditRuleColPO col:colList){
			Element childNode=element.element(col.getColDbName());
			String v=childNode.getTextTrim();
			if(null!=v && !"".equals(v)){
				dataMap.put(col.getColDbName(),v );
			}
		}
		return dataMap;
	}

	@Override
	public List<Map<String, String>> findViewToTable(Map<String, Object> paraMap)
			throws ServiceException {
		return auditRuleDefXmlDAO.findViewToTable(paraMap);
	}

	@Override
	public List<Map<String, String>> findInputObjToAuditObj(
			Map<String, Object> paraMap) throws ServiceException {
		return auditRuleDefXmlDAO.findInputObjToAuditObj(paraMap);
	}

	@Override
	public List<AuditRuleColPO> findViewToTableColList() {
		List<AuditRuleColPO> dataList = new ArrayList<AuditRuleColPO>();
		dataList.add(new AuditRuleColPO("视图ID","VIEWID",4,false));
		dataList.add(new AuditRuleColPO("物理表ID","TABLEID",4,false));
		dataList.add(new AuditRuleColPO("物理表条件","TABWHERE",5,true));
		return dataList;
	}

	@Override
	public List<AuditRuleColPO> findInputObjToAuditObjColList() {
		List<AuditRuleColPO> dataList = new ArrayList<AuditRuleColPO>();
		dataList.add(new AuditRuleColPO("录入对象ID","INPUTTABLEID",4,false));
		dataList.add(new AuditRuleColPO("审核对象ID","MODELID",4,false));
		return dataList;
	}

	
  

}

