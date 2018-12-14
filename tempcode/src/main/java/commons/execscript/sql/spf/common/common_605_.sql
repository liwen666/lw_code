DECLARE
begin
EXECUTE IMMEDIATE Q'/
CREATE OR REPLACE VIEW SPF_T_XZBYBRZ AS
SELECT c.datakey,
       c.objectid,
       e.BUSITYPEID,
       c.BATCHID,
          (select title from BD_T_BATCH where batchid=c.batchid) title,
       e.AGENCYID TARGETAGENCYID,
     e.SOURCEBATCHID,
       (select busitypename from bd_t_busitype where BUSITYPEID = e.BUSITYPEID) busitypename,
       p.dbversion,
       p.agencyid,
       p.PROJTYPEID,
       p.SPFID,
       fs.SETUPSTATUS,
       (case when (fs.SETUPSTATUS=0) then '未设立'  when (fs.SETUPSTATUS=1) then '已确认,未发布' else '已设立' end) SETUPSTATUSNAME,
       1 NEEDUPDATE,
       c.OBJSTATUS,
       1 ORDERID,
       p.BEGINYEAR FINYEAR,
       p.SPFNAME,
       e.NODEID,
       e.WFSTATUS,
       e.wfversion,
       c.DISTRICTID,
       e.remark
  FROM SPF_T_BFLOW_LOG c, (
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
  ) e
  , spf_t_fbaseinfo p ,spf_t_fbasestatus fs where c.spfid = p.spfid and c.objstatus in ('0','1','2') and c.spfid=c.objectid and e.BATCHID=c.BATCHID and fs.SPFID=p.spfid

  /';
end;
--专项资金横向已办视图修改
