begin
 update p#spf_t_listcondiset
    set modecode = 'CODE_T_SPFCODE',
        modelid  = '52D4D2D9DDF519C6E0533906A8C02483'
  where modecode = 'CODE_T_PROJECTANDSPF'
    and modelid = '14DADAE88B8B57D0E050A8C0210557DD'
    and tableid in (select tableid
                      from dict_t_model a, spf_t_listregist b
                     where a.appid = 'SPF'
                       and a.dealtype = b.dealid);
end;
--主列表左侧树修改专项代码表引用
