package commons.execscript.external.service.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.execscript.common.Constants;
import com.tjhq.commons.execscript.external.service.IExtScriptService;
import com.tjhq.commons.execscript.service.IScriptService;
import com.tjhq.commons.install.service.impl.InstallContext;
import com.tjhq.commons.install.service.impl.InstallContextHolder;

@Service
public class ExtScriptService implements IExtScriptService {
    @Resource
    private IScriptService scriptService;
    @Resource
    private IDataCacheService dataCacheService;
    
    @Override
    public void execScript(Map<String, String> param) throws Exception {
        String appID = param.get("appID").toString().toLowerCase();
        
        try {
            //初始化表
            scriptService.initDbScriptObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("初始化升级脚本模块数据结构失败！");
        }
        
		String defaultProvince = null;
		InstallContext installContext = InstallContextHolder
				.getInstallContext();
		if (installContext != null) {
			defaultProvince = installContext.getProvinceCode();
		} else {
			defaultProvince = SecureUtil.getUserSelectProvince();
		}

        //获取所有未执行脚本
        List<Map<String, Object>> dataList;
        try {
            dataList = scriptService.getScriptDataList(appID,defaultProvince);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("获取未执行脚本失败！");
        }
        
        //执行所有脚本
        if(dataList!=null && dataList.size()>0){
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("userLogId", Constants.SCRIPT_INSTALLER);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmss_SSS"); 
            String batchcode = sdf.format(System.currentTimeMillis());
            SimpleDateFormat sdf2=new SimpleDateFormat("yyyy_MM_dd"); 
            String typename = sdf2.format(System.currentTimeMillis());
            paramMap.put("batchcode", batchcode);
            paramMap.put("typename", typename);
            
            //循环执行每一个脚本，单独控制事务
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, Object> fileMap = dataList.get(i);
                paramMap.put("appID", fileMap.get("APPID").toString());
                paramMap.put("typeId", fileMap.get("TYPEID").toString());
                paramMap.put("keyId", fileMap.get("KEYID").toString());
                paramMap.put("province", defaultProvince);//杜崇明添加
                scriptService.execScript(paramMap);
            }
            //编译失效对象
            scriptService.compileInvalidObjects();
        }
        
        //清服务器缓存
        dataCacheService.clearAll();
    }
}
