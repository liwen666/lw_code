DECLARE
 begin
 DELETE FROM P#DICT_T_MODEL WHERE TABLEID='51D4C2175BB02BF7E0533906A8C0229F';
 DELETE FROM P#DICT_T_FACTOR WHERE TABLEID='51D4C2175BB02BF7E0533906A8C0229F';

EXECUTE IMMEDIATE  Q'/CREATE OR REPLACE VIEW SPF_T_KZSBEJ AS
SELECT kzsb.DATAKEY    AS DATAKEY,
       kzsb.ORDERID    AS ORDERID,
       kzsb.NEEDUPDATE AS NEEDUPDATE,
       kzsb.FINYEAR    AS FINYEAR,
       kzsb.AGENCYID   AS AGENCYID,
       kzsb.FINANCE_ID AS FINANCE_ID,
       kzsb.DEPT_ID    AS DEPT_ID,
       kzsb.SPFID      AS SPFID,
       kzsb.PROJECTID  AS PROJECTID,
       kzsb.REALID     AS REALID,
       STATUS,
       DBVERSION
  FROM KZSB kzsb/';
                          
FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   

BEGIN
    /*插入model*/
    insert into p#dict_t_model (YEAR, PROVINCE, TABLEID, NAME, ORDERID, DBTABLENAME, TABLETYPE, ISSHOW, APPID, REMARK, SUITID, DEALTYPE, ISRESERVED, INPUTLVL, ISADD, SHORTTITLE, EXTPROP, BGTLVL, SECUSQL, ISSUMTAB, ISMAN, MAINUPTAB, RELATAB, TABSWHERE, ISPARTITION, STATUS, ISBAK, INITSQLTIME, ISTASK, ISALLDISTRICT, DESCFILE, ISALLYEAR, INITSQLBTNNAME, ISEXCELIMP, ISREADONLY, ISSAVEFORMULA)
    values (v_row.year, v_row.districtid, '51D4C2175BB02BF7E0533906A8C0229F', '项目控制数', 1, 'SPF_T_KZSBEJ', '3', '1', 'SPF', null, 'F9D0E18E32F046A1B854BE992654E98B', 'A7', '0', '3', '0', null, '0000000000000000000000000000000', null, null, '0', '0', null, '51CF682FA0101A1EE0533A06A8C0EE75', null, '0', '1', '0', '1', null, null, null, null, null, null, '0', '0');

    /*插入factor*/


    insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '51D4C2175BB02BF7E0533906A8C0229F', '080001', '51D4C2175BB12BF7E0533906A8C0229F', '行标志', 'DATAKEY', 3, 32, 0, '1', 'sys_guid()', null, '1', 1, '0', '1', 1, '行标志', null, null, null, '0', '1', '0', '0', '0', null, null, '0', null, null, '行标志', '51CF682FA0101A1EE0533A06A8C0EE75', '51CF682FA0111A1EE0533A06A8C0EE75', '0', null, '0', null, null, null, '1', null, '0', null, null);

    insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '51D4C2175BB02BF7E0533906A8C0229F', null, '51D4C2175BB22BF7E0533906A8C0229F', '排序序号', 'ORDERID', 1, 9, 0, '1', null, null, '1', 2, '0', '1', 1, '排序序号', null, null, null, '0', '1', '0', '0', '0', null, null, '0', null, null, '排序序号', '51CF682FA0101A1EE0533A06A8C0EE75', '51CF682FA0121A1EE0533A06A8C0EE75', '0', null, '0', null, null, null, '1', null, '0', null, null);

    insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '51D4C2175BB02BF7E0533906A8C0229F', null, '51D4C2175BB32BF7E0533906A8C0229F', '刷新标志', 'NEEDUPDATE', 3, 400, 0, '1', null, null, '1', 3, '0', '1', 1, '刷新标志', null, null, null, '0', '1', '0', '0', '0', null, null, '0', null, null, '刷新标志', '51CF682FA0101A1EE0533A06A8C0EE75', '51CF682FA0131A1EE0533A06A8C0EE75', '0', null, '0', null, null, null, '1', null, '0', null, null);

    insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '51D4C2175BB02BF7E0533906A8C0229F', null, '51D4C2175BB42BF7E0533906A8C0229F', '财年', 'FINYEAR', 3, 4, 0, '1', null, null, '1', 4, '0', '1', 1, '财年', null, null, null, '0', '1', '0', '0', '0', null, null, '0', null, null, '财年', '51CF682FA0101A1EE0533A06A8C0EE75', '51CF682FA0141A1EE0533A06A8C0EE75', '0', null, '0', null, null, null, '1', null, '0', null, null);

    insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '51D4C2175BB02BF7E0533906A8C0229F', null, '51D4C2175BB52BF7E0533906A8C0229F', '预算单位', 'AGENCYID', 3, 32, 0, '1', null, null, '1', 5, '0', '1', 1, '预算单位', null, null, null, '0', '1', '0', '0', '0', null, null, '0', null, null, '预算单位', '51CF682FA0101A1EE0533A06A8C0EE75', '51CF682FA0151A1EE0533A06A8C0EE75', '0', null, '0', null, null, null, '1', null, '0', null, null);

    insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '51D4C2175BB02BF7E0533906A8C0229F', null, '51D4C2175BB62BF7E0533906A8C0229F', '财政处室ID', 'FINANCE_ID', 3, 32, 0, '1', null, null, '1', 6, '0', '1', 1, '财政处室ID', null, null, null, '0', '1', '0', '0', '0', null, null, '0', null, null, '财政处室ID', '51CF682FA0101A1EE0533A06A8C0EE75', '51CF682FA0161A1EE0533A06A8C0EE75', '0', null, '0', null, null, null, '1', null, '0', null, null);

    insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '51D4C2175BB02BF7E0533906A8C0229F', null, '51D4C2175BB72BF7E0533906A8C0229F', '部门ID', 'DEPT_ID', 3, 32, 0, '1', null, null, '1', 7, '0', '1', 1, '部门ID', null, null, null, '0', '1', '0', '0', '0', null, null, '0', null, null, '部门ID', '51CF682FA0101A1EE0533A06A8C0EE75', '51CF682FA0171A1EE0533A06A8C0EE75', '0', null, '0', null, null, null, '1', null, '0', null, null);

    insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '51D4C2175BB02BF7E0533906A8C0229F', null, '51D4C2175BB92BF7E0533906A8C0229F', '专项唯一标识', 'SPFID', 3, 32, 0, '1', null, null, '1', 9, '0', '1', 1, '专项唯一标识', null, null, null, '0', '1', '0', '0', '0', null, null, '0', null, null, '专项唯一标识', '51CF682FA0101A1EE0533A06A8C0EE75', '51CF682FA0191A1EE0533A06A8C0EE75', '0', null, '0', null, null, null, '1', null, '0', null, null);

    insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '51D4C2175BB02BF7E0533906A8C0229F', null, '51D4C2175BBA2BF7E0533906A8C0229F', '项目唯一标识', 'PROJECTID', 3, 32, 0, '1', null, null, '1', 10, '0', '1', 1, '项目唯一标识', null, null, null, '0', '1', '0', '0', '0', null, null, '0', null, null, '项目唯一标识', '51CF682FA0101A1EE0533A06A8C0EE75', '51CF682FA01A1A1EE0533A06A8C0EE75', '0', null, '0', null, null, null, '1', null, '0', null, null);

    insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '51D4C2175BB02BF7E0533906A8C0229F', null, '51D4C2175BBB2BF7E0533906A8C0229F', '关联ID', 'REALID', 3, 32, 0, '1', null, null, '1', 11, '0', '1', 1, '关联ID', null, null, null, '0', '1', '0', '0', '0', null, null, '0', null, null, '关联ID', '51CF682FA0101A1EE0533A06A8C0EE75', '51CF682FA01B1A1EE0533A06A8C0EE75', '0', null, '0', null, null, null, '1', null, '0', null, null);


 end;
 end loop;
end;--项目控制数脚本
