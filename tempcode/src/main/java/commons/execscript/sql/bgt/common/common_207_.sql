create or replace procedure PROC_QUOTA_DATACOMPARE(P_FINYEAR Char,P_TABLEID Varchar2,P_AGENCYID Varchar2) is
--�����ʽ��ֵ��������ͻ���֧�����Ա�
V_DBTABLENAME Varchar2(32);
V_SQL Clob;
V_DATATYPE Char(1);
V_FUNDVAL Number(18,2);
V_DATAVAL Number(18,2);
Begin
Select DBTABLENAME Into V_DBTABLENAME From DICT_T_MODEL Where TABLEID=P_TABLEID;

--��ɾ�� �Աȱ��е�����
execute Immediate 'DELETE FROM BGT_T_FORMULADZB  WHERE TABLEID='''||P_TABLEID||''' AND AGENCYID='''||P_AGENCYID||'''';

 --���㶨�ʽֵ����������
For c_formula In (Select tt.Projtypeid, tt.Expecoid, tt.Fundsourceid, tt.Formulaid,
              (Select f.Dbcolumnname From  Dict_t_Factor f Where f.Columnid = Tt.Fundsourceid) As Dbcolumnname
         From (Select w.Projtypeid, w.Expecoid, w.Fundsourceid, w.Formulaid
                 From Cal_t_Outwindowset w
                Where w.TABLEID=P_TABLEID
                  And w.Agencyid = P_AGENCYID
                  And w.Formulaid Is Not Null --Not In ('001', '002')
               Union All
               Select w.Projtypeid, w.Expecoid, w.Fundsourceid, w.Formulaid
                 From Cal_t_Outwindowset w
                Where w.tableid=P_TABLEID
                  And w.Agencyid = '*'
                  And w.Formulaid Is Not Null
                  And w.Formulaid Not In ('001', '002')
                  And (w.Projtypeid, w.Expecoid, w.Fundsourceid) Not In
                      (Select Ww.Projtypeid,
                              Ww.Expecoid,
                              Ww.Fundsourceid
                         From Cal_t_Outwindowset Ww
                        Where ww.tableid=P_TABLEID And Ww.Agencyid = P_AGENCYID And ww.FORMULAID Is Not Null)) Tt) Loop
             --���㶨�ʽֵ��ˢ�±��ݱ��е�����
                    Select F_GET_FORMULA_QUOTA_VAL(P_TABLEID,V_DBTABLENAME,P_FINYEAR,P_AGENCYID,c_formula.Projtypeid,c_formula.EXPECOID,c_formula.Fundsourceid) Into V_FUNDVAL From dual;
             --��ȡ��ϸ���е�ֵ

                    V_SQL:='select sum('||c_formula.Dbcolumnname||') from '||V_DBTABLENAME||' t_exp Where ';
                    V_SQL:=V_SQL||' t_exp.finYear='||P_FINYEAR;
                    V_SQL:=V_SQL||' and t_exp.agencyid='''||P_AGENCYID||'''';
                    V_SQL:=V_SQL||' and t_exp.Projtypeid='''||c_formula.Projtypeid||'''';
                    If c_formula.Expecoid!='*' Then
                      V_DATATYPE:='2';
                      V_SQL:=V_SQL||' and t_exp.EXPECOID='''||c_formula.EXPECOID||'''';

                    Else
                        V_DATATYPE:='1';
                    End If;
                    execute Immediate V_SQL Into V_DATAVAL ;
                    --����˱��в�������
                    If V_DATAVAL Is Null Then
                       V_DATAVAL:=0; 
                    End If;

                    V_SQL:='insert into bgt_t_formuladzb(tableid, datatype,agencyid,spfid,expecoid,columnid, formulaid, amounts,formulaamounts) values';
                    V_SQL:=V_SQL||'(:1,:2,:3,:4,:5,:6,:7,:8,:9)';
                    execute Immediate V_SQL Using P_TABLEID,V_DATATYPE,P_AGENCYID,c_formula.Projtypeid,c_formula.Expecoid,c_formula.Fundsourceid,c_formula.formulaid,V_DATAVAL,V_FUNDVAL;
                     End Loop;



end PROC_QUOTA_DATACOMPARE;--14_���ɶ��ʽ���ݶԱ�����PROC_QUOTA_DATACOMPARE
