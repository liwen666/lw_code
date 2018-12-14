BEGIN
  
update P#SPF_T_CONDISET t set t.sentences='exists(select 1 from (select objectid from spf_t_cflow_act where wfdirection in (''0'',''1'') and targetagencyid in (select districtid from code_t_firagency where iscz = ''1'') union all select objectid from spf_t_cflow_hist where wfdirection in (''0'',''1'') and targetagencyid in (select districtid from code_t_firagency where iscz = ''1'')) t where t.objectid =TBASE.projectid )' where t.guid='4E20AD0968040696E0533A06A8C09B34';

END;--综合处理财政备选项目库去掉isbgt
