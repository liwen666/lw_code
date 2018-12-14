DECLARE
begin
EXECUTE IMMEDIATE Q'/
CREATE OR REPLACE VIEW SPF_T_XMRZ AS
SELECT c.datakey,
       c.objectid,
       c.BUSITYPEID,
       c.BATCHID,
            (select title from BD_T_BATCH where batchid=c.batchid) title,
       c.TARGETAGENCYID,
       (select busitypename from bd_t_busitype where BUSITYPEID = c.BUSITYPEID) busitypename,
     c.WFSTATUS,
       c.DISTRICTID,
       c.SOURCEAGENCYID,
       c.SOURCEBATCHID,
       c.WFDIRECTION,
       p.dbversion,
       p.agencyid,
       p.PROJECTID,
       p.PROJTYPEID,
       p.SPFID,
       p.PROJNAME,
       (SELECT f.spfname FROM spf_t_fbaseinfo f where f.spfid=p.SPFID) spfname,
       1 NEEDUPDATE,
       1 ORDERID,
       p.startyear FINYEAR
  FROM spf_t_cflow_act c
, spf_t_pbaseinfo p where c.objectid = p.PROJECTID
/';

delete p#dict_t_factor  where columnid in('F0524BFC09854AAE9C0AF730AE89260A','524D337E65585BC0E0533906A8C03D3F','524D337E655A5BC0E0533906A8C03D3F','CCC8591996384E19A46408A4D294B5BB');

for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
begin
  insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.year, v_row.districtid, '', '', '', '', '', '', 'F0524BFC09854AAE9C0AF730AE89260A', '', 32, 3, 'PROJECTID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '��Ŀid', '1', 13, '', '', null, '0', null, '1', '0', '068A02F4E2AC4969AA5AC25F487669D7', '', '0', '');

  insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.year, v_row.districtid, '', '', '', '', '', '', '524D337E65585BC0E0533906A8C03D3F', '', 200, 3, 'SPFNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, 'ר������', '1', 23, '', '', 0, '0', null, '1', '0', '068A02F4E2AC4969AA5AC25F487669D7', '', '0', '');

  insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.year, v_row.districtid, '', '', '', '', '', '', '524D337E655A5BC0E0533906A8C03D3F', '', 200, 3, 'PROJNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '��Ŀ����', '1', 24, '', '', 0, '0', null, '1', '0', '068A02F4E2AC4969AA5AC25F487669D7', '', '0', '');

  insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.year, v_row.districtid, '', '', '', '', '', '', 'CCC8591996384E19A46408A4D294B5BB', '', 32, 3, 'SPFID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'ר���ʶ', '1', 17, '', '', null, '0', null, '1', '0', '068A02F4E2AC4969AA5AC25F487669D7', '', '0', '');



end;
end loop;
end;
--�޸�SPF_T_XMRZ��ͼ