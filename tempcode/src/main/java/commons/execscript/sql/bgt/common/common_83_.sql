declare 
  v_count integer;
begin
update BIL_T_SETCODE_B b set b.BASETYPENAME='日期' where b.BASETYPEID='006';

SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_SHOWTABLE';
IF v_count=0 THEN
    EXECUTE IMMEDIATE 'create table p#bil_t_showtable(
  GUID VARCHAR2(32) DEFAULT SYS_GUID() PRIMARY KEY,
  BILLTYPEID VARCHAR2(32),
  TABLEID VARCHAR2(32))';
  sys_p_partition_table('P#BIL_T_SHOWTABLE');
END IF;

end;--单据脚本_3#
