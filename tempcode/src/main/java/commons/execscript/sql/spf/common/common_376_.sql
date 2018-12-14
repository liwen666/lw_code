DECLARE
BEGIN
delete from P#SPF_T_CONDISET;
 for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop
  begin
insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('00573CCABBD64B1B95932B48B0BF4497', '1==1', 'SPFID IN(SELECT SPFID FROM $dbtablename_spf$ WHERE SETUPSTATUS = ''2'' ) AND PROJECTID IN(SELECT PROJECTID FROM $dbtablenameplan$ P WHERE P.PROJECTID = PROJECTID AND P.ISPLANVALID <> 0 AND (CHECKSTATUS = ''1'' OR CHECKSTATUS = ''2'')) AND PROJECTID IN (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$)', 'EEEE82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('027E082638A444B79E64FC8C2FA8081A', '$level$==''1''', 'SPFID in (SELECT SPFID FROM SPF_T_FBASEINFO WHERE FIRAGENCYID =substr(''$treeId$'',2))', 'DFF8A9CC1C064317B79DB0F09459ECDF', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('051C239546FB44DAA4C2E94D8D9D5F30', '$auditTabType$==''1''', 'EXISTS(SELECT 1 FROM spf_t_oarelation B WHERE B.TASKID = PROJECTID AND DOCID =''$docID$'')
AND  EXISTS(SELECT 1 FROM $auditTableName$ B WHERE B.DATAID = PROJECTID AND DOCID =''$docID$'')', '76512FBCCC8E4134820BC1DDD44AB684', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('05E9C05F948444359216E444681A9782', '1=2', '', '', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('0807569754DF4E8CA0850DAA565C88BA', '$audit$ == ''0''', 'spfid in (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$) AND SETUPSTATUS = ''0'' ', '79592CE1019A436EA6B261AFD4999711', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('0D0DC7093F09443490909344DFF8B8A2', '1==1', 'spfid in( select spfid from $dbtablename_spf$ where setupstatus = ''2'') and projectid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$)', 'DDDD82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('0EE682C6B4B145958DE78C21AC32F50B', '$commonUserType$==''2''', 'FIRAGENCYID =''$commonAgencyId$''', '76512FBCCC8E4134820BC1DDD44AB684', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('12D5AE652EE3496B827C5E4ED2A13AE3', '$commonUserType$==''0''', 'AGENCYID=''$commonAgencyId$''', '76512FBCCC8E4134820BC1DDD44AB684', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('15BCCBE9385D41A7ACA1224F62A76BD7', '$selectTabType$==''0''', 'NOT EXISTS(SELECT 1 FROM spf_t_oarelation B WHERE B.TASKID = PROJECTID AND DOCID =''$docID$''  )', '76512FBCCC8E4134820BC1DDD44AB684', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('15F2EE6BE8D941F492CE819AD3FCD5A2', '$setup$ ==0', 'exists (select 1 from SPF_T_OARELATIONCOPY where taskid=projectid and status =''1''and docid=''$docId$'')', '71B119DA84CE404C8C3428F234D8D509', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('17E4538BFF1B44B2A13DCC5576F53829', '$inputTabType$==''0''', 'EXISTS(SELECT 1 FROM SPF_T_OARELATION B WHERE B.TASKID = PROJECTID AND DOCID =''$docID$'')', '76512FBCCC8E4134820BC1DDD44AB684', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('1C9D5713D62841EEB921A1C3366E9BBD', '$checkStatus$ == ''0'' && $listLength$ ==''0''', 'projectid in (SELECT TASKID FROM SPF_T_CHOOSEMID WHERE DOCID = BUSIID AND DOCID = ''$docID$'')', '3AF76701EA4B4DB9E053CB01A8C01673', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('1DC215610F31494AA53CF7872103F401', '1==1', 'SPFID in(SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$)', '2D30A2ABFA1FF6DEE050A8C021050AD0', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('1E0FB0E4BBA245C584912CAD8BF0DA0E', '$checkStatus$ == ''0'' && $listLength$ == ''1'' && $spfmType$ != ''1''', 'PROJECTID IN (SELECT PROJECTID FROM SPF_T_PBASEINFO C WHERE C.PROJTYPEID IN (SELECT PROJTYPEID FROM SPF_T_PROJECTTYPE WHERE PROJTYPE = ''2'')) AND (DOCID IN ($listDoc$) OR EXISTS (SELECT 1 FROM SPF_T_CHOOSEMID WHERE DOCID = BUSIID AND DOCID = ''$docID$'')) AND PROJECTID NOT IN (SELECT taskid FROM SPF_T_OARELATION WHERE DOCID = ''$docID$'' AND TASKTYPE = ''1'')', '3AF76701EA4B4DB9E053CB01A8C01673', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('203DE7D368434325A8F62AB207668029', '$commonUserType$==''1''', 'DEPTID =''$commonAgencyId$''', '76512FBCCC8E4134820BC1DDD44AB684', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('CC92A08491AE4EDFBD307C89CB8CEB8D', '1=3', '', '', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('20F06028116C4066826651F0CD468FD3', '$checkStatus$ != ''0'' && $spfmType$ != ''1''', 'EXISTS (SELECT 1 FROM SPF_T_OARELATION WHERE TASKID=PROJECTID AND DOCID=''$docID$'') AND PROJECTID IN (SELECT PROJECTID FROM SPF_T_PBASEINFO C WHERE C.PROJTYPEID IN (SELECT PROJTYPEID FROM SPF_T_PROJECTTYPE WHERE PROJTYPE = ''2''))', '3AF76701EA4B4DB9E053CB01A8C01673', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('25E1076FA346407B84D8E2CF36CE5390', '$step$==''projectAdjust'' || $step$==''projectAdjustAudit''', '(CHECKSTATUS = ''1'' or CHECKSTATUS = ''2'') AND PROJECTID in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ left join SPF_T_PADJUSTSTATUS status on $flowTable$.OBJECTID =status.PROJECTID WHERE $flowWhere$ and status.ADJUSTSTATUS in ($STATUS$) and $flowTable$.STEP = ''adjust'')', '6308DB25C4BB449FA5E1E98B0ADBDACB', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('2680E1CC2A88404E838D1A0F81194784', '$step$==''adjust''||$step$==''adjustaudit''', '(SETUPSTATUS = ''1'' or SETUPSTATUS = ''2'') and ADJUSTSTATUS in ($STATUS$) and spfid in (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$)', 'E73347B088B4461883786B6B69A44273', '',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('26B8E008586143908A060755A4F00AE8', '$projmType$==null||$projmType$ !=1', 'and PROJTYPEID IN(SELECT PROJTYPEID FROM spf_t_projecttype WHERE projtype =''2'')', '71B119DA84CE404C8C3428F234D8D509', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('26CC00CD4EEE430A9AA604BB92A19DA4', '$INSTEAD$ != null && $INSTEAD$ ==''1''', 'ISINSTEAD =''$INSTEAD$'' AND CREATEUSER = ''$USERID$''', '783B60D3816D469A93D48F6C6DDF3917', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('3060614CE334441FA7CDA24758F811B1', '$isBgt$==''0''', 'PROJECTID in (select distinct p.PROJECTID FROM $dbtablenameplan$ p,spf_t_pbaseinfo p01 WHERE p.projectid=p01.projectid and ( p01.isadd=''0'' or p01.isadd is null ) and p.projectid = TBASE.PROJECTID and p.ISPLANVALID<>0 )and (CHECKSTATUS = ''1'' or CHECKSTATUS = ''2'') and (isbgt=''$isBgt$'' or isbgt is null)', 'AAAA82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('328B89E48B604F53B35D2237C9902285', '$isBgt$!=''0''', 'PROJECTID in(select PROJECTID FROM $dbtablenameplan$ p WHERE p.projectid = PROJECTID and p.ISPLANVALID<>0 )and (CHECKSTATUS = ''1'' or CHECKSTATUS = ''2'') and isbgt=''$isBgt$''', 'AAAA82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('396DCFBDA0F44C1ABB8F1D6CC1126107', '$USERTYPE$ == ''0''', 'firagencyId = ''$UPAGENCYID$''', '0339012C49674571A9DCA0A8744FC94E', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('3C4C63F491FC6AEDE053CB01A8C07DBE', '$audit$ == ''0''', 'projectid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) and checkstatus = ''0''', 'B375DD5D93E44B99A28F2B7A99C4A7CA', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('3C4C63F491FD6AEDE053CB01A8C07DBE', '$audit$ != ''0''', 'projectid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) and checkstatus <> ''0''', 'B375DD5D93E44B99A28F2B7A99C4A7CA', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('3C6F083E7EAC49C6E053CB01A8C09ACB', '$audit$ == ''0''', 'spfid in (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$) AND SETUPSTATUS = ''0''', '2F418CA4DD24F444E050A8C02105683B', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('3C6F083E7EAD49C6E053CB01A8C09ACB', '$audit$ !=''0''', 'spfid in (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$) AND SETUPSTATUS != ''0''', '2F418CA4DD24F444E050A8C02105683B', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('3C6F18047DBB4AA4E053CB01A8C06500', '1 == 1', 'SPFID in(SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$)', '83C77CBCACBE459DB98F6026150E65F2', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('3C6F7A82A32F4D67E053CB01A8C0DED6', '1==1', 'SPFID in(SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$)', 'AD6EE9C7B42842F1BC8348C3FBE5583F', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('3C734822ADEC6555E053CB01A8C0A9AA', '$audit$ == ''0''', 'projectid in (select objectid from $flowTableName$  $flowTable$ where $flowWhere$) and checkstatus = ''0'' ', '7163BDE8CE274EBB9854FA43DDEC5229', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('3C734822ADED6555E053CB01A8C0A9AA', '$audit$ <> ''0''', 'projectid in (select objectid from $flowTableName$  $flowTable$ where $flowWhere$) and checkstatus != ''0''', '7163BDE8CE274EBB9854FA43DDEC5229', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('3CD3D1ECA7AF1824E053CB01A8C0F6E0', '$step$==''adjust''||$step$==''adjustaudit''', '(SETUPSTATUS = ''1'' or SETUPSTATUS = ''2'') and ADJUSTSTATUS in ($STATUS$) and spfid in (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$)', '0339012C49674571A9DCA0A8744FC94E', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('3CD3D1ECA7B31824E053CB01A8C0F6E0', '$step$==''projectAdjust''||$step$==''projectAdjustAudit''', '(CHECKSTATUS = ''1'' or CHECKSTATUS = ''2'') AND PROJECTID in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ left join SPF_T_PADJUSTSTATUS status on $flowTable$.OBJECTID =status.PROJECTID WHERE $flowWhere$ and status.ADJUSTSTATUS in ($STATUS$) and $flowTable$.STEP = ''adjust'')', '4F0473A1473646FB9C086F7D5CACD03F', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('3EE399B9A7F44A12839946C05585BF9E', '$dbtablename_proj$!=null && $dbtablename_proj$!=""', 'spfid in (select spfid from $dbtablename_proj$ stp where stp.CREATEUSER =''$USERID$'' and CHECKSTATUS=''0'')', '59011EB26787484DB6CAED40F62D79D9', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C04AAEE7F360B5E0533A06A8C09B3F', '$setup$ ==0', ' exists (select 1 from SPF_T_OARELATIONCOPY where taskid=spfid and status =''1''and docid=''$docId$'')', '101662280BB64532A4BA9C86ADE04E82', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C04AAEE7F460B5E0533A06A8C09B3F', '$setup$ ==1', ' exists (select 1 from spf_t_oarelation where taskid=spfid and docid= ''$docId$'')', '101662280BB64532A4BA9C86ADE04E82', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C04AAEE7F760B5E0533A06A8C09B3F', '$setup$ ==1', 'exists (select 1 from spf_t_oarelation where taskid=projectid and docid= ''$docId$'')', '5B90D1A8546C45D086866831D9A7D04D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C04AAEE7F860B5E0533A06A8C09B3F', '$setup$ ==0', 'exists (select 1 from SPF_T_OARELATIONCOPY where taskid=projectid and status =''1''and docid=''$docId$'')', '5B90D1A8546C45D086866831D9A7D04D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C168A8A8CE6971E0533A06A8C07E76', '$isIndex$==''0''', 'ISINDEX = ''0''', 'BBBB82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C16B6A26DC6987E0533A06A8C0C977', '$step$=="projbgtadjust"', 'CHECKSTATUS=''1'' and (isbgt=1 or isindex=1) and projectid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$ and $flowTable$.STEP = ''projbgtadjust'' intersect SELECT projectid FROM SPF_T_PADJUSTSTATUS where bgtadjuststatus!=''1'')', 'CCCC82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C16B6A26E06987E0533A06A8C0C977', '$isIndex$==''0''', 'ISINDEX = ''0''', 'EEEE82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C1ED54AFBE6D40E0533A06A8C06562', '!$docID$.equals("isnull") && $level$.equals("0") && ("2").equals($setupStatus$)', 'ISRELEASE=$isRelease$ and spfid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C1ED54AFBF6D40E0533A06A8C06562', '!$docID$.equals("isnull") && $level$.equals("1") && ("2").equals($setupStatus$)', 'ISRELEASE=$isRelease$ and spfid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C1ED54AFC06D40E0533A06A8C06562', '!$docID$.equals("isnull") && $level$.equals("-1") && ("2").equals($setupStatus$)', 'ISRELEASE=1 and spfid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) and FIRAGENCYID in ($firagenceId$) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C1ED54AFC26D40E0533A06A8C06562', '$USERID$!=null && !"1".equals($isdec$)', 'SPFID in (select spfid from $viewName$ where projectid != spfid and projtypeid not in (select t.projtypeid from $tableName$ t where t.PROJTYPE = ''9'') and spfid in $usertospf$)', '3E6C3815739B13F3E053CB01A8C0F3A2', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C1ED54AFC36D40E0533A06A8C06562', '$USERID$!=null && "1".equals($isdec$)', 'spfid in (select spfid
                           from $viewName$
                          where projtypeid not in
                                (select t.projtypeid
                                   from $tableName$ t
                                  where t.PROJTYPE = ''9'')
                            and projectid in
                                (select projectid
                                   from $pBaseTableName$
                                  where agencyid = ''$agencyID$''))', '3E6C3815739B13F3E053CB01A8C0F3A2', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C62FD499D30EADE0533A06A8C0BCBD', '$setup$ ==0', ' exists (select 1 from SPF_T_OARELATIONCOPY where taskid=spfid and status =''1''and docid=''$docId$'')', '2C58567F7CB749EF9F993BE1162D15F8', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43C62FD499D40EADE0533A06A8C0BCBD', '$setup$ ==1', ' exists (select 1 from spf_t_oarelation where taskid=spfid and docid= ''$docId$'')', '2C58567F7CB749EF9F993BE1162D15F8', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43FDC1682CE8768DE0533A06A8C0DF8D', '$setup$ ==1', 'exists (select 1 from spf_t_oarelation where taskid=projectid and docid= ''$docId$'')', '76659007F21F4CF780E1E7CA9035D504', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('43FDC1682CE9768DE0533A06A8C0DF8D', '$setup$ ==0', 'exists (select 1 from SPF_T_OARELATIONCOPY where taskid=projectid and status =''1''and docid=''$docId$'')', '76659007F21F4CF780E1E7CA9035D504', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCADF0ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '8CADB101B53E41C18FC2FA9510D9A7DC', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAE00ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '76512FBCCC8E4134820BC1DDD44AB684', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAE10ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '2D30A2ABFA1FF6DEE050A8C021050AD0', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAE20ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'AAAA82614F624DEF84E88BE30959468D', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAE30ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'B375DD5D93E44B99A28F2B7A99C4A7CA', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAE40ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '101662280BB64532A4BA9C86ADE04E82', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAE60ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '76659007F21F4CF780E1E7CA9035D504', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAE70ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'ABD8B909C1E54D12B6FBDBB84F962639', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAE80ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '79592CE1019A436EA6B261AFD4999711', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAE90ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '7163BDE8CE274EBB9854FA43DDEC5229', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAEC0ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'FD3E1F0B0B274C3A85C683910498F228', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAED0ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '3E6C3815739B13F3E053CB01A8C0F3A2', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAEE0ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '3AF76701EA4B4DB9E053CB01A8C01673', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAEF0ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'E73347B088B4461883786B6B69A44273', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAF00ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '4F0473A1473646FB9C086F7D5CACD03F', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAF20ADBE0533A06A8C02882', '1==0', '20,30,50,80,100,200', 'F7C195DD3FA44D3EA2843A08255E389A', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAF30ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'B3A5CFF14F594C619A653A658D0A05FF', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAF40ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'DDDD82614F624DEF84E88BE30959468D', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAF50ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '59011EB26787484DB6CAED40F62D79D9', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAF60ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'CCCC82614F624DEF84E88BE30959468D', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAF70ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'F9CB8D34BC3263AEE040A8C021050BA8', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAF80ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'DFF8A9CC1C064317B79DB0F09459ECDF', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAF90ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '1F66766D2AC349F89DAC15D97F9BC7D0', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAFB0ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '6308DB25C4BB449FA5E1E98B0ADBDACB', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAFC0ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '783B60D3816D469A93D48F6C6DDF3917', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAFD0ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'BBBB82614F624DEF84E88BE30959468D', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCAFE0ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '2F418CA4DD24F444E050A8C02105683B', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCB000ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'EFF66CD4720A44F89C4C0BADAC8FEA46', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCB010ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'EEEE82614F624DEF84E88BE30959468D', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCB020ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '71B119DA84CE404C8C3428F234D8D509', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCB030ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '83C77CBCACBE459DB98F6026150E65F2', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCB040ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '0339012C49674571A9DCA0A8744FC94E', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCB050ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'AD6EE9C7B42842F1BC8348C3FBE5583F', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCB060ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '5B90D1A8546C45D086866831D9A7D04D', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCB080ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', '2C58567F7CB749EF9F993BE1162D15F8', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('440046DBCB090ADBE0533A06A8C02882', '1==0', '10,20,30,50,80,100,200', 'C773F13BD91D447E9E3A95D1A92D35C3', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('443A1932A9043952E0533A06A8C0697F', '$checkStatus$ == ''0''', 'EXISTS (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$)', '44396A156191314CE0533A06A8C09BDE', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('443A1932A9053952E0533A06A8C0697F', '$checkStatus$ != ''0''', 'EXISTS (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$)', '44396A156191314CE0533A06A8C09BDE', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('443AD6E31B513BE0E0533A06A8C07AC0', '$checkStatus$ == ''0''', 'projectid in(select projectid from SPF_T_PAUDITMAIN where projectid in (SELECT TASKID FROM SPF_T_CHOOSEMID WHERE TASKID=PROJECTID AND DOCID=''$docID$''))', '443A19F171013773E0533A06A8C034C8', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('443AD6E31B523BE0E0533A06A8C07AC0', '$checkStatus$ != ''0''', 'projectid in(select projectid from SPF_T_PAUDITMAIN where projectid in (SELECT TASKID FROM SPF_T_OARELATION WHERE TASKID=PROJECTID AND DOCID=''$docID$'' AND TASKTYPE = ''1''))', '443A19F171013773E0533A06A8C034C8', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('443AD6E31B533BE0E0533A06A8C07AC0', '1==0', '10,20,30,50,80,100,200', '443A19F171013773E0533A06A8C034C8', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('443AD6E31B543BE0E0533A06A8C07AC0', '1==0', '10,20,30,50,80,100,200', '44396A156191314CE0533A06A8C09BDE', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4740FA5835BC423FAC6BC29F749BF4B4', '$DISTRICTID$!=null&&$DISTRICTID$!=""', 'DISTRICTID=''$DISTRICTID$''', '1F66766D2AC349F89DAC15D97F9BC7D0', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('49CCC4AA3EDF44A1BB362BB2B88810B5', '1 == 1', 'PROJECTID in (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$)', 'FD3E1F0B0B274C3A85C683910498F228', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('49D09FD91EF51DF7E0533A06A8C0984C', '$docID$.equals("isnull") && $level$.equals("0") &&("1").equals($setupStatus$)&&("0").equals($direction$)', 'ISRELEASE=$isRelease$ and spfid in (select spfId from SPF_T_CFLOW_ACT where TARGETAGENCYID=''$upagencyId$'' and BUSITYPEID =''$busiTypeID$'') and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('49D09FD91EF61DF7E0533A06A8C0984C', '$docID$.equals("isnull") && $level$.equals("0") &&("1").equals($setupStatus$)&&("1").equals($direction$)', 'ISRELEASE=$isRelease$ and spfid in (select s.spfid from SPF_T_BFLOW_LOG s,EFM_T_BFLOW_LOG e where e.BATCHID=s.BATCHID and e.AGENCYID =''$upagencyId$'' and e.BUSITYPEID =''$busiTypeID$'') and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('49D09FD91EF71DF7E0533A06A8C0984C', '$docID$.equals("isnull") && $level$.equals("1") && ("1").equals($setupStatus$) && ("1").equals($direction$)', 'ISRELEASE=$isRelease$ and spfid in (select s.spfid from SPF_T_BFLOW_LOG s,EFM_T_BFLOW_LOG e where e.BATCHID=s.BATCHID and e.AGENCYID =''$upagencyId$'' and e.BUSITYPEID =''$busiTypeID$'') and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('49D09FD91EF81DF7E0533A06A8C0984C', '$docID$.equals("isnull") && $level$.equals("1") && ("1").equals($setupStatus$) && ("0").equals($direction$)', 'ISRELEASE=$isRelease$ and spfid in (select spfId from SPF_T_CFLOW_ACT where TARGETAGENCYID=''$upagencyId$'' and BUSITYPEID =''$busiTypeID$'') and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4A07045398837165E0533A06A8C01EA9', '$step$=="projbgtadjustaudit"', 'CHECKSTATUS=''1'' and (isbgt=1 or isindex=1) and projectid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$ and $flowTable$.STEP = ''projbgtadjust'' intersect SELECT projectid FROM SPF_T_PADJUSTSTATUS where bgtadjuststatus=''1'')', 'CCCC82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4A07867DDF907640E0533A06A8C04B11', '$docID$.equals("isnull") && $level$.equals("-1") && ("1").equals($setupStatus$)&&("0").equals($direction$)', 'ISRELEASE=1 and spfid in (select spfId from SPF_T_CFLOW_ACT where TARGETAGENCYID=''$upagencyId$'' and BUSITYPEID =''$busiTypeID$'') and FIRAGENCYID in ($firagenceId$) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4A07867DDF917640E0533A06A8C04B11', '$docID$.equals("isnull") && $level$.equals("-1") && ("1").equals($setupStatus$)&&("1").equals($direction$)', 'ISRELEASE=1 and spfid in (select s.spfid from SPF_T_BFLOW_LOG s,EFM_T_BFLOW_LOG e where e.BATCHID=s.BATCHID and e.AGENCYID =''$upagencyId$'' and e.BUSITYPEID =''$busiTypeID$'') and FIRAGENCYID in ($firagenceId$) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4A07867DDF927640E0533A06A8C04B11', '$docID$.equals("isnull") && $level$.equals("1") && ("2").equals($setupStatus$)&&("0").equals($direction$)', 'ISRELEASE=$isRelease$ and spfid in (select spfId from SPF_T_CFLOW_ACT where TARGETAGENCYID=''$upagencyId$'' and BUSITYPEID =''$busiTypeID$'') and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4AF561CD606548848BF7F0C9C7758ECD', '$projmType$!=null&&$projmType$ ==1', 'PROJTYPEID IN(SELECT PROJTYPEID FROM spf_t_projecttype WHERE projtype =''3'')', '71B119DA84CE404C8C3428F234D8D509', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4A07867DDF937640E0533A06A8C04B11', '$docID$.equals("isnull") && $level$.equals("-1") && ("2").equals($setupStatus$)&&("1").equals($direction$)', 'ISRELEASE=1 and spfid in (select s.spfid from SPF_T_BFLOW_LOG s,EFM_T_BFLOW_LOG e where e.BATCHID=s.BATCHID and e.AGENCYID =''$upagencyId$'' and e.BUSITYPEID =''$busiTypeID$'') and FIRAGENCYID in ($firagenceId$) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4A07A02374AB77DEE0533A06A8C00B24', '$docID$.equals("isnull") && $level$.equals("-1") && ("2").equals($setupStatus$)&&("0").equals($direction$)', 'ISRELEASE=1 and spfid in (select spfId from SPF_T_CFLOW_ACT where TARGETAGENCYID=''$upagencyId$'' and BUSITYPEID =''$busiTypeID$'') and FIRAGENCYID in ($firagenceId$) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4A07A2E5A35477DCE0533A06A8C0B11B', '$docID$.equals("isnull") && $level$.equals("0") && ("2").equals($setupStatus$)&&("0").equals($direction$)', 'ISRELEASE=$isRelease$ and spfid in (select spfId from SPF_T_CFLOW_ACT where TARGETAGENCYID=''$upagencyId$'' and BUSITYPEID =''$busiTypeID$'') and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4A07A2E5A35577DCE0533A06A8C0B11B', '$docID$.equals("isnull") && $level$.equals("0") && ("2").equals($setupStatus$)&&("1").equals($direction$)', 'ISRELEASE=$isRelease$ and spfid in (select s.spfid from SPF_T_BFLOW_LOG s,EFM_T_BFLOW_LOG e where e.BATCHID=s.BATCHID and e.AGENCYID =''$upagencyId$'' and e.BUSITYPEID =''$busiTypeID$'') and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4A07A2E5A35677DCE0533A06A8C0B11B', '$docID$.equals("isnull") && $level$.equals("1") && ("2").equals($setupStatus$)&&("1").equals($direction$)', 'ISRELEASE=$isRelease$ and spfid in (select s.spfid from SPF_T_BFLOW_LOG s,EFM_T_BFLOW_LOG e where e.BATCHID=s.BATCHID and e.AGENCYID =''$upagencyId$'' and e.BUSITYPEID =''$busiTypeID$'') and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4BC710A524A25F65E0533A06A8C0E9B4', '1==0', '20,30,50,80,100,200', '4BB14D1664652F7CE0533A06A8C0BDC1', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4BD621CCF63A507BE0533A06A8C0F4F1', '1 == 1', 'SPFID in (SELECT SPFID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$) ', '4BD5A26754354AD7E0533A06A8C04D54', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4BDB90882A770575E0533A06A8C0E44A', '$step$=="projbgtadjust"', 'CHECKSTATUS=''1'' and (isbgt=1 or isindex=1) and projectid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$   intersect SELECT projectid FROM SPF_T_PADJUSTSTATUS where bgtadjuststatus!=''1'')', 'FFFF82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4BDB90882A780575E0533A06A8C0E44A', '$step$=="projbgtadjustaudit"', 'CHECKSTATUS=''1'' and (isbgt=1 or isindex=1) and projectid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$  intersect SELECT projectid FROM SPF_T_PADJUSTSTATUS where bgtadjuststatus=''1'')', 'FFFF82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4BDB90882A790575E0533A06A8C0E44A', '1==0', '10,20,30,50,80,100,200', 'FFFF82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4C11F3747F3E4A79E0533A06A8C02C26', '1==0', '10,20,30,50,80,100,200', '4BD5A26754354AD7E0533A06A8C04D54', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4C7DD26CD58912ABE0533A06A8C04C49', '$code$ != '''' && $code$ == ''4''', 'projectid in (select projectid from spf_t_pbaseinfo) and agencyid = ''$agencyId$''', '4BB14D1664652F7CE0533A06A8C0BDC1', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4C8A73D14FA36701E0533A06A8C030BA', '$userType$ == ''0''', 'agencyid =''$agencyId$''', '4BB14D1664652F7CE0533A06A8C0BDC1', '',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4C7C6DE6F829034DE0533A06A8C0420C', '($userType$ == ''1'' || $userType$ == ''2'') && $code$ != ''4''', 'spfid in (select spfid from SECU_T_USERTOSPF where userid= ''$guid$'' union select spfid from secu_t_roletospf where roleid in(select roleguid from fasp_t_causerrole where userguid = ''$guid$''))', '4BB14D1664652F7CE0533A06A8C0BDC1', '',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4C7C7D7FF4AF0349E0533A06A8C0D3AB', '$code$ != '''' && $code$ == ''1''', 'isbgt = ''1'' and projectid in (select objectid from spf_t_cflow_act where spfid <> objectid and wfdirection not in (''2'', ''3'') and targetagencyid in (select guid from code_t_firagency where iscz = ''1'') union select objectid from spf_t_cflow_hist where spfid <> objectid and wfdirection not in (''2'', ''3'') and targetagencyid in (select guid from code_t_firagency where iscz = ''1''))', '4BB14D1664652F7CE0533A06A8C0BDC1', '',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4C7DC628E46C12A9E0533A06A8C0120F', '$code$ != '''' && $code$ == ''2''', '(isbgt <> ''1'' or isbgt is null) and projectid in (select objectid from spf_t_cflow_act where spfid <> objectid and wfdirection not in (''2'', ''3'') and targetagencyid in (select guid from code_t_firagency where iscz = ''1'') union select objectid from spf_t_cflow_hist where spfid <> objectid and wfdirection not in (''2'', ''3'') and targetagencyid in (select guid from code_t_firagency where iscz = ''1''))', '4BB14D1664652F7CE0533A06A8C0BDC1', '',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4C8A73D14FA26701E0533A06A8C030BA', '$code$ != '''' && $code$ == ''3''', 'projectid in (select objectid from spf_t_cflow_act where spfid <> objectid and wfdirection not in (''2'', ''3'') and targetagencyid in (select guid from code_t_firagency) union select objectid from spf_t_cflow_hist where spfid <> objectid and wfdirection not in (''2'', ''3'') and targetagencyid in (select guid from code_t_firagency))', '4BB14D1664652F7CE0533A06A8C0BDC1', '',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4C8CFC3676A40261E0533A06A8C0E00C', '1 == 1', 'districtid = ''$districtId$''', '4BB14D1664652F7CE0533A06A8C0BDC1', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4C90CC1E44732ACFE0533A06A8C0564D', '$boolean$ == ''4''', 'agencyid = ''$agencyId$''', '4BB14D1664652F7CE0533A06A8C0BDC1', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4C911CD0C60A2D48E0533A06A8C0FE8C', '$boolean$ == ''3''', 'firagencyId = ''$firagencyId$''', '4BB14D1664652F7CE0533A06A8C0BDC1', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4CE123E81CD2671AE0533A06A8C05126', '$USERTYPE$ =="1" && !"1".equals($isdec$)', 'PROJECTID in ( SELECT pp.PROJECTID
FROM SPF_T_PBASEINFO pp
where pp.AGENCYID in (
      select guid from code_t_agency_spf cc
      where cc.DISTRICTCODE = DECODE(''$regionCode$'','''',GLOBAL_MULTYear_CZ.Secu_f_Global_Parm(''DIVID''),''$regionCode$'')
      and cc.code<> DECODE(''$regionCode$'','''',GLOBAL_MULTYear_CZ.Secu_f_Global_Parm(''DIVID''),''$regionCode$'')
      and cc.LVLID like decode((select LVLID || ''%'' from code_t_agency_spf where guid = ''$treeId$''), null,''%%'',(select LVLID || ''%'' from code_t_agency_spf where guid = ''$treeId$''))                             
      START WITH guid in (SELECT agencyid FROM secu_t_agency ss where manId = ''$USERID$'' and ss.APPID = ''SPF'' and ss.manType = ''0'')
      CONNECT BY SUPERGUID = PRIOR GUID
))', '38AA7C84397D001AE0530A4405064AEC', '',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4CE194BB2B056B75E0533A06A8C0B129', '$USERTYPE$ !="1" && !"1".equals($isdec$)', 'PROJECTID in ( select PROJECTID from SPF_T_PBASEINFO pp ,code_t_agency_spf cc where pp.AGENCYID=cc.GUID and cc.LVLID like (select LVLID from code_t_agency_spf where guid=''$UPAGENCYID$'') || ''%'')', '38AA7C84397D001AE0530A4405064AEC', '',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4CEF8F3B1555503AE0533A06A8C09E23', '$boolean$ == ''3''', 'firagencyId = ''$firagencyId$''', '4CEECDD3AF1A497FE0533A06A8C01266', '',  v_row.districtid,v_row.year, '1', '点击部门或处室');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4CEF8F3B1556503AE0533A06A8C09E23', '$boolean$ == ''4''', 'agencyid = ''$agencyId$''', '4CEECDD3AF1A497FE0533A06A8C01266', '',  v_row.districtid,v_row.year, '1', '点击单位');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4CEF8F3B155B503AE0533A06A8C09E23', '$userType$ == ''0''', 'agencyid =''$agencyId$''', '4CEECDD3AF1A497FE0533A06A8C01266', '',  v_row.districtid,v_row.year, '1', '单位用户');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4CEF8F3B1557503AE0533A06A8C09E23', '$code$ != '''' && $code$ == ''1''', 'isbgt = ''1'' and exists (select 1 from (select objectid from spf_t_cflow_act where wfdirection in (''0'',''1'') and targetagencyid in (select guid from code_t_firagency where iscz = ''1'') union all select objectid from spf_t_cflow_hist where wfdirection in (''0'',''1'') and targetagencyid in (select guid from code_t_firagency where iscz = ''1'')) t where t.objectid =TBASE.projectid )', '4CEECDD3AF1A497FE0533A06A8C01266', '',  v_row.districtid,v_row.year, '1', '财政当年项目库');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4CEF8F3B1558503AE0533A06A8C09E23', '$code$ != '''' && $code$ == ''2''', '(isbgt <> ''1'' or isbgt is null) and exists(select 1 from (select objectid from spf_t_cflow_act where wfdirection in (''0'',''1'', ''4'') and targetagencyid in (select guid from code_t_firagency where iscz = ''1'') union all select objectid from spf_t_cflow_hist where wfdirection in (''0'',''1'', ''4'') and targetagencyid in (select guid from code_t_firagency where iscz = ''1'')) t where t.objectid =TBASE.projectid )', '4CEECDD3AF1A497FE0533A06A8C01266', '',  v_row.districtid,v_row.year, '1', '财政备选项目库');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4CEF8F3B1559503AE0533A06A8C09E23', '$code$ != '''' && $code$ == ''3''', 'exists(select objectid from (select objectid from spf_t_cflow_act where wfdirection in (''0'',''1'') and targetagencyid in (select guid from code_t_firagency) union all select objectid from spf_t_cflow_hist where wfdirection in (''0'',''1'') and targetagencyid in (select guid from code_t_firagency)) t where t.objectid =TBASE.projectid )', '4CEECDD3AF1A497FE0533A06A8C01266', '',  v_row.districtid,v_row.year, '1', '部门项目库');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4CEF8F3B155A503AE0533A06A8C09E23', '$code$ != '''' && $code$ == ''4''', 'exists (select 1 from (select projectid from spf_t_pbaseinfo where agencyid = ''$agencyId$'') t where t.projectid =TBASE.projectid )', '4CEECDD3AF1A497FE0533A06A8C01266', '',  v_row.districtid,v_row.year, '1', '单位项目库');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4CEF8F3B155C503AE0533A06A8C09E23', '($userType$ == ''1'' || $userType$ == ''2'') && $code$ != ''4''', 'exists (select spfid from (select spfid from SECU_T_USERTOSPF where userid= ''$guid$'' union all select spfid from secu_t_roletospf where roleid in(select roleguid from fasp_t_causerrole where userguid = ''$guid$'')) t where t.spfid =TBASE.spfid)', '4CEECDD3AF1A497FE0533A06A8C01266', '',  v_row.districtid,v_row.year, '1', '财政或部门用户');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4CEF8F3B155D503AE0533A06A8C09E23', '1 == 1', 'districtid = ''$districtId$''', '4CEECDD3AF1A497FE0533A06A8C01266', '',  v_row.districtid,v_row.year, '1', '当前用户地区');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4CEF8F3B155E503AE0533A06A8C09E23', '1==0', '20,30,50,80,100,200', '4CEECDD3AF1A497FE0533A06A8C01266', '',  v_row.districtid,v_row.year, '1', '分页');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('4DBC68BBC52C4422A116984C0D3EED57', '$USERTYPE$ == ''0''', 'firagencyId = ''$UPAGENCYID$''', 'E73347B088B4461883786B6B69A44273', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('5142C3A3CA9E4BCE89D075F8861057BD', '1==0', '10,20,30,50,80,100,200', '69722A5DAFA64394B6BC8E7F7DDCC99E', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('536D49E9F23A453CA52D497FE70CDE53', '$ISADD$ != null && $ISADD$ == ''1''', 'exists ( select PROJECTID FROM SPF_T_PBASEINFO C WHERE ISADD =''$ISADD$'' AND C.PROJECTID =PROJECTID)', 'F7C195DD3FA44D3EA2843A08255E389A', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('59BC224E430F4F5C8E2092576E2148EA', '$dbtablename_proj2$!=null && $dbtablename_proj2$!=""', 'projectid in (select stp.PROJECTID from $dbtablename_proj2$  stp where stp.CREATEUSER =''$USERID$'' and CHECKSTATUS=''0'') ', '59011EB26787484DB6CAED40F62D79D9', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('5A6A607ECF734BF783B2B74EF3C87704', '$setup$ ==0', 'exists (select 1 from SPF_T_OARELATIONCOPY where taskid=spfid and status =''1''and docid=''$docId$'')', 'ABD8B909C1E54D12B6FBDBB84F962639', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('5FC27A6BF1BF48FE98EB74BDBFDA300E', '$USERID$==#USERID#', 'SPFID=#SPFID#', '', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('6728137DE52E4B2D84D37FE31BFE8AF4', '$level$==''-1''', '1=0', 'DFF8A9CC1C064317B79DB0F09459ECDF', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('84F739199194445B8116EC1BD328FB5E', '1==1', 'DISTRICTID=''$districtid$'' or DISTRICTID=''*''', '461151B564C84573BFFF887041F8229A', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('85F1070C2CE44FAF9215A998BC56E340', '$INSTEAD$ != null', 'ISINSTEAD =''$INSTEAD$''', 'F7C195DD3FA44D3EA2843A08255E389A', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('5EA001CCDEE440C0ADBA00A95D6AFAFF', '$USERID$!=null', 'sid in (select spfid from $viewName$ where spfid in $usertospf$ and spfid in (select a.spfid from $FBaseTable$ a where a.FUNDMANAGE in (''1'',''3'')) and projtypeid not in (select t.projtypeid from $tableName$ t where t.PROJTYPE = ''9'') union all select projtypeid from $viewName$ where spfid in $usertospf$ and spfid in (select a.spfid from $FBaseTable$ a where a.FUNDMANAGE in (''1'',''3'')) and projtypeid not in (select t.projtypeid from $tableName$ t where t.PROJTYPE = ''9''))', 'EFF66CD4720A44F89C4C0BADAC8FEA46', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('6E0C2681AF0F4450A2C1BF0918C2A5D0', '$DISTRICTID$!=null && $DISTRICTID$!=""', 'SPFID IN (SELECT spfid FROM SPF_T_FBASEINFO WHERE PROJTYPEID IN
(SELECT PROJTYPEID FROM SPF_T_PROJECTTYPE START WITH PROJTYPEID = ''1''  CONNECT BY PRIOR PROJTYPEID = SUPERID)
)
AND FUNDMANAGE in(''2'',''3'')
AND DISTRICTID = (SELECT t.DISTRICTID FROM CODE_T_AGENCY_SPF t WHERE t.GUID=''$UPAGENCYID$'')', 'C773F13BD91D447E9E3A95D1A92D35C3', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('6F22C2E68AA64F2296C6837327E842FD', '$checkStatus$ != ''0'' && $spfmType$ != ''1''', 'EXISTS (SELECT 1 FROM SPF_T_OARELATION WHERE TASKID=PROJECTID AND DOCID=''$docID$'') AND PROJECTID IN (SELECT PROJECTID FROM SPF_T_PBASEINFO C WHERE C.PROJTYPEID IN (SELECT PROJTYPEID FROM SPF_T_PROJECTTYPE WHERE PROJTYPE = ''3''))', '3AF76701EA4B4DB9E053CB01A8C01673', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('856EA4C852644CB2BCEA8DC5A513DFB2', '!$docID$.equals("isnull") && $level$.equals("0") && ("1").equals($setupStatus$)', 'ISRELEASE=$isRelease$ and spfid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) and spfid in (select spfid from $tableName$ where FIRAGENCYID = substr(''$treeId$'',2)) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('86FAE0265E604A5F84830E8B2A1A3E4D', '$USERID$!=null && "1".equals($isdec$)', 'spfid in (select spfid from $viewName$ where projtypeid not in (select t.projtypeid from $tableName$ t where t.PROJTYPE = ''9'') and projectid in (select projectid from $pBaseTableName$ where agencyid = ''$agencyID$''))', '38AA7C84397D001AE0530A4405064AEC', '',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('876656971BF043F8BEE443507937CD64', '$step$.equals("kzsadjust")', ' PROJTYPEID IN (SELECT PROJTYPEID FROM SPF_T_PROJECTTYPE START WITH PROJTYPEID in (''1'') CONNECT BY PRIOR PROJTYPEID = SUPERID)', 'F9CB8D34BC3263AEE040A8C021050BA8', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('879F38DD6C84421DA591AEF40278C183', '1==1', 'projectid in (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$)', '364E4DE8AA09466C9BC062CED3D12F39', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('8831EA1C90204661B8EC6E09EA0E3F44', '$audit$ != "0"', 'spfid in (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$) AND SETUPSTATUS != ''0'' ', '79592CE1019A436EA6B261AFD4999711', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('893ED0F95AAD4143AAD1AA3EB9E0F101', '$setup$ ==1', 'exists (select 1 from spf_t_oarelation where taskid=spfid and docid= ''$docId$'')
', 'ABD8B909C1E54D12B6FBDBB84F962639', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('8ACE19857E914A34A549DDD0A424A33C', '1==1', 'PROJECTID IN (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$)', '69722A5DAFA64394B6BC8E7F7DDCC99E', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('8B471D29F5114E6FB9CEDE8B0B9CDC74', '$auditTabType$==''0''', 'EXISTS(SELECT 1 FROM spf_t_oarelation B WHERE B.TASKID = PROJECTID AND DOCID =''$docID$'')
AND NOT EXISTS(SELECT 1 FROM  $auditTableName$ B WHERE B.dataid = PROJECTID AND DOCID =''$docID$'')', '76512FBCCC8E4134820BC1DDD44AB684', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('8F74F3B8650648C7A0E85B0ECBE53F46', '$UPDISTRICTID$!=null', 'DISTRICTID=''$DISTRICTID$''', '2D30A2ABFA1FF6DEE050A8C021050AD0', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('8D4437DD78124633A8B2DE28FA0844E3', '!$docID$.equals("isnull") && $level$.equals("1") && ("1").equals($setupStatus$)', 'ISRELEASE=$isRelease$ and spfid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) and spfid = substr(''$treeId$'',2) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('8D75E055F41D47589F855B58775C38B7', '$isBgt$!=''0''', 'PROJECTID in(select PROJECTID FROM $dbtablenameplan$ p WHERE p.projectid = PROJECTID and p.ISPLANVALID<>0 ) and (CHECKSTATUS = ''1'' or CHECKSTATUS = ''2'') and isbgt=''$isBgt$''', 'DDDD82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('8FFCE192BD0E482BA101D15FA4528617', '1==1', 'SPFID IN(SELECT SPFID FROM $dbtablename_spf$ WHERE SETUPSTATUS = ''2'' ) AND PROJECTID IN(SELECT PROJECTID FROM $dbtablenameplan$ P WHERE P.PROJECTID = PROJECTID AND P.ISPLANVALID <> 0 AND (CHECKSTATUS = ''1'' OR CHECKSTATUS = ''2'')) AND PROJECTID IN (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$)', 'BBBB82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('984F11F15A6D4E509D2E5CD36A857FCF', '$USERID$!=null && !"1".equals($isdec$)', 'SPFID in (select spfid from $viewName$ where projectid != spfid and projtypeid not in (select t.projtypeid from $tableName$ t where t.PROJTYPE = ''9'') and spfid in $usertospf$)', '38AA7C84397D001AE0530A4405064AEC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('991F17EF5A014339ABEFF2BD35431EA8', '1==1', 'DISTRICTID LIKE ( SELECT NVL((SELECT DISTINCT  districtspf.GUID DISTRICTID FROM SPF_T_CFLOW_ACT ACT left join code_t_district_spf districtspf on districtspf.CODE = act.DISTRICTID WHERE ACT.TARGETAGENCYID = ''$TARGETAGENCYID$'' AND ACT.BUSITYPEID = ''$BUSITYPEID$''),''%'')FROM DUAL)', '449BE1D80CEB49D0A5CAB8AA3233E11A', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('9B92FA7A5F4E4EDB8318412782A96E27', '$level$==''0''', 'SPFID in (SELECT SPFID FROM SPF_T_FBASEINFO WHERE DEPTID =substr(''$treeId$'',2))', 'DFF8A9CC1C064317B79DB0F09459ECDF', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('A06EF3F523644CA099AA16BE93224059', '$ISADD$ != null && $ISADD$ == ''1''', 'exists ( select PROJECTID FROM SPF_T_PBASEINFO C WHERE ISADD =''$ISADD$'' AND C.PROJECTID =PROJECTID)', '783B60D3816D469A93D48F6C6DDF3917', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('A85A694AFACA48EFADA56DB09DFB4C40', '$checkStatus$ == ''0'' && $listLength$ == ''1'' && $spfmType$ == ''1''', 'PROJECTID IN (SELECT PROJECTID FROM SPF_T_PBASEINFO C WHERE C.PROJTYPEID IN (SELECT PROJTYPEID FROM SPF_T_PROJECTTYPE WHERE PROJTYPE = ''3'')) AND (DOCID IN ($listDoc$) OR EXISTS (SELECT 1 FROM SPF_T_CHOOSEMID WHERE DOCID = BUSIID AND DOCID = ''$docID$'')) AND PROJECTID NOT IN (SELECT taskid FROM SPF_T_OARELATION WHERE DOCID = ''$docID$'' AND TASKTYPE = ''1'')', '3AF76701EA4B4DB9E053CB01A8C01673', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('AB8DB0AE6FF64D4C9A1113D8A0DC5237', '$USERID$!=null && $USERID$!=''''', 'EXISTS (SELECT 1 FROM (SELECT DISTINCT SPFID FROM (SELECT SPFID FROM SECU_T_USERTOSPF WHERE USERID = ''$USERID$'' UNION SELECT SPFID FROM SECU_T_ROLETOSPF A WHERE EXISTS (SELECT 1 FROM FASP_T_CAUSERROLE B WHERE B.USERGUID = ''$USERID$'' AND A.ROLEID = B.ROLEGUID) )) WHERE SPFID = TBASE.SPFID)', 'C773F13BD91D447E9E3A95D1A92D35C3', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('B6128715348D4C7ABD96A9D1B503663E', '$level$==''2''', '
SPFID =substr(''$treeId$'',2)', 'DFF8A9CC1C064317B79DB0F09459ECDF', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('BF174ACA571147F98BAA0B5DAB2C693A', '1==1', 'SPFID in(SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$)', '1F66766D2AC349F89DAC15D97F9BC7D0', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('CAC40057C99844E79998125389C11B17', '1==0', '10,20,30,50,80,100,200', '364E4DE8AA09466C9BC062CED3D12F39', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('D0914BBE730F404F86DD6DC53627F051', '$setup$ ==1', 'exists (select 1 from spf_t_oarelation where taskid=projectid and docid= ''$docId$'')', '71B119DA84CE404C8C3428F234D8D509', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('D52EBF979E50431C91948739A6D41B1A', '1==1', 'PROJECTID in(SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$)', 'F7C195DD3FA44D3EA2843A08255E389A', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('D85D7A8BA08244A0A5C8D3BC941273C4', '$isBgt$==''0''', 'PROJECTID in (select distinct p.PROJECTID FROM $dbtablenameplan$ p,spf_t_pbaseinfo p01 WHERE p.projectid=p01.projectid and ( p01.isadd=''0'' or p01.isadd is null ) and p.projectid = TBASE.PROJECTID and p.ISPLANVALID<>0 ) and (CHECKSTATUS = ''1'' or CHECKSTATUS = ''2'') and (isbgt=''$isBgt$'' or isbgt is null)', 'DDDD82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('DA69A5C1B5E54CFD9A985D183AB6343E', '1==0', '10,20,30,50,80,100,200', '38AA7C84397D001AE0530A4405064AEC', '1',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('DDFED32BAC3147B394F92344B8A6EE54', '1==1', 'spfid in( select spfid from $dbtablename_spf$ where setupstatus = ''2'') and projectid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$)', 'AAAA82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('E338E6B26D06442698468737375E1C3F', '$isIndex$!=''0''', 'ISINDEX = ''1''', 'EEEE82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('E37DC0BDDCF54F16993AFF2BCC291CEA', '$dbtablename_proj2$!=null && $dbtablename_proj2$!=""', 'projectid in (select stp.PROJECTID from $dbtablename_proj2$ stp where stp.CREATEUSER =''$USERID$'' and CHECKSTATUS=''0'')', 'B3A5CFF14F594C619A653A658D0A05FF', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('E80D4532199647CE91F961D61776C30F', '!$docID$.equals("isnull") && $level$.equals("-1") && ("1").equals($setupStatus$)', 'ISRELEASE=1 and spfid in (SELECT OBJECTID FROM $flowTableName$ $flowTable$ WHERE $flowWhere$) and FIRAGENCYID in ($firagenceId$) and SETUPSTATUS=''$setupStatus$''', '8CADB101B53E41C18FC2FA9510D9A7DC', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('F7AD8E8008BC4D40928616A2BF3A404B', '$dbtablename_proj$!=null && $dbtablename_proj$!=""', 'spfid in (select stp.spfid from $dbtablename_proj$ stp where stp.CREATEUSER =''$USERID$'' and CHECKSTATUS=''0'' )', 'B3A5CFF14F594C619A653A658D0A05FF', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('FC90230700F84CE98CC2E2DB4226D4E8', '1==1', 'projectid in (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$)', '783B60D3816D469A93D48F6C6DDF3917', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('FE57F798B0AD40478FFCD86A95F4DDE8', '$isIndex$!=''0''', 'ISINDEX = ''1''', 'BBBB82614F624DEF84E88BE30959468D', '0',  v_row.districtid,v_row.year, '1', '');

insert into P#SPF_T_CONDISET (GUID, CONDITION, SENTENCES, TABLEID, WHERETYPE, PROVINCE, YEAR, STATUS, NOTE)
values ('FE72BBF0334643B89471CD819048E973', '$selectTabType$==''1''', 'EXISTS(SELECT 1 FROM spf_t_oarelation B WHERE B.TASKID = PROJECTID AND DOCID =''$docID$'')', '76512FBCCC8E4134820BC1DDD44AB684', '0',  v_row.districtid,v_row.year, '1', '');


    end;
 end loop;


end;
--SPF_T_CONDISET
