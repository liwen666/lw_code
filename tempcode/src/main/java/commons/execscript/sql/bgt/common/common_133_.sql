begin
delete  from  Dict_t_Public where KEYID = '000001';
insert into P#Dict_t_Public (APPID,CNUM,DBVERSION,ISYES,KEYID,KEYNAME,REMARK,RESERVE_1,RESERVE_2,STATUS,TYPEID,TYPENAME, YEAR)
SELECT 'BGT',0,null,'','000001','打开公文页面','hqoa/workflow/getDocsByTaskId.do?midTableName=bgt_t_oarelation',null,null,'1','openDocPage','综合界面，添加打开公文界面', YEAR FROM pub_t_partition_divid T1 
WHERE YEAR <> '*' AND districtid = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID')
AND NOT EXISTS (SELECT 1 FROM P#Dict_t_Public T2 WHERE appID = 'BGT' AND TYPEID = 'openDocPage' AND T1.year = T2.year);


delete  from  Dict_t_Public where KEYID = '000002';
insert into P#Dict_t_Public (APPID,CNUM,DBVERSION,ISYES,KEYID,KEYNAME,REMARK,RESERVE_1,RESERVE_2,STATUS,TYPEID,TYPENAME, YEAR)
SELECT 'BAS',0,null,'','000002','打开公文页面','hqoa/workflow/getDocsByTaskId.do?midTableName=bgt_t_oarelation',null,null,'1','openDocPage','综合界面，添加打开公文界面', YEAR FROM pub_t_partition_divid T1 
WHERE YEAR <> '*' AND districtid = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID')
AND NOT EXISTS (SELECT 1 FROM P#Dict_t_Public T2 WHERE appID = 'BAS' AND TYPEID = 'openDocPage' AND T1.year = T2.year);

end;--综合界面，添加打开公文界面
