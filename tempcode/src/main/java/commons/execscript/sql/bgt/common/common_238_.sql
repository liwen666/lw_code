CREATE OR REPLACE FUNCTION TRA_F_CNLOWCASE(v_Sour varchar2)
RETURN VARCHAR2
AS
   v_Ret varchar2(4000);
BEGIN
   v_Ret := v_Sour;
   v_Ret := Replace(v_Ret,'��','(');
   v_Ret := Replace(v_Ret,'��',')');
   v_Ret := Replace(v_Ret,'��',' ');
   v_Ret := Replace(v_Ret,'��','1');
   v_Ret := Replace(v_Ret,'��','2');
   v_Ret := Replace(v_Ret,'��','3');
   v_Ret := Replace(v_Ret,'��','4');
   v_Ret := Replace(v_Ret,'��','5');
   v_Ret := Replace(v_Ret,'��','6');
   v_Ret := Replace(v_Ret,'��','7');
   v_Ret := Replace(v_Ret,'��','8');
   v_Ret := Replace(v_Ret,'��','9');
   v_Ret := Replace(v_Ret,'��','0');
   v_Ret := Replace(v_Ret,'��','[');
   v_Ret := Replace(v_Ret,'��',']');
   RETURN v_Ret;
END;
--1-ת��֧������-05121059 - ��
