BEGIN
UPDATE dict_t_modelcode SET dynamicwhere ='((exists
        (select 1
           from spf_t_fbaseinfo fb
          where SPFID = ''$SPFID$''
            AND fb.DISTRICTID = ''$DISTRICTID$'') and
        guid in (select guid
                   from code_t_agency_spfdl
                  start with guid in (select AGENCYID
                                        from SPF_T_FDECLAREAGENCY
                                       where spfid = ''$SPFID$'')
                 connect by prior guid = superguid))

        or (not exists (select 1
                          from spf_t_fbaseinfo fb
                         where SPFID = ''$SPFID$''
                           AND fb.DISTRICTID = ''$DISTRICTID$'') AND
            districtid = ''$DISTRICTID$''))
             start with guid = ''$AGENCYID$''
connect by prior guid = superguid
 order by code' WHERE tableid ='223267AA7D472B22E050A8C021057ED5';
END;
--修改代录代码表条件
