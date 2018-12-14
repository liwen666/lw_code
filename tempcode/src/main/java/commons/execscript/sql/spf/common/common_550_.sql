DECLARE
  n_count integer;
  n_count_bak integer;
  v_tableid varchar2(32);
BEGIN
  select tableid into v_tableid from dict_t_model m where m.DBTABLENAME = 'KPI_T_FXMJXZBB';
  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') LOOP
    insert into P#DICT_T_FACTOR(year,province,columnid,datalength,datatype,dbcolumnname,DBVERSION,EXTPROP,ISBANDCOL,ISHREF,OPENWINDOWTYPE,ISKEY,isleaf,ISREGEX,ISRESERVE,ISSUM,ISUPDATE,ISVIRTUAL,ISVISIBLE,LEVELNO,NAME,NULLABLE,ORDERID,SCALE,SHOWFORMAT,SHOWWIDTH,STATUS,SUPERID,TABLEID,INSERTVERSION,PARENTNODECANCHECK)
     select v_row.year,v_row.districtid,'5186FE2D6DF23278E0533906A8C0AE4E',30,3,'HISTORYLIST',SYSDATE,'0000000000000000000000000000000','0','0','1','0','1','0','0','0','0','0','0',1,'历史指标',1,35,'0','0',100,1,0,v_tableid,sysdate,'0'
     from dual
     where not exists (SELECT 1 FROM P#DICT_T_FACTOR WHERE PROVINCE = V_ROW.DISTRICTID AND YEAR = V_ROW.YEAR  AND TABLEID = v_tableid AND DBCOLUMNNAME = 'HISTORYLIST');
  END LOOP;
  
   SELECT COUNT(*) INTO n_count FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'P#KPI_T_FXMJXZBB' AND COLUMN_NAME = 'HISTORYLIST';
  IF n_count = 0 THEN
     EXECUTE IMMEDIATE 'alter table P#KPI_T_FXMJXZBB add HISTORYLIST varchar2(30) default 0';
  END IF;
 
  SELECT COUNT(*) INTO n_count FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'P#KPI_T_FXMJXZBB_BAK';
  IF n_count > 0 THEN
    SELECT COUNT(*) INTO n_count_bak FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'P#KPI_T_FXMJXZBB_BAK' AND COLUMN_NAME = 'HISTORYLIST';
    if n_count_bak = 0 then
      EXECUTE IMMEDIATE 'alter table P#KPI_T_FXMJXZBB_BAK add HISTORYLIST varchar2(30) default 0';
    end if;
  END IF;
   sys_p_recreate_views(v_tableid);
  select tableid into v_tableid from dict_t_model m where m.DBTABLENAME = 'KPI_T_FZXZJJXZB';
  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') LOOP
    insert into P#DICT_T_FACTOR(year,PROVINCE,columnid,datalength,datatype,dbcolumnname,DBVERSION,EXTPROP,ISBANDCOL,ISHREF,OPENWINDOWTYPE,ISKEY,isleaf,ISREGEX,ISRESERVE,ISSUM,ISUPDATE,ISVIRTUAL,ISVISIBLE,LEVELNO,NAME,NULLABLE,ORDERID,SCALE,SHOWFORMAT,SHOWWIDTH,STATUS,SUPERID,TABLEID,INSERTVERSION,PARENTNODECANCHECK)
     select v_row.year,v_row.districtid,'5186FE2D6DF33278E0533906A8C0AE4E',30,3,'HISTORYLIST',SYSDATE,'0000000000000000000000000000000','0','0','1','0','1','0','0','0','0','0','0',1,'历史指标',1,35,'0','0',100,1,0,v_tableid,sysdate,'0'
     from dual
     where not exists (SELECT 1 FROM P#DICT_T_FACTOR WHERE PROVINCE = V_ROW.DISTRICTID AND YEAR = V_ROW.YEAR  AND TABLEID = v_tableid AND DBCOLUMNNAME = 'HISTORYLIST');
  END LOOP;
 
  SELECT COUNT(*) INTO n_count FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'P#KPI_T_FZXZJJXZB' AND COLUMN_NAME = 'HISTORYLIST';
  IF n_count = 0 THEN
     EXECUTE IMMEDIATE 'alter table P#KPI_T_FZXZJJXZB add HISTORYLIST varchar2(30) default 0';
  END IF;
  SELECT COUNT(*) INTO n_count FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'P#KPI_T_FZXZJJXZB_BAK';
  IF n_count > 0 THEN
    SELECT COUNT(*) INTO n_count_bak FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'P#KPI_T_FZXZJJXZB_BAK' AND COLUMN_NAME = 'HISTORYLIST';
    if n_count_bak = 0 then
      EXECUTE IMMEDIATE 'alter table P#KPI_T_FZXZJJXZB_BAK add HISTORYLIST varchar2(30) default 0';
    end if;
  END IF;
  sys_p_recreate_views(v_tableid);
end;--GF_20170609_增加查看历史指标
