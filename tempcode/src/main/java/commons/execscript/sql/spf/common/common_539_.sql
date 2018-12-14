DECLARE
begin
EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SPF_T_XMBYBRZ AS
SELECT c.datakey,
       c.objectid,
       e.BUSITYPEID,
       c.BATCHID,
         (select title from BD_T_BATCH where batchid=c.batchid) title,
       e.AGENCYID TARGETAGENCYID,
     e.SOURCEBATCHID,
       (select busitypename from bd_t_busitype where BUSITYPEID = e.BUSITYPEID) busintypename,
       p.dbversion,
       p.agencyid,
       p.PROJECTID,
       p.PROJTYPEID,
       p.SPFID,
       e.nodeid,
       c.DISTRICTID,
       c.OBJSTATUS,
       p.PROJNAME,
       1 NEEDUPDATE,
       1 ORDERID,
       e.WFSTATUS,
       e.wfversion,
       ps.CHECKSTATUS,
       (case when(ps.CHECKSTATUS=1) then '已审核' else '未审核' end ) CHECKSTATUSNAME,
       ps.ISBGT,
       (case when(ps.ISBGT=1) then '已纳入预算' else '未纳入预算' end ) ISBGTNAME,
       ps.ISINDEX,
       (case when(ps.ISINDEX=1) then '已纳入指标' else '未纳入指标' end ) ISINDEXNAME,
       p.startyear FINYEAR
  FROM SPF_T_BFLOW_LOG c,(
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
  , spf_t_pbaseinfo p, spf_t_pbasestatus ps where c.objectid = p.PROJECTID and c.objstatus in ('0','1','2') and e.batchid=c.BATCHID and ps.PROJECTID=c.OBJECTID/';

DELETE FROM P#DICT_T_FACTOR WHERE tableid = '5058E9A53D855F30E0533906A8C0ADE0' and columnid = '5131F30EBDC809AAE0533906A8C02019';

FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   
BEGIN

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '5131F30EBDC809AAE0533906A8C02019', null, '32', '3', 'WFVERSION', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '流程版本', '1', '27', null, null, '0', '0', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', null, '0', null);

 end;
 end loop;
end; --- SPF_T_XMBYBRZ 
--SPF_T_XMBYBRZ
