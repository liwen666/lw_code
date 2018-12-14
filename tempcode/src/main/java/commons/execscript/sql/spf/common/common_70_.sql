create or replace procedure SPF_AND_INDEX_INTERFACE(p_districtid in varchar2,p_projectids in varchar2,p_batchid in varchar2) as
--��Ŀ���ָ��ӿڴ洢����
V_COUNT int;
V_AGENCYGUID VARCHAR2(64);
V_FINYEAR VARCHAR2(64);
V_EXPECOID VARCHAR2(64);
V_EXPFUNCGUID VARCHAR2(64);
V_AMT NUMBER(18,4);
V_SFJZ VARCHAR2(64);
V_ISWRITEOFF VARCHAR2(64);
begin
  -- һ�� ������Ŀ����ϸ��Ŀ�����ȡ��Ͷ�ʼƻ��� 
FOR C_TAB IN (
       select i.PROJECTID, 
             i.DATAKEY,
             i.EXPECOID,                      
             i.FINYEAR,
             i.ADDINDEXNUM AMOUNT,
             '1' dealtype,
             i.status,                    
             a1.PROJNAME,
             a1.PROJCODE,
             a1.AGENCYID,
             a1.ISINDEX,
             b1.DISTRICTID,
             i.EXPFUNCID,  
             b1.SPFID,
             b1.DEPTID,
             pt.PROJTYPE                  
        from P#Spf_t_Pinvestplan i,
             spf_t_pbaseinfo   a1,
             spf_t_fbaseinfo   b1,
             SPF_T_PROJECTTYPE pt
       where i.ISPLANVALID = '1'                  
         and i.PROJECTID = a1.PROJECTID
         and a1.SPFID = b1.SPFID
         and b1.PROJTYPEID = pt.PROJTYPEID 
         and i.ISADJUST='1'    
         and  p_projectids like '%'||a1.projectid||'%'
         and a1.ISINDEX = '1'
         and Exists (select 1
                    from code_t_agency_spf
                   where guid = a1.AGENCYID
                     and districtid = p_districtid
                     and isleaf = '1'
                     and divtype = '0'
                    and isdistrict = '0')                 
         )
LOOP

--�м���Ƿ�����Ͷ�ʼƻ����Ӧ������
SELECT COUNT(1) INTO V_COUNT FROM SPF_T_XMSCZBLYB WHERE  DATAKEY = C_TAB.DATAKEY AND PROGUID=C_TAB.PROJECTID AND ISWRITEOFF='0' ; 

