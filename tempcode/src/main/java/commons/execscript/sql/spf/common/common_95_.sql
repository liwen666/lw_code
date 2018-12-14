DECLARE
   v_isExists VARCHAR2(5);
BEGIN
    SELECT COUNT(NAME) into v_isExists FROM USER_SOURCE WHERE TYPE = 'PROCEDURE' and name ='P_KEEP_SPF_ACCOUNTS';
    IF v_isExists > 0 THEN
     execute immediate Q'{DROP PROCEDURE P_KEEP_SPF_ACCOUNTS}';
    END IF;
    SELECT COUNT(NAME) into v_isExists FROM USER_SOURCE WHERE TYPE = 'PROCEDURE' and name ='SYS_P_KEEP_ACCOUNTS';
    IF v_isExists > 0 THEN
      execute immediate Q'{DROP PROCEDURE SYS_P_KEEP_ACCOUNTS}';
    END IF;
    SELECT COUNT(NAME) into v_isExists FROM USER_SOURCE WHERE TYPE = 'PROCEDURE' and name ='SPF_AND_INDEX_INTERFACE';
    IF v_isExists > 0 THEN
      execute immediate Q'{DROP PROCEDURE SPF_AND_INDEX_INTERFACE}';
    END IF;
END;
--É¾³ý¶àÓà´æ´¢¹ý³Ì
