BEGIN
 delete from P#SPF_T_CONDISET where guid in ('4CE123E81CD2671AE0533A06A8C05126','4CE194BB2B056B75E0533A06A8C0B129');
 for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop
  begin
    
  insert into P#SPF_T_CONDISET (PROVINCE, YEAR, CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
  values (v_row.districtid,v_row.year, '!"1".equals($isdec$)','4CE194BB2B056B75E0533A06A8C0B129', null, 'PROJECTID in ( select PROJECTID from SPF_T_PBASEINFO pp ,code_t_agency_spf cc where pp.AGENCYID=cc.GUID and cc.DISTRICTCODE = DECODE(''$regionCode$'','''',GLOBAL_MULTYear_CZ.Secu_f_Global_Parm(''DIVID''),''$regionCode$'') and cc.code<> DECODE(''$regionCode$'','''',GLOBAL_MULTYear_CZ.Secu_f_Global_Parm(''DIVID''),''$regionCode$'') and cc.LVLID like decode((select LVLID || ''%'' from code_t_agency_spf where guid = ''$treeId$''), null,(select LVLID || ''%'' from code_t_agency_spf where guid=''$UPAGENCYID$''),(select LVLID || ''%'' from code_t_agency_spf where guid = ''$treeId$'')))', '1',(SELECT tableid FROM p#dict_t_model where dealtype='ZHCX*01' and PROVINCE=v_row.districtid and year=v_row.year), '0');

  end;
end loop;

end;

--修改综合查询视图得主列表条件设置
