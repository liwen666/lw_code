
begin
   DELETE FROM bd_t_busijsurl where datakey='1B629CA27E892D9CE0533A06A8C01D11';
   DELETE FROM bd_t_busijsurl where datakey='1B629CA27E892D9CE0533A06A8C01D22';
   
   insert into bd_t_busijsurl(datakey,name,value,appid,type,status) values(
       '1B629CA27E892D9CE0533A06A8C01D11','�鿴�ѷ��͵�Ŀ�굥λ','static/app/exp/external/objectView4HasSend.gzjs',
       'BAS','12','1'
   );
   insert into bd_t_busijsurl(datakey,name,value,appid,type,status) values(
       '1B629CA27E892D9CE0533A06A8C01D22','�鿴�ѷ��͵�Ŀ�굥λ','static/app/exp/external/objectView4HasSend.gzjs',
       'BGT','12','1'
   );
   end;
--��Ӳ鿴�ѷ���Ŀ�굥λjs_zz_170428
