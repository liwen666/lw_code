begin
DELETE FROM bd_t_busijsurl where appid in ('BGT','BAS');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A67', '横向通用发送系统', 'static/app/exp/external/batchSelect.js', 'BAS', '4', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A89', '纵向回退业务主体', 'static/app/exp/external/objectSelect4Back.js', 'BAS', '6', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00C00', '纵向拟稿业务主体', 'static/app/exp/task/taskDocCreate.js', 'BGT', '9', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00D00', '纵向拟稿业务主体', 'static/app/exp/task/taskDocCreate.js', 'BAS', '9', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A35', '纵向撤回业务主体', 'static/app/exp/external/objectSelect4Revoke.js', 'BAS', '7', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A33', '纵向回退业务主体', 'static/app/exp/external/objectSelect4Back.js', 'BGT', '6', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A55', '横向通用发送系统', 'static/app/exp/external/batchSelect.js', 'BGT', '4', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B629CA27E732D9CE0533A06A8C01D5B', '纵向概要业务主体', 'static/app/exp/external/barCharts.js', 'BGT', '5', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00B00', '纵向撤回业务主体', 'static/app/exp/external/objectSelect4Revoke.js', 'BGT', '7', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('1B629CA27E892D9CE0533A06A8C01D5B', '纵向概要业务主体', 'static/app/exp/external/barCharts.js', 'BAS', '5', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22177C00A99', '纵向转横向审核主体', 'static/app/exp/external/objectSelect4C2B.js', 'BGT', '3', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22129C00A88', '横向通用发送系统', 'static/app/exp/external/batchSelect.js', 'BGT', '4', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4A5BA0B5E4D9556EE0533906A8C0E5C8', '纵向转横向审核主体', 'static/app/exp/external/objectSelect4C2B.js', 'BAS', '3', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4A5BA0B5E4DB556EE0533906A8C0E5C8', '纵向通用发送系统', 'static/app/exp/external/objectSelect4Report.js', 'BAS', '1', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00RR9', '横向分发业务主体选择', 'static/app/exp/external/objectSelect4NewSelect.js', 'BGT', '10', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A77', '纵向通用发送系统', 'static/app/exp/external/objectSelect4Report.js', 'BGT', '1', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A43', '横向分发业务主体选择', 'static/app/exp/external/objectSelect4NewSelect.js', 'BAS', '10', '1');
end;--配置业务需要的js_n
