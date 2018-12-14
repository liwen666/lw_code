                          
DECLARE 
 isExis INT;
BEGIN
  SELECT COUNT(1) INTO isExis FROM user_views WHERE view_name ='SPF_V_FAUDITOBJYJ';
  IF isExis>0 THEN
     execute immediate Q'{DROP VIEW SPF_V_FAUDITOBJYJ}';
  END IF;
END;
--É¾³ýSPF_V_FAUDITOBJXJ
