begin
--ȥ���б������õ��ֶ�
  UPDATE DICT_T_FACTOR
     SET CSID = NULL, DATATYPE = '3', SHOWFORMAT = '0'
   WHERE COLUMNID IN
         (SELECT COLUMNID
            FROM DICT_T_FACTOR V
           WHERE V.DBCOLUMNNAME IN ('PROJTYPEID', 'EXPFUNCID', 'DEPTID','AGENCYNAME')
             AND TABLEID IN ��SELECT TABLEID FROM DICT_T_MODEL
           WHERE APPID = 'SPF'
             AND NAME LIKE '%����%' ��);
end;--ȥ�������������
