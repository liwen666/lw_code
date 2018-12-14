BEGIN
DELETE from fasp_t_diccolumn where tablecode in (select tablecode from fasp_t_dictable where appid in ('bgt', 'bas', 'spf','BGT', 'BAS', 'SPF'))
   and deid not in (select guid from fasp_t_dicde);
 
 
