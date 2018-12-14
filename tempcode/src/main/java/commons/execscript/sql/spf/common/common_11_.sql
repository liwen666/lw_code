BEGIN
  --刘凯新增专项类别数据
  DELETE FROM SPF_T_INITPROJTYPE;
  insert into SPF_T_INITPROJTYPE (NAME, GUID, SUPERID)
  values ('项目支出', '1', null);
  insert into SPF_T_INITPROJTYPE (NAME, GUID, SUPERID)
  values ('一项级目', '2', '1');
  insert into SPF_T_INITPROJTYPE (NAME, GUID, SUPERID)
  values ('基本支出', '9', null);
  insert into SPF_T_INITPROJTYPE (NAME, GUID, SUPERID)
  values ('专项资金', '3', '1');
END;
--liukaizhuanxiangadd
