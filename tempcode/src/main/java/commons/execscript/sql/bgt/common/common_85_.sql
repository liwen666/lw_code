BEGIN

  DELETE FROM DICT_T_BILLPROC WHERE appID = 'BGT';

  INSERT INTO DICT_T_BILLPROC(appid, procid, procname)
  VALUES
  ('BGT', 'P_TRANSPOSE_ZCXMMXB', '����֧����Դ��ת��');

  INSERT INTO DICT_T_BILLPROC(appid, procid, procname)
  VALUES
  ('BGT', 'P_VIEW_ACCOUNT', '��ת����ϵ');

END;
--�Ǽ���ͼת���洢�����м��
