DECLARE
begin
EXECUTE IMMEDIATE Q'/
CREATE OR REPLACE VIEW SPF_T_YJPBYBRZ AS
SELECT s.datakey,
       s.objectid,
       e.BUSITYPEID,
       s.BATCHID,
         (select title from BD_T_BATCH where batchid=s.batchid) title,
       e.AGENCYID TARGETAGENCYID,
     e.SOURCEBATCHID,
       (select busitypename from bd_t_busitype where BUSITYPEID = e.BUSITYPEID) busintypename,
       sysdate dbversion,
       spfyj.agencyid,
       spfyj.PROJTYPEID,
       spfyj.SPFID,
     spfyj.SPFNAME,
       spfyj.PROJTYPENAME,
       e.nodeid,
       s.DISTRICTID,
       s.OBJSTATUS,
       spfyj.SETUPSTATUS,
       (case when (spfyj.SETUPSTATUS=0) then '未设立'  when (spfyj.SETUPSTATUS=1) then '已确认,未发布' else '已设立' end) SETUPSTATUSNAME,
       1 NEEDUPDATE,
       1 ORDERID,
       e.WFSTATUS,
       e.wfversion,
       to_char(sysdate,'yyyy') FINYEAR,
       e.remark
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
  ) e,SPF_V_SPFFMAINYJ spfyj
where s.BATCHID=e.BATCHID and s.SPFID=spfyj.spfid and s.SPFID = s.OBJECTID  and s.objstatus in ('0','1','2')

  /';
end;
--一级项目已办横向视图修改
