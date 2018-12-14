DECLARE
  v_count integer;
BEGIN
SELECT COUNT(1) INTO v_count FROM User_Views WHERE VIEW_NAME='KPI_V_FMAIN';
IF v_count=0 THEN
  EXECUTE IMMEDIATE Q'{
  CREATE OR REPLACE VIEW KPI_V_FMAIN AS
  SELECT SPFNAME||'{'||spfid SPFGROUP_NAME,
  ''KPITYPE,
  ''BPMNID,
  ''PROCESSID,
  EXPFUNCID,
  ''BPMNTYPENAME,
  ''STATUSID,
  SPFID,
  FIRAGENCYID,
  DATAKEY,
  PROJTYPEID,
  ''ISFLOW,
  ENDYEAR,
  DISTRICTID,
  '' BPMNTYPE,
  AGENCYID,
  DEPTID,
  SPFCODE,
  BEGINYEAR
    FROM spf_t_Fbaseinfo a}';
END IF;

  DELETE FROM dict_t_model WHERE DBTABLENAME='KPI_V_FMAIN'OR TABLEID = '3044E1324C9F4B95E050A8C0210525BB';
	
  insert into dict_t_model (APPID, BGTLVL, DBTABLENAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLEID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, DESCFILE)
  values ('KPI', null, 'KPI_V_FMAIN',  'KF40', '0000000000000000000000000000000', '3', '0', '0', '0', '0', '1', null, '0', null, 'ר�Ч���б���ͼ', 1, null, null, null, '<CLOB>', '1', null, '1', 'F6A4E2F5QF0996DFE040E8E0200390B2', '3044E1324C9F4B95E050A8C0210525BB', '2', null, NULL, null, null);

  DELETE FROM DICT_T_FACTOR WHERE  TABLEID ='3044E1324C9F4B95E050A8C0210525BB';
	
	insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
	values ('ר���ʽ��������', null, null, null, null, 'ר���ʽ��������', '3044E1324CA04B95E050A8C0210525BB', null, 234, 3, 'SPFGROUP_NAME', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, 'ר���ʽ��������', '1', 1, null, null, 0, null, null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

	insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
	values ('����״̬', null, null, null, null, '����״̬', '3044E1324CA14B95E050A8C0210525BB', null, 1, 1, 'KPITYPE', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '����״̬', '1', 2, null, null, 0, null, null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('����Ψһ��ʶ', null, null, null, null, '����Ψһ��ʶ', '3044E1324CA24B95E050A8C0210525BB', null, 32, 3, 'BPMNID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '����Ψһ��ʶ', '1', 3, null, null, 0, null, null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('��������', null, null, null, null, '��������', '3044E1324CA34B95E050A8C0210525BB', null, 32, 3, 'PROCESSID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '��������', '1', 4, null, null, 0, null, null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('���ܿ�Ŀ', null, null, null, null, '���ܿ�Ŀ', '3044E1324CA44B95E050A8C0210525BB', null, 32, 3, 'EXPFUNCID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '���ܿ�Ŀ', '1', 5, null, null, null, '0', null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('��ǰ��Ч����', null, null, null, null, '��ǰ��Ч����', '3044E1324CA54B95E050A8C0210525BB', null, 32, 3, 'BPMNTYPENAME', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '��ǰ��Ч����', '1', 6, null, null, 0, null, null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('����״̬', null, null, null, null, '����״̬', '3044E1324CA64B95E050A8C0210525BB', null, 2, 3, 'STATUSID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '����״̬', '1', 7, null, null, 0, null, null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('ר��Ψһ��ʶ', null, null, null, null, 'ר��Ψһ��ʶ', '3044E1324CA74B95E050A8C0210525BB', null, 32, 3, 'SPFID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, 'ר��Ψһ��ʶ', '1', 8, null, null, null, '0', null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('���ܲ���', null, null, null, null, '���ܲ���', '3044E1324CA84B95E050A8C0210525BB', null, 32, 3, 'FIRAGENCYID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '���ܲ���', '1', 9, null, null, null, '0', null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('�б�־', null, null, null, null, '�б�־', '3044E1324CA94B95E050A8C0210525BB', null, 32, 3, 'DATAKEY', 'sys_guid()', '080001', null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '�б�־', '1', 10, null, null, 0, null, null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('ר�����', null, null, null, null, 'ר�����', '3044E1324CAA4B95E050A8C0210525BB', null, 32, 3, 'PROJTYPEID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, 'ר�����', '1', 11, null, null, null, '0', null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('ISFLOW', null, null, null, null, 'ISFLOW', '3044E1324CAB4B95E050A8C0210525BB', null, 1, 1, 'ISFLOW', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, 'ISFLOW', '1', 12, null, null, 0, null, null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('�������', null, null, null, null, '�������', '3044E1324CAC4B95E050A8C0210525BB', null, 10, 3, 'ENDYEAR', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '�������', '1', 13, null, null, 0, null, null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('��������', null, null, null, null, '��������', '3044E1324CAD4B95E050A8C0210525BB', null, 32, 3, 'DISTRICTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '��������', '1', 14, null, null, null, '0', null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('������״̬��', null, null, null, null, '������״̬��', '3044E1324CAE4B95E050A8C0210525BB', null, 8, 3, 'BPMNTYPE', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '������״̬��', '1', 15, null, null, 0, null, null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('Ԥ�㵥λ', null, null, null, null, 'Ԥ�㵥λ', '3044E1324CAF4B95E050A8C0210525BB', null, 32, 3, 'AGENCYID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, 'Ԥ�㵥λ', '1', 16, null, null, null, '0', null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('��ڹ�������', null, null, null, null, '��ڹ�������', '3044E1324CB04B95E050A8C0210525BB', null, 32, 3, 'DEPTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '��ڹ�������', '1', 17, null, null, null, '0', null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('ר����', null, null, null, null, 'ר����', '3044E1324CB14B95E050A8C0210525BB', null, 150, 3, 'SPFCODE', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, 'ר����', '1', 18, null, null, 0, null, null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);

  insert into DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values ('��ʼ���', null, null, null, null, '��ʼ���', '3044E1324CB24B95E050A8C0210525BB', null, 10, 3, 'BEGINYEAR', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '��ʼ���', '1', 19, null, null, 0, null, null, '1', '0', '3044E1324C9F4B95E050A8C0210525BB', null, '0', null);


END;
--��Ч_����KPI_V_FMAIN_����ƽ