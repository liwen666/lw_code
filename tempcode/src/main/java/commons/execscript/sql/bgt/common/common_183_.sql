BEGIN

DELETE FROM Bd_t_Sendsystem WHERE appid IN ('BAS', 'BGT');

insert into bd_t_sendsystem (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A11', '通用发送系统上报', 'bgtReportServiceImpl', 'BGT', '1', '1');

insert into bd_t_sendsystem (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A22', '通用发送系统下发', 'bgtAssignServiceImpl', 'BGT', '2', '1');

insert into bd_t_sendsystem (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A33', '通用发送系统上报', 'basReportServiceImpl', 'BAS', '1', '1');

insert into bd_t_sendsystem (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A44', '通用发送系统下发', 'basAssignServiceImpl', 'BAS', '2', '1');

insert into bd_t_sendsystem (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF21127C00A44', '通用发送系统回退', 'bdBusiRollbackServiceImpl', 'BGT', '3', '1');

insert into bd_t_sendsystem (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF23127C00A44', '通用发送系统回退', 'bdBusiRollbackServiceImpl', 'BAS', '3', '1');


END;
--发送系统登记
