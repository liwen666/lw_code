
begin
   DELETE FROM bd_t_busijsurl where datakey IN ('4DFB599D5FE046FCE0533A06A8C08C99','4DFB599D5FE046FCE0533A06A8C08C66');
   
   insert into bd_t_busijsurl(datakey,name,value,appid,type,status) values(
       '4DFB599D5FE046FCE0533A06A8C08C99','�������ҵ������','static/app/exp/external/objectView4BFlowServiceArea.gzjs',
       'BGT','8','1'
   );
   insert into bd_t_busijsurl(datakey,name,value,appid,type,status) values(
       '4DFB599D5FE046FCE0533A06A8C08C66','�������ҵ������','static/app/exp/external/objectView4BFlowServiceArea.gzjs',
       'BAS','8','1'
   );
   
end;
--��Ӻ������ҵ������js20170526_zz
