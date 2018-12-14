BEGIN
  
DELETE FROM BD_T_BUSIJSURL WHERE appid IN ('BAS','BGT');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A67', '横向通用发送系统', 'static/app/exp/external/batchSelect.gzjs', 'BAS', '4', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A89', '纵向回退业务主体', 'static/app/exp/external/objectSelect4Back.gzjs', 'BAS', '6', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00C00', '纵向拟稿业务主体', 'static/app/exp/task/taskDocCreate.gzjs', 'BGT', '9', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00D00', '纵向拟稿业务主体', 'static/app/exp/task/taskDocCreate.gzjs', 'BAS', '9', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A35', '纵向撤回业务主体', 'static/app/exp/external/objectSelect4Revoke.gzjs', 'BAS', '7', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A33', '纵向回退业务主体', 'static/app/exp/external/objectSelect4Back.gzjs', 'BGT', '6', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A55', '横向通用发送系统', 'static/app/exp/external/batchSelect.gzjs', 'BGT', '4', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B629CA27E732D9CE0533A06A8C01D5B', '纵向概要业务主体', 'static/app/exp/external/barCharts.gzjs', 'BGT', '5', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00B00', '纵向撤回业务主体', 'static/app/exp/external/objectSelect4Revoke.gzjs', 'BGT', '7', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('1B629CA27E892D9CE0533A06A8C01D5B', '转移支付下达和对账图表', 'static/app/exp/external/barCharts.gzjs', 'BAS', '5', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22177C00A99', '纵向转横向审核主体', 'static/app/exp/external/objectSelect4C2B.gzjs', 'BGT', '3', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22129C00A88', '横向通用发送系统', 'static/app/exp/external/batchSelect.gzjs', 'BGT', '4', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4A5BA0B5E4D9556EE0533906A8C0E5C8', '纵向转横向审核主体', 'static/app/exp/external/objectSelect4C2B.gzjs', 'BAS', '3', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4A5BA0B5E4DB556EE0533906A8C0E5C8', '纵向通用发送系统', 'static/app/exp/external/objectSelect4Report.gzjs', 'BAS', '1', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00RR9', '横向分发业务主体选择', 'static/app/exp/external/objectSelect4NewSelect.gzjs', 'BGT', '10', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A77', '纵向通用发送系统', 'static/app/exp/external/objectSelect4Report.gzjs', 'BGT', '1', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A43', '横向分发业务主体选择', 'static/app/exp/external/objectSelect4NewSelect.gzjs', 'BAS', '10', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4DFB599D5FDF46FCE0533A06A8C08C13', '纵向办结业务主体', 'static/app/exp/external/objectSelect4EndProcess.gzjs', 'BAS', '11', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4DFB599D5FE046FCE0533A06A8C08C13', '纵向办结业务主体', 'static/app/exp/external/objectSelect4EndProcess.gzjs', 'BGT', '11', '1');

insert into BD_T_BUSIJSURL (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('1B629CA27E892D9CE0533A06A8C01D00', '稳增长政策统计图表', 'static/app/exp/external/barCharts1.gzjs', 'BAS', '5', '1');

END;
--登记业务系统JS到BD表
