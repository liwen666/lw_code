delete KPI_T_SETINPUTSTEP
 WHERE guid IN (SELECT guid FROM KPI_T_SETINPUTSTEP GROUP BY guid HAVING COUNT(guid) > 1)
   AND ROWID NOT IN
       (SELECT MIN(ROWID) FROM KPI_T_SETINPUTSTEP GROUP BY guid HAVING COUNT(*) > 1)--WUTF_20161025��Ч�׶�ȥ������ظ���¼