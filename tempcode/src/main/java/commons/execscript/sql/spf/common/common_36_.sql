
--新增财政核定
DECLARE
   V_COL INT;
   V_BAKCOL INT;
BEGIN
  SELECT COUNT(*) INTO V_COL   FROM COLS  WHERE TABLE_NAME = UPPER('P#SPF_T_PBASEINFO')    AND COLUMN_NAME = UPPER('CZHD');
  IF V_COL=0 THEN
    EXECUTE IMMEDIATE 'ALTER TABLE P#SPF_T_PBASEINFO  ADD (  CZHD  VARCHAR2(32))' ;
   delete from dict_t_factor where COLUMNID ='22ACDB4F4582FD47E050A8C021057C65';
    insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (null, null, null, null, null, null, '22ACDB4F4582FD47E050A8C021057C65', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'CZHD', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '财政核定', '1', 43, null, null, 0, '4', null, '1', '0', '9FC09B974CCE4342BB190FA2A47D4B20', null, '0', null);
  END IF;
   SELECT COUNT(*) INTO V_BAKCOL  FROM User_Tables WHERE TABLE_NAME = UPPER('P#SPF_T_PBASEINFO_BAK');
   SELECT COUNT(*) INTO V_COL   FROM COLS  WHERE TABLE_NAME = UPPER('P#SPF_T_PBASEINFO_BAK')    AND COLUMN_NAME = UPPER('CZHD');
    IF V_BAKCOL=1 AND V_COL = 0 THEN
       EXECUTE IMMEDIATE 'ALTER TABLE P#SPF_T_PBASEINFO_BAK  ADD (  CZHD  VARCHAR2(32))' ;
    END IF;
     SYS_P_RECREATE_VIEWS('9FC09B974CCE4342BB190FA2A47D4B20');
END;--新增财政核定

