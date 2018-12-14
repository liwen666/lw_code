package commons.setting.formula.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.setting.formula.dao.FormulaDAO;
import com.tjhq.commons.setting.formula.po.FormulaTFormulaDefPO;
import com.tjhq.commons.setting.formula.service.IRefreshFormulaService;
import com.tjhq.commons.setting.input.web.ConverTables;

@Service
@Transactional(readOnly = true)
public class RefreshFormulaService implements IRefreshFormulaService{
	@Resource
	private FormulaDAO formulaMapper;
	@Resource
	private IDictTFactorService dictTFactorService;
	@Resource
	private IDictTModelService dictTModelService;
	@Resource
	private IDictTModelcodeService dictTModelcodeService;
	@Resource
	private FormulaService formulaMethod;

	// 将物理列名 转换为 中文列名
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String updateFormulaDefChi(String tableID, String formulaID,String defineID,String dealType) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tableID", tableID);
		map.put("formulaType", defineID);
		List<FormulaTFormulaDefPO> formulaList = new ArrayList<FormulaTFormulaDefPO>();
		
		if(ConverTables.isNotNull(formulaID)){
			String formula[] = formulaID.split(",");
			for(String id : formula){
				if(ConverTables.isNotNull(id)){
					map.put("formulaID", id);	
					formulaList.addAll(formulaMapper.selectFormulaData(map));
				}
			}
		}else{
			formulaList = formulaMapper.selectFormulaData(map);
		}

		String formulaDefEng = "";
		for(FormulaTFormulaDefPO po : formulaList){
			 formulaDefEng = po.getFormulaDefEng();
			 formulaID = po.getFormulaID();
			 tableID = po.getTableID();
			 String formulaType = po.getFormulaType();
			 /*
			 //左表达式
			 leftFormula = formulaDefEng.substring(0,formulaDefEng.indexOf("}=")+1);
			 //右表达式
			 formulaDefEng = formulaDefEng.replace(leftFormula, "");*/
			 
			 if(formulaType.equals("0")||formulaType.equals("8")||formulaType.equals("A8")){
				 
				 while (formulaDefEng.indexOf(Constants.TT_START) != -1 && formulaDefEng.indexOf(Constants.TT_END) != -1) {
						String temp = formulaDefEng.substring(formulaDefEng.indexOf(Constants.TT_START), formulaDefEng.indexOf(Constants.TT_END) + 2);
						tableID = temp.replace(Constants.TT_START, "").replace(Constants.TT_END, "");
						//将tableId 替换为 表名
						String tableName = this.getTableName(tableID);
						formulaDefEng = formulaDefEng.replace(temp, "〔" + tableName +"〕");
				 }
				 //处理引用表
				 String rfTableID="";
				 while (formulaDefEng.indexOf(Constants.RF_START) != -1 && formulaDefEng.indexOf(Constants.RF_END) != -1) {
						String temp = formulaDefEng.substring(formulaDefEng.indexOf(Constants.RF_START), formulaDefEng.indexOf(Constants.RF_END) + 2);
						rfTableID = temp.replace(Constants.RF_START, "").replace(Constants.RF_END, "");
						String tableName = this.getCodeTableName(rfTableID);	
					
						//在RF().{『 』}
						String str=
							formulaDefEng.substring(formulaDefEng.indexOf(Constants.RF_END) + 3, formulaDefEng.indexOf("}", formulaDefEng.indexOf(".{")));
						while(str.indexOf(Constants.COLUMN_START) != -1 && str.indexOf(Constants.COLUMN_END) != -1){
						String tem = str.substring(str.indexOf(Constants.COLUMN_START), str.indexOf(Constants.COLUMN_END) + 1);
						String colID = tem.replace(Constants.COLUMN_START, "").replace(Constants.COLUMN_END, "");
						String colName = this.getColChiName(colID,rfTableID);	
						str = str.replace(tem, "「"+ colName +"」");
						}
						formulaDefEng=formulaDefEng.replace(formulaDefEng.substring(formulaDefEng.indexOf(").") + 3, formulaDefEng.indexOf("}", formulaDefEng.indexOf(".{"))),str);
						formulaDefEng = formulaDefEng.replace(temp, "【"+ tableName +"】");
					}
				 formulaDefEng=formulaDefEng.replaceAll("【", Constants.RF_START).replaceAll("】", Constants.RF_END);
				// 处理列 
					while (formulaDefEng.indexOf(Constants.COLUMN_START) != -1 && formulaDefEng.indexOf(Constants.COLUMN_END) != -1) {
						String temp = formulaDefEng.substring(formulaDefEng.indexOf(Constants.COLUMN_START), formulaDefEng.indexOf(Constants.COLUMN_END) + 1);
						String name = temp.replace(Constants.COLUMN_START, "").replace(Constants.COLUMN_END, "");
						//将dbColumnName 替换为 列名
						String columnName = this.getColumnName(name,tableID);	
						formulaDefEng = formulaDefEng.replace(temp, "「"+ columnName +"」");
				}
			}
			 
			//单元格公式 TEMPLATEID 替换为 ORDERID | FDCODE
			if(formulaType.equals("1")){
				formulaDefEng = formulaMethod.dealRowTable(formulaDefEng, tableID, dealType, "show");	
			} 

			formulaDefEng = formulaMethod.replaceString(formulaDefEng);
				
			System.out.println("刷新后的公式内容："+formulaDefEng);
			formulaMapper.refreshFormula(formulaDefEng,formulaID);
		}
		return "{\"flag\":\"true\"}";
	}

	//根据物理名 查询 列中文名称
	private String getColumnName(String dbColumnName,String tableID){
		String name = "";
		DictTFactorPO factor = dictTFactorService.getDictTFactorByDBColumnName(dbColumnName, tableID);
		if(factor!=null){
			name = factor.getName();
		}
		return name;
	}
	
	//根据tableID 查询 表名
	private String getTableName(String tableID){
		String TableName = "";
		DictTModelPO model= dictTModelService.getDictTModelByID(tableID, false);
		if(model!=null){
			TableName = model.getName();
		}
		return TableName;
	}
	//根据tableID 查询代码 表名
	private String getCodeTableName(String tableID){
		String TableName = "";
		DictTModelcodePO model= dictTModelcodeService.getDictTModelcodePOByID(tableID);
		if(model!=null){
			TableName = model.getName();
		}
		return TableName;
	}
	//根据columnID查询代码表的列中文名称
	private String getColChiName(String columnID,String tableID){
		String colChiName="";
		//代码表信息
		DictTModelcodePO model= dictTModelcodeService.getDictTModelcodePOByID(tableID);
		String tableName=model.getDbtablename();
		Map<String, String> map=new HashMap<String, String>();
		map.put("columnID", columnID);
		map.put("tableName", tableName);
		Map<String, String> colNames=formulaMapper.getColNameByColID(map);
		if(colNames!=null){
			colChiName = "[" + colNames.get("CODE") + "]" + colNames.get("NAME");
			
		}
		return colChiName;
	}
}
