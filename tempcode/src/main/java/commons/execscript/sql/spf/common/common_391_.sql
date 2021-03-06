declare
begin
delete from bd_t_handlebean where APPID='SPF';
insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994605824E0533A06A8C0CA69', 'spfCommonDataAuditImpl', '数据审核', '12', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4D0827347B2D744FE0533A06A8C0CBFF', 'projectReportServiceImpl', '二级项目报送单位所在部门', '2', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994625824E0533A06A8C0CA69', 'spfIssuedServiceImpl', '专项下发日志', '1', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994635824E0533A06A8C0CA69', 'spfReportInsertLogServiceImpl', '上报日志', '2', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994645824E0533A06A8C0CA69', 'spfCInsertLogServiceImpl', '下发日志', '1', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994655824E0533A06A8C0CA69', 'allDownAgencyQueryServiceImpl', '下发单位查询', '3', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994665824E0533A06A8C0CA69', 'allUpAgencyQueryServiceImpl', '上报单位查询', '4', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994675824E0533A06A8C0CA69', 'spfCIntransitWaitServiceImpl', '修改在途状态', '5', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994685824E0533A06A8C0CA69', 'spfCDefultReportInsertLogServiceImpl', '二级项目报送一级项目主管部门', '2', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994835824E0533A06A8C0CA69', 'spfVerticalReturnServiceImpl4SpFund', '专项（一级项目）回退日志', '10', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994845824E0533A06A8C0CA69', 'spfCBackLevelZXServiceImpl', '专项项目回退范围', '9', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994855824E0533A06A8C0CA69', 'spfCBackLevelYJServiceImpl', '一级项目回退范围', '9', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994865824E0533A06A8C0CA69', 'spfVerticalReturnLogServiceImpl', '专项项目(二级项目)回退日志', '10', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE9948A5824E0533A06A8C0CA69', 'spfVerticalCancelLogServiceImpl', '纵向撤回', '11', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE9948B5824E0533A06A8C0CA69', 'spfCBackLevelLastServiceImpl', '专项回退范围', '9', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE9948C5824E0533A06A8C0CA69', 'spfCBackLevelEJServiceImpl', '二级项目回退范围', '9', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994945824E0533A06A8C0CA69', 'custodyUpServiceImpl', '托管上报日志', '2', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994955824E0533A06A8C0CA69', 'spfBInitBusiLogServiceImpl', '横向初始化日志', '6', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994965824E0533A06A8C0CA69', 'spfBNodeBusiLogServiceImpl', '横向节点流程日志', '7', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994975824E0533A06A8C0CA69', 'spfBFlowEndStatusServiceImpl', '横向修改结束状态', '8', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CF0640211104F66E0533A06A8C0BBD6', 'spfBFlowReassignServiceImpl', '重新分配日志', '13', 'SPF');

insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CF0640211104F66E0533A06A8DDBBD6', 'spfCBackLevelEJSJServiceImpl', '二级项目(上级部门)回退范围', '9', 'SPF');
end;
--初始化bd_t_handlebean
