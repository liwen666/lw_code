BEGIN
DELETE FROM bd_t_initparambean WHERE appid IN ('BAS', 'BGT');

insert into bd_t_initparambean (DATAKEY, BEANID, BEANNAME, APPID, TYPE, STATUS)
values ('4A9C9528E45B6234E0533A06A8C15F0C', 'bDetailParamService', '横向明细批次', 'BAS', 'batchDetail',  '1');

insert into bd_t_initparambean (DATAKEY, BEANID, BEANNAME, APPID, TYPE, STATUS)
values ('4A94240A159C1C85E0533A06A8C0F2AC', 'commonParamService', '初始化业务参数', 'BGT', 'initBusiParam',  '1');

insert into bd_t_initparambean (DATAKEY, BEANID, BEANNAME, APPID, TYPE, STATUS)
values ('4A94240A159D1C85E0533A06A8C0F2AC', 'commonParamService', '初始化业务参数', 'BAS', 'initBusiParam', '1');

insert into bd_t_initparambean (DATAKEY, BEANID, BEANNAME, APPID, TYPE,  STATUS)
values ('4A9C9528E45B6234E0533A06A8C05F0C', 'bDetailParamService', '横向明细批次', 'BGT', 'batchDetail',  '1');

END;
--开启流程获取默认参数Bean登记
