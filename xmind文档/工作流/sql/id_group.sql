prompt Importing table act_id_group...
set feedback off
set define off
insert into act_id_group (ID_, REV_, NAME_, TYPE_, PID_, CATEGORY_, ORDERCODE, ORGCODE, PROVINCE)
values ('user', 1, 'User', '2', null, null, null, null, null);

insert into act_id_group (ID_, REV_, NAME_, TYPE_, PID_, CATEGORY_, ORDERCODE, ORGCODE, PROVINCE)
values ('test', 1, 'Test', '2', null, null, null, null, null);

insert into act_id_group (ID_, REV_, NAME_, TYPE_, PID_, CATEGORY_, ORDERCODE, ORGCODE, PROVINCE)
values ('role', 1, 'Role', '2', null, null, null, null, null);

insert into act_id_group (ID_, REV_, NAME_, TYPE_, PID_, CATEGORY_, ORDERCODE, ORGCODE, PROVINCE)
values ('dep1', 1, 'Dep1', '1', '0', null, null, null, null);

insert into act_id_group (ID_, REV_, NAME_, TYPE_, PID_, CATEGORY_, ORDERCODE, ORGCODE, PROVINCE)
values ('dep2', 1, 'Dep2', '1', 'dep1', null, null, null, null);

insert into act_id_group (ID_, REV_, NAME_, TYPE_, PID_, CATEGORY_, ORDERCODE, ORGCODE, PROVINCE)
values ('dep3', 1, 'Dep3', '1', 'dep1', null, null, null, null);

insert into act_id_group (ID_, REV_, NAME_, TYPE_, PID_, CATEGORY_, ORDERCODE, ORGCODE, PROVINCE)
values ('manager', 1, 'Manager', '2', null, null, null, null, null);

insert into act_id_group (ID_, REV_, NAME_, TYPE_, PID_, CATEGORY_, ORDERCODE, ORGCODE, PROVINCE)
values ('111', 1, '111', '3', null, 'qwer', null, null, null);

insert into act_id_group (ID_, REV_, NAME_, TYPE_, PID_, CATEGORY_, ORDERCODE, ORGCODE, PROVINCE)
values ('222', 1, '222', '3', null, 'qwer', null, null, null);

prompt Done.
