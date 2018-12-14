declare
v_n number(8):=0;
v_n2 number(8):=0;
v_sql clob;
v_dealType char(4):='2301';
v_tableID varchar2(32);
v_reColID varchar2(32);
v_reTableID varchar2(32);
v_tabWhere varchar2(2000);
v_orderID number(16);
begin
  select count(0) into v_n from user_tab_columns c where lower(c.TABLE_NAME)='bgt_t_jbzcjgb' and c.COLUMN_NAME='ADJUSTCOMMENT';
    select count(0) into v_n2 from user_tab_columns c where lower(c.TABLE_NAME)='bgt_t_jbzcjgb' and c.COLUMN_NAME='ISADJUST'; 
 select m.tableid,m.TABSWHERE into v_tableID,v_tabWhere  from dict_t_model m where m.DEALTYPE=v_dealType;
 if v_n2=0 then
   select  f.COLUMNID,f.TABLEID into v_reColID,v_reTableID from   dict_t_factor f where upper(f.DBCOLUMNNAME)='ISADJUST' and f.TABLEID in(select m.TABLEID from dict_t_model m where m.dealtype='2101');
   insert into dict_t_factor (COLUMNID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, EXTPROP, FRMCOLID, FRMTABID, ISKEY, ISLEAF, ISUPDATE, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, SHOWFORMAT, SUPERID, TABLEID)values ('2D32EFE4987C91DBE050A8C021051137', 1, 3, 'ISADJUST', '0', '0000000000000000000000000000000', v_reColID, v_reTableID, '1', '1', '1', '0', 1, '是否调整', '0', 63, '0', '0', v_tableID);
 end if;

 if v_n=0 then 
  select (max(orderid)+1) into v_orderID   from dict_t_factor  where tableid=v_tableID;
  select  f.COLUMNID,f.TABLEID into v_reColID,v_reTableID  from   dict_t_factor f where upper(f.DBCOLUMNNAME)='ADJUSTCOMMENT' and f.TABLEID in(select m.TABLEID from dict_t_model m where m.dealtype='2101');
  insert into dict_t_factor (COLUMNID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, EXTPROP, FRMCOLID, FRMTABID, ISKEY, ISLEAF, ISUPDATE, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, SHOWFORMAT, SUPERID, TABLEID)values ('2D32EFE4987D91DBE050A8C021051137', 500, 3, 'ADJUSTCOMMENT', '', '0000000000000000000000000000000', v_reColID, v_reTableID, '0', '1', '1', '1', 1, '调整说明', '1', v_orderID, '0', '0', v_tableID);
end if;
if v_n=0 or v_n2=0 then 
  
     v_sql:='CREATE OR REPLACE VIEW BGT_T_JBZCJGB AS SELECT ';
   for c_col in(select c.COLUMN_NAME as colName from user_tab_columns  c where c.TABLE_NAME='BGT_T_JBZCJGB' and c.COLUMN_NAME not in('ADJUSTCOMMENT','ISADJUST')) loop
     v_sql:=v_sql||'bgt_t_zcxmmxb.'||c_col.colname||',';
   
     end loop;
     v_sql:=v_sql||'bgt_t_zcxmmxb.ISADJUST,bgt_t_zcxmmxb.ADJUSTCOMMENT  FROM BGT_T_ZCXMMXB bgt_t_zcxmmxb  WHERE ';
     v_sql:=v_sql||v_tabWhere;
  --从新创建视图
  --dbms_output.put_line(v_sql);
  execute immediate v_sql;  

  end if;

end;

--02_bgt_t_jbzcjgb增加isadJust和adjustcomment字段
