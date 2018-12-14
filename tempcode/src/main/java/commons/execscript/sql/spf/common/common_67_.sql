BEGIN
  delete FROM spf_t_pprojstep WHERE GUID ='F6FEAD9BEB0217D6E040A8C0200EEFAD';
  insert into spf_t_pprojstep (BGTLVL, CODE,  GUID, NAME, ORDERID, STATUS)
  values (null, 'inindex',  'F6FEAD9BEB0217D6E040A8C0200EEFAD', '纳入指标', 11, '1');
END;
--纳入指标阶段
