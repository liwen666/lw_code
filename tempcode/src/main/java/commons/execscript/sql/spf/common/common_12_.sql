begin
--专项资金类别修改模板数据脚本
delete from spf_t_projecttype where projtypeid in ('1','2','3','9');

insert into spf_t_projecttype (ORDERID, SUPERID, ISLEAF, PROJTYPEID, LEVELNO, PROJTYPENAME, ISBASE, CODE, DISTRICTID, BGTLVL, MANADEPT, PROJECTBPMN, DATAKEY, SPFBPMN, PROJTYPE, STATUS)
values (1, '0', '0', '1', 1, '项目支出', '0', '001', '*', null, null, null, '23B449A8B50BC399E055000000000001', null, '1', '1');

insert into spf_t_projecttype (ORDERID, SUPERID, ISLEAF, PROJTYPEID, LEVELNO, PROJTYPENAME, ISBASE, CODE, DISTRICTID, BGTLVL, MANADEPT, PROJECTBPMN, DATAKEY, SPFBPMN, PROJTYPE, STATUS)
values (2, '1', '0', '2', 2, '省级一项级目', '0', '001002', '*', null, null, null, '23B449A8B50CC399E055000000000001', null, '2', '1');

insert into spf_t_projecttype (ORDERID, SUPERID, ISLEAF, PROJTYPEID, LEVELNO, PROJTYPENAME, ISBASE, CODE, DISTRICTID, BGTLVL, MANADEPT, PROJECTBPMN, DATAKEY, SPFBPMN, PROJTYPE, STATUS)
values (4, '1', '0', '3', 2, '专项转移支付', '0', '001003', '*', null, null, null, '23B449A8B50EC399E055000000000001', null, '3', '1');

insert into spf_t_projecttype (ORDERID, SUPERID, ISLEAF, PROJTYPEID, LEVELNO, PROJTYPENAME, ISBASE, CODE, DISTRICTID, BGTLVL, MANADEPT, PROJECTBPMN, DATAKEY, SPFBPMN, PROJTYPE, STATUS)
values (3, '0', '0', '9', 1, '基本支出', '0', '009', '*', null, null, null, '23B449A8B50DC399E055000000000001', null, '9', '1');
end;
--专项资金类别修改模板数据脚本
