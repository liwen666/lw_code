package commons.dict.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dict.dao.DictTAssignProjMapper;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.dict.service.IDictTAssignProjService;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.web.ConverTables;

@Service
@Transactional(readOnly = true)
public class DictTAssignProjService implements IDictTAssignProjService {

	@Resource
	private IDictTSuitService dictTSuitService;
	@Resource
	private DictTAssignProjMapper dictTAssignProjMapper;

	@Override
	public void saveTable(List<String> tableIdList) {
		dictTAssignProjMapper.deleteDictTAssignProj();
		dictTAssignProjMapper.insertDictTAssignProj(tableIdList);
	}

	@Override
	public List<TreeNode> selectAssignProj(String appID) throws Exception {
		List<DictTSuitPO> suitList = dictTSuitService.getDictTSuits(appID, "0",
				true);

		List<TreeNode> treeList = new ArrayList<TreeNode>();
		List<String> assignProjTableList = dictTAssignProjMapper .selectDictTAssignProj();

		treeList = treeData(treeList, suitList, assignProjTableList);
		return treeList;
	}

	private List<TreeNode> treeData(List<TreeNode> treeList,
			List<DictTSuitPO> list, List<String> assignProjTableList)
			throws Exception {
		for (DictTSuitPO suit : list) {
			TreeNode tree = new TreeNode();
			tree.setId(suit.getSuitid());
			tree.setName(suit.getSuitname());
			String superId = suit.getSuperid();
			if ("0".equals(superId)) {
				tree.setPId(suit.getAppid());
			} else {
				tree.setPId(suit.getSuperid());
			}
			tree.setIsLeaf(suit.getIsleaf());
			tree.setLevelNo(suit.getLevelno());
			tree.setAppID(suit.getAppid());
			tree.setIsParent("true");
			tree.setOpen(true);
			treeList.add(tree);
			// 物理表
			List<DictTModelPO> modelList = suit.getDictTModelList();

			if (modelList != null && modelList.size() > 0) {
				for (DictTModelPO model : modelList) {
					TreeNode child = new TreeNode();
					child.setId(model.getTableid());
					child.setName(model.getName());
					child.setPId(model.getSuitid());
					child.setIsLeaf(suit.getIsleaf());
					child.setDealType(model.getDealtype()); // 表处理类型
					child.setDealName(model.getDealName());
					child.setTableType(model.getTabletype()); // 表类型
					child.setAppID(model.getAppid());
					child.setIsParent("false");
					if (assignProjTableList != null && assignProjTableList.size() > 0
							&& assignProjTableList.contains(model.getTableid())) {
						child.setChecked("true");
					}
					treeList.add(child);
				}
			}
			List<DictTSuitPO> suitList = suit.getDictTSuitList();
			if (ConverTables.isNotNullList(suitList)) {
				treeData(treeList, suitList, assignProjTableList);
			}
		}
		return treeList;
	}
}
