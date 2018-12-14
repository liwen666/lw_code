create or replace function FUN_GET_TASKIDBYBUSIID(AGENCYID VARCHAR2) return CLOB is
  v_return CLOB;
begin
  v_return := 'SPFID IN ( select sto.TASKID 
  from HQOA_T_busi c,spf_t_oarelation sto
 where c.busiid = sto.DOCID and sto.TASKTYPE =''0''
   and c.busiid in (select b.busiid
                      from HQOA_T_DOCISSUED b
                     where targetorgs = '''||AGENCYID||'''))';

  return v_return;
end FUN_GET_TASKIDBYBUSIID;

--刘凯_项目倒挂存储过程
