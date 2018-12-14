BEGIN

  DELETE FROM DICT_T_BILLPROC WHERE appID = 'BGT';

  INSERT INTO DICT_T_BILLPROC(appid, procid, procname)
  VALUES
  ('BGT', 'P_TRANSPOSE_ZCXMMXB', '基本支出来源表转换');

  INSERT INTO DICT_T_BILLPROC(appid, procid, procname)
  VALUES
  ('BGT', 'P_VIEW_ACCOUNT', '简单转换关系');

END;
--登记视图转换存储过程中间表
