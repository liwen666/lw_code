DECLARE
  v_cnt NUMBER(1);
BEGIN
  SELECT COUNT(1) INTO v_cnt FROM user_ind_columns WHERE index_name = 'IDX_SPF_T_CHOOSEMID'
         AND table_name  = 'P#SPF_T_CHOOSEMID';
  
  IF v_cnt = 0 THEN
     EXECUTE immediate Q'{ create index IDX_SPF_T_CHOOSEMID on P#SPF_T_CHOOSEMID (DOCID) }'; 
  END IF;
END;
--WJP_1201_SPF_T_CHOOSEMIDÔö¼ÓDOCIDË÷Òý
