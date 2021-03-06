DECLARE
begin
EXECUTE IMMEDIATE Q'/create or replace view SPF_T_SPFPYJACT as
select
   act.batchid
   ,(select title from BD_T_BATCH where batchid=act.batchid) title
  ,act.busitypeid
  ,act.createuser
  ,act.datakey
  ,act.objectid
  ,act.districtid
  ,act.remark
  ,act.sourceagencyid
  ,act.sourcebatchid
  ,act.step
  ,act.targetagencyid
  ,act.wfdirection
  ,act.wfstatus
  ,spfyi.spfid
  ,spfyi.PROJTYPEID
  ,spfyi.agencyid
  ,spfyi.SPFNAME
  ,fs.SETUPSTATUS
  ,(case when (fs.SETUPSTATUS=0) then '未设立' else '已设立' end) SETUPSTATUSNAME
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=act.busitypeid) busitypename
    ,TO_CHAR(SYSDATE,'YYYY') FINYEAR
from spf_t_cflow_act act
  ,SPF_V_SPFFMAINYJ spfyi
  ,spf_t_fbasestatus fs
where spfyi.spfid = act.objectid and fs.SPFID=spfyi.SPFID/';

DELETE FROM P#dict_t_Factor WHERE tableid='4CCF9E279E125A7CE0533A06A8C0FBCD' AND columnid in ('4CCF9E279E195A7CE0533A06A8C0FBCD','525C2E77C2BE1B3CE0533906A8C05ACB','4CCF9E279E1A5A7CE0533A06A8C0FBCD','4CCF9E279E1B5A7CE0533A06A8C0FBCD');

FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   
BEGIN

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '4CCF9E279E195A7CE0533A06A8C0FBCD', '506ADE82C24433E7E0533906A8C0E389', '32', '6', 'WFDIRECTION', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '流程方向', '1', '7', null, null, null, '4', null, '1', '0', '4CCF9E279E125A7CE0533A06A8C0FBCD', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '4CCF9E279E1A5A7CE0533A06A8C0FBCD', null, '1', '3', 'WFSTATUS', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '流程状态', '1', '8', null, null, null, null, null, '1', '0', '4CCF9E279E125A7CE0533A06A8C0FBCD', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '4CCF9E279E1B5A7CE0533A06A8C0FBCD', null, '32', '3', 'SPFID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '一级项目编号', '1', '9', null, null, null, '0', null, '1', '0', '4CCF9E279E125A7CE0533A06A8C0FBCD', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '525C2E77C2BE1B3CE0533906A8C05ACB', null, '200', '3', 'SPFNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '项目名称', '1', '24', null, null, '0', '0', null, '1', '0', '4CCF9E279E125A7CE0533A06A8C0FBCD', null, '0', null);

 end;
 end loop;
end;-----SPF_T_SPFPYJACT  
--SPF_T_SPFPYJACT
