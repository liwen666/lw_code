begin 
   DELETE FROM bd_t_handlebean where datakey in ('4CDFACE9946F5824E0533A06A8C02346','4CDFACE9946F5824E0533A06A8C02376');
   insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values
   (
        '4CDFACE9946F5824E0533A06A8C02346','17','endProcessLogService','����¼��־','BAS','1' 
     );
     insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values(
      '4CDFACE9946F5824E0533A06A8C02376','17','endProcessLogService','����¼��־','BGT','1' );
      
 end;
--��Ӱ��handlebean_zz_170426
