  begin
  delete from P#CODE_T_SPFFUNDMM;
  
  insert into P#CODE_T_SPFFUNDMM (GUID, CODE, NAME, SUPERGUID, ISLEAF, STATUS)
  values ('1', '001', '项目法', '0', '1', '1');

  insert into P#CODE_T_SPFFUNDMM (GUID, CODE, NAME, SUPERGUID, ISLEAF, STATUS)
  values ('2', '002', '因素法', '0', '1', '1');

  insert into P#CODE_T_SPFFUNDMM (GUID, CODE, NAME, SUPERGUID, ISLEAF, STATUS)
  values ('3', '003', '因素法与项目法相结合', '0', '1', '1');

  insert into P#CODE_T_SPFFUNDMM (GUID, CODE, NAME, SUPERGUID, ISLEAF, STATUS)
  values ('4', '004', '预算直接填报', '0', '1', '1');
 
  end;
--项目库资金管理模式
--项目库资金管理模式
