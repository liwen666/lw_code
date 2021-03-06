DECLARE
begin
EXECUTE IMMEDIATE Q'/
CREATE OR REPLACE VIEW SPF_T_XMBYBRZ AS
SELECT c.datakey,
       c.objectid,
       e.BUSITYPEID,
       c.BATCHID,
         (select title from BD_T_BATCH where batchid=c.batchid) title,
       e.AGENCYID TARGETAGENCYID,
     e.SOURCEBATCHID,
       (select busitypename from bd_t_busitype where BUSITYPEID = e.BUSITYPEID) busintypename,
       p.dbversion,
       p.agencyid,
       p.PROJECTID,
       p.PROJTYPEID,
       p.SPFID,
       e.nodeid,
       c.DISTRICTID,
       c.OBJSTATUS,
       p.PROJNAME,
       1 NEEDUPDATE,
       1 ORDERID,
       e.WFSTATUS,
       ps.CHECKSTATUS,
       (case when(ps.CHECKSTATUS=1) then '已审核' else '未审核' end ) CHECKSTATUSNAME,
       ps.ISBGT,
       (case when(ps.ISBGT=1) then '已纳入预算' else '未纳入预算' end ) ISBGTNAME,
       ps.ISINDEX,
       (case when(ps.ISINDEX=1) then '已纳入指标' else '未纳入指标' end ) ISINDEXNAME,
       p.startyear FINYEAR
  FROM SPF_T_BFLOW_LOG c,(
  SELECT distinct BUSITYPEID,
                  AGENCYID,
                  SOURCEBATCHID,
                  nodeid,
                  WFSTATUS,
                  batchid
    from EFM_T_BFLOW_LOG EBL,bd_v_cutuserhistorytask BVC
    WHERE EBL.BATCHID = BVC.business_key_
    AND EBL.NODEID = BVC.task_def_key_
    AND EBL.WFVERSION = BVC.processDefId
  ) e
  , spf_t_pbaseinfo p, spf_t_pbasestatus ps where c.objectid = p.PROJECTID and c.objstatus in ('0','1','2') and e.batchid=c.BATCHID and ps.PROJECTID=c.OBJECTID
  /';
  delete from dict_t_dealtype where dealid='spf_xmyb';
Insert into dict_t_dealtype (APPID,DEALID,DEALNAME,ORDERID,NEEDCONFIG,ISUNIQUE) values ('SPF','spf_xmyb','横向已办日志',12,'0','0');
  DELETE FROM dict_t_defaultcol WHERE dealid ='spf_xmyb';
  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS,  DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('spf_xmyb', 1, '行标识', 'DATAKEY', 3, 32, null, '', '1', '1', '5059376EF60452F9E0533906A8C0AC85', '1',  '', '1', '080001', '0', '0');
  delete from p#dict_t_model WHERE tableid ='5058E9A53D855F30E0533906A8C0ADE0';
  delete from p#dict_t_factor where tableid ='5058E9A53D855F30E0533906A8C0ADE0';
  
   for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop
  begin
  insert into P#DICT_T_MODEL (YEAR, PROVINCE, TABLEID, APPID, DBTABLENAME, NAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, ORDERID, RELATAB, REMARK, SECUSQL, BGTLVL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, INITSQLBTNNAME, ISEXCELIMP, ISREADONLY, DESCFILE)
