BEGIN
DELETE FROM bd_t_initparambean WHERE appid IN ('BAS', 'BGT');

insert into bd_t_initparambean (DATAKEY, BEANID, BEANNAME, APPID, TYPE, STATUS)
values ('4A9C9528E45B6234E0533A06A8C15F0C', 'bDetailParamService', '������ϸ����', 'BAS', 'batchDetail',  '1');

insert into bd_t_initparambean (DATAKEY, BEANID, BEANNAME, APPID, TYPE, STATUS)
values ('4A94240A159C1C85E0533A06A8C0F2AC', 'commonParamService', '��ʼ��ҵ�����', 'BGT', 'initBusiParam',  '1');

insert into bd_t_initparambean (DATAKEY, BEANID, BEANNAME, APPID, TYPE, STATUS)
values ('4A94240A159D1C85E0533A06A8C0F2AC', 'commonParamService', '��ʼ��ҵ�����', 'BAS', 'initBusiParam', '1');

insert into bd_t_initparambean (DATAKEY, BEANID, BEANNAME, APPID, TYPE,  STATUS)
values ('4A9C9528E45B6234E0533A06A8C05F0C', 'bDetailParamService', '������ϸ����', 'BGT', 'batchDetail',  '1');

END;
--�������̻�ȡĬ�ϲ���Bean�Ǽ�
