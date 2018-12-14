DECLARE
begin
  EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SPF_T_XZBRZ AS
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
       c.DISTRICTID
  FROM SPF_T_BFLOW_LOG c, EFM_T_BFLOW_LOG e
  , spf_t_fbaseinfo p ,spf_t_fbasestatus fs where c.spfid = p.spfid and c.spfid=c.objectid and e.BATCHID=c.BATCHID and fs.SPFID=p.spfid/';

delete P#DICT_T_FACTOR where COLUMNID in ('4F3CD6A665671CFDE0533A06A8C02B9F');

for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
  begin

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '4F3CD6A665671CFDE0533A06A8C02B9F', null, '6', '3', 'SETUPSTATUSNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', '1', '设立状态', '1', '27', null, null, '0', '0', null, '1', '0', 'E9F7D4B1B1B14278ACF377BC57250123', null, '0', null);


  end;
 end loop;
end;--SPF_T_XZBRZ视图脚本
--SPF_T_XZBRZ
