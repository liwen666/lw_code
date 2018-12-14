package commons.execscript;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.execscript.common.Constants;
import com.tjhq.commons.execscript.service.IScriptService;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.install.service.impl.InstallContext;
import com.tjhq.commons.install.service.impl.InstallContextHolder;
import com.tjhq.commons.jackson.ObjectMapper;
/**
 * 执行脚本控制类
 * @author bizaiyi
 * 2015-8-13 下午2:43:18
 * ExecScriptController.java
 */
@Controller
@RequestMapping(value = "/commons/execscript")
public class ExecScriptController {
	@Resource 
	private ISettingGridService settingGridService;
	@Resource
	private IScriptService scriptService;
    @Resource
    private IDataCacheService dataCacheService;

	@RequestMapping(value="")
	public String page(Model model,String appId)  throws Exception{
		model.addAttribute("appId", appId.toLowerCase());
		model.addAttribute("province", SecureUtil.getUserSelectProvince());
		return "/commons/execscript/execScript";
	}
	/**
	 * 获取表格列定义
	 * 
	 * @explain
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value = "getTableHead")
	@ResponseBody
	public Object getTableHead(@RequestParam(value = "gridType") String gridType)
			throws Exception {
		try {
			Table grid = scriptService.setTableDefine(gridType);
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGridService
					.initStructure(grid, user.getGuid());
			return table;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * 获取表数据
	 * @param grid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getTableData")
	@ResponseBody
	public Object getTableData(String grid) throws Exception{
		Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
		scriptService.initDbScriptObject();//初始化表

		String gridType = data.getExtProperties().get("gridType").toString();
		String appID = data.getExtProperties().get("appID").toString().toLowerCase();

		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		if(Constants.SCRIPT_GRID.equals(gridType)){//是未执行脚本表
			dataList = scriptService.getScriptDataList(appID,SecureUtil.getUserSelectProvince());
		}else if(Constants.LOG_GRID.equals(gridType)){//是执行日志表
			dataList = scriptService.getLogDataList(data.getExtProperties());
		}
		settingGridService.transferGridData(dataList, data.getPageInfo()); 
		return data.getPageInfo();
	}
	@RequestMapping(value = "exec")
	@ResponseBody
	public String exec(HttpServletRequest request) throws Exception {
		String resMsg = "执行完成！";
		try {
			/*
			 * 其实应该用这个，不过之前已经写好了用scriptInfo，那就这样吧
				String selRows = request.getParameter("selRows");
				ObjectMapper objectMapper = new ObjectMapper();
				List<Map<String, Object>> list = objectMapper.readValue(selRows, List.class);
				//或
				JSONArray rowsArray = JSONArray.fromObject(request.getParameter("selRows"));
				System.out.println(rowsArray);
			*/
			
			String scriptInfo = request.getParameter("scriptInfo");
			String[] scriptInfoArray = scriptInfo.split("#");
			String userLogId = SecureUtil.getCurrentUser().getCode();
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("userLogId", userLogId);
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmss_SSS"); 
	        String batchcode = sdf.format(System.currentTimeMillis());
	        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy_MM_dd"); 
	        String typename = sdf2.format(System.currentTimeMillis());
			param.put("batchcode", batchcode);
			param.put("typename", typename);
			
			
			//安装升级用上下文
            UserDTO user = SecureUtil.getCurrentUser();
            Integer finYearInteger = SecureUtil.getUserSelectYear();
            String defaultProvince = "";
            String defaultYear = finYearInteger + "";
            // 如果是财政用户，取提升后的地区
            if (user.getUsertype() == 1) {
                defaultProvince = user.getUpagencyCode();
            } else {
                defaultProvince = SecureUtil.getUserSelectProvince();
            }
			InstallContext installContext = InstallContextHolder
                    .getInstallContext();
            if (installContext == null) {
                installContext = new InstallContext();
                InstallContextHolder.setInstallContext(installContext);
            }    
            installContext.setProvinceCode(defaultProvince);
            installContext.setYear(defaultYear);
            InstallContextHolder.setInstallContext(installContext);
			
			for (int i = 0; i < scriptInfoArray.length; i++) {
				String[] scriptRow = scriptInfoArray[i].split(",");
				param.put("appID", scriptRow[0]);
				param.put("typeId", scriptRow[1]);
				param.put("keyId", scriptRow[2]);
				param.put("province", SecureUtil.getUserSelectProvince());//杜崇明添加
				scriptService.execScript(param);
			}
	        //编译失效对象
			scriptService.compileInvalidObjects();
	        //编译失效对象后更改log表执行结果
	        //scriptService.updateLogTableInvalidObjects();
		   
			//清服务器缓存
			dataCacheService.clearAll();
		} catch (Exception e) {
			e.printStackTrace();
			resMsg = "执行失败:"+e.getMessage();
		} finally {
		    InstallContextHolder.setInstallContext(null);
		}
		return resMsg;
	}
	/**
	 * 编译失效对象
	 * @return
	 */
	@RequestMapping("/compileInvalidObjects")
	@ResponseBody 
	public Map<String,String> compileInvalidObjects(){
		scriptService.compileInvalidObjects();
		Map<String,String> res = new HashMap<String,String>();
		res.put("msg", "编译完成！");
		return res;
	}
	/**
	 * 获取所有批次号
	 * @return
	 */
	@RequestMapping("/getBatchCodeList")
	@ResponseBody
	public Object getBatchCodeList() {
		List<String> resObj = scriptService.getBatchCodeList(null);
		return resObj;
	}
	/**
	 * 查看脚本内容
	 * @param model
	 * @param keyId
	 * @param typeId
	 * @param batchcode
	 * @return
	 */
	@RequestMapping("/showScript")
	public String showScript(Model model,String keyId,String typeId,String batchcode,String logId){
		Map<String,String> param = new HashMap<String, String>();
		param.put("keyId", keyId);
		param.put("typeId", typeId);
		param.put("batchcode", batchcode);
		param.put("logId", logId);
		Map<String,String> scriptMap = scriptService.getScriptMap(param);
		model.addAttribute("scriptMap", scriptMap);
		return "/commons/setting/manage/showScript";
	}
}
