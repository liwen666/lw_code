/**
 * @Title: CommonDataBackupService.java
 * @Copyright (C) 2016 太极华青
 * @Description:
 * @Revision 1.0 2016-1-7  CAOK
 */

package commons.inputcomponent.backup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longtu.demo.logger.Logger;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.backup.dao.ICommonDataBackupDAO;
import com.tjhq.commons.inputcomponent.backup.service.ICommonDataBackupService;
import com.tjhq.commons.inputcomponent.constants.Constants;

/**
 * @ClassName: CommonDataBackupService
 * @Description: Description of this class
 * @author: CAOK 2016-1-7 上午09:26:59
 */

@Service
@Transactional(readOnly = true)
public class CommonDataBackupService implements ICommonDataBackupService {

    /**
     * @Fields logger : 日志服务
     */
    Logger logger = Logger.getLogger(CommonDataBackupService.class);

    /**
     * @Fields dictTModelService : 表定义信息服务
     */
    @Resource
    private IDictTModelService dictTModelService;

    /**
     * @Fields dictTFactorService : 列定义信息服务
     */
    @Resource
    private IDictTFactorService dictTFactorService;

    /**
     * @Fields commonDataBackupDAO : 数据备份数据库服务
     */
    @Resource
    private ICommonDataBackupDAO commonDataBackupDAO;

    /**
     * @return dictTModelService
     */
    public IDictTModelService getDictTModelService() {
        return dictTModelService;
    }

    /**
     * @param dictTModelService 要设置的 dictTModelService
     */
    public void setDictTModelService(IDictTModelService dictTModelService) {
        this.dictTModelService = dictTModelService;
    }

    /**
     * @return dictTFactorService
     */
    public IDictTFactorService getDictTFactorService() {
        return dictTFactorService;
    }

    /**
     * @param dictTFactorService 要设置的 dictTFactorService
     */
    public void setDictTFactorService(IDictTFactorService dictTFactorService) {
        this.dictTFactorService = dictTFactorService;
    }

    /**
     * @return commonDataBackupDAO
     */
    public ICommonDataBackupDAO getCommonDataBackupDAO() {
        return commonDataBackupDAO;
    }

    /**
     * @param commonDataBackupDAO 要设置的 commonDataBackupDAO
     */
    public void setCommonDataBackupDAO(ICommonDataBackupDAO commonDataBackupDAO) {
        this.commonDataBackupDAO = commonDataBackupDAO;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void backupData(String tableID, String bakWhere) throws ServiceException {
        backupData(tableID, bakWhere, Constants.BakType.BAK_DATAINPUT);

    }
    
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void backupData(String tableID, String bakWhere, String bakType) throws ServiceException {
        DictTModelPO modelPO = null;
        List<DictTFactorPO> factors = null;
        String backupSQL = null;

        modelPO = getDictTModelService().getDictTModelByID(tableID, false);
        if (modelPO == null) {
            throw new ServiceException(ExceptionCode.INP_00001, "要备份的表信息在系统中不存在!", false);
        }
        if (!"1".equals(modelPO.getIsbak())) {
            logger.debug(modelPO.getName() + "不是备份表!");
            return;
        }
        if ("2".equals(modelPO.getTabletype())) {
            logger.debug(modelPO.getName() + "为视图不能进行备份!");
            return;
        }

        // 修改当前最新数据版本
        getCommonDataBackupDAO().updateBackDataVersion(modelPO.getDbtablename(), bakWhere, bakType);

        // 备份最新数据
        factors = getDictTFactorService().getDictTFactorsByTableId(tableID);
        backupSQL = getBackupSQL(modelPO.getDbtablename(), factors, 0, bakWhere, bakType);
        try {
            getCommonDataBackupDAO().backupData(backupSQL);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00105, "执行数据备份错误!", false);
        }
        
    }

    /**.
     * 拼接备份SQL
     * @param tableDbName 备份表名
     * @param factors 备份表列
     * @param currentBakVersion 备份版本
     * @param bakWhere 备份条件
     * @param bakType 备份类型
     * @return 备份SQL
     * @throws
     */
    private String getBackupSQL(String tableDbName, List<DictTFactorPO> factors, Integer currentBakVersion,
                                String bakWhere, String bakType) {
        StringBuffer backupSQLBuffer = new StringBuffer("");
        for (DictTFactorPO factor : factors) {
            if ("0".equals(factor.getIsleaf()) || "1".equals(factor.getIsvirtual())
                    || "1".equals(factor.getIsbandcol()) || factor.getDbcolumnname() == null
                    || factor.getDbcolumnname().trim().equals("")) {
                continue;
            }
            backupSQLBuffer.append(factor.getDbcolumnname()).append(",");
        }

        String insertSQL = "INSERT INTO " + tableDbName + "_BAK(" + backupSQLBuffer.toString()
                + "BAKVERSION, BAKTYPE) ";
        String valueSQL = "SELECT " + backupSQLBuffer.toString() + currentBakVersion + " AS BAKVERSION, '" + bakType
                + "' AS BAKTYPE FROM " + tableDbName;
        if (bakWhere != null && bakWhere.trim().length() > 0) {
            valueSQL += " WHERE " + bakWhere;
        }

        return insertSQL.concat(valueSQL);

    }
}
