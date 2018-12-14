package commons.dict.external.service;

import java.util.List;

import com.tjhq.commons.dict.external.po.DictTFactorcodePO;
import com.tjhq.commons.inputcomponent.po.Grid;

/**
 * 列代码接口
 * 
 * @author xujingsi
 * 
 */
public interface IDictTFactorcodeService {

	/**
	 * 获取列
	 * 
	 * @param columnid
	 *            列ID
	 * @return DictTFactorcodePO
	 */
	public DictTFactorcodePO getDictTFactorcodePOByColumnId(String columnid)
			throws Exception;

	/**
	 * 获取列
	 * 
	 * @param dbcolumnname
	 *            列名称
	 * @return
	 */
	public DictTFactorcodePO getDictTFactorcodePObyDBColumnName(String tableID,
                                                                String dbcolumnname) throws Exception;

	/**
	 * 获取某个表下的所有列
	 * 
	 * @param tableid
	 *            表ID
	 * @return List<DictTFactorcodePO>
	 */
	public List<DictTFactorcodePO> getDictTFactorcodePOsByTableIdName(
            String tableid, String name, String columnid) throws Exception;

	/**
	 * 获取某个表下的所有列
	 * 
	 * @param tableid
	 *            表ID
	 * @return List<DictTFactorcodePO>
	 */
	public List<DictTFactorcodePO> getDictTFactorcodePOsByTableId(String tableid)
			throws Exception;

	/**
	 * 获取 DictTFactorCode grid 表头
	 * 
	 * @param tableid
	 * @return
	 */
	public Grid getDictTFactorCodeHead(String tableid);

	/**
	 * 添加 代码表的列
	 * 
	 * @param DictTFactorcodePO
	 */
	public void insertDictTFactorcode(DictTFactorcodePO dictTFactorcodePO)
			throws Exception;

	/**
	 * 修改 代码表的列 如该列已经被引用，则禁止修改 返回异常信息 需要try catch 捕获错误提示信息。
	 * 
	 * @param DictTFactorcodePO
	 */
	public void updateDictTFactorcode(DictTFactorcodePO dictTFactorcodePO)
			throws Exception;

	/**
	 * 删除 代码表的列 如该列已经被引用，则禁止删除， 返回异常信息 需要try catch 捕获错误提示信息。
	 * 
	 * @param dictTFactorcodePO
	 *            .columnid
	 */
	public void deleteDictTFactorcode(String columnid) throws Exception;

	/** 
	 * 獲取當前代碼表是否被物理表引用過
	 * @param tableID
	 * @return
	 */
	boolean getCodeTableExist(String tableID);
	
}
