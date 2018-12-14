BEGIN
DELETE FROM fasp_t_pubmenu WHERE GUID IN('282E476639F820DBE050A8C0210654AE','3302E92C350BBD3AE0530100007F7598','3302E92C350CBD3AE0530100007F7598');
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('3302E92C350BBD3AE0530100007F7598', 2, 1, '1', '004002026', '批量申报代录项目', '120002', '/spf/spf/proj/input/inputMain.do?style=2'||CHR(38)||'projmType=1'||CHR(38)||'dealtype=50'||CHR(38)||'spfchsdealtype=5*11'||CHR(38)||'instead=1'||CHR(38)||'attention=0'||CHR(38)||'appid=SPF'||CHR(38)||'istemp=0'||CHR(38)||'isreadonly=0'||CHR(38)||'isedit=0'||CHR(38)||'isLimit=0', 5, 'remark', null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null);


insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('3302E92C350CBD3AE0530100007F7598', 2, 1, '1', '004004021', '批量申报代录二级项目', '120002', '/spf/spf/proj/input/inputMain.do?style=2'||CHR(38)||'projmType=2'||CHR(38)||'dealtype=P0'||CHR(38)||'spfchsdealtype=5*11'||CHR(38)||'instead=1'||CHR(38)||'attention=0'||CHR(38)||'appid=SPF'||CHR(38)||'istemp=0'||CHR(38)||'isreadonly=0'||CHR(38)||'isedit=0'||CHR(38)||'isLimit=0', 2, 'remark', null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null);

UPDATE fasp_t_pubmenu SET CODE ='004004020'  WHERE GUID ='282E476639F820DBE050A8C0210654AE';
UPDATE fasp_t_pubmenu SET CODE ='004002022'  WHERE GUID ='282E476639F720DBE050A8C0210654AE';


UPDATE DICT_T_FACTOR
   SET CSID       = '223267AA7D472B22E050A8C021057ED5',
       DATATYPE   = '6',
       DEID       = '10401',
       SHOWFORMAT = '4'
 WHERE TABLEID IN
       (SELECT TABLEID FROM DICT_T_MODEL WHERE DEALTYPE IN ('4*01', 'P*01'))
   AND DBCOLUMNNAME = 'AGENCYID';
END;
--修改批量代录菜单及代录引用表
