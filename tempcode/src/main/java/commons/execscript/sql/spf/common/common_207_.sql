BEGIN
  DELETE FROM DICT_T_SETTINGBUSIINFO T WHERE T.NAME = '重构综合视图(项目库)';
  insert into DICT_T_SETTINGBUSIINFO
    (APPID, DATAKEY, NAME, ORDERID, STATUS, URL, IS_AJAX)
  values
    ('SPF',
     '9AA27B065F224EE5A422438AA0BD1B1B',
     '重构综合视图(项目库)',
     3,
     '1',
     '/project/comprePage/rebuildView.do',
     '1');
END;
--项目库综合查询重构综合视图按钮
