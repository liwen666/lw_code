
begin
   DELETE FROM bd_t_busijsurl where datakey='4DFB599D5FDF46FCE0533A06A8C08C13';
   DELETE FROM bd_t_busijsurl where datakey='4DFB599D5FE046FCE0533A06A8C08C13';
   
   insert into bd_t_busijsurl(datakey,name,value,appid,type,status) values(
       '4DFB599D5FDF46FCE0533A06A8C08C13','纵向办结业务主体','static/app/exp/external/objectSelect4EndProcess.js',
       'BAS','11','1'
   );
   insert into bd_t_busijsurl(datakey,name,value,appid,type,status) values(
       '4DFB599D5FE046FCE0533A06A8C08C13','纵向办结业务主体','static/app/exp/external/objectSelect4EndProcess.js',
       'BGT','11','1'
   );
   end;
--添加办结选择业务主体js_zz_170426
