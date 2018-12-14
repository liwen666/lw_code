declare
v_n number(8):=0;
v_n2 number(8):=0;
v_dealType char(4):='2101';
v_tableID varchar2(32);
v_triger_zjoh_num number(8):=0;
v_bakFlag number(8);
v_bakTFlag number(8);
begin
  select count(0)  into v_bakTFlag  from user_tables c where lower(c.TABLE_NAME) = 'p#bgt_t_zcxmmxb_bak';
  select count(0) into v_triger_zjoh_num from user_triggers where lower(trigger_name) = 'tr_bgt_zczxmmxb_zi_jin_ou_he'; 
  select count(0) into v_n from user_tab_columns c where lower(c.TABLE_NAME)='p#bgt_t_zcxmmxb' and c.COLUMN_NAME='ISADJUST';
    select count(0) into v_n2 from user_tab_columns c where lower(c.TABLE_NAME)='p#bgt_t_zcxmmxb' and c.COLUMN_NAME='ADJUSTCOMMENT';
  select m.tableid into v_tableID  from dict_t_model m where m.DEALTYPE=v_dealType; 
 if v_n=0 or v_n2=0 then 
    if v_triger_zjoh_num=1 then 
      execute immediate 'alter trigger tr_bgt_zczxmmxb_zi_jin_ou_he disable';
    end if;
   if v_n=0 then  
    execute immediate  Q'{alter table P#bgt_t_zcxmmxb add ISADJUST CHAR(1) DEFAULT '0'}';
   end if;
   --备份表
   if v_bakTFlag=1 then 
   select count(0) into v_bakFlag from user_tab_columns c where lower(c.TABLE_NAME)='p#bgt_t_zcxmmxb_bak' and c.COLUMN_NAME='ISADJUST';
   if v_bakFlag=0 then
     execute immediate  Q'{alter table P#bgt_t_Zcxmmxb_Bak add ISADJUST CHAR(1) DEFAULT '0'}';
   end if; 
   
   end if;
 
   if v_n2=0 then 
    execute immediate  Q'{alter table P#bgt_t_zcxmmxb add ADJUSTCOMMENT VARCHAR2(500)}';
   end if;
      --备份表
  if v_bakTFlag=1 then 
   select count(0) into v_bakFlag from user_tab_columns c where lower(c.TABLE_NAME)='p#bgt_t_zcxmmxb_bak' and c.COLUMN_NAME='ADJUSTCOMMENT';
   if v_bakFlag=0 then
     execute immediate  Q'{alter table  P#bgt_t_Zcxmmxb_Bak add ADJUSTCOMMENT VARCHAR2(500)}';
   end if;
  end if;
  
   if v_triger_zjoh_num=1 then 
      execute immediate 'alter trigger tr_bgt_zczxmmxb_zi_jin_ou_he enable';
    end if;
 
  
  --初始化factor信息
   if v_n=0 then  
  insert into dict_t_factor (COLUMNID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, EXTPROP, ISKEY, ISLEAF, ISUPDATE, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, SHOWFORMAT, SUPERID, TABLEID)
  values ('2D32EFE4987A91DBE050A8C021051137', 1, 3, 'ISADJUST', '''0''', '0000000000000000000000000000000', '0', '1', '1', '0', 1, '是否调整', '0', 63, '0', '0', v_tableID);
   end if;
   
if v_n2=0 then 
   insert into dict_t_factor (COLUMNID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, EXTPROP, ISKEY, ISLEAF, ISUPDATE, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, SHOWFORMAT, SUPERID, TABLEID)
   values ('2D32EFE4987B91DBE050A8C021051137', 500, 3, 'ADJUSTCOMMENT', '', '0000000000000000000000000000000', '0', '1', '1', '1', 1, '调整说明', '1', 64, '0', '0', v_tableID);
end if;  
  SYS_P_RECREATE_VIEWS(v_tableID);
  end if;

end;

--01_给支出项目明细表增加isAdjust和adjustcomment列
