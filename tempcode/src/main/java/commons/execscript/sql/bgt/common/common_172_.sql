BEGIN
  UPDATE P#Dict_t_Settingtabinfo t SET t.INITIALIZE = '1', t.ISLEAD = '0';
  UPDATE P#Dict_t_Settingtabinfo t SET t.INITIALIZE = '0' WHERE t.dbtablename = 'P#BIL_T_BILLTYPE';
END;

--ÐÞ¸ÄSETTAB_INITIALIZW×Ö¶Î
