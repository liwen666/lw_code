
--GET_DB_UUID_VAL
create or replace procedure GET_DB_UUID_VAL(u_id OUT varchar2)is
begin
  u_id:=sys_guid();
  --DBMS_OUTPUT.put_line(s);
end GET_DB_UUID_VAL;
	
	
	
--GET_IF_EXISTS	
create or replace procedure GET_IF_EXISTS(v OUT number,v_sql IN varchar2)is
begin
   --select count(1) into v from user_tables where table_name='W#AACC'
   --select count(1) into v from user_views where VIEW_NAME=VIEWS_NAME;
   --   0 不存在    1 存在
  execute immediate v_sql into v;
end;





--ExecDll_Long
create or replace procedure ExecDll_Long(strsql clob) AUTHID CURRENT_USER is
 pragma autonomous_transaction;
 v_execsql varchar2(30000);
 v_ret     varchar2(100);
 v_clob    clob;
begin
  v_execsql:=strsql;
  EXECUTE IMMEDIATE v_execsql;
  commit;
  EXCEPTION
  WHEN OTHERS THEN
    v_clob:=EMPTY_CLOB;
    dbms_lob.createtemporary(v_clob,TRUE);
    insert into Secu_T_Tmp_LogSecuRec(orderid,remark,sqlclob) values(to_char(Secu_seq_FQ.nextval),substr(dbms_utility.format_error_stack,1,200),EMPTY_CLOB()) returning sqlclob into v_clob;
    DBMS_LOB.Copy(v_clob, strSql,90000,1,1);
    commit;
    RAISE_APPLICATION_ERROR(-20001,'脚本执行过程中出现错误，请查看表Secu_T_Tmp_LogSecuRec内容！');
end ExecDll_Long;





--GET_GLOBAL_ISMULTDB_VAL
create or replace procedure GET_GLOBAL_ISMULTDB_VAL(s OUT varchar2)is
begin
  s:=GLOBAL_IsMultDb.v_ISMULT;
  --DBMS_OUTPUT.put_line(s);
end GET_GLOBAL_ISMULTDB_VAL;




create or replace procedure EXECDLL_LONG_PARAM(errorMessage OUT VARCHAR, strsql clob) AUTHID CURRENT_USER is
 pragma autonomous_transaction;
 v_execsql varchar2(30000);
begin
  v_execsql:=strsql;
  EXECUTE IMMEDIATE v_execsql;
  commit;
  EXCEPTION
  WHEN OTHERS THEN
    errorMessage:=substr(dbms_utility.format_error_stack,1,200);
    --RAISE_APPLICATION_ERROR(-20001,'脚本执行过程中出现错误，请查看表Secu_T_Tmp_LogSecuRec内容！');
end EXECDLL_LONG_PARAM;
