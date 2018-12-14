BEGIN

DELETE FROM FASP_T_PUBMENU WHERE GUID IN('11200204','12000303','12000403','1120010102');

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000303', 2, 1, '1', '004003003', '一级项目查看', '120003', '/spf/spf/input/spfInputMain.do?spfmType=2'||CHR(38)||'dealType=30'||CHR(38)||'step=spfview'||CHR(38)||'mainDealType=3*01'||CHR(38)||'isReadOnly=1', 3, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000403', 2, 1, '1', '004004006', '二级项目查看', '120004', '/spf/project/input/projInputMain.do?spfmType=2'||CHR(38)||'step=projview'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'isReadOnly=1', 6, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('1120010102', 2, 1, '1', '004001003', '专项资金查看', '120001', '/spf/spf/input/spfInputMain.do?spfmType=1'||CHR(38)||'dealType=40'||CHR(38)||'step=spfview'||CHR(38)||'mainDealType=4*01'||CHR(38)||'isReadOnly=1', 3, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11200204', 2, 1, '1', '004002008', '项目查看', '120002', '/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=projview'||CHR(38)||'dealType=50'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=5*01'||CHR(38)||'isReadOnly=1', 8, 'remark', 1, 'spf', 1);

END;
--添加查看菜单
