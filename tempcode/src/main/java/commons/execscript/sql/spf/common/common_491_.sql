DECLARE
  begin
delete from bd_t_handlebean where datakey in('4FDC7D92CFAB3641E0533A06A8C0F682','4FDC7D92CFA93641E0533A06A8C0F682','4FDC7D92CFA83641E0533A06A8C0F682','506BF8A163673A38E0533906A8C04658','507840079C544450E0533906A8C0BE6B','507840079C554450E0533506A8C0BE6B');
insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID, STATUS)
values ('4FDC7D92CFA83641E0533A06A8C0F682', '18', 'defaultTitleServiceImpl', '���ͱ���λĬ�ϱ���', 'SPF', '1');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID, STATUS)
values ('4FDC7D92CFA93641E0533A06A8C0F682', '19', 'defaultTitleServiceImpl', '�ϱ�Ĭ�ϱ���', 'SPF', '1');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID, STATUS)
values ('4FDC7D92CFAB3641E0533A06A8C0F682', '20', 'defaultTitleServiceImpl', '�·�Ĭ�ϱ���', 'SPF', '1');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID, STATUS)
values ('506BF8A163673A38E0533906A8C04658', '25', 'defaultTitleServiceImpl', '����Ĭ�ϱ���', 'SPF', '1');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID, STATUS)
values ('507840079C544450E0533906A8C0BE6B', '22', 'autoStartCFlowDownServiceImpl', ' ��������Զ������·�����', 'SPF', '1');

insert into bd_t_handlebean (DATAKEY, HANDLETYPE, HANDLEBEANCODE, HANDLENAME, APPID, STATUS)
values ('507840079C554450E0533506A8C0BE6B', '22', 'autoStartCFlowReportServiceImpl', ' ��������Զ������ϱ�����', 'SPF', '1');


end;--SPF��ʼ��bd_t_handlebeanĬ�ϱ���
--init_spf_handlebean
