BEGIN
DELETE FROM Bd_t_Homeurl WHERE HOMEURLID IN ('4EF0EFF803E2098FE0533A06A8C03278','4EF0EFF803E2098FE0533A06A8C03279');
insert into Bd_t_Homeurl (HOMEURLID, URL, APPID, URLTYPE, STATUS)
values ('4EF0EFF803E2098FE0533A06A8C03278', 'exp/external/bd/getHasSendMore.do', 'BAS', '6', '1');

insert into Bd_t_Homeurl (HOMEURLID, URL, APPID, URLTYPE, STATUS)
values ('4EF0EFF803E2098FE0533A06A8C03279', 'exp/external/bd/getHasSendMore.do', 'BGT', '6', '1');

END;
--添加明细更多URL到BD表
