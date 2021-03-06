DECLARE
begin
EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SPF_T_SPFSDYJHCCOMT AS
SELECT HIST.BATCHID,
       (SELECT TITLE FROM BD_T_BATCH WHERE BATCHID = HIST.BATCHID) TITLE,
       HIST.BUSITYPEID,
       HIST.CREATEUSER,
       HIST.DATAKEY,
       HIST.OBJECTID,
       HIST.DISTRICTID,
       HIST.REMARK,
       HIST.SOURCEAGENCYID,
       HIST.SOURCEBATCHID,
       HIST.STEP,
       HIST.TARGETAGENCYID,
       HIST.WFDIRECTION,
       HIST.WFSTATUS,
       SPFYJ.SPFID,
       SPFYJ.SPFNAME,
       SPFYJ.PROJTYPEID,
       SPFYJ.AGENCYID,
       FS.SETUPSTATUS,
       (CASE
         WHEN (FS.SETUPSTATUS = 0) THEN
          '未设立'
         WHEN (FS.SETUPSTATUS = 1) THEN
          '已确认，未发布'
         ELSE
          '已设立'
       END) SETUPSTATUSNAME,
       1 NEEDUPDATE,
       1 ORDERID,
       (SELECT BUSITYPENAME
          FROM BD_T_BUSITYPE
         WHERE BUSITYPEID = HIST.BUSITYPEID) BUSITYPENAME,
       TO_CHAR(SYSDATE, 'YYYY') FINYEAR
  FROM SPF_T_CFLOW_HIST HIST, SPF_V_SPFFMAINYJ SPFYJ, SPF_T_FBASESTATUS FS
 WHERE SPFYJ.SPFID = HIST.OBJECTID
   AND FS.SPFID = SPFYJ.SPFID
   AND HIST.WFDIRECTION IN ('0' ， '1' ， '2' ， '8')
UNION ALL
SELECT ACT.BATCHID,
       (SELECT TITLE FROM BD_T_BATCH WHERE BATCHID = ACT.BATCHID) TITLE,
       ACT.BUSITYPEID,
       ACT.CREATEUSER,
       ACT.DATAKEY,
       ACT.OBJECTID,
       ACT.DISTRICTID,
       ACT.REMARK,
       ACT.SOURCEAGENCYID,
       ACT.SOURCEBATCHID,
       ACT.STEP,
       ACT.TARGETAGENCYID,
       ACT.WFDIRECTION,
       ACT.WFSTATUS,
       SPFYI.SPFID,
       SPFYI.SPFNAME,
       SPFYI.PROJTYPEID,
       SPFYI.AGENCYID,
       SPFYI.SETUPSTATUS,
       (CASE
         WHEN (SPFYI.SETUPSTATUS = 0) THEN
          '未设立'
         WHEN (SPFYI.SETUPSTATUS = 1) THEN
          '已确认，未发布'
         ELSE
          '已设立'
       END) SETUPSTATUSNAME,
       1 NEEDUPDATE,
       1 ORDERID,
       (SELECT BUSITYPENAME
          FROM BD_T_BUSITYPE
         WHERE BUSITYPEID = ACT.BUSITYPEID) BUSITYPENAME,
       TO_CHAR(SYSDATE, 'YYYY') FINYEAR
  FROM SPF_T_CFLOW_ACT ACT, SPF_V_SPFFMAINYJ SPFYI
 WHERE SPFYI.SPFID = ACT.OBJECTID
   AND ACT.WFDIRECTION IN ('0' ， '1' ， '2')/';

DELETE FROM P#dict_t_Factor WHERE tableid='506D305219A97D2DE0533A06A8C09FC6' AND columnid in ('506D305219B87D2DE0533A06A8C09FC6','525EAA4A154629E1E0533906A8C086FA');

FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   
BEGIN

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '506D305219B87D2DE0533A06A8C09FC6', null, '32', '3', 'SPFID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '专项编号', '1', '15', null, null, null, '0', null, '1', '0', '506D305219A97D2DE0533A06A8C09FC6', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '525EAA4A154629E1E0533906A8C086FA', null, '200', '3', 'SPFNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '专项名称', '1', '24', null, null, '0', '0', null, '1', '0', '506D305219A97D2DE0533A06A8C09FC6', null, '0', null);

 end;
 end loop;
end;-----SPF_T_SPFSDYJHCCOMT
--SPF_T_SPFSDYJHCCOMT
