create or replace procedure SPF_AND_INDEX_INTERFACE(p_districtid in varchar2,p_projectids in varchar2,p_batchid in varchar2) as
--项目库和指标接口存储过程
V_COUNT int;
V_AGENCYGUID VARCHAR2(64);
V_FINYEAR VARCHAR2(64);
V_EXPECOID VARCHAR2(64);
V_EXPFUNCGUID VARCHAR2(64);
V_AMT NUMBER(18,4);
V_SFJZ VARCHAR2(64);
V_ISWRITEOFF VARCHAR2(64);
begin
  -- 一、 类型项目、明细项目，金额取自投资计划表 
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

--中间表是否有与投资计划表对应的数据
SELECT COUNT(1) INTO V_COUNT FROM SPF_T_XMSCZBLYB WHERE  DATAKEY = C_TAB.DATAKEY AND PROGUID=C_TAB.PROJECTID AND ISWRITEOFF='0' ; 

--如果中间表有数据
IF V_COUNT>0 THEN
      --赋值关键元素 agencyid, finyear ,expecoid,expfuncid,amt 
      SELECT T1.AGENCYGUID,T1.FINYEAR,T1.EXPECOID,T1.EXPFUNCGUID,T1.AMT,T1.SFJZ,T1.ISWRITEOFF
      INTO V_AGENCYGUID,V_FINYEAR,V_EXPECOID,V_EXPFUNCGUID,V_AMT,V_SFJZ,V_ISWRITEOFF
      FROM SPF_T_XMSCZBLYB T1 
      WHERE DATAKEY = C_TAB.DATAKEY AND PROGUID=C_TAB.PROJECTID AND ISWRITEOFF='0' ;
      
      --判断投资计划表数据是否被删除     
      IF(C_TAB.STATUS<>'1') THEN
          -- 1、先处理投资计划表中已经被删除数据   
          --   (1)、未记账非冲抵 SFJZ = '0' AND  ISWRITEOFF='0'直接删除
          IF (V_SFJZ = '0' AND V_ISWRITEOFF='0') THEN
              DELETE FROM SPF_T_XMSCZBLYB T1 
              WHERE SFJZ = '0' AND ISWRITEOFF ='0' AND T1.DATAKEY = C_TAB.DATAKEY AND T1.PROGUID=C_TAB.PROJECTID;
          END IF;
          --   (2)、未记账冲抵 SFJZ = '0' AND  ISWRITEOFF  ='1'不处理
          --   (3)、已记账冲抵 SFJZ = '1' AND  ISWRITEOFF ='1'不处理
          --   (4)、已记账冲抵 SFJZ = '1' AND  ISWRITEOFF ='0'生成一笔负的,状态为未记账 SFJZ = '0',ISWRITEOFF ='1'
          --        把已经记账数据是否冲抵改为1
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
          -- 2、处理投资计划表中已存在的数据      
          --   (1)未记账为冲抵 SFJZ = '0' AND  ISWRITEOFF ='0' 的直接更新金额和其他核算要素，其他的不处理
          IF(V_SFJZ = '0' AND V_ISWRITEOFF='0') THEN
              UPDATE SPF_T_XMSCZBLYB T1      
              SET AGENCYGUID = C_TAB.AGENCYID,
                  FINYEAR = C_TAB.FINYEAR,
                  EXPECOID= C_TAB.EXPECOID,
                  EXPFUNCGUID=C_TAB.EXPFUNCID,
                  AMT = C_TAB.AMOUNT   
              WHERE SFJZ = '0' AND ISWRITEOFF = '0' AND T1.DATAKEY = C_TAB.DATAKEY AND T1.PROGUID=C_TAB.PROJECTID;
          END IF;
          --   (2)未记账已冲抵 SFJZ = '0' AND ISWRITEOFF ='1' 不处理
          --   (3)已记账已冲抵 SFJZ = '1' AND ISWRITEOFF ='1' 不处理
          
          --   (4)已记账未冲抵 SFJZ= '1' AND ISWRITEOFF ='0' 
          --      核算要素不等的数据复制一行金额为负数的行，然后再插入一行， 是否冲抵 ISWRITEOFF 都变为'1'，
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
     --如果SPF_T_XMSCZBLYB不存在则插入数据
     IF (C_TAB.STATUS='1') THEN
          INSERT INTO SPF_T_XMSCZBLYB (DATAKEY,      FINYEAR,       FININTORGGUID, AGENCYGUID,     EXPFUNCGUID,        DISTRICTID,        GUID,      AMT,         APROJCODE,     PROCTYPE,           SPFID,          PROGUID,         APROJECTNAME, PROJTYPE,        SFJZ, EXPECOID,       BATCHID,  STATUS)
          VALUES                      (C_TAB.DATAKEY,C_TAB.FINYEAR, C_TAB.DEPTID, C_TAB.AGENCYID,  C_TAB.EXPFUNCID,    C_TAB.DISTRICTID, sys_guid(),C_TAB.AMOUNT,C_TAB.PROJCODE,C_TAB.DEALTYPE,    C_TAB.SPFID,    C_TAB.PROJECTID, C_TAB.PROJNAME, C_TAB.PROJECTID, '0',  C_TAB.EXPECOID, P_BATCHID,'1');
               
     END IF;
 END IF; 
    
END LOOP;
END SPF_AND_INDEX_INTERFACE;
--SPF_AND_INDEX_INTERFACE
