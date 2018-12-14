begin

--项目绩效目标
delete from fasp_t_pubmenu WHERE guid='2200101';
delete from fasp_t_pubmenu WHERE guid='220010101';
delete from fasp_t_pubmenu WHERE guid='220010102';
delete from fasp_t_pubmenu WHERE guid='220010103';
delete from fasp_t_pubmenu WHERE guid='220010104';
delete from KPI_T_SETINPUTSTEP WHERE guid='B8DC679B98674ADAA18BD2E2C4B0B1FA';                                                                                           
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200101',2,0,1,'006001008','项目绩效目标管理','22001','',3,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220010101',3,1,1,'006001008001','项目绩效目标采集','2200101','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'projmType=1'||chr(38)||'processId=target',4,'remark',1,'kpi',1);                                                                                            
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220010102',3,1,1,'006001008002','项目绩效目标填报','2200101','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'projmType=1'||chr(38)||'processId=target',5,'remark',1,'kpi',1);                                                                                                                                                           
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220010103',3,1,1,'006001008003','项目绩效目标查看','2200101',' /spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'projmType=1'||chr(38)||'processId=target',6,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220010104',3,1,1,'006001008004','项目绩效目标确认','2200101',' /spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'projmType=1'||chr(38)||'processId=target',7,'remark',1,'kpi',1);
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(12,'B8DC679B98674ADAA18BD2E2C4B0B1FA','target','项目绩效目标',1);
delete from KPI_T_PROCESSMENU WHERE PROCESSID='B8DC679B98674ADAA18BD2E2C4B0B1FA';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200101'  ,'22001', 0,'B8DC679B98674ADAA18BD2E2C4B0B1FA' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010101' ,'2200101',1,'B8DC679B98674ADAA18BD2E2C4B0B1FA' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010102' ,'2200101',2,'B8DC679B98674ADAA18BD2E2C4B0B1FA' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010103' ,'2200101',3,'B8DC679B98674ADAA18BD2E2C4B0B1FA' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010104' ,'2200101',4,'B8DC679B98674ADAA18BD2E2C4B0B1FA' );




--项目绩效信息
update fasp_t_pubmenu set name='项目绩效信息管理' WHERE guid='2200103';
update fasp_t_pubmenu set name='项目绩效信息采集' WHERE guid='220010301';
update fasp_t_pubmenu set name='项目绩效信息填报' WHERE guid='220010302';
update fasp_t_pubmenu set name='项目绩效信息查看' WHERE guid='220010303';
update fasp_t_pubmenu set name='项目绩效信息管理' WHERE guid='220010304';
update KPI_T_SETINPUTSTEP set name='项目绩效信息'  WHERE guid ='BAF2CEA6DC6A48F7995914A0B8D1FDB5';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='BAF2CEA6DC6A48F7995914A0B8D1FDB5';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200103'  ,'22001', 0,'BAF2CEA6DC6A48F7995914A0B8D1FDB5' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010301' ,'2200103',1,'BAF2CEA6DC6A48F7995914A0B8D1FDB5' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010302' ,'2200103',2,'BAF2CEA6DC6A48F7995914A0B8D1FDB5' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010303' ,'2200103',3,'BAF2CEA6DC6A48F7995914A0B8D1FDB5' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010304' ,'2200103',4,'BAF2CEA6DC6A48F7995914A0B8D1FDB5' );




--项目绩效整改
delete from fasp_t_pubmenu WHERE guid='2200109';
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200109',2,0,1,'006001007','项目绩效整改管理','22001','',9,'remark',1,'kpi',1);
--update fasp_t_pubmenu set name='项目绩效整改管理' WHERE guid='2200109';                                   
update fasp_t_pubmenu set name='项目绩效整改采集' , guid='220010901' , code='006001007001' , parentid='2200109' WHERE  url='/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'projmType=1'||chr(38)||'processId=update';
update fasp_t_pubmenu set name='项目绩效整改填报' , guid='220010902' , code='006001007002', parentid='2200109' WHERE  url='/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'projmType=1'||chr(38)||'processId=update';
update fasp_t_pubmenu set name='项目绩效整改查看' , guid='220010903' , code='006001007003' , parentid='2200109' WHERE  url='/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'projmType=1'||chr(38)||'processId=update';
update fasp_t_pubmenu set name='项目绩效整改确认' , guid='220010904' , code='006001007004', parentid='2200109' WHERE  url='/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'projmType=1'||chr(38)||'processId=update';
update KPI_T_SETINPUTSTEP set name='项目绩效整改'  WHERE guid ='3DC1C701E4274EC98A5A2934565B728E';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='3DC1C701E4274EC98A5A2934565B728E';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200107'  ,'22001', 0,'3DC1C701E4274EC98A5A2934565B728E' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010701' ,'2200107',1,'3DC1C701E4274EC98A5A2934565B728E' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010702' ,'2200107',2,'3DC1C701E4274EC98A5A2934565B728E' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010703' ,'2200107',3,'3DC1C701E4274EC98A5A2934565B728E' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010704' ,'2200107',4,'3DC1C701E4274EC98A5A2934565B728E' );





--项目绩效自评
delete from fasp_t_pubmenu WHERE guid='220010401';
delete from fasp_t_pubmenu WHERE guid='220010402';
delete from fasp_t_pubmenu WHERE guid='220010403';
delete from fasp_t_pubmenu WHERE guid='220010404'; 
update fasp_t_pubmenu set name='项目绩效自评管理'  WHERE guid='2200104';                                                                                     
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220010401',3,1,1,'006001002001','项目绩效自评采集','2200104','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'projmType=1'||chr(38)||'processId=self',4,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220010402',3,1,1,'006001002002','项目绩效自评填报','2200104','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'projmType=1'||chr(38)||'processId=self',5,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220010403',3,1,1,'006001002003','项目绩效自评查看','2200104','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'projmType=1'||chr(38)||'processId=self',6,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220010404',3,1,1,'006001002004','项目绩效自评确认','2200104','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'projmType=1'||chr(38)||'processId=self',7,'remark',1,'kpi',1);
update KPI_T_SETINPUTSTEP set name='项目绩效自评'  WHERE guid ='5F120C2810914A99935210FF200E7812';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='5F120C2810914A99935210FF200E7812';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200104'  ,'22001', 0,'5F120C2810914A99935210FF200E7812' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010401' ,'2200104',1,'5F120C2810914A99935210FF200E7812' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010402' ,'2200104',2,'5F120C2810914A99935210FF200E7812' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010403' ,'2200104',3,'5F120C2810914A99935210FF200E7812' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010404' ,'2200104',4,'BAF2CEA6DC6A48F7995914A0B8D1FDB5' );


