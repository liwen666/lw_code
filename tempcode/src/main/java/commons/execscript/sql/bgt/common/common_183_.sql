BEGIN

DELETE FROM Bd_t_Sendsystem WHERE appid IN ('BAS', 'BGT');

insert into bd_t_sendsystem (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A11', 'ͨ�÷���ϵͳ�ϱ�', 'bgtReportServiceImpl', 'BGT', '1', '1');

insert into bd_t_sendsystem (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A22', 'ͨ�÷���ϵͳ�·�', 'bgtAssignServiceImpl', 'BGT', '2', '1');

insert into bd_t_sendsystem (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A33', 'ͨ�÷���ϵͳ�ϱ�', 'basReportServiceImpl', 'BAS', '1', '1');

insert into bd_t_sendsystem (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF22127C00A44', 'ͨ�÷���ϵͳ�·�', 'basAssignServiceImpl', 'BAS', '2', '1');

insert into bd_t_sendsystem (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF21127C00A44', 'ͨ�÷���ϵͳ����', 'bdBusiRollbackServiceImpl', 'BGT', '3', '1');

insert into bd_t_sendsystem (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('6483E84AFB434D14BECFF23127C00A44', 'ͨ�÷���ϵͳ����', 'bdBusiRollbackServiceImpl', 'BAS', '3', '1');


END;
--����ϵͳ�Ǽ�
