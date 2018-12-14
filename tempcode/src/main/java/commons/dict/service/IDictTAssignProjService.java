package commons.dict.service;

import java.util.List;

import com.tjhq.commons.setting.input.po.TreeNode;

public interface IDictTAssignProjService {
	/**
	 * 保存项目下发设置表
	 * @param paramMap
	 */
	public void saveTable(List<String> tableIdList);

	/**
	 * 查项目下发设置表
	 */
	public List<TreeNode> selectAssignProj(String appID) throws Exception;
}
