begin
update dict_t_factor t
   set t.DATATYPE = '3', t.SHOWFORMAT = '0',T.CSID = null
 where t.COLUMNID ='CEED2030581D4183BAFEA0AA3F6BCEE5';
end;--修改一级专项物理表isleaf字段
