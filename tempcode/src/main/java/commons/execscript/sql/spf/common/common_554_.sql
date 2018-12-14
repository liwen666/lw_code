begin

  delete from p#SPF_T_CONDISET where tableid ='4CEECDD3AF1A497FE0533A06A8C01266';
  
  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
    
    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'!''''.equals($treeCheck$)', '4CEF8F3B1556503AE0533A06A8C09E23', '点击左侧树', 'AGENCYID in(select guid from CODE_T_AGENCY_SPF where LVLID like (SELECT LVLID || ''%'' FROM CODE_T_AGENCY_SPF WHERE GUID = ''$treeCheck$''))', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$code$ != '''' '||CHR(38)||''||CHR(38)||' $code$ == ''1''', '4E20AD0968030696E0533A06A8C09B34', '财政当年预算项目库', 'isbgt = ''1'' and exists (select 1 from (select objectid from spf_t_cflow_act where wfdirection in (''#'',''0'',''1'') and targetagencyid in (select districtid from code_t_firagency where iscz = ''1'') union all select objectid from spf_t_cflow_hist where wfdirection in (''#'',''0'',''1'') and targetagencyid in (select districtid from code_t_firagency where iscz = ''1'')) t where t.objectid =TBASE.projectid )
    and projectid in (select projectid  from EXP_T_PBUDGETTEMP where ispass = 1)', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$code$ != '''' '||CHR(38)||''||CHR(38)||' $code$ == ''2''', '4E20AD0968040696E0533A06A8C09B34', '财政备选项目库', 'isbgt = ''1'' and exists(select 1 from (select objectid from spf_t_cflow_act where wfdirection in (''#'',''0'',''1'') and targetagencyid in (select districtid from code_t_firagency where iscz = ''1'') union all select objectid from spf_t_cflow_hist where wfdirection in (''#'',''0'',''1'') and targetagencyid in (select districtid from code_t_firagency where iscz = ''1'')) t where t.objectid =TBASE.projectid )', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$code$ != '''' '||CHR(38)||''||CHR(38)||' $code$ == ''3''', '4CEF8F3B1559503AE0533A06A8C09E23', '部门项目库', 'exists(select objectid from (select objectid from spf_t_cflow_act where wfdirection in (''#'',''0'',''1'') and targetagencyid in (select guid from code_t_firagency) union all select objectid from spf_t_cflow_hist where wfdirection in (''#'',''0'',''1'') and targetagencyid in (select guid from code_t_firagency)) t where t.objectid =TBASE.projectid )', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$code$ != '''' '||CHR(38)||''||CHR(38)||' $code$ == ''4'' '||CHR(38)||''||CHR(38)||' $userType$ != '''' '||CHR(38)||''||CHR(38)||' $userType$ == ''0''', '4CEF8F3B155A503AE0533A06A8C09E23', '单位项目库', 'exists (select 1 from (select projectid from spf_t_pbaseinfo where agencyid = ''$agencyId$'') t where t.projectid =TBASE.projectid )', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$userType$ == ''0'' '||CHR(38)||''||CHR(38)||' $code$ != '''' '||CHR(38)||''||CHR(38)||' ($code$ == ''1'' || $code$ == ''2'' || $code$ == ''3'')', '4E2304FD0AE72253E0533A06A8C0D10B', '单位用户点击当年备选部门项目库', 'exists (select spfid from (select spfid from SECU_T_USERTOSPF where userid= ''$guid$'' union all select spfid from secu_t_roletospf where roleid in(select roleguid from fasp_t_causerrole where userguid = ''$guid$'')) t where t.spfid =TBASE.spfid)', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$userType$ == ''0'' '||CHR(38)||''||CHR(38)||' $code$ == ''4''', '4CEF8F3B155B503AE0533A06A8C09E23', '单位用户点击单位项目库', 'agencyid =''$agencyId$''', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'($userType$ == ''1'' || $userType$ == ''2'')', '4CEF8F3B155C503AE0533A06A8C09E23', '财政或部门用户', 'exists (select spfid from (select spfid from SECU_T_USERTOSPF where userid= ''$guid$'' union all select spfid from secu_t_roletospf where roleid in(select roleguid from fasp_t_causerrole where userguid = ''$guid$'')) t where t.spfid =TBASE.spfid)', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'1 == 1', '4CEF8F3B155D503AE0533A06A8C09E23', '当前用户地区', 'districtid = ''$districtId$''', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'1==0', '4CEF8F3B155E503AE0533A06A8C09E23', '分页', '20,30,50,80,100,200', '1', '4CEECDD3AF1A497FE0533A06A8C01266', '1');
  end loop;
  
end;
--项目综合处理(查询SQL修改)
