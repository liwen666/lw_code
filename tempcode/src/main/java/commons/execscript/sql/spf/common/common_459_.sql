DECLARE
begin
  EXECUTE IMMEDIATE Q'/create or replace view SPF_T_FBASECFLOW as
select
  cact.batchid
  ,(select title from BD_T_BATCH where batchid=cact.batchid) title
  ,cact.busitypeid
  ,cact.createuser
  ,cact.datakey
  ,cact.objectid
  ,cact.districtid
  ,cact.remark
  ,cact.sourceagencyid
  ,cact.sourcebatchid
  ,cact.step
  ,cact.targetagencyid
  ,cact.wfdirection
  ,cact.wfstatus
  ,fbase.spfid
  ,fbase.agencyid
	,fs.SETUPSTATUS
  ,(case when (fs.SETUPSTATUS=0) then '未设立' when (fs.SETUPSTATUS=1) then '已确认，未发布' else '已设立' end) SETUPSTATUSNAME
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=cact.busitypeid) busitypename
    ,fbase.beginyear FINYEAR
    ,fbase.PROJTYPEID PROJTYPEID
from spf_t_cflow_act cact
  ,spf_t_fbaseinfo fbase
  ,spf_t_fbasestatus fs
where fbase.spfid = cact.objectid and fs.SPFID=fbase.SPFID/';

delete P#DICT_T_FACTOR where COLUMNID in ('4F3820D0905F68CDE0533A06A8C0F51D');

for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
  begin

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '4F3820D0905F68CDE0533A06A8C0F51D', null, '6', '3', 'SETUPSTATUSNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '设立状态', '1', '23', null, null, '0', '0', null, '1', '0', '4BD986CF5E3F6E24E0533A06A8C04FF3', null, '0', null);

  end;
 end loop;
end;--SPF_T_FBASECFLOW视图脚本
--SPF_T_FBASECFLOW
