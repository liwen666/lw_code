CREATE OR REPLACE PACKAGE PKG_BGT IS

  -- AUTHOR  : HSC
  -- Created : 2015/4/28 15:26:11
  -- Purpose : 预算用过程和函数

  -- Public type declarations
  --type <TypeName> is <Datatype>;

  -- Public constant declarations
  --<ConstantName> constant <Datatype> := <Value>;

  -- Public variable declarations
  --<VariableName> <Datatype>;

  -- Public function and procedure declarations
  --function <FunctionName>(<Parameter> <Datatype>) return <Datatype>;
  PROCEDURE QUOTASET_P_AGENCY_STND(P_SRCAGENYID VARCHAR2,P_DESTAGENCYID CLOB,P_STNDID VARCHAR2);
  FUNCTION F_GETTASKID_BY_DOCID(P_DOCIDS CLOB) RETURN VARCHAR2;
  PROCEDURE P_INIT_ZCXMMXB(P_AGENCYID VARCHAR2);
  PROCEDURE P_BGT_DATA(P_ENTRY CHAR DEFAULT '0',P_FLAG CHAR DEFAULT '0',P_ID CLOB,P_RESULT OUT SYS_REFCURSOR);

END PKG_BGT;--PKG_BGT_HEAD
