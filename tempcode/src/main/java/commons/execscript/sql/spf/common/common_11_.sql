BEGIN
  --��������ר���������
  DELETE FROM SPF_T_INITPROJTYPE;
  insert into SPF_T_INITPROJTYPE (NAME, GUID, SUPERID)
  values ('��Ŀ֧��', '1', null);
  insert into SPF_T_INITPROJTYPE (NAME, GUID, SUPERID)
  values ('һ�Ŀ', '2', '1');
  insert into SPF_T_INITPROJTYPE (NAME, GUID, SUPERID)
  values ('����֧��', '9', null);
  insert into SPF_T_INITPROJTYPE (NAME, GUID, SUPERID)
  values ('ר���ʽ�', '3', '1');
END;
--liukaizhuanxiangadd
