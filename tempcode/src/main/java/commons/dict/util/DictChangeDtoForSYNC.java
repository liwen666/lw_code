package commons.dict.util;

import gov.mof.fasp2.dic.dataelement.dto.DataElementDTO;
import gov.mof.fasp2.dic.dataset.dto.DataSetDTO;
import gov.mof.fasp2.dic.enumclass.DataElementEnum;
import gov.mof.fasp2.dic.enumclass.TableTypeEnum;
import gov.mof.fasp2.dic.table.dto.DicColumnDTO;
import gov.mof.fasp2.dic.table.dto.DicTableDTO;
import gov.mof.fasp2.dic.util.DicUtil;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import com.tjhq.commons.dict.external.po.DictElementPO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTFactorcodePO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.inputcomponent.constants.DataType;

/**
 * 
 * @author xujingsi
 * 
 */
public class DictChangeDtoForSYNC {

	public static DictElementPO changeDictElementTreeNode(
			DataElementDTO dataElement) {
		DictElementPO dictElementTreeNode = new DictElementPO();
		dictElementTreeNode.setId(dataElement.getGuid());
		dictElementTreeNode.setCode(dataElement.getCode());
		dictElementTreeNode.setName("[" + dataElement.getCode() + "]"
				+ dataElement.getName());
		dictElementTreeNode.setpId(dataElement.getTypeguid());
		dictElementTreeNode.setElementCode(dataElement.getElementcode());
		formatAnalyzeDataElementType(dictElementTreeNode, dataElement);
		return dictElementTreeNode;
	}

	public static void formatAnalyzeDataElementType(DictElementPO dictElement,
			DataElementDTO dataElement) {
		String[] numbers = DicUtil.numberOfFormat(dataElement.getFormat());
		DataElementEnum datatype = dataElement.getDatatype();
		if (dataElement != null && dataElement.getFormat() != null
				&& dataElement.getFormat().length() > 0) {
			numbers = DicUtil.numberOfFormat(dataElement.getFormat());
			datatype = dataElement.getDatatype();
		}
		// 字符型
		if (DataElementEnum.DE_DATATYPE_CHAR.equals(datatype)
				|| dataElement.getDatatype().compareTo(
						DataElementEnum.DE_DATATYPE_HCHAR) == 0) {
			dictElement.setDataType(DataType.STRING);
			if (!"".equals(numbers[0])) {
				dictElement.setDataLength(Integer.parseInt(numbers[0]));
			}
		}
		// 日期型
		else if (DataElementEnum.DE_DATATYPE_DATE.equals(datatype)) {
			dictElement.setDataType(DataType.STRING);
			dictElement.setDataLength(17);
		}
		// 数字型
		else if (DataElementEnum.DE_DATATYPE_NUM.equals(datatype)) {
			dictElement.setDataType(DataType.NUMBER);
			// numbers[0]=numbers[0].equals("")?"35":numbers[0];
			dictElement.setDataLength(Integer.parseInt(numbers[0]));
			if (!"".equals(numbers[1])) {
				dictElement.setDataScale(Integer.parseInt(numbers[1]));
			}
			// 整型
			else {
				dictElement.setDataType(DataType.INT);
				dictElement.setDataScale(0);
			}
		}
	}

	/**
	 * -------------------同步接口
	 * 对象转换----------------------------------------------
	 * ------------------------------------
	 */
	/**
	 * 龙图 接口 <字段> 对象转换
	 * 
	 * @param dictTFactor
	 * @return
	 * @throws Exception
	 */
//	public static DicColumnDTO changeDicColumnDTO(DictTFactorPO dictTFactor)
//			throws Exception {
//
//		DicColumnDTO dicColumn = null;
//		if (dictTFactor != null) {
//			try {
//				dicColumn = new DicColumnDTO();
//				dicColumn.setColumnid(dictTFactor.getColumnid());
//				dicColumn.setColumncode(dictTFactor.getDbcolumnname()
//						.toUpperCase());
//				dicColumn.setCsid(dictTFactor.getCsid());
//				dicColumn.setDatalength(dictTFactor.getDatalength());
//				DataElementEnum dee = null;
//				Integer type = dictTFactor.getDatatype();
//				if (type == 3 || type == 6 || type == 7 || type == 9) {
//					dee = DataElementEnum.DE_DATATYPE_CHAR;
//				} else if (type == 1 || type == 2) {
//					dee = DataElementEnum.DE_DATATYPE_NUM;
//				} else if (type == 8) {
//					dee = DataElementEnum.DE_DATATYPE_DATE;
//				} else {
//					dee = DataElementEnum.DE_DATATYPE_CHAR;
//				}
//				dicColumn.setDatatype(dee);
//				dicColumn.setDbcolumncode(dictTFactor.getDbcolumnname());
//				dicColumn.setDeid(dictTFactor.getDeid());
//				dicColumn.setGuid(dictTFactor.getColumnid());
//				dicColumn.setName(dictTFactor.getName());
//				dicColumn.setOrderno(dictTFactor.getOrderid());
//				dicColumn.setTablecode(dictTFactor.getTableid());
//				dicColumn.setProvince(SecureUtil.getUserSelectProvince());
//				dicColumn.setYear(SecureUtil.getUserSelectYear());
//				// dicColumn.setExp(dictTFactor.getExtprop());
//				// dicColumn.set(dictTFactor.getIsupdate());
//				// dicColumn.setIsuses(dictTFactor.geti);
//				// dicColumn.setNullable("1".equals(dictTFactor.getNullable())?true:false);
//				// dicColumn.setScale(dictTFactor.getScale());
//				// dicColumn.setVersion(dictTFactor.getVersion());
//
//			} catch (Exception e) {
//				throw new Exception("转换数据元注册列出错");
//			}
//		}
//		return dicColumn;
//	}

