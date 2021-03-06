BEGIN
  
   --删除合同批次model
  DELETE FROM dict_t_model where tableid ='E1F9D17D856A444183F9158057784055';
  --删除合同批次表字段
  DELETE FROM dict_t_factor where tableid ='E1F9D17D856A444183F9158057784055';
  
  --添加项目阶段合同视图model
  insert into dict_t_model (APPID, BGTLVL, DBTABLENAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLEID, TABLETYPE, TABSWHERE, ISBAK)
  values ('SPF', null, 'SPF_V_PCONTRACT', '5*18', '0000000000000000000000000000000', '1', '0', '0', '0', '0', '1', null, '0', null, '项目阶段合同视图', 1, null, null, null, null, '1', null, '1', '1470431B25044D2BE050A8C02105302D', 'E1F9D17D856A444183F9158057784055', '2', null, null);
  
  --添加项目阶段合同视图字段
  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '05EBB857A38A43C58EED939AD8159F52', null, 323, 3, 'AGENCYNAME', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '单位名称', '1', 27, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '1486D9CAA42B43D0822D58D2D2C22178', null, 32, 3, 'CONTRACTID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, 'CONTRACTID', '1', 30, null, null, 0, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '1616CA054947C781E050A8C0210512EA', null, 234, 3, 'PROJGROUP_NAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '项目分组名称', '0', 31, null, null, 0, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '1616CA05494BC781E050A8C0210512EA', '148830CAFDC51F78E050A8C021056ABE', 32, 6, 'DEPTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '财政内部机构', '0', 32, null, null, 0, '4', null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '178907B846464CE49649E4666AD214CB', null, 32, 3, 'AGENCYID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'AGENCYID', '0', 25, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '1FDF8D3060F54DFBBB97016EEDE848D5', null, 100, 3, 'PROJTYPENAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '一级专项', '0', 15, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '52150D1A95BA4927988DA6C125281499', null, 200, 3, 'PROJNAME', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '项目名称', '0', 22, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '5752311089D34AFAA0654616A24B25F3', null, 32, 3, 'CONTRACT_NAME', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '合同名称', '0', 2, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '637492BF56DC4457A7F0667C380BE0B5', null, 32, 3, 'FIRAGENCYID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'FIRAGENCYID', '0', 26, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '64A07BF7B3A74A8496EC6A18CE2DAECD', null, 32, 3, 'PROJECTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'PROJECTID', '0', 20, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '781B098F24504FBB94D061E808B1D50F', null, 369, 3, 'EXPFUNCCLASS', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '功能科目', '1', 3, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '7F8A3F9DBA96445EBB3217DD3C439C7E', null, 60, 3, 'PROJCODE', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '项目编码', '1', 21, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'B0FA85B43C494904A50358A5075913D9', null, 32, 3, 'CONTRACTGROUP_NAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '合同分组名称', '0', 18, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'B7E8D13F17E141B08EEF27F0EFD66C60', null, 32, 3, 'DISTRICTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'DISTRICTID', '1', 9, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'BCE50365AB824A77B152237FC23FAE66', null, 32, 3, 'SPFID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'SPFID', '0', 28, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'CC2BB903803F47EC8E87CE70F1029951', null, 32, 3, 'PROJTYPEID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'PROJTYPEID', '1', 29, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'D5840CA3304A45AFAF2992A47CAA6040', null, 32, 3, 'DATAKEY', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'DATAKEY', '1', 23, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'DF8FAB14A0384739A4DC1519C666DE5B', null, 32, 3, 'BPMNID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'BPMNID', '0', 24, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'F5747C1769F34A72B4BECE42D0A235AA', null, 32, 3, 'EXPFUNCID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'EXPFUNCID', '0', 10, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'F9CED72095F2414D8A4A5B6AE4EBA383', null, 200, 3, 'SPFNAME', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '专项名称', '0', 19, null, null, null, null, null, '1', '0', 'E1F9D17D856A444183F9158057784055', null, '0', null);

  
  EXECUTE IMMEDIATE '
    CREATE OR REPLACE VIEW SPF_V_PCONTRACT AS
      SELECT (nvl(STPC.DATAKEY, A.DATAKEY)) DATAKEY,
         A.PROJECTID BPMNID,
         A.AGENCYID,
         F.FIRAGENCYID,
         AGENCY.NAME AGENCYNAME,
         A.SPFID,
         A.PROJTYPEID,
         F.SPFNAME,
         A.PROJECTID,
         A.PROJCODE,
         A.PROJNAME PROJNAME,
         A.PROJNAME || ''{'' || A.PROJECTID || ''}'' PROJGROUP_NAME,
         (SELECT ACCT.NAME
            FROM CODE_V_ACCTCODE_OUT_SPF ACCT
           WHERE ACCT.GUID = a.EXPFUNCID) AS EXPFUNCCLASS,
         A.DISTRICTID,
         F.EXPFUNCID,
         F.DEPTID,
         (SELECT PROJTYPENAME
            FROM SPF_T_PROJECTTYPE
           where PROJTYPEID = A.PROJTYPEID) PROJTYPENAME,
         STPC.DATAKEY CONTRACTID,
         STPC.CONTRACT_NAME,
         STPC.CONTRACT_NAME || ''{'' || STPC.DATAKEY || ''}'' CONTRACTGROUP_NAME
      FROM SPF_T_PBASEINFO A
      inner join SPF_T_FBASEINFO F
      on A.SPFID = F.SPFID
      inner join CODE_T_FIRAGENCY AGENCY
      on F.FIRAGENCYID = AGENCY.GUID
      left join SPF_T_P_CONTRACT STPC
      on A.PROJECTID = STPC.PROJECTID
   ';
  EXECUTE IMMEDIATE '
    comment on table SPF_V_PCONTRACT is ''项目阶段合同主表''
  ';

END;
--SPF_V_PCONTRACT(项目阶段合同视图)
