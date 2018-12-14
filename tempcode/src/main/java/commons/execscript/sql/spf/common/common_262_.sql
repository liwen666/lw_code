begin
  delete from p#kpi_t_setkpicaliber WHERE KPITYPE in('C1','C2','C3','C4');
   for v_row in (select * from pub_t_partition_divid t where t.year <> '*') LOOP
    insert into p#kpi_t_setkpicaliber(YEAR,PROVINCE,status,DATAKEY,ORDERID,FINYEAR,GUID,KPITYPENAME,KPITYPE,TABLEID,remark,CSCOLUMNID)
    values(v_row.year,v_row.Districtid,'1','A30359A7314244A3B9123BF13449D2DA','9','2015','4C62983C556A4E9793577820A9E12185','项目绩效综合查询','C1','7788E050A8C021056ABE1430CAF3E61F','','75D8D135DB7142B2B8A71858FF4FA022');
     insert into p#kpi_t_setkpicaliber(YEAR,PROVINCE,status,DATAKEY,ORDERID,FINYEAR,GUID,KPITYPENAME,KPITYPE,TABLEID,remark,CSCOLUMNID)
    values(v_row.year,v_row.Districtid,'1','E9A4C2ABE80F494CB8C55DC120BA900C','10','2015','835288D0B05F4299807076CBC5357E7D','专项绩效综合查询','C2','','','');
     insert into p#kpi_t_setkpicaliber(YEAR,PROVINCE,status,DATAKEY,ORDERID,FINYEAR,GUID,KPITYPENAME,KPITYPE,TABLEID,remark,CSCOLUMNID)
    values(v_row.year,v_row.Districtid,'1','A72A4746003247C5BB895EE0572FF749','11','2015','617DF1C90A8A4498BDC6C4D7254EC34F','部门绩效综合查询','C3','','','');
     insert into p#kpi_t_setkpicaliber(YEAR,PROVINCE,status,DATAKEY,ORDERID,FINYEAR,GUID,KPITYPENAME,KPITYPE,TABLEID,remark,CSCOLUMNID)
    values(v_row.year,v_row.Districtid,'1','902DAFBEF30B4D45B37674F9E7F53715','12','2015','1975F9178AEF4B89A449604D89C463A6','财政绩效综合查询','C4','','','');
   END LOOP;
end;


--KPI_20160921_绩效口径综合查询设置初始化
