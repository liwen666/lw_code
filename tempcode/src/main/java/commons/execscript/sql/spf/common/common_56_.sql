--修改初始化脚本主表是否逻辑主键
BEGIN 
  UPDATE DICT_T_FACTOR SET ISKEY ='1' WHERE COLUMNID IN(
SELECT COLUMNID
  FROM DICT_T_FACTOR
 WHERE DBCOLUMNNAME = 'DATAKEY'
   AND TABLEID IN
       (SELECT TABLEID
          FROM DICT_T_MODEL
         WHERE DEALTYPE IN ('4*01', '3*01', '5*01', 'P*01')));
END;
--修改初始化脚本主表是否逻辑主键
