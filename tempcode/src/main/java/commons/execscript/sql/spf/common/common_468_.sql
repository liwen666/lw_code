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
       to_char(sysdate,'yyyy') FINYEAR
from spf_t_bflow_log s,efm_t_bflow_log e,SPF_V_SPFFMAINYJ spfyj
where s.BATCHID=e.BATCHID and s.SPFID=spfyj.spfid and s.SPFID = s.OBJECTID/';

delete P#DICT_T_FACTOR where COLUMNID in ('4F3CBA5E7E1F1A7FE0533A06A8C02D28');

for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
  begin

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '4F3CBA5E7E1F1A7FE0533A06A8C02D28', null, '6', '3', 'SETUPSTATUSNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '设立状态', '1', '21', null, null, '0', '0', null, '1', '0', '4E31468A96D711FEE0533A06A8C0EB50', null, '0', null);

  end;
 end loop;
end;--SPF_T_YJPBRZ视图脚本
--SPF_T_YJPBRZ
