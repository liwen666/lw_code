DECLARE
begin
EXECUTE IMMEDIATE Q'/
CREATE OR REPLACE VIEW SPF_T_XMBYBRZ AS
SELECT c.datakey,
       c.objectid,
       e.BUSITYPEID,
       c.BATCHID,
         (select title from BD_T_BATCH where batchid=c.batchid) title,
       e.AGENCYID TARGETAGENCYID,
     e.SOURCEBATCHID,
       (select busitypename from bd_t_busitype where BUSITYPEID = e.BUSITYPEID) busintypename,
       p.dbversion,
       p.agencyid,
       p.PROJECTID,
       p.PROJTYPEID,
       p.SPFID,
       e.nodeid,
       c.DISTRICTID,
       c.OBJSTATUS,
       p.PROJNAME,
       1 NEEDUPDATE,
       1 ORDERID,
       e.WFSTATUS,
       e.wfversion,
       e.remark,
       ps.CHECKSTATUS,
       (case when(ps.CHECKSTATUS=1) then '已审核' else '未审核' end ) CHECKSTATUSNAME,
       ps.ISBGT,
       (case when(ps.ISBGT=1) then '已纳入预算' else '未纳入预算' end ) ISBGTNAME,
       ps.ISINDEX,
       (case when(ps.ISINDEX=1) then '已纳入指标' else '未纳入指标' end ) ISINDEXNAME,
       p.startyear FINYEAR
  FROM SPF_T_BFLOW_LOG c,(
  SELECT distinct BUSITYPEID,
                  AGENCYID,
                  SOURCEBATCHID,
                  nodeid,
                  WFSTATUS,
                  WFVERSION,
                  batchid,
                  EBL.REMARK
    from EFM_T_BFLOW_LOG EBL,bd_v_cutuserhistorytask BVC
    WHERE EBL.BATCHID = BVC.business_key_
    AND EBL.NODEID = BVC.task_def_key_
    AND EBL.WFVERSION = BVC.processDefId
    OR EBL.REMARK = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('USER')
  ) e
  , spf_t_pbaseinfo p, spf_t_pbasestatus ps where c.objectid = p.PROJECTID 
  and c.objstatus in ('0','1','2') 
  and e.batchid=c.BATCHID 
  and ps.PROJECTID=c.OBJECTID

/';

end;
--横向项目已办修改视图sql
