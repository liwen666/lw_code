DECLARE
      V_CNT NUMBER;
BEGIN
  
  --判断表字段是否存在
  SELECT COUNT(1) INTO V_CNT FROM User_Tab_Cols b WHERE b.TABLE_NAME ='P#SPF_T_YWQLSB' AND COLUMN_NAME ='DISTRICTID';
  IF v_CNT = 0 THEN
    EXECUTE IMMEDIATE 'ALTER TABLE P#SPF_T_YWQLSB  ADD (DISTRICTID NUMBER(24,6))' ;
  END IF; 
  --新增APPLYNUMfactor
  FOR TAB IN (SELECT a.DISTRICTID CODE,year  FROM pub_t_partition_divid a WHERE a.year <> '*') LOOP
     SELECT COUNT(1) INTO V_CNT FROM dict_t_factor WHERE columnid ='53178E749EC23C8AE0533906A8C05505';
     IF   V_CNT = 0 THEN
      DELETE FROM dict_t_factor WHERE dbcolumnname ='DISTRICTID' AND tableid ='4AE7F264D0773498E0533A06A8C0428E';
    insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (TAB.year, TAB.code, '', '', '', '', '', '', '53178E749EC23C8AE0533906A8C05505', '', 18, 1, 'DISTRICTID', '0', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '地区', '1', 13, '', '', 0, '0', null, '1', '0', '4AE7F264D0773498E0533A06A8C0428E', '', '0', '');

    END IF;
  END LOOP;
     sys_p_recreate_views('4AE7F264D0773498E0533A06A8C0428E');
END;
--临时表添加districtid
