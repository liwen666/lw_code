package commons.setting.cachemanage.web;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.cachemanage.service.ICacheManageService;

/**
 * @ClassName: CacheManageController
 * @Description: 缓存管理功能
 * @author: ZhengQ 2016-12-14 上午11:32:40
 */

@Controller
@RequestMapping(value = "/commons/setting/cacheManage")
public class CacheManageController {
    @Resource
    private ICacheManageService cacheManageService;
    
    @RequestMapping(value = "page")
    public String page() {
        return "commons/setting/cachemanage/cacheManagePage";
    }
    
    /**.获取集群环境中 缓存ip
     * @param appID
     * @return
     * @throws Exception
     * @throws
     */
    @RequestMapping(value="ipTree")
    @ResponseBody
    public Object getIpTree(String appID) throws Exception{
        ResultPO resPO = null;
        if(null==appID || ("").equals(appID)){
            appID = "BGT";
        }
        try {
            resPO = new ResultPO(cacheManageService.getIpTree());
        } catch (Exception e) {
            e.printStackTrace();
            resPO = new ResultPO();
        }
    return resPO;
    }
    @RequestMapping(value = "getTableHead")
    @ResponseBody
    public Object getTableHead(){
            ResultPO resPO = null;
            try {
                resPO = new ResultPO(cacheManageService.getTableHead());
            } catch (Exception e) {
                e.printStackTrace();
                resPO = new ResultPO();
            }
        return resPO;
    }
    /**.获取缓存信息element
     * @param grid
     * @return
     * @throws
     */
    @RequestMapping(value = "getTableData")
    @ResponseBody
    public Object getTableData(String grid) {
        CommonGrid commonGrid = null;
        ResultPO resPO = null;
        try {
            commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);
            //String ip = (String) commonGrid.getExtProperties().get("ip");
            resPO = new ResultPO(cacheManageService.getTableData());
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resPO;
    }
    /**.获取缓存信息
     * @return
     * @throws
     */
    @RequestMapping(value = "getCacheInfo")
    @ResponseBody
    public Object getCacheInfo() {
        ResultPO resPO = null;
        try {
            resPO = new ResultPO(cacheManageService.getCacheInfo());
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resPO;
    }
    /**
     * .根据key删除某IP服务器下的缓存,key和ip都为空 则清空全部缓存
     * @param keys
     * @return
     * @throws
     */
    @RequestMapping(value = "clearCache")
    @ResponseBody
    public Object clearCache(String keys){
        ResultPO resPO = null;
        try {
            if (keys.length() == 0 ) {
                cacheManageService.clearAllCache();
            } else {
                cacheManageService.clearCache( keys);
            }
            resPO = new ResultPO("");
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resPO;
    }
}
