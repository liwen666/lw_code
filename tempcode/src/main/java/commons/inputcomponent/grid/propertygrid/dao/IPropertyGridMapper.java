package commons.inputcomponent.grid.propertygrid.dao;

import java.util.List;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface IPropertyGridMapper extends SuperMapper  {
	    /***
	    * 按树型结构获取列
	    * @Title: getGroupGridDataCount 
	    * @Description: TODO(这里用一句话描述这个方法的作用) 
	    * @param @param map
	    * @param @return
	    * @param @throws Exception    设定文件 
	    * @return List    返回类型 
	    * @throws
	     */
	  public List<Object> getColumnForTree(String tableid)throws Exception;
}
