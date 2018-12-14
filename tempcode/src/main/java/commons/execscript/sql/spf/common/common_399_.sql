begin
  delete from fasp_T_pubmenu where appid ='spf';
  insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('100002', 0, 0, '1', '004', '项目库', '0', null, 19, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('120001', 1, 0, '1', '004001', '专项资金管理', '100002', null, 1, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11200101', 2, 1, '1', '004001001', '专项资金设立', '120001', '/spf/spf/input/spfInputMain.do?spfmType=1'||CHR(38)||'dealType=40'||CHR(38)||'step=input'||CHR(38)||'mainDealType=4*01'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}', 1, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('1120010102', 2, 1, '1', '004001003', '专项资金查看', '120001', '/spf/spf/input/spfInputMain.do?spfmType=1'||CHR(38)||'dealType=40'||CHR(38)||'step=spfview'||CHR(38)||'mainDealType=4*01'||CHR(38)||'isReadOnly=1', 3, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11200102', 2, 1, '1', '004001005', '专项资金确认', '120001', '/spf/spf/audit/auditMain.do?dealType=41'||CHR(38)||'spfmType=1'||CHR(38)||'btShow={isAudit:''y''}', 5, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11200111', 2, 1, '1', '004001011', '专项资金审核', '120001', '/spf/spf/audit/auditMain.do?dealType=41'||CHR(38)||'spfmType=1'||CHR(38)||'btShow={isAudit:''y''}'||CHR(38)||'isLC=1', 6, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11202103', 2, 1, '1', '004001009', '专项资金调整', '120001', '/spf/spf/adjust/main.do?style=2'||CHR(38)||'dealtype=42'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=1', 9, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11202105', 2, 1, '1', '004001010', '专项资金调整审核', '120001', '/spf/spf/adjustaudit/main.do?style=2'||CHR(38)||'dealtype=42'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=1', 10, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11202106', 2, 1, '1', '004001006', '专项资金发布', '120001', '/spf/spf/spfpublish/getMainPage.do?dealtype=46', 6, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11202108', 2, 1, '1', '004001004', '专项资金备份', '120001', '/spf/spf/bak/bakMain.do?dealType=44'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=1'||CHR(38)||'bakLogDealType=4*52'||CHR(38)||'isReadOnly=1', 4, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('27CCC3197B1F4D0FBEF43C083ADF07B1', 2, 1, '1', '004001013', 'SPF上下级同步', '120001', '/spf/synch2/config.do', 9, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('6633c22b363641c4943c368794b406d0', 2, 1, '1', '004001018', '专项资金托管设置', '120001', '/spf/spf/setting/custody/custodyMain.do', 15, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('7AB2071676FE489EA6F8DE6C2C1B9869', 2, 1, '1', '004001012', '专项资金下发', '120001', '/spf/spf/export/spfSetting.do?dealtype=4*01', 12, 'remark', 2, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('120002', 1, 0, '1', '004002', '专项项目管理', '100002', null, 2, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11200201', 2, 1, '1', '004002001', '项目申报', '120002', '/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=50'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=5*01'||CHR(38)||'isPL=1'||CHR(38)||'isSb=1'||CHR(38)||'isFree=0', 1, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11200202', 2, 1, '1', '004002010', '项目确认', '120002', '/spf/project/audit/auditMain.do?dealType=51'||CHR(38)||'spfmType=1'||CHR(38)||'step=audit'||CHR(38)||'amountDealType=5*41'||CHR(38)||'btShow={isAudit:''y''}', 10, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11200203', 2, 1, '1', '004002006', '项目排序', '120002', '/spf/project/check/checkMain.do?dealType=59'||CHR(38)||'sameSpf=0'||CHR(38)||'step=audit'||CHR(38)||'spfmType=1'||CHR(38)||'btShow={isAudit:''y''}', 6, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11200204', 2, 1, '1', '004002008', '项目查看', '120002', '/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=projview'||CHR(38)||'dealType=50'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=5*01'||CHR(38)||'isReadOnly=1', 8, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11200205', 2, 1, '1', '004002011', '纳入预算', '120002', '/spf/spf/proj/inBudget/inputMain.do?dealtype=54'||CHR(38)||'projmType=1'||CHR(38)||'mainDealType=5*01'||CHR(38)||'spfmType=1'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}', 11, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('112002051', 2, 1, '1', '004002017', '纳入指标', '120002', '/spf/spf/proj/inBudget/inputIndexMain.do?dealtype=55'||CHR(38)||'projmType=1'||CHR(38)||'mainDealType=5*01'||CHR(38)||'spfmType=1'||CHR(38)||''||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}', 17, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11200209', 2, 1, '1', '004002002', '项目删除', '120002', '/spf/spf/proj/del/page.do?projmType=1'||CHR(38)||'dealtype=58'||CHR(38)||'mainDealType=5*01', 1, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11200212', 2, 1, '1', '004002011', '项目审核', '120002', '/spf/project/audit/auditMain.do?dealType=51'||CHR(38)||'spfmType=1'||CHR(38)||'step=audit'||CHR(38)||'amountDealType=5*41'||CHR(38)||'btShow={isAudit:''y''}'||CHR(38)||'isLC=1', 11, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('120002001', 2, 1, '1', '004002014', '项目调整', '120002', '/spf/spf/proj/adjust/inputMain.do?style=2'||CHR(38)||'projmType=1'||CHR(38)||'dealtype=52'||CHR(38)||'spfchsdealtype=5*11'||CHR(38)||'instead=0'||CHR(38)||'attention=0'||CHR(38)||'appid=SPF'||CHR(38)||'hisDealType=5*27', 14, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('120002002', 2, 1, '1', '004002015', '项目调整审核', '120002', '/spf/spf/proj/adjustaudit/main.do?style=2'||CHR(38)||'projmType=1'||CHR(38)||'dealtype=52'||CHR(38)||'spfchsdealtype=5*11'||CHR(38)||'instead=0'||CHR(38)||'attention=0'||CHR(38)||'appid=SPF'||CHR(38)||'hisDealType=5*27', 15, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('120002003', 2, 1, '1', '004002005', '项目备份', '120002', '/spf/project/bak/bakMain.do?step=projbak'||CHR(38)||'spfmType=1'||CHR(38)||'dealType=53'||CHR(38)||'bakLogDealType=4*53'||CHR(38)||'isReadOnly=1', 1, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('120002028', 2, 1, '1', '004002028', '查询项目(已删)', '120002', '/spf/spf/project/selectdelproj/selectmain.do?projmType=1'||CHR(38)||'dealtype=SCXMCX', 28, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('2295863373578E3CE050A8C021056471', 2, 1, '1', '004002004', '申报项目（自由）', '120002', '/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=50'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=5*01'||CHR(38)||'isPL=1'||CHR(38)||'isSb=1'||CHR(38)||'isFree=1', 1, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('28A64972D270028DE050A8C021050A79', 2, 1, '1', '004002023', '项目综合查询(申报)', '120002', '/spf/project/comprePage/page.do?dealtype=ZHCX*01'||CHR(38)||'isdec=1'||CHR(38)||'appId=SPF', 4, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('28A64972D272028DE050A8C021050A79', 2, 1, '1', '004002024', '项目综合查询(管理)', '120002', '/spf/project/comprePage/page.do?dealtype=ZHCX*01'||CHR(38)||'isdec=0'||CHR(38)||'appId=SPF', 3, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('29ACF4437D18BCB1E050A8C021053D73', 2, 1, '1', '004002025', '对下转移支付综合查询', '120002', '/spf/project/dx/comprePage/page.do?dealtype=ZHCX*01'||CHR(38)||'appId=SPF', 5, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('2A6528A83F97725BE0530100007F850A', 2, 1, '1', '004002013', '项目预算调整', '120002', '/spf/spf/proj/bgtadjust/auditMain.do?dealtype=56'||CHR(38)||'samespf=0'||CHR(38)||'step=projbgtadjust'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}', 2, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('3302E92C350BBD3AE0530100007F7598', 2, 1, '1', '004002026', '项目代录', '120002', '/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=50'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=5*01'||CHR(38)||'instead=1'||CHR(38)||'isPL=1'||CHR(38)||'isSb=1', 5, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('62E4B32357ED42409988EEC76625ED59', 2, 1, '1', '004002029', '项目预算调整确认', '120002', '/spf/spf/proj/bgtadjustaudit/main.do?style=2'||CHR(38)||'projmType=1'||CHR(38)||'dealtype=56'||CHR(38)||'appid=SPF', 29, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('120003', 1, 0, '1', '004003', '一级项目管理', '100002', null, 3, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000301', 2, 1, '1', '004003001', '一级项目设立', '120003', '/spf/spf/input/spfInputMain.do?spfmType=2'||CHR(38)||'dealType=30'||CHR(38)||'step=input'||CHR(38)||'mainDealType=3*01'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}', 1, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000303', 2, 1, '1', '004003003', '一级项目查看', '120003', '/spf/spf/input/spfInputMain.do?spfmType=2'||CHR(38)||'dealType=30'||CHR(38)||'step=spfview'||CHR(38)||'mainDealType=3*01'||CHR(38)||'isReadOnly=1', 3, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000304', 2, 1, '1', '004003005', '一级项目确认', '120003', '/spf/spf/audit/auditMain.do?dealType=31'||CHR(38)||'spfmType=2'||CHR(38)||'btShow={isAudit:''y''}', 5, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000305', 2, 1, '1', '004003006', '一级项目调整', '120003', '/spf/spf/adjust/main.do?style=2'||CHR(38)||'dealtype=32'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=2', 6, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000306', 2, 1, '1', '004003007', '一级项目调整审核', '120003', '/spf/spf/adjustaudit/main.do?style=2'||CHR(38)||'dealtype=32'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=2', 7, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000307', 2, 1, '1', '004003004', '一级本级项目备份', '120003', '/spf/spf/bak/bakMain.do?dealType=36'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=2'||CHR(38)||'bakLogDealType=4*52'||CHR(38)||'isReadOnly=1', 7, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000314', 2, 1, '1', '004003011', '一级项目审核', '120003', '/spf/spf/audit/auditMain.do?dealType=31'||CHR(38)||'spfmType=2'||CHR(38)||'btShow={isAudit:''y''}'||CHR(38)||'isLC=1', 5, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('120004', 1, 0, '1', '004004', '二级项目管理', '100002', null, 4, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000403', 2, 1, '1', '004004006', '二级项目查看', '120004', '/spf/project/input/projInputMain.do?spfmType=2'||CHR(38)||'step=projview'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'isReadOnly=1', 6, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000404', 2, 1, '1', '004004014', '二级项目纳入预算', '120004', '/spf/spf/proj/inBudget/inputMain.do?dealtype=P4'||CHR(38)||'projmType=1'||CHR(38)||'mainDealType=P*01'||CHR(38)||'spfmType=2'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}', 14, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000405', 2, 1, '1', '004004015', '二级项目纳入指标', '120004', '/spf/spf/proj/inBudget/inputIndexMain.do?dealtype=P5'||CHR(38)||'projmType=1'||CHR(38)||'mainDealType=P*01'||CHR(38)||'spfmType=2'||CHR(38)||''||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}', 15, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000406', 2, 1, '1', '004004012', '二级项目调整', '120004', '/spf/spf/proj/adjust/inputMain.do?style=2'||CHR(38)||'projmType=2'||CHR(38)||'dealtype=P2'||CHR(38)||'spfchsdealtype=5*11'||CHR(38)||'instead=0'||CHR(38)||'attention=0'||CHR(38)||'appid=SPF'||CHR(38)||'isreadonly=1', 12, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000407', 2, 1, '1', '004004013', '二级项目调整审核', '120004', '/spf/spf/proj/adjustaudit/main.do?style=2'||CHR(38)||'projmType=2'||CHR(38)||'dealtype=P2'||CHR(38)||'spfchsdealtype=5*11'||CHR(38)||'instead=0'||CHR(38)||'attention=0'||CHR(38)||'appid=SPF'||CHR(38)||'isreadonly=1', 13, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000408', 2, 1, '1', '004004010', '二级项目确认', '120004', '/spf/project/audit/auditMain.do?dealType=P1'||CHR(38)||'spfmType=2'||CHR(38)||'step=audit'||CHR(38)||'amountDealType=5*41'||CHR(38)||'btShow={isAudit:''y''}', 10, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000409', 2, 1, '1', '004004007', '二级项目选择排序', '120004', '/spf/project/check/checkMain.do?dealType=P9'||CHR(38)||'sameSpf=1'||CHR(38)||'step=audit'||CHR(38)||'spfmType=2'||CHR(38)||'btShow={isAudit:''y''}', 7, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000413', 2, 1, '1', '004004004', '二级项目删除', '120004', '/spf/spf/proj/del/page.do?projmType=2'||CHR(38)||'dealtype=P8'||CHR(38)||'mainDealType=P*01', 1, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000414', 2, 1, '1', '004004003', '二级项目代录', '120004', '/spf/project/input/projInputMain.do?spfmType=2'||CHR(38)||'step=input'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'instead=1'||CHR(38)||'isSb=1', 1, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000416', 2, 1, '1', '004004016', '查询二级项目（已删）', '120004', '/spf/spf/project/selectdelproj/selectmain.do?projmType=2'||CHR(38)||'dealtype=SCXMCX', 16, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12000418', 2, 1, '1', '004004011', '二级项目审核', '120004', '/spf/project/audit/auditMain.do?dealType=P1'||CHR(38)||'spfmType=2'||CHR(38)||'step=audit'||CHR(38)||'amountDealType=5*41'||CHR(38)||'btShow={isAudit:''y''}'||CHR(38)||'isLC=1', 10, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('22957CD1D93491A2E050A8C0210562F7', 2, 1, '1', '004004008', '二级项目备份', '120004', '/spf/project/bak/bakMain.do?step=projbak'||CHR(38)||'spfmType=2'||CHR(38)||'dealType=53'||CHR(38)||'bakLogDealType=4*53'||CHR(38)||'isReadOnly=1', 1, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('2295863373578E3CE050A8C021056472', 2, 1, '1', '004004002', '二级项目申报', '120004', '/spf/project/input/projInputMain.do?spfmType=2'||CHR(38)||'step=input'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'isPL=1'||CHR(38)||'isSb=1', 1, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('282E476639F720DBE050A8C0216515A3', 2, 1, '1', '004004023', '追加二级项目申报', '120004', '/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'isadd=1'||CHR(38)||'isSb=1', 2, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('282E476639F721DBE050A8C0216515A6', 2, 1, '1', '004004028', '二级项目预算调整确认', '120004', '/spf/spf/proj/bgtadjustaudit/main.do?style=2'||CHR(38)||'projmType=2'||CHR(38)||'dealtype=P6'||CHR(38)||'appid=SPF', 17, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('282E476639F820DBE050A8C0210538AE', 2, 1, '1', '004004017', '追加代录二级项目申报', '120004', '/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'instead=1'||CHR(38)||'isadd=1'||CHR(38)||'isPL=1'||CHR(38)||'isSb=1', 2, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('29481D6E981A028AE0530100007F8351', 2, 1, '1', '004004019', '二级项目预算调整', '120004', '/spf/spf/proj/bgtadjust/auditMain.do?dealtype=P6'||CHR(38)||'spfmType=2'||CHR(38)||'samespf=0'||CHR(38)||'step=projbgtadjust'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}', 2, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('3302E92C350CBD3AE0530100007F7598', 2, 1, '1', '004004021', '申报代录二级项目', '120004', '/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'instead=1'||CHR(38)||'isPL=1'||CHR(38)||'isSb=1', 2, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('120010', 1, 0, '1', '004008', '项目权限设置', '100002', null, 8, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001003', 2, 1, '1', '004008005', '财政用户对专项资金', '120010', '/spf/spf/secu/userToSPF.do?usertype=1'||CHR(38)||'busiTypeID=SPF', 5, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001004', 2, 1, '1', '004008004', '部门用户对专项资金', '120010', '/spf/spf/secu/userToSPF.do?usertype=2'||CHR(38)||'busiTypeID=SPF', 4, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001005', 2, 1, '1', '004008001', '部门用户对专项类别', '120010', '/spf/spf/secu/userToType.do?usertype=2'||CHR(38)||'busiTypeID=SPF', 1, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001006', 2, 1, '1', '004008002', '财政用户对专项类别', '120010', '/spf/spf/secu/userToType.do?usertype=1'||CHR(38)||'busiTypeID=SPF', 2, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001007', 2, 1, '1', '004008006', '角色对专项资金', '120010', '/spf/spf/secu/roletospf.do', 6, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001008', 2, 1, '1', '004008003', '角色对专项类别', '120010', '/spf/spf/secu/roletotype.do', 3, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001009', 2, 1, '1', '004009007', '专项（一级项目）主信息表对应附表', '120010', '/spf/spf/secu/coltotab.do?tId=4*01'||CHR(38)||'suitId=F6A4E2F5DF0996F7E040A8C0200352B4', 7, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001010', 2, 1, '1', '004009008', '项目（二级项目）主信息表对应附表', '120010', '/spf/spf/secu/coltotab.do?tId=5*01'||CHR(38)||'suitId=F6A4E2F5DF1D96F7E040A8C0200352B4', 8, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('BAAF20DF44B34831A79ECB5A7399232A', 2, 1, '1', '004009009', '主列表条件设置', '120010', '/spf/spf/setting/listCondiSet.do', 12, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('D1F3E5E3812E4CE6A36A9AECA075FEC0', 1, 1, '1', '004008007', '资金性质对资金来源', '120010', '/spf/spf/setting/fundTpToSour.do', 11, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('120012', 1, 0, '1', '004009', '项目录入设置', '100002', null, 9, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('004009011', 1, 1, '1', '004009011', '数据审核公式定义', '120012', '/spf/commons/setting/auditrule/auditRulePage.do?appId=SPF', 11, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('1144', 2, 1, '1', '004009001', '专项资金类别', '120012', '/spf/spf/setting/projecttype/projectTypePage.do?dealtype=4*30'||CHR(38)||'spftype=F', 1, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001200', 2, 1, '1', '004009002', '专项编码设置', '120012', '/spf/spf/setting/projCode.do?type=1', 2, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001201', 2, 1, '1', '004009003', '项目编码设置', '120012', '/spf/spf/setting/projCode.do?type=0', 3, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001202', 2, 1, '1', '004009004', '专项资金附件设置', '120012', '/spf/spf/setting/attach.do', 4, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001203', 2, 1, '1', '004009005', '项目附件设置', '120012', '/spf/spf/setting/specAttach.do', 5, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001204', 2, 1, '1', '004009006', '项目阶段报表设置', '120012', '/spf/spf/setting/projreport.do?psuit=F6A4E2F5DF1D96F7E040A8C0200352B4'||CHR(38)||'fsuit=F6A4E2F5DF0996F7E040A8C0200352B4', 6, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001205', 2, 1, '1', '12001205', '表类型缺省列设置', '120012', '/spf/commons/dict/dicttdealtype.do', 1, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001206', 2, 1, '1', '12001206', '业务要素管理', '120012', '/spf/commons/dict/dicttcode.do', 2, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001207', 2, 1, '1', '12001207', '录入表定义', '120012', '/spf/commons/dict/dictCommons.do', 3, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('120012111', 2, 1, '1', '120012111', '公式刷新_无任务', '120012', '/spf/exp/formulaCalculate.do?appID=SPF', 6, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001216', 2, 1, '1', '0241', '记账定义', '120012', '/spf/commons/setting/billdef/mpage.do?appId=SPF'||CHR(38)||'all=1', 11, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('12001217', 2, 1, '1', '0242', '记账定义类型', '120012', '/spf/commons/setting/billdeftype/mpage.do?appId=SPF', 12, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('15761DCAC036390DE050A8C0210AAED3', 2, 1, '1', '004009010', '数据审核设置', '120012', '/spf/exp/setting/businessAudit/businessAuditPage.do?appId=SPF', 10, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('120021', 2, 0, '1', '004010', '系统设置', '100002', null, 10, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('004010008', 2, 1, '1', '004010008', '录入界面定义', '120021', '/spf/commons/setting/input.do?appId=SPF', 10, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11201407', 2, 1, '1', '004010005', '系统维护', '120021', '/spf/commons/setting/manage/main.do?appId=spf', 5, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('11202104', 2, 1, '1', '004010001', '主从关系设置', '120021', '/spf/commons/setting/input/mainSubRela.do?appId=SPF', 1, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('121110', 2, 1, '1', '004010003', '数据权限设置', '120021', '/spf/commons/secu/roleToAgency.do', 3, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('15761DCAC036390DE050A8C0210AAEDD', 2, 1, '1', '004010004', '用户对应单位', '120021', '/spf/commons/secu/roleToAgency.do?type=2', 4, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('256EA8EC1F8E1BBBE050A8C0210507BF', 2, 1, '1', '004010006', '异常管理', '120021', '/spf/commons/setting/exception/page.do?option=manage', 9, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('256EA8EC1F8E1BBBE050A8C0210507RE', 2, 1, '1', '004010007', '异常查看', '120021', '/spf/commons/setting/exception/page.do?appId=SPF'||CHR(38)||'option=view', 7, null, 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('31E8B9EF5BF812B7E050A8C0210550CC', 2, 1, '1', '004010010', '业务配置功能表设置', '120021', '/spf/commons/setting/busimanage/busimanage.do', 12, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('31E8B9EF5BF812B7E050A8C0210550DD', 2, 1, '1', '004010011', '业务配置功能维护', '120021', '/spf/commons/setting/busimanage/busiView.do', 13, 'remark', 1, 'spf', 1);

insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, APPID, ADMINTYPE)
values ('241D34420C04497A9459DA0A1B26D1A1', 2, 0, '1', '004011', '阶段管理', '100002', null, 11, null, 1, 'spf', null);

-- 项目库 spf
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('DBE3BA4C3987A0A7025FF498AF74F05D', 1, 0, '1', '0040012', '业务驱动', '100002', '#', 12, null, null, null, null, 'spf', null, null, null, null, null, null, 1, null);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('9C33A62E610E78B295A72632FC3A337C', 2, 1, '1', '001001200001', '流程条件设置', 'DBE3BA4C3987A0A7025FF498AF74F05D', '/spf/bd/condition/conditionQuery.do', 1, null, null, null, null, 'spf', null, null, null, null, null, null, 1, null);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('071AEC4A2FA5A80768A40AC582A00710', 2, 1, '1', '001001200002', '业务类型', 'DBE3BA4C3987A0A7025FF498AF74F05D', '/spf/busiType/busiTypeSet.do', 2, null, null, null, null, 'spf', null, null, null, null, null, null, 1, null);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('40EA77415896D8DFA9BF80385ED46B1C', 2, 1, '1', '001001200003', '待办路径设置', 'DBE3BA4C3987A0A7025FF498AF74F05D', '/spf/bd/configure/urlConfigList.do', 3, null, null, null, null, 'spf', null, null, null, null, null, null, 1, null);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('84FBFCB1E468E0C0456472EA379A1812', 2, 1, '1', '001001200004', '流程定义', 'DBE3BA4C3987A0A7025FF498AF74F05D', '/spf/fasp/hqbpmn/template/tempList.jsp', 4, null, null, null, null, 'spf', null, null, null, null, null, null, 1, null);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('E053097F7736A40B0F786ED9CFB7443C', 2, 1, '1', '001001200005', '首页角色权限配置', 'DBE3BA4C3987A0A7025FF498AF74F05D', '/spf/bd/sys/roleManagePage.do', 5, null, null, null, null, 'spf', null, null, null, null, null, null, 1, null);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('4883D349AE9CE02094520325219E87B2', 2, 1, '1', '001001200006', '业务类型模板配置', 'DBE3BA4C3987A0A7025FF498AF74F05D', '/spf/busiType/busiTypeSet.do?serviceTempType=1', 6, null, null, null, null, 'spf', null, null, null, null, null, null, 1, null);

insert into FASP_T_PUBMENU (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('spfCreatebusi', 1, null, '1', '0040010', '发起业务', '100002', '3', 10, '', '', null, '', '', 'spf', '', '', '', '', '', '', null, '');

end;
--修改项目库菜单
