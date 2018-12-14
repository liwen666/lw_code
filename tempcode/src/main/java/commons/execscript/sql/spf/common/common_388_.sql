declare
begin
delete from bd_t_busijsurl
 where datakey in ('4A1CA405E33768E0E0533A06A8C0699D',
                   '4A1CA405E33868E0E0533A06A8C0699D',
                   '4A1CA405E33968E0E0533A06A8C0699D',
                   '4A1CA405E33A68E0E0533A06A8C0699D',
                   '6483E84AFB434D14BECFF22127C00A00',
                   '6483E84AFB434D14BECFF22127C00A22',
                   '4CF2D8D5009865DBE0533A06A8C0310F',
                   '4BB3002043683F04E0533A06A8C00F3A',
                   '4A1CA405E33A68E0E0533A06A8C0689D',
                   '6483E84AFB434D14BECFF22127C00565',
                   '4B35BEAAE8291322E0533A06A8C01884',
                   '4B35BEAAE82A1322E0533A06A8C01884',
                   '6483E84AFB434D14BECFF22127C00A66',
                   '4B5233F44CA825DFE0533A06A8C02937',
                   '4B5233F44CA925DFE0533A06A8C02937',
                   '4B5233F44CAA25DFE0533A06A8C02937',
                   '4B5233F44CAB25DFE0533A06A8C02937',
                   '4B5233F44CAC25DFE0533A06A8C02937',
                   '4B5233F44CAD25DFE0533A06A8C02937',
                   '4B5233F44CAE25DFE0533A06A8C02937',
                   '4B5233F44CAF25DFE0533A06A8C02937',
                   '4E378CDCA80E4EE9E0533A06A8C0EE9A',
           '4E478CDCA80F4EE9E0533A06A8C0EE9A',
           '4E578CDCA80F4EE9E0533A06A8C0EE9A',
           '4E678CDCA80F4EE9E0533A06A8C0EE9A','4D915AD388230EB2E0533A06A8C0D0D6');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4A1CA405E33768E0E0533A06A8C0699D', '纵向一级项目查看', 'static/app/spf/external/selectSpfMainCYJ.js', 'SPF', '5', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4A1CA405E33868E0E0533A06A8C0699D', '纵向专项资金查看', 'static/app/spf/external/selectSpfMainCFZJ.js', 'SPF', '5', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4A1CA405E33968E0E0533A06A8C0699D', '纵向二级项目查看', 'static/app/spf/external/selectSpfMainCPEJ.js', 'SPF', '5', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4A1CA405E33A68E0E0533A06A8C0699D', '纵向专项项目查看', 'static/app/spf/external/selectSpfMainCFP.js', 'SPF', '5', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A00', '纵向拟稿专项查看', 'static/app/spf/external/SpfCSpfNewExternal.js', 'SPF', '9', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A22', '纵向拟稿项目查看', 'static/app/spf/external/SpfCNewProjectExternal.js', 'SPF', '9', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4CF2D8D5009865DBE0533A06A8C0310F', '专项重新分配主体', 'static/app/spf/external/SpfBSpfRedistribution.js', 'SPF', '10', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4D915AD388230EB2E0533A06A8C0D0D6', '项目重新分配主体', 'static/app/spf/external/SpfBProjectRedistribution.js', 'SPF', '10', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4BB3002043683F04E0533A06A8C00F3A', '纵向专项撤回查看', 'static/app/spf/external/SpfCSpfExternaReback.js', 'SPF', '7', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4A1CA405E33A68E0E0533A06A8C0689D', '纵向项目撤回主体', 'static/app/spf/external/SpfCProjectExternaReback.js', 'SPF', '7', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00565', '专项资金回退查看', 'static/app/spf/external/SpfCSpfExternaBackspace.js', 'SPF', '6', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B35BEAAE8291322E0533A06A8C01884', '横向专项待办主体', 'static/app/spf/external/SpfBSpfExternal.js', 'SPF', '8', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B35BEAAE82A1322E0533A06A8C01884', '横向项目待办主体', 'static/app/spf/external/SpfBProjectExternal.js', 'SPF', '8', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A66', '专项项目回退主体', 'static/app/spf/external/SpfCProjectExternaBackspace.js', 'SPF', '6', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CA825DFE0533A06A8C02937', '纵向专项上报主体', 'static/app/spf/external/SpfCSpfExternaReport.js', 'SPF', '1', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CA925DFE0533A06A8C02937', '纵向专项下发主体', 'static/app/spf/external/SpfCSpfExternaIssued.js', 'SPF', '2', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CAA25DFE0533A06A8C02937', '纵向项目上报主体', 'static/app/spf/external/SpfCProjectExternaReport.js', 'SPF', '1', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CAB25DFE0533A06A8C02937', '纵向项目下发主体', 'static/app/spf/external/SpfCProjectExternaIssued.js', 'SPF', '2', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CAC25DFE0533A06A8C02937', '横向专项拟稿主体', 'static/app/spf/external/SpfC2BSpfExternaTemp.js', 'SPF', '3', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CAD25DFE0533A06A8C02937', '横向项目拟稿主体', 'static/app/spf/external/SpfC2BProjectExternaTemp.js', 'SPF', '3', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CAE25DFE0533A06A8C02937', '横向专项概要主体', 'static/app/spf/external/SpfBSpfDetailExternal.js', 'SPF', '4', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CAF25DFE0533A06A8C02937', '横向项目概要主体', 'static/app/spf/external/SpfBProjectDetailExternal.js', 'SPF', '4', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4E378CDCA80E4EE9E0533A06A8C0EE9A', '专项项目轨迹', 'static/app/spf/external/selectSpfSendCFP.js', 'SPF', '12', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4E478CDCA80F4EE9E0533A06A8C0EE9A', '专项资金轨迹', 'static/app/spf/external/selectSpfSendCFZJ.js', 'SPF', '12', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4E578CDCA80F4EE9E0533A06A8C0EE9A', '一级项目轨迹', 'static/app/spf/external/selectSpfSendCYJ.js', 'SPF', '12', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4E678CDCA80F4EE9E0533A06A8C0EE9A', '二级项目轨迹', 'static/app/spf/external/selectSpfSendCPEJ.js', 'SPF', '12', '1');

end;
--初始化Bd_t_Busijsurl
