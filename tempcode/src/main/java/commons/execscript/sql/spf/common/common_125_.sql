BEGIN
  
   --ɾ����Ŀ�׶κ�ͬ���·���ͼmodel
  DELETE FROM dict_t_model where tableid ='33B6826A67014FCAB9EC1F41B485BCB6';
  --ɾ����Ŀ�׶κ�ͬ���·���ͼ�ֶ�
  DELETE FROM dict_t_factor where tableid ='33B6826A67014FCAB9EC1F41B485BCB6';
  
  --������Ŀ�׶κ�ͬ���·���ͼmodel
  insert into dict_t_model (APPID, BGTLVL, DBTABLENAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLEID, TABLETYPE, TABSWHERE, ISBAK)
  values ('SPF', null, 'SPF_V_PCONISSUED', '5*17', '0000000000000000000000000000000', '1', '0', '0', '0', '0', '1', null, '0', null, '��Ŀ�׶κ�ͬ���·���ͼ', 1, null, null, null, null, '1', null, '1', '1470431B25044D2BE050A8C02105302D', '33B6826A67014FCAB9EC1F41B485BCB6', '2', null, null);

  --������Ŀ�׶κ�ͬ���·���ͼ�ֶ�
  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '1607A121988241B1E050A8C02105646D', '148830CAFDC51F78E050A8C021056ABE', 32, 6, 'DEPTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '�����ڲ�����', '0', 14, null, null, 0, '4', null, '1', '0', '33B6826A67014FCAB9EC1F41B485BCB6', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '1607A121988441B1E050A8C02105646D', '14DA206175AB0A8FE050A8C021055537', 32, 6, 'FIRAGENCYID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '���ܲ�������', '0', 15, null, null, 0, '4', null, '1', '0', '33B6826A67014FCAB9EC1F41B485BCB6', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '6D2317783750488AA72D0DB829BE0B3D', null, 32, 3, 'DATAKEY', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'DATAKEY', '1', 1, null, null, null, null, null, '1', '0', '33B6826A67014FCAB9EC1F41B485BCB6', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '8EF8C1667489488596081F1F45D1D662', null, 32, 3, 'PROJTYPEID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'PROJTYPEID', '1', 4, null, null, null, null, null, '1', '0', '33B6826A67014FCAB9EC1F41B485BCB6', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, '9EE9A335CC484E46B9380F6E7DB42F2B', null, 32, 3, 'AGENCYID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '��λ����', '0', 13, null, null, 0, null, null, '1', '0', '33B6826A67014FCAB9EC1F41B485BCB6', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'A0B739C0A59A48A79BE8A57F3E3013A1', null, 32, 3, 'SPFID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'SPFID', '0', 3, null, null, null, null, null, '1', '0', '33B6826A67014FCAB9EC1F41B485BCB6', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'AB120A075D1E482F887CE688B83C59CC', null, 234, 3, 'PROJGROUP_NAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '��Ŀ��������', '0', 8, null, null, null, null, null, '1', '0', '33B6826A67014FCAB9EC1F41B485BCB6', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'B497256FAE5546068714D9F8345BAD8A', null, 200, 3, 'PROJNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '��Ŀ����', '0', 7, null, null, null, null, null, '1', '0', '33B6826A67014FCAB9EC1F41B485BCB6', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'B5B37E0EE32F4F7E98C56F00D2EE1786', null, 32, 3, 'BPMNID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'BPMNID', '0', 2, null, null, null, null, null, '1', '0', '33B6826A67014FCAB9EC1F41B485BCB6', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'BC04F4592E484D13B316B2AEEA27BC9B', null, 100, 3, 'PROJTYPENAME', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, 'һ��ר��', '0', 12, null, null, null, null, null, '1', '0', '33B6826A67014FCAB9EC1F41B485BCB6', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'E991F0A67E5C4F5B9267C4B39B5AA1CE', null, 32, 3, 'PROJECTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, 'PROJECTID', '0', 6, null, null, null, null, null, '1', '0', '33B6826A67014FCAB9EC1F41B485BCB6', null, '0', null);

  insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (null, null, null, null, null, null, 'FF62D148524A44E086058125C72AA836', null, 200, 3, 'SPFNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, 'ר������', '0', 5, null, null, null, null, null, '1', '0', '33B6826A67014FCAB9EC1F41B485BCB6', null, '0', null);

  
  EXECUTE IMMEDIATE '
    CREATE OR REPLACE VIEW SPF_V_PCONISSUED AS
    SELECT A.DATAKEY,
           A.PROJECTID BPMNID,
           A.SPFID,
           A.PROJTYPEID,
           F.SPFNAME,
           A.PROJECTID,
           A.PROJNAME PROJNAME,
           A.PROJNAME || ''{'' || A.PROJECTID || ''}'' PROJGROUP_NAME,
           (SELECT PROJTYPENAME
              FROM SPF_T_PROJECTTYPE
             where PROJTYPEID = A.PROJTYPEID) PROJTYPENAME,
           A.AGENCYID,
           F.DEPTID,
           F.FIRAGENCYID
      FROM SPF_T_PBASEINFO A
     inner join SPF_T_FBASEINFO F
        on A.SPFID = F.SPFID
     inner join CODE_T_FIRAGENCY AGENCY
        on F.FIRAGENCYID = AGENCY.GUID
   ';
  EXECUTE IMMEDIATE '
    comment on table SPF_V_PCONISSUED is ''��Ŀ�׶κ�ͬ���·���ͼ''
  ';

END;
--SPF_V_PCONISSUED(��Ŀ�׶κ�ͬ���·���ͼ)