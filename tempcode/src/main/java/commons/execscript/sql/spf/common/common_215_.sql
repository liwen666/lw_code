
BEGIN
    UPDATE DICT_T_SETTINGTABINFO SET YEARPART ='0' WHERE GUID ='2C92F7242C93F3C9E050A8C0210562A2';
       
    DELETE FROM FASP_T_PUBMENU WHERE GUID IN('1120010101','112002031','12000302','12000402');
    insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
    values ('1120010101', 2, 1, '1', '004001002', '转移支付项目编辑', '120001', '/spf/spf/input/inputMain.do?style=2'||CHR(38)||'dealtype=40'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=1'||CHR(38)||'isTemp=0'||CHR(38)||'isreadonly=0'||CHR(38)||'isedit=1', 2, 'remark', null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null);

    insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
    values ('112002031', 2, 1, '1', '004002007', '项目编辑', '120002', '/spf/spf/proj/input/inputMain.do?style=2'||CHR(38)||'projmType=1'||CHR(38)||'dealtype=50'||CHR(38)||'spfchsdealtype=5*11'||CHR(38)||'instead=0'||CHR(38)||'attention=0'||CHR(38)||'appid=SPF'||CHR(38)||'istemp=0'||CHR(38)||'isreadonly=0'||CHR(38)||'isedit=1', 7, 'remark', null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null);

    insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
    values ('12000302', 2, 1, '1', '004003002', '一级项目编辑', '120003', '/spf/spf/input/inputMain.do?style=2'||CHR(38)||'dealtype=30'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=2'||CHR(38)||'isTemp=0'||CHR(38)||'isreadonly=0'||CHR(38)||'isedit=1', 2, 'remark', null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null);

    insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
    values ('12000402', 2, 1, '1', '004004005', '二级项目编辑', '120004', '/spf/spf/proj/input/inputMain.do?style=2'||CHR(38)||'projmType=2'||CHR(38)||'dealtype=P0'||CHR(38)||'spfchsdealtype=5*11'||CHR(38)||'instead=0'||CHR(38)||'attention=0'||CHR(38)||'appid=SPF'||CHR(38)||'istemp=0'||CHR(38)||'isreadonly=0'||CHR(38)||'isedit=1', 5, 'remark', null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null);

END;
--修改专项类别非全年度添加项目编辑菜单
