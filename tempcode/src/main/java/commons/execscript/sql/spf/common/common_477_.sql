DECLARE
begin
EXECUTE IMMEDIATE Q'/
CREATE OR REPLACE VIEW SPF_T_EJPBRZ AS
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
where s.BATCHID=e.BATCHID and s.OBJECTID=spfej.PROJECTID
/';
delete P#DICT_T_FACTOR where COLUMNID in ('4F3C79664C7B18C6E0533A06A8C04C61','4F3C79664C7D18C6E0533A06A8C04C61','4F3C79664C7F18C6E0533A06A8C04C61');
for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
begin


insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid,  '', '', '', '', '', '', '4F3C79664C7B18C6E0533A06A8C04C61', '', 6, 3, 'CHECKSTATUSNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '是否审核', '1', 22, '', '', 0, '0', null, '1', '0', '4E247F8864AC32F5E0533A06A8C06069', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '', '', '', '', '', '', '4F3C79664C7D18C6E0533A06A8C04C61', '', 10, 3, 'ISBGTNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否纳入预算', '1', 23, '', '', 0, '0', null, '1', '0', '4E247F8864AC32F5E0533A06A8C06069', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '', '', '', '', '', '', '4F3C79664C7F18C6E0533A06A8C04C61', '', 10, 3, 'ISINDEXNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否纳入指标', '1', 24, '', '', 0, '0', null, '1', '0', '4E247F8864AC32F5E0533A06A8C06069', '', '0', '');

end;
end loop;
end;--修改横向二级项目视图
