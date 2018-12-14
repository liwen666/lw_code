CREATE OR REPLACE VIEW SPF_V_PROJECTTYPE_A AS
SELECT DBVERSION,
       STATUS,
       SUPERID,
       ISBASE,
       ORDERID,
       PROJTYPENAME,
       LEVELNO,
       PROJTYPE,
       BGTLVL,
       DATAKEY,
       PROJTYPEID,
       MANADEPT,
       ISLEAF,
       CODE,
       SPFBPMN,
       PROJECTBPMN,
       DISTRICTID
  FROM P#SPF_T_PROJECTTYPE p#spf_t_projecttype
 WHERE STATUS = '1'
   AND (DISTRICTID = (select nvl((select (select districtid
                                           from Code_t_Agency_Spf
                                          where guid = upagencyid)
                                   from fasp_t_causer
                                  where guid =
                                        global_multyear_cz.Secu_f_GLOBAL_PARM('USER')),
                                 (select districtid
                                    from Code_t_Agency_Spf
                                   where code =
                                         global_multyear_cz.Secu_f_GLOBAL_PARM('DIVID')))
                        from dual) OR DISTRICTID = '*')
--SPF_V_PROJECTTYPE_A
