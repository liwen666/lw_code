DECLARE
is_exists INT;
BEGIN
UPDATE P#dict_t_model SET isbak ='0' WHERE dealtype ='5*04';
SELECT COUNT(1) INTO is_exists FROM USER_Views WHERE view_name ='SPF_T_PATTACH_BAK';
IF is_exists > 0 THEN
EXECUTE IMMEDIATE 'DROP VIEW SPF_T_PATTACH_BAK';
END IF;
SELECT COUNT(1) INTO is_exists FROM User_Tables WHERE table_name ='P#SPF_T_PATTACH_BAK';
IF is_exists > 0 THEN
EXECUTE IMMEDIATE 'DROP TABLE P#SPF_T_PATTACH_BAK';
END IF;
END;
--ɾ����Ŀ��������Ҫ���±���
