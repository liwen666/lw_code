CREATE OR REPLACE PROCEDURE IND_SP_ZYAuditSumProc(v_TaskID varchar2,v_DestModelID varchar2,v_AgencyID varchar2)
AS
  v_RelaTableName varchar2(32);
  v_UserManaDistrict varchar2(32);
  v_AuditTypeID varchar2(32);
  v_ModelID varchar2(32);
--  v_TaskBudgetLevel varchar2(10);
  v_SQL varchar2(30000);
  v_sql2 varchar2(500);

--  v_DataStartDate varchar2(20);
--  v_DataEndDate varchar2(20);

BEGIN
  SELECT DBTableName INTO v_RelaTableName FROM Dict_t_Model WHERE TableID = v_DestModelID;
  --SELECT DistrictID INTO v_UserManaDistrict FROM Tra_v_UserManaDistrict WHERE UserID = v_UserID;
  --SELECT AGENCY INTO v_UserManaDistrict FROM FASP_T_CAUSER WHERE GUID = v_UserID;
  v_UserManaDistrict := v_AgencyID;
  --SELECT AuditTypeID INTO v_AuditTypeID FROM Ind_t_Audittype WHERE AuditTypeName = '财政部专用电子对帐';
/*  SELECT '' TaskBudgetLevel,CreateDate,EndDate
    INTO v_TaskBudgetLevel,v_DataStartDate,v_DataEndDate
    FROM CDT_T_TASKINFO where TaskID = v_TaskID;*/

/*  SELECT '' TaskBudgetLevel,DataStartDate,DataEndDate
    INTO v_TaskBudgetLevel,v_DataStartDate,v_DataEndDate
    FROM CDT_T_TASKINFO where TaskID = v_TaskID;

/*

上级下达指标汇总表 IND_T_ZBDJHZB
本级电子对账指标汇总表 IND_T_DZDJHZB

*/


  --刷新汇总表数据
/*  FOR c1 IN (SELECT ModelID FROM Ind_t_AuditSumDef WHERE AuditTypeID = v_AuditTypeID) LOOP
     v_ModelID := c1.ModelID;
     IND_SP_AuditSumProc(v_TaskID,v_AuditTypeID,v_ModelID,v_UserManaDistrict);
  END LOOP;*/

--直接执行汇总语句
  v_SQL := 'DELETE FROM B3_BZXJZYZFQKBFZ  WHERE agencyid = '''||v_UserManaDistrict||'''';
  EXECUTE IMMEDIATE v_SQL;
  v_SQL := 'INSERT INTO B3_BZXJZYZFQKBFZ
  (DataKey,
   agencyid,
   EXPFUNCGUID,
   FUNDTYPEGUID,
   ZY,
   BZ,
   XDWH,
   SFJSBZ,
   BMLX,
   XDLX,
   AMT,
   SENDDOCTIME,
   FININTORGGUID,
   INPUTDISTRICT)
  SELECT RAWTOHEX(SYS_GUID()) AS DataKey,
         AgencyID,
         Z_THSZCGNKM,
         ConnStrA(DISTINCT (SELECT x.Name || '' ''
                     FROM (SELECT ID, NAME FROM FASP_V_PUBFUNDTYPE) x
                    WHERE x.ID = FUNDTYPEGUID) || ''''),
         ConnStrA(DISTINCT ZY || ''''),
         ConnStrA(DISTINCT BZ || ''''),
         XDWH,
         ConnStrA(DISTINCT (SELECT x.Name || '' ''
                     FROM (SELECT ID, NAME FROM FASP_V_PUBPUBBOOL) x
                    WHERE x.ID = SFJSBZ) || ''''),
         ConnStrA(DISTINCT (SELECT x.Name || '' ''
                     FROM (SELECT ID, NAME FROM FASP_V_PUBSUPDEP) x
                    WHERE x.ID = BMLX) || ''''),
         ConnStrA(DISTINCT (SELECT x.Name || '' ''
                     FROM (SELECT ID, NAME FROM CODE_T_PUBXDLX) x
                    WHERE x.ID = XDLX) || ''''),
         Sum(AMT),
         ConnStrA(DISTINCT SENDDOCTIME || ''''),
         ConnStrA(DISTINCT (SELECT x.Name || '' ''
                     FROM (SELECT ID, NAME FROM FASP_V_PUBDEPARTMENT) x
                    WHERE x.ID = FININTORGGUID) || ''''),
         INPUTDISTRICT
    FROM (SELECT aa.*,
                 CASE
                   WHEN EXISTS
                    (SELECT 1
                           FROM FASP_V_PUBEXPFUNC x
                          WHERE rownum < 2
                            AND SubStr(x.ShowID, 1, 5) IN (''23003'', ''23004'')
                            AND aa.TRANPAYFUNC = x.ID) THEN
                    aa.EXPFUNCGUID
                   ELSE
                    aa.TRANPAYFUNC
                 END as Z_THSZCGNKM
            FROM BAS_T_ZYZYZFZBB aa) a
   WHERE agencyid = '''||v_UserManaDistrict||''''||'
   GROUP BY INPUTDISTRICT, XDWH, AgencyID, Z_THSZCGNKM';
   EXECUTE IMMEDIATE v_SQL;

/*  v_sql2 := 'delete from a where b= ''B3_BZXJZYZFQKBFZ''';
  EXECUTE IMMEDIATE v_SQL2;
  insert into a(a,b) values (v_SQL,'B3_BZXJZYZFQKBFZ');*/

  v_SQL := 'DELETE FROM B4_BJDJZBQKBFZ  WHERE agencyid = '''||v_UserManaDistrict||'''';
  EXECUTE IMMEDIATE v_SQL;

  v_SQL := 'INSERT INTO B4_BJDJZBQKBFZ
  (DataKey, agencyid, EXPFUNCGUID, AMT, XDWH, BZ, SJAGENCYID)
  SELECT RAWTOHEX(SYS_GUID()) AS DataKey,
         AgencyID,
         EXPFUNCGUID,
         Sum(AMT),
         XDWH,
         ConnStrA(DISTINCT BZ || ''''),
         SJAGENCYID
    FROM BAS_T_BJDJZBQKB a
   WHERE agencyid = '''||v_UserManaDistrict||''''||'
   GROUP BY XDWH, SJAGENCYID, AgencyID, EXPFUNCGUID';
  EXECUTE IMMEDIATE v_SQL;

/*  v_sql2 := 'delete from a where b= ''B4_BJDJZBQKBFZ''';
  EXECUTE IMMEDIATE v_SQL2;
  insert into a(a,b) values (v_SQL,'B4_BJDJZBQKBFZ' );*/

  --删除对账结果表中数据
  v_SQL := 'DELETE FROM '||v_RelaTableName||' WHERE TaskID = '''||v_TaskID||'''' || ' and agencyid = '''||v_UserManaDistrict||'''';
  EXECUTE IMMEDIATE v_SQL;

/*
year, province, dbversion, status, taskid, datakey, orderid, needupdate, finyear, agencyid,
xdwh, senddoctime, finintorgguid, ysxm, tranpayfunc, expfuncguid, fundtypeguid, sfjsbz, amt, bdwjewy, cewy, dzxx, bz, expstrname, sjagencyid

DATAKEY,ORDERID,FINYEAR,AGENCYID,XDWH,SENDDOCTIME,FININTORGGUID,YSXM,TRANPAYFUNC,EXPFUNCGUID,EXPSTRNAME,FUNDTYPEGUID,SFJSBZ,AMT,BDWJEWY,CEWY,DZXX,SJAGENCYID,BZ

*/

  v_SQL :='
INSERT INTO '||v_RelaTableName||' (
            DATAKEY,ORDERID,FINYEAR,AGENCYID,XDWH,SENDDOCTIME,FININTORGGUID,YSXM,EXPFUNCGUID,FUNDTYPEGUID,SFJSBZ,AMT,BDWJEWY,CEWY,DZXX,SJAGENCYID,BZ
            )
SELECT RAWTOHEX(SYS_GUID()) AS DataKey,
rownum as orderid,
a.finyear,
a.agencyid,
a.xdwh,
a.senddoctime,
a.FININTORGGUID,
a.YSXM,
a.EXPFUNCGUID,
a.FUNDTYPEGUID,
a.SFJSBZ,
a.AMT,
0 as BDWJEWY,
a.AMT as CEWY,
''001'' AS DZXX,
a.inputdistrict,
a.bz
FROM B3_BZXJZYZFQKBFZ a
WHERE NOT EXISTS(SELECT 1 FROM B4_BJDJZBQKBFZ b WHERE TRA_F_CNLOWCASE(a.xdwh) = TRA_F_CNLOWCASE(b.xdwh) and a.agencyid = b.agencyid  and a.inputdistrict = b.sjagencyid)
  AND a.AgencyID = '''||v_UserManaDistrict||'''

UNION ALL

SELECT RAWTOHEX(SYS_GUID()) AS DataKey,
rownum as orderid,
b.finyear,
b.agencyid,
b.xdwh,
'''' as senddoctiem,
'''' as FININTORGGUID,
'''' as YSXM,
b.EXPFUNCGUID,
'''' as FUNDTYPEGUID,
'''' as SFJSBZ,
0 as AMT,
b.amt  as BDWJEWY,
0-b.amt  as CEWY,
''002'' AS DZXX,
b.sjagencyid as sjagencyid,
'''' as bz
FROM B4_BJDJZBQKBFZ b
WHERE NOT EXISTS(SELECT 1 FROM B3_BZXJZYZFQKBFZ a WHERE TRA_F_CNLOWCASE(a.xdwh) = TRA_F_CNLOWCASE(b.xdwh)  and a.agencyid = b.agencyid and a.inputdistrict = b.sjagencyid)
  AND b.AgencyID = '''||v_UserManaDistrict||'''

UNION  ALL

SELECT RAWTOHEX(SYS_GUID()) AS DataKey,
rownum as orderid,
a.finyear,
a.agencyid,
a.xdwh,
a.senddoctime,
a.FININTORGGUID,
a.YSXM,
a.EXPFUNCGUID,
a.FUNDTYPEGUID,
a.SFJSBZ,
a.AMT,
0 as BDWJEWY,
a.AMT as CEWY,
''003'' AS DZXX,
a.inputdistrict,
a.bz
FROM B3_BZXJZYZFQKBFZ a,FASP_V_PUBEXPFUNC aa
WHERE EXISTS(SELECT 1 FROM B4_BJDJZBQKBFZ b WHERE TRA_F_CNLOWCASE(a.xdwh) = TRA_F_CNLOWCASE(b.xdwh) and a.agencyid = b.agencyid  and a.inputdistrict = b.sjagencyid)
  AND a.EXPFUNCGUID = aa.ID
  AND NOT EXISTS(SELECT 1 FROM B4_BJDJZBQKBFZ b,FASP_V_PUBEXPFUNC ba WHERE ba.ID = b.EXPFUNCGUID AND TRA_F_CNLOWCASE(a.xdwh) = TRA_F_CNLOWCASE(b.xdwh) and a.agencyid = b.agencyid  and a.inputdistrict = b.sjagencyid AND ba.LvlID LIKE aa.LvlID || ''%'')
  AND a.AgencyID = '''||v_UserManaDistrict||'''

UNION ALL

SELECT RAWTOHEX(SYS_GUID()) AS DataKey,
rownum as orderid,
b.finyear,
b.agencyid,
b.xdwh,
'''' as senddoctiem,
'''' as FININTORGGUID,
'''' as YSXM,
b.EXPFUNCGUID,
'''' as FUNDTYPEGUID,
'''' as SFJSBZ,
0 as AMT,
b.amt  as BDWJEWY,
0-b.amt  as CEWY,
''004'' AS DZXX,
b.sjagencyid as sjagencyid,
'''' as bz
FROM B4_BJDJZBQKBFZ b,FASP_V_PUBEXPFUNC ba
WHERE EXISTS(SELECT 1 FROM B3_BZXJZYZFQKBFZ a WHERE TRA_F_CNLOWCASE(a.xdwh) = TRA_F_CNLOWCASE(b.xdwh)  and a.agencyid = b.agencyid and a.inputdistrict = b.sjagencyid)
  AND ba.ID = b.EXPFUNCGUID
  AND NOT EXISTS(SELECT 1 FROM B3_BZXJZYZFQKBFZ a,FASP_V_PUBEXPFUNC aa WHERE aa.ID = a.EXPFUNCGUID AND TRA_F_CNLOWCASE(a.xdwh) = TRA_F_CNLOWCASE(b.xdwh)  and a.agencyid = b.agencyid and a.inputdistrict = b.sjagencyid AND ba.LvlID LIKE aa.LvlID || ''%'')
  AND b.AgencyID = '''||v_UserManaDistrict||'''

UNION  ALL

SELECT RAWTOHEX(SYS_GUID()) AS DataKey,
rownum as orderid,
a.finyear,
a.agencyid,
a.xdwh,
a.senddoctime,
a.FININTORGGUID,
a.YSXM,
a.EXPFUNCGUID,
a.FUNDTYPEGUID,
a.SFJSBZ,
a.amt,
Nvl((SELECT Sum(Nvl(b.amt,0)) FROM B4_BJDJZBQKBFZ b,FASP_V_PUBEXPFUNC ba WHERE b.AgencyID = '''||v_UserManaDistrict||''' AND b.EXPFUNCGUID = ba.ID AND TRA_F_CNLOWCASE(a.xdwh) = TRA_F_CNLOWCASE(b.xdwh)  and a.agencyid = b.agencyid and a.inputdistrict = b.sjagencyid AND ba.LvlID LIKE aa.LvlID || ''%''),0),
a.amt - Nvl((SELECT Sum(Nvl(b.amt,0)) FROM B4_BJDJZBQKBFZ b,FASP_V_PUBEXPFUNC ba WHERE b.AgencyID = '''||v_UserManaDistrict||''' AND b.EXPFUNCGUID = ba.ID AND TRA_F_CNLOWCASE(a.xdwh) = TRA_F_CNLOWCASE(b.xdwh)  and a.agencyid = b.agencyid and a.inputdistrict = b.sjagencyid AND ba.LvlID LIKE aa.LvlID || ''%''),0),
''005'' AS DZXX,
a.inputdistrict,
a.bz
FROM B3_BZXJZYZFQKBFZ a,FASP_V_PUBEXPFUNC aa
WHERE a.EXPFUNCGUID = aa.ID
  AND a.amt <> Nvl((
         SELECT Sum(Nvl(b.amt,0))
           FROM B4_BJDJZBQKBFZ b,FASP_V_PUBEXPFUNC ba
          WHERE b.AgencyID = '''||v_UserManaDistrict||'''
            AND b.EXPFUNCGUID = ba.ID
            AND TRA_F_CNLOWCASE(a.xdwh) = TRA_F_CNLOWCASE(b.xdwh)  and a.agencyid = b.agencyid and a.inputdistrict = b.sjagencyid
            AND ba.LvlID LIKE aa.LvlID || ''%''
            AND NOT EXISTS(
SELECT 1
FROM B3_BZXJZYZFQKBFZ xa,FASP_V_PUBEXPFUNC xaa
WHERE xa.AgencyID = b.AgencyID
  AND xa.EXPFUNCGUID = b.EXPFUNCGUID
  AND TRA_F_CNLOWCASE(xa.xdwh) = TRA_F_CNLOWCASE(b.xdwh) and xa.inputdistrict = b.sjagencyid
  AND xa.EXPFUNCGUID = xaa.ID
  AND xa.amt = Nvl((SELECT Sum(Nvl(xb.amt,0)) FROM B4_BJDJZBQKBFZ xb,FASP_V_PUBEXPFUNC xba WHERE xb.AgencyID = '''||v_UserManaDistrict||''' AND xb.EXPFUNCGUID = xba.ID AND TRA_F_CNLOWCASE(xa.xdwh) = TRA_F_CNLOWCASE(xb.xdwh)  and xa.agencyid = xb.agencyid and xa.inputdistrict = xb.sjagencyid AND xba.LvlID LIKE xaa.LvlID || ''%''),0)
  AND EXISTS(SELECT 1 FROM B4_BJDJZBQKBFZ xb2,FASP_V_PUBEXPFUNC xb2a WHERE xb2.AgencyID = '''||v_UserManaDistrict||''' AND TRA_F_CNLOWCASE(xb2.xdwh) = TRA_F_CNLOWCASE(xa.xdwh)  and xa.agencyid = xb2.agencyid and xa.inputdistrict = xb2.sjagencyid AND xb2.EXPFUNCGUID = xb2a.ID AND xb2a.LvlID LIKE xaa.LvlID || ''%'')
  AND xa.AgencyID = '''||v_UserManaDistrict||'''

)
       ),0)
  AND EXISTS(SELECT 1 FROM B4_BJDJZBQKBFZ b2,FASP_V_PUBEXPFUNC b2a WHERE b2.AgencyID = '''||v_UserManaDistrict||''' AND TRA_F_CNLOWCASE(b2.xdwh) = TRA_F_CNLOWCASE(a.xdwh)  and a.agencyid = b2.agencyid and a.inputdistrict = b2.sjagencyid AND b2.EXPFUNCGUID = b2a.ID AND b2a.LvlID LIKE aa.LvlID || ''%'')
  AND a.AgencyID = '''||v_UserManaDistrict||'''
  AND NOT EXISTS(
SELECT 1
FROM B3_BZXJZYZFQKBFZ xa,FASP_V_PUBEXPFUNC xaa
WHERE xa.AgencyID = a.AgencyID
  AND xa.EXPFUNCGUID = a.EXPFUNCGUID
  AND TRA_F_CNLOWCASE(xa.xdwh) = TRA_F_CNLOWCASE(a.xdwh) and xa.inputdistrict = a.inputdistrict
  AND xa.EXPFUNCGUID = xaa.ID
  AND xa.amt = Nvl((SELECT Sum(Nvl(xb.amt,0)) FROM B4_BJDJZBQKBFZ xb,FASP_V_PUBEXPFUNC xba WHERE xb.AgencyID = '''||v_UserManaDistrict||''' AND xb.EXPFUNCGUID = xba.ID AND TRA_F_CNLOWCASE(xa.xdwh) = TRA_F_CNLOWCASE(xb.xdwh)  and xa.agencyid = xb.agencyid and xa.inputdistrict = xb.sjagencyid  AND xba.LvlID LIKE xaa.LvlID || ''%''),0)
  AND EXISTS(SELECT 1 FROM B4_BJDJZBQKBFZ xb2,FASP_V_PUBEXPFUNC xb2a WHERE xb2.AgencyID = '''||v_UserManaDistrict||''' AND TRA_F_CNLOWCASE(xb2.xdwh) = TRA_F_CNLOWCASE(xa.xdwh)  and xa.agencyid = xb2.agencyid and xa.inputdistrict = xb2.sjagencyid AND xb2.EXPFUNCGUID = xb2a.ID AND xb2a.LvlID LIKE xaa.LvlID || ''%'')
  AND xa.AgencyID = '''||v_UserManaDistrict||'''

)

UNION ALL

SELECT RAWTOHEX(SYS_GUID()) AS DataKey,
rownum as orderid,
a.finyear,
a.agencyid,
a.xdwh,
a.senddoctime,
a.FININTORGGUID,
a.YSXM,
a.EXPFUNCGUID,
a.FUNDTYPEGUID,
a.SFJSBZ,
a.AMT,
a.AMT as BDWJEWY,
0 as CEWY,
''009'' AS DZXX,
a.inputdistrict,
a.bz
FROM B3_BZXJZYZFQKBFZ a,FASP_V_PUBEXPFUNC aa
WHERE a.EXPFUNCGUID = aa.ID
  AND a.amt = Nvl((
SELECT Sum(Nvl(b.amt,0))
  FROM B4_BJDJZBQKBFZ b,FASP_V_PUBEXPFUNC ba
 WHERE b.AgencyID = '''||v_UserManaDistrict||'''
   AND b.EXPFUNCGUID = ba.ID
   AND TRA_F_CNLOWCASE(a.xdwh) = TRA_F_CNLOWCASE(b.xdwh)  and a.agencyid = b.agencyid and a.inputdistrict = b.sjagencyid
   AND ba.LvlID LIKE aa.LvlID || ''%''

  AND NOT EXISTS(
SELECT 1
FROM B3_BZXJZYZFQKBFZ xa,FASP_V_PUBEXPFUNC xaa
WHERE xa.AgencyID = b.AgencyID
  AND xa.EXPFUNCGUID = b.EXPFUNCGUID
  AND TRA_F_CNLOWCASE(xa.xdwh) = TRA_F_CNLOWCASE(b.xdwh) and xa.inputdistrict = b.sjagencyid
  AND xaa.LvlID LIKE aa.LvlID || ''%''
  AND xaa.ID <> aa.ID
  AND xa.EXPFUNCGUID = xaa.ID
  AND xa.amt = Nvl((SELECT Sum(Nvl(xb.amt,0)) FROM B4_BJDJZBQKBFZ xb,FASP_V_PUBEXPFUNC xba WHERE xb.AgencyID = '''||v_UserManaDistrict||''' AND xb.EXPFUNCGUID = xba.ID AND TRA_F_CNLOWCASE(xa.xdwh) = TRA_F_CNLOWCASE(xb.xdwh)  and xa.agencyid = xb.agencyid and xa.inputdistrict = xb.sjagencyid AND xba.LvlID LIKE xaa.LvlID || ''%''),0)
  AND EXISTS(SELECT 1 FROM B4_BJDJZBQKBFZ xb2,FASP_V_PUBEXPFUNC xb2a WHERE xb2.AgencyID = '''||v_UserManaDistrict||''' AND TRA_F_CNLOWCASE(xb2.xdwh) = TRA_F_CNLOWCASE(xa.xdwh)  and xa.agencyid = xb2.agencyid and xa.inputdistrict = xb2.sjagencyid AND xb2.EXPFUNCGUID = xb2a.ID AND xb2a.LvlID LIKE xaa.LvlID || ''%'')
  AND xa.AgencyID = '''||v_UserManaDistrict||'''
)


),0)
  AND EXISTS(SELECT 1 FROM B4_BJDJZBQKBFZ b2,FASP_V_PUBEXPFUNC b2a WHERE b2.AgencyID = '''||v_UserManaDistrict||''' AND TRA_F_CNLOWCASE(b2.xdwh) = TRA_F_CNLOWCASE(a.xdwh)  and a.agencyid = b2.agencyid and a.inputdistrict = b2.sjagencyid AND b2.EXPFUNCGUID = b2a.ID AND b2a.LvlID LIKE aa.LvlID || ''%'')
  AND a.AgencyID = '''||v_UserManaDistrict||'''
  AND NOT EXISTS(
SELECT 1
FROM B3_BZXJZYZFQKBFZ xa,FASP_V_PUBEXPFUNC xaa
WHERE xa.AgencyID = a.AgencyID
  AND xa.EXPFUNCGUID = a.EXPFUNCGUID
  AND TRA_F_CNLOWCASE(xa.xdwh) = TRA_F_CNLOWCASE(a.xdwh) and xa.inputdistrict = a.inputdistrict
  AND xaa.LvlID LIKE aa.LvlID || ''%''
  AND xaa.ID <> aa.ID
  AND xa.EXPFUNCGUID = xaa.ID
  AND xa.amt = Nvl((SELECT Sum(Nvl(xb.amt,0)) FROM B4_BJDJZBQKBFZ xb,FASP_V_PUBEXPFUNC xba WHERE xb.AgencyID = '''||v_UserManaDistrict||''' AND xb.EXPFUNCGUID = xba.ID AND TRA_F_CNLOWCASE(xa.xdwh) = TRA_F_CNLOWCASE(xb.xdwh)  and xa.agencyid = xb.agencyid and xa.inputdistrict = xb.sjagencyid AND xba.LvlID LIKE xaa.LvlID || ''%''),0)
  AND EXISTS(SELECT 1 FROM B4_BJDJZBQKBFZ xb2,FASP_V_PUBEXPFUNC xb2a WHERE xb2.AgencyID = '''||v_UserManaDistrict||''' AND TRA_F_CNLOWCASE(xb2.xdwh) = TRA_F_CNLOWCASE(xa.xdwh)  and xa.agencyid = xb2.agencyid and xa.inputdistrict = xb2.sjagencyid AND xb2.EXPFUNCGUID = xb2a.ID AND xb2a.LvlID LIKE xaa.LvlID || ''%'')
  AND xa.AgencyID = '''||v_UserManaDistrict||'''
)
';
  --update thstest set a = v_sql;
  --dbms_output.put_line(v_SQL);

  EXECUTE IMMEDIATE v_SQL;


/*  v_sql2 := 'delete from a where b= '''|| v_RelaTableName||'''';
  EXECUTE IMMEDIATE v_SQL2;
  insert into a(a,b) values (v_SQL,v_RelaTableName );*/

  update BAS_T_ZYZFDZXJGQKB a set a.EXPSTRNAME = '一般性转移支付支出'
   where EXISTS(SELECT 1 FROM FASP_V_PUBEXPFUNC x WHERE rownum < 2 AND SubStr(x.ShowID,1,5) ='23002' AND a.TRANPAYFUNC = x.ID)
     and a.agencyid = v_AgencyID;
 update BAS_T_ZYZFDZXJGQKB a set a.EXPSTRNAME = '专项转移支付支出'
   where EXISTS(SELECT 1 FROM FASP_V_PUBEXPFUNC x WHERE rownum < 2 AND SubStr(x.ShowID,1,5) ='23003' AND a.TRANPAYFUNC = x.ID)
     and a.agencyid = v_AgencyID;

  update BAS_T_ZYZFDZXJGQKB a set a.EXPSTRNAME = '政府性基金转移支付支出'
   where EXISTS(SELECT 1 FROM FASP_V_PUBEXPFUNC x WHERE rownum < 2 AND SubStr(x.ShowID,1,5)= '23004'  AND a.TRANPAYFUNC = x.ID)
     and a.agencyid = v_AgencyID;



  update BAS_T_ZYZYZFZBB a set a.DZJG = (select b.dzxx from BAS_T_ZYZFDZXJGQKB b where a.inputdistrict = b.sjagencyid and TRA_F_CNLOWCASE(a.xdwh） =TRA_F_CNLOWCASE(b.xdwh）and a.agencyid = b.agencyid  and a.EXPFUNCGUID = b.EXPFUNCGUID )
   where exists( select 1 from BAS_T_ZYZFDZXJGQKB c  where a.inputdistrict = c.sjagencyid and a.agencyid =c.agencyid and TRA_F_CNLOWCASE(a.xdwh）= TRA_F_CNLOWCASE(c.xdwh） and a.EXPFUNCGUID = c.EXPFUNCGUID)
     and EXISTS(SELECT 1 FROM FASP_V_PUBEXPFUNC x WHERE rownum < 2 AND SubStr(x.ShowID,1,5) IN ('23003','23004') AND a.TRANPAYFUNC = x.ID)
     and a.agencyid = v_AgencyID;

  update BAS_T_ZYZYZFZBB a set a.DZJG = (select b.dzxx from BAS_T_ZYZFDZXJGQKB b where a.inputdistrict = b.sjagencyid and TRA_F_CNLOWCASE(a.xdwh) =TRA_F_CNLOWCASE(b.xdwh) and a.agencyid = b.agencyid  and a.TRANPAYFUNC = b.EXPFUNCGUID)
   where exists( select 1 from BAS_T_ZYZFDZXJGQKB c where a.inputdistrict = c.sjagencyid and a.agencyid =c.agencyid and TRA_F_CNLOWCASE(a.xdwh）= TRA_F_CNLOWCASE(c.xdwh） and a.TRANPAYFUNC = c.EXPFUNCGUID)
     and EXISTS(SELECT 1 FROM FASP_V_PUBEXPFUNC x WHERE rownum < 2 AND SubStr(x.ShowID,1,5) not IN ('23003','23004') AND a.TRANPAYFUNC = x.ID)
     and a.agencyid = v_AgencyID;

   update BAS_T_ZYZYZFZBB a set a.ISWZZ = (select '1'  from BAS_T_ZYZFDZXJGQKB b where b.dzxx = '009' and a.inputdistrict = b.sjagencyid and TRA_F_CNLOWCASE(a.xdwh） =TRA_F_CNLOWCASE(b.xdwh）and a.agencyid = b.agencyid  and a.EXPFUNCGUID = b.EXPFUNCGUID )
   where exists( select 1 from BAS_T_ZYZFDZXJGQKB c  where c.dzxx = '009' and a.inputdistrict = c.sjagencyid and a.agencyid =c.agencyid and TRA_F_CNLOWCASE(a.xdwh）= TRA_F_CNLOWCASE(c.xdwh） and a.EXPFUNCGUID = c.EXPFUNCGUID)
     and EXISTS(SELECT 1 FROM FASP_V_PUBEXPFUNC x WHERE rownum < 2 AND (SubStr(x.ShowID,1,5) IN ('23003','23004')  or x.ShowID in ('2300220','2300221','2300222','2300223','2300203','2300228','2300229','2300230','2300231') ) AND a.TRANPAYFUNC = x.ID)
     and a.agencyid = v_AgencyID;

   update BAS_T_ZYZYZFZBB a set a.ISWZZ = (select '0' from BAS_T_ZYZFDZXJGQKB b where a.inputdistrict = b.sjagencyid and TRA_F_CNLOWCASE(a.xdwh） =TRA_F_CNLOWCASE(b.xdwh）and a.agencyid = b.agencyid  and a.TRANPAYFUNC = b.EXPFUNCGUID )
   where exists( select 1 from BAS_T_ZYZFDZXJGQKB c  where a.inputdistrict = c.sjagencyid and a.agencyid =c.agencyid and TRA_F_CNLOWCASE(a.xdwh）= TRA_F_CNLOWCASE(c.xdwh） and a.TRANPAYFUNC = c.EXPFUNCGUID)
     and EXISTS(SELECT 1 FROM FASP_V_PUBEXPFUNC x WHERE rownum < 2 AND (SubStr(x.ShowID,1,5) not IN ('23003','23004')  and  x.ShowID not in ('2300220','2300221','2300222','2300223','2300203','2300228','2300229','2300230','2300231') ) AND a.TRANPAYFUNC = x.ID)
     and a.agencyid = v_AgencyID;


/*   update  BAS_T_ZYZYZFZBB a set a.iswzz = '1' where a.dzjg = '009' and a.agencyid = v_agencyid;*/

  return;
END;

--2-转移支付对账-05121059 - 秦
