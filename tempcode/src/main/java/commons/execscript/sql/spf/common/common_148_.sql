BEGIN
  UPDATE dict_t_model SET tabswhere ='spfid in (SELECT spfid
                   FROM SPF_T_FBASEINFO
                  WHERE PROJTYPEID IN
                        (SELECT PROJTYPEID
                           FROM SPF_T_PROJECTTYPE
                          START WITH PROJTYPEID = ''1''  CONNECT BY PRIOR PROJTYPEID = SUPERID) AND FUNDMANAGE in( ''2'',''3''))' WHERE dealtype ='2105';
UPDATE dict_t_model SET tabswhere ='spfid in (SELECT spfid
                   FROM SPF_V_FBASEINFO_A
                  WHERE PROJTYPEID IN
                        (SELECT PROJTYPEID
                           FROM SPF_V_PROJECTTYPE_A
                          START WITH PROJTYPEID = ''1''
                         CONNECT BY PRIOR PROJTYPEID = SUPERID))' WHERE dealtype ='5*05';
UPDATE dict_t_model SET tabswhere ='spfid in (SELECT spfid
                   FROM SPF_T_FBASEINFO
                  WHERE PROJTYPEID IN
                        (SELECT PROJTYPEID
                           FROM SPF_T_PROJECTTYPE
                          START WITH PROJTYPEID = ''1''
                         CONNECT BY PRIOR PROJTYPEID = SUPERID) AND projectid =''*'')' WHERE dealtype ='4*50';
SYS_P_RECREATE_VIEWS('88A60D407D2F4363887343806F05458F');
SYS_P_RECREATE_VIEWS('B4719068F4ED492BB1A782247FAEE701');
SYS_P_RECREATE_VIEWS('293889E1D09E42B9B4F2F7481F320797');
END;
--修改明细表where条件