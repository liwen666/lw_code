DECLARE
begin
       /*插入dealtype*/
  delete from dict_t_dealtype where dealid='56';
    Insert into dict_t_dealtype (APPID,DEALID,DEALNAME,ORDERID,NEEDCONFIG,ISUNIQUE) values ('SPF','56','项目预算调整主列表',55,'0','0');
    delete from P#dict_t_model WHERE tableid ='CCCC82614F624DEF84E88BE30959468D';
    delete from P#dict_t_factor where tableid ='CCCC82614F624DEF84E88BE30959468D';
  DELETE FROM dict_t_defaultcol WHERE dealid ='56';
  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('56', 1, '行标识', 'DATAKEY', 3, 32, null, null, '1', '1', sys_guid(), '1',  null, '1', '080001', '0', '0');
  EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SPF_V_PBGTAUDITMAIN AS
SELECT A.DATAKEY,
       A.AGENCYID,
       F.FIRAGENCYID,
       AGENCY.NAME AGENCYNAME,
       A.SPFID,
       A.PROJTYPEID,
       F.SPFNAME,
       A.PROJECTID,
       A.PROJCODE,
       A.PROJNAME PROJNAME,
       A.PROJNAME || '{' || A.PROJECTID || '}' PROJGROUP_NAME,
       (SELECT ACCT.NAME
          FROM CODE_V_ACCTCODE_OUT_SPF ACCT
         WHERE ACCT.GUID = a.EXPFUNCID) AS EXPFUNCCLASS,
       A.CREATEUSER,
       A.CREATETIME,
       A.IMPORTANCE,
       A.DISTRICTID,
       PB.ISBGT,
       PB.ISINDEX,
       NVL(PB.CHECKSTATUS, '0') CHECKSTATUS,
       A.ISINSTEAD,
       F.FUNDLEVEL,
       F.EXPFUNCID,
       a.ISTEMP,
       f.DISTRICTID Spf_districtid
  FROM SPF_T_PBASEINFO   A,
       CODE_T_FIRAGENCY  AGENCY,
       SPF_T_FBASEINFO   F,
       SPF_T_PBASESTATUS PB
 WHERE F.FIRAGENCYID = AGENCY.GUID
   AND A.SPFID = F.SPFID
   AND A.PROJECTID = PB.PROJECTID
   AND A.PROJTYPEID IN (SELECT PROJTYPEID
                          FROM SPF_T_PROJECTTYPE
                         where PROJTYPE in ('3', '9'))/';
 for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop
  begin
