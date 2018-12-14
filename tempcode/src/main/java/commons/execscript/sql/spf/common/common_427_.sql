DECLARE
begin

---�޸�ר������б�����----
DELETE FROM dict_t_defaultcol WHERE dealid ='46';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
	values ('46', 1, '�б�ʶ', 'DATAKEY', 3, 32, null, null, '1', '1', '4DE27D8C10852DE4E0533906A8C0A523', '1',  null, '1', '080001', '0', '0');

---�޸���д�˶�������ô�����Ҳ�������----
/*������ͼ*/
	EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SFP_T_XMTZJHHDJEB AS
	SELECT DBVERSION, STATUS ,spf_t_pinvestplan.EXPFUNCID AS EXPFUNCID ,spf_t_pinvestplan.PROVNUM AS PROVNUM ,spf_t_pinvestplan.CITYAGNUM AS CITYAGNUM ,spf_t_pinvestplan.PROJECTID AS PROJECTID ,spf_t_pinvestplan.FINYEAR AS FINYEAR ,spf_t_pinvestplan.COUNTYAGNUM AS COUNTYAGNUM ,spf_t_pinvestplan.COUNTYNUM AS COUNTYNUM ,spf_t_pinvestplan.CENTAGNUM AS CENTAGNUM ,spf_t_pinvestplan.DATAKEY AS DATAKEY ,spf_t_pinvestplan.EXPECOID AS EXPECOID ,spf_t_pinvestplan.PROVAGNUM AS PROVAGNUM ,spf_t_pinvestplan.CITYNUM AS CITYNUM ,spf_t_pinvestplan.CENTNUM AS CENTNUM  FROM SPF_T_PINVESTPLAN spf_t_pinvestplan WHERE 1 = 1/';
    
	/*����dealtype*/
	delete from dict_t_dealtype where dealid='5*41';
    Insert into dict_t_dealtype (APPID,DEALID,DEALNAME,ORDERID,NEEDCONFIG,ISUNIQUE) values ('SPF','5*41','��ĿͶ�ʼƻ��˶�����',12,'0','0');
    delete from p#dict_t_model WHERE tableid ='3A6A5FE533CC60DDE053CB01A8C06FD9';
    delete from p#dict_t_factor where tableid ='3A6A5FE533CC60DDE053CB01A8C06FD9';
	DELETE FROM dict_t_defaultcol WHERE dealid ='5*41';
	insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
	values ('5*41', 1, '�б�ʶ', 'DATAKEY', 3, 32, null, null, '1', '1', '4DE1736EBEAA298AE0533906A8C09A0C', '1',  null, '1', '080001', '0', '0');
 for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop
  begin
insert into P#DICT_T_MODEL (YEAR, PROVINCE, TABLEID, APPID, DBTABLENAME, NAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, ORDERID, RELATAB, REMARK, SECUSQL, BGTLVL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, INITSQLBTNNAME, ISEXCELIMP, ISREADONLY, DESCFILE)
values (v_row.year,v_row.districtid, '3A6A5FE533CC60DDE053CB01A8C06FD9', 'SPF', 'SFP_T_XMTZJHHDJEB', '��ĿͶ�ʼƻ��˶�����', '5*41', '0000000000000000000000000000000', '3', '0', '0', '0', '0', '1', '', '0', '', 1, '14C0C3214F27189EE050A8C0210507CA', '', '', '', '<CLOB>', '1', '', '1', 'FB12E708FE56425F82EF3D89FDE0D0EF', '3', '', '0', '', '', '', '', '0', '');


insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', 'DATAKEY', '3A6A5FE533CD60DDE053CB01A8C06FD9', '', 32, 3, 'DATAKEY', 'sys_guid()', '080001', '0000000000000000000000000000000', '1684820C202BFDA8E050A8C0210542E8', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�б�־', '0', 2, '', '', 0, '0', 0, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '��Ŀid', '3A6A5FE533CE60DDE053CB01A8C06FD9', '', 32, 3, 'PROJECTID', '', '10804', '0000000000000000000000000000000', '14C0C3214F28189EE050A8C0210507CA', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '��ĿΨһ��ʶ', '0', 4, '', '', 0, '0', 0, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '����', '3A6A5FE533CF60DDE053CB01A8C06FD9', '9786827170284907A734AF1107A3B65D', 32, 6, 'FINYEAR', '', '10206', '0000000000000000000000000000000', '14C0C3214F29189EE050A8C0210507CA', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '����', '0', 7, '', '', 0, '4', 90, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '3A6A5FE533D060DDE053CB01A8C06FD9', 'E48AD8120DD1434F8A36295480DA5A4D', 32, 6, 'EXPFUNCID', '', '10604', '0000000000000000000000000000000', '170704CA9CDB7503E050A8C021057EFA', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '֧�����ܷ����Ŀ', '1', 14, '', '', 0, '4', 180, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '1', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '', '3A6A5FE533D160DDE053CB01A8C06FD9', '30E16A146B1200D2E0530A440506C518', 32, 6, 'EXPECOID', '', '10607', '0000000000000000000000000000000', '170704CA9CDA7503E050A8C021057EFA', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '֧�����÷����Ŀ', '0', 19, '', '', 0, '4', 180, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '1', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, 'ʡ�����˶����', '', '', '', '', 'ʡ�����˶����', '3A6A5FE533D260DDE053CB01A8C06FD9', '', 24, 2, 'PROVNUM', '0', '', '0000000000000000000000000000000', '14C0C3214F2B189EE050A8C0210507CA', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '1', '1', '0', '1', 1, 'ʡ�����˶����', '0', 29, '', '', 2, '0', 0, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '�ز��ź˶����(��Ԫ)', '3A6A5FE533D360DDE053CB01A8C06FD9', '', 24, 2, 'COUNTYAGNUM', '0', '', '0000000000000000000000000000000', '14C0C3214F2E189EE050A8C0210507CA', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '1', '1', '0', '1', 1, '�ز��ź˶����', '1', 190, '', '', 2, '0', 0, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '�ز����˶����(��Ԫ)', '3A6A5FE533D460DDE053CB01A8C06FD9', '', 24, 2, 'COUNTYNUM', '0', '', '0000000000000000000000000000000', '14C0C3214F2D189EE050A8C0210507CA', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '1', '1', '0', '1', 1, '�ز����˶����', '1', 191, '', '', 2, '0', 0, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '�в����˶����(��Ԫ)', '3A6A5FE533D560DDE053CB01A8C06FD9', '', 24, 2, 'CITYNUM', '0', '', '0000000000000000000000000000000', '14C0C3214F2F189EE050A8C0210507CA', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '1', '1', '0', '1', 1, '�в����˶����', '1', 192, '', '', 2, '0', 0, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '�в��ź˶����(��Ԫ)', '3A6A5FE533D660DDE053CB01A8C06FD9', '', 24, 2, 'CITYAGNUM', '0', '', '0000000000000000000000000000000', '14C0C3214F30189EE050A8C0210507CA', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '1', '1', '0', '1', 1, '�в��ź˶����', '1', 193, '', '', 2, '0', 0, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', 'ʡ���ź˶����(��Ԫ)', '3A6A5FE533D760DDE053CB01A8C06FD9', '', 24, 2, 'PROVAGNUM', '0', '', '0000000000000000000000000000000', '14C0C3214F2C189EE050A8C0210507CA', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '1', '1', '0', '1', 1, 'ʡ���ź˶����', '1', 194, '', '', 2, '0', 0, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '���벿�ź˶����(��Ԫ)', '3A6A5FE533D860DDE053CB01A8C06FD9', '', 24, 2, 'CENTAGNUM', '0', '', '0000000000000000000000000000000', '14C0C3214F32189EE050A8C0210507CA', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '1', '1', '0', '1', 1, '���벿�ź˶����', '1', 202, '', '', 6, '0', 0, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,v_row.districtid, '', '', '', '', '', '��������˶����(��Ԫ)', '3A6A5FE533D960DDE053CB01A8C06FD9', '', 24, 2, 'CENTNUM', '0', '', '0000000000000000000000000000000', '14C0C3214F31189EE050A8C0210507CA', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '1', '1', '0', '1', 1, '��������˶����', '1', 209, '', '', 6, '0', 0, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '0', '');


	  end;
 end loop;
end;--�޸�ר������б�����