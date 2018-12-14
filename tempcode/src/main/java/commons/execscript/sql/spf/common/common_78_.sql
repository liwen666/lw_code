begin
EXECUTE IMMEDIATE Q'{alter table P#SPF_T_PROJECTTYPE modify (PROJTYPE char(1))}';
end;
--修改列PROJTYPE数据类型
