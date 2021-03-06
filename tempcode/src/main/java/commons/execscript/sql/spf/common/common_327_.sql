DECLARE 
viewNumber number;

BEGIN
  
  SELECT COUNT(1) INTO viewNumber FROM USER_VIEWS WHERE VIEW_NAME='CODE_T_DISTRICT_SPF_CS';
    IF viewNumber > 0 THEN
    --删除  项目公文选择
    EXECUTE IMMEDIATE Q'{
      drop view CODE_T_DISTRICT_SPF_CS
    }';
  END IF;
  --创建 地区代码表  
  EXECUTE IMMEDIATE Q'{
     CREATE OR REPLACE VIEW CODE_T_DISTRICT_SPF_CS AS
        SELECT PROVINCE,
               GUID,
               CODE,
               '['||CODE||']'||NAME NAME,
               ISLEAF,
               LEVELNO,
               SUPERGUID,
               '' ALIAS,
               '' PINYIN,
               '' REMARK,
               '' CREATEDATE,
               '' STARTDATE,
               '' ENDDATE,
               '' SRCGUID,
               '' DESGUID,
               SRCSCALE,
               STATUS,
               DBVERSION,
               '' EMW,
               BGTLEVEL,
               STDCODE,
               DISTRLVL,
               ZGXIAN,
               ISSELF,
               PROVINCE_SH,
               CITY,
               COUNTY,
               PROVINCE_SHID,
               CITYID,
               COUNTYID,
               YEAR
          FROM CODE_M_DISTRICT
         WHERE YEAR = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR')
          ORDER BY CODE
   }';
   
   --删除
   DELETE FROM P#DICT_T_MODELCODE WHERE TABLEID ='5771989AF427418B84E84CEDD68C8837';
   DELETE FROM P#DICT_T_FACTORCODE WHERE TABLEID ='5771989AF427418B84E84CEDD68C8837';
   --重新增加
   FOR v_row IN (SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP 
     
      INSERT INTO P#DICT_T_MODELCODE (YEAR, PROVINCE, APPID, FASPCSID, DBTABLENAME, TABLEID, NAME, DYNAMICWHERE, ISLVL, ISFASP, ISORGID, ISREPBASE, ORDERID, SQLCON, STATUS, TABLETYPE)
      VALUES (v_row.YEAR, v_row.DISTRICTID, 'SPF', '5771989AF427418B84E84CEDD68C8837', 'CODE_T_DISTRICT_SPF_CS', '5771989AF427418B84E84CEDD68C8837', '测算地区代码表', null, '0', '0', '0', '1', 1, null, '1',  '2');

    
      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '072720C1F5974008B1404613FD5D02F8', null, 200, 3, 'CODE', null, null, '0', '0', 'CODE', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '129C4DA106F04D5C988A85135DA62002', null, 0, 1, 'DISTRLVL', null, null, '0', '0', 'DISTRLVL', null, 0, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '16962E3DB6A24830A5F0D7C5527D234C', null, 32, 3, 'COUNTYID', null, null, '0', '0', 'COUNTYID', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '2D077F8DF4D14D018F73870AB627D114', null, 32, 3, 'CITYID', null, null, '0', '0', 'CITYID', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '2D83461229D64C2E93A43EDF7F9235F1', null, 457, 3, 'COUNTY', null, null, '0', '0', 'COUNTY', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '2DB1914F035F47FABA8C1AD8F5541F10', null, 1, 3, 'ISSELF', null, null, '0', '0', 'ISSELF', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '36FA4C4EC9F642FE8938140CB02FDB3F', null, 0, 1, 'SRCSCALE', null, null, '0', '0', 'SRCSCALE', null, 0, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '5016BA4F76FC4B21A7948C2DF5146936', null, 0, 3, 'ALIAS', null, null, '0', '0', 'ALIAS', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '5F116685D20B42A6B5FD4C1F4A5F80F5', null, 0, 3, 'ENDDATE', null, null, '0', '0', 'ENDDATE', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '6D8143AF4988484C95AA6296B1C4A097', null, 457, 3, 'CITY', null, null, '0', '0', 'CITY', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '718F48E594D845789D21B4A2F13D0EAD', null, 0, 3, 'CREATEDATE', null, null, '0', '0', 'CREATEDATE', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '7568E9C4F52D432CACC6D2B33C42B55A', null, 200, 3, 'STDCODE', null, null, '0', '0', 'STDCODE', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '8BB7EFBF91B142AABC111BF79218BFF0', null, 0, 3, 'DESGUID', null, null, '0', '0', 'DESGUID', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, '9F058261D9084A9383376AE2D675A9A5', null, 32, 3, 'SUPERGUID', null, null, '0', '0', 'SUPERGUID', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'A67C04A890EB4B38923771EA2E838BB8', null, 4, 3, 'YEAR', null, null, '0', '0', 'YEAR', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'A7A7156E972446E18DA0D5B0F188759E', null, 0, 3, 'REMARK', null, null, '0', '0', 'REMARK', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'A8F76322ECA64EB88F21178C9F615481', null, 457, 3, 'NAME', null, null, '0', '0', 'NAME', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'AA8C396F605047DB852866808843F6B9', null, 10, 3, 'ZGXIAN', null, null, '0', '0', 'ZGXIAN', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'B55EAA95253A4F5DB1A0D60739BAAD58', null, 1, 3, 'STATUS', null, null, '0', '0', 'STATUS', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'C5961429C3C145BDA6448C295FE9C4C3', null, 1, 1, 'ISLEAF', null, null, '0', '0', 'ISLEAF', null, 0, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'D6ACA59DE3914460A584EBBDDA458F5B', null, 457, 3, 'PROVINCE_SH', null, null, '0', '0', 'PROVINCE_SH', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'D7FCE25E9DF742028092F470B89E7A44', null, 0, 3, 'SRCGUID', null, null, '0', '0', 'SRCGUID', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'DDB4E8E8DCC245C1A71066E44A5BDAA4', null, 9, 3, 'PROVINCE', null, null, '0', '0', 'PROVINCE', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'E7EBFECA5D704DA69C3C3E5131D9B50E', null, 0, 3, 'PINYIN', null, null, '0', '0', 'PINYIN', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'EC197271CF194934A788FE8E48EAC213', null, 0, 3, 'EMW', null, null, '0', '0', 'EMW', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'EDF9B3452C83470FB77033AA4B0C2983', null, 32, 3, 'PROVINCE_SHID', null, null, '0', '0', 'PROVINCE_SHID', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'F4BFC88121234A1BA1CACB9C8630C7CF', null, 0, 3, 'STARTDATE', null, null, '0', '0', 'STARTDATE', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'F7BFA0954B884501B30B45977A537194', null, 32, 3, 'GUID', null, null, '0', '0', 'GUID', null, null, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'FC3A050E58824A5CB21F76DE89E77A74', null, 0, 1, 'BGTLEVEL', null, null, '0', '0', 'BGTLEVEL', null, 0, '1', '5771989AF427418B84E84CEDD68C8837');

      INSERT INTO P#DICT_T_FACTORCODE (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
      VALUES (v_row.YEAR, v_row.DISTRICTID,null, 'FDF492DF7BF94E4994BF5F86CEEFAD68', null, 0, 1, 'LEVELNO', null, null, '0', '0', 'LEVELNO', null, 0, '1', '5771989AF427418B84E84CEDD68C8837');

  END LOOP ;


   --设置为测算地区代码表   过滤条件
   UPDATE P#DICT_T_MODELCODE SET DYNAMICWHERE =' 1 = 1
          START WITH GUID =
                (SELECT CASE
                        WHEN (SUBSTR(CODE, LENGTH(CODE) - 1) = ''00'') THEN
                         (SELECT GUID
                            FROM CODE_T_DISTRICT_SPF
                           WHERE GUID = B.SUPERGUID)
                        ELSE
                         GUID
                      END DISTRICT
                 FROM CODE_T_DISTRICT_SPF B
                WHERE GUID =
                      (SELECT admdiv
                         FROM FASP_T_CAUSER
                        WHERE GUID =
                              (GLOBAL_MULTYear_CZ.Secu_f_Global_Parm(''USER'')))
                  AND GUID IN (select guid
                                 from code_t_district_spf
                                start with GUID in
                                           (SELECT DISTRICTID
                                              FROM SPF_T_FDECLAREREGION
                                             WHERE spfid in(
                                                   ''$SPFID$'',''$PROJECTID$'') or spfid = (select spfid from SPF_T_PBASEINFO where PROJECTID=''$PROJECTID$''))
                               CONNECT by prior guid = superguid))
                                       CONNECT by prior guid = superguid
                                               order by CODE' 
   WHERE TABLEID = '5771989AF427418B84E84CEDD68C8837';


  --地区代码表   更换表名      
  UPDATE P#DICT_T_MODELCODE 
       SET NAME = '区代码表' ,DYNAMICWHERE = null 
     WHERE DBTABLENAME = 'CODE_T_DISTRICT_SPF'
       AND TABLEID = 'D754323AE16C448AAB86064FA77B96CE'
       AND APPID = 'SPF';
       
  -- 修改引用    测算地区代码表 
  UPDATE P#DICT_T_FACTOR
     SET CSID = '5771989AF427418B84E84CEDD68C8837'
   WHERE CSID = 'D754323AE16C448AAB86064FA77B96CE';

END;
--地区代码表
