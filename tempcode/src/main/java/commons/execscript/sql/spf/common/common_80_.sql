/**
 *  ��������������ҳ��˵���Ҫʹ�õı�������
 */
DECLARE existCount NUMBER(9);
v_sql VARCHAR2(32000);
BEGIN
  -- �����������
  SELECT COUNT(1) INTO existCount FROM DICT_T_DEALTYPE WHERE DEALID = '2105';
  IF(existCount = 0) THEN
    INSERT INTO DICT_T_DEALTYPE (APPID, DEALID, DEALNAME, ORDERID, NEEDCONFIG) VALUES ('SPF', '2105', '����֧����ϸ��[��������]', 215, '0');
  END IF;

  -- �����˵�
  SELECT COUNT(1) INTO existCount FROM FASP_T_PUBMENU WHERE GUID = '11202119';
  IF(existCount = 0) THEN
    INSERT INTO FASP_T_PUBMENU (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, ADMINTYPE)
      VALUES ('11202119', 2, 1, '1', '004001007', 'ר���ʽ�������', '120001', '/spf/spf/allotment.do?appID=SPF', 10, '����ҳ��', null, 2, null, null, 'spf', 1);
  ELSE
    UPDATE FASP_T_PUBMENU SET URL = '/spf/spf/allotment.do?appID=SPF' WHERE GUID = '11202119';
  END IF;
END;
--��������������ҳ��˵���Ҫʹ�õı�������
