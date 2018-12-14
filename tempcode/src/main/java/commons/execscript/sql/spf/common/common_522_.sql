  begin
  delete from P#CODE_T_SPFCPFS where guid in('01687XttpwgfG','01687HuVWR1UZ','01687Q0ZIRMyZ','01687z5OfCsSg');
  
  insert into P#CODE_T_SPFCPFS (GUID, CODE, NAME, SUPERGUID, ISLEAF, STATUS)
  values ('01687XttpwgfG', '001', '财政审批', '0', '1', '1');

  insert into P#CODE_T_SPFCPFS (GUID, CODE, NAME, SUPERGUID, ISLEAF, STATUS)
  values ('01687HuVWR1UZ', '002', '部门审批', '0', '1', '1');

  insert into P#CODE_T_SPFCPFS (GUID, CODE, NAME, SUPERGUID, ISLEAF, STATUS)
  values ('01687Q0ZIRMyZ', '003', '联合审批', '0', '1', '1');

  insert into P#CODE_T_SPFCPFS (GUID, CODE, NAME, SUPERGUID, ISLEAF, STATUS)
  values ('01687z5OfCsSg', '004', '直接审批', '0', '1', '2');
  end;
--项目审批初始化
