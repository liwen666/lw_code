DECLARE
begin
EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SPF_T_XZBYBRZ AS
SELECT c.datakey,
       c.objectid,
       e.BUSITYPEID,
       c.BATCHID,
          (select title from BD_T_BATCH where batchid=c.batchid) title,
       e.AGENCYID TARGETAGENCYID,
     e.SOURCEBATCHID,
       (select busitypename from bd_t_busitype where BUSITYPEID = e.BUSITYPEID) busitypename,
       p.dbversion,
       p.agencyid,
       p.PROJTYPEID,
       p.SPFID,
       fs.SETUPSTATUS,
       (case when (fs.SETUPSTATUS=0) then '未设立'  when (fs.SETUPSTATUS=1) then '已确认,未发布' else '已设立' end) SETUPSTATUSNAME,
       1 NEEDUPDATE,
       c.OBJSTATUS,
       1 ORDERID,
       p.BEGINYEAR FINYEAR,
       p.SPFNAME,
       e.NODEID,
       e.WFSTATUS,
       e.wfversion,
       c.DISTRICTID
  FROM SPF_T_BFLOW_LOG c, (
  SELECT distinct BUSITYPEID,
                  AGENCYID,
                  SOURCEBATCHID,
                  nodeid,
                  WFSTATUS,
                  WFVERSION,
                  batchid
    from EFM_T_BFLOW_LOG EBL,bd_v_cutuserhistorytask BVC
    WHERE EBL.BATCHID = BVC.business_key_
    AND EBL.NODEID = BVC.task_def_key_
    AND EBL.WFVERSION = BVC.processDefId
  ) e
  , spf_t_fbaseinfo p ,spf_t_fbasestatus fs where c.spfid = p.spfid and c.objstatus in ('0','1','2') and c.spfid=c.objectid and e.BATCHID=c.BATCHID and fs.SPFID=p.spfid/';

DELETE FROM P#DICT_T_FACTOR WHERE tableid = '5059B502EFEA6354E0533906A8C0C94B' and columnid = '5131F30EBDC609AAE0533906A8C02019';

FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   
BEGIN

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '5131F30EBDC609AAE0533906A8C02019', null, '32', '3', 'WFVERSION', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '流程版本', '1', '22', null, null, '0', '0', null, '1', '0', '5059B502EFEA6354E0533906A8C0C94B', null, '0', null);

 end;
 end loop;
end; --- SPF_T_XZBYBRZ 
--SPF_T_XZBYBRZ
