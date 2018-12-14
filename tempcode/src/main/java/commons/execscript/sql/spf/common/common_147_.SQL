create or replace procedure SPF_AND_BGT_INTERFACE(p_districtid in varchar2,p_projectids in varchar2,p_batchid in varchar2) as
--项目库和预算接口存储过程
v_sql varchar2(32767);
v_sql_01 VARCHAR2(2048);
v_sql_02 VARCHAR2(2048);
v_sql_03 VARCHAR2(2048);
v_sql_04 VARCHAR2(2048);
v_sql_05 VARCHAR2(2048);
v_sql_06 VARCHAR2(2048);
v_sql_07 VARCHAR2(2048);
v_error  VARCHAR2(1024);
clob_data clob;

begin

  FOR c_data in (
  select a.DBCOLUMNNAME DBCOLUMNNAMES, b.DBCOLUMNNAME DBCOLUMNNAMED
    from (select t.DBCOLUMNNAME, t.DEID
            from dict_t_factor t
           where tableid =
                 (select t.tableid
                    from dict_t_model t
                   where dbtablename = 'SPF_T_PINVESTPLAN')
             and datatype = 2) a,
         (select t.DBCOLUMNNAME, t.DEID
            from dict_t_factor t
           where tableid =
                 (select t.tableid
                    from dict_t_model t
                   where dbtablename = 'EXP_T_PBUDGETTEMP')
             and datatype = 2) b
   where a.deid = b.deid and upper(b.DBCOLUMNNAME)<>'BUDGETNUM'
)

LOOP
  --SELECT
  v_sql_01:=v_sql_01||','||c_data.DBCOLUMNNAMES;
  v_sql_02:=v_sql_02||','||'sum(t.'||c_data.DBCOLUMNNAMES||') '||c_data.DBCOLUMNNAMES;
  v_sql_03:=v_sql_03||','||'sum(s.'||c_data.DBCOLUMNNAMES||') '||c_data.DBCOLUMNNAMES;
  v_sql_04:=v_sql_04||','||'i.'||c_data.DBCOLUMNNAMES;

  --UPDATE
  v_sql_05:=v_sql_05||','||c_data.DBCOLUMNNAMED||' = C_TAB.'||c_data.DBCOLUMNNAMES;

  --INSERT
  v_sql_06:=v_sql_06||','||c_data.DBCOLUMNNAMED;
  v_sql_07:=v_sql_07||','||'C_TAB.'||c_data.DBCOLUMNNAMES;
END LOOP;

v_sql:='

begin
declare
v_datakey VARCHAR2(64);
begin
FOR C_TAB IN (
select PROJECTID,FINYEAR,AGENCYID,ACCTCODE,PROJNAME,PROJCODE,DISTRICTID,nvl(INBUDGETNUM,0) as INBUDGETNUM,DEALTYPE,SPFID,PROJTYPE,EXPECOID'||v_sql_01||
' from ( select PROJECTID,FINYEAR,AGENCYID,ACCTCODE,PROJNAME,PROJCODE,DISTRICTID,INBUDGETNUM,DEALTYPE,SPFID, PROJTYPE,EXPECOID'||v_sql_01||
' from (
       -- 1,2 本级预算单位正常申报项目（不跨部门与跨部门合并）  金额取自投资计划表
       select  t.PROJECTID PROJECTID,
               t.PROJNAME PROJNAME,
               t.PROJCODE PROJCODE,
               t.DISTRICTID DISTRICTID,
               t.EXPFUNCID ACCTCODE,
               t.AGENCYID,
               t.FINYEAR,
               sum(t.INBUDGETNUM) INBUDGETNUM,
               ''1'' dealtype,
               t.SPFID,
               t.PROJTYPE,
               t.EXPECOID'||v_sql_02||
               /*
               sum(t.HJ) HJ,                                     --预算单位资金来源合计
               --部门预算资金来源
               sum(t.YSDWZJLYHJ) YSDWZJLYHJ,                     --部门预算合计
               --政府预算资金来源
               sum(t.ZFYSZJLYHJ) ZFYSZJLYHJ,                     --政府预算合计
               */
          ' from
               (select i.PROJECTID,
                       i.FINYEAR,
                       i.INBUDGETNUM,
                       i.EXPECOID,
                       /*i.HJ,
                       --部门预算资金来源
                       i.YSDWZJLYHJ,                               --部门预算合计
                       --政府预算资金来源
                       i.ZFYSZJLYHJ,                                 --政府预算合计
                       */
                       a1.PROJNAME,
                       a1.PROJCODE,
                       a1.ISBGT,
                       a1.AGENCYID,
                       b1.DISTRICTID,
                       i.EXPFUNCID,
                       b1.FIRAGENCYID,
                       b1.SPFID,
                       pt.PROJTYPE '||v_sql_04||
                 ' from Spf_t_Pinvestplan i,
                       spf_t_pbaseinfo   a1,
                       spf_t_fbaseinfo   b1,
                       SPF_T_PROJECTTYPE pt
                 where i.ISPLANVALID = ''1''
                   and i.PROJECTID = a1.PROJECTID
                   and a1.SPFID = b1.SPFID
                   and b1.PROJTYPEID = pt.PROJTYPEID
                   and (i.ISADJUST=''0'' or i.ISADJUST is null)
                   and a1.projectid in '||p_projectids||'
                  ) t
         where
            t.ISBGT = ''1''
           and Exists (select 1
                  from code_t_agency_spf
                 where guid = AGENCYID
                   and districtid = '''||p_districtid||
                   '''
                   and divtype = ''0''
       )
         group by PROJECTID,
                  PROJNAME,
                  PROJCODE,
                  DISTRICTID,
                  EXPFUNCID,
                  AGENCYID,
                  FINYEAR,
                  PROJTYPE,
                  EXPECOID,
                  SPFID

        union all

/*3 本级非预算单位申报项目（企业、个人）
    按照专项资金地区，找到该专项地区下的“单位或个人部门”（通过单位性质字段区分）。
    按照专项资金加该部门汇总明细项目
*/
   select      s.SPFID ,
               s.SPFNAME ,
               s.SPFCODE,
               s.DISTRICTID,
               s.EXPFUNCID ACCTCODE,
               s.FIRAGENCYID,
               s.FINYEAR,
               sum(s.INBUDGETNUM) INBUDGETNUM,
               ''2'' dealtype,
               s.spfid,
               s.PROJTYPE,
               s.EXPECOID'||v_sql_03||
               /*
               sum(s.HJ) HJ,
               --部门预算资金来源
               sum(s.YSDWZJLYHJ) YSDWZJLYHJ,                               --部门预算合计
               --政府预算资金来源
               sum(s.ZFYSZJLYHJ) ZFYSZJLYHJ,                                 --政府预算合计
               */
          ' from (
                select s1.PROJECTID,
                       s1.PROJNAME,
                       i.EXPFUNCID,
                       s1.DISTRICTID,
                       s1.AGENCYID,
                       s1.SPFID,
                       i.FINYEAR,
                       i.INBUDGETNUM,
                       i.EXPECOID,'||
                       /* i.HJ,'
                          部门预算资金来源
                          i.YSDWZJLYHJ,                               --部门预算合计
                         --政府预算资金来源
                          i.ZFYSZJLYHJ,                                 --政府预算合计
                       */
                      'f1.FIRAGENCYID,
                       f1.SPFCODE,
                       f1.SPFNAME,
                       pt.PROJTYPE'||v_sql_04||
                 '  from Spf_t_Pinvestplan i ,spf_t_pbaseinfo s1, spf_t_fbaseinfo f1,SPF_T_PROJECTTYPE pt
                 where i.PROJECTID = s1.PROJECTID
                   AND s1.SPFID = f1.SPFID
                   and f1.PRJREPLY = pt.PROJTYPE
                   and (i.ISADJUST=''0'' or i.ISADJUST is null)
                   AND i.ISPLANVALID = ''1''
                   and s1.ISBGT = ''1''
                   and f1.SPFID in ( select distinct spfid from spf_t_pbaseinfo where projectid in '||p_projectids||' )
                   and Exists
                 (select 1
                          from code_t_agency_spf
                         where guid = s1.AGENCYID
                           and districtid = '''||p_districtid||
                          ''' and isleaf = ''1''
                           and (divtype = ''3'' or divtype = ''4'')
                           and isdistrict = ''0'')
                  ) s
         group by s.SPFID, s.SPFNAME,s.SPFCODE,s.DISTRICTID, s.EXPFUNCID, s.FIRAGENCYID ,s.FINYEAR,s.PROJTYPE,s.EXPECOID

        union all
   -- 4 下级单位申报项目（财政、预算单位、企业、个人）  按照专项资金（地区）汇总明细项目
        select s.SPFID ,
               s.SPFNAME ,
               s.SPFCODE,
               s.DISTRICTID,
               '''' ACCTCODE,
               ag.DISTRICTID ,
               t.FINYEAR,
               sum(t.INBUDGETNUM) INBUDGETNUM,
               ''2'' dealtype,
               s.SPFID,
                s.PROJTYPE,
               '''' EXPECOID'||v_sql_02||
               /*
               sum(t.HJ) HJ,
               --部门预算资金来源
               sum(t.YSDWZJLYHJ) YSDWZJLYHJ,                               --部门预算合计
               --政府预算资金来源
               sum(t.ZFYSZJLYHJ) ZFYSZJLYHJ,                                 --政府预算合计
               */
          ' from (select s1.PROJECTID,
                       s1.PROJNAME,
                       s1.EXPFUNCID,
                       s1.AGENCYID,
                       s1.SPFID,
                       f1.SPFNAME,
                       f1.SPFCODE,
                       f1.DISTRICTID,
                       pt.PROJTYPE
                  from spf_t_pbaseinfo s1, spf_t_fbaseinfo f1,SPF_T_PROJECTTYPE pt
                 where s1.SPFID = f1.SPFID
                   and f1.PROJTYPEID = pt.PROJTYPEID
                   and s1.ISBGT = ''1''
                   and f1.SPFID  in ( select distinct spfid from spf_t_pbaseinfo where projectid in '||p_projectids||' )
                   and Exists
                 (select 1
                          from code_t_agency_spf
                         where districtid <> '''||p_districtid||
                         '''  and guid = s1.AGENCYID
                           and isleaf = ''1''
                           and isdistrict = ''0'')
                   ) s,
               (select i.PROJECTID,
                       i.FINYEAR,
                       i.INBUDGETNUM,
                       i.EXPECOID'||v_sql_04||
                       /*
                       i.HJ,
                      --部门预算资金来源
                       i.YSDWZJLYHJ,                               --部门预算合计
                       i.QTSRAP,                                   --其他收入
                       --政府预算资金来源
                       */
                  ' from Spf_t_Pinvestplan i,
                       spf_t_pbaseinfo   a1,
                       spf_t_fbaseinfo   b1
                 where i.ISPLANVALID = ''1''
                   and i.PROJECTID = a1.PROJECTID
                   and a1.SPFID = b1.SPFID
                   and (i.ISADJUST=''0'' or i.ISADJUST is null)
                  ) t
               ,code_t_agency_spf ag
         where s.PROJECTID = t.PROJECTID
           and s.AGENCYID = ag.guid
         group by s.SPFID, s.SPFNAME,s.SPFCODE,s.districtid, ag.DISTRICTID,t.FINYEAR,s.projtype) ut
       where     1=1
     ))
    LOOP
      select sys_guid into v_datakey from dual;
      update EXP_T_PBUDGETTEMP T set
         SPFID = C_TAB.SPFID,
         PROJNAME = C_TAB.PROJNAME,
         EXPFUNCID = C_TAB.acctcode,
         BuDGetNUM = C_TAB.Inbudgetnum,
         PROJTYPE = C_TAB.PROJTYPE,
         EXPECOID = C_TAB.EXPECOID'|| v_sql_05||
      ' where
          PROJECTID = C_TAB.PROJECTID
          AND T.FINYEAR = C_TAB.FINYEAR
          AND T.AGENCYID = C_TAB.AGENCYID
          and nvl(EXPFUNCID,''*'') =nvl(C_TAB.Acctcode,''*'')
          and nvl(EXPECOID,''*'')=nvl(C_TAB.Expecoid,''*'') ;

      insert into EXP_T_PBUDGETTEMP
        (DATAKEY,
         BATCHID,
         SPFID,
         projectid,
         FINYEAR,
         AGENCYID,
         FUNDSOURCEID,
         EXPFUNCID,
         PROJNAME,
         BuDGetNUM,
         DEALTYPE,
         PROJTYPE,
         XZQHCODE,
         EXPECOID'||v_sql_06||
        ')
        select v_datakey,v_datakey,
               C_TAB.SPFID,
               C_TAB.projectid,
               C_TAB.finyear,
               C_TAB.agencyid,
               ''0001'',
               C_TAB.acctcode,
               C_TAB.projname,
               C_TAB.INBUDGETNUM,
               C_TAB.dealtype,
               C_TAB.PROJTYPE,
               (select a.code
                  from code_t_district_spf a, spf_t_fbaseinfo b
                 where a.guid = b.DISTRICTID
                   and b.spfid = C_TAB.SPFID),
               C_TAB.EXPECOID'||v_sql_07||
              '
          from dual
         where not exists
         (select 1
                  from EXP_T_PBUDGETTEMP
                 where projectid = C_TAB.projectid
                   and finyear = C_TAB.Finyear
                   and agencyid = C_TAB.Agencyid
                   and nvl(EXPFUNCID, ''*'') =
                       nvl(C_TAB.Acctcode, ''*'')
                   and nvl(EXPECOID, ''*'') = nvl(C_TAB.Expecoid, ''*''));

     end loop;
end ;
end ;';
execute immediate v_sql;
/*exception
  when others then
     rollback;
     v_error:='执行存储过程失败,原因是:'||sqlerrm;
     dbms_output.put_line(v_error);*/

end SPF_AND_BGT_INTERFACE;
--纳入预算存储过程-纳入预算总额字段不参与数据源配置
