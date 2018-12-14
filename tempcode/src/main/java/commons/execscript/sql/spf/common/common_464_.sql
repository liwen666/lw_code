DECLARE
begin
  EXECUTE IMMEDIATE Q'/create or replace view SPF_T_SPFPYJHIST as
select
   hist.batchid,
    (select title from BD_T_BATCH where batchid=hist.batchid) title
  ,hist.busitypeid
  ,hist.createuser
  ,hist.datakey
  ,hist.objectid
  ,hist.districtid
  ,hist.remark
  ,hist.sourceagencyid
  ,hist.sourcebatchid
  ,hist.step
  ,hist.targetagencyid
  ,hist.wfdirection
  ,hist.wfstatus
  ,spfyj.spfid
  ,spfyj.agencyid
  ,spfyj.PROJTYPEID
	,fs.SETUPSTATUS
  ,(case when (fs.SETUPSTATUS=0) then '未设立' when (fs.SETUPSTATUS=1) then '已确认,未发布' else '已设立' end) SETUPSTATUSNAME
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=hist.busitypeid) busitypename
    ,TO_CHAR(SYSDATE,'YYYY') FINYEAR
from spf_t_cflow_hist hist
  ,SPF_V_SPFFMAINYJ spfyj
  ,spf_t_fbasestatus fs
where spfyj.spfid = hist.objectid and fs.SPFID=spfyj.SPFID/';

delete P#DICT_T_FACTOR where COLUMNID in ('4F382573E65469C2E0533A06A8C0CDC6');

for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
  begin

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '4F382573E65469C2E0533A06A8C0CDC6', null, '6', '3', 'SETUPSTATUSNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '设立状态', '1', '23', null, null, '0', '0', null, '1', '0', '4CCF7F63AC9F5A78E0533A06A8C05145', null, '0', null);

  end;
 end loop;
end;--SPF_T_SPFPYJHIST视图脚本
--SPF_T_SPFPYJHIST
