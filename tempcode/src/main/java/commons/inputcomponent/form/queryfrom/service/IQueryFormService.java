package commons.inputcomponent.form.queryfrom.service;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.form.baseform.service.IBaseFormService;
import com.tjhq.commons.inputcomponent.form.queryfrom.po.QueryForm;
import com.tjhq.commons.inputcomponent.po.Form;

public interface IQueryFormService extends IBaseFormService {
	
	/**
	 * 设置QueryForm定义
	 * @param queryForm
	 * @throws Exception
	 */
	public void setFormDefine(QueryForm queryForm) throws ServiceException;
	
	/**
	 * 获取QueryForm各个item的默认值
	 * @param form
	 * @return
	 */
    public Object getDefaultData(Form form)  throws ServiceException ;
}
