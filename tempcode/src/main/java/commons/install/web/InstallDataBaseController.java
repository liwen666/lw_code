package commons.install.web;

import gov.mof.fasp2.sec.syncuserinfo.filter.FilterStatic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.install.service.IInitSqlXmlService;
import com.tjhq.commons.install.service.IInstallDataBaseService;
import com.tjhq.commons.install.service.impl.InstallContext;
import com.tjhq.commons.install.service.impl.InstallContextHolder;
import com.tjhq.commons.setting.input.po.TreeNode;

@Controller
@RequestMapping(value = "/installdb")
public class InstallDataBaseController {

	@Resource
	private IInitSqlXmlService initSqlXmlService;
	@Resource
	private IInstallDataBaseService installDataBaseService;

	@RequestMapping(value = "/install")
	public String installMainPage() {
		FilterStatic.init("true", "false", "false", "false");
		return "/commons/install/InstallDataBase";
		
	}
	@RequestMapping(value = "/hasModel")
	@ResponseBody
	public String hasModel(){
		FilterStatic.init("true", "false", "false", "false");
		// 先判断model表里是否存在EFM_T_INSTALLLOG表 存在返回已经安装
		// 不存在 开始安装
		Integer resCount = installDataBaseService.hasModel("DICT_T_MODEL");
		if (resCount == 0)
			return "success";
		else
			return "fail";
			
	}
	@RequestMapping(value = "/getDefineTabHead")
	@ResponseBody
	public Object getDefineTabHead() {
		return installDataBaseService.getDefineTabHead();
	}

	@RequestMapping(value = "/getInstalledData")
	@ResponseBody
	public Object getInstalledData(String grid, HttpServletRequest request)
			throws Exception {
		InstallContext installContext = InstallContextHolder
				.getInstallContext();
		if (installContext == null) {
			installContext = new InstallContext();
			InstallContextHolder.setInstallContext(installContext);
		}
		CommonGrid commonGrid = (CommonGrid) (new ObjectMapper()).readValue(
				grid, CommonGrid.class);
		
		Object object = installDataBaseService.getInstalledData(commonGrid);
		
		InstallContextHolder.setInstallContext(null);
		
		return object;
	}

	@RequestMapping(value = "/generate")
	@ResponseBody
	public String generateInstallSqlFile() {
		try {
			initSqlXmlService.generateInstallSqlFile();
			return "Generate Success";
		} catch (Exception e) {
			return "Generate Fail";
		}
	}

	@RequestMapping(value = "/initDataBase")
	@ResponseBody
	public String initDataBase(String defaultYear, String defaultProvince,
			String dbLinkName) {
		try {
			InstallContext installContext = InstallContextHolder
					.getInstallContext();
			if (installContext == null) {
				installContext = new InstallContext();
				InstallContextHolder.setInstallContext(installContext);
			}
			initSqlXmlService.initDataBase(defaultYear, defaultProvince,
					dbLinkName);
			return "Init DataBase Success";
		} catch (Exception e) {
			return "Init DataBase Fail";
		} finally {
            InstallContextHolder.setInstallContext(null);
        }
	}

	@RequestMapping(value = "/installWorkFlow")
	@ResponseBody
	public String installWorkFlow(String defaultYear, String defaultProvince) {
		try {
			InstallContext installContext = InstallContextHolder
					.getInstallContext();
			if (installContext == null) {
				installContext = new InstallContext();
				InstallContextHolder.setInstallContext(installContext);
			}
			initSqlXmlService.installWorkFlow(defaultYear, defaultProvince);
			return "Init WorkFlow Success";
		} catch (Exception e) {
			return "Init WorkFlow Fail";
		} finally {
            InstallContextHolder.setInstallContext(null);
        }
	}

	@RequestMapping(value = "/createObjects")
	@ResponseBody
	public String createObjects(String isSysObj, String objectType,
			String defaultYear, String defaultProvince, String dbLinkName) {
		try {
			InstallContext installContext = InstallContextHolder
					.getInstallContext();
			if (installContext == null) {
				installContext = new InstallContext();
				InstallContextHolder.setInstallContext(installContext);
			}
			initSqlXmlService.createObjects(isSysObj, objectType, defaultYear,
					defaultProvince, dbLinkName);
			return "Create " + objectType + "S Success";
		} catch (Exception e) {
			return "Create " + objectType + "S Fail";
		} finally {
            InstallContextHolder.setInstallContext(null);
        }
	}

	@RequestMapping(value = "/compileInvalidObjects")
	@ResponseBody
	public String compileInvalidObjects() {
		try {
			InstallContext installContext = InstallContextHolder
					.getInstallContext();
			if (installContext == null) {
				installContext = new InstallContext();
				InstallContextHolder.setInstallContext(installContext);
			}
			initSqlXmlService.compileInvalidObjects();
			return "Compile Invalid Objects Success";
		} catch (Exception e) {
			return "Compile Invalid Objects Fail";
		} finally {
            InstallContextHolder.setInstallContext(null);
        }
	}
	@RequestMapping(value = "/getDefaultProvince")
	@ResponseBody
	public Object getDefaultProvince() {
		List<Map<String, Object>> treeList =installDataBaseService.getDefaultProvince();
		
		
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for (Map<String, Object> treeNode: treeList) {
			TreeNode tree = new TreeNode();
			tree.setId(treeNode.get("GUID").toString());
			tree.setName(treeNode.get("C_NAME").toString());
			tree.setPId(treeNode.get("SUPERGUID").toString());
			tree.setIsLeaf(treeNode.get("ISLEAF").toString());
			tree.setDbColumnName(treeNode.get("NAME").toString());
			tree.setColumnName(treeNode.get("NAME").toString());
			tree.setColumnId(treeNode.get("GUID").toString());
			tree.setCsid(treeNode.get("CODE").toString());
			treeNodes.add(tree);
		}
		return treeNodes;
	}

	@RequestMapping(value = "/recreateInvalidObjects")
	@ResponseBody
	public String recreateInvalidObjects() {
		try {
			InstallContext installContext = InstallContextHolder
					.getInstallContext();
			if (installContext == null) {
				installContext = new InstallContext();
				InstallContextHolder.setInstallContext(installContext);
			}
			initSqlXmlService.recreateInvalidObjects(10);
			return "Recreate Invalid Objects Success";
		} catch (Exception e) {
			return "Recreate Invalid Objects Fail";
		} finally {
            InstallContextHolder.setInstallContext(null);
        }
	}

	@RequestMapping(value = "/initBusiData")
	@ResponseBody
	public String initBusiData(String defaultYear, String defaultProvince) {
		try {
			InstallContext installContext = InstallContextHolder
					.getInstallContext();
			if (installContext == null) {
				installContext = new InstallContext();
			}
			installContext.setProvinceCode(defaultProvince);
			installContext.setYear(defaultYear);
			InstallContextHolder.setInstallContext(installContext);
			initSqlXmlService.initBusiData(defaultYear, defaultProvince);
			return "Init BusiData Success";
		} catch (Exception e) {
			return "Init BusiData Fail";
		} finally {
            InstallContextHolder.setInstallContext(null);
        }
	}

}
