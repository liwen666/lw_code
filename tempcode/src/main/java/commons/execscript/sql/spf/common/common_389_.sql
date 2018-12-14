declare
begin
DELETE FROM P#bd_t_homeurl
 WHERE HOMEURLID IN ('033675B202F74EC39573D38AA61B7111',
                     '033675B202F74EC39573D38AA61B7125',
                     '033675B202F74EC39573D38AA61B7123');
DELETE FROM bd_t_initparambean WHERE DATAKEY ='4ABFCDE7E0515774E0533A06A8C0A2EC';                     
insert into P#bd_t_homeurl (HOMEURLID, URL, APPID, URLTYPE, STATUS)
values ('033675B202F74EC39573D38AA61B7111', 'spf/oa/selectWait/getSelectWait.do', 'SPF', '1', '1');

insert into P#bd_t_homeurl (HOMEURLID, URL, APPID, URLTYPE, STATUS)
values ('033675B202F74EC39573D38AA61B7125', 'spf/oa/FlowSelect/getFlowHasHandledSelect.do', 'SPF', '3', '1');

insert into P#bd_t_homeurl (HOMEURLID, URL, APPID, URLTYPE, STATUS)
values ('033675B202F74EC39573D38AA61B7123', 'spf/oa/FlowSelect/getFlowHandlingSelect.do', 'SPF', '2', '1');

insert into bd_t_initparambean (DATAKEY, BEANID, BEANNAME, APPID, TYPE, STATUS)
values ('4ABFCDE7E0515774E0533A06A8C0A2EC', 'batchDetailServiceImpl', '横向明细批次', 'SPF', 'batchDetail', '1');

end;
--初始化P#bd_t_homeurl
