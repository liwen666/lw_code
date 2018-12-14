package commons.setting.dataaudit.auditrule.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.po.TreePO;

/**
* @ClassName: IAuditRuleTypeService
* @Description: 审核类型 审核分类 服务接口
* @author xiehongtao
* @date 2017-6-27 上午9:43:35
*
 */
public interface IAuditRuleTypeService {
	
	/**
	 *得到审核分类
	 * @param appId
	 * @return
	 */
	public List<TreePO> getCheckTypeTree(String appId);
	
	/**
	 * 得到审核类型表的列定义
	 * @return
	 */
	public Table getCheckTypeTableDefine();
	
	/**
	* @Title: getCheckTypeTableData
	* @Description:获取审核类型表的数据
	* @param @return
	* @return Table
	* @throws
	 */
	public Object getCheckTypeTableData(String grid);
	
	/**
	* @Title: delCheckSort
	* @Description: 删除审核类型数据
	* @param @param grid
	* @param @return
	* @return Object
	* @throws
	 */
	public Object delCheckType(String grid);
	
	/**
	* @Title: saveCheckType
	* @Description: 保存审核类型数据
	* @param @param grid
	* @param @return
	* @return Object
	* @throws
	 */
	public Object saveCheckType(String grid);
	
	/**
	* @Title: checkLvUnique
	* @Description: 验证 审核类别的序号唯一性
	* @param @param m
	* @param @return
	* @param @throws Exception
	* @return int
	* @throws
	 */
	public int checkLvUnique(Map<String, Object> m) throws Exception;
	
	/**
	 * 检测审核名称
	 * @param m
	 * @return 
	 */
	public int checkAuditSortNameUnique(Map<String, Object> m);
	
	/**
	 * 验证审核分类父亲是否被引用
	 * @param superId
	 * @return
	 */
	public int checkRelationBySuperId(String superId);
	
}
