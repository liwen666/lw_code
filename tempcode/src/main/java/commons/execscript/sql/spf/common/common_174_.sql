begin 
delete from fasp_t_pubmenu where guid = '7A08A9B9231B405BA6F1435F66DE5178';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('7A08A9B9231B405BA6F1435F66DE5178', 1, 0, '1', '006006', 'º®–ßΩ◊∂Œ…Ë÷√', '22006', '', 1, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);


delete from fasp_t_pubmenu where guid = '7A18A9B9231B405BA6F1435F66DE5178';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('7A18A9B9231B405BA6F1435F66DE5178', 2, 0, '1', '006004001', 'º®–ßΩ◊∂Œ…Ë÷√', '7A08A9B9231B405BA6F1435F66DE5178', '/spf/kpi/setting/performance/performance.do', 1, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);


end ;
--º®–ß_WTF_º®–ßΩ◊∂Œ≤Àµ•
