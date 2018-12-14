declare
--初始化预算调整表
v_n number(8):=0;
v_newGuid varchar2(32);
v_orderID number(16);
begin
select count(0) into v_n from user_views v where v.VIEW_NAME='BGT_T_JBZCTZB';
if v_n=0 then 
--创建表结构
execute immediate 'create table  BGT_T_JBZCTZB as select t.* from bgt_t_jbzcmxb t where rownum=0';
      for c_colData in(select * from dict_t_factor f 
                        where f.TABLEID =(select m.tableid from dict_t_model m where m.dealtype = '2201')
                         and f.DEFAULTVALUE is not null) loop
    
         execute immediate 'alter table BGT_T_JBZCTZB modify '||c_colData.Dbcolumnname||' default  '||c_colData.DEFAULTVALUE;
       end loop;
       
        execute immediate  Q'{alter table bgt_t_jbzctzb modify STATUS default '1'}';  
        execute immediate  Q'{alter table bgt_t_jbzctzb modify DBVERSION default sysdate}';  
 --增加 调整说明字段
 execute immediate  Q'{alter table bgt_t_jbzctzb add ADJUSTCOMMENT VARCHAR2(500)}';    
 --设置主键 
 execute immediate Q'{alter table BGT_T_JBZCTZB add constraint PK_P#BGT_T_JBZCTZB primary key (DATAKEY)}';
--刷分区

SYS_P_PARTITION_TABLE('BGT_T_JBZCTZB');



--初始化dealtype 和默认列信息
delete from dict_t_dealtype where dealid = '2401';
insert into dict_t_dealtype(appid,dealid,dealname,orderid,needconfig) values('BGT','2401','基本支出调整表',103,'0');
delete from dict_t_defaultcol where dealid = '2401';
insert into dict_t_defaultcol(DEALID,ORDERID, NAME,DBCOLUMNNAME,DATATYPE,DATALENGTH,SCALE,CSID,ISPRIMARY,ISRESERVE,GUID,STATUS,DBVERSION,DEFAULTVALUE,ISLOGICKEY,DEID)
select '2401' as DEALID,ORDERID,NAME,DBCOLUMNNAME,DATATYPE,DATALENGTH,SCALE,CSID,ISPRIMARY,ISRESERVE,sys_guid() GUID,STATUS,SYSDATE as DBVERSION,DEFAULTVALUE,ISLOGICKEY,DEID from dict_t_defaultcol tt where tt.dealid='2201';

--初始化model和factor信息
delete from dict_t_model where tableid = '2891250A01630912E050A8C0210515F6';
insert into dict_t_model(APPID,DBTABLENAME, DBVERSION, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, NAME, ORDERID, SUITID, TABLEID, TABLETYPE, ISBAK, INSERTVERSION, ISALLDISTRICT)
select 'BGT' as APPID,'BGT_T_JBZCTZB' as DBTABLENAME,SYSDATE AS DBVERSION,'2401' AS DEALTYPE,m.EXTPROP,m.INPUTLVL,m.ISADD,m.ISMAN,m.ISPARTITION,m.ISRESERVED,m.ISSHOW,m.ISTASK,m.ISSUMTAB,'基本支出调整表' AS NAME,m.ORDERID,m.SUITID,'2891250A01630912E050A8C0210515F6' as TABLEID,m.TABLETYPE,m.ISBAK,m.INSERTVERSION,m.ISALLDISTRICT 
from dict_t_model m
where m.dealtype = '2201';

delete from dict_t_factor where tableid = '2891250A01630912E050A8C0210515F6';
insert into dict_t_factor(ALIAS,COLTIPS,COLUMNID,CSID,DATALENGTH,DATATYPE,DBCOLUMNNAME,DBVERSION,DEFAULTVALUE,DEID,EXTPROP,ISBANDCOL,ISHREF,OPENWINDOWTYPE,ISKEY,ISLEAF,ISREGEX,ISRESERVE,ISSUM,ISUPDATE,ISVIRTUAL,ISVISIBLE,LEVELNO,NAME,NULLABLE,ORDERID,SCALE,SHOWFORMAT,SHOWWIDTH,STATUS,SUPERID,TABLEID,INSERTVERSION,PARENTNODECANCHECK)
select f.ALIAS,
       f.COLTIPS,
       f.COLUMNID,
       f.CSID,
       f.DATALENGTH,
       f.DATATYPE,
       f.DBCOLUMNNAME,
       sysDate as DBVERSION,
       f.DEFAULTVALUE,
       f.DEID,
       f.EXTPROP,
       f.ISBANDCOL,
       f.ISHREF,
       f.OPENWINDOWTYPE,
       f.ISKEY,
       f.ISLEAF,
       f.ISREGEX,
       f.ISRESERVE,
       f.ISSUM,
       f.ISUPDATE,
       f.ISVIRTUAL,
       f.ISVISIBLE,
       f.LEVELNO,
       f.NAME,
       f.NULLABLE,
       f.ORDERID,
       f.SCALE,
       f.SHOWFORMAT,
       f.SHOWWIDTH,
       f.STATUS,
       f.SUPERID,
       '2891250A01630912E050A8C0210515F6' as TABLEID,
       sysdate as INSERTVERSION,
       f.PARENTNODECANCHECK
   from dict_t_factor f
 where f.TABLEid =(select md.TABLEID from dict_t_model md where md.DEALTYPE = '2201');
 select max(orderid)+1 into v_orderID  from dict_t_factor  where tableid=(select md.TABLEID from dict_t_model md where md.DEALTYPE = '2201');
insert into dict_t_factor (COLUMNID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, EXTPROP, ISKEY, ISLEAF, ISUPDATE, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, SHOWFORMAT, SUPERID, TABLEID)
values (sys_guid(), 500, 3, 'ADJUSTCOMMENT', '', '0000000000000000000000000000000', '0', '1', '1', '1', 1, '调整说明', '1', v_orderID, '0', '0', '2891250A01630912E050A8C0210515F6');
 
 update dict_t_factor tabM set tabM.COLUMNID=sys_guid()
   where tabM.TABLEID='2891250A01630912E050A8C0210515F6' and tabM.ISLEAF='1';
  

for c_superData in ( select c.COLUMNID
                  from dict_t_factor c
                 where c.tableid = '2891250A01630912E050A8C0210515F6'
                   and c.ISLEAF='0')loop
             select sys_guid() into v_newGuid from dual;
             update dict_t_factor tabM set tabM.SUPERID=v_newGuid
             where tabM.TABLEID='2891250A01630912E050A8C0210515F6' and tabM.SUPERID=c_superData.Columnid;
             
              update dict_t_factor tabM set tabM.COLUMNID=v_newGuid
             where tabM.TABLEID='2891250A01630912E050A8C0210515F6' and tabM.COLUMNID=c_superData.Columnid;
                    
                   end loop;  
  
end if;


end;

--03_初始化基本支出调整表BGT_T_JBZCTZB
