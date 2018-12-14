package commons.dict.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dict.dao.DictTAppregisterMapper;
import com.tjhq.commons.dict.dao.DictTDealtypeMapper;
import com.tjhq.commons.dict.external.po.DictTAppregisterPO;
import com.tjhq.commons.dict.external.po.DictTDealtypePO;
import com.tjhq.commons.dict.service.IDictTDealtypeService;

/**
 * 处理类型表接口
 * 
 * @author xujingsi
 * 
 */
@Service
@Transactional
public class DictTDealtypeService implements IDictTDealtypeService {

	@Resource
	private DictTDealtypeMapper dictTDealtypeMapper;

	@Resource
	private DictTAppregisterMapper dictTAppregisterMapper;

	/**
	 * 添加
	 * 
	 * @param DictTDealtypePO
	 */
	public void insertDictTDealtype(DictTDealtypePO dictTDealtype)
			throws Exception {
		try {
			this.dictTDealtypeMapper.insertDictTDealtype(dictTDealtype);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("添加处理类型报错");
		}
	}

	/**
	 * 修改
	 * 
	 * @param DictTDealtypePO
	 */
	public void updateDictTDealtype(DictTDealtypePO dictTDealtype)
			throws Exception {
		try {
			this.dictTDealtypeMapper.updateDictTDealtype(dictTDealtype);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("修改处理类型报错");
		}
	}

	/**
	 * 删除
	 * 
	 * @param String
	 *            dealid
	 */
	public void deleteDictTDealtype(String dealid) throws Exception {
		try {
			this.dictTDealtypeMapper.deleteDictTDealtype(dealid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("删除处理类型报错");
		}
	}

	/**
	 * *************************************************************************
	 * **********
	 */

	/**
	 * 
	 * @param dealID
	 * @param appID
	 * @return
	 * @throws Exception
	 */
	public DictTDealtypePO getDictTDealtype(String dealID, String appID)
			throws Exception {
		DictTDealtypePO dtd = null;
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("dealID", dealID);
			param.put("appID", appID);
			dtd = this.dictTDealtypeMapper.getDictTDealtype(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("获取处理类型报错");
		}
		return dtd;
	}

	/**
	 * 获取全部
	 * 
	 * @return List<DictTDealtype>
	 */
	public List<DictTDealtypePO> getAllDictTDealtype() throws Exception {
		List<DictTDealtypePO> dtds = null;
		try {
			dtds = this.dictTDealtypeMapper.getAllDictTDealtype();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("获取全部处理类型报错");
		}
		return dtds;
	}

	@Override
	public List<DictTDealtypePO> findDictTDealtypeListForZTree()
			throws Exception {
		List<DictTDealtypePO> listDTDs = null;
		// 全部
		try {
			listDTDs = this.dictTDealtypeMapper.getAllDictTDealtypeLevel();
			for (DictTDealtypePO dtd : listDTDs) {
				if (!"*".equals(dtd.getAppid())) {
					dtd.setSupperid(dtd.getAppid());
				} else {
					dtd.setSupperid("0");
				}
				dtd.setNocheck(false);
			}
			// 假标题
			List<DictTDealtypePO> spperAppid = this.dictTDealtypeMapper
					.getDictTDealtypeForGroupBy();
			for (DictTDealtypePO dtd : spperAppid) {
				DictTAppregisterPO app = this.dictTAppregisterMapper
						.getDictTAppregisterByAppid(dtd.getAppid());
				if (app != null) {
					dtd.setSupperid("-1");
					dtd.setDealid(dtd.getAppid());
					dtd.setNocheck(true);
					dtd.setDealname(app.getAppname());
					listDTDs.add(dtd);
				}
			}

			// 添加通用类型父节点
			DictTDealtypePO d = new DictTDealtypePO();
			d.setSupperid("-1");
			d.setDealid("0");
			d.setNocheck(true);
			d.setDealname("通用");
			d.setOrderid(-1);
			listDTDs.add(0, d);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return listDTDs;
	}
}
