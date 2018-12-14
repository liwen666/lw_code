declare
v_n number(8):=0;
v_n2 number(8):=0;
v_tabwhere varchar2(1500);
begin
SELECT COUNT(1) INTO v_n FROM user_tables WHERE table_name = 'P#BGT_T_JBZCMXB';
SELECT COUNT(1) INTO v_n2  FROM user_views WHERE view_name = 'BGT_T_JBZCMXB';
if v_n=0 and v_n2=1 then 
  select t.TABSWHERE into v_tabwhere from dict_t_model t where t.tableid='DEB397AD6B7E4C1D96751492F6ED57B6';
   if instr(v_tabwhere,'ISADJUST')=0 then 
      update  dict_t_model tb set tb.TABSWHERE='ISADJUST=''0'' AND '||v_tabwhere where  tb.tableid='DEB397AD6B7E4C1D96751492F6ED57B6';
      
      sys_p_recreate_views('DEB397AD6B7E4C1D96751492F6ED57B6');
   end if;

 end if;
end;
--jbzcmxb单表模式视图中追加isadjust=0
