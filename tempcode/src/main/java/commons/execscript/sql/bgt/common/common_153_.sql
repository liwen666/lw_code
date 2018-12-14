declare
v_n number(8);
begin
  select count(0) into v_n from dict_t_settingtabinfo m where upper(m.DBTABLENAME)='P#BGT_T_CHECKCONDITION';
  if v_n=0 then
   insert into dict_t_settingtabinfo(dbtablename,name,appID,YEARPART,PROVINCEPART,INITIALIZE,ISLEAD) values  ('P#BGT_T_CHECKCONDITION','审核条件表','bgt','1','1','0','0');
  end if;
end;
--将check_t_condition加入分区设置表中
