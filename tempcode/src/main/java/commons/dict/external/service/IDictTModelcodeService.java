package commons.dict.external.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.dict.external.po.DictTFactorcodePO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.setting.external.po.TreeNode;

/**
 * 表接口
 * 
 * @author xujingsi
 * 
 */
public interface IDictTModelcodeService {

	/**
	 * 获取单个DictTModelcodePO
	 * 
	 * @param tableid
	 *            表id
	 * @return DictTModelcodePO
	 */
	public DictTModelcodePO getDictTModelcodePOByID(String tableid);

	/**
	 * 获取单个DictTModelcodePO
	 * 
	 * @param dbtablename
	 *            物理表名称
	 * @return DictTModelcodePO
	 */
	public DictTModelcodePO getDictTModelcodePOByDBtableName(String dbtablename);

	/**
	 * 获取单个DictTModelcodePO
	 * 
	 * @param Name
	 *            名称
	 * @return DictTModelcodePO
	 */
	public List<DictTModelcodePO> getDictTModelcodePOByName(String Name);

	/**
	 * 根据参数appid 获取引用代码表
	 * 
	 * @param paramMap 系统ID
	 * @return List<DictTModelcodePO>
	 */
	public List<DictTModelcodePO> findDictTModelcodeByArgs(Map<String, Object> paramMap);

	/**
	 * 获取 DictTModelCode grid 表头
	 * 
	 * @param tableid
	 * @return
	 */
	public Grid getDictTModelCodeHead(String tableid);

	/**
	 * 添加 代码表
	 * 
	 * @param DictTModelcodePO
	 */
	public void insertDictTModelcode(DictTModelcodePO dictTModelcodePO)
			throws Exception;

	/**
	 * 修改 代码表 如该代码表已经被引用，则禁止修改， 返回异常信息 需要try catch 捕获错误提示信息。
	 * 
	 * @param DictTModelcodePO
	 */
	public void updateDictTModelcode(DictTModelcodePO dictTModelcodePO)
			throws Exception;

	/**
	 * 删除 代码表 如该代码表已经被引用，则禁止删除 ，返回异常信息 需要try catch 捕获错误提示信息。
	 * 
	 * @param DictTModelcodePO
	 *            .tableid
	 */
	public void deleteDictTModelcode(String tableid) throws Exception;

	/**
	 * 添加自定义代码表
	 * 
	 * @param dictTModelcodePO
	 * @throws Exception
	 */
	public void insertDictTModelForCustomcode(DictTModelcodePO dictTModelcodePO)
			throws Exception;

	/**
	 * 
	 * @param appId
	 * @param currentValue
	 * @return
	 */

	public DictTModelcodePO findDictTModelName(String appId, String currentValue);

	/**
	 * 取到未登记的平台代码表列表
	 * 
	 * @param appID
	 *            业务系统ID
	 * @return 未登记的平台代码表列表
	 * @throws Exception
	 */
	public List<DictTModelcodePO> getDictModelCodeElementCodeList(String appID)
			throws Exception;

	/**
	 * 登记平台代码表信息到业务系统
	 * @param appID  业务系统ID
	 * @param selectedTableCodeList 选择的平台代码表
	 * @throws Exception
	 */
	public void saveElementCode2DictModelCode(String appID,
                                              List<String> selectedTableCodeList) throws Exception;

	/**
	 * 自定义代码表批量登记到平台
	 * @param dtoList
	 * @throws Exception
	 */
	void toFasp(List<DictTModelcodePO> dtoList) throws Exception;

    /**
     * 
     * @Title: 
     * @Description:用于从平台获取某视图的所有列
     * @param  设定文件
     * @return  List<DictTFactorcodePO>
     * @throws
     */
	public List<DictTFactorcodePO> getElementCode2DictFactorCode(
            String selectedTableCode) throws Exception;
	
	/**
	 *  获取当前where条件是否有效 
	 * @param dbtablename
	 * @param dynamicwhere
	 * @return
	 */
	String valiWhere(String dbtablename, String dynamicwhere);
	
	
	/**
	 * 
	 * @Title: 插入新增代码表 --物理表
	 * @Description: (这里用一句话描述这个方法的作用)
	 * @param  DictTModelcodePO dtm
	 * @return  返回类型void
	 * @throws
	 */
	public void insertDictTModelcodeForPhysics(DictTModelcodePO dtm)  throws Exception;
	/**
	 * DictTDealtypePO  simple data 有自定义数据  父节点
	 * @param appid
	 * @return
	 */
	public List<TreeNode> findDictTModelcodeListForZTree() throws Exception;
	
	/**
	 * 获取DictTModelCode grid 表头 
	 * @param tableid
	 * @param appid 系统id
	 * @return
	 */
	public Grid getDictTDefaultcolHead(String tableid, String appid);
	
	
	public List<DictTModelcodePO> getDictTDefaultcols4Show(String tableId) throws Exception;
	
}
