
DECLARE
begin
EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SPF_V_PADJUSTOBJXM AS
SELECT
       SPF_V_PADJUSTMAIN.ISINSTEAD       AS ISINSTEAD,
       SPF_V_PADJUSTMAIN.DISTRICTID      AS DISTRICTID,
       SPF_V_PADJUSTMAIN.PROJCODE        AS PROJCODE,
       SPF_V_PADJUSTMAIN.FUNDLEVEL       AS FUNDLEVEL,
       SPF_V_PADJUSTMAIN.FIRAGENCYID     AS FIRAGENCYID,
       SPF_V_PADJUSTMAIN.PROJGROUP_NAME  AS PROJGROUP_NAME,
       SPF_V_PADJUSTMAIN.EXPFUNCCLASS    AS EXPFUNCCLASS,
       SPF_V_PADJUSTMAIN.EXPFUNCID       AS EXPFUNCID,
       SPF_V_PADJUSTMAIN.CHECKSTATUS     AS CHECKSTATUS,
       SPF_V_PADJUSTMAIN.CREATEUSER      AS CREATEUSER,
       SPF_V_PADJUSTMAIN.AGENCYID        AS AGENCYID,
       SPF_V_PADJUSTMAIN.SPFID           AS SPFID,
       SPF_V_PADJUSTMAIN.ISBGT           AS ISBGT,
       SPF_V_PADJUSTMAIN.AGENCYNAME      AS AGENCYNAME,
       SPF_V_PADJUSTMAIN.PROJNAME        AS PROJNAME,
       SPF_V_PADJUSTMAIN.PROJTYPEID      AS PROJTYPEID,
       SPF_V_PADJUSTMAIN.DATAKEY         AS DATAKEY,
       SPF_V_PADJUSTMAIN.SPFNAME         AS SPFNAME,
       SPF_V_PADJUSTMAIN.ISTEMP          AS ISTEMP,
       SPF_V_PADJUSTMAIN.PROJECTID       AS PROJECTID,
       SPF_V_PADJUSTMAIN.IMPORTANCE      AS IMPORTANCE,
       SPF_V_PADJUSTMAIN.CREATETIME      AS CREATETIME,
       SPF_V_PADJUSTMAIN.REPORTAGENCY    AS REPORTAGENCY
  FROM SPF_V_PADJUSTMAIN SPF_V_PADJUSTMAIN
/';
delete from p#dict_t_model t where t.TABLEID='6308DB25C4BB449FA5E1E98B0ADBDACB' ;
delete from p#dict_t_factor t where t.TABLEID='6308DB25C4BB449FA5E1E98B0ADBDACB';
FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   
BEGIN
  insert into P#DICT_T_MODEL (YEAR, PROVINCE, TABLEID, APPID, DBTABLENAME, NAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, ORDERID, RELATAB, REMARK, SECUSQL, BGTLVL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, INITSQLBTNNAME, ISEXCELIMP, ISREADONLY, DESCFILE)
values (v_row.year, v_row.districtid, '6308DB25C4BB449FA5E1E98B0ADBDACB', 'SPF', 'SPF_V_PADJUSTOBJXM', '项目调整主列表', '52', '0000000000000000000000000000000', '3', '0', null, '0', '0', '1', '0', '0', null, 6, null, null, null, null, null, '1', null, '1', 'B29F2474AA8F40938D299880CFB87BEF', '2', null, '0', '0', '1', null, null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, null, null, null, '1E64E887DFAE40DBBDE99B05BB626465', null, 1, 3, 'ISINSTEAD', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '是否代录', '1', 49, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, '3857428E8880469F810FF137BB1E8652', null, 32, 3, 'DISTRICTID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '申报地区', '1', 45, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, '4686642E80384747B69458758F4E5A4E', null, 60, 3, 'PROJCODE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '项目编码', '1', 8, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, '50338B91DDAC4207BA5BF7F6B4088D32', null, 32, 3, 'FUNDLEVEL', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '资金级次', '0', 60, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, '50FD9F106635489BA0891557A19649E9', '14DA206175AB0A8FE050A8C021055537', 32, 6, 'FIRAGENCYID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '主管部门', '0', 2, null, null, 0, '4', 160, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, '55868758458E4CB5BB64FBEDC5E98A28', null, 234, 3, 'PROJGROUP_NAME', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '项目分组名称', '0', 5, null, null, 0, '0', 200, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, null, null, null, '5B521778AB3C4E9EAA0462EF812AC560', null, 369, 3, 'EXPFUNCCLASS', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '类', '1', 35, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, '6E49909D14F546E095809B4BC43972DD', null, 32, 3, 'EXPFUNCID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '支出功能分类科目', '0', 71, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, null, null, null, '7D9B2E0A80C6457FA53382476BC1835B', null, 1, 3, 'CHECKSTATUS', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '审核状态', '1', 47, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, '7ED9A0D7CD274C8E82D91130066F2AD6', null, 32, 3, 'CREATEUSER', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '申报人', '0', 40, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, '8A8612DC38E547ABB28C38FFD0F30529', null, 32, 3, 'AGENCYID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '项目申请单位', '0', 23, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, '9DEEF421B9F944909AA9F1159A02CCB1', null, 32, 3, 'SPFID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '专项唯一标识', '0', 26, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, null, null, null, '9DF301DA546E4F4F9198F7B3B0FE64EE', null, 32, 3, 'ISBGT', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否纳入预算', '1', 46, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, 'ADAEC40F8C554BC0A533DCF716545B82', null, 323, 3, 'AGENCYNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '单位名称', '1', 25, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, 'B55E9BFCA86B471BBF024D61A74D41BA', null, 200, 3, 'PROJNAME', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '项目名称', '0', 7, null, null, 0, '0', 300, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, 'C3CE74C29CA84F2EA37DD43FD32F2B8A', null, 32, 3, 'PROJTYPEID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '专项类型', '0', 27, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, null, null, null, 'C46575ACEA7D4FE5AB46DBEEEC7A1B8A', null, 32, 3, 'DATAKEY', 'sys_guid()', '080001', '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '1', '1', '0', '0', '0', '1', '0', '0', 1, '行标志', '0', 20, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, 'CA62299C5ABC4B36B0E059A2D08A9467', null, 200, 3, 'SPFNAME', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '专项名称', '0', 3, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, null, null, null, 'D0E1BCE5C36A400C91572FFC79B021E7', null, 1, 3, 'ISTEMP', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否临时', '0', 52, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, null, null, null, 'D5AFA94A63684AA0B048E22EA86DAFD1', null, 32, 3, 'PROJECTID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '1', '1', '0', '0', '0', '1', '0', '0', 1, '项目唯一标识', '0', 29, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, 'D88929A75BBF41E0B2F6CE5E355F574E', null, 18, 3, 'IMPORTANCE', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '重要程度', '1', 44, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, null, null, null, 'DC64E6EB054C4DD9BF0140CE5A7975EA', null, 20, 3, 'CREATETIME', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '申报时间', '1', 41, null, null, 0, '2', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, null, null, null, '1', null, null, 'E189D17D095C44459CC7D0079398C399', null, 300, 3, 'REPORTAGENCY', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '项目申请单位', '1', 31, null, null, 0, '0', 0, '1', '0', '6308DB25C4BB449FA5E1E98B0ADBDACB', null, '0', null);

 end;
 end loop;
end;
--项目调整主列表
