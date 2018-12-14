BEGIN
  update fasp_t_pubmenu t set t.url = replace(t.url,'projmType=','spfmType=') where APPID = 'spf' and code in ('004004013','004002014','004004012','004002015');
END;
--修改项目调整spfmType
