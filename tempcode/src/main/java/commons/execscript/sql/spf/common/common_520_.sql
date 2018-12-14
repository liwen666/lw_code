create or replace procedure SPF_AND_BGT_INTERFACE(p_districtid in varchar2,p_projectids in varchar2,p_iscancel in varchar2) as
--项目库和预算接口存储过程
v_sql CLOB;
v_sqltemp varchar2(32767);
v_sql_01 VARCHAR2(2048);
v_sql_02 VARCHAR2(2048);
v_sql_03 VARCHAR2(2048);
v_sql_04 VARCHAR2(2048);
v_sql_05 VARCHAR2(2048);
v_sql_052 VARCHAR2(2048);
v_sql_06 VARCHAR2(2048);
v_sql_07 VARCHAR2(2048);
v_error  VARCHAR2(1024);

begin
---查询元数据
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
  v_sql_05:=v_sql_05||','||c_data.DBCOLUMNNAMED||' ='||c_data.DBCOLUMNNAMED||'+ C_TAB.'||c_data.DBCOLUMNNAMES;
  v_sql_052:=v_sql_052||','||c_data.DBCOLUMNNAMED||' ='||c_data.DBCOLUMNNAMED||'- C_TAB.'||c_data.DBCOLUMNNAMES;

  --INSERT
  v_sql_06:=v_sql_06||','||c_data.DBCOLUMNNAMED;
  v_sql_07:=v_sql_07||','||'C_TAB.'||c_data.DBCOLUMNNAMES;
END LOOP;
dbms_lob.createtemporary(v_sql,false);
v_sqltemp:='

begin
declare
v_datakey VARCHAR2(64);
v_iscancel  VARCHAR2(64);
begin
  v_iscancel:='''||p_iscancel||''';
  if v_iscancel=''0''
    then
--1、本级预算单位正常申报项目（不跨部门与跨部门合并）  金额取自投资计划表
FOR C_TAB IN (
       select  t.PROJECTID PROJECTID,
               (select projname from spf_t_pbaseinfo where projectid=t.projectid and rownum=1) PROJNAME,
               t.AGENCYID,
               t.FINYEAR,
               sum(t.INBUDGETNUM) INBUDGETNUM,
               ''1'' DEALTYPE,
               (select spfid from spf_t_pbaseinfo where projectid=t.projectid and rownum=1) SPFID,
               (select a.projtypeid from spf_t_fbaseinfo a,spf_t_projecttype b  where a.spfid=t.spfid and a.projtypeid=b.projtypeid and rownum=1) PROJTYPE,
               t.EXPFUNCID ,
               t.EXPECOID'||v_sql_02||
          ' from
               (select i.PROJECTID ,
                       i.FINYEAR,
                       i.INBUDGETNUM,
                       a1.SPFID,
                       i.EXPECOID,
                       a1.AGENCYID,
                       i.EXPFUNCID '||v_sql_04||
                 ' from Spf_t_Pinvestplan i,
                       spf_t_pbaseinfo   a1,
                       SPF_T_PBASESTATUS PB
                 where i.ISPLANVALID = ''1''
                   and i.PROJECTID = a1.PROJECTID
                   and a1.PROJECTID=PB.PROJECTID
                   and (i.ISADJUST=''0'' or i.ISADJUST is null)
                   and PB.isbgt=''1''
                   and a1.projectid in '||p_projectids||'
                  ) t
         where Exists (select 1
                  from code_t_agency_spf
                 where guid = AGENCYID
                   and districtid = '''||p_districtid||
                   '''
                   and divtype = ''0'')

       group by PROJECTID,
                SPFID,
                AGENCYID,
                FINYEAR,
                EXPFUNCID,
                EXPECOID

    )';
DBMS_LOB.WRITEAPPEND(v_sql,LENGTH(v_sqltemp),v_sqltemp);
v_sqltemp:=' LOOP
      select sys_guid into v_datakey from dual;
    
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
               C_TAB.expfuncid,
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
                   and nvl(EXPFUNCID, ''*'') = nvl(C_TAB.EXPFUNCID, ''*'')
                   and nvl(EXPECOID, ''*'')  = nvl(C_TAB.Expecoid,  ''*'') );

END LOOP;';
DBMS_LOB.WRITEAPPEND(v_sql,LENGTH(v_sqltemp),v_sqltemp);
v_sqltemp:='elsif v_iscancel=''1''
   then
     FOR C_TAB IN (
       select  t.PROJECTID PROJECTID,
               (select projname from spf_t_pbaseinfo where projectid=t.projectid and rownum=1) PROJNAME,
               t.AGENCYID,
               t.FINYEAR,
               sum(t.INBUDGETNUM) INBUDGETNUM,
               ''1'' DEALTYPE,
               (select spfid from spf_t_pbaseinfo where projectid=t.projectid and rownum=1) SPFID,
               (select a.projtypeid from spf_t_fbaseinfo a,spf_t_projecttype b  where a.spfid=t.spfid and a.projtypeid=b.projtypeid and rownum=1) PROJTYPE,
               t.EXPFUNCID ,
               t.EXPECOID'||v_sql_02||
          ' from
               (select i.PROJECTID ,
                       i.FINYEAR,
                       i.INBUDGETNUM,
                       a1.SPFID,
                       i.EXPECOID,
                       a1.AGENCYID,
                       i.EXPFUNCID '||v_sql_04||
                 ' from Spf_t_Pinvestplan i,
                       spf_t_pbaseinfo   a1,
                       SPF_T_PBASESTATUS PB
                 where i.ISPLANVALID = ''1''
                   and i.PROJECTID = a1.PROJECTID
                   and a1.PROJECTID = PB.PROJECTID
                   and (i.ISADJUST=''0'' or i.ISADJUST is null)
                   and PB.isbgt=''0''
                   and a1.projectid in '||p_projectids||'
                  ) t
         where Exists (select 1
                  from code_t_agency_spf
                 where guid = AGENCYID
                   and districtid = '''||p_districtid||
                   '''
                   and divtype = ''0'')

       group by PROJECTID,
                SPFID,
                AGENCYID,
                FINYEAR,
                EXPFUNCID,
                EXPECOID

    )
    LOOP
     delete EXP_T_PBUDGETTEMP t where projectid=C_TAB.PROJECTID;
     END LOOP;
   end if;';
DBMS_LOB.WRITEAPPEND(v_sql,LENGTH(v_sqltemp),v_sqltemp);
v_sqltemp:='
/*

2、本级非预算单位申报项目（企业、个人）
    按照专项资金地区，找到该专项地区下的“单位或个人部门”（通过单位性质字段区分）。
    按照专项资金加该部门汇总明细项目
*/
 if v_iscancel=''0''
    then
FOR C_TAB IN (
   select      t.SPFID PROJECTID ,
               (select spfname from spf_t_fbaseinfo where spfid=t.spfid and rownum=1 ) PROJNAME,
               t.EXPFUNCID EXPFUNCID,
               t.AGENCYID,
               t.FINYEAR,
               sum(t.INBUDGETNUM) INBUDGETNUM,
               ''2'' DEALTYPE,
               t.spfid,
               (select a.projtypeid from spf_t_fbaseinfo a,spf_t_projecttype b  where a.spfid=t.spfid and a.projtypeid=b.projtypeid and rownum=1) PROJTYPE,
               t.EXPECOID'||v_sql_02||
   ' from (
                select i.EXPFUNCID,
                       s1.SPFID,
                       i.FINYEAR,
                       i.INBUDGETNUM,
                       i.EXPECOID,'||
                      'f1.FIRAGENCYID AGENCYID '||v_sql_04||
                 '  from Spf_t_Pinvestplan i ,spf_t_pbaseinfo s1, spf_t_fbaseinfo f1, SPF_T_PBASESTATUS PB
                 where i.PROJECTID = s1.PROJECTID
                   AND s1.SPFID = f1.SPFID
                   and s1.PROJECTID = PB.PROJECTID
                   and (i.ISADJUST=''0'' or i.ISADJUST is null)
                   AND i.ISPLANVALID = ''1''
                   and PB.ISBGT = ''1''
                   and s1.projectid in '||p_projectids||'
                   and Exists
                 (select 1
                          from code_t_agency_spf
                         where guid = s1.AGENCYID
                           and districtid = '''||p_districtid||
                          '''
                          and (divtype = ''3'' or divtype = ''4'')
                 )
            ) t
         group by t.SPFID,t.AGENCYID ,t.FINYEAR,t.EXPFUNCID,t.EXPECOID
 ) ';
DBMS_LOB.WRITEAPPEND(v_sql,LENGTH(v_sqltemp),v_sqltemp);
v_sqltemp:='
 LOOP
      select sys_guid into v_datakey from dual;
   
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
               C_TAB.EXPFUNCID,
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
                 where spfid=C_TAB.spfid
                   and finyear = C_TAB.Finyear
                   and agencyid = C_TAB.Agencyid
                   and nvl(EXPFUNCID, ''*'') = nvl(C_TAB.EXPFUNCID, ''*'')
                   and nvl(EXPECOID, ''*'')  = nvl(C_TAB.Expecoid,  ''*'') );

END LOOP;';
DBMS_LOB.WRITEAPPEND(v_sql,LENGTH(v_sqltemp),v_sqltemp);
v_sqltemp:='
 elsif v_iscancel=''1''
   then
     FOR C_TAB IN (
   select      t.SPFID PROJECTID ,
               (select spfname from spf_t_fbaseinfo where spfid=t.spfid and rownum=1 ) PROJNAME,
               t.EXPFUNCID EXPFUNCID,
               t.AGENCYID,
               t.FINYEAR,
               sum(t.INBUDGETNUM) INBUDGETNUM,
               ''2'' DEALTYPE,
               t.spfid,
               (select a.projtypeid from spf_t_fbaseinfo a,spf_t_projecttype b  where a.spfid=t.spfid and a.projtypeid=b.projtypeid and rownum=1) PROJTYPE,
               t.EXPECOID'||v_sql_02||
   ' from (
                select i.EXPFUNCID,
                       s1.SPFID,
                       i.FINYEAR,
                       i.INBUDGETNUM,
                       i.EXPECOID,'||
                      'f1.FIRAGENCYID AGENCYID '||v_sql_04||
                 '  from Spf_t_Pinvestplan i ,spf_t_pbaseinfo s1, spf_t_fbaseinfo f1,SPF_T_PBASESTATUS PB
                 where i.PROJECTID = s1.PROJECTID
                   AND s1.SPFID = f1.SPFID
                   AND s1.PROJECTID = PB.PROJECTID
                   and (i.ISADJUST=''0'' or i.ISADJUST is null)
                   AND i.ISPLANVALID = ''1''
                   and PB.ISBGT = ''0''
                   and s1.projectid in '||p_projectids||'
                   and Exists
                 (select 1
                          from code_t_agency_spf
                         where guid = s1.AGENCYID
                           and districtid = '''||p_districtid||
                          '''
                          and (divtype = ''3'' or divtype = ''4'')
                 )
            ) t
         group by t.SPFID,t.AGENCYID ,t.FINYEAR,t.EXPFUNCID,t.EXPECOID
 )
 LOOP
     delete EXP_T_PBUDGETTEMP t where projectid=C_TAB.PROJECTID;
   END LOOP;
   end if;';
DBMS_LOB.WRITEAPPEND(v_sql,LENGTH(v_sqltemp),v_sqltemp);
v_sqltemp:='
--3、下级单位申报项目（财政、预算单位、企业、个人）  按照专项资金（地区）汇总明细项目
if v_iscancel=''0''
    then
      FOR C_TAB IN (

        select t.SPFID PROJECTID ,
               (select spfname from spf_t_fbaseinfo where spfid=t.spfid and rownum=1 ) PROJNAME,
               t.EXPFUNCID,
               t.AGENCYID ,
               t.FINYEAR,
               sum(t.INBUDGETNUM) INBUDGETNUM,
               ''2'' DEALTYPE,
               t.SPFID,
               (select a.projtypeid from spf_t_fbaseinfo a,spf_t_projecttype b  where a.spfid=t.spfid and a.projtypeid=b.projtypeid and rownum=1) PROJTYPE,
               t.EXPECOID '||v_sql_02||
       ' from ( select i.GOVEXPFUNCID EXPFUNCID,
                       i.GOVEXPECOID EXPECOID,
                       i.FINYEAR,
                       i.INBUDGETNUM,
                       s1.SPFID,
                       ag.DISTRICTID AGENCYID '|| v_sql_04|| '
                  from spf_t_pbaseinfo s1,Spf_t_Pinvestplan i,code_t_agency_spf ag,SPF_T_PBASESTATUS PB
                 where PB.ISBGT = ''1''
                   and s1.projectid=PB.projectid
                   and s1.projectid in '||p_projectids||'
                   and i.ISPLANVALID = ''1''
                   and (i.ISADJUST=''0'' or i.ISADJUST is null)
                   and i.PROJECTID = s1.PROJECTID
                   and s1.agencyid = ag.guid
                   and Exists
                 (select 1
                          from code_t_agency_spf
                         where districtid <> '''||p_districtid||
                         '''  and guid = s1.AGENCYID  )
                   ) t
      group by t.SPFID,t.AGENCYID,t.FINYEAR,T.EXPFUNCID,T.EXPECOID
   ) ';
DBMS_LOB.WRITEAPPEND(v_sql,LENGTH(v_sqltemp),v_sqltemp);
v_sqltemp:='
LOOP
 select sys_guid into v_datakey from dual;    
 update EXP_T_PBUDGETTEMP T set
         PROJNAME = C_TAB.PROJNAME,
         BuDGetNUM =BuDGetNUM+ C_TAB.Inbudgetnum,
         PROJTYPE = C_TAB.PROJTYPE,
         EXPFUNCID = C_TAB.EXPFUNCID,
         EXPECOID = C_TAB.EXPECOID'|| v_sql_05||
      ' where
          SPFID = C_TAB.SPFID
          AND AGENCYID = C_TAB.AGENCYID
          AND T.FINYEAR = C_TAB.FINYEAR
          AND t.ispass=''0''
          and nvl(EXPFUNCID,''*'') =nvl(C_TAB.EXPFUNCID,''*'')
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
               C_TAB.EXPFUNCID,
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
                 where spfid = C_TAB.spfid
                   AND ispass=''0''
                   and agencyid = C_TAB.Agencyid
                   and finyear = C_TAB.Finyear
                   and nvl(EXPFUNCID, ''*'') = nvl(C_TAB.EXPFUNCID, ''*'')
                   and nvl(EXPECOID, ''*'') =  nvl(C_TAB.Expecoid,  ''*'') );          
END LOOP;';
DBMS_LOB.WRITEAPPEND(v_sql,LENGTH(v_sqltemp),v_sqltemp);
v_sqltemp:='
   FOR C_TAB2 IN (
     select i.DATAKEY as INVEST_DATAKEY,m.DATAKEY AS INBUDGET_DATAKEY from spf_t_pinvestplan i,spf_t_pbaseinfo a1,exp_t_pbudgettemp m,SPF_T_PBASESTATUS PB  where
     i.ISPLANVALID =''1''
     and i.PROJECTID = a1.PROJECTID
      and a1.PROJECTID = PB.PROJECTID
     and (i.ISADJUST=''0'' or i.ISADJUST is null)
     and PB.isbgt=''1''
     and m.ISPASS=''0''
     and m.AGENCYID=a1.DISTRICTID
     and a1.PROJECTID in '||p_projectids||'
     and Exists (select 1
                      from code_t_agency_spf
                     where guid = a1.AGENCYID
                       and districtid<>  '''||p_districtid||'''
               )
       and  m.PROJECTID=a1.SPFID
       and  m.FINYEAR=i.FINYEAR
       and nvl(m.EXPFUNCID,''*'') =nvl(i.GOVEXPFUNCID,''*'')
       and nvl(m.EXPECOID,''*'')=nvl(i.GOVExpecoid,''*'') 
       )
LOOP 
   select sys_guid into v_datakey from dual;    
   UPDATE SPF_T_INBUDGETTEMP T SET T.INBUDGET_DATAKEY=C_TAB2.INBUDGET_DATAKEY,ISSELECT=''0'',ZCMX_DATAKEY=''NULL'' WHERE T.INVEST_DATAKEY=C_TAB2.INVEST_DATAKEY;
   INSERT INTO SPF_T_INBUDGETTEMP (DATAKEY,INVEST_DATAKEY,INBUDGET_DATAKEY,ISSELECT,ZCMX_DATAKEY) SELECT V_DATAKEY,C_TAB2.INVEST_DATAKEY,C_TAB2.INBUDGET_DATAKEY,''0'',''NULL'' FROM DUAL WHERE 1=1 AND
   NOT EXISTS(SELECT 1 FROM SPF_T_INBUDGETTEMP WHERE INVEST_DATAKEY=C_TAB2.INVEST_DATAKEY AND ISSELECT=''0'');
END LOOP;';
DBMS_LOB.WRITEAPPEND(v_sql,LENGTH(v_sqltemp),v_sqltemp);
v_sqltemp:='
  elsif  v_iscancel=''1''
      then 
    FOR C_TAB IN (
    SELECT TEM.INVEST_DATAKEY,TEM.INBUDGET_DATAKEY,TEM.ZCMX_DATAKEY,I.INBUDGETNUM '||v_sql_04||' FROM SPF_T_PINVESTPLAN I,SPF_T_INBUDGETTEMP TEM WHERE TEM.INVEST_DATAKEY=I.DATAKEY
     AND I.PROJECTID IN '||p_projectids||'
     )
     LOOP
      UPDATE BGT_T_ZCXMMXB T SET BUDGETNUM=BUDGETNUM-C_TAB.INBUDGETNUM WHERE DATAKEY=C_TAB.ZCMX_DATAKEY;
       update EXP_T_PBUDGETTEMP T set
         BuDGetNUM =BuDGetNUM-C_TAB.Inbudgetnum'|| v_sql_052||
      ' where DATAKEY=C_TAB.INBUDGET_DATAKEY;
      UPDATE  SPF_T_INBUDGETTEMP SET INBUDGET_DATAKEY=''NULL'',ZCMX_DATAKEY=''NULL'',ISSELECT=''0'' WHERE INVEST_DATAKEY=C_TAB.INVEST_DATAKEY;
    END LOOP;
  end if;


end ;
end ;';
DBMS_LOB.WRITEAPPEND(v_sql,LENGTH(v_sqltemp),v_sqltemp);
execute immediate v_sql;
DBMS_LOB.freetemporary(v_sql);

/*P_CONTROL_PROJECT(V_PROJECTIDS =>p_projectids ,V_ISCANCLE =>p_iscancel,V_RESULT =>v_error );*/

/*exception
  when others then
     rollback;
     v_error:='执行存储过程失败,原因是:'||sqlerrm;
    dbms_output.put_line(v_error);*/

end SPF_AND_BGT_INTERFACE;
--纳入预算存储过程
