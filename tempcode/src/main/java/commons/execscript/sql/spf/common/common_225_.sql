BEGIN
execute immediate Q'{CREATE  OR REPLACE VIEW CODE_T_AGENCY_DEPT_SPF AS
SELECT GUID, CODE, NAME, SUPERGUID, ISLEAF, LEVELNO, DISTRICTID, STATUS
  FROM (SELECT GUID,
               CODE,
               NAME,
               SUPERGUID,
               ISLEAF,
               LEVELNO,
               DISTRICTID,
               '1' STATUS
          FROM CODE_T_AGENCY_SPF
        UNION ALL
        SELECT GUID,
               CODE,
               NAME,
               SUPERGUID,
               ISLEAF,
               LEVELNO,
               DISTRICTID,
               STATUS
          FROM CODE_T_DEPT_SPF)}';
   DELETE FROM P#DICT_T_MODELCODE WHERE tableid ='379634C2CAFF55E0E053CB01A8C0CC9B';
   DELETE FROM P#DICT_T_FACTORCODE WHERE tableid ='379634C2CAFF55E0E053CB01A8C0CC9B';
   for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop 
    INSERT INTO P#DICT_T_MODELCODE (YEAR, PROVINCE, APPID, TABLEID, NAME, DBTABLENAME, ORDERID, ISREPBASE, ISLVL, SQLCON, DYNAMICWHERE, ISORGID, STATUS, FASPCSID, ISFASP, TABLETYPE)
    VALUES (V_ROW.YEAR, V_ROW.DISTRICTID, 'SPF', '379634C2CAFF55E0E053CB01A8C0CC9B', '��Ŀ�⣨��λ�������ң������', 'CODE_T_AGENCY_DEPT_SPF', 1, '1', '0', null, null, '0', '1', '379634C2CAFF55E0E053CB01A8C0CC9B', '0', null);

    INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    VALUES (V_ROW.YEAR, V_ROW.DISTRICTID, '379634C2CAFF55E0E053CB01A8C0CC9B', null, '379634C2CB0055E0E053CB01A8C0CC9B', 'SUPERGUID', null, 'SUPERGUID', 3, 32, null, null, null, '0', '0', null, '1');

    INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    VALUES (V_ROW.YEAR, V_ROW.DISTRICTID, '379634C2CAFF55E0E053CB01A8C0CC9B', null, '379634C2CB0155E0E053CB01A8C0CC9B', 'ISLEAF', null, 'ISLEAF', 1, 0, 0, null, null, '0', '0', null, '1');

    INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    VALUES (V_ROW.YEAR, V_ROW.DISTRICTID, '379634C2CAFF55E0E053CB01A8C0CC9B', null, '379634C2CB0255E0E053CB01A8C0CC9B', 'LEVELNO', null, 'LEVELNO', 1, 0, 0, null, null, '0', '0', null, '1');

    INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    VALUES (V_ROW.YEAR, V_ROW.DISTRICTID, '379634C2CAFF55E0E053CB01A8C0CC9B', null, '379634C2CB0355E0E053CB01A8C0CC9B', 'DISTRICTID', null, 'DISTRICTID', 3, 32, null, null, null, '0', '0', null, '1');

    INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    VALUES (V_ROW.YEAR, V_ROW.DISTRICTID, '379634C2CAFF55E0E053CB01A8C0CC9B', null, '379634C2CB0455E0E053CB01A8C0CC9B', 'STATUS', null, 'STATUS', 3, 1, null, null, null, '0', '0', null, '1');

    INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    VALUES (V_ROW.YEAR, V_ROW.DISTRICTID, '379634C2CAFF55E0E053CB01A8C0CC9B', null, '379634C2CB0555E0E053CB01A8C0CC9B', 'GUID', null, 'GUID', 3, 32, null, null, null, '0', '0', null, '1');

    INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    VALUES (V_ROW.YEAR, V_ROW.DISTRICTID, '379634C2CAFF55E0E053CB01A8C0CC9B', null, '379634C2CB0655E0E053CB01A8C0CC9B', 'CODE', null, 'CODE', 3, 200, null, null, null, '0', '0', null, '1');

    INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    VALUES (V_ROW.YEAR, V_ROW.DISTRICTID, '379634C2CAFF55E0E053CB01A8C0CC9B', null, '379634C2CB0755E0E053CB01A8C0CC9B', 'NAME', null, 'NAME', 3, 457, null, null, null, '0', '0', null, '1');
   END LOOP;
END;

--��Ŀ�⣨��λ�������ң������