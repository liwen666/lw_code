DECLARE
v_datatype varchar2(100);
begin
 SELECT DATA_TYPE into v_datatype FROM user_tab_columns WHERE table_name='P#CAL_T_FORMULA'  AND COLUMN_NAME='REFCOLID'; 
 if v_datatype='VARCHAR2' then
   EXECUTE IMMEDIATE 'alter table P#CAL_T_FORMULA add (REFCOLIDCLOB clob)';
   EXECUTE IMMEDIATE 'update P#CAL_T_FORMULA tt set tt.refcolidclob=TT.REFCOLID';
   EXECUTE IMMEDIATE 'alter table P#CAL_T_FORMULA drop column REFCOLID';
   EXECUTE IMMEDIATE 'alter table P#CAL_T_FORMULA rename column REFCOLIDCLOB to REFCOLID';
   
 end IF;
  
end;



--修改cal_t_formula的refcolid列类型改成clob
