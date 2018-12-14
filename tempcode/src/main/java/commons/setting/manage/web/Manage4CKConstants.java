package commons.setting.manage.web;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 系统维护控制类
 * @author bizaiyi
 * 2015-12-17 下午4:04:32
 * Manage4CKConstants.java
 * 满足懒人曹坤无理要求
 */
@Controller
@RequestMapping(value = "/sysmain")
public class Manage4CKConstants {

    /**
     * 跳转到系统维护主页面
     * @param model
     * @param appId
     * @return
     */
    @RequestMapping(value="")
    public String page(Model model,String appId)  throws Exception{
        model.addAttribute("appId", appId);
        model.addAttribute("province", SecureUtil.getUserSelectProvince());
        return "/commons/setting/manage/manage";
    }
}