insert into P#dict_t_model (YEAR, PROVINCE, TABLEID, NAME, ORDERID, DBTABLENAME, TABLETYPE, ISSHOW, APPID, REMARK, SUITID, DEALTYPE, ISRESERVED, INPUTLVL, ISADD, SHORTTITLE, EXTPROP, BGTLVL, SECUSQL, ISSUMTAB, ISMAN, MAINUPTAB, RELATAB, TABSWHERE, ISPARTITION, STATUS, ISBAK, INITSQL, INITSQLTIME, ISTASK, ISALLDISTRICT, DESCFILE, ISALLYEAR, INITSQLBTNNAME, ISEXCELIMP, ISREADONLY, ISSAVEFORMULA)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '项目预算调整主列表', 1, 'SPF_V_PBGTAUDITMAIN', '2', '1', 'SPF','', 'FB12E708FE56425F82EF3D89FDE0D0EF', '56', '0', '3', '0', '', '0000000000000000000000000000000', '', '', '0', '0', '', '', '', '0', '1', '','<CLOB>', '1', '', '', '', '', '', '', '', '0');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC216EA17A4A25BE6322D61623DFCE', '重要程度', 'IMPORTANCE', 3, 18, 0, '0', '', '', '1', 63, '0', '1', 1, '', '', '0', 0, '0', '0', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '1', '1', '', '0', '1', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC275C3FD440EC8C1DB1CE600AE6F9', 'FUNDLEVEL', 'FUNDLEVEL', 3, 32, null, '1', '', '', '0', 23, '0', '1', 1, '', '', '', null, '0', '0', '0', '0', '0', '', '', '0', '', '', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC62B4800846B8B601123A4B56F89E', '是否纳入预算', 'ISBGT', 3, 32, 0, '0', '', '', '1', 38, '0', '1', 1, '', '', '0', 0, '0', '0', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC67494F824EA6A6F85C95A17FGEF5', '专项类型', 'PROJTYPEID', 3, 32, 0, '0', '', '', '1', 47, '0', '1', 1, '', '', '0', 0, '0', '0', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '1', '1', '', '0', '1', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC6AA097C947A9A2B810660B76F07B', '是否纳入指标', 'ISINDEX', 3, 32, 0, '1', '', '', '1', 91, '0', '1', 1, '', '', '0', 0, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC6EF4D0B5427D8ADE8310B41DC7B6', '主管部门', 'FIRAGENCYID', 6, 32, 0, '0', '', '14DA206175AB0A8FE050A8C021055537', '1', 2, '0', '1', 1, '', '', '4', 200, '0', '1', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', 'http://192.168.5.27:7008/hqbi/flex/MenuPage.html;jsessionid=5PnQVBYTVhCvWQrqd1wTthSTDv7HzZQkWvSJ0crDcT91hQPdlfGq!1228268080?from=1=1430213291076585FDD7C9A814A71BD3', 'FA8A10907BA5B59AE040A8C021057E5A', '1', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC7B476C9545F7AA14061394692086', '申报时间', 'CREATETIME', 3, 20, 0, '0', '', '', '1', 58, '0', '1', 1, '', '', '2', 0, '0', '0', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC7DFDC08342E19136310A5B15AD1D', '项目编码', 'PROJCODE', 3, 60, 0, '1', '', '', '0', 14, '0', '1', 1, '', '', '0', 220, '0', '1', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC8264D2124F94A71CB3257BE650AD', '申报地区', 'DISTRICTID', 3, 32, 0, '0', '', '', '1', 83, '0', '1', 1, '', '', '0', 0, '0', '1', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '080001', 'CCCC829CE4634A66A5C6FF0BD5CD8204', '行标志', 'DATAKEY', 3, 32, 0, '0', 'sys_guid()', '', '1', 88, '0', '1', 1, '', '', '0', 0, '1', '0', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC83AE899746ABA14E6F3CD9G213CB', 'EXPFUNCCLASS', 'EXPFUNCCLASS', 3, 129, null, '1', '', '', '0', 12, '0', '1', 1, '', '', '', null, '0', '0', '0', '0', '0', '', '', '0', '', '', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC8D75F074452CB4DEB107C56F5355', '预算单位', 'AGENCYID', 6, 32, 0, '0', '', '1708CF6F853F76EDE050A8C0210507FC', '0', 61, '0', '1', 1, '', '', '4', 240, '0', '1', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '1', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC940E81544A389F6A624D2DFBA649', '是否代录', 'ISINSTEAD', 6, 1, 0, '1', '', '1F61620487DA498B8BD0F2D015054BEB', '0', 66, '0', '1', 1, '', '', '4', 0, '0', '0', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC94FB871E4ABD950E56ACF0DC14F7', '审核状态', 'CHECKSTATUS', 3, 1, 0, '0', '', '', '1', 35, '0', '1', 1, '', '', '0', 0, '0', '0', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCC9A5DCE5E42D187700D9FCB188925', '专项名称', 'SPFNAME', 3, 200, 0, '0', '', '', '0', 2, '0', '1', 1, '', '', '0', 0, '0', '1', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '1', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCCBFE3D2694A5BA7B6AA198B9AF4C0', 'ISTEMP', 'ISTEMP', 3, 32, null, '1', '', '', '0', 32, '0', '1', 1, '', '', '', null, '0', '0', '0', '0', '0', '', '', '0', '', '', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCCCF7ECF2E48AAB3943F28D86D5B39', '项目分组名称', 'PROJGROUP_NAME', 3, 234, 0, '0', '', '', '0', 12, '0', '1', 1, '', '', '0', 150, '0', '1', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '1', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCCEA5EC94F45A89AECB66CAD5080C6', '项目唯一标识', 'PROJECTID', 3, 32, 0, '0', '', '', '1', 49, '0', '1', 1, '', '', '0', 0, '1', '0', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCCEE2BBBED46B5BDAE17D89B0412A1', '项目名称', 'PROJNAME', 3, 234, 0, '0', '', '', '0', 10, '0', '1', 1, '', '', '0', 250, '0', '1', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '1', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCCF314374846CBB1C397135D8EF1B3', '支出功能分类科目', 'EXPFUNCID', 3, 263, 0, '0', '', '', '1', 22, '0', '1', 1, '', '', '0', 200, '0', '0', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCCF693B1224B3E998FC9926477A088', '申报人', 'CREATEUSER', 3, 32, 0, '0', '', '', '1', 29, '0', '1', 1, '', '', '0', 0, '0', '0', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '1', '1', '', '0', '1', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCCF82D4595458EBF2004592A03206A', '专项唯一标识', 'SPFID', 3, 32, 0, '0', '', '', '1', 3, '0', '1', 1, '', '', '0', 0, '0', '0', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCCFB82068E4A37ADA8ADDD4E3AEB33', '专项地区', 'SPF_DISTRICTID', 3, 32, 0, '1', '', '', '1', 89, '0', '1', 1, '', '', '0', 0, '0', '0', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '1', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, 'CCCC82614F624DEF84E88BE30959468D', '', 'CCCCFC30674B4054990B8F67A0BB9F53', '主管部门名称', 'AGENCYNAME', 3, 457, 0, '1', '', '', '0', 20, '0', '1', 1, '', '', '0', 200, '0', '0', '0', '0', '0', '', '', '0', '', '00000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '1', '1', '', '0', '0', '');
    end;
 end loop;
 sys_p_recreate_views('CCCC82614F624DEF84E88BE30959468D');
 end;
 
--项目预算调整脚本
