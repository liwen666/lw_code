  BEGIN
    UPDATE  P#DICT_T_MODEL SET ISALLDISTRICT ='0' WHERE dealtype IN('4*01','5*01') AND tabletype ='1';
    FOR v_ROWS IN ( SELECT TABLEID FROM DICT_T_MODEL WHERE dealtype IN('4*01','5*01') AND tabletype ='1') LOOP
      sys_p_recreate_views(v_ROWS.TABLEID);
    END LOOP;
    END;
--ר���Ŀ�����Ϊ��ȫ��������
