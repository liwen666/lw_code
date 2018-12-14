begin 
  DELETE FROM CODE_T_PERSONCONFIRM where GUID = '0';
  DELETE FROM CODE_T_PERSONCONFIRM where GUID = '1';
  DELETE FROM CODE_T_PERSONCONFIRM where GUID = '2';

  insert into CODE_T_PERSONCONFIRM (guid,CODE,ISLEAF,LEVELNO,NAME,SUPERGUID)
VALUES
  ('0','0', '1', '1', '新增','0');
   insert into CODE_T_PERSONCONFIRM (guid,CODE,ISLEAF,LEVELNO,NAME,SUPERGUID)
VALUES
  ('1','1', '1', '1', '确认','0');
    insert into CODE_T_PERSONCONFIRM (guid,CODE,ISLEAF,LEVELNO,NAME,SUPERGUID)
VALUES
  ('2','2', '1', '1', '修改待确认','0');
end;--2-creatandinsert_personconfirm
