BEGIN  --目标,监督，整改，自评，评价，信息
    update kpi_t_setinputstep set orderid='1' WHERE  code='target';
    update kpi_t_setinputstep set orderid='2' WHERE  code='monitor';
    update kpi_t_setinputstep set orderid='3' WHERE  code='update';
    update kpi_t_setinputstep set orderid='4' WHERE  code='self';
    update kpi_t_setinputstep set orderid='5' WHERE  code='eval';
    update kpi_t_setinputstep set orderid='6' WHERE  code='message';
    
    for v_type in (select distinct type from kpi_t_setinputstep ) LOOP
             for V_ROW in (SELECT A.*, ROWNUM RN FROM (SELECT * FROM kpi_t_setinputstep) A
                   where type = v_type.type and code not in('target','monitor','update','self','eval','message')) LOOP       
          update kpi_t_setinputstep set orderid=V_ROW.RN+6 WHERE GUID=V_ROW.GUID;
       END LOOP;
      END LOOP;
END;
--WUTF_20161111阶段排序
