BEGIN
    DELETE FROM FASP_T_PUBMENU WHERE GUID ='52FD60F211AF6DFBE0533906A8C0AC5F';
    insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, APPSYSID, APPID, ADMINTYPE)
    values ('52FD60F211AF6DFBE0533906A8C0AC5F', 2, 1, '1', '004009010', '���������û���ר���ʽ�', '120010', '/spf/spf/secu/adminToSPFInfo.do?appId=SPF', 5, 1, 'spf', 1);
END;
  
--�������������û���ר���ʽ�˵�
