BEGIN

DELETE FROM Bd_t_Homeurl  WHERE appid IN ('BAS','BGT');
  
insert into Bd_t_Homeurl (HOMEURLID, URL, APPID, URLTYPE, STATUS)
values ('4DE206E58FC865F2E0533A06A8C0730E', 'exp/external/bd/getDetailMore.do', 'BGT', '5', '1');

insert into Bd_t_Homeurl (HOMEURLID, URL, APPID, URLTYPE, STATUS)
values ('A02A2DB4F3934466A7FF756C5F62A04B', 'exp/external/bd/getTaskList.do', 'BGT', '3', '1');

insert into Bd_t_Homeurl (HOMEURLID, URL, APPID, URLTYPE, STATUS)
values ('033675B202F74EC39573D38AA61B7368', 'exp/external/bd/getTaskList.do', 'BGT', '1', '1');

insert into Bd_t_Homeurl (HOMEURLID, URL, APPID, URLTYPE, STATUS)
values ('033675B202F74EC39573D38AA61B7000', 'exp/external/bd/getTaskList.do', 'BAS', '3', '1');

insert into Bd_t_Homeurl (HOMEURLID, URL, APPID, URLTYPE, STATUS)
values ('4A48321F7CEB68F4E0533A06A8C0F31C', 'exp/external/bd/getTaskList.do', 'BAS', '1', '1');

insert into Bd_t_Homeurl (HOMEURLID, URL, APPID, URLTYPE, STATUS)
values ('4A48321F7CEB68F4E0533A06A8C0F31D', 'exp/external/bd/getDetailMore.do', 'BAS', '5', '1');

END;
--业务系统登记Homeurl
