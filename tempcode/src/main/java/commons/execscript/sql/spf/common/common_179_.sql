BEGIN
  update dict_t_factor
   set csid = null
 where csid not in (select tableid from dict_t_modelcode);
 
update dict_t_factor
   set datatype = '3',
       SHOWFORMAT = '0'
 where datatype = '6'
   and csid is null; 
END;
--绩效_验证modelfactor问题
