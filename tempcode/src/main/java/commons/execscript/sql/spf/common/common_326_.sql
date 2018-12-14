 BEGIN 
 --二级项目纳入预算主表
 update p#spf_t_condiset  t set t.TABLEID='DDDD82614F624DEF84E88BE30959468D' where t.TABLEID='B9A2EBE169EA4AC49C1C067226A09680';
 update p#spf_t_listcondiset  t set t.TABLEID='DDDD82614F624DEF84E88BE30959468D' where t.TABLEID='B9A2EBE169EA4AC49C1C067226A09680';
 update p#SPF_T_LISTREGIST  t set t.dealid='P4' where t.dealid='5*46';
 update  p#SPF_T_LISTCONDISET t set t.factor='DDDD8264D2124F94A71CB3257BE650AD'  where t.tableid='DDDD82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_DISTRICT_SPF';
 update  p#SPF_T_LISTCONDISET t set t.factor='DDDD6EF4D0B5427D8ADE8310B41DC7B6'  where t.tableid='DDDD82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_FIRAGENCY';
 update  p#SPF_T_LISTCONDISET t set t.factor='DDDDF82D4595458EBF2004592A03206A'  where t.tableid='DDDD82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_PROJECTANDSPF';

 --项目纳入预算主表 
 update p#spf_t_condiset  t set t.TABLEID='AAAA82614F624DEF84E88BE30959468D' where t.TABLEID='EB7A78031EDD4A67BE1085AEAC3380FC';
 update p#spf_t_listcondiset  t set t.TABLEID='AAAA82614F624DEF84E88BE30959468D' where t.TABLEID='EB7A78031EDD4A67BE1085AEAC3380FC';
 update p#SPF_T_LISTREGIST  t set t.dealid='54' where t.dealid='5*42';
 update  p#SPF_T_LISTCONDISET t set t.factor='AAAA8264D2124F94A71CB3257BE650AD'  where t.tableid='AAAA82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_DISTRICT_SPF';
 update  p#SPF_T_LISTCONDISET t set t.factor='AAAA6EF4D0B5427D8ADE8310B41DC7B6'  where t.tableid='AAAA82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_FIRAGENCY';
 update  p#SPF_T_LISTCONDISET t set t.factor='AAAAF82D4595458EBF2004592A03206A'  where t.tableid='AAAA82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_PROJECTANDSPF';

 --项目纳入指标主表
 update p#spf_t_condiset  t set t.TABLEID='BBBB82614F624DEF84E88BE30959468D' where t.TABLEID='254AA211CADD4D9BA8B02CAA3C9692F7';
 update p#spf_t_listcondiset  t set t.TABLEID='BBBB82614F624DEF84E88BE30959468D' where t.TABLEID='254AA211CADD4D9BA8B02CAA3C9692F7';
 update p#SPF_T_LISTREGIST  t set t.dealid='55' where t.dealid='5*43';
 update  p#SPF_T_LISTCONDISET t set t.factor='BBBB8264D2124F94A71CB3257BE650AD'  where t.tableid='BBBB82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_DISTRICT_SPF';
 update  p#SPF_T_LISTCONDISET t set t.factor='BBBB6EF4D0B5427D8ADE8310B41DC7B6'  where t.tableid='BBBB82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_FIRAGENCY';
 update  p#SPF_T_LISTCONDISET t set t.factor='BBBBF82D4595458EBF2004592A03206A'  where t.tableid='BBBB82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_PROJECTANDSPF';

--二级项目纳入指标主表
 update p#spf_t_condiset  t set t.TABLEID='EEEE82614F624DEF84E88BE30959468D' where t.TABLEID='A46BFB4F1F0146CCA3F2E07C7CFC4F8B';
 update p#spf_t_listcondiset  t set t.TABLEID='EEEE82614F624DEF84E88BE30959468D' where t.TABLEID='A46BFB4F1F0146CCA3F2E07C7CFC4F8B';
 update p#SPF_T_LISTREGIST  t set t.dealid='P5' where t.dealid='5*47';
 update  p#SPF_T_LISTCONDISET t set t.factor='EEEE8264D2124F94A71CB3257BE650AD'  where t.tableid='EEEE82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_DISTRICT_SPF';
 update  p#SPF_T_LISTCONDISET t set t.factor='EEEE6EF4D0B5427D8ADE8310B41DC7B6'  where t.tableid='EEEE82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_FIRAGENCY';
 update  p#SPF_T_LISTCONDISET t set t.factor='EEEEF82D4595458EBF2004592A03206A'  where t.tableid='EEEE82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_PROJECTANDSPF';

 
 --二级项目预算调整主表
 update p#spf_t_condiset  t set t.TABLEID='FFFF82614F624DEF84E88BE30959468D' where t.TABLEID='17A453B8A24848F59BBB05FD0BC6C1EC';
 update p#spf_t_listcondiset  t set t.TABLEID='FFFF82614F624DEF84E88BE30959468D' where t.TABLEID='17A453B8A24848F59BBB05FD0BC6C1EC';
 update p#SPF_T_LISTREGIST  t set t.dealid='P6' where t.dealid='5*48';
 update  p#SPF_T_LISTCONDISET t set t.factor='FFFF8264D2124F94A71CB3257BE650AD'  where t.tableid='FFFF82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_DISTRICT_SPF';
 update  p#SPF_T_LISTCONDISET t set t.factor='FFFF6EF4D0B5427D8ADE8310B41DC7B6'  where t.tableid='FFFF82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_FIRAGENCY';
 update  p#SPF_T_LISTCONDISET t set t.factor='FFFFF82D4595458EBF2004592A03206A'  where t.tableid='FFFF82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_PROJECTANDSPF';

 
--项目预算调整主表
 update p#spf_t_condiset  t set t.TABLEID='CCCC82614F624DEF84E88BE30959468D' where t.TABLEID='9D945BDE3F3847AAB682207578D2F7A0';
 update p#spf_t_listcondiset  t set t.TABLEID='CCCC82614F624DEF84E88BE30959468D' where t.TABLEID='9D945BDE3F3847AAB682207578D2F7A0';
 update p#SPF_T_LISTREGIST  t set t.dealid='56' where t.dealid='5*45';
 update  p#SPF_T_LISTCONDISET t set t.factor='CCCC8264D2124F94A71CB3257BE650AD'  where t.tableid='CCCC82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_DISTRICT_SPF';
 update  p#SPF_T_LISTCONDISET t set t.factor='CCCC6EF4D0B5427D8ADE8310B41DC7B6'  where t.tableid='CCCC82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_FIRAGENCY';
 update  p#SPF_T_LISTCONDISET t set t.factor='CCCCF82D4595458EBF2004592A03206A'  where t.tableid='CCCC82614F624DEF84E88BE30959468D' and t.modecode='CODE_T_PROJECTANDSPF';

UPDATE P#dict_t_factor set isvisible = '0'  where   tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='ISINDEX' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='CITYNUM' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='COUNTYAGNUM' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='CITYAGNUM' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='PROVNUM' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='PROVAGNUM' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='COUNTYNUM' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='AGENCYID' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='FIRAGENCYID' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='SPFNAME' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='PROJCODE' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='PROJNAME' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='DISTRICTID' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6')); 
UPDATE P#dict_t_factor set isvisible = '1'  where DBCOLUMNNAME='PROJGROUP_NAME' and  tableid in(select tableid from dict_t_model where dealtype in('54','55','56','P4','P5','P6'));

 END;
 
 --修改主列表条件设置
 