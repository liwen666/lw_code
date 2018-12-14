
begin
   DELETE FROM bd_t_busijsurl where datakey='1B629CA27E892D9CE0533A06A8C01D11';
   DELETE FROM bd_t_busijsurl where datakey='1B629CA27E892D9CE0533A06A8C01D22';
   
   insert into bd_t_busijsurl(datakey,name,value,appid,type,status) values(
       '1B629CA27E892D9CE0533A06A8C01D11','查看已发送的目标单位','static/app/exp/external/objectView4HasSend.gzjs',
       'BAS','12','1'
   );
   insert into bd_t_busijsurl(datakey,name,value,appid,type,status) values(
       '1B629CA27E892D9CE0533A06A8C01D22','查看已发送的目标单位','static/app/exp/external/objectView4HasSend.gzjs',
       'BGT','12','1'
   );
   end;
--添加查看已发送目标单位js_zz_170428
