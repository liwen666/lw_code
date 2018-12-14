 
--专项资金备份版本  显示备份时间列
BEGIN
update dict_t_factor t
   set t.ISVISIBLE = '1'
 where t.TABLEID = (select t1.TABLEID from dict_t_model t1 where t1.DBTABLENAME ='SPF_V_FBAKLOG')
   and t.DBCOLUMNNAME = 'DBVERSION';
--专项资金备份   隐藏无需显示的列
update dict_t_factor
   set ISVISIBLE = '0'
 where COLUMNID in ('C656CAA932584BFDA07F6731CCBB5049',
                    'F0057A2C25E54AD39D82BNH80C5A5795',
                    '225EF844364FFR57B77C2B9A76E987D9',
                    '73ACAEDDR5F141928914E7540B1F297E',
                    '1A2A01BEA7AB4D7C9NNGGE7A7FD4F46D'); 
--项目备份数据 隐藏无需显示的列
update dict_t_factor set ISVISIBLE = '0'
 where COLUMNID in ('BN4T06A11D0E4A7AB7C6B61A230E87D0',
                   'CVB7F15D796046BE84E650E853B2C4A1',
                   '225EF844364FFR57B77C2B9A76E987D9',
                   '73ACAEDDR5F141928914E7540B1F297E',
                    'U5TH3B49BB5B460889B15FC40E913123');
UPDATE dict_t_factor SET datatype ='3',csid = NULL ,showformat ='0' WHERE DBCOLUMNNAME ='SETUPSTATUS';
--删除多余的专项下发菜单
DELETE FROM fasp_t_pubmenu WHERE GUID in ('7AB2071676FE489EA6F8DE6C2C1B9869');
END;
--DF_专项资金备份版本
