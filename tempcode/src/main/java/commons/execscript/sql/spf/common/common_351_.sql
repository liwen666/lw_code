declare 
viewNumber number;
begin
  delete from fasp_t_pubmenu where appid='hqoa' and guid='1200020033333';
  
  insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
  values ('1200020033333', 2, 1, '1', '004010012', '项目综合处理', '25CAE63BC46EC42A93EAB74F1B877589', '/spf/project/query/queryMain.do?dealtype=PROJZHCL', 1, 'remark', 1, null, null, 'hqoa', null, null, null, null, null, null, 1, null);
  
  delete from DICT_T_DEALTYPE where appid='SPF' and DEALID='PROJZHCL';
  
  insert into DICT_T_DEALTYPE (APPID, DEALID, DEALNAME, ORDERID, NEEDCONFIG, ISUNIQUE)
  values ('SPF', 'PROJZHCL', '项目综合处理', 35, '0', '0');
  
  delete from dict_t_defaultcol where DEALID='PROJZHCL';

  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('PROJZHCL', 1, '行标志', 'DATAKEY', 3, 32, null, null, '1', '1', '4BC2D5BA2D833606E0533A06A8C03781', '1', 'sys_guid()', null, '080001', '0', '1');
	
  
  select count(1) into viewNumber from user_views where VIEW_NAME='SPF_T_PROJZHCLQUERY';
    if viewNumber > 0 then
    --删除  专项托管表物理视图
    EXECUTE IMMEDIATE Q'{
      drop view SPF_T_PROJZHCLQUERY
    }';
  end if;
  
  EXECUTE IMMEDIATE Q'/
    CREATE OR REPLACE VIEW SPF_T_PROJZHCLQUERY AS
      SELECT P.DBVERSION,
             P.STATUS,
             P.PROJCODE,
             P.PROJNAME,
             P.CREATEUSER,
             P.ISPUBSHOW,
             P.CREATEUSERNAME,
             P.PROJECTID,
             P.ISDISTRICT,
             P.ISJX,
             P.SPFID,
             P.DISTRICTID,
             PB.ISINDEX,
             P.ISMEASURED,
             P.PROJTYPEID,
             P.ISADD,
             P.ENDYEAR,
             P.STARTYEAR,
             P.EXPFUNCID,
             PB.ISBGT,
             P.ISINSTEAD,
             PB.CHECKSTATUS,
             P.DATAKEY,
             P.CREATETIME,
             P.AGENCYID,
             P.ISTEMP,
             PROJNAME||'{'||P.PROJECTID||'}' PROJGROUP_NAME,
             (SELECT FIRAGENCYID FROM SPF_T_FBASEINFO F WHERE F.SPFID = P.SPFID ) FIRAGENCYID,
             (SELECT DISTRICTID FROM SPF_T_FBASEINFO F WHERE F.SPFID = P.SPFID ) SPF_DISTRICTID,
             (SELECT DEPTID FROM SPF_T_FBASEINFO F WHERE F.SPFID = P.SPFID ) DEPTID
        FROM SPF_T_PBASEINFO P,SPF_T_PBASESTATUS PB WHERE P.PROJECTID = PB.PROJECTID 
  /';
  
  delete from p#dict_t_model where tableid ='4CEECDD3AF1A497FE0533A06A8C01266';
  delete from p#dict_t_factor t where t.TABLEID ='4CEECDD3AF1A497FE0533A06A8C01266';

  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
    
    insert into p#dict_t_model (YEAR, PROVINCE, TABLEID, APPID, DBTABLENAME, NAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, ORDERID, RELATAB, REMARK, SECUSQL, BGTLVL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, INITSQLBTNNAME, ISEXCELIMP, ISREADONLY, DESCFILE, ISSAVEFORMULA)
    values (v_row.YEAR, v_row.DISTRICTID, '4CEECDD3AF1A497FE0533A06A8C01266', 'SPF', 'SPF_T_PROJZHCLQUERY', '项目综合处理', 'PROJZHCL', '0000000000000000000000000000000', '3', '0', '0', '0', '0', '1', null, '0', null, 1, null, null, null, null, null, '1', null, '1', 'FB12E708FE56425F82EF3D89FDE0D0EF', '2', null, null, null, null, null, null, '1', null, '0');
  
    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF1B497FE0533A06A8C01266', null, 1, 3, 'STATUS', '''1''', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, 'STATUS', '1', 1, null, null, null, null, null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF1C497FE0533A06A8C01266', null, 60, 3, 'PROJCODE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '项目编码', '1', 7, null, null, null, null, 200, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF1D497FE0533A06A8C01266', null, 300, 3, 'PROJNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '项目名称', '1', 3, null, null, null, null, null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF1E497FE0533A06A8C01266', null, 32, 3, 'CREATEUSER', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '申报人', '1', 4, null, null, null, null, null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF1F497FE0533A06A8C01266', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISPUBSHOW', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否公示', '1', 5, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF21497FE0533A06A8C01266', null, 1000, 3, 'CREATEUSERNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '项目申报人', '1', 9, null, null, null, null, null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF22497FE0533A06A8C01266', null, 32, 3, 'PROJECTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '项目唯一标识', '0', 10, null, null, null, null, null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF23497FE0533A06A8C01266', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISDISTRICT', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否区划', '1', 11, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF24497FE0533A06A8C01266', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISJX', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否绩效', '1', 12, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF25497FE0533A06A8C01266', '14DADAE88B8B57D0E050A8C0210557DD', 32, 6, 'SPFID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '专项名称', '0', 13, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF2A497FE0533A06A8C01266', 'D754323AE16C448AAB86064FA77B96CE', 32, 6, 'DISTRICTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '申报地区', '1', 31, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF2D497FE0533A06A8C01266', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISINDEX', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否追加指标', '1', 20, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF2E497FE0533A06A8C01266', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISMEASURED', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否测算分配项目', '1', 21, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF2F497FE0533A06A8C01266', null, 32, 3, 'PROJTYPEID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '项目类别', '1', 24, null, null, null, null, null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF31497FE0533A06A8C01266', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISADD', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否追加', '1', 27, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF32497FE0533A06A8C01266', '9786827170284907A734AF1107A3B65D', 32, 6, 'ENDYEAR', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '资金申请结束年', '1', 30, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF34497FE0533A06A8C01266', '9786827170284907A734AF1107A3B65D', 32, 6, 'STARTYEAR', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '资金申请起始年', '1', 29, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF36497FE0533A06A8C01266', 'E48AD8120DD1434F8A36295480DA5A4D', 32, 6, 'EXPFUNCID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '支出功能分类科目', '0', 28, null, null, null, '4', 120, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF38497FE0533A06A8C01266', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISBGT', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '是否纳入预算', '1', 42, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF39497FE0533A06A8C01266', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISINSTEAD', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否代录', '1', 43, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF3A497FE0533A06A8C01266', null, 32, 3, 'CHECKSTATUS', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '审核状态', '1', 44, null, null, null, null, null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF3B497FE0533A06A8C01266', null, 32, 3, 'DATAKEY', 'sys_guid()', '080001', '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '1', '0', '1', '0', '0', 1, '行标志', '0', 45, null, null, null, null, null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF3E497FE0533A06A8C01266', null, 20, 3, 'CREATETIME', 'to_char(sysdate,''fxyyyy-mm-dd'')', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '项目申报时间', '1', 48, null, null, null, '2', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF41497FE0533A06A8C01266', '1708CF6F853F76EDE050A8C0210507FC', 32, 6, 'AGENCYID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '预算单位', '1', 25, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF42497FE0533A06A8C01266', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISTEMP', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否临时项目', '1', 51, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF43497FE0533A06A8C01266', null, 234, 3, 'PROJGROUP_NAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '项目分组名称', '0', 91, null, null, null, null, null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF44497FE0533A06A8C01266', '14DA206175AB0A8FE050A8C021055537', 32, 6, 'FIRAGENCYID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '主管部门', '0', 92, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF45497FE0533A06A8C01266', '5771989AF427418B84E84CEDD68C8837', 32, 6, 'SPF_DISTRICTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '专项地区', '1', 14, null, null, null, '4', 130, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4CEECDD3AF46497FE0533A06A8C01266', '8FCC6B91BADC44E0BC73A2CC22CC9FBF', 32, 6, 'DEPTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '归口处室', '0', 94, null, null, null, '4', null, '1', '0', '4CEECDD3AF1A497FE0533A06A8C01266', null, '0', null);
    
  end loop;
end;
--项目综合处理
