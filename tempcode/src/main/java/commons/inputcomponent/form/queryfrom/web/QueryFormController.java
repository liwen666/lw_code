package commons.inputcomponent.form.queryfrom.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.form.queryfrom.po.QueryForm;
import com.tjhq.commons.inputcomponent.form.queryfrom.service.IQueryFormService;
import com.tjhq.commons.inputcomponent.po.Form;
import com.tjhq.commons.inputcomponent.po.ResultPO;

@Controller("queryFormController")
@RequestMapping(value = "/component/queryForm")
public class QueryFormController {
	
	@Resource
	private IQueryFormService queryFormService;
	
	public IQueryFormService getQueryFormService() {
		return queryFormService;
	}

	public void setQueryFormService(IQueryFormService queryFormService) {
		this.queryFormService = queryFormService;
	}

	/**
	 * 获取查询面板定义
	 * @param tableID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getForm")
	@ResponseBody
	public Object getForm(String tableID) {
		QueryForm form = new QueryForm();
		ResultPO resultPO = null;
		
		form.setTableID(tableID);
		
		try {
            getQueryFormService().setFormDefine(form);
            resultPO = new ResultPO(form);
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        
		return resultPO;
	}
    /**
     * 获取QueryForm查询条件默认值数据
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getData")
    @ResponseBody
    public Object getData(@RequestBody Form form) {
        ResultPO resultPO = null;
        
        try {
            resultPO = new ResultPO(getQueryFormService().getDefaultData(form));
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        
        return resultPO;
    }
}
