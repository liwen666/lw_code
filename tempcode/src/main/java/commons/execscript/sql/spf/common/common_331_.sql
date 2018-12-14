DECLARE
begin
UPDATE P#SPF_T_SETCODE_B SET BASETYPENAME='地区编码(专项)'  WHERE TYPEID = '0'  AND basetypeid='005' ;
DELETE FROM P#SPF_T_SETCODE_B WHERE BASETYPEID IN('009','010') AND TYPEID ='0';
for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop   

  begin
  
  insert into P#SPF_T_SETCODE_B (YEAR, PROVINCE, BASETYPEID, BASETYPENAME, BGTLVL, CODESHOW, ISSPESTR, ISUSE, ORDERID, STATUS, TYPEID)
  values (v_row.year, v_row.districtid, '009', '地区编码(项目)', '', '000', '0', '1', 8, '1', 0);

  insert into P#SPF_T_SETCODE_B (YEAR, PROVINCE, BASETYPEID, BASETYPENAME, BGTLVL, CODESHOW, ISSPESTR, ISUSE, ORDERID, STATUS, TYPEID)
  values (v_row.year, v_row.districtid, '010', '流水号(全局)', '', '5', '3', '1', 100, '1', 0);
  end;
end loop;

end;

--修改项目编码得基本元素类型，即添加全局流水号、项目地区编码元素

--修改项目编码基本元素类型