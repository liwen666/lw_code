begin

update P#dict_t_model set tabswhere='PROJTYPEID IN (SELECT PROJTYPEID
                           FROM SPF_T_PROJECTTYPE
                           START WITH PROJTYPEID in (''3'',''9'')
                           CONNECT BY PRIOR PROJTYPEID = SUPERID)' where tableid='F7C195DD3FA44D3EA2843A08255E389A';
						 
update P#dict_t_model set tabswhere='PROJTYPEID IN (SELECT PROJTYPEID
                          FROM SPF_T_PROJECTTYPE
                          START WITH PROJTYPEID in (''2'',''9'')
                          CONNECT BY PRIOR PROJTYPEID = SUPERID)' where tableid='783B60D3816D469A93D48F6C6DDF3917';
						 
sys_p_recreate_views('F7C195DD3FA44D3EA2843A08255E389A');
sys_p_recreate_views('783B60D3816D469A93D48F6C6DDF3917');
end;
--修改项目信息主列表和二级项目信息主列表where条件



--修改项目信息主列表和二级项目信息主列表where条件
