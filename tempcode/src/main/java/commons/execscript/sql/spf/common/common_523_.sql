begin
  -- 项目、二级项目排序URL初始化tableid
  update fasp_t_pubmenu set url ='/spf/project/check/checkMain.do?dealType=5*40'||CHR(38)||'tableId=364E4DE8AA09466C9BC062CED3D12F39'||CHR(38)||'sameSpf=0'||CHR(38)||'step=audit'||CHR(38)||'spfmType=1'||CHR(38)||'btShow={isAudit:''y''}' where guid ='11200203' and appid ='spf';
  update fasp_t_pubmenu set url ='/spf/project/check/checkMain.do?dealType=P*40'||CHR(38)||'tableId=69722A5DAFA64394B6BC8E7F7DDCC99E'||CHR(38)||'sameSpf=0'||CHR(38)||'step=audit'||CHR(38)||'spfmType=1'||CHR(38)||'btShow={isAudit:''y''}' where guid ='12000409' and appid ='spf';

  -- 二级项目审核主列表条件修改
  delete from P#SPF_T_CONDISET where tableid ='7163BDE8CE274EBB9854FA43DDEC5229';
  for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop
    insert into P#SPF_T_CONDISET (YEAR,PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.year, v_row.districtid, '$audit$ != ''0''', '3C734822ADED6555E053CB01A8C0A9AA', null, 'projectid in (select objectid from $flowTableName$  $flowTable$ where $flowWhere$) and checkstatus <> ''0''', '1', '7163BDE8CE274EBB9854FA43DDEC5229', '0');

    insert into P#SPF_T_CONDISET (YEAR,PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.year, v_row.districtid, '$audit$ == ''0''', '3C734822ADEC6555E053CB01A8C0A9AA', null, 'projectid in (select objectid from $flowTableName$  $flowTable$ where $flowWhere$) and checkstatus = ''0'' ', '1', '7163BDE8CE274EBB9854FA43DDEC5229', '0');

    insert into P#SPF_T_CONDISET (YEAR,PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.year, v_row.districtid, '1==0', '440046DBCAE90ADBE0533A06A8C02882', null, '10,20,30,50,80,100,200', '1', '7163BDE8CE274EBB9854FA43DDEC5229', '1');
  end loop;
end;
--项目、二级项目排序URL初始化tableid
