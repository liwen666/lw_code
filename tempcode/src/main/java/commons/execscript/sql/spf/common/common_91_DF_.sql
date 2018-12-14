BEGIN        
UPDATE dict_t_factor fa SET fa.DEFAULTVALUE='sys_guid()'
 where fa.TABLEID =
       (select tableid
          from dict_t_model t
         where t.DBTABLENAME = 'SPF_T_PROJECTTYPE') AND fa.DBCOLUMNNAME = 'DATAKEY';
         
UPDATE dict_t_factor fa SET fa.DEFAULTVALUE='sys_guid()'
 where fa.TABLEID =
       (select tableid
          from dict_t_model t
         where t.DBTABLENAME = 'SPF_T_PROJECTTYPE') AND fa.DBCOLUMNNAME = 'PROJTYPEID';
         
         
UPDATE dict_t_factor fa SET fa.CSID='148830CAF3E61F78E050A8C021056ABE',fa.DEFAULTVALUE=chr(39)||1||chr(39)
 where fa.TABLEID =
       (select tableid
          from dict_t_model t
         where t.DBTABLENAME = 'SPF_T_PROJECTTYPE') AND fa.DBCOLUMNNAME = 'PROJTYPE';
END;
--DF_liukai修改一级专项默认列
