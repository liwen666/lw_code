BEGIN
delete 
  from P#dict_t_factorcode
 where tableid in (select tableid
                     from P#dict_t_modelcode
                    where dbtablename = 'CODE_T_PROJECTANDSPF')
   and dbcolumnname not in
       (select column_name
          from user_tab_columns
         where table_name = 'CODE_T_PROJECTANDSPF');
END;
--�޸�CODE_T_PROJECTANDSPF
