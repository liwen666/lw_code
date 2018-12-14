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
values ('4A1CA405E33768E0E0533A06A8C0699D', '����һ����Ŀ�鿴', 'static/app/spf/external/selectSpfMainCYJ.js', 'SPF', '5', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4A1CA405E33868E0E0533A06A8C0699D', '����ר���ʽ�鿴', 'static/app/spf/external/selectSpfMainCFZJ.js', 'SPF', '5', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4A1CA405E33968E0E0533A06A8C0699D', '���������Ŀ�鿴', 'static/app/spf/external/selectSpfMainCPEJ.js', 'SPF', '5', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4A1CA405E33A68E0E0533A06A8C0699D', '����ר����Ŀ�鿴', 'static/app/spf/external/selectSpfMainCFP.js', 'SPF', '5', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A00', '�������ר��鿴', 'static/app/spf/external/SpfCSpfNewExternal.js', 'SPF', '9', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A22', '���������Ŀ�鿴', 'static/app/spf/external/SpfCNewProjectExternal.js', 'SPF', '9', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4CF2D8D5009865DBE0533A06A8C0310F', 'ר�����·�������', 'static/app/spf/external/SpfBSpfRedistribution.js', 'SPF', '10', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4D915AD388230EB2E0533A06A8C0D0D6', '��Ŀ���·�������', 'static/app/spf/external/SpfBProjectRedistribution.js', 'SPF', '10', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4BB3002043683F04E0533A06A8C00F3A', '����ר��ز鿴', 'static/app/spf/external/SpfCSpfExternaReback.js', 'SPF', '7', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4A1CA405E33A68E0E0533A06A8C0689D', '������Ŀ��������', 'static/app/spf/external/SpfCProjectExternaReback.js', 'SPF', '7', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00565', 'ר���ʽ���˲鿴', 'static/app/spf/external/SpfCSpfExternaBackspace.js', 'SPF', '6', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B35BEAAE8291322E0533A06A8C01884', '����ר���������', 'static/app/spf/external/SpfBSpfExternal.js', 'SPF', '8', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B35BEAAE82A1322E0533A06A8C01884', '������Ŀ��������', 'static/app/spf/external/SpfBProjectExternal.js', 'SPF', '8', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A66', 'ר����Ŀ��������', 'static/app/spf/external/SpfCProjectExternaBackspace.js', 'SPF', '6', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CA825DFE0533A06A8C02937', '����ר���ϱ�����', 'static/app/spf/external/SpfCSpfExternaReport.js', 'SPF', '1', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CA925DFE0533A06A8C02937', '����ר���·�����', 'static/app/spf/external/SpfCSpfExternaIssued.js', 'SPF', '2', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CAA25DFE0533A06A8C02937', '������Ŀ�ϱ�����', 'static/app/spf/external/SpfCProjectExternaReport.js', 'SPF', '1', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CAB25DFE0533A06A8C02937', '������Ŀ�·�����', 'static/app/spf/external/SpfCProjectExternaIssued.js', 'SPF', '2', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CAC25DFE0533A06A8C02937', '����ר���������', 'static/app/spf/external/SpfC2BSpfExternaTemp.js', 'SPF', '3', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CAD25DFE0533A06A8C02937', '������Ŀ�������', 'static/app/spf/external/SpfC2BProjectExternaTemp.js', 'SPF', '3', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CAE25DFE0533A06A8C02937', '����ר���Ҫ����', 'static/app/spf/external/SpfBSpfDetailExternal.js', 'SPF', '4', '1');

insert into Bd_t_Busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4B5233F44CAF25DFE0533A06A8C02937', '������Ŀ��Ҫ����', 'static/app/spf/external/SpfBProjectDetailExternal.js', 'SPF', '4', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4E378CDCA80E4EE9E0533A06A8C0EE9A', 'ר����Ŀ�켣', 'static/app/spf/external/selectSpfSendCFP.js', 'SPF', '12', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4E478CDCA80F4EE9E0533A06A8C0EE9A', 'ר���ʽ�켣', 'static/app/spf/external/selectSpfSendCFZJ.js', 'SPF', '12', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4E578CDCA80F4EE9E0533A06A8C0EE9A', 'һ����Ŀ�켣', 'static/app/spf/external/selectSpfSendCYJ.js', 'SPF', '12', '1');

insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4E678CDCA80F4EE9E0533A06A8C0EE9A', '������Ŀ�켣', 'static/app/spf/external/selectSpfSendCPEJ.js', 'SPF', '12', '1');

end;
--��ʼ��Bd_t_Busijsurl
