BEGIN

DELETE FROM Bd_t_Homeurl WHERE appid IN ('BAS','BGT');
  
insert into Bd_t_Homeurl (HOMEURLID, URL, APPID, URLTYPE)
values ('6F9026A9416D42458653D7A3A6496979', 'exp/external/bd/getTaskList.do', 'BGT', '2');

insert into Bd_t_Homeurl (HOMEURLID, URL, APPID, URLTYPE)
values ('A02A2DB4F3934466A7FF756C5F62A04B', 'exp/external/bd/getTaskList.do', 'BGT', '3');

insert into Bd_t_Homeurl (HOMEURLID, URL, APPID, URLTYPE)
values ('033675B202F74EC39573D38AA61B7368', 'exp/external/bd/getTaskList.do', 'BGT', '1');

insert into Bd_t_Homeurl (HOMEURLID, URL, APPID, URLTYPE)
values ('033675B202F74EC39573D38AA61B7000', 'exp/external/bd/getTaskList.do', 'BAS', '3');

insert into Bd_t_Homeurl (HOMEURLID, URL, APPID, URLTYPE)
values ('4A48321F7CEB68F4E0533A06A8C0F31C', 'exp/external/bd/getTaskList.do', 'BAS', '1');

END;
--获取待办URL登记
