DECLARE
  IS_EXISTS INT;
BEGIN
  DELETE FROM P#DICT_T_FACTOR WHERE TABLEID = '24B3A23BC4960040E050A8C0210532D5' AND DBCOLUMNNAME = 'DATAKEY'; 
  SYS_P_RECREATE_VIEWS('24B3A23BC4960040E050A8C0210532D5');
  SELECT COUNT(1)
    INTO IS_EXISTS
    FROM USER_TAB_COLUMNS
   WHERE TABLE_NAME = 'P#SPF_T_CHECKRESULT'
     AND COLUMN_NAME = 'DATAKEY';
  IF IS_EXISTS > 0 THEN
   EXECUTE IMMEDIATE 'ALTER TABLE P#SPF_T_CHECKRESULT DROP(DATAKEY)';
  END IF;
  DELETE FROM P#DICT_T_FACTOR WHERE TABLEID = '24B3A23BC4A70040E050A8C0210532D5' AND DBCOLUMNNAME = 'DATAKEY'; 
  SYS_P_RECREATE_VIEWS('24B3A23BC4A70040E050A8C0210532D5');
  SELECT COUNT(1)
    INTO IS_EXISTS
    FROM USER_TAB_COLUMNS
   WHERE TABLE_NAME = 'P#SPF_T_CHECKERROR'
     AND COLUMN_NAME = 'DATAKEY';
  IF IS_EXISTS > 0 THEN
   EXECUTE IMMEDIATE 'ALTER TABLE P#SPF_T_CHECKERROR DROP(DATAKEY)';
  END IF;
END;
--删除审核结果和明细表DATAKEY