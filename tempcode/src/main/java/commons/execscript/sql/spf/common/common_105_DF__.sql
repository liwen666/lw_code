--DF_修改项目单位代码表
begin
	UPDATE dict_t_modelcode SET DYNAMICWHERE ='1=1 order by code' WHERE dbtablename ='CODE_T_PROJECTANDSPF';
	UPDATE dict_t_factor SET csid ='14DADAE88B8B57D0E050A8C0210557DD' WHERE csid ='FC35B5F4D7A74552BE69C81BC33345F6';
END;
--DF_修改项目单位代码表
