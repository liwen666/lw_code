BEGIN
  
DELETE FROM bd_t_handlebean WHERE appid IN ('BAS','BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9949D5824E0533A06A8C0CA99', '16', 'distShowIssuedMenuService', '是否显示下发按钮', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9949D5824E0533A06A8C0CA66', '16', 'distShowIssuedMenuService', '是否显示下发按钮', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9945B5824E0533A06A8C0CA69', '9', 'cBackLevelService', '纵向回退级次', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9945C5824E0533A06A8C0CA69', '10', 'cBackRecordLogService', '纵向回退日志', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE994695824E0533A06A8C0CA69', '7', 'bRecordLogService', '横向记录日志', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9946A5824E0533A06A8C0CA69', '8', 'bStatusChangeService', '横向更改流程状态', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9946B5824E0533A06A8C0CA69', '1', 'cIssuedRecordLogService', '纵向下发日志', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9946C5824E0533A06A8C0CA69', '2', 'cReportRecordLogService', '纵向上报日志', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9946E5824E0533A06A8C0CA69', '6', 'copyObjectC2BService', '纵向活动表向横向日志表复制业务主体', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9946F5824E0533A06A8C0CA69', '5', 'cStatusChangeService', '纵向更改流程状态', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE994705824E0533A06A8C0CA69', '3', 'issuedRangeService', '下发范围', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE994715824E0533A06A8C0CA69', '4', 'reportRangeService', '上报范围', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE994725824E0533A06A8C0CA69', '7', 'bRecordLogService', '横向记录日志', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE994735824E0533A06A8C0CA69', '8', 'bStatusChangeService', '横向更改流程状态', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE994745824E0533A06A8C0CA69', '1', 'cIssuedRecordLogService', '纵向下发日志', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE994755824E0533A06A8C0CA69', '2', 'cReportRecordLogService', '纵向上报日志', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE994775824E0533A06A8C0CA69', '6', 'copyObjectC2BService', '纵向活动表向横向日志表复制业务主体', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE994785824E0533A06A8C0CA69', '5', 'cStatusChangeService', '纵向更改流程状态', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE994795824E0533A06A8C0CA69', '3', 'issuedRangeService', '下发范围', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9947A5824E0533A06A8C0CA69', '4', 'reportRangeService', '上报范围', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9947B5824E0533A06A8C0CA69', '9', 'cBackLevelService', '纵向回退级次', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9947C5824E0533A06A8C0CA69', '10', 'cBackRecordLogService', '纵向回退日志', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE994875824E0533A06A8C0CA69', '11', 'cRevokeRecordLogService', '纵向撤回日志', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE994885824E0533A06A8C0CA69', '11', 'cRevokeRecordLogService', '纵向撤回日志', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9949A5824E0533A06A8C0CA69', '12', 'dataAuditBHandlerService', '数据审核', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9949B5824E0533A06A8C0CA69', '12', 'dataAuditBHandlerService', '数据审核', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9949C5824E0533A06A8C0CA69', '13', 'bNewSelectRecordLogService', '横向分发业务主体选择', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9949D5824E0533A06A8C0CA69', '13', 'bNewSelectRecordLogService', '横向分发业务主体选择', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9949D5824E0533A06A8C0C855', '15', 'distShowReportMenuService', '是否显示上报按钮', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9949D5824E0533A06A8C0C655', '15', 'distShowReportMenuService', '是否显示上报按钮', 'BAS');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9946F5824E0533A06A8C0C111', '3', 'distIssuedRangeService', '转移支付下发范围', 'BGT');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID)
values ('4CDFACE9946F5824E0533A06A8C0234', '3', 'distIssuedRangeService', '转移支付下发范围', 'BAS');

END;
--插入默认的HandleBean配置
