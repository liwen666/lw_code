package commons.install.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;

public interface IInstallDataBaseService {
	/**
	 * 获取表头定义
	 * @return
	 */
	public Table getDefineTabHead();
	/**
	 * 获取已经安装的数据
	 * @param grid
	 * @return
	 */
	public Object getInstalledData(Grid grid);
	/**
	 * 判断是否存在model表   
	 * @param tableName
	 * @return
	 */
	public Integer hasModel(String tableName);
	/**
	 * 获取地区树
	 * @return
	 */
	public List<Map<String, Object>> getDefaultProvince();
}
