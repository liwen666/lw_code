DECLARE
begin
delete p#spf_t_condiset where tableid='8CADB101B53E41C18FC2FA9510D9A7DC';
for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop
  begin
  insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('$docID$.equals("isnull") && $level$.equals("0") &&("1").equals($setupStatus$)&&("0").equals($direction$)',v_row.year, v_row.districtid,'49D09FD91EF51DF7E0533A06A8C0984C', null, 'ISRELEASE=$isRelease$ and spfid in (select spfId from SPF_T_CFLOW_ACT where TARGETAGENCYID=''$upagencyId$'' $busitypeIdSql$) and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('!$docID$.equals("isnull") && $level$.equals("0") && ("2").equals($setupStatus$)', v_row.year, v_row.districtid, '43C1ED54AFBE6D40E0533A06A8C06562', null, 'ISRELEASE=$isRelease$ and spfid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('!$docID$.equals("isnull") && $level$.equals("1") && ("2").equals($setupStatus$)', v_row.year, v_row.districtid, '43C1ED54AFBF6D40E0533A06A8C06562', null, 'ISRELEASE=$isRelease$ and spfid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('!$docID$.equals("isnull") && $level$.equals("-1") && ("2").equals($setupStatus$)', v_row.year, v_row.districtid,'43C1ED54AFC06D40E0533A06A8C06562', null, 'ISRELEASE=1 and spfid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) $firAgencyIdSql$ and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('1==0', v_row.year, v_row.districtid,'440046DBCADF0ADBE0533A06A8C02882', null, '10,20,30,50,80,100,200', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '1');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('!$docID$.equals("isnull") && $level$.equals("0") && ("1").equals($setupStatus$)', v_row.year, v_row.districtid, '856EA4C852644CB2BCEA8DC5A513DFB2', null, 'ISRELEASE=$isRelease$ and spfid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('!$docID$.equals("isnull") && $level$.equals("1") && ("1").equals($setupStatus$)', v_row.year, v_row.districtid,'8D4437DD78124633A8B2DE28FA0844E3', null, 'ISRELEASE=$isRelease$ and spfid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('!$docID$.equals("isnull") && $level$.equals("-1") && ("1").equals($setupStatus$)', v_row.year, v_row.districtid, 'E80D4532199647CE91F961D61776C30F', null, 'ISRELEASE=1 and spfid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) $firAgencyIdSql$ and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('$docID$.equals("isnull") && $level$.equals("0") &&("1").equals($setupStatus$)&&("1").equals($direction$)', v_row.year, v_row.districtid, '49D09FD91EF61DF7E0533A06A8C0984C', null, 'ISRELEASE=$isRelease$ and spfid in (select s.spfid from SPF_T_BFLOW_LOG s,EFM_T_BFLOW_LOG e where e.BATCHID=s.BATCHID and e.AGENCYID =''$upagencyId$'' $busitypeIdSql$) and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('$docID$.equals("isnull") && $level$.equals("1") && ("1").equals($setupStatus$) && ("1").equals($direction$)', v_row.year, v_row.districtid, '49D09FD91EF71DF7E0533A06A8C0984C', null, 'ISRELEASE=$isRelease$ and spfid in (select s.spfid from SPF_T_BFLOW_LOG s,EFM_T_BFLOW_LOG e where e.BATCHID=s.BATCHID and e.AGENCYID =''$upagencyId$'' $busitypeIdSql$) and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('$docID$.equals("isnull") && $level$.equals("1") && ("1").equals($setupStatus$) && ("0").equals($direction$)',v_row.year, v_row.districtid, '49D09FD91EF81DF7E0533A06A8C0984C', null, 'ISRELEASE=$isRelease$ and spfid in (select spfId from SPF_T_CFLOW_ACT where TARGETAGENCYID=''$upagencyId$'' $busitypeIdSql$) and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('$docID$.equals("isnull") && $level$.equals("-1") && ("1").equals($setupStatus$)&&("0").equals($direction$)', v_row.year, v_row.districtid, '4A07867DDF907640E0533A06A8C04B11', null, 'ISRELEASE=1 and spfid in (select spfId from SPF_T_CFLOW_ACT where TARGETAGENCYID=''$upagencyId$'' $busitypeIdSql$) $firAgencyIdSql$ and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('$docID$.equals("isnull") && $level$.equals("-1") && ("1").equals($setupStatus$)&&("1").equals($direction$)', v_row.year, v_row.districtid,'4A07867DDF917640E0533A06A8C04B11', null, 'ISRELEASE=1 and spfid in (select s.spfid from SPF_T_BFLOW_LOG s,EFM_T_BFLOW_LOG e where e.BATCHID=s.BATCHID and e.AGENCYID =''$upagencyId$'' $busitypeIdSql$) $firAgencyIdSql$ and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('$docID$.equals("isnull") && $level$.equals("1") && ("2").equals($setupStatus$)&&("0").equals($direction$)', v_row.year, v_row.districtid, '4A07867DDF927640E0533A06A8C04B11', null, 'ISRELEASE=$isRelease$ and spfid in (select spfId from SPF_T_CFLOW_ACT where TARGETAGENCYID=''$upagencyId$'' $busitypeIdSql$) and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('$docID$.equals("isnull") && $level$.equals("-1") && ("2").equals($setupStatus$)&&("1").equals($direction$)', v_row.year, v_row.districtid, '4A07867DDF937640E0533A06A8C04B11', null, 'ISRELEASE=1 and spfid in (select s.spfid from SPF_T_BFLOW_LOG s,EFM_T_BFLOW_LOG e where e.BATCHID=s.BATCHID and e.AGENCYID =''$upagencyId$'' $busitypeIdSql$) $firAgencyIdSql$ and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('$docID$.equals("isnull") && $level$.equals("-1") && ("2").equals($setupStatus$)&&("0").equals($direction$)', v_row.year, v_row.districtid,'4A07A02374AB77DEE0533A06A8C00B24', null, 'ISRELEASE=1 and spfid in (select spfId from SPF_T_CFLOW_ACT where TARGETAGENCYID=''$upagencyId$'' $busitypeIdSql$) $firAgencyIdSql$ and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('$docID$.equals("isnull") && $level$.equals("0") && ("2").equals($setupStatus$)&&("0").equals($direction$)', v_row.year, v_row.districtid, '4A07A2E5A35477DCE0533A06A8C0B11B', null, 'ISRELEASE=$isRelease$ and spfid in (select spfId from SPF_T_CFLOW_ACT where TARGETAGENCYID=''$upagencyId$'' $busitypeIdSql$) and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('$docID$.equals("isnull") && $level$.equals("0") && ("2").equals($setupStatus$)&&("1").equals($direction$)', v_row.year, v_row.districtid, '4A07A2E5A35577DCE0533A06A8C0B11B', null, 'ISRELEASE=$isRelease$ and spfid in (select s.spfid from SPF_T_BFLOW_LOG s,EFM_T_BFLOW_LOG e where e.BATCHID=s.BATCHID and e.AGENCYID =''$upagencyId$'' $busitypeIdSql$) and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

insert into p#spf_t_condiset (CONDITION,YEAR,PROVINCE, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
values ('$docID$.equals("isnull") && $level$.equals("1") && ("2").equals($setupStatus$)&&("1").equals($direction$)', v_row.year, v_row.districtid, '4A07A2E5A35677DCE0533A06A8C0B11B', null, 'ISRELEASE=$isRelease$ and spfid in (select s.spfid from SPF_T_BFLOW_LOG s,EFM_T_BFLOW_LOG e where e.BATCHID=s.BATCHID and e.AGENCYID =''$upagencyId$'' $busitypeIdSql$) and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '1', '8CADB101B53E41C18FC2FA9510D9A7DC', '0');

  end;
  end loop;
END;--修改主列表条件（项目发布）
