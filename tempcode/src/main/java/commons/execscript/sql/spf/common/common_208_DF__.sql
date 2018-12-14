BEGIN

delete from  dict_t_dealtype t where t.dealid = '4*55' and t.appid = 'SPF';

delete from dict_t_defaultcol dtd where dtd.dealid = '4*55';

delete from P#dict_t_model dm where dm.APPID = 'SPF' and dm.DEALTYPE = '4*55' and dm.DBTABLENAME = 'SPF_T_ZCMXB4BUDGET';

delete from P#dict_t_factor dtf where dtf.TABLEID = 'E8B0C824DEF249B49AE56F377B0885F6';
--���봦������
insert into dict_t_dealtype (APPID, DEALID, DEALNAME, ORDERID, NEEDCONFIG, ISUNIQUE)
values ('SPF', '4*55', 'Ԥ��֧����ϸ��[����Ŀ]', 1002, '0', '0');

--����ȱʡ��
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*55', 14, '��ĿΨһ��ʶ', 'PROJECTID', 3, 32, 0, null, '0', '1', '2D096E712E0B73A7E050A8C02105191A', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS,  DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*55', 1, '�б�־', 'DATAKEY', 3, 32, 0, null, '1', '1', '306FD7CBE3D1C294E050A8C021051B55', '1', 'sys_guid()', '1', '080001', '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS,  DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*55', 8, '���ܿ�Ŀ', 'EXPFUNCID', 3, 32, 0, null, '0', '1', '557C72D17C4E4AFEB5C0079BF89DF98F', '1',  null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS,  DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*55', 7, '���ÿ�Ŀ', 'EXPECOID', 3, 32, 0, null, '0', '1', '5C5A04A9B8AC4005AC5FA9A0938CE1D5', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS,  DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*55', 11, 'Ԥ��˶����', 'BUDGETNUM', 1, 18, 4, null, '0', '1', 'A07EA20E53E8480392CB23A802AFE94B', '1',  null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS,  DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*55', 8, 'ר��Ψһ��ʶ', 'SPFID', 3, 32, null, null, '0', '1', 'B771B3002CB049DAAFC23ACBDCCBEFB1', '1', null, null, null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS,  DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*55', 8, '��Ŀ���', 'PROJTYPEID', 3, 32, 0, null, '0', '1', 'F61C899D9D3F04DFE040A8C020030E25', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS,  DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*55', 5, '����', 'FINYEAR', 3, 4, 0, null, '0', '1', 'F6FBAD1E937E05D5E040A8C0200332C7', '1',  null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*55', 9, 'Ԥ�㵥λ', 'AGENCYID', 3, 32, null, null, '0', '1', '267B9ED8840E4D0590832336982DF214', '1', null, null, null, '0', '0');

