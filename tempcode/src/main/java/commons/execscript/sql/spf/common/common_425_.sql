BEGIN
delete from dict_t_settingtabinfo
   where guid in ('4AE5737A6D4F544FE0533A06A8C05A30',
                  '4AE5737A6D50544FE0533A06A8C05A30',
                  '4AE5737A6D4D544FE0533A06A8C05A30',
                  '4AE5737A6D4E544FE0533A06A8C05A30',
                  '58877142369444D2BFC56170F7D77F8E',
                  '1C0DEACBED854968B641C772CABB2DD1');
insert into dict_t_settingtabinfo (GUID, DBTABLENAME, NAME, REMARK, APPID, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD)
values ('4AE5737A6D4E544FE0533A06A8C05A30', 'P#SPF_T_BFLOW_LOG', '项目库横向日志表', '项目库横向日志表', 'spf', '1', '0', '1', '0', '0');

insert into dict_t_settingtabinfo (GUID, DBTABLENAME, NAME, REMARK, APPID, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD)
values ('4AE5737A6D4F544FE0533A06A8C05A30', 'P#SPF_T_CFLOW_ACT', '项目库纵向活动表', '项目库纵向活动表', 'spf', '1', '0', '1', '0', '0');

insert into dict_t_settingtabinfo (GUID, DBTABLENAME, NAME, REMARK, APPID, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD)
values ('4AE5737A6D50544FE0533A06A8C05A30', 'P#SPF_T_CFLOW_HIST', '项目库纵向历史表', '项目库纵向历史表', 'spf', '1', '0', '1', '0', '0');

insert into dict_t_settingtabinfo (GUID, DBTABLENAME, NAME, REMARK, APPID, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD)
values ('58877142369444D2BFC56170F7D77F8E', 'P#SPF_T_FBASESTATUS', '项目库专项状态表', '项目库专项状态表', 'spf', '0', '0', '1', '0', '0');

insert into dict_t_settingtabinfo (GUID, DBTABLENAME, NAME, REMARK, APPID, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD)
values ('1C0DEACBED854968B641C772CABB2DD1', 'P#SPF_T_PBASESTATUS', '项目库项目状态表', '项目库项目状态表', 'spf', '0', '0', '1', '0', '0');

delete from act_hq_code where ID IN('4D06F797AAA50DAEE0533A06A8C012C1','4D06F797AAA60DAEE0533A06A8C012C1');
insert into act_hq_code (ID, CODE_KEY, CODE_NAME, SUPER_ID, CODE_TYPE, ORDER_ID, DEPT_ID)
values ('4D06F797AAA50DAEE0533A06A8C012C1', '${spfBflowAuditServiceImpl}', '专项资金一级项目确认(横向)', '#', 'executionListenerClass', 1, null);

insert into act_hq_code (ID, CODE_KEY, CODE_NAME, SUPER_ID, CODE_TYPE, ORDER_ID, DEPT_ID)
values ('4D06F797AAA60DAEE0533A06A8C012C1', '${projectBflowAuditServiceImpl}', '项目二级项目确认(横向)', '#', 'executionListenerClass', 2, null);
END;
--删除查看菜单，url新增itemtyp
