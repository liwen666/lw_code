DECLARE
  v_i NUMBER(10);
BEGIN
  SELECT COUNT(1) INTO v_i FROM User_Tab_Columns WHERE table_Name = 'P#CDT_T_TASKTYPEMODEL' AND column_Name = 'CONVERTSQL';
  IF v_i = 0 THEN
    EXECUTE IMMEDIATE 'ALTER TABLE P#CDT_T_TASKTYPEMODEL ADD CONVERTSQL CLOB';
  END IF;
  EXECUTE IMMEDIATE Q'{COMMENT ON COLUMN P#CDT_T_TASKTYPEMODEL.CONVERTSQL IS 'ת����������'}';
  EXECUTE IMMEDIATE Q'{CREATE OR REPLACE VIEW CDT_T_TASKTYPEMODEL AS
                              SELECT  GUID,TASKTYPEID,MODELID,DISTRICTID,ORDERID,STATUS, DBVERSION,REPCONDITION,ANGLECODETAB,ISASSIGN,ASSIGNCONDITION,CONVERTSQL 
                              FROM P#CDT_T_TASKTYPEMODEL 
                              WHERE  STATUS='1'}';

END;
--��ϲ÷__�������Ͷ�Ӧ¼������Ӻ���sql�ֶ