for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
  --����model
  insert into P#dict_t_model (YEAR, PROVINCE,APPID, BGTLVL, DBTABLENAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLEID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, INITSQLBTNNAME, ISEXCELIMP, DESCFILE)
  values (v_row.YEAR, v_row.DISTRICTID,'SPF', null, 'SPF_T_ZCMXB4BUDGET', '4*55', '0000000000000000000000000000000', '3', null, null, '0', '0', '1', '0', '0', null, 'Ԥ��֧����ϸ��[����Ŀ]', 1111, '1FFB71E8965441A1AB5617677A447AA4', null, null, null, '1', null, '1', '8288F140A88F4CCB8FF5BD9B4B28DCEF', 'E8B0C824DEF249B49AE56F377B0885F6', '3', null, '0',  null, null, null, null, null);
  --����factor
  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, '011D1203682B4F928F7C4D99F665D33B', null, 32, 3, 'BATCHID', null, null, '0000000000000000000000000000000', 'E4D1AEFA29C74D53B4090194365059BD', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '���α���', '1', 78, null, null, 0, null, null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, '1C844A4F6CCD4B4199BB1D641D9609D2', null, 200, 3, 'PROJECTNAME', null, '10805', '0000000000000000000000000000000', 'A83B056F5E7D42BFA1F5A05FF2EDF6A5', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '��Ŀ����', '1', 2, null, null, 0, null, null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,'����', null, null, null, null, '����', '367F2765E6A1463EA6623FB29EA4394B', null, 32, 3, 'FINYEAR', null, '10206', '0000000000000000000000000000000', 'A7849D3BDF8248FA893F18058AA2B07C', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '����', '1', 5, null, null, 0, '0', null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, '3A4CC1D70548418DBB3FE8D0597F59C5', null, 32, 3, 'SUPERGUID', null, null, '0000000000000000000000000000000', 'E794CC4E5EC444DF8880751C8959A655', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�ϼ�Ψһ��ʶ', '1', 76, null, null, 0, null, null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, '3AE40412F6AD4729A9E3423F27D76DD8', null, 1, 1, 'ISLEAF', '0', null, '0000000000000000000000000000000', '3ADBAC6CA01F454D92C93E95C6DF87AF', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�Ƿ�ĩ��', '1', 75, null, null, 0, null, null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, '43E947F6A3024230BFF7CD4A5B820CFC', null, 10, 1, 'LEVELNO', '0', null, '0000000000000000000000000000000', '4BDF545F985F4BD2808D32159D07A4F3', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�����', '1', 68, null, null, 0, null, null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, '5D98819E3BAA44E58FF5728C510D08EA', '09B774215D424706B303ED8D30DB1125', 32, 6, 'PROJECTID', '''0''', '10804', '0000000000000000000000000000000', '36AEE3A1EA7B46C48A208D9BE9C77AA9', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '��ĿΨһ��ʶ', '0', 2, null, null, 0, '4', null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, '�б�־', '6261955968954563B1B61C7A924BB1D9', null, 32, 3, 'DATAKEY', 'sys_guid()', null, '0000000000000000000000000000000', 'ADF1F5645EEE40639850107002952DFC', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�б�־', '1', 1, null, null, 0, null, null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, '8232EAC0D1C446CC8AD187460AA01A58', null, 18, 2, 'BUDGETNUM', '0', '1500yV0pcqhj', '0000000000000000000000000000000', 'F84F09AACA0D43C798F767884573BE7D', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '�˶����', '1', 46, null, null, 2, null, null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, 'A4B284450A2341258C97E247126B9066', null, 32, 3, 'EXPFUNCID', null, '10604', '0000000000000000000000000000000', 'D8B6BE5EFC8341F1BAB824DC5838BAED', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '֧�����ܷ����Ŀ', '1', 33, null, null, 0, '0', null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, '1', null, null, 'CB37BFB85E984C75AF752CB8690D19ED', null, 1, 3, 'ISADJUST', '''0''', null, '0000000000000000000000000000000', '2D32EFE4987A91DBE050A8C021051137', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�Ƿ����', '0', 79, null, null, 0, '0', 0, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, 'D060463B7871418CBC723B6E4DC66ECD', null, 32, 3, 'SPFID', '''0''', '10801', '0000000000000000000000000000000', '36050D4D98874AFFB81652D7304FD631', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, 'һ����ĿΨһ��ʶ', '0', 77, null, null, 0, '0', null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, '��λ', 'D729F74E96F0416198999B286E2EFFF9', null, 32, 3, 'AGENCYID', null, '10401', '0000000000000000000000000000000', '24B3614C604C4E3B8B8CC50480886FD1', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, 'Ԥ�㵥λ', '1', 32, null, null, 0, '0', null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, '�����ֶ�', 'EA220B3813A24AC69E95C8BE4F431D5B', null, 9, 1, 'ORDERID', '0', null, '0000000000000000000000000000000', '743A95A8330B425AB7412644CEAE8B4B', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�������', '1', 27, null, null, 0, null, null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, 'F398A9BCD7384BB6BFBD5C7A156160D7', null, 32, 3, 'EXPECOID', null, '10607', '0000000000000000000000000000000', '2D510939DE084E96A563FE204D985196', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '֧�����÷����Ŀ', '1', 35, null, null, 0, '0', null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);

  insert into P#dict_t_FACTOR (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
  values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, 'F4A070BD7FC2403F855FD2372875062A', null, 32, 3, 'PROJTYPEID', null, '10807', '0000000000000000000000000000000', '24EFD9F2F1F2475BA08E454D32C52759', '1FFB71E8965441A1AB5617677A447AA4', null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, 'ר���ʽ����Ψһ��ʶ', '1', 32, null, null, 0, null, null, '1', '0', 'E8B0C824DEF249B49AE56F377B0885F6', null, '0', null);
  
END LOOP;
sys_p_recreate_views('E8B0C824DEF249B49AE56F377B0885F6');
END;
--֧����ϸ��(����Ŀ)