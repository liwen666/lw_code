begin
update P#dict_t_model set ISPARTITION ='1',ISALLDISTRICT = '1',ISALLYEAR ='0' where tableid ='4C8F6FFB6F621B0AE0533A06A8C048EE';
update P#dict_t_model set ISPARTITION ='1',ISALLDISTRICT = '1',ISALLYEAR ='0' where tableid ='4C8F62C7CD581AACE0533A06A8C0993B';
update dict_t_settingtabinfo set PROVINCEPART ='1' where guid in('58877142369444D2BFC56170F7D77F8E','1C0DEACBED854968B641C772CABB2DD1');
update dict_t_settingtabinfo set initialize ='0' where GUID in('2C92F7242C46F3C9E050A8C0210562A1',
'2C92F7242C4CF3C9E050A8C0210562A1','2C92F7242C54F3C9E050A8C0210562A1');
sys_p_recreate_views('4C8F6FFB6F621B0AE0533A06A8C048EE');
sys_p_recreate_views('4C8F62C7CD581AACE0533A06A8C0993B');
UPDATE P#DICT_T_FACTOR
   SET CSID = '223267AA7D472B22E050A8C021057ED5'
 WHERE COLUMNID = '95B108EA16944569BB795B12556C6A80';
end;
--�޸Ļ������