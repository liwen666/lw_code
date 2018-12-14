DECLARE
  v_i NUMBER(2);
BEGIN
  SELECT COUNT(1) INTO v_i FROM USER_CONSTRAINTS WHERE CONSTRAINT_NAME = 'PK_P#EXP_S_PROJINVEST';
  IF v_i > 0 THEN 
    EXECUTE IMMEDIATE 'alter table P#EXP_S_PROJINVEST drop constraint PK_P#EXP_S_PROJINVEST cascade';
  END IF;
  
  SELECT COUNT(1) INTO v_i FROM USER_INDEXES WHERE INDEX_NAME = 'PK_P#EXP_S_PROJINVEST';
  IF v_i > 0 THEN 
    EXECUTE IMMEDIATE 'drop index PK_P#EXP_S_PROJINVEST';
  END IF;
  
  EXECUTE IMMEDIATE 'alter table P#EXP_S_PROJINVEST add constraint PK_P#EXP_S_PROJINVEST primary key (PROVINCE, YEAR, STATUS, FUNDSOURCEID, GUID)';
END;
--修改资金来源对应物理列设置表主键20151123郑桥
