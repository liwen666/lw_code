begin

  delete from p#SPF_T_CONDISET where tableid='8CADB101B53E41C18FC2FA9510D9A7DC';
  
  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
    
  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'49D09FD91EF51DF7E0533A06A8C0984C', '$docID$.equals("isnull") && $level$.equals("0") &&("1").equals($setupStatus$)&&("0").equals($direction$)', 'ISRELEASE=$isRelease$ and $spfIdSql$ and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'43C1ED54AFBE6D40E0533A06A8C06562', '!$docID$.equals("isnull") && $level$.equals("0") && ("2").equals($setupStatus$)', 'ISRELEASE=$isRelease$ and $spfIdSql$ and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'43C1ED54AFBF6D40E0533A06A8C06562', '!$docID$.equals("isnull") && $level$.equals("1") && ("2").equals($setupStatus$)', 'ISRELEASE=$isRelease$ and $spfIdSql$ and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'43C1ED54AFC06D40E0533A06A8C06562', '!$docID$.equals("isnull") && $level$.equals("-1") && ("2").equals($setupStatus$)', 'ISRELEASE=1 and $spfIdSql$ and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'440046DBCADF0ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '8CADB101B53E41C18FC2FA9510D9A7DC', '1', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'856EA4C852644CB2BCEA8DC5A513DFB2', '!$docID$.equals("isnull") && $level$.equals("0") && ("1").equals($setupStatus$)', 'ISRELEASE=$isRelease$ and $spfIdSql$ and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'8D4437DD78124633A8B2DE28FA0844E3', '!$docID$.equals("isnull") && $level$.equals("1") && ("1").equals($setupStatus$)', 'ISRELEASE=$isRelease$ and $spfIdSql$ and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'E80D4532199647CE91F961D61776C30F', '!$docID$.equals("isnull") && $level$.equals("-1") && ("1").equals($setupStatus$)', 'ISRELEASE=1 and $spfIdSql$ and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'49D09FD91EF61DF7E0533A06A8C0984C', '$docID$.equals("isnull") && $level$.equals("0") &&("1").equals($setupStatus$)&&("1").equals($direction$)', 'ISRELEASE=$isRelease$ and $spfIdSql$ and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'49D09FD91EF71DF7E0533A06A8C0984C', '$docID$.equals("isnull") && $level$.equals("1") && ("1").equals($setupStatus$) && ("1").equals($direction$)', 'ISRELEASE=$isRelease$ and $spfIdSql$ and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'49D09FD91EF81DF7E0533A06A8C0984C', '$docID$.equals("isnull") && $level$.equals("1") && ("1").equals($setupStatus$) && ("0").equals($direction$)', 'ISRELEASE=$isRelease$ and $spfIdSql$ and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'4A07867DDF907640E0533A06A8C04B11', '$docID$.equals("isnull") && $level$.equals("-1") && ("1").equals($setupStatus$)&&("0").equals($direction$)', 'ISRELEASE=1 and $spfIdSql$ and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'4A07867DDF917640E0533A06A8C04B11', '$docID$.equals("isnull") && $level$.equals("-1") && ("1").equals($setupStatus$)&&("1").equals($direction$)', 'ISRELEASE=1 and $spfIdSql$ and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'4A07867DDF927640E0533A06A8C04B11', '$docID$.equals("isnull") && $level$.equals("1") && ("2").equals($setupStatus$)&&("0").equals($direction$)', 'ISRELEASE=$isRelease$ and $spfIdSql$ and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'4A07867DDF937640E0533A06A8C04B11', '$docID$.equals("isnull") && $level$.equals("-1") && ("2").equals($setupStatus$)&&("1").equals($direction$)', 'ISRELEASE=1 and $spfIdSql$ and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'4A07A02374AB77DEE0533A06A8C00B24', '$docID$.equals("isnull") && $level$.equals("-1") && ("2").equals($setupStatus$)&&("0").equals($direction$)', 'ISRELEASE=1 and $spfIdSql$ and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'4A07A2E5A35477DCE0533A06A8C0B11B', '$docID$.equals("isnull") && $level$.equals("0") && ("2").equals($setupStatus$)&&("0").equals($direction$)', 'ISRELEASE=$isRelease$ and $spfIdSql$ and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'4A07A2E5A35577DCE0533A06A8C0B11B', '$docID$.equals("isnull") && $level$.equals("0") && ("2").equals($setupStatus$)&&("1").equals($direction$)', 'ISRELEASE=$isRelease$ and $spfIdSql$ and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  insert into P#SPF_T_CONDISET (YEAR, PROVINCE,GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, NOTE, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,'4A07A2E5A35677DCE0533A06A8C0B11B', '$docID$.equals("isnull") && $level$.equals("1") && ("2").equals($setupStatus$)&&("1").equals($direction$)', 'ISRELEASE=$isRelease$ and $spfIdSql$ and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0', null, '1');

  end loop;
    update fasp_t_pubmenu set name ='一级项目备份' where guid ='12000307';
    delete from fasp_t_pubmenu where guid ='3302E92C350CBD3AE0530100007F7598';
end;
--项目发布主列表条件设置
