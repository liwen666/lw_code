declare
  n_count number;
BEGIN                                                                   
  select count(1) into n_count from kpi_t_setinputstep   where name  = '���ż�Ч���۽��'  and type = '3' ;
  if n_count > 0 then
    EXECUTE IMMEDIATE Q'{delete kpi_t_setinputstep where name  = '���ż�Ч���۽��'  and type = '3'}' ;
  end if;
  EXECUTE IMMEDIATE Q'{ update kpi_t_setinputstep set name = '���ż�ЧĿ��'  where name = '���ż�Ч����Ŀ��'}' ;
    EXECUTE IMMEDIATE Q'{ INSERT INTO KPI_T_SETINPUTSTEP (STATUS,DATAKEY,NEEDUPDATE,ORDERID,FINYEAR,GUID,CODE,NAME,TYPE)
    VALUES ('1','23E40EFB9316406780EAA967A5B63221',NULL,24,'2015','ABF8104524CD4682A13EC1F4C757D64','result','���ż�Ч���۽��','3')}' ;
  
END;



--��Ч_WTF_20160708_�׶α��޸�--���ż�Ч�׶��޸�
