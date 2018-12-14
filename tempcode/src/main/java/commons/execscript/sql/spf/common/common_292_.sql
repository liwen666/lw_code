BEGIN 
--删除转移支付项目预算编制
DELETE FROM FASP_T_PUBMENU WHERE GUID ='11200192' AND CODE ='004001008';
--删除转移支付项目记帐
DELETE FROM FASP_T_PUBMENU WHERE GUID ='24A46FC5BB30EF34E050A8C021057D70' AND CODE ='004001011';
--删除转移支付项目下发设置
DELETE FROM FASP_T_PUBMENU WHERE GUID ='233969F3EEC10AE9E050A8C021050CC7' AND CODE ='01530101';
--删除二级项目倒挂
DELETE FROM FASP_T_PUBMENU WHERE GUID ='22957CD1D93691A2E050A8C0210562G8' AND CODE ='004002009';
--删除二级项目倒挂
DELETE FROM FASP_T_PUBMENU WHERE GUID ='12000412' AND CODE ='004004009';
--删除二级项目自由申报
DELETE FROM FASP_T_PUBMENU WHERE GUID ='12000410' AND CODE ='004004011';
--删除多余系统维护菜单
DELETE FROM FASP_T_PUBMENU WHERE GUID ='2336DD416BC2F3FFE050A8C021055F97' AND CODE ='11201407';
--删除对下项目确认
DELETE FROM FASP_T_PUBMENU WHERE GUID ='2A37B530B2658B53E050A8C021051764' AND CODE ='004001013';

UPDATE FASP_T_PUBMENU SET PARENTID ='120004' WHERE GUID ='3302E92C350CBD3AE0530100007F7598';


