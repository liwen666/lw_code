DECLARE
begin
EXECUTE IMMEDIATE Q'/
CREATE OR REPLACE VIEW SPF_T_XMBRZ AS
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
       ps.CHECKSTATUS,
       (case when(ps.CHECKSTATUS=1) then '�����' else 'δ���' end ) CHECKSTATUSNAME,
       ps.ISBGT,
       (case when(ps.ISBGT=1) then '������Ԥ��' else 'δ����Ԥ��' end ) ISBGTNAME,
       ps.ISINDEX,
       (case when(ps.ISINDEX=1) then '������ָ��' else 'δ����ָ��' end ) ISINDEXNAME,
       p.startyear FINYEAR
  FROM SPF_T_BFLOW_LOG c,(SELECT distinct BUSITYPEID,AGENCYID,SOURCEBATCHID,nodeid,WFSTATUS,batchid,WFVERSION from EFM_T_BFLOW_LOG) e
  , spf_t_pbaseinfo p, spf_t_pbasestatus ps where c.objectid = p.PROJECTID and e.batchid=c.BATCHID and ps.PROJECTID=c.OBJECTID
/';

end;
--�޸�SPF_T_XMBRZ��ͼ