values (v_row.year,v_row.districtid, '5058E9A53D855F30E0533906A8C0ADE0', 'SPF', 'SPF_T_XMBYBRZ', '横向已办视图', 'spf_xmyb', '0000000000000000000000000000000', '3', '0', '0', '0', '0', '1', '', '0', '', 2, '', '', '', '', '<CLOB>', '1', '', '1', 'FB12E708FE56425F82EF3D89FDE0D0EF', '2', '', '', '', '', '', '', '1', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '行标志', '', '', '', '', '行标志', '5058E9A53D865F30E0533906A8C0ADE0', '', 32, 3, 'DATAKEY', 'sys_guid()', '080001', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', 1, '行标志', '1', 1, '', '', 0, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D875F30E0533906A8C0ADE0', '', 32, 3, 'OBJECTID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '业务主体id', '1', 2, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D885F30E0533906A8C0ADE0', '', 32, 3, 'BUSITYPEID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '业务类型ID', '1', 3, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D895F30E0533906A8C0ADE0', '', 32, 3, 'BATCHID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '批次ID', '1', 4, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D8A5F30E0533906A8C0ADE0', '', 200, 3, 'TITLE', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '标题', '1', 5, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D8B5F30E0533906A8C0ADE0', '', 32, 3, 'TARGETAGENCYID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '目标单位id', '1', 6, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D8C5F30E0533906A8C0ADE0', '', 32, 3, 'SOURCEBATCHID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '来源批次ID', '1', 7, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D8D5F30E0533906A8C0ADE0', '', 200, 3, 'BUSINTYPENAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '业务类型', '1', 8, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '预算单位', '', '', '', '', '预算单位', '5058E9A53D8E5F30E0533906A8C0ADE0', '', 32, 3, 'AGENCYID', '', '10401', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', 1, '预算单位', '1', 9, '', '', 0, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D8F5F30E0533906A8C0ADE0', '', 32, 3, 'PROJECTID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '项目（二级项目）id', '1', 10, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D905F30E0533906A8C0ADE0', '', 32, 3, 'PROJTYPEID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '一级专项id', '1', 11, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D915F30E0533906A8C0ADE0', '', 32, 3, 'SPFID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '专项（一级项目）id', '1', 12, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D925F30E0533906A8C0ADE0', '', 32, 3, 'NODEID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '节点id', '1', 13, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D935F30E0533906A8C0ADE0', '', 32, 3, 'DISTRICTID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '地区', '1', 14, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D945F30E0533906A8C0ADE0', '', 1, 3, 'OBJSTATUS', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '业务类型状态', '1', 15, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D955F30E0533906A8C0ADE0', '', 200, 3, 'PROJNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '项目（二级项目）名称', '1', 16, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '刷新标志', '', '', '', '', '刷新标志', '5058E9A53D965F30E0533906A8C0ADE0', '', 4000, 3, 'NEEDUPDATE', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', 1, '刷新标志', '1', 17, '', '', 0, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '排序序号', '', '', '', '', '排序序号', '5058E9A53D975F30E0533906A8C0ADE0', '', 9, 1, 'ORDERID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', 1, '排序序号', '1', 18, '', '', 0, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D985F30E0533906A8C0ADE0', '', 1, 3, 'WFSTATUS', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'WFSTATUS', '1', 19, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D995F30E0533906A8C0ADE0', '', 1, 1, 'CHECKSTATUS', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '是否审核（状态）', '1', 20, '', '', 0, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D9A5F30E0533906A8C0ADE0', '', 6, 3, 'CHECKSTATUSNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否审核', '1', 21, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D9B5F30E0533906A8C0ADE0', '', 1, 1, 'ISBGT', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '是否纳入预算（状态）', '1', 22, '', '', 0, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D9C5F30E0533906A8C0ADE0', '', 10, 3, 'ISBGTNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否纳入预算', '1', 23, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D9D5F30E0533906A8C0ADE0', '', 1, 1, 'ISINDEX', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '是否纳入指标（状态）', '1', 24, '', '', 0, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '5058E9A53D9E5F30E0533906A8C0ADE0', '', 10, 3, 'ISINDEXNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否纳入指标', '1', 25, '', '', null, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '财年', '', '', '', '', '财年', '5058E9A53D9F5F30E0533906A8C0ADE0', '', 4, 3, 'FINYEAR', '', '10206', '', '', '', '', '', '0', '0', '', '0', '1', '0', '1', '0', '0', '0', '1', 1, '财年', '1', 26, '', '', 0, '', null, '1', '0', '5058E9A53D855F30E0533906A8C0ADE0', '', '0', '');



  end;
  end loop;
  end;--横向项目已办日志
