DECLARE
  a INTEGER;
  sqlstr VARCHAR2(2000); 
begin 
  
  select count(1) into a from p#dict_t_model t where t.dealtype = 'A0*04';
  if a > 0 then
     return;
  end if;
  
  select count(1) into a from user_tables t where t.TABLE_NAME = 'P#KZSB';
  if a > 0 then
     return;
  end if;
  
  delete from p#dict_t_model where tableid = '51CF682FA0101A1EE0533A06A8C0EE75';
  delete from p#dict_t_factor where tableid = '51CF682FA0101A1EE0533A06A8C0EE75';
  
  FOR v_row in (select * from pub_t_partition_divid t where t.year <> '*') LOOP

    insert into p#dict_t_model (YEAR, PROVINCE, TABLEID, APPID, DBTABLENAME, NAME, DBVERSION, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, ORDERID, RELATAB, REMARK, SECUSQL, BGTLVL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLETYPE, TABSWHERE, ISBAK, INSERTVERSION, ISALLDISTRICT, ISALLYEAR, INITSQLBTNNAME, ISEXCELIMP, ISREADONLY, DESCFILE, ISSAVEFORMULA)
    values (v_row.year, v_row.districtid, '51CF682FA0101A1EE0533A06A8C0EE75', 'BGT', 'KZSB', '控制数表', sysdate, 'A0*04', '0000000000000000000000000000000', '3', '0', '0', '1', '0', '1', '0', '0', null, 1, null, null, null, null, null, '1', null, '1', 'C685E107CC4649DEBF7816BD5D056E1C', '1', null, '0', sysdate, '0', '0', null, '0', '0', null, '0');

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DBVERSION, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, INSERTVERSION, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '行标志', null, null, null, null, '行标志', '51CF682FA0111A1EE0533A06A8C0EE75', null, 32, 3, 'DATAKEY',sysdate, 'sys_guid()', '080001', null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '0', 1, '行标志', '1', 1, null, null, 0, null, null, '1', '0', '51CF682FA0101A1EE0533A06A8C0EE75', null,sysdate, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DBVERSION, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, INSERTVERSION, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '排序序号', null, null, null, null, '排序序号', '51CF682FA0121A1EE0533A06A8C0EE75', null, 9, 1, 'ORDERID',sysdate, null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '0', 1, '排序序号', '1', 2, null, null, 0, null, null, '1', '0', '51CF682FA0101A1EE0533A06A8C0EE75', null,sysdate, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DBVERSION, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, INSERTVERSION, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '刷新标志', null, null, null, null, '刷新标志', '51CF682FA0131A1EE0533A06A8C0EE75', null, 400, 3, 'NEEDUPDATE',sysdate, null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '0', 1, '刷新标志', '1', 3, null, null, 0, null, null, '1', '0', '51CF682FA0101A1EE0533A06A8C0EE75', null,sysdate, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DBVERSION, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, INSERTVERSION, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '财年', null, null, null, null, '财年', '51CF682FA0141A1EE0533A06A8C0EE75', null, 4, 3, 'FINYEAR',sysdate, null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '0', 1, '财年', '1', 4, null, null, 0, null, null, '1', '0', '51CF682FA0101A1EE0533A06A8C0EE75', null,sysdate, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DBVERSION, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, INSERTVERSION, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '预算单位', null, null, null, null, '预算单位', '51CF682FA0151A1EE0533A06A8C0EE75', null, 32, 3, 'AGENCYID',sysdate, null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '1', 1, '预算单位', '1', 5, null, null, 0, null, null, '1', '0', '51CF682FA0101A1EE0533A06A8C0EE75', null, sysdate, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DBVERSION, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, INSERTVERSION, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '财政处室ID', null, null, null, null, '财政处室ID', '51CF682FA0161A1EE0533A06A8C0EE75', null, 32, 3, 'FINANCE_ID',sysdate, null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '0', 1, '财政处室', '1', 6, null, null, 0, null, null, '1', '0', '51CF682FA0101A1EE0533A06A8C0EE75', null,sysdate, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DBVERSION, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, INSERTVERSION, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '部门ID', null, null, null, null, '部门ID', '51CF682FA0171A1EE0533A06A8C0EE75', null, 32, 3, 'DEPT_ID', sysdate, null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '0', 1, '部门', '1', 7, null, null, 0, null, null, '1', '0', '51CF682FA0101A1EE0533A06A8C0EE75', null,sysdate, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DBVERSION, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, INSERTVERSION, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '专项唯一标识', null, null, null, null, '专项唯一标识', '51CF682FA0191A1EE0533A06A8C0EE75', null, 32, 3, 'SPFID',sysdate, null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '0', 1, '专项', '1', 9, null, null, 0, null, null, '1', '0', '51CF682FA0101A1EE0533A06A8C0EE75', null, sysdate, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DBVERSION, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, INSERTVERSION, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '项目唯一标识', null, null, null, null, '项目唯一标识', '51CF682FA01A1A1EE0533A06A8C0EE75', null, 32, 3, 'PROJECTID',sysdate, null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '0', 1, '项目', '1', 10, null, null, 0, null, null, '1', '0', '51CF682FA0101A1EE0533A06A8C0EE75', null,sysdate, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DBVERSION, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, INSERTVERSION, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '关联ID', null, null, null, null, '关联ID', '51CF682FA01B1A1EE0533A06A8C0EE75', null, 32, 3, 'REALID', sysdate, null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '1', '0', '0', 1, '关联ID', '1', 11, null, null, 0, null, null, '1', '0', '51CF682FA0101A1EE0533A06A8C0EE75', null, sysdate, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DBVERSION, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, INSERTVERSION, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '项目名称', null, null, null, null, '项目名称', '51CF682FA01B1A1EE0533A06A8C01234', null, 500, 3, 'XMXSL', sysdate, null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '1', '0', '1', 1, '财政/部门/专项/项目', '1', 11, null, null, 0, null, 500, '1', '0', '51CF682FA0101A1EE0533A06A8C0EE75', null, sysdate, '0', null);


  END LOOP;

  sqlstr := 'CREATE TABLE P#KZSB (  
         DATAKEY VARCHAR2 ( 32 ) DEFAULT sys_guid() ,
         ORDERID NUMBER ( 9,0 ) ,
         NEEDUPDATE VARCHAR2 ( 400 ) ,
         FINYEAR VARCHAR2 ( 4 ) ,
         AGENCYID VARCHAR2 ( 32 ) ,
         FINANCE_ID VARCHAR2 ( 32 ) ,
         DEPT_ID VARCHAR2 ( 32 ) ,
         SPFID VARCHAR2 ( 32 ) ,
         PROJECTID VARCHAR2 ( 32 ) ,
         REALID VARCHAR2 ( 32 ),
         XMXSL VARCHAR2 ( 500 )
  ) ' ;
  execute immediate sqlstr;
  
  sys_p_partition_table('P#KZSB');
  
  sqlstr := 'ALTER TABLE P#KZSB ADD CONSTRAINT PK_P#KZSB PRIMARY KEY  (PROVINCE,YEAR,STATUS,AGENCYID,DATAKEY ) ';
  execute immediate sqlstr;
  
end;




--控制数_新建1个控制数表
