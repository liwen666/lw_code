CREATE OR REPLACE VIEW CODE_T_AGENCY_SPFDL AS
SELECT GUID,
       CODE,
       NAME,
       SUPERGUID,
       ISLEAF,
       LEVELNO,
       DIVTYPE,
       ISDISTRICT,
       DISTRICTID,
       DISTRICTCODE,
       DISTRICTNAME,
       DISTRICTLVL,
       AGENCYTYPE,
       ID,
       ENDFLAG,
       LVLID,
       SUPERID,
       FININTORG
  FROM CODE_T_AGENCY_SPF
 WHERE GUID IN (
                --��ǰ�û�������λ
                select GUID
                  from CODE_T_AGENCY_SPF
                 WHERE districtid =
                       (select districtid
                          from code_t_agency_spf
                         where guid =
                               (select upagencyid
                                  from secu_t_user
                                 where guid =
                                       GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('USER')))
                union all
                --��ǰ�û��¼�����
                select GUID
                  from CODE_T_DISTRICT_SPF
                 start with guid =
                            (select case isself
                                      when '1' then
                                       superguid
                                      else
                                       guid
                                    end
                               from code_t_district_spf
                              where guid =
                                    (select districtid
                                       from code_t_agency_spf
                                      where guid =
                                            (select upagencyid
                                               from secu_t_user
                                              where guid =
                                                    GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('USER'))))
                CONNECT BY PRIOR GUID = SUPERGUID)
--CODE_T_AGENCY_SPFDL
