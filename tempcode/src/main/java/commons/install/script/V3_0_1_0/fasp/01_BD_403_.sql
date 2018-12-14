begin
delete from fasp_t_dependencies where APPID='bgt' and MVNAME='BD_M_SYSORG';
insert into fasp_t_dependencies (APPID, MVNAME, DSNAME)
values ('bgt', 'BD_M_SYSORG', 'P#FASP_T_PUPFASP003');

insert into fasp_t_dependencies (APPID, MVNAME, DSNAME)
values ('bgt', 'BD_M_SYSORG', 'P#FASP_T_PUPAGENCY');

insert into fasp_t_dependencies (APPID, MVNAME, DSNAME)
values ('bgt', 'BD_M_SYSORG', 'P#FASP_T_PUPCS021');

