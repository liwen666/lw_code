BEGIN
DELETE FROM Bd_t_Handlebean WHERE DATAKEY IN ('4CDFACE9946F5824E0533A06A8C02388','4CDFACE9946F5824E0533A06A8C02399');
insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values
   (
        '4CDFACE9946F5824E0533A06A8C02388','3','noRepeatIssuedRangeService','下发范围（控制不能重复下发）','BGT','1' 
     );
     insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values(
      '4CDFACE9946F5824E0533A06A8C02399','3','noRepeatIssuedRangeService','下发范围（控制不能重复下发）','BAS','1' );
 END;     
 
--添加下发handler（控制不能重复下发）170502_zz
