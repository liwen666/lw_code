declare
  v_n number(6) :=0;
BEGIN 
  SELECT count(1) into v_n FROM user_tab_columns WHERE upper(TABLE_NAME)='P#BIL_T_BILLTYPE' and upper(column_name)='FINYEAR'; 
   --P#BIL_T_BILLTYPE����FINYEAR�ֶ�
  if v_n = 0 then
     execute immediate Q'{alter table P#BIL_T_BILLTYPE add FINYEAR char(4)}';
     execute immediate Q'{create or replace view bil_t_billtype as
             select BILLTYPEID,BILLTYPENAME,CODE,DBVERSION,ISLEAF,LEVELNO,ORDERID,STATUS,SUPERID ,FINYEAR 
             from P#BIL_T_BILLTYPE where PROVINCE=Global_MultYear_CZ.Secu_f_GLOBAL_PARM('DIVID') AND STATUS='1'}';
     execute immediate Q'{UPDATE p#bil_t_billtype SET finyear = YEAR}';
  end if;
  --P#BIL_T_BILLTYPE�Ǽ�Ϊ������ȷ���
  UPDATE DICT_T_SETTINGTABINFO SET YEARPART = '0'  WHERE  dbtablename = 'P#BIL_T_BILLTYPE';
  --�������еĵ��ݲ˵�����appID
    UPDATE Fasp_t_Pubmenu SET URL = URL ||'?appID=BIL' WHERE CODE IN('005009002001','005009002002','005009002003') AND URL NOT LIKE '%appID=BIL%';  
  UPDATE Fasp_t_Pubmenu SET URL = URL || CHR(38)||'appID=BIL' WHERE URL LIKE '%bgt/bil/datainput%' AND URL NOT LIKE '%appID=BIL%';  
END;

--��������BIL_T_BILLTYPE����FINYEAR�ֶ�&���Ӳ˵�appID160713
