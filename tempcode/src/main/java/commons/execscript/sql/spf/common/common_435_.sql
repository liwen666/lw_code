declare 
viewNumber number;
begin
  delete from P#SPF_T_CONDISET where tableid ='4CEECDD3AF1A497FE0533A06A8C01266';--删除项目综合处理wheresql
  
  delete from P#SPF_T_LISTCONDISET where tableid ='364E4DE8AA09466C9BC062CED3D12F39';--删除项目排序条件
  delete from P#SPF_T_LISTCONDISET where tableid ='69722A5DAFA64394B6BC8E7F7DDCC99E';--删除二级项目排序条件
  
  
  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
    insert into P#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$boolean$ == ''3''', '4CEF8F3B1555503AE0533A06A8C09E23', '点击左侧部门或处室', 'firagencyId = ''$firagencyId$''', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into P#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$boolean$ == ''4''', '4CEF8F3B1556503AE0533A06A8C09E23', '点击左侧单位', 'agencyid = ''$agencyId$''', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into P#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$code$ != '''' && $code$ == ''1''', '4E20AD0968030696E0533A06A8C09B34', '财政当年项目库', 'isbgt = ''1'' and exists (select 1 from (select objectid from spf_t_cflow_act where wfdirection in (''0'',''1'') and targetagencyid in (select districtid from code_t_firagency where iscz = ''1'') union all select objectid from spf_t_cflow_hist where wfdirection in (''0'',''1'') and targetagencyid in (select districtid from code_t_firagency where iscz = ''1'')) t where t.objectid =TBASE.projectid )', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into P#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$code$ != '''' && $code$ == ''2''', '4E20AD0968040696E0533A06A8C09B34', '财政备选项目库', 'isbgt <= ''0'' and exists(select 1 from (select objectid from spf_t_cflow_act where wfdirection in (''0'',''1'') and targetagencyid in (select districtid from code_t_firagency where iscz = ''1'') union all select objectid from spf_t_cflow_hist where wfdirection in (''0'',''1'') and targetagencyid in (select districtid from code_t_firagency where iscz = ''1'')) t where t.objectid =TBASE.projectid )', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into P#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$code$ != '''' && $code$ == ''3''', '4CEF8F3B1559503AE0533A06A8C09E23', '部门项目库', 'exists(select objectid from (select objectid from spf_t_cflow_act where wfdirection in (''0'',''1'') and targetagencyid in (select guid from code_t_firagency) union all select objectid from spf_t_cflow_hist where wfdirection in (''0'',''1'') and targetagencyid in (select guid from code_t_firagency)) t where t.objectid =TBASE.projectid )', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into P#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$code$ != '''' && $code$ == ''4'' && $userType$ != '''' && $userType$ == ''0''', '4CEF8F3B155A503AE0533A06A8C09E23', '单位项目库', 'exists (select 1 from (select projectid from spf_t_pbaseinfo where agencyid = ''$agencyId$'') t where t.projectid =TBASE.projectid )', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into P#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$userType$ == ''0'' && $code$ != '''' && ($code$ == ''1''|| $code$ == ''1'' || $code$ == ''2'' || $code$ == ''3'')', '4E2304FD0AE72253E0533A06A8C0D10B', '单位用户点击当年备选部门项目库', 'exists (select spfid from (select spfid from SECU_T_USERTOSPF where userid= ''$guid$'' union all select spfid from secu_t_roletospf where roleid in(select roleguid from fasp_t_causerrole where userguid = ''$guid$'')) t where t.spfid =TBASE.spfid)', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into P#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$userType$ == ''0'' && $code$ == ''4''', '4CEF8F3B155B503AE0533A06A8C09E23', '单位用户点击单位项目库', 'agencyid =''$agencyId$''', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into P#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'($userType$ == ''1'' || $userType$ == ''2'')', '4CEF8F3B155C503AE0533A06A8C09E23', '财政或部门用户', 'exists (select spfid from (select spfid from SECU_T_USERTOSPF where userid= ''$guid$'' union all select spfid from secu_t_roletospf where roleid in(select roleguid from fasp_t_causerrole where userguid = ''$guid$'')) t where t.spfid =TBASE.spfid)', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into P#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'1 == 1', '4CEF8F3B155D503AE0533A06A8C09E23', '当前用户地区', 'districtid = ''$districtId$''', '1', '4CEECDD3AF1A497FE0533A06A8C01266', null);

    insert into P#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'1==0', '4CEF8F3B155E503AE0533A06A8C09E23', '分页', '20,30,50,80,100,200', '1', '4CEECDD3AF1A497FE0533A06A8C01266', '1');


    --新增项目排序条件
    insert into P#SPF_T_LISTCONDISET (YEAR, PROVINCE, FACTOR, GUID, MODECODE, MODELID, ORDERID, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID,'BFF579A8F345402990152FCA58D22873', '2E92D1213D9B40E7BBBAF3FA8D5F2A55', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 2, '1', '364E4DE8AA09466C9BC062CED3D12F39');

    insert into P#SPF_T_LISTCONDISET (YEAR, PROVINCE, FACTOR, GUID, MODECODE, MODELID, ORDERID, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID,'58A4C14FBFD749889E425AFE0D95943E', '5E559F59CBE04FC6A059E2977716EEC6', 'CODE_T_SP_TYPE', '148830CAF3E61F78E050A8C021056ABE', 1, '1', '364E4DE8AA09466C9BC062CED3D12F39');

    --新增二级项目排序条件修改
    insert into P#SPF_T_LISTCONDISET (YEAR, PROVINCE,FACTOR, GUID, MODECODE, MODELID, ORDERID, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID,'CDDAB6DE4DED4326A0874E3F1D212495', '97AAC40DACB74B90A571AE3E991F2E75', 'CODE_T_SP_TYPE', '148830CAF3E61F78E050A8C021056ABE', 1, '1', '69722A5DAFA64394B6BC8E7F7DDCC99E');

    insert into P#SPF_T_LISTCONDISET (YEAR, PROVINCE,FACTOR, GUID, MODECODE, MODELID, ORDERID, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID,'56FFC5C27C344793B46C6F9D1AFBED6D', 'CDE5EECF629C4F2CB8C7810DC611981E', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 2, '1', '69722A5DAFA64394B6BC8E7F7DDCC99E');

  end loop;
end;

--综合处理条件脚本
