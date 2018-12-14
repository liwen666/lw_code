begin 
   DELETE FROM bd_t_handlebean where datakey in ('5044666BD4426B9EE0533A06A8C0E891','5044666BD4436B9EE0533A06A8C0E891','5044666BD4446B9EE0533A06A8C0E891','5044666BD4456B9EE0533A06A8C0E891');
   insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values
   (
        '5044666BD4426B9EE0533A06A8C0E891','23','autoBackupReportService','上报自动备份','BAS','1' 
     );
   insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values(
    '5044666BD4436B9EE0533A06A8C0E891','23','autoBackupReportService','上报自动备份','BGT','1' );
   insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values(
     '5044666BD4446B9EE0533A06A8C0E891','24','autoBackupIssuedService','下发自动备份','BAS','1' );
   insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values(
    '5044666BD4456B9EE0533A06A8C0E891','24','autoBackupIssuedService','下发自动备份','BGT','1' );
 end;
--添加上报下发自动备份handler20170524_zz
