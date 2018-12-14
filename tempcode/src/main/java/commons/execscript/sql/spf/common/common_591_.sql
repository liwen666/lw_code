begin
  execute immediate Q'{
    create or replace view code_t_spfcode as
      select spfid guid,
         '' code,
         spfname name,
         '' superguid,
         '' isleaf,
         '' leveln
      from spf_t_fbaseinfo
  }';
  
  delete from p#dict_t_modelcode where tableid ='52D4D2D9DDF519C6E0533906A8C02483';
  delete from p#dict_t_factorcode where tableid ='52D4D2D9DDF519C6E0533906A8C02483';
  
  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop 
    
    insert into p#dict_t_modelcode (YEAR, PROVINCE, APPID, FASPCSID, DBTABLENAME, TABLEID, NAME, DYNAMICWHERE, ISLVL, ISFASP, ISORGID, ISREPBASE, ORDERID, SQLCON, STATUS, TABLETYPE)
    values (v_row.YEAR, v_row.DISTRICTID, 'SPF', '52D4D2D9DDF519C6E0533906A8C02483', 'CODE_T_SPFCODE', '52D4D2D9DDF519C6E0533906A8C02483', '专项代码表', null, '0', '0', '0', '0', 1, null, '1', '2');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID, null, '52D4D2D9DDF619C6E0533906A8C02483', null, 32, 3, 'GUID', '0', null, '0', '0', '专项唯一标示', null, null, '1', '52D4D2D9DDF519C6E0533906A8C02483');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID, null, '52D4D2D9DDF719C6E0533906A8C02483', null, 32, 3, 'CODE', '0', null, '0', '0', '编码', null, null, '1', '52D4D2D9DDF519C6E0533906A8C02483');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID, null, '52D4D2D9DDF819C6E0533906A8C02483', null, 32, 3, 'NAME', '0', null, '0', '0', '专项名称', null, null, '1', '52D4D2D9DDF519C6E0533906A8C02483');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID, null, '52D4D2D9DDF919C6E0533906A8C02483', null, 32, 3, 'SUPERGUID', '0', null, '0', '0', '上级GUID', null, null, '1', '52D4D2D9DDF519C6E0533906A8C02483');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID, null, '52D4D2D9DDFA19C6E0533906A8C02483', null, 1, 3, 'ISLEAF', '0', null, '0', '0', '是否末级', null, null, '1', '52D4D2D9DDF519C6E0533906A8C02483');

    insert into p#dict_t_factorcode (YEAR, PROVINCE, BGTLVL, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISRESERVE, ISVISIBLE, NAME, ORDERID, SCALE, STATUS, TABLEID)
    values (v_row.YEAR, v_row.DISTRICTID, null, '52D4D2D9DDFB19C6E0533906A8C02483', null, 1, 1, 'LEVELNO', '0', null, '0', '0', '级次', null, null, '1', '52D4D2D9DDF519C6E0533906A8C02483');

  end loop;
  
end;
 
 
--专项代码表
