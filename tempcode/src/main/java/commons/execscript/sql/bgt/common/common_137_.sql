create or replace view bil_t_oarelation as
select DBVERSION,DOCID,GUID,ORGID,REMARK,STATUS,BILLID,BILLTYPEID,USERID from P#BIL_T_OARELATION WHERE  STATUS='1'
--bil_t_oarelation取消年度160630
