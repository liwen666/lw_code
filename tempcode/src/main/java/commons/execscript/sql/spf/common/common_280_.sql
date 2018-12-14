begin
  --ר��ݲ鿴����ӱ������
  EXECUTE IMMEDIATE Q'{
    CREATE OR REPLACE VIEW SPF_V_FBAKLOG AS
    SELECT (SELECT C.NAME FROM FASP_T_CAUSER C WHERE C.GUID = A.BAKUSERID) AS BAKUSERID,
           to_char(a.DBVERSION, 'YYYY-MM-DD HH24:MI') as dbversion,
           a.BAKVERSION,
           a.SPFID,
           A.DATAKEY,
           a.BAKTYPE
      FROM Spf_t_Fbaseinfo_Bak A
  }';
  
--��Ŀ���ݲ鿴����ӱ������ 
  EXECUTE IMMEDIATE Q'{
    CREATE OR REPLACE VIEW SPF_V_PBAKLOG AS
    SELECT (SELECT C.NAME FROM FASP_T_CAUSER C WHERE C.GUID = A.BAKUSERID) AS BAKUSERID,
           to_char(a.DBVERSION, 'YYYY-MM-DD HH24:MI') as dbversion,
           a.BAKVERSION,
           a.SPFID,
           a.PROJECTID,
           A.DATAKEY,
           a.BAKTYPE
      FROM Spf_t_Pbaseinfo_Bak A
  }';
end;
--ר���Ŀ������ͼ���Baktype
