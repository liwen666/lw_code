create or replace procedure PROC_GENE_QUOTA_AGENCY(P_FINYEAR Char,P_TABLEID Varchar2,P_AGENCYID Varchar2) is
--将基本支出明细表的公式值刷新到bak表 baktype=7
V_DBTABLENAME Varchar2(32);
V_DBTABLENAME_BAK Varchar2(32);
V_SQL Clob;
V_COL Clob:='';
V_BAKTYPE Char(1):='7';
V_N Number(8);
V_FUNDVAL Number(18,2);
Begin
Select DBTABLENAME Into V_DBTABLENAME From DICT_T_MODEL Where TABLEID=P_TABLEID;
V_DBTABLENAME_BAK:=V_DBTABLENAME||'_BAK';
Select Count(0) Into V_N From User_Views Where VIEW_NAME=V_DBTABLENAME_BAK;
If V_N=0 Then
  RAISE_APPLICATION_ERROR(-20001, '备份表 '||V_DBTABLENAME_BAK||'  不存在,请创建! ');
  End If;

--先删除 bak表中该单位的所有数据
  execute Immediate 'delete from '||V_DBTABLENAME_BAK||' where baktype='''||V_BAKTYPE||''' and agencyID='''||P_AGENCYID||''' and finYear='''||P_FINYEAR||'''';

 --复制明细数据
 V_SQL:='INSERT INTO  ';
  For c_colData In (Select * From DICT_T_FACTOR Where TABLEID=P_TABLEID  AND  ISLEAF='1' AND (DBCOLUMNNAME IS NOT NULL)  And ISBANDCOL!='1' AND ISVIRTUAL!='1')Loop

   V_COL:=V_COL||c_colData.Dbcolumnname||',';

   End Loop;
  V_SQL:=V_SQL||V_DBTABLENAME_BAK||'('||V_COL||'BAKTYPE) SELECT '||V_COL||'7 BAKTYPE FROM '||V_DBTABLENAME;
  V_SQL:=V_SQL||' WHERE AGENCYID='''||P_AGENCYID||''' AND FINYEAR='||P_FINYEAR;
  dbms_output.put_line(V_SQL);
   execute Immediate V_SQL;

 --复制专项数据
 V_COL:='';
 V_SQL:='INSERT INTO  '||V_DBTABLENAME_BAK||'(';
  For c_colData In (Select * From DICT_T_FACTOR Where TABLEID=P_TABLEID And dbcolumnname!='DATAKEY' AND  ISLEAF='1' AND (DBCOLUMNNAME IS NOT NULL)  And ISBANDCOL!='1' AND ISVIRTUAL!='1' )Loop
   V_SQL:=V_SQL||c_colData.Dbcolumnname||',';
   If c_coldata.datatype<3 Then
        V_COL:=V_COL||'sum(nvl('||c_colData.Dbcolumnname||',0)),';

     Else
       If c_coldata.dbcolumnname='EXPECOID' Then
         --用*标志该条数据是专项数据
          V_COL:=V_COL||'''*'',';
         Else
            V_COL:=V_COL||'max('||c_colData.Dbcolumnname||'),';
         End If;


     End If;


   End Loop;
   V_SQL:=V_SQL||'BAKTYPE) SELECT '||V_COL||'7 FROM '||V_DBTABLENAME;
   V_SQL:=V_SQL||' WHERE AGENCYID='''||P_AGENCYID||''' AND FINYEAR='||P_FINYEAR;
   V_SQL:=V_SQL||' GROUP BY FINYEAR,AGENCYID,PROJTYPEID';
   execute Immediate V_SQL;

 --计算定额公式值并覆盖数据
For c_formula In (Select tt.Projtypeid, tt.Expecoid, tt.Fundsourceid, tt.Formulaid,(Select f.DBCOLUMNNAME From dict_t_factor f Where f.COLUMNID=tt.Fundsourceid) As DBCOLUMNNAME
         From (Select w.Projtypeid, w.Expecoid, w.Fundsourceid, w.Formulaid
                 From Cal_t_Outwindowset w
                Where w.TABLEID=P_TABLEID And w.Agencyid = P_AGENCYID
                  And w.Formulaid Not In ('001', '002')
               Union All
               Select w.Projtypeid, w.Expecoid, w.Fundsourceid, w.Formulaid
                 From Cal_t_Outwindowset w
                Where w.TABLEID=P_TABLEID And w.Agencyid = '*'
                  And w.Formulaid Not In ('001', '002')
                  And (w.Projtypeid, w.Expecoid, w.Fundsourceid) Not In
                      (Select Ww.Projtypeid,
                              Ww.Expecoid,
                              Ww.Fundsourceid
                         From Cal_t_Outwindowset Ww
                        Where ww.TABLEID=P_TABLEID And Ww.Agencyid = P_AGENCYID)) Tt) Loop
             --计算定额公式值并刷新备份表中的数据
                    Select F_GET_FORMULA_QUOTA_VAL(P_TABLEID,V_DBTABLENAME,P_FINYEAR,P_AGENCYID,c_formula.Projtypeid,c_formula.EXPECOID,c_formula.Fundsourceid) Into V_FUNDVAL From dual;
                    V_SQL:='update '||V_DBTABLENAME_BAK||' t_bak set t_bak.'||c_formula.Dbcolumnname||'='||V_FUNDVAL;
                    V_SQL:=V_SQL||' WHERE T_BAK.BAKTYPE='||V_BAKTYPE;
                    V_SQL:=V_SQL||' and t_bak.finYear='''||P_FINYEAR||'''';
                    V_SQL:=V_SQL||' and t_bak.agencyid='''||P_AGENCYID||'''';
                    V_SQL:=V_SQL||' and t_bak.Projtypeid='''||c_formula.Projtypeid||'''';
                    V_SQL:=V_SQL||' and t_bak.EXPECOID='''||c_formula.EXPECOID||'''';
                   -- dbms_output.put_line(V_SQL);
                     execute Immediate V_SQL;


                        End Loop;



end PROC_GENE_QUOTA_AGENCY;
--02_PROC_GENE_QUOTA_AGENCY
