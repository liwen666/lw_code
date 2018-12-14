package commons.setting.external.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.Constants;
import com.tjhq.commons.dao.UtilsMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.service.impl.DictTFactorService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.setting.external.dao.FormulaOuterMapper;
import com.tjhq.commons.setting.external.po.CellFormulaPo;
import com.tjhq.commons.setting.external.service.IFormulaOuterService;
import com.tjhq.commons.setting.formula.dao.FormulaDAO;
import com.tjhq.commons.setting.formula.po.FormulaTFormulaDefPO;
import com.tjhq.commons.setting.formula.service.IRefreshFormulaService;
import com.tjhq.commons.setting.input.web.ConverTables;

@Service
@Transactional(readOnly = true)
public class FormulaOuterService implements IFormulaOuterService {

	@Resource
	private FormulaOuterMapper formulaOuterMapper;
	@Resource
	private DictTFactorService dictTFactorService;
	@Resource
	private IRefreshFormulaService refreshFormulaService;
	@Resource
	private FormulaDAO formulaMapper;
	@Resource
	private UtilsMapper utilsDAO;

	private static String floatCol = "ORDERID|FDCODE|ISINSERT|LEVELNO|ISLEAF|ISQZX|ISDJ|ORIGCODE";

	public Map<String, Object> oneTableFormula(String data, String tableId)
			throws Exception {
		// 根据TableID 获取表中 叶子节点
		List<DictTFactorPO> colList = dictTFactorService
				.getDictTFactorByTableidAndType(tableId, "1");
		Map dataMap = (new ObjectMapper()).readValue(data, HashMap.class);
		if (dataMap.containsKey("TASKID")) {
			utilsDAO.setTaskParam((String)dataMap.get("TASKID"));
		}

		int colSize = colList.size();
		StringBuffer dataBuffer = new StringBuffer(1024 * colSize);

		// 设置列
		Pattern is_setting = Pattern
				.compile(floatCol, Pattern.CASE_INSENSITIVE);// 显示列

		if (colList != null && colSize > 0) {
			for (DictTFactorPO po : colList) {
				Object colValue = null;
				String col = po.getDbcolumnname().toUpperCase();
				if (is_setting.matcher(col).find())
					colValue = dataMap.get("c_" + col);
				else
					colValue = dataMap.get(col);

				if (1 == po.getDatatype() || 2 == po.getDatatype()) {
					if (ConverTables.isNotNull(colValue)) {
						dataBuffer.append(colValue);
					} else {
						dataBuffer.append(0);
					}

				} else {
					if (ConverTables.isNotNull(colValue)) {
						colValue = ((String) colValue).replace("'", "''");
						dataBuffer.append(" '" + colValue + "' ");
					} else {
						dataBuffer.append("''");
					}

				}

				dataBuffer.append(" " + col);
				dataBuffer.append(" , ");
			}
		}
		int dataLength = dataBuffer.toString().lastIndexOf(",");
		String dataString = dataBuffer.toString().substring(0, dataLength);
		// 将数据 进行封装
		Map<String, String> fromula = new HashMap<String, String>();
		fromula.put("data", dataString);
		fromula.put("tableId", tableId);
		fromula.put("result", "");

		formulaOuterMapper.oneTableFormula(fromula);
		String returnValue = fromula.get("result");

		return this.verifyReturnData(returnValue);
	}

	public Map<String, Object> verifyReturnData(String returnValue) {
		Map<String, Object> data = new HashMap<String, Object>();

		if (ConverTables.isNotNull(returnValue)) {
			String value[] = returnValue.split(",");
			// 组成Map 格式
			for (String col : value) {
				if (col.indexOf("=") != -1) {
					data.put(col.split("=")[0], col.split("=")[1]);
				}
			}
		}
		return data;
	}

	// 返回公式列 | 公式影响列
	public Map<String, List<String>> getForComCol_RefColumn(String tableId) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<Map<String, Object>> formula = formulaOuterMapper
				.getForComCol_RefColumn(tableId);

