DECLARE
begin


       /*插入dealtype*/
  delete from dict_t_dealtype where dealid='45';
    Insert into dict_t_dealtype (APPID,DEALID,DEALNAME,ORDERID,NEEDCONFIG,ISUNIQUE) values ('SPF','45','专项预算编制',1211,'0','0');
    delete from P#dict_t_model WHERE tableid ='4BD5A26754354AD7E0533A06A8C04D54';
    delete from P#dict_t_factor where tableid ='4BD5A26754354AD7E0533A06A8C04D54';
  DELETE FROM dict_t_defaultcol WHERE dealid ='45';
  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('45', 1, '行标识', 'DATAKEY', 3, 32, null, null, '1', '1', sys_guid(), '1',  null, '1', '', '0', '0');
  EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SPF_V_SPFBUDGETMAIN AS
SELECT (SELECT S.NAME FROM CODE_T_FIRAGENCY S WHERE S.GUID = A.FIRAGENCYID) FIRAGENCYNAME,
       A.SPFID,
       A.SPFNAME  SPFNAME,
       A.SPFNAME || '{' || A.SPFID || '}' SPFGROUP_NAME,
       (SELECT S.NAME FROM CODE_T_SP_TYPE S WHERE S.GUID = A.PROJTYPEID) PROJTYPENAME,
       A.SPFCODE,
       A.AGENCYID,
        (select  '[' || AGENCY.CODE || ']' || AGENCY.NAME from code_t_firagency  AGENCY where  a.AGENCYID = AGENCY.GUID ) AGENCYNAME,
       A.BEGINYEAR,
       A.ENDYEAR,
       A.EXPFUNCID,
       (SELECT ACCT.CODE
          FROM CODE_V_ACCTCODE_OUT_SPF ACCT
         WHERE ACCT.GUID = A.EXPFUNCID) EXPFUNCCODE,
       (SELECT ACCT.NAME
          FROM CODE_V_ACCTCODE_OUT_SPF ACCT
         WHERE ACCT.GUID = A.EXPFUNCID) EXPFUNCNAME,
       A.CREATETIME,
       A.SETUPSTATUS,
       A.PROJTYPEID,
       CASE (SELECT DISTINCT (LEVELNO)
           FROM CODE_V_ACCTCODE_OUT_SPF T
          WHERE CODE LIKE '2%'
            AND GUID = a.EXPFUNCID)
         WHEN 3 THEN
          (SELECT SUPERGUID
             FROM CODE_V_ACCTCODE_OUT_SPF
            WHERE GUID = a.EXPFUNCID)
         ELSE
          (SELECT SUPERGUID
             FROM CODE_T_ACCTITEM_SPF
            WHERE YEAR = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR') AND GUID = (SELECT SUPERGUID
                            FROM CODE_V_ACCTCODE_OUT_SPF
                           WHERE GUID = a.EXPFUNCID))
       END AS EXPFUNCID2,
       (SELECT V.LEICODENAME
          FROM CODE_V_ACCTCODE_OUT_SPF V
         WHERE V.GUID = A.EXPFUNCID) EXPFUNCCLASS,
       NVL((SELECT SUM(NVL(B.CTRLNUM, 0))
       FROM SPF_T_FFOUDCONTROL B
       WHERE B.SPFID = A.SPFID),
        0) CTRLNUMS,
       A.FIRAGENCYID,
       A.DEPTID,
       (SELECT NAME FROM CODE_T_DEPT_SPF WHERE GUID= A.DEPTID) DEPTNAME,
       A.DISTRICTID,
       A.ISRELEASE,
       A.APPLYPRJEFM,
       A.APPROVALTYPE,
       A.ISGROVPROC,
       A.FUNDLEVEL,
       A.FUNDMANAGE,
       A.ISTEMP,
       A.DATAKEY,
       A.DBVERSION
  FROM SPF_T_FBASEINFO A/';
 for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop
  begin
insert into P#dict_t_model (YEAR, PROVINCE, TABLEID, NAME, ORDERID, DBTABLENAME, TABLETYPE, ISSHOW, APPID, REMARK, SUITID, DEALTYPE, ISRESERVED, INPUTLVL, ISADD, SHORTTITLE, EXTPROP, BGTLVL, SECUSQL, ISSUMTAB, ISMAN, MAINUPTAB, RELATAB, TABSWHERE, ISPARTITION, STATUS, ISBAK, INITSQL, INITSQLTIME, ISTASK, ISALLDISTRICT, DESCFILE, ISALLYEAR, INITSQLBTNNAME, ISEXCELIMP, ISREADONLY, ISSAVEFORMULA)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '专项预算编制(视图)', 1, 'SPF_V_SPFBUDGETMAIN', '2', '1', 'SPF','', '8288F140A88F4CCB8FF5BD9B4B28DCEF', '45', '0', '3', '0', '', '0000000000000000000000000000000', '', '', '0', '0', '', '', '', '0', '1', '',  '', '1', '', '', '', '', '', '', '1', '0');


insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754364AD7E0533A06A8C04D54', '主管部门名称', 'FIRAGENCYNAME', 3, 266, null, '1', '', '', '0', 1, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754374AD7E0533A06A8C04D54', '专项唯一标识', 'SPFID', 3, 32, null, '1', '', '', '0', 2, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754384AD7E0533A06A8C04D54', '专项名称', 'SPFNAME', 3, 200, null, '1', '', '', '0', 3, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754394AD7E0533A06A8C04D54', '专项分组名称', 'SPFGROUP_NAME', 3, 234, null, '1', '', '', '0', 4, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A267543A4AD7E0533A06A8C04D54', '专项类型', 'PROJTYPENAME', 3, 100, null, '1', '', '', '0', 5, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A267543B4AD7E0533A06A8C04D54', '专项编码', 'SPFCODE', 3, 150, null, '1', '', '', '0', 6, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A267543C4AD7E0533A06A8C04D54', '预算单位', 'AGENCYID', 3, 32, null, '0', '', '', '0', 7, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A267543D4AD7E0533A06A8C04D54', '单位名称', 'AGENCYNAME', 3, 289, null, '1', '', '', '0', 8, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A267543E4AD7E0533A06A8C04D54', '开始年份', 'BEGINYEAR', 3, 10, null, '1', '', '', '0', 9, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A267543F4AD7E0533A06A8C04D54', '结束年份', 'ENDYEAR', 3, 10, null, '1', '', '', '0', 10, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754404AD7E0533A06A8C04D54', '支出功能分类科目', 'EXPFUNCID', 3, 32, null, '1', '', '', '0', 11, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754414AD7E0533A06A8C04D54', '支出功能分类科目代码', 'EXPFUNCCODE', 3, 7, null, '1', '', '', '0', 12, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754424AD7E0533A06A8C04D54', '支出功能分类科目名称', 'EXPFUNCNAME', 3, 129, null, '1', '', '', '0', 13, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754434AD7E0533A06A8C04D54', '创建时间', 'CREATETIME', 3, 20, null, '1', '', '', '0', 14, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754444AD7E0533A06A8C04D54', '审核状态', 'SETUPSTATUS', 3, 1, null, '1', '', '', '0', 15, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754454AD7E0533A06A8C04D54', '项目类型', 'PROJTYPEID', 3, 32, null, '1', '', '', '0', 16, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754464AD7E0533A06A8C04D54', '项', 'EXPFUNCID2', 3, 32, null, '1', '', '', '0', 17, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754474AD7E0533A06A8C04D54', '类', 'EXPFUNCCLASS', 3, 128, null, '1', '', '', '0', 18, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754484AD7E0533A06A8C04D54', '专项资金控制数', 'CTRLNUMS', 1, 8, 0, '1', '', '', '0', 19, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754494AD7E0533A06A8C04D54', '主管部门', 'FIRAGENCYID', 3, 32, null, '1', '', '', '0', 20, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A267544A4AD7E0533A06A8C04D54', '财政内部机构', 'DEPTID', 3, 32, null, '1', '', '', '0', 21, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A267544B4AD7E0533A06A8C04D54', '财政归口处室名称', 'DEPTNAME', 3, 266, null, '1', '', '', '0', 22, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A267544C4AD7E0533A06A8C04D54', '行政区划', 'DISTRICTID', 3, 32, null, '1', '', '', '0', 23, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A267544D4AD7E0533A06A8C04D54', '是否发布', 'ISRELEASE', 3, 10, null, '1', '', '', '0', 24, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A267544E4AD7E0533A06A8C04D54', '是否项目绩效', 'APPLYPRJEFM', 3, 32, null, '1', '', '', '0', 25, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A267544F4AD7E0533A06A8C04D54', '审批方式', 'APPROVALTYPE', 3, 32, null, '1', '', '', '0', 26, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754504AD7E0533A06A8C04D54', '是否政府采购', 'ISGROVPROC', 3, 1, null, '1', '', '', '0', 27, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754514AD7E0533A06A8C04D54', '专项资金级次', 'FUNDLEVEL', 3, 32, null, '1', '', '', '0', 28, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754524AD7E0533A06A8C04D54', '资金管理模式', 'FUNDMANAGE', 3, 32, null, '1', '', '', '0', 29, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754534AD7E0533A06A8C04D54', '临时专项', 'ISTEMP', 3, 1, null, '1', '', '', '0', 30, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754544AD7E0533A06A8C04D54', '行标志', 'DATAKEY', 3, 32, null, '0', '', '', '0', 31, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

insert into P#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '4BD5A26754354AD7E0533A06A8C04D54', '', '4BD5A26754554AD7E0533A06A8C04D54', '状态', 'STATUS', 3, 1, null, '0', '', '', '0', 32, '0', '1', 1, '', '', '', null, '0', '1', '0', '0', '0', '', '', '0', '', '0000000000000000000000000000000', '', '', '', '0', '', '0', '', '', '', '1', '', '0', '0', '');

    end;
 end loop;
 sys_p_recreate_views('4BD5A26754354AD7E0533A06A8C04D54');
end;
--专项预算编制（45）脚本
