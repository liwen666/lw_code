BEGIN
DELETE FROM P#SPF_T_FSPFSTEP WHERE GUID ='D3E627141DF14954BD290C7D63EBA253';
for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
  insert into P#SPF_T_FSPFSTEP (YEAR, PROVINCE,BGTLVL, CODE, DBVERSION, GUID, NAME, ORDERID, STATUS)
  values (v_row.YEAR, v_row.DISTRICTID,null, 'kzsadjust', null, 'D3E627141DF14954BD290C7D63EBA253', '控制数调整', 11, '1');
END LOOP;
DELETE FROM Fasp_t_Pubmenu WHERE GUID='36DDF8575D163CC4E050A8C0F0004A26';
insert into Fasp_t_Pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('36DDF8575D163CC4E050A8C0F0004A26', 2, 1, '1', '004001011', '专项控制数调整', '120001', '/spf/spf/input/inputMain.do?style=2'||CHR(38)||'dealtype=40'||CHR(38)||'appid=SPF'||CHR(38)||'spfmType=1'||CHR(38)||'isTemp=0'||CHR(38)||'isreadonly=0'||CHR(38)||'step=kzsadjustto', 12, 'remark', null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null);

END;
--添加控制数调整阶段
