begin
delete from dict_t_settingtabinfo where guid ='1C0DEACBED854968B641C772CAB12354';
insert into dict_t_settingtabinfo (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
values ('1C0DEACBED854968B641C772CAB12354', 'P#SPF_T_INBUDGETTEMP', '对下项目纳入预算中间表', null, null, 'spf', '1', '0', '0', '0', '0', '0', null);
end;
--纳入预算表分区
