DECLARE
begin
EXECUTE IMMEDIATE Q'/create or replace view SPF_T_PBASEHCCOMT as
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
  ,pbase.spfid
  ,pbase.projectid
  ,pbase.PROJNAME
  ,(SELECT spfname FROM spf_t_fbaseinfo fs WHERE fs.SPFID=pbase.SPFID) SPFNAME
  ,pbase.agencyid
  ,pstatus.CHECKSTATUS
  ,(case when(pstatus.CHECKSTATUS=1) then '已审核' else '未审核' end ) CHECKSTATUSNAME
  ,pstatus.ISBGT
  ,(case when(pstatus.ISBGT=1) then '已纳入预算' else '未纳入预算' end ) ISBGTNAME
  ,pstatus.ISINDEX
  ,(case when(pstatus.ISINDEX=1) then '已纳入指标' else '未纳入指标' end ) ISINDEXNAME
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=cact.busitypeid) busitypename
    ,pbase.startyear FINYEAR
    ,pbase.PROJTYPEID
from spf_t_cflow_act cact
  ,spf_t_pbaseinfo pbase
  ,spf_t_pbasestatus pstatus
where pbase.projectid = cact.objectid and cact.OBJECTID=pstatus.PROJECTID
and cact.WFDIRECTION in('0'，'1'，'2')
union all
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
  ,pbase.spfid
  ,pbase.projectid
  ,pbase.PROJNAME
  ,(SELECT spfname FROM spf_t_fbaseinfo fs WHERE fs.SPFID=pbase.SPFID) SPFNAME
  ,pbase.agencyid
  ,pstatus.CHECKSTATUS
  ,(case when(pstatus.CHECKSTATUS=1) then '已审核' else '未审核' end ) CHECKSTATUSNAME
  ,pstatus.ISBGT
  ,(case when(pstatus.ISBGT=1) then '已纳入预算' else '未纳入预算' end ) ISBGTNAME
  ,pstatus.ISINDEX
  ,(case when(pstatus.ISINDEX=1) then '已纳入指标' else '未纳入指标' end ) ISINDEXNAME
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=hist.busitypeid) busitypename
    ,pbase.startyear FINYEAR
        ,pbase.PROJTYPEID
from spf_t_cflow_hist hist
  ,spf_t_pbaseinfo pbase
  ,spf_t_pbasestatus pstatus
where pbase.projectid = hist.objectid  and hist.OBJECTID=pstatus.PROJECTID
and hist.WFDIRECTION in('0'，'1'，'2'，'8')/';

DELETE FROM P#dict_t_Factor WHERE tableid='5068A303E62B4AF4E0533A06A8C01C39' AND columnid in ('5068A303E63A4AF4E0533A06A8C01C39','525DE592CAD7262DE0533906A8C0CCC5','5068A303E63B4AF4E0533A06A8C01C39','525DE592CAD9262DE0533906A8C0CCC5');

FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   
BEGIN
	
insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '5068A303E63A4AF4E0533A06A8C01C39', null, '32', '3', 'SPFID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '专项编号', '1', '15', null, null, null, '0', null, '1', '0', '5068A303E62B4AF4E0533A06A8C01C39', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '5068A303E63B4AF4E0533A06A8C01C39', null, '32', '3', 'PROJECTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '项目编号', '1', '16', null, null, null, '0', null, '1', '0', '5068A303E62B4AF4E0533A06A8C01C39', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '525DE592CAD7262DE0533906A8C0CCC5', null, '200', '3', 'SPFNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '专项名称', '1', '29', null, null, '0', '0', null, '1', '0', '5068A303E62B4AF4E0533A06A8C01C39', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '525DE592CAD9262DE0533906A8C0CCC5', null, '200', '3', 'PROJNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '项目名称', '1', '30', null, null, '0', '0', null, '1', '0', '5068A303E62B4AF4E0533A06A8C01C39', null, '0', null);

 end;
 end loop;
end; --SPF_T_PBASEHCCOMT
--SPF_T_PBASEHCCOMT
