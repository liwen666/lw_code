DECLARE
  n INTEGER;
  a INTEGER;
  sqlstr VARCHAR2(2000); 
begin
  
  --�����ֶ� X_END
  select count(1) into n from user_tab_columns t where t.TABLE_NAME = 'P#BGT_T_CHART' and t.COLUMN_NAME = 'X_END';
  if n = 0 then
     sqlstr := 'alter table p#bgt_t_chart add x_end number';
     execute immediate sqlstr;
  end if;
  
  --�����ֶ� Y_END
  select count(1) into n from user_tab_columns t where t.TABLE_NAME = 'P#BGT_T_CHART' and t.COLUMN_NAME = 'Y_END';
  if n = 0 then
     sqlstr := 'alter table p#bgt_t_chart add y_end number';
     execute immediate sqlstr;
  end if;
  
  --ɾ���ֶ� TOOLTIP_NAME
  select count(1) into n from user_tab_columns t where t.TABLE_NAME = 'P#BGT_T_CHART' and t.COLUMN_NAME = 'TOOLTIP_NAME';
  if n <> 0 then
     sqlstr := 'alter table p#bgt_t_chart drop column tooltip_name';
     execute immediate sqlstr;
  end if;
  
  --ɾ���ֶ� SAVEASIMAGE_IS_SHOW
  select count(1) into n from user_tab_columns t where t.TABLE_NAME = 'P#BGT_T_CHART' and t.COLUMN_NAME = 'SAVEASIMAGE_IS_SHOW';
  if n <> 0 then
     sqlstr := 'alter table p#bgt_t_chart drop column saveasimage_is_show';
     execute immediate sqlstr;
     
     sys_p_partition_table('P#BGT_T_CHART');
  end if;

end;

--17��ͼ���޸��ֶ�x_end,y_end
