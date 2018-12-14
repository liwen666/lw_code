BEGIN
update P#SPF_T_CONDISET   
       set     SENTENCES= '(SETUPSTATUS = ''1'' or SETUPSTATUS = ''2'') and ADJUSTSTATUS in ($STATUS$) and spfid in (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$ and $flowTable$.STEP =''adjust'')'
       where GUID='3CD3D1ECA7AF1824E053CB01A8C0F6E0';
  
update P#SPF_T_CONDISET  
        set  SENTENCES= '(SETUPSTATUS = ''1'' or SETUPSTATUS = ''2'') and ADJUSTSTATUS in ($STATUS$) and spfid in (SELECT OBJECTID FROM $flowTableName$  $flowTable$ WHERE $flowWhere$ and $flowTable$.STEP = ''adjust'')' 
       where guid='2680E1CC2A88404E838D1A0F81194784';
end;
--dyg修改主列表条件
