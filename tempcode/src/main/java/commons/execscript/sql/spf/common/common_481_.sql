BEGIN
  DELETE FROM  bd_t_handlebean where datakey='4CDFACE9946F5824E0533A06A8C12398';
  
  insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID, STATUS)
  values ('4CDFACE9946F5824E0533A06A8C12398', '17', 'spfCVerticalFinishServiceImpl', '������', 'SPF',  '1');

END;--������handlebean���
