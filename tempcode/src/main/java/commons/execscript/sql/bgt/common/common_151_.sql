declare
--�������������
v_n1 number(8):=0;
v_n2 number(8):=0;
begin 
  
select count(1) into v_n1 from user_views where lower(view_name) = 'bgt_t_checkcondition';
select count(1) into v_n2 from user_tables where lower(table_name) = 'bgt_t_checkcondition';
if v_n1=0 and v_n2=0 then 
  execute immediate Q'{create  table bgt_t_checkCondition(guid varchar2(32) default sys_guid(),checkresultID varchar2(32),deid varchar2(32),deidVal varchar2(1000),conTotalNum number(8))}';
  execute immediate Q'{comment on column BGT_T_CHECKCONDITION.checkresultid is '��˽�����ID'}';
  execute immediate Q'{comment on column BGT_T_CHECKCONDITION.deid is '����ԴID'}';
  execute immediate Q'{comment on column BGT_T_CHECKCONDITION.deidval is '����ԴID����ֵ'}';
  execute immediate Q'{comment on column BGT_T_CHECKCONDITION.contotalnum  is '�ܵ���������'}';
  execute immediate Q'{alter table bgt_t_checkCondition  add constraint BGT_T_CHECKCONDITION_PKY  primary key (guid) }';
  SYS_P_PARTITION_TABLE('bgt_t_checkCondition');
end if;
end;
--��ʼ�����������
