BEGIN
DELETE FROM Bd_t_Handlebean WHERE DATAKEY IN ('4CDFACE9946F5824E0533A06A8C01234','4CDFACE9946F5824E0533A06A8C05678');
insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values
   (
        '4CDFACE9946F5824E0533A06A8C01234','1','cIssuedWithoutChildRecordLogService','�����·���¼��־�������¼���','BGT','1' 
     );
     insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values(
      '4CDFACE9946F5824E0533A06A8C05678','1','cIssuedWithoutChildRecordLogService','�����·���¼��־�������¼���','BAS','1' );
 END;     
 
--��������·��������¼�����¼��־handler_170505_zz
