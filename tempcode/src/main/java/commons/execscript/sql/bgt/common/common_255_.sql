BEGIN
DELETE FROM Bd_t_Handlebean WHERE DATAKEY IN ('2CDFACE9949D5824E0533A06A8C0EF11','2CDFACE9949D5824E0533A06A8C0EF22');

insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) 
   SELECT  '2CDFACE9949D5824E0533A06A8C0EF11','25','defaultTitleParamService','����Ĭ�ϱ���','BGT','1'  FROM DUAL
     UNION ALL
   SELECT '2CDFACE9949D5824E0533A06A8C0EF22','25','defaultTitleParamService','����Ĭ�ϱ���','BAS','1'   FROM DUAL;
    
 END;     
--��ӻ���Ĭ�ϱ���handler20170526_zz
