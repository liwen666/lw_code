BEGIN
DELETE FROM Bd_t_Handlebean WHERE DATAKEY IN ('4CDFACE9946F5824E0533A06A8C02388','4CDFACE9946F5824E0533A06A8C02399');
insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values
   (
        '4CDFACE9946F5824E0533A06A8C02388','3','noRepeatIssuedRangeService','�·���Χ�����Ʋ����ظ��·���','BGT','1' 
     );
     insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values(
      '4CDFACE9946F5824E0533A06A8C02399','3','noRepeatIssuedRangeService','�·���Χ�����Ʋ����ظ��·���','BAS','1' );
 END;     
 
--����·�handler�����Ʋ����ظ��·���170502_zz
