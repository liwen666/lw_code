BEGIN
  update fasp_t_pubmenu set url = url||CHR(38)||'itemType=1' where parentid in ('120002','120004') and url not like '%itemType%';
END;
--给所有项目菜单加个itemType
