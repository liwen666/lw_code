declare
  n_count number;
BEGIN                                                                   
  select count(1) into n_count from kpi_t_setinputstep   where name  = '部门绩效评价结果'  and type = '3' ;
  if n_count > 0 then
    EXECUTE IMMEDIATE Q'{delete kpi_t_setinputstep where name  = '部门绩效评价结果'  and type = '3'}' ;
  end if;
  EXECUTE IMMEDIATE Q'{ update kpi_t_setinputstep set name = '部门绩效目标'  where name = '部门绩效评价目标'}' ;
    EXECUTE IMMEDIATE Q'{ INSERT INTO KPI_T_SETINPUTSTEP (STATUS,DATAKEY,NEEDUPDATE,ORDERID,FINYEAR,GUID,CODE,NAME,TYPE)
    VALUES ('1','23E40EFB9316406780EAA967A5B63221',NULL,24,'2015','ABF8104524CD4682A13EC1F4C757D64','result','部门绩效评价结果','3')}' ;
  
END;



--绩效_WTF_20160708_阶段表修改--部门绩效阶段修改
