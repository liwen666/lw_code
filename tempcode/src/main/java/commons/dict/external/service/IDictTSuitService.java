package commons.dict.external.service;

import java.util.List;

import com.tjhq.commons.dict.external.po.DictTAppregisterPO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.setting.external.po.TreeNode;


/**
 * 套表接口
 * @author xujingsi
 *
 */
public interface IDictTSuitService {


	/**
	 * 获取全部AppId
	 * @return List<DictTAppregister>
	 */
	public List<DictTAppregisterPO> getAllDictTAppregister();
	/**
	 * 获取单个套表 BY ID
	 * @param suitid ID
	 * @return DictTSuit
	 */
   public DictTSuitPO getDictTSuitByID(String suitid);
   
   
   
	/**
	 * 获取某个级别下的全部套表；（树状结构）
	 * @param appid 应用ID
	 * @param suitid 套表ID(默认"0" 获取全部)
	 * @param childTable 是否获取末端节点的真实表
	 * @return
	 */
  public List<DictTSuitPO> getDictTSuitsForTree(String appid, String suitid, boolean childTable);
  
  /**
	 * 获取某个级别下的全部套表；（普通结构）
	 * @param appid 应用ID
	 * @param suitid 套表ID(默认"0" 获取全部)
	 * @param childTable 是否获取末端节点的真实表
	 * @return
	 */
  public List<DictTSuitPO> getDictTSuits(String appid, String suitid, boolean childTable);

  /**
	 * 获取某个级别下的全部套表；（树状结构）(带用户ID)
	 * @param appid 应用ID
	 * @param userId 用户ID
	 * @param suitid 套表ID(默认"0" 获取全部)
	 * @param childTable 是否获取末端节点的真实表
	 * @return
	 */
//  public List<DictTSuitPO> getDictTSuitsByUserForTree(String appid,String suitid,boolean childTable,String userId);



  /**
	 * 获取某个级别套表下的全部套表；（普通结构）(带用户ID)
	 * @param appid 应用ID
	 * @param suitid 套表ID(默认"0" 获取全部)
	 * @param childTable 是否获取末端节点的真实表
	 * @return
	 */
//  public List<DictTSuitPO> getDictTSuitsByUser(String appid,String suitid,boolean childTable,String userId);

  
  /**
 	 * 获取某个级别套表下的子套表；（普通结构）
 	 * @param appid 应用ID
 	 * @param suitid 套表ID(默认"0" 获取全部)
 	 * @return
 	 */
  public List<DictTSuitPO> getDictTSuitsBySupperid(String appid, String suitid);
  
  public List<TreeNode> getDictTSuitTree(String appid, String showApp, String string);
  
  public List<TreeNode> getAllDictTSuitWithModelTree(String firAppid, String showApp); 
  
  /**
   * 得到指定业务类型下所有可定义公式报表树
   * @param appID
   * @return 可定义公式报表树
   */
  public List<TreeNode> getAllDictTSuitWithModelTree4Formula(String appID);
}
