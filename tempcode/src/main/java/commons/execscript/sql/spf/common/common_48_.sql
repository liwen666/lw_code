BEGIN
  DELETE FROM DICT_T_PUBLIC WHERE APPID ='SPF' AND KEYID IN('0016','0017') AND TYPEID IN('SPF_KEEP','BGT_KEEP');
  INSERT INTO DICT_T_PUBLIC (APPID, CNUM, KEYID, KEYNAME, RESERVE_1, TYPEID, TYPENAME)
  VALUES ('SPF', 0, '0016', '专项记账存储过程', 'P_KEEP_SPF_ACCOUNTS', 'SPF_KEEP', '项目库记账存储过程');
  INSERT INTO DICT_T_PUBLIC (APPID, CNUM, KEYID, KEYNAME, RESERVE_1, TYPEID, TYPENAME)
  VALUES ('SPF', 0, '0017', '预算记账存储过程', 'P_KEEP_BGT_ACCOUNTS', 'BGT_KEEP', '预算记账存储过程');
END;

--添加记账存储过程公共变量
