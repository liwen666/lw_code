BEGIN
DELETE FROM fasp_t_pubmenu WHERE GUID = '4DE51369412A04A9E0533A06A8C0E5B5';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('4DE51369412A04A9E0533A06A8C0E5B5', 2, 1, '1', '005005009', '单位定额公式复制_调整', '113000', '/bgt/exp/quotamanage/formulasetCopy.do?busiTypeID=BGT'||chr(38)||'dealType=2401', 9, '', null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);
END;--creat_menu2