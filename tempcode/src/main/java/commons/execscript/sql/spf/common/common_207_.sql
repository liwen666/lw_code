BEGIN
  DELETE FROM DICT_T_SETTINGBUSIINFO T WHERE T.NAME = '�ع��ۺ���ͼ(��Ŀ��)';
  insert into DICT_T_SETTINGBUSIINFO
    (APPID, DATAKEY, NAME, ORDERID, STATUS, URL, IS_AJAX)
  values
    ('SPF',
     '9AA27B065F224EE5A422438AA0BD1B1B',
     '�ع��ۺ���ͼ(��Ŀ��)',
     3,
     '1',
     '/project/comprePage/rebuildView.do',
     '1');
END;
--��Ŀ���ۺϲ�ѯ�ع��ۺ���ͼ��ť
