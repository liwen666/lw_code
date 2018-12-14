
BEGIN
update P#SPF_T_CONDISET set SENTENCES='spfid in (select spfid from $viewName$ where projtypeid  in (select t.projtypeid from $tableName$ t where t.PROJTYPE in(''2'',''3'')) and projectid in (select projectid from $pBaseTableName$ where agencyid = ''$agencyID$''))' where guid='86FAE0265E604A5F84830E8B2A1A3E4D';
update P#SPF_T_CONDISET set SENTENCES='SPFID in (select spfid from spf_t_pbaseinfo where projtypeid in (select t.projtypeid from $tableName$ t where t.PROJTYPE in (''2'',''3'')) and spfid in $usertospf$)' where guid ='984F11F15A6D4E509D2E5CD36A857FCF';
update P#SPF_T_CONDISET set SENTENCES='PROJECTID in ( select PROJECTID from SPF_T_PBASEINFO pp ,code_t_agency_spf cc where pp.AGENCYID=cc.GUID and cc.DISTRICTCODE = DECODE(''$regionCode$'','''',GLOBAL_MULTYear_CZ.Secu_f_Global_Parm(''DIVID''),''$regionCode$'') and cc.LVLID like (select decode(''$treeId$'', '''',(select LVLID || ''%'' from code_t_agency_spf where guid=''$UPAGENCYID$''),(select LVLID || ''%'' from code_t_agency_spf where guid = ''$treeId$''))from dual))' where guid ='4CE194BB2B056B75E0533A06A8C0B129';
END;
--优化综合查询视图得主列表条件设置
