Declare
  v_n1 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'BGT_T_FORMULADZB';
  if v_n1 = 0 then
    execute immediate Q'{create table BGT_T_FORMULADZB(guid VARCHAR2(32) default SYS_GUID() not null,dataType CHAR(1),agencyID VARCHAR2(32),spfID  VARCHAR2(32),expecoID VARCHAR2(32),columnID VARCHAR2(32),TABLEID VARCHAR2(32),amounts NUMBER(18,2),formulaID VARCHAR2(32),formulaAmounts NUMBER(18,2))}';
    execute immediate 'alter table BGT_T_FORMULADZB add constraint BGT_T_FORMULADZB_PKY  primary key (guid)';
    SYS_P_PARTITION_TABLE('BGT_T_FORMULADZB');
  
  end if;
end;


--15_wkn_CREATE_T_FORMULADZB

