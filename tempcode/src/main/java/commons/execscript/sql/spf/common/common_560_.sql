begin
  
  EXECUTE IMMEDIATE Q'/
    CREATE OR REPLACE VIEW SPF_T_ZXYJXMKZSB AS
      SELECT KZSB.DATAKEY    AS DATAKEY,
             KZSB.ORDERID    AS ORDERID,
             KZSB.NEEDUPDATE AS NEEDUPDATE,
             KZSB.FINYEAR    AS FINYEAR,
             KZSB.AGENCYID   AS AGENCYID,
             KZSB.FINANCE_ID AS FINANCE_ID,
             KZSB.DEPT_ID    AS DEPT_ID,
             KZSB.SPFID      AS SPFID,
             KZSB.PROJECTID  AS PROJECTID,
             KZSB.REALID     AS REALID,
             STATUS,
             DBVERSION
        FROM KZSB KZSB
    /';
  
  delete from p#dict_t_model where tableid ='51D5017FE7812D9AE0533906A8C0D655';
  delete from p#dict_t_factor where tableid ='51D5017FE7812D9AE0533906A8C0D655';

  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
    --添加model表数据
    insert into p#dict_t_model (YEAR, PROVINCE, TABLEID, APPID, DBTABLENAME, NAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, ORDERID, RELATAB, REMARK, SECUSQL, BGTLVL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, INITSQLBTNNAME, ISEXCELIMP, ISREADONLY, DESCFILE, ISSAVEFORMULA)
    values (v_row.YEAR, v_row.DISTRICTID, '51D5017FE7812D9AE0533906A8C0D655', 'SPF', 'SPF_T_ZXYJXMKZSB', '专项一级项目控制数表', 'A6', '0000000000000000000000000000000', '3', '0', '0', '0', '0', '1', null, '0', null, 1, '51CF682FA0101A1EE0533A06A8C0EE75', null, null, null, null, '1', null, '1', 'F41F98CA4C3B4912A78DF20E437F79F2', '3', null, '0', null, null, null, null, '0', null, '0');

    
    insert into p#dict_t_factor (YEAR, PROVINCE, COLUMNID, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, '51D5017FE7822D9AE0533906A8C0D655', '行标志', null, null, null, null, '行标志', null, 32, 3, 'DATAKEY', 'sys_guid()', '080001', null, '51CF682FA0111A1EE0533A06A8C0EE75', '51CF682FA0101A1EE0533A06A8C0EE75', null, null, '0', '0', null, '0', '1', '0', '0', '0', '1', '0', '1', 1, '行标志', '1', 1, null, null, 0, null, null, '1', '0', '51D5017FE7812D9AE0533906A8C0D655', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, COLUMNID, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, '51D5017FE7832D9AE0533906A8C0D655', '排序序号', null, null, null, null, '排序序号', null, 9, 1, 'ORDERID', null, null, null, '51CF682FA0121A1EE0533A06A8C0EE75', '51CF682FA0101A1EE0533A06A8C0EE75', null, null, '0', '0', null, '0', '1', '0', '0', '0', '1', '0', '1', 1, '排序序号', '1', 2, null, null, 0, null, null, '1', '0', '51D5017FE7812D9AE0533906A8C0D655', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, COLUMNID, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, '51D5017FE7842D9AE0533906A8C0D655', '刷新标志', null, null, null, null, '刷新标志', null, 400, 3, 'NEEDUPDATE', null, null, null, '51CF682FA0131A1EE0533A06A8C0EE75', '51CF682FA0101A1EE0533A06A8C0EE75', null, null, '0', '0', null, '0', '1', '0', '0', '0', '1', '0', '1', 1, '刷新标志', '1', 3, null, null, 0, null, null, '1', '0', '51D5017FE7812D9AE0533906A8C0D655', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, COLUMNID, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, '51D5017FE7852D9AE0533906A8C0D655', '财年', null, null, null, null, '财年', null, 4, 3, 'FINYEAR', null, null, null, '51CF682FA0141A1EE0533A06A8C0EE75', '51CF682FA0101A1EE0533A06A8C0EE75', null, null, '0', '0', null, '0', '1', '0', '0', '0', '1', '0', '1', 1, '财年', '1', 4, null, null, 0, null, null, '1', '0', '51D5017FE7812D9AE0533906A8C0D655', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, COLUMNID, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, '51D5017FE7862D9AE0533906A8C0D655', '预算单位', null, null, null, null, '预算单位', null, 32, 3, 'AGENCYID', null, null, null, '51CF682FA0151A1EE0533A06A8C0EE75', '51CF682FA0101A1EE0533A06A8C0EE75', null, null, '0', '0', null, '0', '1', '0', '0', '0', '1', '0', '1', 1, '预算单位', '1', 5, null, null, 0, null, null, '1', '0', '51D5017FE7812D9AE0533906A8C0D655', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, COLUMNID, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, '51D5017FE7872D9AE0533906A8C0D655', '财政处室ID', null, null, null, null, '财政处室ID', null, 32, 3, 'FINANCE_ID', null, null, null, '51CF682FA0161A1EE0533A06A8C0EE75', '51CF682FA0101A1EE0533A06A8C0EE75', null, null, '0', '0', null, '0', '1', '0', '0', '0', '1', '0', '1', 1, '财政处室ID', '1', 6, null, null, 0, null, null, '1', '0', '51D5017FE7812D9AE0533906A8C0D655', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, COLUMNID, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, '51D5017FE7882D9AE0533906A8C0D655', '部门ID', null, null, null, null, '部门ID', null, 32, 3, 'DEPT_ID', null, null, null, '51CF682FA0171A1EE0533A06A8C0EE75', '51CF682FA0101A1EE0533A06A8C0EE75', null, null, '0', '0', null, '0', '1', '0', '0', '0', '1', '0', '1', 1, '部门ID', '1', 7, null, null, 0, null, null, '1', '0', '51D5017FE7812D9AE0533906A8C0D655', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, COLUMNID, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, '51D5017FE78A2D9AE0533906A8C0D655', '项目唯一标识', null, null, null, null, '项目唯一标识', null, 32, 3, 'PROJECTID', null, null, null, '51CF682FA01A1A1EE0533A06A8C0EE75', '51CF682FA0101A1EE0533A06A8C0EE75', null, null, '0', '0', null, '0', '1', '0', '0', '0', '1', '0', '1', 1, '项目唯一标识', '1', 10, null, null, 0, null, null, '1', '0', '51D5017FE7812D9AE0533906A8C0D655', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, COLUMNID, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, '51D5017FE78B2D9AE0533906A8C0D655', '专项唯一标识', null, null, null, null, '专项唯一标识', null, 32, 3, 'SPFID', null, null, null, '51CF682FA0191A1EE0533A06A8C0EE75', '51CF682FA0101A1EE0533A06A8C0EE75', null, null, '0', '0', null, '0', '1', '0', '0', '0', '1', '0', '1', 1, '专项唯一标识', '1', 9, null, null, 0, null, null, '1', '0', '51D5017FE7812D9AE0533906A8C0D655', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, COLUMNID, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, '51D5017FE78C2D9AE0533906A8C0D655', '关联ID', null, null, null, null, '关联ID', null, 32, 3, 'REALID', null, null, null, '51CF682FA01B1A1EE0533A06A8C0EE75', '51CF682FA0101A1EE0533A06A8C0EE75', null, null, '0', '0', null, '0', '1', '0', '0', '0', '1', '0', '1', 1, '关联ID', '1', 11, null, null, 0, null, null, '1', '0', '51D5017FE7812D9AE0533906A8C0D655', null, '0', null);
    
  end loop;
  
end;
--专项一级项目控制数表
