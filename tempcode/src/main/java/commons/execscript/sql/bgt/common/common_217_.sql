begin 
   DELETE FROM bd_t_handlebean where datakey in ('4CDFACE9946F5824E0533A06A8C02346','4CDFACE9946F5824E0533A06A8C02376');
   insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values
   (
        '4CDFACE9946F5824E0533A06A8C02346','17','endProcessLogService','办结记录日志','BAS','1' 
     );
     insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values(
      '4CDFACE9946F5824E0533A06A8C02376','17','endProcessLogService','办结记录日志','BGT','1' );
      
 end;
--添加办结handlebean_zz_170426
