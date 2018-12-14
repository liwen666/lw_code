DECLARE
begin
EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SPF_T_SPFSDYJHCCOMT AS
SELECT HIST.BATCHID,
       (SELECT TITLE FROM BD_T_BATCH WHERE BATCHID = HIST.BATCHID) TITLE,
       HIST.BUSITYPEID,
       HIST.CREATEUSER,
       HIST.DATAKEY,
       HIST.OBJECTID,
       HIST.DISTRICTID,
       HIST.REMARK,
       HIST.SOURCEAGENCYID,
       HIST.SOURCEBATCHID,
       HIST.STEP,
       HIST.TARGETAGENCYID,
       HIST.WFDIRECTION,
       HIST.WFSTATUS,
       SPFYJ.SPFID,
       SPFYJ.PROJTYPEID,
       SPFYJ.AGENCYID,
       FS.SETUPSTATUS,
       (CASE
         WHEN (FS.SETUPSTATUS = 0) THEN
          '未设立'
         WHEN (FS.SETUPSTATUS = 1) THEN
          '已确认，未发布'
         ELSE
          '已设立'
       END) SETUPSTATUSNAME,
       1 NEEDUPDATE,
       1 ORDERID,
       (SELECT BUSITYPENAME
          FROM BD_T_BUSITYPE
         WHERE BUSITYPEID = HIST.BUSITYPEID) BUSITYPENAME,
       TO_CHAR(SYSDATE, 'YYYY') FINYEAR
  FROM SPF_T_CFLOW_HIST HIST, SPF_V_SPFFMAINYJ SPFYJ, SPF_T_FBASESTATUS FS
 WHERE SPFYJ.SPFID = HIST.OBJECTID
   AND FS.SPFID = SPFYJ.SPFID
   AND HIST.WFDIRECTION IN ('0' ， '1' ， '2' ， '8')
UNION ALL
SELECT ACT.BATCHID,
       (SELECT TITLE FROM BD_T_BATCH WHERE BATCHID = ACT.BATCHID) TITLE,
       ACT.BUSITYPEID,
       ACT.CREATEUSER,
       ACT.DATAKEY,
       ACT.OBJECTID,
       ACT.DISTRICTID,
       ACT.REMARK,
       ACT.SOURCEAGENCYID,
       ACT.SOURCEBATCHID,
       ACT.STEP,
       ACT.TARGETAGENCYID,
       ACT.WFDIRECTION,
       ACT.WFSTATUS,
       SPFYI.SPFID,
       SPFYI.PROJTYPEID,
       SPFYI.AGENCYID,
       SPFYI.SETUPSTATUS,
       (CASE
         WHEN (SPFYI.SETUPSTATUS = 0) THEN
          '未设立'
         WHEN (SPFYI.SETUPSTATUS = 1) THEN
          '已确认，未发布'
         ELSE
          '已设立'
       END) SETUPSTATUSNAME,
       1 NEEDUPDATE,
       1 ORDERID,
       (SELECT BUSITYPENAME
          FROM BD_T_BUSITYPE
         WHERE BUSITYPEID = ACT.BUSITYPEID) BUSITYPENAME,
       TO_CHAR(SYSDATE, 'YYYY') FINYEAR
  FROM SPF_T_CFLOW_ACT ACT, SPF_V_SPFFMAINYJ SPFYI
 WHERE SPFYI.SPFID = ACT.OBJECTID
   AND ACT.WFDIRECTION IN ('0' ， '1' ， '2')/';

end;--已发送发送目标一级项目视图SPF_T_SPFSDYJHCCOMT

--SPF_T_SPFSDYJHCCOMT
--SPF_T_SPFSDYJHCCOMT
