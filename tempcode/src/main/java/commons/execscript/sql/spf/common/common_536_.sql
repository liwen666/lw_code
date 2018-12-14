DECLARE
begin
EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SPF_T_EJPBDBRZ AS
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
       e.WFVERSION,
       spfej.SPFNAME,
       spfej.PROJNAME,
       spfej.CHECKSTATUS,
       (case when(spfej.CHECKSTATUS=1) then '已审核' else '未审核' end ) CHECKSTATUSNAME,
       spfej.ISBGT,
       (case when(spfej.ISBGT=1) then '已纳入预算' else '未纳入预算' end ) ISBGTNAME,
       spfej.ISINDEX,
       (case when(spfej.ISINDEX=1) then '已纳入指标' else '未纳入指标' end ) ISINDEXNAME,
       to_char(sysdate,'yyyy') FINYEAR
from spf_t_bflow_log s,efm_t_bflow_log e,SPF_V_SPFPMAINEJ spfej
where s.BATCHID=e.BATCHID and s.OBJECTID=spfej.PROJECTID  and e.WFSTATUS=0 and Objstatus=0/';

DELETE FROM P#DICT_T_FACTOR WHERE tableid = '50660D4EFB191851E0533906A8C00977' and columnid = '51320E4A3C190B6FE0533906A8C09B59';

FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   
BEGIN

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '51320E4A3C190B6FE0533906A8C09B59', null, '32', '3', 'WFVERSION', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '流程版本', '1', '28', null, null, '0', '0', null, '1', '0', '50660D4EFB191851E0533906A8C00977', null, '0', null);

 end;
 end loop;
end; --- SPF_T_EJPBDBRZ 
--SPF_T_EJPBDBRZ
