begin
  delete from p#SPF_T_CONDISET where guid in ('4CE194BB2B056B75E0533A06A8C0B129','984F11F15A6D4E509D2E5CD36A857FCF',
      '86FAE0265E604A5F84830E8B2A1A3E4D','5132EA75A78710AEE0533906A8C01B43','5132596163940D82E0533906A8C0AE18','514914BA6EE67AA5E0533906A8C050FF',
      'DA69A5C1B5E54CFD9A985D183AB6343E','524C9FF6789F5883E0533906A8C0A33A' ,'5131583D558B06C5E0533906A8C0F25C','52750D2F22EB117DE0533906A8C0647D');
  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE, CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID, '!''1''.equals($isdec$) '||CHR(38)||''||CHR(38)||' !''''.equals($treeCheck$)', '4CE194BB2B056B75E0533A06A8C0B129', '点击左侧树，刷新右侧列表', 'exists (select 1 from CODE_T_AGENCY_SPF ctas where LVLID like (SELECT LVLID || ''%'' FROM CODE_T_AGENCY_SPF WHERE GUID = ''$treeCheck$'') and TBASE.Agencyid = ctas.GUID )', '1', '36DA6D0F1C750084E0530A700187AB0D', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE, CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID, '$USERID$ != null '||CHR(38)||''||CHR(38)||' !''1''.equals($isdec$)', '984F11F15A6D4E509D2E5CD36A857FCF', '树与右侧列表查询条件', '$usertospf$', '1', '36DA6D0F1C750084E0530A700187AB0D', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE, CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID, '$USERID$ != null '||CHR(38)||''||CHR(38)||' ''1''.equals($isdec$)', '86FAE0265E604A5F84830E8B2A1A3E4D', '申报页面条件', 'agencyID = ''$agencyID$'' AND DISTRICTID = (SELECT GUID FROM CODE_T_DISTRICT_SPF WHERE CODE = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM(''DIVID''))', '1', '36DA6D0F1C750084E0530A700187AB0D', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE, CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID, '$projType$ == ''2'' '||CHR(38)||''||CHR(38)||' !''1''.equals($isdec$)', '5132EA75A78710AEE0533906A8C01B43', '一级项目', 'projtypeid in (select projtypeid from SPF_T_PROJECTTYPE where projtype =''2'')', '1', '36DA6D0F1C750084E0530A700187AB0D', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE, CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID, '$projType$ == ''3'' '||CHR(38)||''||CHR(38)||' !''1''.equals($isdec$)', '5132596163940D82E0533906A8C0AE18', '专项资金', 'projtypeid in (select projtypeid from SPF_T_PROJECTTYPE where projtype =''3'')', '1', '36DA6D0F1C750084E0530A700187AB0D', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE, CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID, '$userType$ == 1 '||CHR(38)||''||CHR(38)||' $projType$ == ''2'' '||CHR(38)||''||CHR(38)||' !''1''.equals($isdec$)', '514914BA6EE67AA5E0533906A8C050FF', '财政用户，并且查询一级项目添加用户对单位', 'agencyID IN (SELECT GUID FROM CODE_T_SECUAGENCY_SPF )', '1', '36DA6D0F1C750084E0530A700187AB0D', '0');

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE, CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID, '1==0', 'DA69A5C1B5E54CFD9A985D183AB6343E', '分页', '10,20,30,50,80,100,200', '1', '36DA6D0F1C750084E0530A700187AB0D', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE, CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID, '!''1''.equals($isdec$)', '524C9FF6789F5883E0533906A8C0A33A', '横加参数当前用户agencyID', 'exists (select guid from CODE_T_AGENCY_SPF ctas where LVLID like (SELECT LVLID || ''%'' FROM CODE_T_AGENCY_SPF WHERE GUID = (select z.superguid from CODE_T_AGENCY_SPF z where guid= ''$upagencyId$'')) and TBASE.Agencyid = ctas.GUID)', '1', '36DA6D0F1C750084E0530A700187AB0D', '0');

	insert into p#SPF_T_CONDISET (YEAR, PROVINCE, CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
	values (v_row.YEAR, v_row.DISTRICTID, '$userType$ == 2', '52750D2F22EB117DE0533906A8C0647D', '部门用户查询当前部门下单位项目', 'exists (select 1 from CODE_T_AGENCY_SPF ctas where ctas.lvlid like (select lvlid||''%'' from CODE_T_AGENCY_SPF where guid =''$upagencyId$'') and TBASE.Agencyid = ctas.GUID)', '1', '36DA6D0F1C750084E0530A700187AB0D', '0');

  end loop;
  
end;
--项目综合查询添加横加单位条件
