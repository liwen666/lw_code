begin 
update P#dict_t_setrec
   set typeflag = '1'
 where typeflag is null
   and tableid in
       (SELECT tableid
          FROM dict_t_model
         where appid = 'SPF'
           and tabletype = '1'
           and tableid in (SELECT tableid
                             FROM dict_t_factor
                            where DBCOLUMNNAME = 'PROJECTID'));
update P#dict_t_setrec set typeflag = '0' where typeflag is null;
                            
end;
--��ʼ��dict_t_setrec��typeflag�ű�
