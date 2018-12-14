--ר�������������Ŀ����ר��ȷ��ʱ���ô洢���̰�ר����Ϣ���뵽��������ÿ��ר�����Ϣ������������Ÿ���һ����
CREATE OR REPLACE PROCEDURE P_CONTROL_SPFID(V_SPFID IN VARCHAR2, V_RESULT OUT VARCHAR2) IS
  V_DBTABLENAME             VARCHAR2(100);
  V_REALID                  VARCHAR2(32);
  V_SQL                     VARCHAR2(1000);
  V_FINANCE_ID              VARCHAR2(32);--����ID
  V_FINANCE_AGENCYID        VARCHAR2(32);--���ҵ�agencyID
  V_DEPT_ID                 VARCHAR2(32);--����ID
  V_AGENCY_ID               VARCHAR2(32);--ר��ĵ�λID
  V_NUM                     INTEGER;
BEGIN
  --�ж�A0*04�ı��Ƿ����
  select count(1) into V_NUM from dict_t_model where dealtype = 'A0*04';
  if V_NUM = 1 then
    select dbtablename into V_DBTABLENAME from dict_t_model where dealtype = 'A0*04';
  else
    V_RESULT := '��������ΪA0*04�ı��ظ��򲻴���';
    return;
  end if;

  if V_DBTABLENAME is null then
    V_RESULT := '������������ΪA0*04�ı����Ϊ��';
    return;
  end if;

  select count(1) into V_NUM from user_tab_columns where table_name = V_DBTABLENAME and column_name in( 'DATAKEY','AGENCYID','FINANCE_ID','DEPT_ID','SPFID','PROJECTID','REALID') ;
  if V_NUM <> 7 then
    V_RESULT := '����������ȱ��ĳ��Ĭ����DATAKEY AGENCYID FINANCE_ID DEPT_ID SPFID PROJECTID REALID����';
    return;
  end if;

  --��ѯר���firagencyid��deptid
  select count(1) into V_NUM from spf_t_fbaseinfo where spfid = V_SPFID;
  if V_NUM = 1 then
    select firagencyid, deptid into V_DEPT_ID, V_FINANCE_ID from spf_t_fbaseinfo where spfid = V_SPFID;
  else
    V_RESULT := 'spf_t_fbaseinfo���ظ��򲻴��ڴ�spfid��' || V_SPFID;
    return;
  end if;

  if V_DEPT_ID is null then
    V_RESULT := 'spf_t_fbaseinfo�д���Ŀ��ר���firagencyidΪ�գ�' || V_SPFID;
    return;
  end if;

  if V_FINANCE_ID is null then
    V_RESULT := 'spf_t_fbaseinfo�д���Ŀ��ר���deptidΪ�գ�' || V_SPFID;
    return;
  end if;

  --��ѯ���ҵ�agencyid
  select count(1) into V_NUM from CODE_T_FIRAGENCY where guid = V_DEPT_ID;
  if V_NUM = 1 then
    select superguid into V_FINANCE_AGENCYID from CODE_T_FIRAGENCY where guid = V_DEPT_ID;
  else
    V_RESULT := 'CODE_T_FIRAGENCY���ظ��򲻴��ڴ�guid��' || V_SPFID;
    return;
  end if;
  if V_FINANCE_AGENCYID is null then
    V_RESULT := 'CODE_T_FIRAGENCY�д˲��ŵ�superguidΪ�գ�' || V_SPFID;
    return;
  end if;

  V_SQL := 'delete from  ' || V_DBTABLENAME ||
           ' where agencyid = :1 and finance_id = :2 and dept_id = :3 and spfid = :4 ';
  --ɾ������
  EXECUTE IMMEDIATE V_SQL using V_FINANCE_AGENCYID,V_FINANCE_ID,V_DEPT_ID,V_SPFID;
  --ɾ������
  EXECUTE IMMEDIATE V_SQL using V_DEPT_ID,         V_FINANCE_ID,V_DEPT_ID,V_SPFID;

  --����REALID
  select sys_guid() into V_REALID from dual;

  V_SQL := 'insert into  ' || V_DBTABLENAME ||
           ' (datakey, agencyid, finance_id, dept_id, spfid, realid) values ( sys_guid(), :1,:2,:3,:4,:5 )';
  --���봦��
  EXECUTE IMMEDIATE V_SQL
    using V_FINANCE_AGENCYID, V_FINANCE_ID, V_DEPT_ID, V_SPFID, V_REALID;
  --���벿��
  EXECUTE IMMEDIATE V_SQL
    using V_DEPT_ID,          V_FINANCE_ID, V_DEPT_ID, V_SPFID, V_REALID;

END P_CONTROL_SPFID;
--12���洢���̣�ר�������(�޸ĺ�)