	/**
	 * 龙图 接口 <表> 对象转换
	 * 
	 * @param dictTFactor
	 * @return
	 * @throws Exception
	 */

	public static DicTableDTO changeDicTableDTO(DictTModelPO dictTModel)
			throws Exception {

		DicTableDTO dicTable = null;
		if (dictTModel != null) {
			try {
				dicTable = new DicTableDTO();
				//登记到平台的业务类型小写
				dicTable.setAppid(dictTModel.getAppid().toLowerCase());
				dicTable.setDbtabname(dictTModel.getDbtablename());
				dicTable.setGuid(dictTModel.getTableid());
				dicTable.setTablecode(dictTModel.getTableid());
				dicTable.setName(dictTModel.getName());
				dicTable.setRemark(dictTModel.getName());
				dicTable.setTabletype(TableTypeEnum.getEnum(Integer
						.parseInt(dictTModel.getTabletype())));
				dicTable.setViewtablename(dictTModel.getDbtablename());
				// dicTable.setExp(dictTModel.getExtprop());
				// dicTable.setIsshow("1".equals(dictTModel.getIsshow())?true:false);
				// dicTable.setIssys("1".equals(dictTModel.getIsreserved())?true:false);
				// dicTable.setListCol(ilistColstCol);
				// dicTable.setOrderno(dictTModel.getOrderid());
				// dicTable.setPartition(dictTModel.getIspartition());
				// dicTable.setRemark(dictTModel.getRemark());
				// dicTable.setCreatetime(new Date());
				dicTable.setYear(SecureUtil.getUserSelectYear());
				dicTable.setProvince(SecureUtil.getUserSelectProvince());
			} catch (Exception e) {
				throw new Exception("转换数据元注册列出错");
			}
		}
		return dicTable;
	}

	/**
	 * --------------------代码表-
	 * 对象转换----------------------------------------------
	 * ---------------------------
	 */
	/**
	 * 龙图 接口 <代码表 的 表> 对象转换
	 * 
	 * @param DictTModelcodePO
	 * @return
	 * @throws Exception
	 */
	public static DictTModelcodePO changeDicTableDTO(DataSetDTO dataset,
			DicTableDTO dicTableDTO) throws Exception {

		DictTModelcodePO dtm = null;
		if (dicTableDTO != null) {
			dtm = new DictTModelcodePO();
			dtm.setTableid(dicTableDTO.getTablecode());
			dtm.setAppid(dicTableDTO.getAppid());
			dtm.setFaspcsid(dataset.getGuid());
			// dtm.setDbtablename(dicTableDTO.getDbtabname().toUpperCase());
			String dbName = dicTableDTO.getViewtablename() == null ? dicTableDTO
					.getDbtabname().toUpperCase() : dicTableDTO
					.getViewtablename().toUpperCase();
			dbName = dbName.replaceFirst("_T_", "_V_");
			dtm.setDbtablename(dbName);
			dtm.setName("[" + dataset.getCode() + "]" + dataset.getName());
			dtm.setOrderid(dicTableDTO.getOrderno());
			// dtm.setSqlcon(sqlcon);
			// dtm.setDynamicwhere(dicTableDTO.get);
			dtm.setIslvl("1");
			dtm.setIsorgid("0");
			dtm.setIsrepbase("0");

		}
		return dtm;
	}

	/**
	 * 龙图 接口 <代码表 的 列> 对象转换
	 * 
	 * @param DictTFactorcodePO
	 * @return
	 * @throws Exception
	 */
	public static DictTFactorcodePO changeDicColumnDTO(DicColumnDTO dicColumnDTO)
			throws Exception {

		DictTFactorcodePO dtf = null;
		if (dicColumnDTO != null) {
			dtf = new DictTFactorcodePO();
			dtf.setName(dicColumnDTO.getName());
			dtf.setTableid(dicColumnDTO.getTablecode());
			dtf.setColumnid(dicColumnDTO.getColumnid());
			dtf.setCsid(dicColumnDTO.getCsid());
			dtf.setDatalength(dicColumnDTO.getDatalength());
			DataElementEnum dee = dicColumnDTO.getDatatype();
			Integer type = null;
			if (DataElementEnum.DE_DATATYPE_CHAR.equals(dee)) {
				type = 3;
			} else if (DataElementEnum.DE_DATATYPE_NUM.equals(dee)) {
				type = 1;
			} else if (DataElementEnum.DE_DATATYPE_DATE.equals(dee)) {
				type = 8;
			} else {
				type = 3;
			}
			dtf.setDatatype(type);
			dtf.setDbcolumnname(dicColumnDTO.getDbcolumncode().toUpperCase());
			dtf.setDefaultvalue(dicColumnDTO.getDefaultvalue());
			dtf.setDeid(dicColumnDTO.getDeid());
			dtf.setOrderid(dicColumnDTO.getOrderno());
			dtf.setScale(dicColumnDTO.getScale());
			dtf.setIsreserve("0");
			dtf.setIsvisible("0");
			// dtf.setBgtlvl(dicColumnDTO.get);

		}
		return dtf;
	}

}
