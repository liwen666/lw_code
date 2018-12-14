create or replace procedure SPF_AND_INDEX_INTERFACE(p_districtid in varchar2,p_projectids in varchar2,p_batchid in varchar2) as
--项目库和指标接口存储过程
V_count int;
V_wjzcount int;
V_yjzcount int;
v_difamt  number(18,4);
begin
FOR C_TAB IN (
select PROJECTID,EXPECOID,FINYEAR,AGENCYID,ACCTCODE,PROJNAME,PROJCODE,DISTRICTID,nvl(AMOUNT,0) as AMOUNT,DEALTYPE,SPFID,DEPTID,PROJTYPE from (
select PROJECTID,EXPECOID,FINYEAR,AGENCYID,ACCTCODE,PROJNAME,PROJCODE,DISTRICTID,AMOUNT,DEALTYPE,spfid,DEPTID, PROJTYPE 
  from (
  -- 1.1 类型项目    明细项目   金额取自投资计划表  
   select      t.PROJECTID PROJECTID,
               t.EXPECOID,
               t.PROJNAME PROJNAME,
               t.PROJCODE PROJCODE,
               t.DISTRICTID DISTRICTID,
               t.EXPFUNCID ACCTCODE,
               t.AGENCYID,
               t.FINYEAR,
               sum(t.ADDINDEXNUM) AMOUNT,
               '1' dealtype,
               t.SPFID,
               t.DEPTID,
               t.PROJTYPE        
          from   (select i.PROJECTID, 
                       i.EXPECOID,                      
                       i.FINYEAR,
                       i.ADDINDEXNUM,
                       a1.PROJNAME,
                       a1.PROJCODE,
                       a1.AGENCYID,
                       a1.ISINDEX,
                       b1.DISTRICTID,
                       i.EXPFUNCID,
                       b1.SPFID,
                       b1.DEPTID,
                       pt.PROJTYPE
                  from Spf_t_Pinvestplan i,
                       spf_t_pbaseinfo   a1,
                       spf_t_fbaseinfo   b1,
                       SPF_T_PROJECTTYPE pt
                 where i.ISPLANVALID = '1'                  
                   and i.PROJECTID = a1.PROJECTID
                   and a1.SPFID = b1.SPFID
                   and b1.PROJTYPEID = pt.PROJTYPEID     
                   and  p_projectids like '%'||a1.projectid||'%'
                  ) t            
         where 
            ISINDEX = '1'
           and Exists (select 1
                  from code_t_agency_spf
                 where guid = AGENCYID
                   and districtid = p_districtid
                   and isleaf = '1'
                   and divtype = '0'
                   and isdistrict = '0')          
         group by PROJECTID,
                  PROJNAME,
                  PROJCODE,
                  DISTRICTID,
                  EXPFUNCID,
                  EXPECOID,
                  AGENCYID,
                  FINYEAR,
                  SPFID,
                  DEPTID,
                  PROJTYPE  
                  
        union all
   -- 3 按照专项资金（部门）汇总明细项目
        select t.SPFID ,
                '' EXPECOID,
               t.SPFNAME ,
               t.SPFCODE,
               t.DISTRICTID,
                '' ACCTCODE,
               t.FIRAGENCYID,
               t.FINYEAR,
               sum(t.ADDINDEXNUM) AMOUNT,
               '2' dealtype,
               t.SPFID,
               t.DEPTID,
               t.PROJTYPE
          from ( select s1.PROJECTID,
                       s1.PROJNAME,
                       s1.EXPFUNCID,
                       s1.DISTRICTID,
                       s1.AGENCYID,
                       s1.SPFID                       
                  from spf_t_pbaseinfo s1, spf_t_fbaseinfo f1
                 where s1.SPFID = f1.SPFID
                   and s1.ISINDEX = '1'                 
                   and Exists
                 (select 1
                          from code_t_agency_spf
                         where guid = s1.AGENCYID
                           and districtid = p_districtid
                           and isleaf = '1'
                           and (divtype = '3' or divtype = '4')
                           and isdistrict = '0')
                  ) s,
               (select i.PROJECTID,
                       i.EXPECOID,
                       i.FINYEAR,
                       i.ADDINDEXNUM ADDINDEXNUM,
                       b1.SPFID,
                       b1.SPFNAME,
                       b1.SPFCODE,
                       b1.DISTRICTID,
                       i.EXPFUNCID,
                       b1.FIRAGENCYID,
                       b1.DEPTID,
                       pt.PROJTYPE
                  from Spf_t_Pinvestplan i,
                       spf_t_pbaseinfo   a1,
                       spf_t_fbaseinfo   b1,
                       SPF_T_PROJECTTYPE pt
                 where i.ISPLANVALID = '1'                   
                   and i.PROJECTID = a1.PROJECTID
                   and a1.SPFID = b1.SPFID
                   and b1.PROJTYPEID = pt.PROJTYPEID
                   and b1.SPFID in (  select distinct spfid from spf_t_pbaseinfo where  p_projectids like '%'||projectid||'%' ) 
                  ) t           
         where s.PROJECTID = t.PROJECTID          
         group by t.SPFID, t.SPFNAME,t.SPFCODE,t.DISTRICTID,t.FIRAGENCYID,t.DEPTID,t.PROJTYPE, t.FINYEAR

        union all
      -- 4 按照专项资金（地区）汇总明细项目
        select t.SPFID ,
               '' EXPECOID,
               t.SPFNAME ,
               t.SPFCODE,
               t.DISTRICTID,
               '' ACCTCODE,
               ag.DISTRICTID,
               t.FINYEAR,
               sum(t.AMOUNT) AMOUNT,
               '2' dealtype,
               t.SPFID,
               t.DEPTID,
               t.PROJTYPE
          from (select s1.PROJECTID,
                       s1.PROJNAME,
                       s1.EXPFUNCID,
                       s1.AGENCYID,
                       s1.SPFID
                  from spf_t_pbaseinfo s1, spf_t_fbaseinfo f1
                 where s1.SPFID = f1.SPFID
                   and s1.ISINDEX = '1'
                   and Exists
                 (select 1
                          from code_t_agency_spf
                         where districtid <> p_districtid
                           and guid = s1.AGENCYID
                           and isleaf = '1'
                           and isdistrict = '0')
                   ) s,
               (select i.PROJECTID,
                       i.EXPECOID,
                       i.FINYEAR,
                       i.ADDINDEXNUM AMOUNT,
                       b1.SPFID,
                       b1.SPFNAME,
                       b1.SPFCODE,
                       b1.DISTRICTID,
                       i.EXPFUNCID,
                       b1.DEPTID,
                       pt.PROJTYPE
                  from Spf_t_Pinvestplan i,
                       spf_t_pbaseinfo   a1,
                       spf_t_fbaseinfo   b1,
                       SPF_T_PROJECTTYPE pt
                 where i.ISPLANVALID = '1'                   
                   and i.PROJECTID = a1.PROJECTID
                   and a1.SPFID = b1.SPFID
                   and b1.PROJTYPEID = pt.PROJTYPEID
                   and b1.SPFID in (  select distinct spfid from spf_t_pbaseinfo where p_projectids like '%'||projectid||'%') 
                  ) t,          
               code_t_agency_spf ag
         where s.PROJECTID = t.PROJECTID          
           and s.AGENCYID = ag.guid
         group by t.SPFID, t.SPFNAME,t.SPFCODE,t.PROJTYPE, ag.DISTRICTID,t.DISTRICTID,t.DEPTID, t.FINYEAR) ut
 where     1=1
       )
      )
    LOOP
     
        --获取记录总数量
        select count(1) into V_count
          from SPF_T_XMSCZBLYB t
         where PROGUID = C_TAB.PROJECTID
           AND T.FINYEAR = C_TAB.FINYEAR
           AND T.AGENCYGUID = C_TAB.AGENCYID
           and nvl(T.EXPECOID,'*') = nvl(C_TAB.EXPECOID,'*')
           and nvl(T.EXPFUNCGUID,'*') = nvl(C_TAB.ACCTCODE,'*');
        --获取未记账的记录数量
        select count(1) into V_wjzcount
          from SPF_T_XMSCZBLYB t
         where PROGUID = C_TAB.PROJECTID
           AND T.FINYEAR = C_TAB.FINYEAR
           AND T.AGENCYGUID = C_TAB.AGENCYID
           and nvl(T.EXPECOID,'*') = nvl(C_TAB.EXPECOID,'*')
           and nvl(T.EXPFUNCGUID,'*') = nvl(C_TAB.ACCTCODE,'*')
           and t.SFJZ = '0'  ;
         --如果未记账的数量大于0则更新数据
         if (V_wjzcount>0) then
            update SPF_T_XMSCZBLYB T
               set APROJCODE    = C_TAB.Projcode,
                   APROJECTNAME = C_TAB.PROJNAME,
                   AMT          = C_TAB.AMOUNT
             where PROGUID = C_TAB.PROJECTID
               AND T.FINYEAR = C_TAB.FINYEAR
               AND T.AGENCYGUID = C_TAB.AGENCYID
               and nvl(T.EXPECOID,'*') = nvl(C_TAB.EXPECOID,'*')
               and nvl(T.EXPFUNCGUID,'*') = nvl(C_TAB.ACCTCODE,'*')
               and t.SFJZ = '0';
         else  
            --没有未记账则分为有已记账和没有记录两种情况
            --获取已经记账的记录数量
            select count(1) into V_yjzcount
              from SPF_T_XMSCZBLYB t
             where PROGUID = C_TAB.PROJECTID
               AND T.FINYEAR = C_TAB.FINYEAR
               AND T.AGENCYGUID = C_TAB.AGENCYID
               and nvl(T.EXPECOID,'*') = nvl(C_TAB.EXPECOID,'*')
               and nvl(T.EXPFUNCGUID,'*') = nvl(C_TAB.ACCTCODE,'*')
               and t.SFJZ = '1'   ;            
               --获取差额
         select C_TAB.AMOUNT -  nvl( (select  sum(t.AMT) 
              from SPF_T_XMSCZBLYB t
             where PROGUID = C_TAB.PROJECTID
               AND T.FINYEAR = C_TAB.FINYEAR
               AND T.AGENCYGUID = C_TAB.AGENCYID
               and nvl(T.EXPECOID,'*') = nvl(C_TAB.EXPECOID,'*')
               and nvl(T.EXPFUNCGUID,'*') = nvl(C_TAB.ACCTCODE,'*')
               and t.SFJZ = '1'  
               group by PROGUID,FINYEAR,AGENCYGUID,EXPECOID,EXPFUNCGUID) ,0)  into v_difamt from dual ;
               
             --如果有记账
             if (V_yjzcount>0 and v_difamt <> 0) then
                insert into SPF_T_XMSCZBLYB(DATAKEY,PROGUID,EXPECOID,FINYEAR,AGENCYGUID,EXPFUNCGUID, APROJECTNAME,APROJCODE,DISTRICTID,AMT,PROCTYPE,SPFID,PROJTYPE,FININTORGGUID,BATCHID )
                select sys_guid(),C_TAB.projectid,C_TAB.EXPECOID,C_TAB.finyear,C_TAB.agencyid,C_TAB.acctcode,C_TAB.projname,C_TAB.projcode,C_TAB.districtid,v_difamt,C_TAB.DEALTYPE,C_TAB.SPFID,C_TAB.PROJTYPE,C_TAB.DEPTID,sys_guid()
                from dual ;
             elsif (V_count=0) then --没有记录则插入新记录     
             insert into SPF_T_XMSCZBLYB
               (DATAKEY,
                PROGUID,
                EXPECOID,
                FINYEAR,
                AGENCYGUID,
                EXPFUNCGUID,
                APROJECTNAME,
                APROJCODE,
                DISTRICTID,
                AMT,
                PROCTYPE,
                SPFID,
                PROJTYPE,
                FININTORGGUID,
                BATCHID)
               select sys_guid(),
                      C_TAB.projectid,
                      C_TAB.EXPECOID,
                      C_TAB.finyear,
                      C_TAB.agencyid,
                      C_TAB.acctcode,
                      C_TAB.projname,
                      C_TAB.projcode,
                      C_TAB.districtid,
                      C_TAB.AMOUNT,
                      C_TAB.DEALTYPE,
                      C_TAB.SPFID,
                      C_TAB.PROJTYPE,
                      C_TAB.DEPTID,
                      p_batchid
                 from dual
                where not exists
                (select 1
                         from SPF_T_XMSCZBLYB
                        where PROGUID = C_TAB.projectid
                          and APROJCODE = C_TAB.PROJCODE                          
                          and finyear = C_TAB.Finyear
                          and agencyGUid = C_TAB.Agencyid
                          and nvl(EXPECOID, '*') = nvl(C_TAB.EXPECOID, '*')
                          and nvl(EXPFUNCGUID, '*') = nvl(C_TAB.ACCTCODE, '*')
                          and districtid = C_TAB.Districtid);
            end if;
           
         end if;  
        
     end loop;
end SPF_AND_INDEX_INTERFACE;
--SPF_AND_INDEX_INTERFACE
