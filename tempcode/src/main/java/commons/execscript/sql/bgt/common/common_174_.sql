BEGIN
DELETE FROM Dict_t_Settingtabinfo WHERE DBTABLENAME = 'P#BGT_T_BUSIRULETODOCTYPE';
insert into Dict_t_Settingtabinfo ( DBTABLENAME, NAME, REMARK, TABSWHERE, APPID,  STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD)
Select 'P#BGT_T_BUSIRULETODOCTYPE', '忽略公文类型关系表', '忽略公文类型表', null, '*', '1', '1', '0', '1', '1', '0'     From dual;

insert into p#Synch_t_Setting (PHYSDBNAME, SYNCHORDER, SYNCHCONDITION, PKCOL, SYNCHEDHANDLER, SYNCHRECOGCOL, TABLETYPE, MAXROW, NETTYPE, REMARK, YEAR, PROVINCE, DIRECTION, FILTERCOL, ISALWAYSEXPORT)
Select 'P#BGT_T_BUSIRULETODOCTYPE', 51, null, 'PROVINCE,YEAR,STATUS,GUID', null, 'DBVERSION', '1', 100000, null, null, T1.year, T1.Districtid, 'O', 'BUSIRULEID In(Select BBCK.GUID From P#BGT_T_BUSINESSCHECKDEF BBCK Where  BBCK.CHECKID IN (SELECT CHECKID FROM P#BGT_T_CHECKDEF WHERE (LMODELID IN (@TABLEID@) OR RMODELID IN (@TABLEID@))))', '0'
 From Pub_t_Partition_Divid T1
   Where Year <> '*'
     And Districtid = Global_Multyear_Cz.Secu_f_Global_Parm('DIVID')
        And Not Exists (Select 1
            From p#Synch_t_Setting T2
           Where t2.status='1' 
             And t2.province =t1.districtid
             And t2.physdbname= 'P#BGT_T_BUSIRULETODOCTYPE'
             And t2.year = T1.YEAR);
End;
--初始化忽略公文类型的区划配置信息
