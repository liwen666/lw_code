BEGIN
  UPDATE p#Dict_t_Factor SET DEID = '' WHERE TABLEID IN (SELECT MAINTABID FROM P#DICT_T_SETMAINSUBRELA WHERE STATUS = '1') AND DBCOLUMNNAME = 'DATAKEY';
  UPDATE p#Dict_t_Factor SET DEID = '080001', NULLABLE = '0' WHERE COLUMNID IN (SELECT MAINFKID FROM P#DICT_T_SETMAINSUBRELA WHERE STATUS = '1');
  UPDATE p#Dict_t_Factor SET DEID = '' WHERE TABLEID IN (SELECT SUBTABID FROM P#DICT_T_SETMAINSUBRELA WHERE STATUS = '1') AND DBCOLUMNNAME = 'DATAKEY';
  UPDATE p#Dict_t_Factor SET DEID = '080001', NULLABLE = '0' WHERE COLUMNID IN (SELECT FKID FROM P#DICT_T_SETMAINSUBRELA WHERE STATUS = '1');
END;
--修改主子表主外键数据元
