CREATE OR REPLACE VIEW KPI_V_DEPEXPTARGET AS
SELECT a.NAME || '{' || a.guid group_deptname,
       a.guid datakey,
       a.guid bpmnid,
       a.guid departmentid, --��λ����ID
       a.superid superId,
       a.code deptcode,
       b.code distcode,
       b.guid districtid, --����
       a.ISLEAF isagency
  FROM Code_t_Agency_Spf a --code_m_department a
 INNER JOIN code_t_district b
    ON a.districtid = b.guid
		WHERE a.ISDISTRICT  = '0'
   --AND a.iscz = '0'
 ORDER BY b.code, a.code
--WUTF_20161024�ؽ��������б���ͼ�ӵ�λ
