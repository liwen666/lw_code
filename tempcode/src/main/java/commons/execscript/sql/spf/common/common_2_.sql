begin
  EXECUTE IMMEDIATE Q'{
    create or replace trigger TR_P#SPF_T_ASSIGNSPF
     before insert or update ON SPF_T_ASSIGNSPF FOR EACH ROW
    begin
     if inserting then
       :new.dbversion:=case when to_char(:new.dbversion,'yyyy-mm-dd') ='2012-01-01' then to_date('2012-01-01','yyyy-mm-dd') else sysdate end;
     end if;
     if (to_char(:new.dbversion,'yyyy-mm-dd') ='2012-01-01' and ((updating and updating('dbversion')) or Inserting )) then
       return ;
      end if;
     if updating then
       :new.dbversion:=sysdate;
     end if;
    end  TR_P#SPF_T_ASSIGNSPF;}';
  SYS_P_PARTITION_TABLE('SPF_T_ASSIGNSPF');
end;
--����һ_20150925_����ר���·����ñ�����Ȼ��ˢ�ɷ�����
