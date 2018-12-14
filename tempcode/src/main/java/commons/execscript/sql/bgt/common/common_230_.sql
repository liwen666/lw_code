BEGIN
DELETE FROM Bd_t_Handlebean WHERE DATAKEY IN ('4CDFACE9946F5824E0533A06A8C01234','4CDFACE9946F5824E0533A06A8C05678');
insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values
   (
        '4CDFACE9946F5824E0533A06A8C01234','1','cIssuedWithoutChildRecordLogService','纵向下发记录日志（不带下级）','BGT','1' 
     );
     insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values(
      '4CDFACE9946F5824E0533A06A8C05678','1','cIssuedWithoutChildRecordLogService','纵向下发记录日志（不带下级）','BAS','1' );
 END;     
 
--添加纵向下发（不带下级）记录日志handler_170505_zz
