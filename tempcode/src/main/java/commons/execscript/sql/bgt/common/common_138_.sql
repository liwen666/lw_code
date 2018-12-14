DECLARE
  v_count integer;
  v_tableid VARCHAR2(32); 
	v_tablename VARCHAR2(1000); 
	v_sql VARCHAR2(2000);
	CURSOR corsor_biltable  IS select tableid ,dbtablename FROM dict_t_model WHERE dealtype = 'BIL01';
BEGIN
	UPDATE dict_t_defaultcol SET DATALENGTH = 32 , DATATYPE = 3 WHERE GUID ='25BBAC45ED9DC624E050A8C02105645A';
	OPEN corsor_biltable;  
	LOOP
	fetch  corsor_biltable into v_tableid, v_tablename; 
	Exit when corsor_biltable%NOTFOUND; 
	    SELECT COUNT(1) INTO v_count FROM User_Views WHERE VIEW_NAME = v_tablename;
			IF v_count > 0 THEN
				v_sql := 'alter table P#'||v_tablename||' MODIFY USERID varchar2(32)';
				EXECUTE IMMEDIATE v_sql;
				UPDATE Dict_t_Factor SET DATALENGTH = 32,DATATYPE = 3 WHERE  TABLEID = v_tableid;
				sys_p_recreate_views(v_tableid);
			END IF;
	END LOOP;
	CLOSE corsor_biltable ;  
END;  
--修改所有单据主表的制单人字段160630
