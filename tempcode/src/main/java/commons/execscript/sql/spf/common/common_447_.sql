begin
  EXECUTE IMMEDIATE Q'/alter table P#SPF_T_SETCODE_B modify codeshow VARCHAR2(1000)/';
  EXECUTE IMMEDIATE Q'/alter table P#SPF_T_SETCODE modify codeshow VARCHAR2(1000)/';
   delete from P#SPF_T_SETCODE_B where BASETYPEID in ('0009','011');
for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop   
  begin
  insert into P#SPF_T_SETCODE_B (YEAR, PROVINCE, BASETYPEID, BASETYPENAME, BGTLVL, CODESHOW, ISSPESTR, ISUSE, ORDERID, STATUS, TYPEID)
  values (v_row.year, v_row.districtid, '0009', '自定义编码', '', 'select ''1'' spfcode from dual', '4', '1', 9, '1', '1');

  insert into P#SPF_T_SETCODE_B (YEAR, PROVINCE, BASETYPEID, BASETYPENAME, BGTLVL, CODESHOW, ISSPESTR, ISUSE, ORDERID, STATUS, TYPEID)
  values (v_row.year, v_row.districtid, '011', '自定义编码', '', 'select ''1'' projcode from dual', '4', '1', 10, '1', '0');
 end;
end loop;
end;
--给编码设置添加基本元素类型
