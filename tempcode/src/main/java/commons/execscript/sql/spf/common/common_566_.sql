DECLARE
begin
EXECUTE IMMEDIATE Q'/create or replace view SPF_T_FBASEHISTFLOW as
select
  hist.batchid
   ,(select title from BD_T_BATCH where batchid=hist.batchid) title
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
  ,fbase.spfid
  ,fs.SETUPSTATUS
  ,fbase.agencyid
  ,(case when (fs.SETUPSTATUS=0) then 'δ����' else '������' end) SETUPSTATUSNAME
  ,fbase.SPFNAME
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=hist.busitypeid) busitypename
    ,fbase.beginyear FINYEAR
    ,fbase.PROJTYPEID
from spf_t_cflow_hist hist
  ,spf_t_fbaseinfo fbase
  ,spf_t_fbasestatus fs
where fbase.spfid = hist.objectid and fs.SPFID=fbase.SPFID/';

DELETE FROM P#dict_t_Factor WHERE tableid='4C8D5F45038404CDE0533A06A8C07933' AND columnid in ('525C2E77C2C41B3CE0533906A8C05ACB','4C8D5F45039104CDE0533A06A8C07933','4C8D5F45039204CDE0533A06A8C07933','4C8D5F45039304CDE0533A06A8C07933');

FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   
BEGIN

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '4C8D5F45039104CDE0533A06A8C07933', '506ADE82C24433E7E0533906A8C0E389', '32', '6', 'WFDIRECTION', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '���̷���', '1', '13', null, null, null, '4', null, '1', '0', '4C8D5F45038404CDE0533A06A8C07933', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '4C8D5F45039204CDE0533A06A8C07933', null, '1', '3', 'WFSTATUS', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '����״̬', '1', '14', null, null, null, null, null, '1', '0', '4C8D5F45038404CDE0533A06A8C07933', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '4C8D5F45039304CDE0533A06A8C07933', null, '32', '3', 'SPFID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', 'ר����', '1', '15', null, null, null, '0', null, '1', '0', '4C8D5F45038404CDE0533A06A8C07933', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '525C2E77C2C41B3CE0533906A8C05ACB', null, '200', '3', 'SPFNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', 'ר������', '1', '24', null, null, '0', '0', null, '1', '0', '4C8D5F45038404CDE0533A06A8C07933', null, '0', null);

 end;
 end loop;
end;-----SPF_T_FBASEHISTFLOW  
--SPF_T_FBASEHISTFLOW