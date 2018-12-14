package commons.setting.busimanage.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;

/**
 * @ClassName: IBusimanageService
 * @Description: 业务配置对外接口
 * @author: wtf 2016-05-05 下午06:03:50
 */

public interface IBusimanageService {

    /**.
     * 初始化数据对象
     * @param CommonGrid 表格对象
     * @return 相应信息列表带分页
     * @throws Exception 业务异常
     */
	PageInfo getDataList(CommonGrid commonGrid) throws Exception;

	   /**.
     * 初始化数据对象
     * @param CommonGrid 表格对象
     * @return 相应信息列表
     * @throws Exception 业务异常
     */
	List<Map<String, Object>> getDataList() throws Exception;

    /**.
     * 保存更改信息
     * @param Map<String, Object> map
     * @return null
     * @throws ServiceException 业务异常
     */
	void saveData(Map<String, Object> map);


    /**.
     * 保存更改信息
     * @param String grid 
     * @return Object
     * @throws ServiceException 业务异常
     */
	Object saveData(String grid);


    /**.
     * 表结构初始化
     * @return 设置完表格信息的对象
     * @throws ServiceException 业务异常
     */
	Table setTableDefine();
	  /**.
     * 保存更改信息
     * @param Map<String, Object> map
     * @return null
     * @throws ServiceException 业务异常
     */
	void updateData(Map<String, Object> map);

	  /**.
     * 删除更改信息
     * @param Map<String, Object> map
     * @return null
     * @throws ServiceException 业务异常
     */
	void delData(Map<String, Object> map);

}