--项目绩效评价
delete from fasp_t_pubmenu WHERE guid='22001010';
delete from fasp_t_pubmenu WHERE guid='2200101001';
delete from fasp_t_pubmenu WHERE guid='2200101002';
delete from fasp_t_pubmenu WHERE guid='2200101003'; 
delete from fasp_t_pubmenu WHERE guid='2200101004'; 
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('22001010',2,0,1,'0060010011','项目绩效评价管理','22001','',3,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200101001',3,1,1,'0060010011001','项目绩效评价采集','22001010','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'projmType=1'||chr(38)||'processId=eval',4,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200101002',3,1,1,'0060010011002','项目绩效评价填报','22001010','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'projmType=1'||chr(38)||'processId=eval',5,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200101003',3,1,1,'0060010011003','项目绩效评价查看','22001010','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'projmType=1'||chr(38)||'processId=eval',6,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200101004',3,1,1,'0060010011004','项目绩效评价确认','22001010','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'projmType=1'||chr(38)||'processId=eval',7,'remark',1,'kpi',1);
update KPI_T_SETINPUTSTEP set name='项目绩效评价'  WHERE guid ='0323180BCF484CD5808CC36BD9ABD051'; 
delete from KPI_T_PROCESSMENU WHERE PROCESSID='0323180BCF484CD5808CC36BD9ABD051';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '22001010'  ,'22001', 0,'0323180BCF484CD5808CC36BD9ABD051' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('2200101001' ,'22001010',1,'0323180BCF484CD5808CC36BD9ABD051' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('2200101002' ,'22001010',2,'0323180BCF484CD5808CC36BD9ABD051' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('2200101003' ,'22001010',3,'0323180BCF484CD5808CC36BD9ABD051' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('2200101004' ,'22001010',4,'0323180BCF484CD5808CC36BD9ABD051' );


--项目绩效调整
update fasp_t_pubmenu set name='项目绩效调整管理' WHERE guid='2200105';
update fasp_t_pubmenu set name='项目绩效调整采集' WHERE guid='220010501';
update fasp_t_pubmenu set name='项目绩效调整填报' WHERE guid='220010502';
update fasp_t_pubmenu set name='项目绩效调整查看' WHERE guid='220010503';
update fasp_t_pubmenu set name='项目绩效调整管理' WHERE guid='220010504';
update KPI_T_SETINPUTSTEP set name='项目绩效调整'  WHERE guid ='C54E0432DF42439483CED810DF41262F';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='C54E0432DF42439483CED810DF41262F';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200105'  ,'22001', 0,'C54E0432DF42439483CED810DF41262F' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010501' ,'2200105',1,'C54E0432DF42439483CED810DF41262F' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010502' ,'2200105',2,'C54E0432DF42439483CED810DF41262F' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010503' ,'2200105',3,'C54E0432DF42439483CED810DF41262F' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010504' ,'2200105',4,'C54E0432DF42439483CED810DF41262F' );




--项目绩效监督
delete from fasp_t_pubmenu WHERE guid='2200102';
delete from fasp_t_pubmenu WHERE guid='220010201';
delete from fasp_t_pubmenu WHERE guid='220010202';
delete from fasp_t_pubmenu WHERE guid='220010203';
delete from fasp_t_pubmenu WHERE guid='220010204';
delete from KPI_T_SETINPUTSTEP WHERE guid='C0CCDDFB0770467F89A76EF54D4B61E7';
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(12,'C0CCDDFB0770467F89A76EF54D4B61E7','monitor','项目绩效监督',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200102',2,0,1,'006001009','项目绩效监督管理','22001','',3,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220010201',3,1,1,'006001009001','项目绩效监督采集','2200102','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'projmType=1'||chr(38)||'processId=monitor',4,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220010202',3,1,1,'006001009002','项目绩效监督填报','2200102','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'projmType=1'||chr(38)||'processId=monitor',5,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220010203',3,1,1,'006001009003','项目绩效监督查看','2200102','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'projmType=1'||chr(38)||'processId=monitor',6,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220010204',3,1,1,'006001009004','项目绩效监督确认','2200102','/spf/kpi/business/main.do?dealtype=KP50'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'projmType=1'||chr(38)||'processId=monitor',7,'remark',1,'kpi',1);
delete from KPI_T_PROCESSMENU WHERE PROCESSID='C0CCDDFB0770467F89A76EF54D4B61E7';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200102'  ,'22001', 0,'C0CCDDFB0770467F89A76EF54D4B61E7' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010201' ,'2200201',1,'C0CCDDFB0770467F89A76EF54D4B61E7' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010202' ,'2200201',2,'C0CCDDFB0770467F89A76EF54D4B61E7' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010203' ,'2200201',3,'C0CCDDFB0770467F89A76EF54D4B61E7' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010204' ,'2200201',4,'C0CCDDFB0770467F89A76EF54D4B61E7' );



--项目绩效评价结果
update fasp_t_pubmenu set name='项目绩效评价结果管理' WHERE guid='2200106';
update fasp_t_pubmenu set name='项目绩效评价结果采集' WHERE guid='220010601';
update fasp_t_pubmenu set name='项目绩效评价结果填报' WHERE guid='220010602';
update fasp_t_pubmenu set name='项目绩效评价结果查看' WHERE guid='220010603';
update fasp_t_pubmenu set name='项目绩效评价结果管理' WHERE guid='220010604';
delete from KPI_T_SETINPUTSTEP WHERE guid='9FE64BC6980B4CC4998E358009926678';
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(16,'9FE64BC6980B4CC4998E358009926678','result','项目绩效评价结果',1);
delete from KPI_T_PROCESSMENU WHERE PROCESSID='9FE64BC6980B4CC4998E358009926678';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200106'  ,'22001', 0,'9FE64BC6980B4CC4998E358009926678' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010601' ,'2200106',1,'9FE64BC6980B4CC4998E358009926678' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010602' ,'2200106',2,'9FE64BC6980B4CC4998E358009926678' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010603' ,'2200106',3,'9FE64BC6980B4CC4998E358009926678' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220010604' ,'2200106',4,'9FE64BC6980B4CC4998E358009926678' );




--专项绩效目标
delete from fasp_t_pubmenu WHERE guid='2200302';
delete from fasp_t_pubmenu WHERE guid='220030201';
delete from fasp_t_pubmenu WHERE guid='220030202';
delete from fasp_t_pubmenu WHERE guid='220030203';
delete from fasp_t_pubmenu WHERE guid='220030204';
delete from KPI_T_SETINPUTSTEP WHERE guid='B4897BA84FA544D4934A16125AB40C0C';
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(16,'B4897BA84FA544D4934A16125AB40C0C','target','专项绩效目标',2);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200302',2,0,1,'006002007','专项绩效目标管理','22003','',3,'remark',1,'kpi',1);                                                                                                                                                        
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220030201',3,1,1,'006002007001','专项绩效目标采集','2200302','/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'spfmType=1'||chr(38)||'processId=target',4,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220030202',3,1,1,'006002007002','专项绩效目标填报','2200302','/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'spfmType=1'||chr(38)||'processId=target',5,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220030203',3,1,1,'006002007003','专项绩效目标查看','2200302','/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'spfmType=1'||chr(38)||'processId=target',6,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220030204',3,1,1,'006002007004','专项绩效目标确认','2200302','/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'spfmType=1'||chr(38)||'processId=target',7,'remark',1,'kpi',1);
delete from KPI_T_PROCESSMENU WHERE PROCESSID='B4897BA84FA544D4934A16125AB40C0C';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200302'  ,'22003', 0,'B4897BA84FA544D4934A16125AB40C0C' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030201' ,'2200302',1,'B4897BA84FA544D4934A16125AB40C0C' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030202' ,'2200302',2,'B4897BA84FA544D4934A16125AB40C0C' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030203' ,'2200302',3,'B4897BA84FA544D4934A16125AB40C0C' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030204' ,'2200302',4,'B4897BA84FA544D4934A16125AB40C0C' );




--专项绩效信息
update fasp_t_pubmenu set name='专项绩效信息管理' WHERE guid='2200303';                                               
update fasp_t_pubmenu set name='专项绩效信息采集' , guid='220030301' , code='006002001001'  WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'spfmType=1'||chr(38)||'processId=message';
update fasp_t_pubmenu set name='专项绩效信息填报' , guid='220030302' , code='006002001002'  WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'spfmType=1'||chr(38)||'processId=message';
update fasp_t_pubmenu set name='专项绩效信息查看' , guid='220030303' , code='006002001003'  WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'spfmType=1'||chr(38)||'processId=message';
update fasp_t_pubmenu set name='专项绩效信息确认' , guid='220030304' , code='006002001004'  WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'spfmType=1'||chr(38)||'processId=message';
update KPI_T_SETINPUTSTEP set name='专项绩效信息'  WHERE guid ='00A74CAD9712424DA95907E1F919A66E';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='00A74CAD9712424DA95907E1F919A66E';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200303'  ,'22003', 0,'00A74CAD9712424DA95907E1F919A66E' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030301' ,'2200303',1,'00A74CAD9712424DA95907E1F919A66E' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030302' ,'2200303',2,'00A74CAD9712424DA95907E1F919A66E' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030303' ,'2200303',3,'00A74CAD9712424DA95907E1F919A66E' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030304' ,'2200303',4,'00A74CAD9712424DA95907E1F919A66E' );



--项目绩效整改
delete from fasp_t_pubmenu WHERE guid='2200301';
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200301',2,0,1,'006002006','专项绩效整改管理','22003','',9,'remark',1,'kpi',1);
--update fasp_t_pubmenu set name='专项绩效整改管理' WHERE guid='2200109';                                                                                
update fasp_t_pubmenu set name='专项绩效整改采集' , guid='220030101' , code='006002006001' , parentid='2200301' WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'spfmType=1'||chr(38)||'processId=update';
update fasp_t_pubmenu set name='专项绩效整改填报' , guid='220030102' , code='006002006002' , parentid='2200301' WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'spfmType=1'||chr(38)||'processId=update';
update fasp_t_pubmenu set name='专项绩效整改查看' , guid='220030103' , code='006002006003' , parentid='2200301' WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'spfmType=1'||chr(38)||'processId=update';
update fasp_t_pubmenu set name='专项绩效整改确认' , guid='220030104' , code='006002006004' , parentid='2200301' WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'spfmType=1'||chr(38)||'processId=update';
update KPI_T_SETINPUTSTEP set name='专项绩效整改'  WHERE guid ='06B133DF372844769F8C612F78CF0ED3';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='06B133DF372844769F8C612F78CF0ED3';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200301'  ,'22003', 0,'06B133DF372844769F8C612F78CF0ED3' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030101' ,'2200301',1,'06B133DF372844769F8C612F78CF0ED3' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030102' ,'2200301',2,'06B133DF372844769F8C612F78CF0ED3' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030103' ,'2200301',3,'06B133DF372844769F8C612F78CF0ED3' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030104' ,'2200301',4,'06B133DF372844769F8C612F78CF0ED3' );




--专项绩效自评
delete from fasp_t_pubmenu  where guid='220030403';
delete from fasp_t_pubmenu  where guid='220030402';
update fasp_t_pubmenu set name='专项绩效自评管理' WHERE guid='2200304';                                                           
update fasp_t_pubmenu set name='专项绩效自评采集' , guid='220030401' , code='006002002001' , parentid='2200304' WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'spfmType=1'||chr(38)||'processId=self';
update fasp_t_pubmenu set name='专项绩效自评填报' , guid='220030405' , code='006002002002' , parentid='2200304' WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'spfmType=1'||chr(38)||'processId=self';
update fasp_t_pubmenu set name='专项绩效自评查看' , guid='220030406' , code='006002002003' , parentid='2200304' WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'spfmType=1'||chr(38)||'processId=self';
update fasp_t_pubmenu set name='专项绩效自评确认' , guid='220030404' , code='006002002004' , parentid='2200304' WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'spfmType=1'||chr(38)||'processId=self';
update KPI_T_SETINPUTSTEP set name='专项绩效自评'  WHERE guid ='F198182AC8D7458393F6AF1D0849FC46';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='F198182AC8D7458393F6AF1D0849FC46';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200304'  ,'22003', 0,'F198182AC8D7458393F6AF1D0849FC46' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030401' ,'2200304',1,'F198182AC8D7458393F6AF1D0849FC46' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030405' ,'2200304',2,'F198182AC8D7458393F6AF1D0849FC46' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030406' ,'2200304',3,'F198182AC8D7458393F6AF1D0849FC46' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030404' ,'2200304',4,'F198182AC8D7458393F6AF1D0849FC46' );



--专项绩效评价
delete from fasp_t_pubmenu WHERE guid='2200308';
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200308',2,0,1,'006002007','专项绩效评价管理','22003','',9,'remark',1,'kpi',1);                                                         
update fasp_t_pubmenu set name='专项绩效评价采集' , guid='220030801' , code='006002007001' , parentid='2200308' WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'spfmType=1'||chr(38)||'processId=eval';
update fasp_t_pubmenu set name='专项绩效评价填报' , guid='220030802' , code='006002007002' , parentid='2200308' WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'spfmType=1'||chr(38)||'processId=eval';
update fasp_t_pubmenu set name='专项绩效评价查看' , guid='220030803' , code='006002007003' , parentid='2200308' WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'spfmType=1'||chr(38)||'processId=eval';
update fasp_t_pubmenu set name='专项绩效评价确认' , guid='220030804' , code='006002007004' , parentid='2200308' WHERE  url='/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'spfmType=1'||chr(38)||'processId=eval';
update KPI_T_SETINPUTSTEP set name='专项绩效评价'  WHERE guid ='0323180BCF484CD5808CC36BD9ABD333';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='0323180BCF484CD5808CC36BD9ABD333';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200308'  ,'22003', 0,'0323180BCF484CD5808CC36BD9ABD333' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030801' ,'2200308',1,'0323180BCF484CD5808CC36BD9ABD333' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030802' ,'2200308',2,'0323180BCF484CD5808CC36BD9ABD333' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030803' ,'2200308',3,'0323180BCF484CD5808CC36BD9ABD333' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030804' ,'2200308',4,'0323180BCF484CD5808CC36BD9ABD333' );




--专项绩效调整
update fasp_t_pubmenu set name='专项绩效调整管理' WHERE guid='2200305';
update fasp_t_pubmenu set name='专项绩效调整采集' WHERE guid='220030501';
update fasp_t_pubmenu set name='专项绩效调整填报' WHERE guid='220030502';
update fasp_t_pubmenu set name='专项绩效调整查看' WHERE guid='220030503';
update fasp_t_pubmenu set name='专项绩效调整管理' WHERE guid='220030504';
update KPI_T_SETINPUTSTEP set name='专项绩效调整'  WHERE guid ='199BF7B29CAA43BF898BBF93E8D1C1A6';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='199BF7B29CAA43BF898BBF93E8D1C1A6';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200305'  ,'22003', 0,'199BF7B29CAA43BF898BBF93E8D1C1A6' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030501' ,'2200305',1,'199BF7B29CAA43BF898BBF93E8D1C1A6' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030502' ,'2200305',2,'199BF7B29CAA43BF898BBF93E8D1C1A6' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030503' ,'2200305',3,'199BF7B29CAA43BF898BBF93E8D1C1A6' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030504' ,'2200305',4,'199BF7B29CAA43BF898BBF93E8D1C1A6' );



--专项绩效监督
delete from fasp_t_pubmenu WHERE guid='2200309';
delete from fasp_t_pubmenu WHERE guid='220030901';
delete from fasp_t_pubmenu WHERE guid='220030902';
delete from fasp_t_pubmenu WHERE guid='220030903';
delete from fasp_t_pubmenu WHERE guid='220030904';
delete from KPI_T_SETINPUTSTEP WHERE guid='1643464E32014525A8F1921F8A343C47'; 
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(16,'1643464E32014525A8F1921F8A343C47','monitor','专项绩效监督',2);                                                                                                                          
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200309',2,0,1,'006002008','专项绩效监督管理','22003','',3,'remark',1,'kpi',1);                                                                                                                                                            
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220030901',3,1,1,'006002008001','专项绩效监督采集','2200309','/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'spfmType=1'||chr(38)||'processId=monitor',4,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220030902',3,1,1,'006002008002','专项绩效监督填报','2200309','/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'spfmType=1'||chr(38)||'processId=monitor',5,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220030903',3,1,1,'006002008003','专项绩效监督查看','2200309','/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'spfmType=1'||chr(38)||'processId=monitor',6,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220030904',3,1,1,'006002008004','专项绩效监督确认','2200309','/spf/kpi/business/main.do?dealtype=KF40'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'spfmType=1'||chr(38)||'processId=monitor',7,'remark',1,'kpi',1);
delete from KPI_T_PROCESSMENU WHERE PROCESSID='1643464E32014525A8F1921F8A343C47';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200309'  ,'22003', 0,'1643464E32014525A8F1921F8A343C47' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030901' ,'2200309',1,'1643464E32014525A8F1921F8A343C47' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030902' ,'2200309',2,'1643464E32014525A8F1921F8A343C47' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030903' ,'2200309',3,'1643464E32014525A8F1921F8A343C47' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030904' ,'2200309',4,'1643464E32014525A8F1921F8A343C47' );




--专项绩效评价结果
update fasp_t_pubmenu set name='专项绩效评价结果管理' WHERE guid='2200306';
update fasp_t_pubmenu set name='专项绩效评价结果采集' WHERE guid='220030601';
update fasp_t_pubmenu set name='专项绩效评价结果填报' WHERE guid='220030602';
update fasp_t_pubmenu set name='专项绩效评价结果查看' WHERE guid='220030603';
update fasp_t_pubmenu set name='专项绩效评价结果管理' WHERE guid='220030604';
delete from KPI_T_SETINPUTSTEP WHERE guid='2F6573688C5C45A4B4EA8CC16B059EA4';
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(11,'2F6573688C5C45A4B4EA8CC16B059EA4','result','专项绩效评价结果',2);
delete from KPI_T_PROCESSMENU WHERE PROCESSID='2F6573688C5C45A4B4EA8CC16B059EA4';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200306'  ,'22003', 0,'2F6573688C5C45A4B4EA8CC16B059EA4' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030601' ,'2200306',1,'2F6573688C5C45A4B4EA8CC16B059EA4' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030602' ,'2200306',2,'2F6573688C5C45A4B4EA8CC16B059EA4' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030603' ,'2200306',3,'2F6573688C5C45A4B4EA8CC16B059EA4' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220030604' ,'2200306',4,'2F6573688C5C45A4B4EA8CC16B059EA4' );



--部门绩效目标
update fasp_t_pubmenu set name='部门绩效目标管理' WHERE guid='2200401';                                               
update fasp_t_pubmenu set name='部门绩效目标采集' WHERE  guid='220040101';
update fasp_t_pubmenu set name='部门绩效目标填报' WHERE  guid='220040102';
update fasp_t_pubmenu set name='部门绩效目标查看' WHERE  guid='220040103';
update fasp_t_pubmenu set name='部门绩效目标确认' WHERE  guid='220040104';
update KPI_T_SETINPUTSTEP set name='部门绩效目标'  WHERE guid ='BAF8104524CD4682A13EC1F4C757D654';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='BAF8104524CD4682A13EC1F4C757D654';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200401'  ,'22004', 0,'BAF8104524CD4682A13EC1F4C757D654' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040101' ,'2200401',1,'BAF8104524CD4682A13EC1F4C757D654' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040102' ,'2200401',2,'BAF8104524CD4682A13EC1F4C757D654' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040103' ,'2200401',3,'BAF8104524CD4682A13EC1F4C757D654' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040104' ,'2200401',4,'BAF8104524CD4682A13EC1F4C757D654' );




--部门绩效信息
update fasp_t_pubmenu set name='部门绩效信息管理' WHERE guid='2200403';                                               
update fasp_t_pubmenu set name='部门绩效信息采集' WHERE  guid='220040301';
update fasp_t_pubmenu set name='部门绩效信息填报' WHERE  guid='220040302';
update fasp_t_pubmenu set name='部门绩效信息查看' WHERE  guid='220040303';
update fasp_t_pubmenu set name='部门绩效信息确认' WHERE  guid='220040304';
update KPI_T_SETINPUTSTEP set name='部门绩效信息'  WHERE guid ='F3A01A3B9569456D9B5EB2525AE9A47D';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='F3A01A3B9569456D9B5EB2525AE9A47D';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200403'  ,'22004', 0,'F3A01A3B9569456D9B5EB2525AE9A47D' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040301' ,'2200403',1,'F3A01A3B9569456D9B5EB2525AE9A47D' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040302' ,'2200403',2,'F3A01A3B9569456D9B5EB2525AE9A47D' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040303' ,'2200403',3,'F3A01A3B9569456D9B5EB2525AE9A47D' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040304' ,'2200403',4,'F3A01A3B9569456D9B5EB2525AE9A47D' );



--部门绩效整改
delete from fasp_t_pubmenu WHERE guid='2200408';
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200408',2,0,1,'006003008','部门绩效整改管理','22004','',9,'remark',1,'kpi',1);
--update fasp_t_pubmenu set name='部门绩效整改管理' WHERE guid='2200109';                                                                                
update fasp_t_pubmenu set name='部门绩效整改采集' , guid='220040801' , code='006003008001' , parentid='2200408' WHERE  url='/spf/kpi/business/main.do?dealtype=60'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'processId=update';
update fasp_t_pubmenu set name='部门绩效整改填报' , guid='220040802' , code='006003008002' , parentid='2200408' WHERE  url='/spf/kpi/business/main.do?dealtype=60'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'processId=update';
update fasp_t_pubmenu set name='部门绩效整改查看' , guid='220040803' , code='006003008003' , parentid='2200408' WHERE  url='/spf/kpi/business/main.do?dealtype=60'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'processId=update';
update fasp_t_pubmenu set name='部门绩效整改确认' , guid='220040804' , code='006003008004' , parentid='2200408' WHERE  url='/spf/kpi/business/main.do?dealtype=60'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'processId=update';
update KPI_T_SETINPUTSTEP set name='部门绩效整改'  WHERE guid ='4982C8A7F6AA44A38B1BBF4F89A799E3';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='4982C8A7F6AA44A38B1BBF4F89A799E3';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200408'  ,'22004', 0,'4982C8A7F6AA44A38B1BBF4F89A799E3' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040801' ,'2200408',1,'4982C8A7F6AA44A38B1BBF4F89A799E3' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040802' ,'2200408',2,'4982C8A7F6AA44A38B1BBF4F89A799E3' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040803' ,'2200408',3,'4982C8A7F6AA44A38B1BBF4F89A799E3' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040804' ,'2200408',4,'4982C8A7F6AA44A38B1BBF4F89A799E3' );




--部门绩效自评
update fasp_t_pubmenu set name='部门绩效自评管理' WHERE guid='2200404';                                               
update fasp_t_pubmenu set name='部门绩效自评采集' WHERE  guid='220040401';
update fasp_t_pubmenu set name='部门绩效自评填报' WHERE  guid='220040402';
update fasp_t_pubmenu set name='部门绩效自评查看' WHERE  guid='220040403';
update fasp_t_pubmenu set name='部门绩效自评确认' WHERE  guid='220040404';
update KPI_T_SETINPUTSTEP set name='部门绩效自评'  WHERE guid ='90938683A81E4A0A91390FB7E754249F';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='90938683A81E4A0A91390FB7E754249F';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200404'  ,'22004', 0,'90938683A81E4A0A91390FB7E754249F' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040401' ,'2200404',1,'90938683A81E4A0A91390FB7E754249F' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040402' ,'2200404',2,'90938683A81E4A0A91390FB7E754249F' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040403' ,'2200404',3,'90938683A81E4A0A91390FB7E754249F' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040404' ,'2200404',4,'90938683A81E4A0A91390FB7E754249F' );




--部门绩效评价
delete from fasp_t_pubmenu WHERE guid='2200409';
delete from fasp_t_pubmenu WHERE guid='220040901';
delete from fasp_t_pubmenu WHERE guid='220040902';
delete from fasp_t_pubmenu WHERE guid='220040903';
delete from fasp_t_pubmenu WHERE guid='220040904';
delete from KPI_T_SETINPUTSTEP WHERE guid='4C411C8668F24B9E9DDAAE7F672AA44C';
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200409',2,0,1,'006003009','部门绩效评价管理','22004','',3,'remark',1,'kpi',1);                                                                                                                                                          
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220040901',3,1,1,'006003009001','部门绩效评价采集','2200409','/spf/kpi/business/main.do?dealtype=60'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'processId=eval',4,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220040902',3,1,1,'006003009002','部门绩效评价填报','2200409','/spf/kpi/business/main.do?dealtype=60'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'processId=eval',5,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220040903',3,1,1,'006003009003','部门绩效评价查看','2200409','/spf/kpi/business/main.do?dealtype=60'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'processId=eval',6,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220040904',3,1,1,'006003009004','部门绩效评价确认','2200409','/spf/kpi/business/main.do?dealtype=60'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'processId=eval',7,'remark',1,'kpi',1);
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(11,'4C411C8668F24B9E9DDAAE7F672AA44C','eval','部门绩效评价',3);
delete from KPI_T_PROCESSMENU WHERE PROCESSID='4C411C8668F24B9E9DDAAE7F672AA44C';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200409'  ,'22004', 0,'4C411C8668F24B9E9DDAAE7F672AA44C' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040901' ,'2200409',1,'4C411C8668F24B9E9DDAAE7F672AA44C' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040902' ,'2200409',2,'4C411C8668F24B9E9DDAAE7F672AA44C' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040903' ,'2200409',3,'4C411C8668F24B9E9DDAAE7F672AA44C' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040904' ,'2200409',4,'4C411C8668F24B9E9DDAAE7F672AA44C' );




--部门绩效调整
update fasp_t_pubmenu set name='部门绩效调整管理' WHERE guid='2200405';                                               
update fasp_t_pubmenu set name='部门绩效调整采集' WHERE  guid='220040501';
update fasp_t_pubmenu set name='部门绩效调整填报' WHERE  guid='220040502';
update fasp_t_pubmenu set name='部门绩效调整查看' WHERE  guid='220040503';
update fasp_t_pubmenu set name='部门绩效调整确认' WHERE  guid='220040504';
update KPI_T_SETINPUTSTEP set name='部门绩效调整'  WHERE guid ='934689B70E3B49E287A8688E5B8EA607';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='934689B70E3B49E287A8688E5B8EA607';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200405'  ,'22004', 0,'934689B70E3B49E287A8688E5B8EA607' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040501' ,'2200405',1,'934689B70E3B49E287A8688E5B8EA607' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040502' ,'2200405',2,'934689B70E3B49E287A8688E5B8EA607' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040503' ,'2200405',3,'934689B70E3B49E287A8688E5B8EA607' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040504' ,'2200405',4,'934689B70E3B49E287A8688E5B8EA607' );


--部门绩效监督
delete from fasp_t_pubmenu WHERE guid='2200402';
delete from fasp_t_pubmenu WHERE guid='220040201';
delete from fasp_t_pubmenu WHERE guid='220040202';
delete from fasp_t_pubmenu WHERE guid='220040203';
delete from fasp_t_pubmenu WHERE guid='220040204';
delete from KPI_T_SETINPUTSTEP WHERE guid='F10CA876EF2A43D5864319596AAFD464';
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(16,'F10CA876EF2A43D5864319596AAFD464','monitor','部门绩效监督',3);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200402',2,0,1,'006003003','部门绩效监督管理','22004','',3,'remark',1,'kpi',1);                                                                                                                                                                                                                                                           
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220040201',3,1,1,'006003003001','部门绩效监督采集','2200402','/spf/kpi/business/main.do?dealtype=60'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'processId=monitor',4,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220040202',3,1,1,'006003003002','部门绩效监督填报','2200402','/spf/kpi/business/main.do?dealtype=60'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'processId=monitor',5,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220040203',3,1,1,'006003003003','部门绩效监督查看','2200402','/spf/kpi/business/main.do?dealtype=60'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'processId=monitor',6,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220040204',3,1,1,'006003003004','部门绩效监督确认','2200402','/spf/kpi/business/main.do?dealtype=60'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'processId=monitor',7,'remark',1,'kpi',1);
delete from KPI_T_PROCESSMENU WHERE PROCESSID='F10CA876EF2A43D5864319596AAFD464';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200402'  ,'22004', 0,'F10CA876EF2A43D5864319596AAFD464' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040201' ,'2200402',1,'F10CA876EF2A43D5864319596AAFD464' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040202' ,'2200402',2,'F10CA876EF2A43D5864319596AAFD464' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040203' ,'2200402',3,'F10CA876EF2A43D5864319596AAFD464' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040204' ,'2200402',4,'F10CA876EF2A43D5864319596AAFD464' );



