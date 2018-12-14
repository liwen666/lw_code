create or replace function FUN_KPI_INDI(V_FUNC_SQL VARCHAR2, V_KPITYPE VARCHAR2,V_DATAID VARCHAR2,V_SCORE VARCHAR2)
return VARCHAR2
IS
  v_replace_str VARCHAR2(100);
  v_sql VARCHAR2(4000);
  v_result VARCHAR2(100);
BEGIN
  v_sql := V_FUNC_SQL;
	--dbms_output.put_line(v_sql);
	v_result := V_SCORE;
	--û���Զ��庯����ֱ��ȡ��ѯ�������ָ��ֵ��SCORE
	IF v_sql IS NULL THEN
		RETURN v_result;
	END IF;

  IF V_KPITYPE = '1' THEN                 --��Ŀ��Ч
    v_replace_str := '$PROJECTID$';
  ELSIF V_KPITYPE = '2' THEN              --ר�Ч
    v_replace_str := '$SPFID$';
  ELSIF V_KPITYPE = '3' THEN              --���ż�Ч
    v_replace_str := '$DEPARTMENTID$';
    select REPLACE( v_sql, v_replace_str, ''''||V_DATAID||'''') INTO v_sql  from dual;
    v_replace_str := '$AGENCYID$';
  ELSIF V_KPITYPE = '4' THEN              --������Ч
    v_replace_str := '$DISTRICTID$';
  END IF;
  select REPLACE( v_sql, v_replace_str , ''''||V_DATAID||'''') INTO v_sql  from dual;
  --dbms_output.put_line(v_sql);

  execute immediate 'select '||  v_sql || ' from dual ' INTO v_result;

  RETURN v_result;
END;
--KPI_WJP_1024_��Чָ�꺯��
