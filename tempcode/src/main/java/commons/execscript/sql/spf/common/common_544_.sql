DECLARE
begin
EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SPF_T_YJPBRZ AS
SELECT s.datakey,
       s.objectid,
       e.BUSITYPEID,
       s.BATCHID,
         (select title from BD_T_BATCH where batchid=s.batchid) title,
       e.AGENCYID TARGETAGENCYID,
     e.SOURCEBATCHID,
       (select busitypename from bd_t_busitype where BUSITYPEID = e.BUSITYPEID) busintypename,
       sysdate dbversion,
       spfyj.agencyid,
       spfyj.PROJTYPEID,
       spfyj.SPFID,
     spfyj.SPFNAME,
       spfyj.PROJTYPENAME,
       e.nodeid,
       s.DISTRICTID,
       s.OBJSTATUS,
       spfyj.SETUPSTATUS,
       (case when (spfyj.SETUPSTATUS=0) then '未设立'  when (spfyj.SETUPSTATUS=1) then '已确认,未发布' else '已设立' end) SETUPSTATUSNAME,
       1 NEEDUPDATE,
       1 ORDERID,
       e.WFSTATUS,
       e.WFVERSION,
       to_char(sysdate,'yyyy') FINYEAR
from spf_t_bflow_log s,efm_t_bflow_log e,SPF_V_SPFFMAINYJ spfyj
where s.BATCHID=e.BATCHID and s.SPFID=spfyj.spfid and s.SPFID = s.OBJECTID/';


DELETE FROM P#DICT_T_FACTOR WHERE tableid = '4E31468A96D711FEE0533A06A8C0EB50' and columnid = '512EC501E30C7808E0533906A8C0F636';

FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   
	
BEGIN

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '512EC501E30C7808E0533906A8C0F636', null, '32', '3', 'WFVERSION', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '流程版本', '1', '22', null, null, '0', '0', null, '1', '0', '4E31468A96D711FEE0533A06A8C0EB50', null, '0', null);

 end;
 end loop;
end;--- SPF_T_YJPBRZ  横向一级项目明细视图 



--SPF_T_YJPBRZ
