CREATE OR REPLACE PROCEDURE pro_initKpiIndiTempData(v_tempTableName in VARCHAR2,v_tableName in
varchar2,v_columnName in varchar2,v_columnValue in varchar2,v_kpiObjectId in varchar2,v_tempKpiType in
varchar2,v_indiType in varchar2,v_result out varchar2) As
insert_sql varchar2(4000);
delete_sql varchar2(4000);
select_sql varchar2(4000);
update_sql varchar2(4000);
errorException exception; --申明异常
errorCode number; --异常编码
errorMsg varchar2(1000); --异常信息
v_guid varchar2(32);
v_KPINAME varchar2(2000);
v_score NUMBER(10);
v_STANDARD varchar2(500);
v_ISINSERT char(1);
v_ISUPDATE char(1);
v_ISDELETE char(1);
v_EXPLANATION varchar2(300);
flag varchar2(10);
TYPE ref_cursor_type IS REF CURSOR;
v_res_cur         ref_cursor_type;
 begin
   /**
   创建：create by gf 20170607
   业务说明：业务数据进行初始化指标时触发
   返回值：v_result
   **/
   v_result := '0';
   if v_tempTableName is null then
     v_result :='参数v_tempTableName不能为空';
     return;
   end if;
   if v_tableName is null then
     v_result :='参数v_tableName不能为空';
     return;
   end if;
   if v_columnName is null then
     v_result :='参数v_columnName不能为空';
     return;
   end if;
 --  delete_sql:=' delete from '||v_tableName||' where '||v_columnName||' = '''||v_columnValue||'';
 --  删除业务表中有但是指标库中已经不存在的指标
   delete_sql:=' delete from '||v_tableName||' f where f.'||v_columnName||' = '''||v_columnValue||'''
   and (f.SUPERID  in (select templateid from '||v_tableName||' m where m.'||v_columnName||' = '''||
v_columnValue||''' and m.TEMPLATEID not in ( select
   guid from '||v_tempTableName||')) or f.TEMPLATEID in (select templateid from '||v_tableName||' m where
m.'||v_columnName||' = '''||v_columnValue||''' and m.TEMPLATEID not in ( select
   guid from '||v_tempTableName||')))';
   EXECUTE IMMEDIATE delete_sql;
   Dbms_output.put_line(delete_sql);
   --更新属性值
  -- select_sql :='select KPINAME,SCORE,STANDARD,ISINSERT,ISUPDATE,ISDELETE,EXPLANATION from ';
   select_sql :='select f.guid,f.KPINAME,f.SCORE,f.STANDARD,f.ISINSERT,f.ISUPDATE,f.ISDELETE,f.EXPLANATION
from '||v_tempTableName||' f,KPI_T_KPITOCALIBER s
    where f.guid = s.KPIID and s.kpitype = '''||v_tempKpiType||''' and s.OBJECTID = '''||v_kpiObjectId||'''
    and f.inditype = '''||v_indiType||''' and f.isrelease = ''1''  and f.guid in(select TEMPLATEID from '||
v_tableName||'
    where '||v_columnName||' = '''||v_columnValue||''')';
   open v_res_cur for select_sql;
  --循环
   LOOP
      fetch v_res_cur
      into v_guid,
           v_KPINAME,
           v_score,
           v_STANDARD,
           v_ISINSERT,
           v_ISUPDATE,
           v_ISDELETE,
           v_EXPLANATION;
           exit when v_res_cur%notfound;
         --  update_sql :=' update ';
           update_sql := 'update '||v_tableName||' set KPINAME = '''||v_KPINAME||''',
                          SCORE = '''||v_score||''',
                          STANDARD = '''||v_STANDARD||''',
                          ISINSERT = '''||v_ISINSERT||''',
                          ISUPDATE = '''||v_ISUPDATE||''',
                          ISDELETE = '''||v_ISDELETE||''',
                          EXPLANATION = '''||v_EXPLANATION||'''
           where  TEMPLATEID = '''|| v_guid ||''' and '||v_columnName||' ='''||v_columnValue||''' ';
           EXECUTE IMMEDIATE update_sql;
           END LOOP;
  close v_res_cur;

   insert_sql :='insert into '||v_tableName||' (
DATAKEY,TEMPLATEID,SUPERID,ISLEAF,ORDERID,LEVELNO,ISINSERT,ISDELETE, KPINAME,SCORE,STANDARD
   ,EXPLANATION,ISDJ,ISQZX,ISUPDATE,ISTEMPLATE,CELLSECU,FDCODE, userid, '||v_columnName||',ISINDI)
   select sys_guid() DATAKEY,t.GUID
   TEMPLATEID,t.SUPERGUID SUPERID,t.ISLEAF,t.ORDERID,t.LEVELNO,t.ISINSERT,t.ISDELETE,
t.KPINAME,t.SCORE,t.STANDARD,t.EXPLANATION,''0''
   ISDJ,''0'' ISQZX,t.ISUPDATE,''1'' ISTEMPLATE,'''' CELLSECU,'''' || t.LEVELNO FDCODE, ''*'' userid ,'''||
v_columnValue||''', ''1'' ISINDI
   from '||v_tempTableName||' t, KPI_T_KPITOCALIBER s
   where t.GUID = s.KPIID and s.KPITYPE = '''||v_tempKpiType||''' and s.OBJECTID = '''||v_kpiObjectId||'''
   and t.INDITYPE = '''||v_indiType||''' and t.isrelease = ''1'' and not exists (select 1 from '||
v_tableName||' f where f.'||v_columnName||' = '''||v_columnValue||'''
   AND f.TEMPLATEID = t.GUID ) ';
   EXECUTE IMMEDIATE insert_sql;
   Dbms_output.put_line(insert_sql);
   commit;
   exception
     when errorException then
              errorCode := SQLCODE;
              errorMsg := SUBSTR(SQLERRM, 1, 200);
              flag := 'false';
              v_result := 'flag=' || flag || ',errorCode=' || errorCode || ',errorMsg=' || errorMsg;
     when others then
               errorCode := SQLCODE;
               errorMsg := SUBSTR(SQLERRM, 1, 200);
               flag := 'false';
               v_result := 'flag=' || flag || ',errorCode=' || errorCode || ',errorMsg=' || errorMsg;
end pro_initKpiIndiTempData;--GF_201706013_初始化指标增加修改和删除功能（既与指标库指标同步）(修复01)