UPDATE FASP_T_PUBMENU SET URL='/spf/spf/input/spfInputMain.do?spfmType=1'||CHR(38)||'dealType=40'||CHR(38)||'step=input'||CHR(38)||'mainDealType=4*01'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}' WHERE  GUID ='11200101';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/input/spfInputMain.do?spfmType=1'||CHR(38)||'dealType=40'||CHR(38)||'step=input'||CHR(38)||'mainDealType=4*01'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}' WHERE  GUID ='1120010101';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/input/spfInputMain.do?spfmType=1'||CHR(38)||'dealType=40'||CHR(38)||'step=spfview'||CHR(38)||'mainDealType=4*01'||CHR(38)||'isReadOnly=1' WHERE  GUID ='1120010102';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/bak/bakMain.do?dealType=44'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=1'||CHR(38)||'bakLogDealType=4*52'||CHR(38)||'isReadOnly=1' WHERE  GUID ='11202108';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/audit/auditMain.do?dealType=41'||CHR(38)||'spfmType=1'||CHR(38)||'btShow={isAudit:''y''}' WHERE  GUID ='11200102';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/spfpublish/getMainPage.do?dealtype=46' WHERE  GUID ='11202106';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/allotment.do?appID=SPF' WHERE  GUID ='11202119';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/adjust/main.do?style=2'||CHR(38)||'dealtype=42'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=1' WHERE  GUID ='11202103';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/adjustaudit/main.do?style=2'||CHR(38)||'dealtype=42'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=1' WHERE  GUID ='11202105';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/fundcontrolladjust/getpage.do?style=2'||CHR(38)||'dealtype=SPF_40'||CHR(38)||'mainDealType=4*01'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=1'||CHR(38)||'isreadonly=0'||CHR(38)||'step=kzsadjust' WHERE  GUID ='36DDF8575D163CC4E050A8C0F0004A26';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/export/spfSetting.do?dealtype=4*01' WHERE  GUID ='7AB2071676FE489EA6F8DE6C2C1B9869';
UPDATE FASP_T_PUBMENU SET URL='/spf/synch2/config.do' WHERE  GUID ='27CCC3197B1F4D0FBEF43C083ADF07B1';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/setting/custody/custodyMain.do' WHERE  GUID ='6633c22b363641c4943c368794b406d0';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/backspaceSelect/selectMain.do?spfmType=1'||CHR(38)||'dealtype=4101'||CHR(38)||'step=spfBack' WHERE  GUID ='DD93AD73A6B04062911175AFB2577329';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=50'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=5*01'||CHR(38)||'isSb=1' WHERE  GUID ='11200201';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/del/page.do?projmType=1'||CHR(38)||'dealtype=58'||CHR(38)||'mainDealType=5*01' WHERE  GUID ='11200209';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=50'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=5*01'||CHR(38)||'instead=1'||CHR(38)||'isSb=1' WHERE  GUID ='11200231';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=50'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=5*01'||CHR(38)||'isPL=1'||CHR(38)||'isSb=1' WHERE  GUID ='2295863373578E3CE050A8C021056471';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/bak/bakMain.do?step=projbak'||CHR(38)||'spfmType=1'||CHR(38)||'dealType=53'||CHR(38)||'bakLogDealType=4*53'||CHR(38)||'isReadOnly=1' WHERE  GUID ='120002003';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/check/checkMain.do?dealType=59'||CHR(38)||'sameSpf=0'||CHR(38)||'step=audit'||CHR(38)||'spfmType=1'||CHR(38)||'btShow={isAudit:''y''}' WHERE  GUID ='11200203';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=50'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=5*01' WHERE  GUID ='112002031';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=projview'||CHR(38)||'dealType=50'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=5*01'||CHR(38)||'isReadOnly=1' WHERE  GUID ='11200204';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/audit/auditMain.do?dealType=51'||CHR(38)||'spfmType=1'||CHR(38)||'step=audit'||CHR(38)||'amountDealType=5*41'||CHR(38)||'btShow={isAudit:''y''}' WHERE  GUID ='11200202';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/inBudget/inputMain.do?dealtype=54'||CHR(38)||'projmType=1'||CHR(38)||'mainDealType=5*01'||CHR(38)||'spfmType=1'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}' WHERE  GUID ='11200205';
UPDATE FASP_T_PUBMENU SET URL='/spf/colligate/commonweb/docMain.do?appID=SPF'||CHR(38)||'dealType=D0' WHERE  GUID ='11200235';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/bgtadjust/auditMain.do?dealtype=56'||CHR(38)||'samespf=0'||CHR(38)||'step=projbgtadjust'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}' WHERE  GUID ='2A6528A83F97725BE0530100007F850A';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/adjust/inputMain.do?style=2'||CHR(38)||'projmType=1'||CHR(38)||'dealtype=52'||CHR(38)||'spfchsdealtype=5*11'||CHR(38)||'instead=0'||CHR(38)||'attention=0'||CHR(38)||'appid=SPF'||CHR(38)||'hisDealType=5*27' WHERE  GUID ='120002001';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/adjustaudit/main.do?style=2'||CHR(38)||'projmType=1'||CHR(38)||'dealtype=52'||CHR(38)||'spfchsdealtype=5*11'||CHR(38)||'instead=0'||CHR(38)||'attention=0'||CHR(38)||'appid=SPF'||CHR(38)||'hisDealType=5*27' WHERE  GUID ='120002002';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/inBudget/inputIndexMain.do?dealtype=55'||CHR(38)||'projmType=1'||CHR(38)||'mainDealType=5*01'||CHR(38)||'spfmType=1'||CHR(38)||''||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}' WHERE  GUID ='112002051';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=50'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=5*01'||CHR(38)||'instead=1'||CHR(38)||'isadd=1'||CHR(38)||'isSb=1' WHERE  GUID ='282E476639F720DBE050A8C0210538AE';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=50'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=5*01'||CHR(38)||'isadd=1'||CHR(38)||'isSb=1' WHERE  GUID ='282E476639F720DBE050A8C0210654AE';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/comprePage/page.do?dealtype=ZHCX*01'||CHR(38)||'isdec=1'||CHR(38)||'appId=SPF' WHERE  GUID ='28A64972D270028DE050A8C021050A79';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/choose/chooseMain.do?dealType=XMGWXZB'||CHR(38)||'step=audit'||CHR(38)||'projtype=1'||CHR(38)||'dealType2=51'||CHR(38)||'spfmType=1' WHERE  GUID ='256D064E14684681BABC5BBBBB611640';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/comprePage/page.do?dealtype=ZHCX*01'||CHR(38)||'isdec=0'||CHR(38)||'appId=SPF' WHERE  GUID ='28A64972D272028DE050A8C021050A79';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/dx/comprePage/page.do?dealtype=ZHCX*01'||CHR(38)||'appId=SPF' WHERE  GUID ='29ACF4437D18BCB1E050A8C021053D73';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=50'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=5*01'||CHR(38)||'instead=1'||CHR(38)||'isPL=1'||CHR(38)||'isSb=1' WHERE  GUID ='3302E92C350BBD3AE0530100007F7598';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/backspaceSelect/selectMain.do?projmType=1'||CHR(38)||'dealtype=57'||CHR(38)||'samespf=0'||CHR(38)||'projstep=projBack' WHERE  GUID ='62E4B32357ED42409988EEC74425ED57';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/input/spfInputMain.do?spfmType=2'||CHR(38)||'dealType=30'||CHR(38)||'step=input'||CHR(38)||'mainDealType=3*01'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}' WHERE  GUID ='12000301';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/input/spfInputMain.do?spfmType=2'||CHR(38)||'dealType=30'||CHR(38)||'step=input'||CHR(38)||'mainDealType=3*01'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}' WHERE  GUID ='12000302';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/input/spfInputMain.do?spfmType=2'||CHR(38)||'dealType=30'||CHR(38)||'step=spfview'||CHR(38)||'mainDealType=3*01'||CHR(38)||'isReadOnly=1' WHERE  GUID ='12000303';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/bak/bakMain.do?dealType=36'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=2'||CHR(38)||'bakLogDealType=4*52'||CHR(38)||'isReadOnly=1' WHERE  GUID ='12000307';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/audit/auditMain.do?dealType=31'||CHR(38)||'spfmType=2'||CHR(38)||'btShow={isAudit:''y''}' WHERE  GUID ='12000304';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/adjust/main.do?style=2'||CHR(38)||'dealtype=32'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=2' WHERE  GUID ='12000305';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/adjustaudit/main.do?style=2'||CHR(38)||'dealtype=32'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=2' WHERE  GUID ='12000306';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/backspaceSelect/selectMain.do?spfmType=2'||CHR(38)||'dealtype=3101'||CHR(38)||'step=spfBack' WHERE  GUID ='A8287138518F4157ACE87CC24205A12D';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=2'||CHR(38)||'step=input'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'isSb=1' WHERE  GUID ='12000401';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=2'||CHR(38)||'step=input'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'isPL=1'||CHR(38)||'isSb=1' WHERE  GUID ='2295863373578E3CE050A8C021056472';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=2'||CHR(38)||'step=input'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'instead=1'||CHR(38)||'isSb=1' WHERE  GUID ='12000414';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/del/page.do?projmType=2'||CHR(38)||'dealtype=P8'||CHR(38)||'mainDealType=P*01' WHERE  GUID ='12000413';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=2'||CHR(38)||'step=input'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01' WHERE  GUID ='12000402';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=2'||CHR(38)||'step=projview'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'isReadOnly=1' WHERE  GUID ='12000403';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/check/checkMain.do?dealType=P9'||CHR(38)||'sameSpf=1'||CHR(38)||'step=audit'||CHR(38)||'spfmType=2'||CHR(38)||'btShow={isAudit:''y''}' WHERE  GUID ='12000409';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/bak/bakMain.do?step=projbak'||CHR(38)||'spfmType=2'||CHR(38)||'dealType=53'||CHR(38)||'bakLogDealType=4*53'||CHR(38)||'isReadOnly=1' WHERE  GUID ='22957CD1D93491A2E050A8C0210562F7';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/audit/auditMain.do?dealType=P1'||CHR(38)||'spfmType=2'||CHR(38)||'step=audit'||CHR(38)||'amountDealType=5*41'||CHR(38)||'btShow={isAudit:''y''}' WHERE  GUID ='12000408';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/adjust/inputMain.do?style=2'||CHR(38)||'projmType=2'||CHR(38)||'dealtype=P2'||CHR(38)||'spfchsdealtype=5*11'||CHR(38)||'instead=0'||CHR(38)||'attention=0'||CHR(38)||'appid=SPF'||CHR(38)||'isreadonly=1' WHERE  GUID ='12000406';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/adjustaudit/main.do?style=2'||CHR(38)||'projmType=2'||CHR(38)||'dealtype=P2'||CHR(38)||'spfchsdealtype=5*11'||CHR(38)||'instead=0'||CHR(38)||'attention=0'||CHR(38)||'appid=SPF'||CHR(38)||'isreadonly=1' WHERE  GUID ='12000407';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/inBudget/inputMain.do?dealtype=P4'||CHR(38)||'projmType=1'||CHR(38)||'mainDealType=P*01'||CHR(38)||'spfmType=2'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}' WHERE  GUID ='12000404';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/inBudget/inputIndexMain.do?dealtype=P5'||CHR(38)||'projmType=1'||CHR(38)||'mainDealType=P*01'||CHR(38)||'spfmType=2'||CHR(38)||''||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}' WHERE  GUID ='12000405';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'instead=1'||CHR(38)||'isadd=1'||CHR(38)||'isSb=1' WHERE  GUID ='282E476639F820DBE050A8C0210538AE';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/bgtadjust/auditMain.do?dealtype=P6'||CHR(38)||'samespf=0'||CHR(38)||'step=projbgtadjust'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}' WHERE  GUID ='29481D6E981A028AE0530100007F8351';
UPDATE FASP_T_PUBMENU SET URL='/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'instead=1'||CHR(38)||'isPL=1'||CHR(38)||'isSb=1' WHERE  GUID ='3302E92C350CBD3AE0530100007F7598';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/backspaceSelect/selectMain.do?projmType=2'||CHR(38)||'dealtype=P7'||CHR(38)||'samespf=0'||CHR(38)||'projstep=projBack' WHERE  GUID ='256D064E14684681BABC5BBB1A611640';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/proj/choose/chooseMain.do?dealType=XMGWXZB'||CHR(38)||'step=audit'||CHR(38)||'projtype=0'||CHR(38)||'dealType2=P1'||CHR(38)||'spfmType=2' WHERE  GUID ='256D064E14684681BABC5BBBBBBBBBBB';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/contract/main.do?dealtype=5*18'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=contractSelect'||CHR(38)||'projmType=1'||CHR(38)||'processId=CONTRACTBPMN'||CHR(38)||'conDealtype=5*17' WHERE  GUID ='11200501';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/contract/main.do?dealtype=5*18'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=contractInput'||CHR(38)||'projmType=1'||CHR(38)||'processId=CONTRACTBPMN'||CHR(38)||'conDealtype=5*17' WHERE  GUID ='11200502';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/contract/main.do?dealtype=5*18'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=contractInput'||CHR(38)||'projmType=1'||CHR(38)||'processId=CONTRACTBPMN'||CHR(38)||'conDealtype=5*17'||CHR(38)||'readonly=1' WHERE  GUID ='11200503';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/contract/main.do?dealtype=5*18'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=contractAudit'||CHR(38)||'projmType=1'||CHR(38)||'processId=CONTRACTBPMN' WHERE  GUID ='11200504';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/contract/main.do?dealtype=5*18'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=conAdjustInput'||CHR(38)||'projmType=1'||CHR(38)||'processId=CONTRACTADJUSTBPMN'||CHR(38)||'bpmnIssued=0' WHERE  GUID ='11200506';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/contract/main.do?dealtype=5*18'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=conAdjustSelect'||CHR(38)||'projmType=1'||CHR(38)||'processId=CONTRACTADJUSTBPMN'||CHR(38)||'hisDealType=5*27'||CHR(38)||'bpmnIssued=0' WHERE  GUID ='11200507';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/contract/main.do?dealtype=5*18'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=conAdjustAudit'||CHR(38)||'projmType=1'||CHR(38)||'processId=CONTRACTADJUSTBPMN'||CHR(38)||'hisDealType=5*27'||CHR(38)||'bpmnIssued=0' WHERE  GUID ='11200505';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/staged/main.do?dealtype=5*19'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=collectInput'||CHR(38)||'projmType=1'||CHR(38)||'processId=collect' WHERE  GUID ='11200601';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/staged/main.do?dealtype=5*19'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=collectInput'||CHR(38)||'projmType=1'||CHR(38)||'processId=collect'||CHR(38)||'isReadOnly=1' WHERE  GUID ='11200604';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/staged/main.do?dealtype=5*19'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=collectSelect'||CHR(38)||'projmType=1'||CHR(38)||'processId=collect' WHERE  GUID ='11200603';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/staged/main.do?dealtype=5*19'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=collectAudit'||CHR(38)||'projmType=1'||CHR(38)||'processId=collect' WHERE  GUID ='11200602';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/staged/main.do?dealtype=5*19'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=acceptInput'||CHR(38)||'projmType=1'||CHR(38)||'processId=ACCEPTANCEBPMN' WHERE  GUID ='11200401';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/staged/main.do?dealtype=5*19'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=acceptInput'||CHR(38)||'projmType=1'||CHR(38)||'processId=ACCEPTANCEBPMN'||CHR(38)||'isReadOnly=1' WHERE  GUID ='11200404';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/staged/main.do?dealtype=5*19'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=acceptSelect'||CHR(38)||'projmType=1'||CHR(38)||'processId=ACCEPTANCEBPMN' WHERE  GUID ='11200403';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/project/staged/main.do?dealtype=5*19'||CHR(38)||'kpiType=1'||CHR(38)||'spfStage=acceptAudit'||CHR(38)||'projmType=1'||CHR(38)||'processId=ACCEPTANCEBPMN' WHERE  GUID ='11200402';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/secu/userToType.do?usertype=2'||CHR(38)||'busiTypeID=SPF' WHERE  GUID ='12001005';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/secu/userToType.do?usertype=1'||CHR(38)||'busiTypeID=SPF' WHERE  GUID ='12001006';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/secu/roletotype.do' WHERE  GUID ='12001008';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/secu/userToSPF.do?usertype=2'||CHR(38)||'busiTypeID=SPF' WHERE  GUID ='12001004';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/secu/userToSPF.do?usertype=1'||CHR(38)||'busiTypeID=SPF' WHERE  GUID ='12001003';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/secu/roletospf.do' WHERE  GUID ='12001007';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/setting/fundTpToSour.do' WHERE  GUID ='D1F3E5E3812E4CE6A36A9AECA075FEC0';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/setting/projecttype/projectTypePage.do?dealtype=4*30'||CHR(38)||'spftype=F' WHERE  GUID ='1144';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/setting/projCode.do?type=1' WHERE  GUID ='12001200';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/setting/projCode.do?type=0' WHERE  GUID ='12001201';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/setting/attach.do' WHERE  GUID ='12001202';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/setting/specAttach.do' WHERE  GUID ='12001203';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/setting/projreport.do?psuit=F6A4E2F5DF1D96F7E040A8C0200352B4'||CHR(38)||'fsuit=F6A4E2F5DF0996F7E040A8C0200352B4' WHERE  GUID ='12001204';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/secu/coltotab.do?tId=4*01'||CHR(38)||'suitId=F6A4E2F5DF0996F7E040A8C0200352B4' WHERE  GUID ='12001009';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/secu/coltotab.do?tId=5*01'||CHR(38)||'suitId=F6A4E2F5DF1D96F7E040A8C0200352B4' WHERE  GUID ='12001010';
UPDATE FASP_T_PUBMENU SET URL='/spf/spf/setting/listCondiSet.do' WHERE  GUID ='12001011';
UPDATE FASP_T_PUBMENU SET URL='/spf/exp/setting/businessAudit/businessAuditPage.do?appId=SPF' WHERE  GUID ='15761DCAC036390DE050A8C0210AAED3';
UPDATE FASP_T_PUBMENU SET URL='/spf/commons/setting/auditrule/auditRulePage.do?appId=SPF' WHERE  GUID ='004009011';
UPDATE FASP_T_PUBMENU SET URL='/spf/commons/setting/input/mainSubRela.do?appId=SPF' WHERE  GUID ='11202104';
UPDATE FASP_T_PUBMENU SET URL='/spf/commons/secu/roleToAgency.do' WHERE  GUID ='121110';
UPDATE FASP_T_PUBMENU SET URL='/spf/commons/secu/roleToAgency.do?type=2' WHERE  GUID ='15761DCAC036390DE050A8C0210AAEDD';
UPDATE FASP_T_PUBMENU SET URL='/spf/commons/setting/manage/main.do?appId=spf' WHERE  GUID ='11201407';
UPDATE FASP_T_PUBMENU SET URL='/spf/commons/setting/exception/page.do?option=manage' WHERE  GUID ='256EA8EC1F8E1BBBE050A8C0210507BF';
UPDATE FASP_T_PUBMENU SET URL='/spf/commons/setting/exception/page.do?appId=SPF'||CHR(38)||'option=view' WHERE  GUID ='256EA8EC1F8E1BBBE050A8C0210507RE';
UPDATE FASP_T_PUBMENU SET URL='/spf/commons/setting/input.do?appId=SPF' WHERE  GUID ='004010008';
UPDATE FASP_T_PUBMENU SET URL='/spf/commons/setting/busimanage/busimanage.do' WHERE  GUID ='31E8B9EF5BF812B7E050A8C0210550CC';
UPDATE FASP_T_PUBMENU SET URL='/spf/commons/setting/busimanage/busiView.do' WHERE  GUID ='31E8B9EF5BF812B7E050A8C0210550DD';
UPDATE FASP_T_PUBMENU SET URL='/spf/commons/setting/regist/regist.do?appid=spf' WHERE  GUID ='269691062A7860C5E050A8C021052698';
UPDATE FASP_T_PUBMENU SET URL='/spf/commons/setting/billdef/mpage.do?appId=SPF'||CHR(38)||'all=1' WHERE  GUID ='12001216';
UPDATE FASP_T_PUBMENU SET URL='/spf/commons/setting/billdeftype/mpage.do?appId=SPF' WHERE  GUID ='12001217';
DELETE FROM fasp_T_pubmenu WHERE GUID ='241D34420C04497A9459DA0A1B26D1A1';
insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK,  APPSYSID, APPID)
values ('241D34420C04497A9459DA0A1B26D1A1', 2, 0, '1', '004011', '阶段管理', '100002', null, 11, NULL, 1, 'spf');
DELETE FROM fasp_t_pubmenu WHERE GUID ='282E476639F720DBE050A8C0216515A3';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('282E476639F720DBE050A8C0216515A3', 2, 1, '1', '004004023', '追加二级项目申报', '120004', '/spf/project/input/projInputMain.do?spfmType=1'||CHR(38)||'step=input'||CHR(38)||'dealType=P0'||CHR(38)||'btShow={isAdd:''y'',isAudit:''y''}'||CHR(38)||'mainDealType=P*01'||CHR(38)||'isadd=1'||CHR(38)||'isSb=1', 2, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1, null);
END;
--新版项目库菜单sql