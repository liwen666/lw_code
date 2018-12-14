DECLARE
begin
EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SPF_T_EJPBYBRZ AS
SELECT s.datakey,
       s.objectid,
       e.BUSITYPEID,
       s.BATCHID,
         (select title from BD_T_BATCH where batchid=s.batchid) title,
       e.AGENCYID TARGETAGENCYID,
     e.SOURCEBATCHID,
       (select busitypename from bd_t_busitype where BUSITYPEID = e.BUSITYPEID) busintypename,
       sysdate dbversion,
       spfej.agencyid,
       spfej.PROJECTID,
       spfej.PROJTYPEID,
       spfej.SPFID,
       e.nodeid,
       s.DISTRICTID,
       s.OBJSTATUS,
       1 NEEDUPDATE,
       1 ORDERID,
       e.WFSTATUS,
       e.wfversion,
       spfej.SPFNAME,
       spfej.PROJNAME,
       spfej.CHECKSTATUS,
       (case when(spfej.CHECKSTATUS=1) then '已审核' else '未审核' end ) CHECKSTATUSNAME,
       spfej.ISBGT,
       (case when(spfej.ISBGT=1) then '已纳入预算' else '未纳入预算' end ) ISBGTNAME,
       spfej.ISINDEX,
       (case when(spfej.ISINDEX=1) then '已纳入指标' else '未纳入指标' end ) ISINDEXNAME,
       to_char(sysdate,'yyyy') FINYEAR
from spf_t_bflow_log s,(
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
  ) e,SPF_V_SPFPMAINEJ spfej
where s.BATCHID=e.BATCHID and s.OBJECTID=spfej.PROJECTID  and s.objstatus in ('0','1','2')/';

DELETE FROM P#DICT_T_FACTOR WHERE tableid = '5068491F31EB2401E0533906A8C0FB47' and columnid = '5131BF59F3BD0872E0533906A8C088DC';

FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   
BEGIN

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '5131BF59F3BD0872E0533906A8C088DC', null, '32', '3', 'WFVERSION', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '流程版本', '1', '28', null, null, '0', '0', null, '1', '0', '5068491F31EB2401E0533906A8C0FB47', null, '0', null);

 end;
 end loop;
end; --- SPF_T_EJPBYBRZ 
--SPF_T_EJPBYBRZ