--����м��������
IF V_COUNT>0 THEN
      --��ֵ�ؼ�Ԫ�� agencyid, finyear ,expecoid,expfuncid,amt 
      SELECT T1.AGENCYGUID,T1.FINYEAR,T1.EXPECOID,T1.EXPFUNCGUID,T1.AMT,T1.SFJZ,T1.ISWRITEOFF
      INTO V_AGENCYGUID,V_FINYEAR,V_EXPECOID,V_EXPFUNCGUID,V_AMT,V_SFJZ,V_ISWRITEOFF
      FROM SPF_T_XMSCZBLYB T1 
      WHERE DATAKEY = C_TAB.DATAKEY AND PROGUID=C_TAB.PROJECTID AND ISWRITEOFF='0' ;
      
      --�ж�Ͷ�ʼƻ��������Ƿ�ɾ��     
      IF(C_TAB.STATUS<>'1') THEN
          -- 1���ȴ���Ͷ�ʼƻ������Ѿ���ɾ������   
          --   (1)��δ���˷ǳ�� SFJZ = '0' AND  ISWRITEOFF='0'ֱ��ɾ��
          IF (V_SFJZ = '0' AND V_ISWRITEOFF='0') THEN
              DELETE FROM SPF_T_XMSCZBLYB T1 
              WHERE SFJZ = '0' AND ISWRITEOFF ='0' AND T1.DATAKEY = C_TAB.DATAKEY AND T1.PROGUID=C_TAB.PROJECTID;
          END IF;
          --   (2)��δ���˳�� SFJZ = '0' AND  ISWRITEOFF  ='1'������
          --   (3)���Ѽ��˳�� SFJZ = '1' AND  ISWRITEOFF ='1'������
          --   (4)���Ѽ��˳�� SFJZ = '1' AND  ISWRITEOFF ='0'����һ�ʸ���,״̬Ϊδ���� SFJZ = '0',ISWRITEOFF ='1'
          --        ���Ѿ����������Ƿ��ָ�Ϊ1
          IF (V_SFJZ = '1' AND V_ISWRITEOFF='0') THEN
              INSERT INTO  SPF_T_XMSCZBLYB (DATAKEY, ORDERID, NEEDUPDATE, FINYEAR, FININTORGGUID, AGENCYGUID,  LOCEXPSTRGUID, EXPFUNCGUID, LAIYWH, ZHAIY, DISTRICTID, GUID,     AMT, APROJCODE, PROCTYPE, SPFID, PROGUID, APROJECTNAME, PROJTYPE, SFJZ, EXPECOID, BATCHID, STATUS, DBVERSION, ISWRITEOFF)
              SELECT                       DATAKEY, ORDERID, NEEDUPDATE, FINYEAR, FININTORGGUID, AGENCYGUID,  LOCEXPSTRGUID, EXPFUNCGUID, LAIYWH, ZHAIY, DISTRICTID, SYS_GUID(),0- AMT, APROJCODE, PROCTYPE, SPFID, PROGUID, APROJECTNAME, PROJTYPE, '0', EXPECOID, BATCHID, STATUS, DBVERSION, '1'
              FROM SPF_T_XMSCZBLYB T1 
              WHERE SFJZ = '1' AND ISWRITEOFF ='0' AND T1.DATAKEY = C_TAB.DATAKEY AND T1.PROGUID=C_TAB.PROJECTID;     
          
              UPDATE SPF_T_XMSCZBLYB T1
              SET ISWRITEOFF = '1'
              WHERE SFJZ = '1' AND ISWRITEOFF ='0' AND T1.DATAKEY = C_TAB.DATAKEY AND T1.PROGUID=C_TAB.PROJECTID;    
          END IF;  
      ELSE    
          -- 2������Ͷ�ʼƻ������Ѵ��ڵ�����      
          --   (1)δ����Ϊ��� SFJZ = '0' AND  ISWRITEOFF ='0' ��ֱ�Ӹ��½�����������Ҫ�أ������Ĳ�����
          IF(V_SFJZ = '0' AND V_ISWRITEOFF='0') THEN
              UPDATE SPF_T_XMSCZBLYB T1      
              SET AGENCYGUID = C_TAB.AGENCYID,
                  FINYEAR = C_TAB.FINYEAR,
                  EXPECOID= C_TAB.EXPECOID,
                  EXPFUNCGUID=C_TAB.EXPFUNCID,
                  AMT = C_TAB.AMOUNT   
              WHERE SFJZ = '0' AND ISWRITEOFF = '0' AND T1.DATAKEY = C_TAB.DATAKEY AND T1.PROGUID=C_TAB.PROJECTID;
          END IF;
          --   (2)δ�����ѳ�� SFJZ = '0' AND ISWRITEOFF ='1' ������
          --   (3)�Ѽ����ѳ�� SFJZ = '1' AND ISWRITEOFF ='1' ������
          
          --   (4)�Ѽ���δ��� SFJZ= '1' AND ISWRITEOFF ='0' 
          --      ����Ҫ�ز��ȵ����ݸ���һ�н��Ϊ�������У�Ȼ���ٲ���һ�У� �Ƿ��� ISWRITEOFF ����Ϊ'1'��
          IF (V_SFJZ = '1' AND V_ISWRITEOFF='0') THEN
              IF (C_TAB.AGENCYID<>V_AGENCYGUID OR C_TAB.FINYEAR<>V_FINYEAR OR C_TAB.EXPECOID<>V_EXPECOID OR C_TAB.EXPFUNCID<>V_EXPFUNCGUID OR C_TAB.AMOUNT<>V_AMT ) THEN
                
                    INSERT INTO SPF_T_XMSCZBLYB (DATAKEY, ORDERID, NEEDUPDATE, FINYEAR, FININTORGGUID, AGENCYGUID, FUNDTYPEGUID, BGTSOURCEGUID, LOCEXPSTRGUID, EXPFUNCGUID, LAIYWH, ZHAIY, DISTRICTID, GUID,         AMT, APROJCODE, PROCTYPE, SPFID, PROGUID, APROJECTNAME, PROJTYPE, SFJZ, EXPECOID, BATCHID, STATUS, DBVERSION, ISWRITEOFF)
                    SELECT                       DATAKEY, ORDERID, NEEDUPDATE, FINYEAR, FININTORGGUID, AGENCYGUID, FUNDTYPEGUID, BGTSOURCEGUID, LOCEXPSTRGUID, EXPFUNCGUID, LAIYWH, ZHAIY, DISTRICTID, SYS_GUID(),0- AMT, APROJCODE, PROCTYPE, SPFID, PROGUID, APROJECTNAME, PROJTYPE,'0', EXPECOID, BATCHID,  STATUS, DBVERSION, '1'
                    FROM SPF_T_XMSCZBLYB T1 
                    WHERE SFJZ = '1' AND ISWRITEOFF = '0' AND T1.DATAKEY = C_TAB.DATAKEY AND T1.PROGUID=C_TAB.PROJECTID;


                    INSERT INTO SPF_T_XMSCZBLYB (DATAKEY,      FINYEAR,       FININTORGGUID, AGENCYGUID,     EXPFUNCGUID,        DISTRICTID,        GUID,      AMT,         APROJCODE,     PROCTYPE,           SPFID,          PROGUID,         APROJECTNAME, PROJTYPE,        SFJZ, EXPECOID,       BATCHID,  STATUS)
                    VALUES                      (C_TAB.DATAKEY,C_TAB.FINYEAR, C_TAB.DEPTID, C_TAB.AGENCYID,  C_TAB.EXPFUNCID,    C_TAB.DISTRICTID, sys_guid(),C_TAB.AMOUNT,C_TAB.PROJCODE,C_TAB.DEALTYPE,    C_TAB.SPFID,    C_TAB.PROJECTID, C_TAB.PROJNAME, C_TAB.PROJECTID, '0',  C_TAB.EXPECOID, P_BATCHID,'1');
     

                    UPDATE SPF_T_XMSCZBLYB T1 
                    SET ISWRITEOFF = '1'
                    WHERE SFJZ = '1' AND ISWRITEOFF = '0' AND T1.DATAKEY = C_TAB.DATAKEY AND T1.PROGUID=C_TAB.PROJECTID;

              END IF;
           END IF;
     END IF;
 ELSE
     --���SPF_T_XMSCZBLYB���������������
     IF (C_TAB.STATUS='1') THEN
          INSERT INTO SPF_T_XMSCZBLYB (DATAKEY,      FINYEAR,       FININTORGGUID, AGENCYGUID,     EXPFUNCGUID,        DISTRICTID,        GUID,      AMT,         APROJCODE,     PROCTYPE,           SPFID,          PROGUID,         APROJECTNAME, PROJTYPE,        SFJZ, EXPECOID,       BATCHID,  STATUS)
          VALUES                      (C_TAB.DATAKEY,C_TAB.FINYEAR, C_TAB.DEPTID, C_TAB.AGENCYID,  C_TAB.EXPFUNCID,    C_TAB.DISTRICTID, sys_guid(),C_TAB.AMOUNT,C_TAB.PROJCODE,C_TAB.DEALTYPE,    C_TAB.SPFID,    C_TAB.PROJECTID, C_TAB.PROJNAME, C_TAB.PROJECTID, '0',  C_TAB.EXPECOID, P_BATCHID,'1');
               
     END IF;
 END IF; 
    
END LOOP;
END SPF_AND_INDEX_INTERFACE;
--SPF_AND_INDEX_INTERFACE
