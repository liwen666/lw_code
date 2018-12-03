
/* 20180316_mfl_初始化默认的简版版本模式信息*/
declare
  i          integer;
  keyValue   varchar2(255) := 'default_version_mode_';
  valueValue number(1) := 1;--0全版/1简版
begin
  select count(*)
    into i
    from ACT_HQ_CONFIG
   where key_ = 'default_version_mode_';
  if i = 0 then
    execute immediate 'insert into ACT_HQ_CONFIG(key_, value2_) values(:2,:3)'
      using keyValue, valueValue;
  end if;
  
  commit;
end;

