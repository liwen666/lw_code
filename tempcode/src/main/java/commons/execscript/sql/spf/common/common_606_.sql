DECLARE
begin
EXECUTE IMMEDIATE Q'/
CREATE OR REPLACE VIEW SPF_T_EJPBYBRZ AS
SELECT s.datakey,
       s.objectid,
       e.BUSITYPEID,
       s.BATCHID,
         (select title from BD_T_BATCH where batchid=s.batchid) title,
       e.AGENCYID TARGETAGENCYID,
     e.SOURCEBATCHID,
       (select busitypename from bd_t_busitype where BUSITYPEID = e.BUSITYPEID) busintypename,
       sysdate dbversion,
       spfej.agencyid,
       spfej.PROJECTID,
       spfej.PROJTYPEID,
       spfej.SPFID,
       e.nodeid,
       s.DISTRICTID,
       s.OBJSTATUS,
       1 NEEDUPDATE,
       1 ORDERID,
       e.WFSTATUS,
       e.wfversion,
       spfej.SPFNAME,
       spfej.PROJNAME,
       spfej.CHECKSTATUS,
       (case when(spfej.CHECKSTATUS=1) then '已审核' else '未审核' end ) CHECKSTATUSNAME,
       spfej.ISBGT,
       (case when(spfej.ISBGT=1) then '已纳入预算' else '未纳入预算' end ) ISBGTNAME,
       spfej.ISINDEX,
       (case when(spfej.ISINDEX=1) then '已纳入指标' else '未纳入指标' end ) ISINDEXNAME,
       to_char(sysdate,'yyyy') FINYEAR,
       e.REMARK
from spf_t_bflow_log s,(
  SELECT distinct BUSITYPEID,
                  AGENCYID,
                  SOURCEBATCHID,
                  nodeid,
                  WFSTATUS,
                  WFVERSION,
                  batchid,
                  ebl.REMARK
    from EFM_T_BFLOW_LOG EBL,bd_v_cutuserhistorytask BVC
    WHERE EBL.BATCHID = BVC.business_key_
    AND EBL.NODEID = BVC.task_def_key_
    AND EBL.WFVERSION = BVC.processDefId
    or ebl.REMARK = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('USER')
  ) e,SPF_V_SPFPMAINEJ spfej
where s.BATCHID=e.BATCHID and s.OBJECTID=spfej.PROJECTID  and s.objstatus in ('0','1','2')

  /';
  end;
--修改横向二级项目已办视图