--部门绩效评价结果
update fasp_t_pubmenu set name='部门绩效评价结果管理' WHERE guid='2200406';
update fasp_t_pubmenu set name='部门绩效评价结果采集' WHERE guid='220040601';
update fasp_t_pubmenu set name='部门绩效评价结果填报' WHERE guid='220040602';
update fasp_t_pubmenu set name='部门绩效评价结果查看' WHERE guid='220040603';
update fasp_t_pubmenu set name='部门绩效评价结果管理' WHERE guid='220040604';
delete from KPI_T_SETINPUTSTEP WHERE guid='4F7C4D202ECB48F78523FADB77D2BC39';
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(11,'4F7C4D202ECB48F78523FADB77D2BC39','result','部门绩效评价结果',3);
delete from KPI_T_PROCESSMENU WHERE PROCESSID='4F7C4D202ECB48F78523FADB77D2BC39';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200406'  ,'22004', 0,'4F7C4D202ECB48F78523FADB77D2BC39' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040601' ,'2200406',1,'4F7C4D202ECB48F78523FADB77D2BC39' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040602' ,'2200406',2,'4F7C4D202ECB48F78523FADB77D2BC39' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040603' ,'2200406',3,'4F7C4D202ECB48F78523FADB77D2BC39' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220040604' ,'2200406',4,'4F7C4D202ECB48F78523FADB77D2BC39' );