		List<String> forComColList = new ArrayList<String>();
		List<String> refColumnList = new ArrayList<String>();

		for (Map<String, Object> data : formula) {
			String forComCol = (String) data.get("FORCOMCOL");
			String refColumn = (String) data.get("REFCOLUMN");
			if (ConverTables.isNotNull(refColumn)) {
				String col[] = refColumn.split(",");
				for (String s : col) {
					if (ConverTables.isNotNull(s))
						refColumnList.add(s);
				}
			}
			forComColList.add(forComCol);
		}
		map.put("forComCol", forComColList);
		map.put("refColumn", refColumnList);

		return map;
	}

	/**
	 * 处理单元格公式 | 转换为 前台需要的格式
	 * 
	 * 〖FDCODE='*'〗{SZL1}=〖FDCODE='*'〗{SZL1}+〖FDCODE='*'〗{SZL1}
	 * 
	 * [{ resultCell : { row : { FDCODE : '*', }, col : { dbcolumnname : 'SZL1'
	 * } }, resulteedCell : [{ row : { FDCODE : '*', }, col : { dbcolumnname :
	 * 'SZL1' } }], // 后台返回的公式脚本，应支持JS运行 formula :"return formulaCell({row:{FDCODE:'*'},col:{dbcolumnname : 'SZL1'}}) + formulaCell({row:{FDCODE:'*'},col:{dbcolumnname : 'SZL1'}})"
	 * }]
	 */

	public List<Map<String, Object>> getCellFormulaFormat(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		map.put("formulaType", "1");
		List<FormulaTFormulaDefPO> formulaList = formulaMapper
				.selectFormulaData(map);

		List<Map<String, Object>> cellFormula = new ArrayList<Map<String, Object>>();
		for (FormulaTFormulaDefPO po : formulaList) {
			Map<String, Object> cellMap = new HashMap<String, Object>();

			String formulaDef = po.getFormulaDef(); // 公式内容

			List<Map<String, Object>> resulteedCell = new ArrayList<Map<String, Object>>();
			// 处理单个公式
			while (formulaDef.indexOf(Constants.WHERE_START) != -1
					&& formulaDef.indexOf(Constants.COMCOL_END) != -1) {
				String temp = formulaDef.substring(formulaDef
						.indexOf(Constants.WHERE_START), formulaDef
						.indexOf(Constants.COMCOL_END) + 1);

				String where = temp.substring(temp
						.indexOf(Constants.WHERE_START) + 1, temp
						.indexOf(Constants.WHERE_END));
				String comcol = temp.substring(temp
						.indexOf(Constants.COMCOL_START) + 1, temp
						.indexOf(Constants.COMCOL_END));

				CellFormulaPo cell = new CellFormulaPo();
				if (where.indexOf("=") != -1) { // 行
					String row[] = where.split("=");
					cell.setRow(row[0], row[1].replaceAll("'", ""));
				}
				cell.setCol("dbcolumnname", comcol); // 列

				if (!ConverTables.isNotNull(cellMap.get("resultCell"))) {
					cellMap.put("resultCell", cell.getResultCell());
				} else {
					resulteedCell.add(cell.getResultCell());
				}

				formulaDef = formulaDef.replace(temp, "");
			}
			cellMap.put("resulteedCell", resulteedCell);

			String formulaDefEng = po.getFormulaDefEng();
			// 处理整个公式
			while (formulaDefEng.indexOf(Constants.WHERE_START) != -1
					&& formulaDefEng.indexOf(Constants.WHERE_START) != -1) {
				String temp = formulaDefEng.substring(formulaDefEng
						.indexOf(Constants.WHERE_START), formulaDefEng
						.indexOf(Constants.WHERE_END) + 1);
				String name = temp.replace(Constants.WHERE_START, "").replace(
						Constants.WHERE_END, "");
				String rowString = "";
				if (name.indexOf("=") != -1) {
					String row[] = name.split("=");
					rowString = "this.formulaCell(｛row:｛\"" + row[0] + "\":"
							+ row[1] + "｝,";
				}
				formulaDefEng = formulaDefEng.replace(temp, rowString);
			}
			while (formulaDefEng.indexOf(Constants.COMCOL_START) != -1
					&& formulaDefEng.indexOf(Constants.COMCOL_END) != -1) {
				String temp = formulaDefEng.substring(formulaDefEng
						.indexOf(Constants.COMCOL_START), formulaDefEng
						.indexOf(Constants.COMCOL_END) + 1);
				String name = temp.replace(Constants.COMCOL_START, "").replace(
						Constants.COMCOL_END, "").replace(
						Constants.COLUMN_START, "").replace(
						Constants.COLUMN_END, "");

				String colString = "col:｛\"dbcolumnname\":\"" + name + "\"｝｝)";

				formulaDefEng = formulaDefEng.replace(temp, colString);
			}
			// 处理后的公式结果
			formulaDefEng = "return "
					+ formulaDefEng.replaceAll("｛", Constants.COMCOL_START)
							.replaceAll("｝", Constants.COMCOL_END) + ";";
			cellMap.put("formula", formulaDefEng);

			cellFormula.add(cellMap);

			System.out.println(cellFormula);

		}
		return cellFormula;
	}

	// 是否存在公式
	public int existsFormulaCount(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);

		return formulaOuterMapper.existsFormulaCount(map);
	}

	@Override
	public boolean isDefineFormula(String tableID) {
		if (formulaOuterMapper.isDefineFormula(tableID) > 0) {
			return true;
		}
		return false;
	}

	// 调用存储过程、调用表间公式
	public boolean betweenTableFormula(String appID, String tableId,
			String agencyId) {
		try {
			formulaOuterMapper.betweenTableFormula(appID, tableId, agencyId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 取数列公式
	public boolean calculateFomula_A8(String tableId, String agencyId) {
		try {
			formulaOuterMapper.calculateFomula_A8(tableId, agencyId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 取数复杂公式
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean calculateFomula_A0(String tableId, String agencyId) throws Exception {
		try {
			formulaOuterMapper.calculateFomula_A0(tableId, agencyId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}
	
	@Override
	public List<String> calculateCellFormula(String tableID,
			String formulaCell, String refFormulaCell, Map<String, Object> paramMap) {
		if (paramMap.containsKey("mainTaskID")) {
			utilsDAO.setTaskParam((String)paramMap.get("mainTaskID"));
		}
		Map<String, String> params = new HashMap<String, String>();
        params.put("tableID", tableID);
		params.put("formulaCell", formulaCell);
		params.put("refFormulaCell", refFormulaCell);
		params.put("result", "");
		formulaOuterMapper.calculateCellFormula(params);
		String formulaData = params.get("result"); 
		List<String> resultList = new ArrayList<String>();
		if (formulaData == null) return resultList;
		for (String data : formulaData.split("],")) {
			resultList.add(data.substring(1, data.endsWith("]") ? data.length() - 1 : data.length()));
		}
		return resultList;
	}
	@Override
	public List<Map<String, String>> selectFormulaColumn(String tableID,
			String formulaType) {

		return formulaMapper.selectFormulaColumn(tableID, formulaType);
	}

    @Override
    public void deleteFormulaByTableID(String tableID, String formulaType) throws ServiceException {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("tableID", tableID);
        paramMap.put("formulaType", formulaType);
       try {
        formulaOuterMapper.deleteFormulaByTableID(paramMap);
    } catch (Exception e) {
        e.printStackTrace();
        throw new ServiceException(e.getCause().getMessage());
    } 
        
    }

    @Override
    public void refreshFormula(String tableID, String formulaType,String tableType) throws ServiceException {
        try {
            refreshFormulaService.updateFormulaDefChi(tableID, "", formulaType, tableType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
