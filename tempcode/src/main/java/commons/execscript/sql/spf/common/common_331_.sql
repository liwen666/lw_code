DECLARE
begin
UPDATE P#SPF_T_SETCODE_B SET BASETYPENAME='��������(ר��)'  WHERE TYPEID = '0'  AND basetypeid='005' ;
DELETE FROM P#SPF_T_SETCODE_B WHERE BASETYPEID IN('009','010') AND TYPEID ='0';
for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop   

  begin
  
  insert into P#SPF_T_SETCODE_B (YEAR, PROVINCE, BASETYPEID, BASETYPENAME, BGTLVL, CODESHOW, ISSPESTR, ISUSE, ORDERID, STATUS, TYPEID)
  values (v_row.year, v_row.districtid, '009', '��������(��Ŀ)', '', '000', '0', '1', 8, '1', 0);

  insert into P#SPF_T_SETCODE_B (YEAR, PROVINCE, BASETYPEID, BASETYPENAME, BGTLVL, CODESHOW, ISSPESTR, ISUSE, ORDERID, STATUS, TYPEID)
  values (v_row.year, v_row.districtid, '010', '��ˮ��(ȫ��)', '', '5', '3', '1', 100, '1', 0);
  end;
end loop;

end;

--�޸���Ŀ����û���Ԫ�����ͣ������ȫ����ˮ�š���Ŀ��������Ԫ��

--�޸���Ŀ�������Ԫ������