DECLARE
  I VARCHAR2(10);
BEGIN
  SELECT GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_SETPARM('',
                                                  PKG_CONSTANTS.GETDEFAULTPROVINCE,
                                                  '2017',
                                                  PKG_CONSTANTS.GETDEFAULTPROVINCE ||
                                                  '2017')
    INTO I
    FROM DUAL;

  UPDATE BGT_T_CFLOW_ACT T
     SET STATUS = '9'
   WHERE T.TASKID IN ('C61696A7874C49A8854E479E189D0BDF',
                      '91EB5160DF8F44868039218BD8563729',
                      '51D48ABF6F8C4A7BAC5EAFA4FB7AA136')
     AND T.OBJECTID NOT IN (SELECT GUID FROM CODE_T_DISTRICT)
     AND PKG_CONSTANTS.GETDEFAULTPROVINCE <> '8700';

  UPDATE BGT_T_CFLOW_ACT T
     SET STATUS = '9'
   WHERE T.TASKID IN ('C61696A7874C49A8854E479E189D0BDF',
                      '91EB5160DF8F44868039218BD8563729',
                      '51D48ABF6F8C4A7BAC5EAFA4FB7AA136')
     AND T.WFDIRECTION IN ('0', '2')
     AND T.SOURCEAGENCYID <> 'B5BBAB08B9994532B6DE3E296DCD579D'
     AND PKG_CONSTANTS.GETDEFAULTPROVINCE = '8700';

  UPDATE BGT_T_CFLOW_ACT T
     SET STATUS = '9'
   WHERE T.TASKID IN ('C61696A7874C49A8854E479E189D0BDF',
                      '91EB5160DF8F44868039218BD8563729',
                      '51D48ABF6F8C4A7BAC5EAFA4FB7AA136')
     AND T.WFDIRECTION = '1'
     AND T.TARGETAGENCYID <> 'B5BBAB08B9994532B6DE3E296DCD579D'
     AND PKG_CONSTANTS.GETDEFAULTPROVINCE = '8700';

END;

--删除纵向日志表垃圾数据
