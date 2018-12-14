begin
  execute immediate Q'{
    create or replace view code_t_projcode as
      select projectid guid,
         '' code,
         projname name,
         '' superguid,
         '' isleaf,
         '' levelno
      from spf_t_pbaseinfo 
  }';
  
  delete from p#dict_t_modelcode where tableid ='52D59B9FA1C01E18E0533906A8C0389B';
  delete from p#dict_t_factorcode where tableid ='52D59B9FA1C01E18E0533906A8C0389B';
  
  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
  
    insert into p#dict_t_modelcode (YEAR, PROVINCE, APPID, FASPCSID, DBTABLENAME, TABLEID, NAME, DYNAMICWHERE, ISLVL, ISFASP, ISORGID, ISREPBASE, ORDERID, SQLCON, STATUS, TABLETYPE)
    values (v_row.YEAR, v_row.DISTRICTID, 'SPF', '52D59B9FA1C01E18E0533906A8C0389B', 'CODE_T_PROJCODE', '52D59B9FA1C01E18E0533906A8C0389B', '项目代码表', null, '0', '0', '0', '0', 1, null, '1', '2');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID, null, '52D59B9FA1C11E18E0533906A8C0389B', null, 32, 3, 'GUID', '0', null, '0', '0', '项目唯一标示', null, null, '1', '52D59B9FA1C01E18E0533906A8C0389B');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID, null, '52D59B9FA1C21E18E0533906A8C0389B', null, 32, 3, 'CODE', '0', null, '0', '0', '编码', null, null, '1', '52D59B9FA1C01E18E0533906A8C0389B');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID, null, '52D59B9FA1C31E18E0533906A8C0389B', null, 200, 3, 'NAME', '0', null, '0', '0', '项目名称', null, null, '1', '52D59B9FA1C01E18E0533906A8C0389B');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID, null, '52D59B9FA1C41E18E0533906A8C0389B', null, 32, 3, 'SUPERGUID', '0', null, '0', '0', '上级GUID', null, null, '1', '52D59B9FA1C01E18E0533906A8C0389B');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID, null, '52D59B9FA1C51E18E0533906A8C0389B', null, 1, 3, 'ISLEAF', '0', null, '0', '0', '是否末级', null, null, '1', '52D59B9FA1C01E18E0533906A8C0389B');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID, null, '52D59B9FA1C61E18E0533906A8C0389B', null, 1, 3, 'LEVELNO', '0', null, '0', '0', '级次', null, null, '1', '52D59B9FA1C01E18E0533906A8C0389B');

  end loop;
  
end;
--项目代码表
