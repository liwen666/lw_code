i integer;
BEGIN
select count(1) into i  from fasp_t_pubmenu t where guid ='100001FF';
 if i = 0 
 then 
delete from fasp_t_pubmenu where appid in ('spf','kpi','bas','bgt');
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC01C390DE050A8C021050E32', 0, 0, '1', '003', '基础资料', '0', '/bas/commons/portal/home.do?appId=bas&systemName=%E5%9F%BA%E7%A1%80%E8%B5%84%E6%96%99', 18, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC038390DE050A8C021050E32', 1, 0, '1', '003001', '数据采集', '15761DCAC01C390DE050A8C021050E32', null, 1, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC04A390DE050A8C021050E32', 2, 1, '1', '003001001', '数据填报', '15761DCAC038390DE050A8C021050E32', '/bas/exp/datainputOA/main.do?appID=BAS', 1, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('E64DC65D2B454BFCAF6AD9D373424AD0', 2, 1, '1', '003001002', '数据代录', '15761DCAC038390DE050A8C021050E32', '/bas/exp/datainputOA/main.do?appID=BAS&operate=4', 2, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC045390DE050A8C021050E32', 2, 1, '1', '003001003', '数据查看', '15761DCAC038390DE050A8C021050E32', '/bas/exp/datainputOA/main.do?appID=BAS&opt=view', 3, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC044390DE050A8C021050E32', 2, 1, '1', '003001004', '批量数据审核', '15761DCAC038390DE050A8C021050E32', '/bas/exp/zhshOA/audit/main.do?appID=BAS', 4, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC02C390DE050A8C021050E32', 1, 0, '1', '003002', '数据测算', '15761DCAC01C390DE050A8C021050E32', null, 2, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC03E390DE050A8C021050E32', 3, 1, '1', '003002001', '测算模型定义', '15761DCAC02C390DE050A8C021050E32', '/bas/est/estModel.do?style=1', 1, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC01F390DE050A8C021050E32', 3, 1, '1', '003002002', '测算结果管理', '15761DCAC02C390DE050A8C021050E32', '/bas/est/estResult/main.do?style=1&appID=BAS', 2, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC020390DE050A8C021050E32', 3, 1, '1', '003002003', '来源表维护', '15761DCAC02C390DE050A8C021050E32', '/bas/est/estsrctab/main.do?style=1&appID=BAS', 3, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC024390DE050A8C021050E32', 1, 0, '1', '003003', '任务管理', '15761DCAC01C390DE050A8C021050E32', null, 3, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC03B390DE050A8C021050E32', 2, 1, '1', '003003001', '任务类型管理', '15761DCAC024390DE050A8C021050E32', '/bas/exp/setting/tasktype.do?appID=BAS', 1, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC021390DE050A8C021050E32', 2, 1, '1', '003003002', '创建任务', '15761DCAC024390DE050A8C021050E32', '/bas/exp/taskdoc/taskCreate.do?appID=BAS', 2, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC021390DE050A8C021050E3W', 2, 1, '1', '003003003', '查看任务', '15761DCAC024390DE050A8C021050E32', '/bas/exp/taskdoc/taskDocInfo.do?appID=BAS', 3, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC01D390DE050A8C021050E32', 2, 1, '1', '003003004', '任务数据初始化', '15761DCAC024390DE050A8C021050E32', '/bas/exp/taskdoc/taskInit.do?appID=BAS', 4, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC033390DE050A8C021050E32', 1, 0, '1', '003004', '系统设置', '15761DCAC01C390DE050A8C021050E32', null, 4, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC03C390DE050A8C021050E32', 2, 1, '1', '003004001', '数据审核设置', '15761DCAC033390DE050A8C021050E32', '/bas/exp/setting/businessAudit/businessAuditPage.do?appId=BAS', 1, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('1C60611727FB01B4E050A8C021053302', 2, 1, '1', '003004002', '数据库维护', '15761DCAC033390DE050A8C021050E32', '/bas/commons/setting/manage/main.do?appId=bas', 2, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('24419773306F33C2E050A8C021052853', 2, 1, '1', '003004003', '异常管理', '15761DCAC033390DE050A8C021050E32', '/bas/commons/setting/exception/page.do?appId=BAS&option=manage', 3, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('24533F53F590CA01E050A8C021057340', 2, 1, '1', '003004004', '异常查看', '15761DCAC033390DE050A8C021050E32', '/bas/commons/setting/exception/page.do?appId=BAS&option=view', 4, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC02A390DE050A8C021050E32', 2, 1, '1', '003004005', '主从关系设置', '15761DCAC033390DE050A8C021050E32', '/bas/commons/setting/input/mainSubRela.do?appId=BAS', 5, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC046390DE050A8C021050E32', 2, 1, '1', '003004006', '主表列属性对应附表定义', '15761DCAC033390DE050A8C021050E32', '/bas/exp/setting/coltotable/coltotab.do', 6, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC037390DE050A8C021050E32', 1, 0, '1', '003005', '数据权限设置', '15761DCAC01C390DE050A8C021050E32', null, 5, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC036390DE050A8C021050E32', 2, 1, '1', '003005001', '数据权限设置', '15761DCAC037390DE050A8C021050E32', '/bas/commons/secu/roleToAgency.do', 1, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC036390DE050A8C0210CCE32', 2, 1, '1', '003005002', '用户对应单位', '15761DCAC037390DE050A8C021050E32', '/bas/commons/secu/roleToAgency.do?type=2', 2, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC048390DE050A8C021050E32', 2, 1, '1', '003005003', '处室对单位', '15761DCAC037390DE050A8C021050E32', '/bas/exp/secu/agencyDept/agencyDeptPage.do', 3, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC038390DE050A8C021050EEW', 1, 0, '1', '003008', '通用设置', '15761DCAC01C390DE050A8C021050E32', null, 8, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC034390DE050A8C021050E32', 2, 1, '1', '003008001', '表类型缺省列设置', '15761DCAC038390DE050A8C021050EEW', '/bas/commons/dict/dicttdealtype.do', 1, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC02D390DE050A8C021050E32', 2, 1, '1', '003008002', '业务要素管理', '15761DCAC038390DE050A8C021050EEW', '/bas/commons/dict/dicttcode.do', 2, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC035390DE050A8C021050E32', 2, 1, '1', '003008003', '录入表定义', '15761DCAC038390DE050A8C021050EEW', '/bas/commons/dict/dictCommons.do', 3, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC02B390DE050A8C021050E32', 2, 1, '1', '003008004', '公式定义', '15761DCAC038390DE050A8C021050EEW', '/bas/commons/setting/formula.do?appId=BAS', 4, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('D00753AAE948446B8B25FC21CD829B1B', 2, 1, '1', '003008005', '公式刷新顺序设置', '15761DCAC038390DE050A8C021050EEW', '/bas/exp/formulaRefreshOrder/main.do?appID=BAS', 5, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC01E390DE050A8C021050E32', 2, 1, '1', '003008006', '公式刷新', '15761DCAC038390DE050A8C021050EEW', '/bas/exp/formulaRefresh.do?appID=BAS', 6, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC049390DE050A8C021050E32', 2, 1, '1', '003008007', '数据下发表关联设置', '15761DCAC038390DE050A8C021050EEW', '/bas/commons/dict/dicttrelation.do?appid=BAS', 7, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('242BF5113D6BB141E050A8C02105302D', 2, 1, '1', '003008008', '录入审核对象登记', '15761DCAC038390DE050A8C021050EEW', '/bas/commons/setting/dataaudit/modelToAuditobj/page.do', 8, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('24172F329469E63AE050A8C0210541A2', 2, 1, '1', '003008009', '视图物理表登记', '15761DCAC038390DE050A8C021050EEW', '/bas/commons/setting/dataaudit/viewTotable/page.do', 9, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC028390DE050A8C021050E32', 2, 1, '1', '003008010', '录入界面定义', '15761DCAC038390DE050A8C021050EEW', '/bas/commons/setting/input.do?appId=BAS', 10, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC023390DE050A8C021050E32', 1, 1, '1', '003008011', '数据审核公式定义', '15761DCAC038390DE050A8C021050EEW', '/bas/commons/setting/auditrule/auditRulePage.do?appId=BAS', 11, null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('100002', 0, 0, '1', '004', '项目库', '0', '/spf/commons/portal/home.do?appId=spf&systemName=%E9%A1%B9%E7%9B%AE%E5%BA%93', 19, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('120001', 1, 0, '1', '004001', '专项资金管理', '100002', null, 1, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200101', 2, 1, '1', '004001001', '专项资金设立', '120001', '/spf/spf/input/inputMain.do?style=2&dealtype=40&appid=SPF&spfmType=1&isTemp=0&isreadonly=0', 1, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('1120010101', 2, 1, '1', '004001002', '专项资金编辑', '120001', '/spf/spf/input/inputMain.do?style=2&dealtype=40&appid=SPF&spfmType=1&isTemp=0&isreadonly=0&isedit=1', 2, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('1120010102', 2, 1, '1', '004001003', '专项资金查看', '120001', '/spf/spf/input/inputMain.do?style=2&dealtype=40&appid=SPF&spfmType=1&isTemp=0&isreadonly=1', 3, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11202108', 2, 1, '1', '004001004', '专项资金备份', '120001', '/spf/spf/baklist/main.do?style=2&dealtype=44&appid=SPF&spfmType=1&isTemp=0&bakLogDealType=4*52', 4, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200102', 2, 1, '1', '004001005', '专项资金确认', '120001', '/spf/spf/audit/auditMain.do?istemp=0&dealtype=41', 5, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11202106', 2, 1, '1', '004001006', '专项资金发布', '120001', '/spf/spf/spfpublish/getMainPage.do?dealtype=40', 6, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11202119', 2, 1, '1', '004001007', '专项资金测算分配', '120001', '/spf/spf/allotment.do?appID=SPF', 7, '管理页面', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200192', 2, 1, '1', '004001008', '专项资金预算编制
', '120001', '/spf/spf/acount/auditMain.do?istemp=0&dealtype=41', 8, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11202103', 2, 1, '1', '004001009', '专项资金调整', '120001', '/spf/spf/adjust/main.do?style=2&dealtype=42&appid=SPF&spfmType=1&isTemp=0', 9, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11202105', 2, 1, '1', '004001010', '专项资金调整审核', '120001', '/spf/spf/adjustaudit/main.do?style=2&dealtype=42&appid=SPF&spfmType=1&isTemp=0', 10, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('24A46FC5BB30EF34E050A8C021057D70', 2, 1, '1', '004001011', '专项资金记帐', '120001', '/spf/spf/check/initSpfTally.do?istemp=0&dealtype=41', 11, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('7AB2071676FE489EA6F8DE6C2C1B9869', 2, 1, '1', '004001012', '转移支付下发', '120001', '/spf/spf/export/spfSetting.do?dealtype=4*01', 12, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('120002', 1, 0, '1', '004002', '专项项目管理', '100002', null, 2, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200201', 2, 1, '1', '004002001', '项目申报', '120002', '/spf/spf/proj/input/inputMain.do?style=2&projmType=1&dealtype=50&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&istemp=0&isreadonly=0&isedit=0', 1, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200209', 2, 1, '1', '004002002', '项目删除', '120002', '/spf/spf/proj/del/page.do?projmType=1&dealtype=50', 2, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200231', 2, 1, '1', '004002003', '项目代录', '120002', '/spf/spf/proj/input/inputMain.do?style=2&projmType=1&dealtype=50&spfchsdealtype=5*11&instead=1&attention=0&appid=SPF&istemp=0&isreadonly=0&isedit=0', 3, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2295863373578E3CE050A8C021056471', 2, 1, '1', '004002004', '批量申报项目', '120002', '/spf/spf/proj/input/inputMain.do?style=2&projmType=1&dealtype=50&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&istemp=0&isreadonly=0&isedit=0&isLimit=0', 4, null, 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('120002003', 2, 1, '1', '004002005', '项目备份', '120002', '/spf/spf/proj/baklist/main.do?style=2&dealtype=53&appid=SPF&projmType=1&isTemp=0&bakLogDealType=4*53', 5, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200203', 2, 1, '1', '004002006', '项目选择排序', '120002', '/spf/spf/proj/check/auditMain.do?istemp=0&dealtype=51&samespf=0&projstep=audit', 6, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('112002031', 2, 1, '1', '004002007', '项目编辑', '120002', '/spf/spf/proj/input/inputMain.do?style=2&projmType=1&dealtype=50&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&istemp=0&isreadonly=0&isedit=1', 7, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200204', 2, 1, '1', '004002008', '项目查看', '120002', '/spf/spf/proj/input/inputMain.do?style=2&projmType=1&dealtype=50&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&istemp=0&isreadonly=1', 8, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22957CD1D93691A2E050A8C0210562G8', 2, 1, '1', '004002009', '项目倒挂', '120002', '/spf/spf/normalHang/getMainPage.do?dealtype=50&appId=SPF', 9, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200202', 2, 1, '1', '004002010', '项目确认', '120002', '/spf/spf/proj/audit/auditMain.do?istemp=0&dealtype=51&samespf=0', 10, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200205', 2, 1, '1', '004002011', '纳入预算', '120002', '/spf/spf/proj/inBudget/inputMain.do?style=2&dealtype=50&projmType=1', 11, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200235', 2, 1, '1', '004002012', '项目预算编制', '120002', '/spf/colligate/commonweb/docMain.do?appID=SPF&dealType=D0', 12, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2A6528A83F97725BE0530100007F850A', 2, 1, '1', '004002013', '项目预算调整', '120002', '/spf/spf/proj/bgtadjust/auditMain.do?istemp=0&dealtype=51&samespf=0&projstep=projbgtadjust', 13, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('120002001', 2, 1, '1', '004002014', '项目调整', '120002', '/spf/spf/proj/adjust/inputMain.do?style=2&projmType=1&dealtype=52&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&hisDealType=5*27', 14, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('120002002', 2, 1, '1', '004002015', '项目调整审核', '120002', '/spf/spf/proj/adjustaudit/main.do?style=2&projmType=1&dealtype=52&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&hisDealType=5*27', 15, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('112002051', 2, 1, '1', '004002017', '纳入指标', '120002', '/spf/spf/proj/inBudget/inputMain.do?style=2&dealtype=50&projmType=1&quota=1', 17, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('264CFA21E0888D1FE050A8C0210558D4', 2, 1, '1', '004002018', '项目综合处理', '120002', '/spf/spf/proj/inBudgetDeal/inputMain.do?style=2&dealtype=50&projmType=1&step=projcompdeal', 18, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('120003', 1, 0, '1', '004003', '一级项目管理', '100002', null, 3, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000301', 2, 1, '1', '004003001', '一级项目设立', '120003', '/spf/spf/input/inputMain.do?style=2&dealtype=30&appid=SPF&spfmType=2&isTemp=0&isreadonly=0', 1, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000302', 2, 1, '1', '004003002', '一级项目编辑', '120003', '/spf/spf/input/inputMain.do?style=2&dealtype=30&appid=SPF&spfmType=2&isTemp=0&isreadonly=0&isedit=1', 2, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000303', 2, 1, '1', '004003003', '一级项目查看', '120003', '/spf/spf/input/inputMain.do?style=2&dealtype=30&appid=SPF&spfmType=2&isTemp=0&isreadonly=1', 3, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000307', 2, 1, '1', '004003004', '一级项目备份', '120003', '/spf/spf/baklist/main.do?style=2&dealtype=36&appid=SPF&spfmType=2&isTemp=0&bakLogDealType=4*52', 4, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000304', 2, 1, '1', '004003005', '一级项目确认', '120003', '/spf/normalspf/audit/auditMain.do?istemp=0&dealtype=31', 5, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000305', 2, 1, '1', '004003006', '一级项目调整', '120003', '/spf/spf/adjust/main.do?style=2&dealtype=32&appid=SPF&spfmType=2&isTemp=0', 6, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000306', 2, 1, '1', '004003007', '一级项目调整审核', '120003', '/spf/spf/adjustaudit/main.do?style=2&dealtype=32&appid=SPF&spfmType=2&isTemp=0', 7, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('120004', 1, 0, '1', '004004', '二级项目管理', '100002', null, 4, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000401', 2, 1, '1', '004004001', '二级项目申报', '120004', '/spf/spf/proj/input/inputMain.do?style=2&projmType=2&dealtype=P0&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&istemp=0&isreadonly=0&isedit=0', 1, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2295863373578E3CE050A8C021056472', 2, 1, '1', '004004002', '二级项目批量申报', '120004', '/spf/spf/proj/input/inputMain.do?style=2&projmType=2&dealtype=50&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&istemp=0&isreadonly=0&isedit=0&isLimit=0', 2, null, 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000414', 2, 1, '1', '004004003', '二级项目代录', '120004', '/spf/spf/proj/input/inputMain.do?style=2&projmType=2&dealtype=P0&spfchsdealtype=5*11&instead=1&attention=0&appid=SPF&istemp=0&isreadonly=0&isedit=0', 3, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000413', 2, 1, '1', '004004004', '二级项目删除', '120004', '/spf/spf/proj/del/page.do?projmType=2&dealtype=P0', 4, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000402', 2, 1, '1', '004004005', '二级项目编辑', '120004', '/spf/spf/proj/input/inputMain.do?style=2&projmType=2&dealtype=P0&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&istemp=0&isreadonly=0&isedit=1', 5, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000403', 2, 1, '1', '004004006', '二级项目查看', '120004', '/spf/spf/proj/input/inputMain.do?style=2&projmType=2&dealtype=P0&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&istemp=0&isreadonly=1', 6, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000409', 2, 1, '1', '004004007', '二级项目选择排序', '120004', '/spf/spf/normalproj/check/auditMain.do?dealtype=P1&istemp=0&samespf=1&projstep=audit', 7, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22957CD1D93491A2E050A8C0210562F7', 2, 1, '1', '004004008', '二级项目备份', '120004', '/spf/spf/proj/baklist/main.do?style=2&projmType=2&dealtype=P3&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&istemp=0&isreadonly=1&bakLogDealType=4*53', 8, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000412', 2, 1, '1', '004004009', '二级项目倒挂', '120004', '/spf/spf/normalHang/getMainPage.do?dealtype=P0&appId=SPF', 9, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000408', 2, 1, '1', '004004010', '二级项目确认', '120004', '/spf/spf/normalproj/audit/auditMain.do?dealtype=P1&istemp=0', 10, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000410', 2, 1, '1', '004004011', '二级项目自由申报', '120004', '/spf/spf/proj/input/inputMain.do?style=2&projmType=2&dealtype=P0&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&istemp=0&isreadonly=0&isedit=0&bySelf=1', 11, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000406', 2, 1, '1', '004004012', '二级项目调整', '120004', '/spf/spf/proj/adjust/inputMain.do?style=2&projmType=2&dealtype=P2&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&istemp=0&isreadonly=1', 12, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000407', 2, 1, '1', '004004013', '二级项目调整审核', '120004', '/spf/spf/proj/adjustaudit/main.do?style=2&projmType=2&dealtype=P2&spfchsdealtype=5*11&instead=0&attention=0&appid=SPF&istemp=0&isreadonly=1', 13, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000404', 2, 1, '1', '004004014', '二级项目纳入预算', '120004', '/spf/spf/proj/inBudget/inputMain.do?style=2&dealtype=P0&projmType=2', 14, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000405', 2, 1, '1', '004004015', '二级项目纳入指标', '120004', '/spf/spf/proj/inBudget/inputMain.do?style=2&dealtype=P0&projmType=2&quota=1', 15, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('265DBD6D372160F8E0539764A8C09EE1', 2, 1, '1', '004004016', '二级项目综合处理', '120004', '/spf/spf/proj/inBudgetDeal/inputMain.do?style=2&dealtype=P0&projmType=2&step=projcompdeal', 16, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('112005', 2, 0, '1', '004005', '项目合同管理', '100002', null, 5, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200501', 3, 1, '1', '004005001', '项目合同选择', '112005', '/spf/spf/project/contract/main.do?dealtype=5*18&kpiType=1&spfStage=contractSelect&projmType=1&processId=CONTRACTBPMN&conDealtype=5*17', 1, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200502', 3, 1, '1', '004005002', '项目合同填报', '112005', '/spf/spf/project/contract/main.do?dealtype=5*18&kpiType=1&spfStage=contractInput&projmType=1&processId=CONTRACTBPMN&conDealtype=5*17', 2, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200503', 3, 1, '1', '004005003', '项目合同查看', '112005', '/spf/spf/project/contract/main.do?dealtype=5*18&kpiType=1&spfStage=contractInput&projmType=1&processId=CONTRACTBPMN&conDealtype=5*17&readonly=1', 3, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200504', 3, 1, '1', '004005004', '项目合同确认', '112005', '/spf/spf/project/contract/main.do?dealtype=5*18&kpiType=1&spfStage=contractAudit&projmType=1&processId=CONTRACTBPMN', 4, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200506', 3, 1, '1', '004005005', '项目合同调整填报', '112005', '/spf/spf/project/contract/main.do?dealtype=5*18&kpiType=1&spfStage=conAdjustInput&projmType=1&processId=CONTRACTADJUSTBPMN&bpmnIssued=0', 5, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200507', 3, 1, '1', '004005006', '项目合同调整选择', '112005', '/spf/spf/project/contract/main.do?dealtype=5*18&kpiType=1&spfStage=conAdjustSelect&projmType=1&processId=CONTRACTADJUSTBPMN&hisDealType=5*27&bpmnIssued=0', 6, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200505', 3, 1, '1', '004005007', '项目合同调整确认', '112005', '/spf/spf/project/contract/main.do?dealtype=5*18&kpiType=1&spfStage=conAdjustAudit&projmType=1&processId=CONTRACTADJUSTBPMN&hisDealType=5*27&bpmnIssued=0', 7, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('112006', 2, 0, '1', '004006', '项目执行情况管理', '100002', null, 6, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200601', 3, 1, '1', '004006001', '项目执行情况填报', '112006', '/spf/spf/project/staged/main.do?dealtype=5*19&kpiType=1&spfStage=collectInput&projmType=1&processId=collect', 1, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200604', 3, 1, '1', '004006002', '项目执行情况查看', '112006', '/spf/spf/project/staged/main.do?dealtype=5*19&kpiType=1&spfStage=collectInput&projmType=1&processId=collect&isReadOnly=1', 2, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200603', 3, 1, '1', '004006003', '项目执行情况选择', '112006', '/spf/spf/project/staged/main.do?dealtype=5*19&kpiType=1&spfStage=collectSelect&projmType=1&processId=collect', 3, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200602', 3, 1, '1', '004006004', '项目执行情况确认', '112006', '/spf/spf/project/staged/main.do?dealtype=5*19&kpiType=1&spfStage=collectAudit&projmType=1&processId=collect', 4, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('112004', 2, 0, '1', '004007', '项目验收管理', '100002', null, 7, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200401', 3, 1, '1', '004007001', '项目验收填报', '112004', '/spf/spf/project/staged/main.do?dealtype=5*19&kpiType=1&spfStage=acceptInput&projmType=1&processId=ACCEPTANCEBPMN', 1, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200404', 3, 1, '1', '004007002', '项目验收查看', '112004', '/spf/spf/project/staged/main.do?dealtype=5*19&kpiType=1&spfStage=acceptInput&projmType=1&processId=ACCEPTANCEBPMN&isReadOnly=1', 2, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200403', 3, 1, '1', '004007003', '项目验收选择', '112004', '/spf/spf/project/staged/main.do?dealtype=5*19&kpiType=1&spfStage=acceptSelect&projmType=1&processId=ACCEPTANCEBPMN', 3, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200402', 3, 1, '1', '004007004', '项目验收', '112004', '/spf/spf/project/staged/main.do?dealtype=5*19&kpiType=1&spfStage=acceptAudit&projmType=1&processId=ACCEPTANCEBPMN', 4, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('120010', 1, 0, '1', '004008', '项目权限设置', '100002', null, 8, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001005', 2, 1, '1', '004008001', '部门用户对一级专项', '120010', '/spf/spf/secu/userToType.do?usertype=2&busiTypeID=SPF', 1, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001006', 2, 1, '1', '004008002', '财政用户对一级专项', '120010', '/spf/spf/secu/userToType.do?usertype=1&busiTypeID=SPF', 2, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001008', 2, 1, '1', '004008003', '角色对一级专项', '120010', '/spf/spf/secu/roletotype.do', 3, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001004', 2, 1, '1', '004008004', '部门用户对专项资金', '120010', '/spf/spf/secu/userToSPF.do?usertype=2&busiTypeID=SPF', 4, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001003', 2, 1, '1', '004008005', '财政用户对专项资金', '120010', '/spf/spf/secu/userToSPF.do?usertype=1&busiTypeID=SPF', 5, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001007', 2, 1, '1', '004008006', '角色对专项资金', '120010', '/spf/spf/secu/roletospf.do', 6, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('120012', 1, 0, '1', '004009', '项目录入设置', '100002', null, 9, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('1144', 2, 1, '1', '004009001', '专项资金类别', '120012', '/spf/spf/setting/projecttype/projectTypePage.do?dealtype=4*30&spftype=F', 1, null, 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001200', 2, 1, '1', '004009002', '专项编码设置', '120012', '/spf/spf/setting/projCode.do?type=1', 2, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001201', 2, 1, '1', '004009003', '项目编码设置', '120012', '/spf/spf/setting/projCode.do?type=0', 3, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001202', 2, 1, '1', '004009004', '专项资金附件设置', '120012', '/spf/spf/setting/attach.do', 4, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001203', 2, 1, '1', '004009005', '项目附件设置', '120012', '/spf/spf/setting/specAttach.do', 5, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001204', 2, 1, '1', '004009006', '项目阶段报表设置', '120012', '/spf/spf/setting/projreport.do?psuit=F6A4E2F5DF1D96F7E040A8C0200352B4&fsuit=F6A4E2F5DF0996F7E040A8C0200352B4', 6, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001009', 2, 1, '1', '004009007', '专项（一级项目）主信息表对应附表', '120010', '/spf/spf/secu/coltotab.do?tId=4*01&suitId=F6A4E2F5DF0996F7E040A8C0200352B4', 7, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001010', 2, 1, '1', '004009008', '项目（二级项目）主信息表对应附表', '120010', '/spf/spf/secu/coltotab.do?tId=5*01&suitId=F6A4E2F5DF1D96F7E040A8C0200352B4', 8, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12001205', 2, 1, '1', '004009009', '项目测算公式设置', '120012', '/spf/spf/quotamanage/formulaset.do', 9, null, 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC036390DE050A8C0210AAED3', 2, 1, '1', '004009010', '数据审核设置', '120012', '/spf/exp/setting/businessAudit/businessAuditPage.do?appId=SPF', 10, null, 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('120021', 2, 0, '1', '004010', '系统设置', '100002', null, 10, null, 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11202104', 2, 1, '1', '004010001', '主从关系设置', '120021', '/spf/commons/setting/input/mainSubRela.do?appId=SPF', 1, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11202113', 2, 1, '1', '004010002', '角色快捷菜单设置', '120021', '/spf/commons/setting/shortcutMenu/init.do', 2, null, 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('121110', 2, 1, '1', '004010003', '数据权限设置', '120021', '/spf/commons/secu/roleToAgency.do', 3, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC036390DE050A8C0210AAEDD', 2, 1, '1', '004010004', '用户对应单位', '120021', '/spf/commons/secu/roleToAgency.do?type=2', 4, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11201407', 2, 1, '1', '004010005', '数据库维护', '120021', '/spf/commons/setting/manage/main.do?appId=spf', 5, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('256EA8EC1F8E1BBBE050A8C0210507BF', 2, 1, '1', '004010006', '异常管理', '120021', '/spf/commons/setting/exception/page.do?option=manage', 6, null, 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('256EA8EC1F8E1BBBE050A8C0210507RE', 2, 1, '1', '004010007', '异常查看', '120021', '/spf/commons/setting/exception/page.do?appId=SPF&option=view', 7, null, 1, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('100001FF', 0, 0, '1', '005', '预算编制', '0', '/bgt/commons/portal/home.do?appId=bgt&systemName=%E9%A2%84%E7%AE%97%E7%BC%96%E5%88%B6', 20, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('130001', 1, 0, '1', '005001', '本级预算编制', '100001FF', null, 1, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('113117', 2, 1, '1', '005001001', '数据填报_项目支出基本支出_测算模式', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT&operate=7', 1, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('174DBA542FC9CCF6E050A8C021055C5E', 2, 1, '1', '005001002', '数据填报_基本支出_测算模式', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT&operate=72', 2, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('295658F7122F619DE050A8C02105714F', 2, 1, '1', '005001003', '数据填报_项目支出基本支出_结果模式', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT&operate=6', 3, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2999FC18B5115A87E050A8C021050FD3', 2, 1, '1', '005001004', '数据填报_基本支出_结果模式', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT&operate=62', 4, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('113118', 2, 1, '1', '005001005', '数据查看_项目支出基本支出', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT&opt=view', 5, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('174DBA542FCACCF6E050A8C021055C5E', 2, 1, '1', '005001006', '数据查看_基本支出', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT&operate=2&opt=view', 6, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('D4ED5FE3E0EF421981AE23E52B270CCE', 2, 1, '1', '005001007', '基本支出代录_测算模式', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT&operate=47', 7, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('29BE64D2EDEBA62EE050A8C021056708', 2, 1, '1', '005001008', '基本支出代录_结果模式', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT&operate=46', 8, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('1E46DDFA344754C9E050A8C0210546F7', 2, 1, '1', '005001009', '数据备份', '130001', '/bgt/exp/backup/backup.do?appID=BGT', 9, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('113115', 2, 1, '1', '005001010', '批量数据审核', '130001', '/bgt/exp/zhshOA/audit/main.do?appID=BGT', 10, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('113144', 2, 1, '1', '005001011', '预算确认', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT&operate=1', 11, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2E46DDFA344754C9E050A8C0210546F7', 2, 1, '1', '005001013', '数据比较', '130001', '/bgt/exp/dataCompare.do?appID=BGT', 13, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2E46DDFA344754C9E050A8C0221546F7', 2, 1, '1', '005001014', '数据日志', '130001', '/bgt/exp/dataLog.do?appID=BGT', 14, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('CAC8404A33EF0221EF1762F8CF5C46D0', 2, 1, '1', '005001015', '部门预算记账', '130001', '/bcom/core/autobill/add.page?mouldid=10D6D9064A2B5BCBE0531A03A8C05BAD&vchtypeid=3E651E5D8D6C825AD1A32FBE148B7741&mainmenu=&submenu=3E651E5D8D6C825AD1A32FBE148B7741&appid=bgt', 15, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('32111351D47911FEE050A8C021055CA1', 2, 1, '1', '005001016', '基本支出调整', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT&operate=82', 16, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('29BE64D2EDECA62EE050A8C021056708', 2, 1, '1', '005001017', '基本支出代录调整', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT&operate=482', 17, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('W5D11351D47911FEE050A8C021055CA1', 1, 0, '1', '005002', '对下转移预算编制', '100001FF', null, 2, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('25D11351D47911FEE050A8C021055CA1', 2, 1, '1', '005002001', '转移支出项目预算填报', 'W5D11351D47911FEE050A8C021055CA1', '/bgt/exp/datainputOA/main.do?appID=BGT&operate=75', 1, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('25D32FDC2907BD9CE050A8C0210575E2', 2, 1, '1', '005002002', '转移支出项目查看', 'W5D11351D47911FEE050A8C021055CA1', '/bgt/exp/datainputOA/main.do?appID=BGT&operate=5&opt=view', 2, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('25D32FDC2908BD9CE050A8C0210575E2', 2, 1, '1', '005002003', '转移支出项目确认', 'W5D11351D47911FEE050A8C021055CA1', '/bgt/exp/datainputOA/main.do?appID=BGT&operate=51', 3, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('140001', 1, 0, '1', '005003', '控制数测算', '100001FF', null, 3, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('140001003', 3, 1, '1', '005003001', '测算模型定义', '140001', '/bgt/est/estModel.do?style=1&appId=BGT', 1, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('140001004', 3, 1, '1', '005003002', '测算结果管理', '140001', '/bgt/est/estResult/main.do?style=1&appID=BGT', 2, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('140001005', 3, 1, '1', '005003003', '来源表维护', '140001', '/bgt/est/estsrctab/main.do?style=1&appID=BGT', 3, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('110001', 1, 0, '1', '005004', '数据权限设置', '100001FF', null, 4, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('111002', 2, 1, '1', '005004001', '数据权限设置', '110001', '/bgt/commons/secu/roleToAgency.do', 1, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('111003', 2, 1, '1', '005004002', '处室对单位', '110001', '/bgt/exp/secu/agencyDept/agencyDeptPage.do', 2, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('15761DCAC036390DE050A8C0210AAE32', 2, 1, '1', '005004003', '用户对应单位', '110001', '/bgt/commons/secu/roleToAgency.do?type=2', 3, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('113000', 1, 0, '1', '005005', '基本支出设置', '100001FF', null, 5, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('1130002', 2, 1, '1', '005005001', '预算来源关系设置', '113000', '/bgt/exp/basicFundSource/main.do', 1, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('1130003', 2, 1, '1', '005005002', '单位项目类别对应科目', '113000', '/bgt/exp/basicProjectTypeTo/main.do', 2, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('1130004', 2, 1, '1', '005005003', '定额公式设置', '113000', '/bgt/exp/quotamanage/formulasetNew.do?busiTypeID=BGT&dealType=2201', 3, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('1130005', 2, 1, '1', '005005004', '标准类档维护', '113000', '/bgt/exp/quotamanage/quotaset.do', 4, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('1130006', 2, 1, '1', '005005005', '单位对应标准类档', '113000', '/bgt/exp/quotamanage/agstangra.do?busiTypeID=BGT', 5, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('295658F71232619DE050A8C02105714F', 2, 1, '1', '005005006', '定额公式设置_调整', '113000', '/bgt/exp/quotamanage/formulasetNew.do?busiTypeID=BGT&dealType=2401', 6, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('W95658F71232619DE050A8C02105714F', 1, 0, '1', '005006', '系统设置', '100001FF', null, 6, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('114004', 2, 1, '1', '005006001', '数据审核设置', 'W95658F71232619DE050A8C02105714F', '/bgt/exp/setting/businessAudit/businessAuditPage.do?appId=BGT', 1, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('21F4FB35375C7285E053C064A8C0A960', 2, 1, '1', '005006002', '数据库维护', 'W95658F71232619DE050A8C02105714F', '/bgt/commons/setting/manage/main.do?appId=bgt', 2, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('24419773306E33C2E050A8C021052853', 2, 1, '1', '005006003', '异常管理', 'W95658F71232619DE050A8C02105714F', '/bgt/commons/setting/exception/page.do?appId=BGT&option=manage', 3, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('24533F53F58FCA01E050A8C021057340', 2, 1, '1', '005006004', '异常查看', 'W95658F71232619DE050A8C02105714F', '/bgt/commons/setting/exception/page.do?appId=BGT&option=view', 4, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('115002', 2, 1, '1', '005006005', '主从关系设置', 'W95658F71232619DE050A8C02105714F', '/bgt/commons/setting/input/mainSubRela.do?appId=BGT', 5, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('115007', 2, 1, '1', '005006006', '主表列属性对应附表定义', 'W95658F71232619DE050A8C02105714F', '/bgt/exp/setting/coltotable/coltotab.do', 6, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('110003', 1, 0, '1', '005007', '任务管理', '100001FF', null, 7, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('113102', 2, 1, '1', '005007001', '任务类型管理', '110003', '/bgt/exp/setting/tasktype.do?appID=BGT', 1, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('113109', 2, 1, '1', '005007002', '创建任务', '110003', '/bgt/exp/taskdoc/taskCreate.do?appID=BGT', 2, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('113120', 2, 1, '1', '005007003', '任务初始化', '110003', '/bgt/exp/taskdoc/taskInit.do?appID=BGT', 3, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('113122', 2, 1, '1', '005007004', '查看任务', '110003', '/bgt/exp/taskdoc/taskDocInfo.do?appID=BGT', 4, 'remark', 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('114000', 1, 0, '1', '005008', '通用设置', '100001FF', null, 8, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('114001', 2, 1, '1', '005008001', '表类型缺省列设置', '114000', '/bgt/commons/dict/dicttdealtype.do', 1, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('114003', 2, 1, '1', '005008002', '业务要素管理', '114000', '/bgt/commons/dict/dicttcode.do', 2, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('114002', 2, 1, '1', '005008003', '录入表定义', '114000', '/bgt/commons/dict/dictCommons.do', 3, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('115003', 2, 1, '1', '005008004', '公式定义', '114000', '/bgt/commons/setting/formula.do?appId=BGT', 4, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('114007', 2, 1, '1', '005008005', '公式刷新顺序设置', '114000', '/bgt/exp/formulaRefreshOrder/main.do?appID=BGT', 5, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('114005', 2, 1, '1', '005008006', '公式刷新', '114000', '/bgt/exp/formulaRefresh.do?appID=BGT', 6, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('114051', 2, 1, '1', '005008007', '数据下发关联表设置', '114000', '/bgt/commons/dict/dicttrelation.do?appid=BGT', 7, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('24172F329468E63AE050A8C0210541A2', 2, 1, '1', '005008008', '审核视图登记', '114000', '/bgt/commons/setting/dataaudit/viewTotable/page.do', 8, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('242BF5113D6AB141E050A8C02105302D', 2, 1, '1', '005008009', '录入审核对象登记', '114000', '/bgt/commons/setting/dataaudit/modelToAuditobj/page.do', 9, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('115001', 2, 1, '1', '005008010', '录入界面定义', '114000', '/bgt/commons/setting/input.do?appId=BGT', 10, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('115006', 1, 1, '1', '005008011', '数据审核公式定义', '114000', '/bgt/commons/setting/auditrule/auditRulePage.do?appId=BGT', 11, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('269691062A7830C5E050A8C021052698', 2, 1, '1', '005008012', '分区表设置', '114000', '/bgt/commons/setting/regist/regist.do?appid=bgt', 12, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('114006', 2, 1, '1', '005008013', '业务规范下发设置', '114000', '/bgt/synch2/config.do', 13, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('092022D819058EFEE050A8C02105570B', 0, 0, '1', '006', '绩效评价
', '0', '/spf/kpi/commons/portal/KPIhome.do?appId=kpi&systemName=%E9%A2%84%E7%AE%97%E8%AF%84%E4%BB%B7', 21, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22001', 1, 0, '1', '006001', '项目绩效管理', '092022D819058EFEE050A8C02105570B', null, 1, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200103', 2, 0, '1', '006001001', '项目绩效运行监控管理', '22001', null, 3, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010301', 3, 1, '1', '006001001001', '项目绩效信息任务采集', '2200103', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=projSelect&projmType=1&processId=message&isReadOnly=1', 4, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010302', 3, 1, '1', '006001001002', '项目绩效信息任务填报', '2200103', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageInput&projmType=1&processId=message', 5, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010303', 3, 1, '1', '006001001003', '项目绩效信息任务查看', '2200103', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageInput&projmType=1&processId=message&isReadOnly=1', 6, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010304', 3, 1, '1', '006001001004', '项目绩效信息任务确认', '2200103', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageAudit&projmType=1&processId=message&isReadOnly=1', 7, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010305', 3, 1, '1', '006001001005', '项目绩效信息整改任务采集', '2200103', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=projSelect&projmType=1&processId=update&isReadOnly=1', 8, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010306', 3, 1, '1', '006001001006', '项目绩效信息整改任务填报', '2200103', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageInput&projmType=1&processId=update', 9, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010307', 3, 1, '1', '006001001007', '项目绩效信息整改任务查看', '2200103', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageInput&projmType=1&processId=update&isReadOnly=1', 10, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010308', 3, 1, '1', '006001001008', '项目绩效信息整改任务确认', '2200103', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageAudit&projmType=1&processId=update&isReadOnly=1', 11, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200104', 2, 0, '1', '006001002', '项目绩效评价管理', '22001', null, 12, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010402', 3, 0, '1', '006001002001', '项目绩效自评信息管理', '2200104', null, 13, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22001040201', 4, 1, '1', '006001002001001', '项目绩效自评信息任务采集', '220010402', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=projSelect&projmType=1&processId=self&isReadOnly=1', 14, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22001040202', 4, 1, '1', '006001002001002', '项目绩效自评信息任务填报', '220010402', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageInput&projmType=1&processId=self', 15, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22001040203', 4, 1, '1', '006001002001003', '项目绩效自评信息任务查看', '220010402', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageInput&projmType=1&processId=self&isReadOnly=1', 16, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22001040204', 4, 1, '1', '006001002001004', '项目绩效自评信息任务确认', '220010402', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageAudit&projmType=1&processId=self&isReadOnly=1', 17, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010403', 3, 0, '1', '006001002002', '项目绩效考评管理', '2200104', null, 18, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22001040301', 4, 1, '1', '006001002002001', '项目绩效考评任务采集', '220010403', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=projSelect&projmType=1&processId=eval&isReadOnly=1', 19, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22001040302', 4, 1, '1', '006001002002002', '项目绩效考评任务填写', '220010403', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageInput&projmType=1&processId=eval', 20, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22001040303', 4, 1, '1', '006001002002003', '项目绩效考评任务编辑', '220010403', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageInput&projmType=1&processId=eval&isReadOnly=0', 21, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22001040304', 4, 1, '1', '006001002002004', '项目绩效考评任务确认', '220010403', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageAudit&projmType=1&processId=eval&isReadOnly=1', 22, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200105', 2, 0, '1', '006001003', '项目绩效调整管理', '22001', null, 23, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010501', 3, 1, '1', '006001003001', '项目绩效调整采集', '2200105', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=projAdjustSelect&projmType=1&processId=adjust&isReadOnly=1', 24, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010502', 3, 1, '1', '006001003002', '项目绩效调整填报', '2200105', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=projAdjustInput&projmType=1&processId=adjust', 25, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010503', 3, 1, '1', '006001003003', '项目绩效调整查看', '2200105', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=projAdjustInput&projmType=1&processId=adjust&isReadOnly=1', 26, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010504', 3, 1, '1', '006001003004', '项目绩效调整确认', '2200105', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=projAdjustAudit&projmType=1&processId=adjust&isReadOnly=1', 27, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200106', 2, 0, '1', '006001004', '项目绩效评价结果管理', '22001', null, 28, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010601', 3, 1, '1', '006001004001', '项目绩效评价结果采集', '2200106', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=projSelect&projmType=1&processId=result&isReadOnly=1', 29, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010602', 3, 1, '1', '006001004002', '项目绩效评价结果填报', '2200106', '/spf/kpi/project/evalResult/main.do?dealtype=KP50&redealtype=RE02&kpiType=1&kpiStage=projInputEvalResult&projmType=1&processId=result', 30, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010603', 3, 1, '1', '006001004003', '项目绩效评价结果查看', '2200106', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageInput&projmType=1&processId=result&isReadOnly=1', 31, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010604', 3, 1, '1', '006001004004', '项目绩效评价结果确认', '2200106', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageAudit&projmType=1&processId=result&isReadOnly=1', 32, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200107', 2, 0, '1', '006001005', '项目绩效专家评价管理', '22001', null, 23, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010701', 3, 1, '1', '006001005001', '项目绩效专家选择', '2200107', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=expertSelect&zjDealType=ZJ50&zhongjDealType=ZJ40&projmType=1&processId=expert&isReadOnly=1', 24, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010702', 3, 1, '1', '006001005002', '项目绩效专家评价填报', '2200107', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageInput&projmType=1&processId=expert', 25, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010703', 3, 1, '1', '006001005003', '项目绩效专家评价查看', '2200107', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageInput&projmType=1&processId=expertAudit&isReadOnly=1', 26, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010704', 3, 1, '1', '006001005004', '项目绩效专家评价确认', '2200107', '/spf/kpi/project/message/main.do?dealtype=KP50&kpiType=1&kpiStage=messageAudit&projmType=1&processId=expertAudit&isReadOnly=1', 27, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22003', 1, 0, '1', '006002', '专项绩效管理', '092022D819058EFEE050A8C02105570B', null, 2, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200303', 2, 0, '1', '006002001', '专项绩效运行监控管理', '22003', null, 34, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030301', 3, 1, '1', '006002001001', '专项绩效信息任务采集', '2200303', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageSelect&spfmType=1&processId=message&isReadOnly=1', 35, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030302', 3, 1, '1', '006002001002', '专项绩效信息任务填报', '2200303', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageInput&spfmType=1&processId=message', 36, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030303', 3, 1, '1', '006002001003', '专项绩效信息任务查看', '2200303', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageInput&spfmType=1&processId=message&isReadOnly=1', 37, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030304', 3, 1, '1', '006002001004', '专项绩效信息任务确认', '2200303', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageAudit&spfmType=1&processId=message&isReadOnly=1', 38, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030305', 2, 1, '1', '006002001005', '专项绩效信息整改任务采集', '2200303', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageAdjustSelect&spfmType=1&processId=update&isReadOnly=1', 39, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030306', 3, 1, '1', '006002001006', '专项绩效信息整改任务填报', '2200303', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageAdjustInput&spfmType=1&processId=update', 40, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030307', 3, 1, '1', '006002001007', '专项绩效信息整改任务查看', '2200303', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageAdjustInput&spfmType=1&processId=update&isReadOnly=1', 41, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030308', 3, 1, '1', '006002001008', '专项绩效信息整改任务确认', '2200303', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageAdjustAudit&spfmType=1&processId=update&isReadOnly=1', 42, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200304', 2, 0, '1', '006002002', '专项绩效评价管理', '22003', null, 43, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030402', 3, 0, '1', '006002002001', '专项绩效自评信息管理', '2200304', null, 44, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22003040201', 4, 1, '1', '006002002001001', '专项绩效自评信息任务采集', '220030402', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageSelect&spfmType=1&processId=self&isReadOnly=1', 45, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22003040202', 4, 1, '1', '006002002001002', '专项绩效自评信息任务填报', '220030402', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageInput&spfmType=1&processId=self', 46, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22003040203', 4, 1, '1', '006002002001003', '专项绩效自评信息任务查看', '220030402', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageInput&spfmType=1&processId=self&isReadOnly=1', 47, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22003040204', 4, 1, '1', '006002002001004', '专项绩效自评信息任务确认', '220030402', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageAudit&spfmType=1&processId=self&isReadOnly=1', 48, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030403', 3, 0, '1', '006002002002', '专项绩效评价管理', '2200304', null, 49, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22003040301', 4, 1, '1', '006002002002001', '专项绩效考评任务采集', '220030403', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageSelect&spfmType=1&processId=eval&isReadOnly=1', 50, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22003040302', 4, 1, '1', '006002002002002', '专项绩效考评任务填写', '220030403', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageInput&spfmType=1&processId=eval', 51, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22003040303', 4, 1, '1', '006002002002003', '专项绩效考评任务编辑', '220030403', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageInput&spfmType=1&processId=eval&isReadOnly=0', 52, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22003040304', 4, 1, '1', '006002002002004', '专项绩效考评任务确认', '220030403', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageAudit&spfmType=1&processId=eval&isReadOnly=1', 53, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200305', 2, 0, '1', '006002003', '专项绩效调整管理', '22003', null, 54, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030501', 3, 1, '1', '006002003001', '专项绩效调整采集', '2200305', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageAdjustSelect&spfmType=1&processId=adjust&isReadOnly=1', 55, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030502', 3, 1, '1', '006002003002', '专项绩效调整填报', '2200305', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageAdjustInput&spfmType=1&processId=adjust', 56, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030503', 3, 1, '1', '006002003003', '专项绩效调整查看', '2200305', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageAdjustInput&spfmType=1&processId=adjust&isReadOnly=1', 57, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030504', 3, 1, '1', '006002003004', '专项绩效调整确认', '2200305', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageAdjustAudit&spfmType=1&processId=adjust&isReadOnly=1', 58, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200306', 2, 0, '1', '006002004', '专项绩效评价结果管理', '22003', null, 59, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030601', 3, 1, '1', '006002004001', '专项绩效评价结果采集', '2200306', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageSelect&spfmType=1&processId=result&isReadOnly=1', 60, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030602', 3, 1, '1', '006002004002', '专项绩效评价结果填报', '2200306', '/spf/kpi/project/evalResult/main.do?dealtype=KP50&redealtype=RE02&kpiType=2&kpiStage=messageAdjustInput&spfmType=1&processId=adjust', 61, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030603', 3, 1, '1', '006002004003', '专项绩效评价结果查看', '2200306', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageInput&spfmType=1&processId=result&isReadOnly=0', 62, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030604', 3, 1, '1', '006002004004', '专项绩效评价结果确认', '2200306', '/spf/kpi/spf/message/main.do?dealtype=KF40&kpiType=2&kpiStage=messageAudit&spfmType=1&processId=result&isReadOnly=1', 63, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22004', 1, 0, '1', '006003', '部门绩效管理', '092022D819058EFEE050A8C02105570B', null, 3, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200401', 2, 0, '1', '006003001', '部门绩效目标管理', '22004', null, 65, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040101', 3, 1, '1', '006003001001', '部门绩效目标任务采集', '2200401', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageSelect&processId=target&isReadOnly=1', 66, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040102', 3, 1, '1', '006003001002', '部门绩效目标填报', '2200401', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageInput&processId=target', 67, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040103', 3, 1, '1', '006003001003', '部门绩效目标查看', '2200401', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageInput&processId=target&isReadOnly=1', 68, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040104', 3, 1, '1', '006003001004', '部门绩效目标确认', '2200401', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageAudit&processId=target&isReadOnly=1', 69, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200403', 2, 0, '1', '006003002', '部门绩效运行监控管理', '22004', null, 65, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040301', 3, 1, '1', '006003002001', '部门绩效信息任务采集', '2200403', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageSelect&processId=message&isReadOnly=1', 66, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040302', 3, 1, '1', '006003002002', '部门绩效信息任务填报', '2200403', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageInput&processId=message', 67, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040303', 3, 1, '1', '006003002003', '部门绩效信息任务查看', '2200403', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageInput&processId=message&isReadOnly=1', 68, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040304', 3, 1, '1', '006003002004', '部门绩效信息任务确认', '2200403', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageAudit&processId=message&isReadOnly=1', 69, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040305', 3, 1, '1', '006003002005', '部门绩效信息整改任务采集', '2200403', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageAdjustSelect&processId=update&isReadOnly=1', 70, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040306', 3, 1, '1', '006003002006', '部门绩效信息整改任务填报', '2200403', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageAdjustInput&processId=update', 71, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040307', 3, 1, '1', '006003002007', '部门绩效信息整改任务查看', '2200403', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageInput&processId=update&isReadOnly=1', 72, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040308', 3, 1, '1', '006003002008', '部门绩效信息整改任务确认', '2200403', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageAudit&processId=update&isReadOnly=1', 73, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200404', 2, 0, '1', '006003004', '部门绩效评价管理', '22004', null, 74, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040401', 3, 1, '1', '006003004001', '部门绩效评价采集', '2200404', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageSelect&processId=self&isReadOnly=1', 1, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040402', 3, 1, '1', '006003004002', '部门绩效评价填报', '2200404', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageInput&processId=self', 2, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040403', 3, 1, '1', '006003004003', '部门绩效评价查看', '2200404', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageInput&processId=self&isReadOnly=1', 3, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040404', 3, 1, '1', '006003004004', '部门绩效评价确认', '2200404', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageAudit&processId=self&isReadOnly=1', 4, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200405', 2, 0, '1', '006003005', '部门绩效调整管理', '22004', null, 79, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040501', 3, 1, '1', '006003005001', '部门绩效调整采集', '2200405', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageSelect&processId=adjust&isReadOnly=1', 80, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040502', 3, 1, '1', '006003005002', '部门绩效调整填报', '2200405', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageInput&processId=adjust', 81, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040503', 3, 1, '1', '006003005003', '部门绩效调整查看', '2200405', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageInput&processId=adjust&isReadOnly=0', 82, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040504', 3, 1, '1', '006003005004', '部门绩效调整确认', '2200405', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageAudit&processId=adjust&isReadOnly=1', 83, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200406', 2, 0, '1', '006003006', '部门绩效评价结果管理', '22004', null, 84, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040601', 3, 1, '1', '006003006001', '部门绩效评价结果采集', '2200406', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageSelect&processId=result&isReadOnly=1', 1, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040602', 3, 1, '1', '006003006002', '部门绩效评价结果填报', '2200406', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageInput&processId=result', 2, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040603', 3, 1, '1', '006003006003', '部门绩效评价结果查看', '2200406', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageInput&processId=result&isReadOnly=0', 3, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040604', 3, 1, '1', '006003006004', '部门绩效评价结果确认', '2200406', '/spf/kpi/department/message/main.do?dealtype=60&kpiStage=messageAudit&processId=result&isReadOnly=1', 4, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22005', 1, 0, '1', '006004', '财政绩效管理', '092022D819058EFEE050A8C02105570B', null, 4, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200501', 2, 0, '1', '006004001', '财政绩效目标管理', '22005', null, 1, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050101', 3, 1, '1', '006004001001', '财政绩效目标任务采集', '2200501', '/spf/kpi/finance/message/main.do?dealtype=70&kpiStage=messageSelect&processId=target&isReadOnly=1', 1, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050102', 3, 1, '1', '006004001002', '财政绩效目标填报', '2200501', '/spf/kpi/finance/message/main.do?dealtype=70&kpiStage=messageInput&processId=target', 2, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050103', 3, 1, '1', '006004001003', '财政绩效目标查看', '2200501', '/spf/kpi/finance/message/main.do?dealtype=70&kpiStage=messageInput&processId=target&isReadOnly=1', 3, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050104', 3, 1, '1', '006004001004', '财政绩效目标确认', '2200501', '/spf/kpi/finance/message/main.do?dealtype=70&kpiStage=messageAudit&processId=target&isReadOnly=1', 4, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200502', 2, 0, '1', '006004002', '财政绩效自评管理', '22005', null, 2, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050201', 3, 1, '1', '006004002001', '财政绩效自评任务采集', '2200502', '/spf/kpi/finance/message/main.do?dealtype=70&kpiStage=messageSelect&processId=self&isReadOnly=1', 1, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050202', 3, 1, '1', '006004002002', '财政绩效自评任务填报', '2200502', '/spf/kpi/finance/message/main.do?dealtype=70&kpiStage=messageInput&processId=self', 2, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050203', 3, 1, '1', '006004002003', '财政绩效自评任务查看', '2200502', '/spf/kpi/finance/message/main.do?dealtype=70&kpiStage=messageInput&processId=self&isReadOnly=1', 3, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050204', 3, 1, '1', '006004002004', '财政绩效自评任务确认', '2200502', '/spf/kpi/finance/message/main.do?dealtype=70&kpiStage=messageAudit&processId=self&isReadOnly=1', 4, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200503', 2, 0, '1', '006004003', '财政绩效评价结果管理', '22005', null, 3, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050301', 3, 1, '1', '006004003001', '财政绩效评价结果采集', '2200503', '/spf/kpi/finance/message/main.do?dealtype=70&kpiStage=messageSelect&processId=result&isReadOnly=1', 1, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050302', 3, 1, '1', '006004003002', '财政绩效评价结果填报', '2200503', '/spf/kpi/finance/message/main.do?dealtype=70&kpiStage=messageInput&processId=result', 2, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050303', 3, 1, '1', '006004003003', '财政绩效评价结果查看', '2200503', '/spf/kpi/finance/message/main.do?dealtype=70&kpiStage=messageInput&processId=result&isReadOnly=1', 3, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050304', 3, 1, '1', '006004003004', '财政绩效评价结果确认', '2200503', '/spf/kpi/finance/message/main.do?dealtype=70&kpiStage=messageAudit&processId=result&isReadOnly=1', 4, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200507', 2, 0, '1', '006004004', '财政绩效专家评价管理', '22005', null, 4, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050701', 3, 1, '1', '006004004001', '财政绩效专家选择', '2200507', '/spf/kpi/finance/message/main.do?dealtype=70&kpiType=1&kpiStage=expertSelect&zjDealType=ZJ50&zhongjDealType=ZJ40&processId=expert&isReadOnly=1', 1, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050702', 3, 1, '1', '006004004002', '财政绩效专家填报', '2200507', '/spf/kpi/finance/message/main.do?dealtype=70&kpiType=1&kpiStage=expertInput&zjDealType=ZJ50&zhongjDealType=ZJ40&processId=expert', 2, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050703', 3, 1, '1', '006004004003', '财政绩效专家查看', '2200507', '/spf/kpi/finance/message/main.do?dealtype=70&kpiType=1&kpiStage=expertAudit&zjDealType=ZJ50&zhongjDealType=ZJ40&processId=expertAudit&isReadOnly=1', 3, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050704', 3, 1, '1', '006004004004', '财政绩效专家确认', '2200507', '/spf/kpi/finance/message/main.do?dealtype=70&kpiType=1&kpiStage=expertAudit&zjDealType=ZJ50&zhongjDealType=ZJ40&processId=expertAudit&isReadOnly=1', 4, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22008', 1, 0, '1', '006005', '绩效口径管理', '092022D819058EFEE050A8C02105570B', null, 5, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200801', 2, 1, '1', '006005001', '绩效指标口径维护', '22008', '/spf/kpi/setting/kpicaliber.do', 1, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200802', 2, 1, '1', '006005002', '绩效评价指标口径维护', '22008', '/spf/kpi/setting/evalcaliber.do', 2, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200803', 2, 1, '1', '006005003', '录入表口径设置', '22008', '/spf/kpi/setting/inputcaliber.do', 3, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22009', 1, 0, '1', '006006', '绩效指标库管理', '092022D819058EFEE050A8C02105570B', null, 6, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200901', 2, 1, '1', '006006001', '专项绩效指标库', '22009', '/spf/kpi/setting/projkpi.do?kType=A2', 1, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200902', 2, 1, '1', '006006002', '项目绩效指标库', '22009', '/spf/kpi/setting/projkpi.do?kType=A1', 2, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22010', 1, 0, '1', '006007', '绩效评价共性指标库', '092022D819058EFEE050A8C02105570B', null, 7, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2201001', 2, 1, '1', '006007001', '专项绩效评价共性指标库', '22010', '/spf/kpi/setting/projeval.do?kType=B2', 1, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2201004', 2, 1, '1', '006007002', '财政绩效评价共性指标库', '22010', '/spf/kpi/setting/projeval.do?kType=B4', 2, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2201002', 2, 1, '1', '006007003', '项目绩效评价共性指标库', '22010', '/spf/kpi/setting/projeval.do?kType=B1', 3, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2201003', 2, 1, '1', '006007004', '部门绩效评价共性指标库', '22010', '/spf/kpi/setting/projeval.do?kType=B3', 4, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22011', 1, 0, '1', '006008', '绩效评价录入表维护', '092022D819058EFEE050A8C02105570B', null, 8, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2201101', 2, 1, '1', '006008001', '专项绩效录入表维护', '22011', '/spf/kpi/setting/input.do?kType=2', 1, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2201102', 2, 1, '1', '006008002', '项目绩效录入表维护', '22011', '/spf/kpi/setting/input.do?kType=1', 2, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2201103', 2, 1, '1', '006008003', '部门绩效录入表维护', '22011', '/spf/kpi/setting/input.do?kType=3', 3, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2201104', 2, 1, '1', '006008004', '财政绩效录入表维护', '22011', '/spf/kpi/setting/input.do?kType=4', 4, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22012', 1, 0, '1', '006009', '系统设置', '092022D819058EFEE050A8C02105570B', null, 9, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2201201', 2, 1, '1', '006009001', '公式定义', '22012', '/spf/commons/setting/formula.do?appId=KPI', 1, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2201202', 2, 1, '1', '006009002', '录入界面定义', '22012', '/spf/commons/setting/input.do?appId=KPI', 2, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2201203', 2, 1, '1', '006009003', '数据审核公式定义', '22012', '/spf/commons/setting/auditrule/auditRulePage.do?appId=KPI', 3, null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2201204', 2, 1, '1', '006009004', '角色快捷菜单设置', '22012', '/spf/kpi/setting/shortcutMenu/init.do', 4, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2201205', 2, 1, '1', '006009005', '系统功能维护', '22012', '/spf/commons/setting/manage/main.do?appId=kpi', 5, 'remark', 1, null, null, 'kpi', null, null, null, null, null, null, 1);
end if;