--财政绩效目标
update fasp_t_pubmenu set name='财政绩效目标管理' WHERE guid='2200501';                                               
update fasp_t_pubmenu set name='财政绩效目标采集' WHERE  guid='220050101';
update fasp_t_pubmenu set name='财政绩效目标填报' WHERE  guid='220050102';
update fasp_t_pubmenu set name='财政绩效目标查看' WHERE  guid='220050103';
update fasp_t_pubmenu set name='财政绩效目标确认' WHERE  guid='220050104';
update KPI_T_SETINPUTSTEP set name='财政绩效目标'  WHERE guid ='AEC03AC23F59433597A322DA13891111';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='AEC03AC23F59433597A322DA13891111';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200501'  ,'22005', 0,'AEC03AC23F59433597A322DA13891111' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050101' ,'2200501',1,'AEC03AC23F59433597A322DA13891111' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050102' ,'2200501',2,'AEC03AC23F59433597A322DA13891111' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050103' ,'2200501',3,'AEC03AC23F59433597A322DA13891111' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050104' ,'2200501',4,'AEC03AC23F59433597A322DA13891111' );



--财政绩效信息
delete from fasp_t_pubmenu WHERE guid='2200504';
delete from fasp_t_pubmenu WHERE guid='220050401';
delete from fasp_t_pubmenu WHERE guid='220050402';
delete from fasp_t_pubmenu WHERE guid='220050403';
delete from fasp_t_pubmenu WHERE guid='220050404';
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200504',2,0,1,'006004006','财政绩效信息管理','22005','',3,'remark',1,'kpi',1);                                                                                                                                                                            
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050401',3,1,1,'006004006001','财政绩效信息采集','2200504','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'processId=message',4,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050402',3,1,1,'006004006002','财政绩效信息填报','2200504','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'processId=message',5,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050403',3,1,1,'006004006003','财政绩效信息查看','2200504','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'processId=message',6,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050404',3,1,1,'006004006004','财政绩效信息确认','2200504','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'processId=message ',7,'remark',1,'kpi',1); 
update KPI_T_SETINPUTSTEP set name='财政绩效信息'  WHERE guid ='AEC03AC23F59433597A322DA13891FE7';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='AEC03AC23F59433597A322DA13891FE7';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200504'  ,'22005', 0,'AEC03AC23F59433597A322DA13891FE7' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050401' ,'2200504',1,'AEC03AC23F59433597A322DA13891FE7' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050402' ,'2200504',2,'AEC03AC23F59433597A322DA13891FE7' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050403' ,'2200504',3,'AEC03AC23F59433597A322DA13891FE7' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050404' ,'2200504',4,'AEC03AC23F59433597A322DA13891FE7' );
--监督评价整改


--财政绩效整改
delete from fasp_t_pubmenu WHERE guid='2200505';
delete from fasp_t_pubmenu WHERE guid='220050501';
delete from fasp_t_pubmenu WHERE guid='220050502';
delete from fasp_t_pubmenu WHERE guid='220050503';
delete from fasp_t_pubmenu WHERE guid='220050504';
delete from KPI_T_SETINPUTSTEP WHERE guid='AC16A5B1539945EDB735193EFF155A9E';
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(16,'AC16A5B1539945EDB735193EFF155A9E','update','财政绩效整改',4);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200505',2,0,1,'006004007','财政绩效整改管理','22005','',3,'remark',1,'kpi',1);                                                                                                                                                                                                                                                                               
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050501',3,1,1,'006004007001','财政绩效整改采集','2200505','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'processId=update',4,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050502',3,1,1,'006004007002','财政绩效整改填报','2200505','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'processId=update',5,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050503',3,1,1,'006004007003','财政绩效整改查看','2200505','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'processId=update',6,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050504',3,1,1,'006004007004','财政绩效整改确认','2200505','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'processId=update',7,'remark',1,'kpi',1);
delete from KPI_T_PROCESSMENU WHERE PROCESSID='AC16A5B1539945EDB735193EFF155A9E';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200505'  ,'22005', 0,'AC16A5B1539945EDB735193EFF155A9E' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050501' ,'2200505',1,'AC16A5B1539945EDB735193EFF155A9E' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050502' ,'2200505',2,'AC16A5B1539945EDB735193EFF155A9E' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050503' ,'2200505',3,'AC16A5B1539945EDB735193EFF155A9E' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050504' ,'2200505',4,'AC16A5B1539945EDB735193EFF155A9E' );



--财政绩效自评
update fasp_t_pubmenu set name='财政绩效自评管理' WHERE guid='2200502';                                               
update fasp_t_pubmenu set name='财政绩效自评采集' WHERE  guid='220050201';
update fasp_t_pubmenu set name='财政绩效自评填报' WHERE  guid='220050202';
update fasp_t_pubmenu set name='财政绩效自评查看' WHERE  guid='220050203';
update fasp_t_pubmenu set name='财政绩效自评确认' WHERE  guid='220050204';
update KPI_T_SETINPUTSTEP set name='财政绩效自评'  WHERE guid ='E572EE6F7D144ED7B44ACD43695D19EF';
delete from KPI_T_PROCESSMENU WHERE PROCESSID='E572EE6F7D144ED7B44ACD43695D19EF';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200502'  ,'22005', 0,'E572EE6F7D144ED7B44ACD43695D19EF' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050201' ,'2200502',1,'E572EE6F7D144ED7B44ACD43695D19EF' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050202' ,'2200502',2,'E572EE6F7D144ED7B44ACD43695D19EF' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050203' ,'2200502',3,'E572EE6F7D144ED7B44ACD43695D19EF' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050204' ,'2200502',4,'E572EE6F7D144ED7B44ACD43695D19EF' );




--财政绩效评价
delete from fasp_t_pubmenu WHERE guid='2200506';
delete from fasp_t_pubmenu WHERE guid='220050601';
delete from fasp_t_pubmenu WHERE guid='220050602';
delete from fasp_t_pubmenu WHERE guid='220050603';
delete from fasp_t_pubmenu WHERE guid='220050604';
delete from KPI_T_SETINPUTSTEP WHERE guid='2F8A0EE93A7746A581497FECD2332C45';
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(16,'2F8A0EE93A7746A581497FECD2332C45','eval','财政绩效评价',4);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200506',2,0,1,'006004008','财政绩效评价管理','22005','',3,'remark',1,'kpi',1 );                                                                                                                                                                                                                                                                                                 
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050601',3,1,1,'006004008001','财政绩效评价采集','2200506','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'processId=eval',4,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050602',3,1,1,'006004008002','财政绩效评价填报','2200506','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'processId=eval',5,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050603',3,1,1,'006004008003','财政绩效评价查看','2200506','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'processId=eval',6,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050604',3,1,1,'006004008004','财政绩效评价确认','2200506','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'processId=eval',7,'remark',1,'kpi',1);

delete from KPI_T_PROCESSMENU WHERE PROCESSID='2F8A0EE93A7746A581497FECD2332C45';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200506'  ,'22005', 0,'2F8A0EE93A7746A581497FECD2332C45' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050601' ,'2200506',1,'2F8A0EE93A7746A581497FECD2332C45' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050602' ,'2200506',2,'2F8A0EE93A7746A581497FECD2332C45' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050603' ,'2200506',3,'2F8A0EE93A7746A581497FECD2332C45' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050604' ,'2200506',4,'2F8A0EE93A7746A581497FECD2332C45' );



--财政绩效调整
delete from fasp_t_pubmenu WHERE guid='2200509';
delete from fasp_t_pubmenu WHERE guid='220050901';
delete from fasp_t_pubmenu WHERE guid='220050902';
delete from fasp_t_pubmenu WHERE guid='220050903';
delete from fasp_t_pubmenu WHERE guid='220050904';
delete from KPI_T_SETINPUTSTEP WHERE guid='08BE40F5DE8A4628A6C8D506130A9C40';
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(16,'08BE40F5DE8A4628A6C8D506130A9C40','adjust','财政绩效调整',4);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200509',2,0,1,'006004009','财政绩效调整管理','22005','',3,'remark',1,'kpi',1);                                                                                                                                                                                                                                                                                              
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050901',3,1,1,'006004009001','财政绩效调整采集','2200509','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'processId=adjust',4,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050902',3,1,1,'006004009002','财政绩效调整填报','2200509','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'processId=adjust',5,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050903',3,1,1,'006004009003','财政绩效调整查看','2200509','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'processId=adjust',6,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('220050904',3,1,1,'006004009004','财政绩效调整确认','2200509','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'processId=adjust  ',7,'remark',1,'kpi',1);
delete from KPI_T_PROCESSMENU WHERE PROCESSID='08BE40F5DE8A4628A6C8D506130A9C40';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200509'  ,'22005', 0,'08BE40F5DE8A4628A6C8D506130A9C40' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050901' ,'2200509',1,'08BE40F5DE8A4628A6C8D506130A9C40' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050902' ,'2200509',2,'08BE40F5DE8A4628A6C8D506130A9C40' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050903' ,'2200509',3,'08BE40F5DE8A4628A6C8D506130A9C40' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050904' ,'2200509',4,'08BE40F5DE8A4628A6C8D506130A9C40' );




--财政绩效监督
delete from fasp_t_pubmenu WHERE guid='22005010';
delete from fasp_t_pubmenu WHERE guid='2200501001';
delete from fasp_t_pubmenu WHERE guid='2200501002';
delete from fasp_t_pubmenu WHERE guid='2200501003';
delete from fasp_t_pubmenu WHERE guid='2200501004';
delete from KPI_T_SETINPUTSTEP WHERE guid='AC20506CF39D4F99BAD23E4D60D20636';
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('22005010',2,0,1,'0060040010','财政绩效监督管理','22005','',3,'remark',1,'kpi',1);                                                                                                                                                                                                                                                                       
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200501001',3,1,1,'0060040010001','财政绩效监督采集','22005010','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageSelect'||chr(38)||'processId=monitor',4,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200501002',3,1,1,'0060040010002','财政绩效监督填报','22005010','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageInput'||chr(38)||'processId=monitor',5,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200501003',3,1,1,'0060040010003','财政绩效监督查看','22005010','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageView'||chr(38)||'processId=monitor',6,'remark',1,'kpi',1);
insert  into fasp_t_pubmenu (GUID,Levelno,ISLEAF,STATUS,CODE,NAME,PARENTID,URL,MENUORDER,REMARK,APPSYSID,APPID,ADMINTYPE) VALUES('2200501004',3,1,1,'0060040010004','财政绩效监督确认','22005010','/spf/kpi/business/main.do?dealtype=70'||chr(38)||'appID=KPI'||chr(38)||'kpiStage=messageAudit'||chr(38)||'processId=monitor ',7,'remark',1,'kpi',1);
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(12,'AC20506CF39D4F99BAD23E4D60D20636','monitor','财政绩效监督',4); 
delete from KPI_T_PROCESSMENU WHERE PROCESSID='AC20506CF39D4F99BAD23E4D60D20636';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '22005010'  ,'22005', 0,'AC20506CF39D4F99BAD23E4D60D20636' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('2200501001' ,'22005010',1,'AC20506CF39D4F99BAD23E4D60D20636' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('2200501002' ,'22005010',2,'AC20506CF39D4F99BAD23E4D60D20636' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('2200501003' ,'22005010',3,'AC20506CF39D4F99BAD23E4D60D20636' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('2200501004' ,'22005010',4,'AC20506CF39D4F99BAD23E4D60D20636' );


--财政绩效评价结果
update fasp_t_pubmenu set name='财政绩效评价结果管理' WHERE guid='2200503';                                               
update fasp_t_pubmenu set name='财政绩效评价结果采集' WHERE  guid='220050301';
update fasp_t_pubmenu set name='财政绩效评价结果填报' WHERE  guid='220050302';
update fasp_t_pubmenu set name='财政绩效评价结果查看' WHERE  guid='220050303';
update fasp_t_pubmenu set name='财政绩效评价结果确认' WHERE  guid='220050304';
delete from KPI_T_SETINPUTSTEP WHERE guid='66108B3D2E8C4BC0B33A0C2C2C919F20';
insert into KPI_T_SETINPUTSTEP(ORDERID,GUID,CODE,NAME,TYPE)values(12,'66108B3D2E8C4BC0B33A0C2C2C919F20','result','财政绩效评价结果',4); 
delete from KPI_T_PROCESSMENU WHERE PROCESSID='66108B3D2E8C4BC0B33A0C2C2C919F20';
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ( '2200503'  ,'22005', 0,'66108B3D2E8C4BC0B33A0C2C2C919F20' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050301' ,'2200503',1,'66108B3D2E8C4BC0B33A0C2C2C919F20' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050302' ,'2200503',2,'66108B3D2E8C4BC0B33A0C2C2C919F20' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050303' ,'2200503',3,'66108B3D2E8C4BC0B33A0C2C2C919F20' );
insert into KPI_T_PROCESSMENU (MENUGUID, PARENTID, MENUTYPE,PROCESSID) values ('220050304' ,'2200503',4,'66108B3D2E8C4BC0B33A0C2C2C919F20' );
end;
--WUTF_20160816整理阶段和菜单关联
