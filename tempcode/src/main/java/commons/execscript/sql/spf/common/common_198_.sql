BEGIN
 update P#DICT_T_MODELCODE set DYNAMICWHERE =' 1 = 1
 START WITH GUID =
            (SELECT CASE
                      WHEN (SUBSTR(CODE, LENGTH(CODE) - 1) = ''00'') THEN
                       (SELECT GUID
                          FROM CODE_T_DISTRICT_SPF
                         WHERE GUID = B.SUPERGUID)
                      ELSE
                       GUID
                    END DISTRICT
               FROM CODE_T_DISTRICT_SPF B
              WHERE GUID =
                    (SELECT admdiv
                       FROM FASP_T_CAUSER
                      WHERE GUID =
                            (GLOBAL_MULTYear_CZ.Secu_f_Global_Parm(''USER'')))
                AND GUID IN (select guid
                               from code_t_district_spf
                              start with GUID in
                                         (SELECT DISTRICTID
                                            FROM SPF_T_FDECLAREREGION
                                           WHERE spfid =
                                                 ''$SPFID$'')
                             CONNECT by prior guid = superguid))
CONNECT by prior guid = superguid
 order by CODE' 
 where tableid in('D754323AE16C448AAB86064FA77B96CE','D2F1DEB6AB314AA3A29604EB79C74051');
 
update P#dict_t_factor
   set ISVISIBLE = '0'
 where tableid in
       (select tableid from dict_t_model where dealtype in ('2104', '2105'))
   AND DBCOLUMNNAME = 'SPFID';
END;
--ÐÞ¸Ä²âËãµ¥Î»ºÍÒþ²ØSPFID×Ö¶Î
