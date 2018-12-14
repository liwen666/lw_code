create or replace procedure PROC_GENE_QUOTA_TAB(P_FINYEAR Char,P_TABLEID Varchar2,P_AGENCYID Varchar2 Default Null) is
--������֧����ϸ��Ĺ�ʽֵˢ�µ�bak�� baktype=7
V_DBTABLENAME Varchar2(32);
V_DBTABLENAME_BAK Varchar2(32);
---V_AGENCY_DBTABLENAME Varchar2(32);
V_N Number(8);
Begin 
Select DBTABLENAME Into V_DBTABLENAME From DICT_T_MODEL Where TABLEID=P_TABLEID;
V_DBTABLENAME_BAK:=V_DBTABLENAME||'_BAK';
Select Count(0) Into V_N From User_Views Where VIEW_NAME=V_DBTABLENAME_BAK;
If V_N=0 Then 
  RAISE_APPLICATION_ERROR(-20001, '���ݱ� '||V_DBTABLENAME_BAK||'  ������,�봴��! '); 
  End If;
 --���û�д���λ�� ȡ��ͼCODE_T_AGENCY_BGT�����е�ĩ����λ
If P_AGENCYID Is Null Then 
  --V_AGENCY_DBTABLENAME:='CODE_T_AGENCY_BGT';
  For c_data In(Select ags.guid From CODE_T_AGENCY_BGT ags Where ags.isleaf=1) Loop
     PROC_GENE_QUOTA_AGENCY(P_FINYEAR,P_TABLEID,c_data.guid);
  End Loop;
  Else
    PROC_GENE_QUOTA_AGENCY(P_FINYEAR ,P_TABLEID ,P_AGENCYID);
 End If;
--DBMS_OUTPUT.put_line(V_AGENCY_DBTABLENAME||P_FINYEAR);
  
end PROC_GENE_QUOTA_TAB;

--03_PROC_GENE_QUOTA_TAB
