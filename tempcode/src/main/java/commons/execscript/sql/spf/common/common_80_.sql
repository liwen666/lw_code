/**
 *  创建测算分配管理页面菜单和要使用的表处理类型
 */
DECLARE existCount NUMBER(9);
v_sql VARCHAR2(32000);
BEGIN
  -- 插入表处理类型
  SELECT COUNT(1) INTO existCount FROM DICT_T_DEALTYPE WHERE DEALID = '2105';
  IF(existCount = 0) THEN
    INSERT INTO DICT_T_DEALTYPE (APPID, DEALID, DEALNAME, ORDERID, NEEDCONFIG) VALUES ('SPF', '2105', '基本支出明细表[测算结果表]', 215, '0');
  END IF;

  -- 创建菜单
  SELECT COUNT(1) INTO existCount FROM FASP_T_PUBMENU WHERE GUID = '11202119';
  IF(existCount = 0) THEN
    INSERT INTO FASP_T_PUBMENU (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, ADMINTYPE)
      VALUES ('11202119', 2, 1, '1', '004001007', '专项资金测算分配', '120001', '/spf/spf/allotment.do?appID=SPF', 10, '管理页面', null, 2, null, null, 'spf', 1);
  ELSE
    UPDATE FASP_T_PUBMENU SET URL = '/spf/spf/allotment.do?appID=SPF' WHERE GUID = '11202119';
  END IF;
END;
--创建测算分配管理页面菜单和要使用的表处理类型
