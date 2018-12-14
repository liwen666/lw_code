--专项控制数，由项目库在专项确认时调用存储过程把专项信息插入到控制数表，每条专项的信息需给财政、部门各插一条。
CREATE OR REPLACE PROCEDURE P_CONTROL_SPFID(V_SPFID IN VARCHAR2, V_RESULT OUT VARCHAR2) IS
  V_DBTABLENAME             VARCHAR2(100);
  V_REALID                  VARCHAR2(32);
  V_SQL                     VARCHAR2(1000);
  V_FINANCE_ID              VARCHAR2(32);--处室ID
  V_FINANCE_AGENCYID        VARCHAR2(32);--处室的agencyID
  V_DEPT_ID                 VARCHAR2(32);--部门ID
  V_AGENCY_ID               VARCHAR2(32);--专项的单位ID
  V_NUM                     INTEGER;
BEGIN
  --判断A0*04的表是否存在
  select count(1) into V_NUM from dict_t_model where dealtype = 'A0*04';
  if V_NUM = 1 then
    select dbtablename into V_DBTABLENAME from dict_t_model where dealtype = 'A0*04';
  else
    V_RESULT := '处理类型为A0*04的表重复或不存在';
    return;
  end if;

  if V_DBTABLENAME is null then
    V_RESULT := '处理类型类型为A0*04的表表名为空';
    return;
  end if;

  select count(1) into V_NUM from user_tab_columns where table_name = V_DBTABLENAME and column_name in( 'DATAKEY','AGENCYID','FINANCE_ID','DEPT_ID','SPFID','PROJECTID','REALID') ;
  if V_NUM <> 7 then
    V_RESULT := '控制数表中缺少某个默认列DATAKEY AGENCYID FINANCE_ID DEPT_ID SPFID PROJECTID REALID请检查';
    return;
  end if;

  --查询专项的firagencyid，deptid
  select count(1) into V_NUM from spf_t_fbaseinfo where spfid = V_SPFID;
  if V_NUM = 1 then
    select firagencyid, deptid into V_DEPT_ID, V_FINANCE_ID from spf_t_fbaseinfo where spfid = V_SPFID;
  else
    V_RESULT := 'spf_t_fbaseinfo中重复或不存在此spfid：' || V_SPFID;
    return;
  end if;

  if V_DEPT_ID is null then
    V_RESULT := 'spf_t_fbaseinfo中此项目的专项的firagencyid为空：' || V_SPFID;
    return;
  end if;

  if V_FINANCE_ID is null then
    V_RESULT := 'spf_t_fbaseinfo中此项目的专项的deptid为空：' || V_SPFID;
    return;
  end if;

  --查询处室的agencyid
  select count(1) into V_NUM from CODE_T_FIRAGENCY where guid = V_DEPT_ID;
  if V_NUM = 1 then
    select superguid into V_FINANCE_AGENCYID from CODE_T_FIRAGENCY where guid = V_DEPT_ID;
  else
    V_RESULT := 'CODE_T_FIRAGENCY中重复或不存在此guid：' || V_SPFID;
    return;
  end if;
  if V_FINANCE_AGENCYID is null then
    V_RESULT := 'CODE_T_FIRAGENCY中此部门的superguid为空：' || V_SPFID;
    return;
  end if;

  V_SQL := 'delete from  ' || V_DBTABLENAME ||
           ' where agencyid = :1 and finance_id = :2 and dept_id = :3 and spfid = :4 ';
  --删除处室
  EXECUTE IMMEDIATE V_SQL using V_FINANCE_AGENCYID,V_FINANCE_ID,V_DEPT_ID,V_SPFID;
  --删除部门
  EXECUTE IMMEDIATE V_SQL using V_DEPT_ID,         V_FINANCE_ID,V_DEPT_ID,V_SPFID;

  --生成REALID
  select sys_guid() into V_REALID from dual;

  V_SQL := 'insert into  ' || V_DBTABLENAME ||
           ' (datakey, agencyid, finance_id, dept_id, spfid, realid) values ( sys_guid(), :1,:2,:3,:4,:5 )';
  --插入处室
  EXECUTE IMMEDIATE V_SQL
    using V_FINANCE_AGENCYID, V_FINANCE_ID, V_DEPT_ID, V_SPFID, V_REALID;
  --插入部门
  EXECUTE IMMEDIATE V_SQL
    using V_DEPT_ID,          V_FINANCE_ID, V_DEPT_ID, V_SPFID, V_REALID;

END P_CONTROL_SPFID;
--12、存储过程，专项控制数(修改后)
