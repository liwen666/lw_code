DECLARE
  v_cnt number;
BEGIN
  select count(1) into v_cnt from user_tables WHERE TABLE_NAME = 'P#CODE_T_SPFCPFS';

  if v_cnt = 0 THEN 
      EXECUTE IMMEDIATE Q'/CREATE TABLE P#CODE_T_SPFCPFS
      (
        GUID      VARCHAR2(32) not null,
        CODE      VARCHAR2(32) NOT NULL,
        NAME      VARCHAR2(32) NOT NULL,
        SUPERGUID VARCHAR2(20) NOT NULL,
        ISLEAF    CHAR(1) NOT NULL,
        LEVELNO    CHAR(1),
        STATUS char(1)
      )/';  
        
      EXECUTE IMMEDIATE 'alter table P#CODE_T_SPFCPFS add constraint PK_CODE_T_SPFCPFS primary key (GUID)';

  end if;

  --������ͼ��������
  EXECUTE immediate Q'/
    CREATE OR REPLACE VIEW CODE_T_SPFCPFS AS
      SELECT         
      T.GUID          GUID,
      T.CODE          CODE,
      T.NAME          NAME,
      T.SUPERGUID     SUPERGUID,
      ISLEAF          ISLEAF,
      STATUS          STATUS,
      LEVELNO
      FROM P#CODE_T_SPFCPFS T
      WHERE T.STATUS = '1'
    /'; 
    
  delete from p#dict_t_modelcode where tableid ='50697B2B00352BBBE0533906A8C03017';
  delete from p#dict_t_factorcode where tableid ='50697B2B00352BBBE0533906A8C03017';
  
  for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop
    
    --p#dict_t_modelcode
    insert into p#dict_t_modelcode (YEAR, PROVINCE, APPID, TABLEID, NAME, DBTABLENAME, ORDERID, ISREPBASE, ISLVL, SQLCON, DYNAMICWHERE, ISORGID, STATUS, FASPCSID, ISFASP, TABLETYPE)
    values (v_row.YEAR, v_row.DISTRICTID, 'SPF', '50697B2B00352BBBE0533906A8C03017', '��Ŀ������ģʽ', 'CODE_T_SPFCPFS', 1, '0', '0', null, null, '0', '1', '50697B2B00352BBBE0533906A8C03017', '0', '2');
  
    --p#dict_t_factorcode
    insert into p#dict_t_factorcode (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    values (v_row.YEAR, v_row.DISTRICTID,'50697B2B00352BBBE0533906A8C03017', null, '50697B2B00362BBBE0533906A8C03017', 'GUID', null, 'GUID', 3, 32, null, null, null, '0', '0', null, '1');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    values (v_row.YEAR, v_row.DISTRICTID,'50697B2B00352BBBE0533906A8C03017', null, '50697B2B00372BBBE0533906A8C03017', 'CODE', null, 'CODE', 3, 32, null, null, null, '0', '0', null, '1');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    values (v_row.YEAR, v_row.DISTRICTID,'50697B2B00352BBBE0533906A8C03017', null, '50697B2B00382BBBE0533906A8C03017', 'NAME', null, 'NAME', 3, 32, null, null, null, '0', '0', null, '1');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    values (v_row.YEAR, v_row.DISTRICTID,'50697B2B00352BBBE0533906A8C03017', null, '50697B2B00392BBBE0533906A8C03017', 'SUPERGUID', null, 'SUPERGUID', 3, 20, null, null, null, '0', '0', null, '1');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    values (v_row.YEAR, v_row.DISTRICTID,'50697B2B00352BBBE0533906A8C03017', null, '50697B2B003A2BBBE0533906A8C03017', 'ISLEAF', null, 'ISLEAF', 3, 1, null, null, null, '0', '0', null, '1');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    values (v_row.YEAR, v_row.DISTRICTID,'50697B2B00352BBBE0533906A8C03017', null, '50697B2B003B2BBBE0533906A8C03017', 'STATUS', null, 'STATUS', 3, 1, null, null, null, '0', '0', null, '1');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
    values (v_row.YEAR, v_row.DISTRICTID,'50697B2B00352BBBE0533906A8C03017', null, '50697B2B003C2BBBE0533906A8C03017', 'LEVELNO', null, 'LEVELNO', 3, 1, null, null, null, '0', '0', null, '1');

  end loop;
  
  --�滻�����
  update p#dict_t_factor set csid = '50697B2B00352BBBE0533906A8C03017' where csid ='8339F0300BB24D638F73288890EBFBAF';
  

END;
--��Ŀ������ģʽ