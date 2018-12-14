package commons.setting.manage.test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/commons/setting/test")
public class BZYTestController {

    @RequestMapping(value="")
    public String page(Model model,String appId)  throws Exception{
        /*String sql = "select sys_guid() from dual";
        Object obj = commonDao.queryForMap(sql);
        System.out.println(obj);*/
        List<String> list = new ArrayList<String>();
        list.add("本级未填报的xxxxx："+"\\n");
        list.add("aaa1"+"\\n");
        list.add("aaa2"+"\\n");
        list.add("aaa3"+"\\n");
        list.add("aaa4"+"\\n");
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i);
        }
        model.addAttribute("str",str);
        return "/commons/setting/manage/test";
    }  

    @RequestMapping({"batchExportWord4PrjPerf" })
    public ModelAndView batchExportWord4PrjPerf(HttpServletRequest request) throws Exception {
        String batchConditions = request.getParameter("conditions");
        String sysId = request.getParameter("sysId");
        System.out.println("batchConditions1===========" + batchConditions);
        System.out.println("sysId===========" + sysId);
        return new ModelAndView();
    }
    @RequestMapping(value = "batchExportWord4PrjPerf2")
    public ModelAndView batchExportWord4PrjPerf2(Model model, HttpServletRequest request, String appId) throws Exception {
        String batchConditions = request.getParameter("conditions");//post
        String sysId = request.getParameter("sysId");//get
        System.out.println("sysId==========="+sysId);
        System.out.println("batchConditions1==========="+batchConditions);
        return new ModelAndView();
    }
    @RequestMapping(value = "batchExportWord4PrjPerf1")
    public ModelAndView batchExportWord4PrjPerf1(HttpServletRequest request) throws Exception {
        String batchConditions = request.getParameter("conditions");//post
        String sysId = request.getParameter("sysId");//get
        System.out.println("sysId==========="+sysId);
        System.out.println("batchConditions1==========="+batchConditions);
        return new ModelAndView();
    }
    @RequestMapping(value = "batchExportWord4PrjPerf3")
    @ResponseBody
    public Object batchExportWord4PrjPerf3(HttpServletRequest request) throws Exception {
        String batchConditions = request.getParameter("conditions");//post
        String sysId = request.getParameter("sysId");//get
        System.out.println("sysId==========="+sysId);
        System.out.println("batchConditions1==========="+batchConditions);
        return batchConditions;
    }
    @RequestMapping(value = "batchExportWord4PrjPerf4")
    @ResponseBody
    public Object batchExportWord4PrjPerf4(String batchConditions) throws Exception {
        System.out.println("batchConditions1==========="+batchConditions);
        return batchConditions;
    }
    
    @RequestMapping(value="page2")
    public String page2(Model model,String appId)  throws Exception{
        return "/commons/setting/manage/test2";
    }
    @RequestMapping(value="page3")
    public String page3(Model model,String appId)  throws Exception{
        return "/commons/setting/manage/query2";
    }
    @RequestMapping(value="bizyTestDa")
    public String testDa(Model model,String appId)  throws Exception{
        return "/commons/setting/manage/testDa";
    }
}
