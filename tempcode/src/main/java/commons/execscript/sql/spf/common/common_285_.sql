begin
	delete from p#dict_t_factor where tableid='69722A5DAFA64394B6BC8E7F7DDCC99E';
	for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '单位编码', null, null, null, null, '单位编码', '0023743DC28C4133AED1534D55F0AFCF', null, 32, 3, 'AGENCYID', null, null, '0000000000000000000000000000000', '9947937F4F3E41B7A4A9006A447930C8', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '单位编码', '1', 2, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '功能科目', null, null, null, null, '功能科目', '0328FD5D54594F6A804ACE064C3709E9', 'E48AD8120DD1434F8A36295480DA5A4D', 32, 6, 'EXPFUNCID', null, null, '0000000000000000000000000000000', '5D389198E14F4D8988F5B6D4421AB5F9', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '功能科目', '1', 10, null, null, null, '4', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '项目申报时间', null, null, null, null, '项目申报时间', '0A16C1EC7E614A74BA0215705178813E', null, 20, 3, 'CREATETIME', 'to_char(sysdate,''fxyyyy-mm-dd'')', null, '0000000000000000000000000000000', 'FA809FCE2C8E44629CB1FCE09EA47E3A', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '项目申报时间', '1', 12, null, null, 0, '2', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '单位上报时间', null, null, null, null, '单位上报时间', '0DA4757C5B63446BA821FA9E538B3D35', null, 20, 3, 'UPTIME', 'sysdate', null, '0000000000000000000000000000000', '32C3B824EE2D4D948758C26FB287CF91', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '单位上报时间', '1', 14, null, null, 0, '2', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '项目排序', null, null, null, null, '项目排序', '14BA182FB82846B9A2E681EAEA12BB79', null, 18, 3, 'IMPORTANCE', null, null, '0000000000000000000000000000000', '874212C145764076A505EE33B2B18308', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '项目排序', '1', 5, null, null, 0, null, 50, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '是否测算分配项目', null, null, null, null, '是否测算分配项目', '2032DD653A4E44599D8365082CEF6841', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISMEASURED', '''0''', null, '0000000000000000000000000000000', '92A9611641BA40F4AE81548527777554', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否测算分配项目', '1', 14, null, null, null, '4', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '临时项目审核状态', null, null, null, null, '临时项目审核状态', '2B08D65478E64DDA8122E01AC9976852', null, 32, 3, 'TEMPCHECKSTATUS', null, null, '0000000000000000000000000000000', '894F08A86BA04A9C8B29CE978187BE25', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '临时项目审核状态', '1', 34, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '资金申请结束年', null, null, null, null, '资金申请结束年', '2E7969B1B2BA4ADE922C0E9514C44E8A', '9786827170284907A734AF1107A3B65D', 32, 6, 'ENDYEAR', null, null, '0000000000000000000000000000000', '3098A466D6444E24949B4A9FBA9A3D12', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '资金申请结束年', '1', 22, null, null, null, '4', 70, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '项目名称', null, null, null, null, '项目名称', '3B6AAEAB4442480D900776975B516A5B', null, 200, 3, 'PROJNAME', null, null, '0000000000000000000000000000000', '169E1267BD6842189E539E007D650310', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '项目名称', '1', 4, null, null, 0, null, 200, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '是否临时项目', null, null, null, null, '是否临时项目', '466DB88E2B954538B080C4FE4AEBA6F7', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISTEMP', '''0''', null, '0000000000000000000000000000000', 'D7DD09FC37174572B50BA5A9F0A5D0FD', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否临时项目', '1', 36, null, null, null, '4', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '项目申报人ID', null, null, null, null, '项目申报人ID', '49E74984D97C429582973BA9F485D8A6', null, 32, 3, 'CREATEUSER', null, null, '0000000000000000000000000000000', '1E5564B73EC44E959E681BF496D09FCB', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '项目申报人ID', '1', 11, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '临时项目主管部门', null, null, null, null, '临时项目主管部门', '4BB5423316C04FF0950032D6833B4711', null, 32, 3, 'TEMPFIRAGENCYID', null, null, '0000000000000000000000000000000', 'E1B022A060214468B2385F773572A5E1', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '临时项目主管部门', '1', 33, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '专项ID', null, null, null, null, '专项ID', '56FFC5C27C344793B46C6F9D1AFBED6D', null, 32, 3, 'SPFID', null, null, '0000000000000000000000000000000', '7D121B8BCD7644EF9ABC4062DE439433', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '专项ID', '1', 8, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '审核状态', null, null, null, null, '审核状态', '6C6D75F236334AC7BCDF47996A109D72', null, 32, 3, 'CHECKSTATUS', '''0''', null, '0000000000000000000000000000000', '151522C5EE67449B8EF1B4FF39993EE2', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '审核状态', '1', 24, null, null, 0, '0', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '是否绩效', null, null, null, null, '是否绩效', '6DDBDE4FA7D54981AF77E2302B3C1F33', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISJX', null, null, '0000000000000000000000000000000', '079425CE7BB24AE998C5505E76C134DA', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否绩效', '1', 19, null, null, null, '4', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '项目编码', null, null, null, null, '项目编码', '7196115367D2408A8EA1431B4E412F94', null, 60, 3, 'PROJCODE', null, null, '0000000000000000000000000000000', 'F46AE9AB3E494778B4675CADCB23F03F', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '项目编码', '1', 7, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '单位上报状态', null, null, null, null, '单位上报状态', '7BC679D4DA554ECC8EE9C16643213E31', null, 1, 3, 'UPSTATUS', '''0''', null, '0000000000000000000000000000000', 'DE48D21124424D65826A40790B7C2AF8', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '单位上报状态', '1', 15, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '报送地区', null, null, null, null, '报送地区', '9648EBDCE0DD4ED8A8EA952BF7AA98CD', null, 32, 3, 'DISTRICTID', null, null, '0000000000000000000000000000000', '166B4A85AEEA44C2814A160F4581EC15', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '报送地区', '1', 20, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '是否备案', null, null, null, null, '是否备案', '9A07B4E5CAB04B43AC184078C77D8A67', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISCUT', '''0''', null, '0000000000000000000000000000000', 'B27333A1F5784CC78E9028E83BE2B5D3', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否备案', '1', 31, null, null, null, '4', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '是否政府采购', null, null, null, null, '是否政府采购', '9B5190B9DF4C4F2186B7EC3C3608EE4E', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISGROVPROC', '''0''', null, '0000000000000000000000000000000', '3A07821801624C17828CA675E6034A94', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否政府采购', '1', 18, null, null, null, '4', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '临时项目归口处室', null, null, null, null, '临时项目归口处室', 'A0A1CC54BBF5416C8481C6482A8F2023', null, 32, 3, 'TEMPDEPTID', null, null, '0000000000000000000000000000000', '7611115F59424C8699BF594B092DCB08', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '临时项目归口处室', '1', 32, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '临时项目上报状态', null, null, null, null, '临时项目上报状态', 'A824E57559D748EC9E9D70D6185440DB', null, 32, 3, 'TEMPUPSTATUS', null, null, '0000000000000000000000000000000', 'AE1EAE55DDAA48D0B2348B0DDEA896A8', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '临时项目上报状态', '1', 35, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '资金申请起始年', null, null, null, null, '资金申请起始年', 'AF4A5162992E4DABAE96CFDBB7CF6015', '9786827170284907A734AF1107A3B65D', 32, 6, 'STARTYEAR', null, null, '0000000000000000000000000000000', '3F9DEC84F16041E0861D2F14BD45B4E5', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '资金申请起始年', '1', 21, null, null, null, '4', 70, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '是否代录', null, null, null, null, '是否代录', 'AF5E0EE7B4244843A3ECD15C5E26C9E1', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISINSTEAD', '''0''', null, '0000000000000000000000000000000', 'EFC7910D8F2E484D8DA2DE3CC53532B1', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否代录', '1', 29, null, null, null, '4', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '是否追加指标', null, null, null, null, '是否追加指标', 'B4017934DE3D4CD18AB24B829F0F20F2', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISINDEX', '''0''', null, '0000000000000000000000000000000', '694798EB665D44CDAD23704598097143', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否追加指标', '1', 38, null, null, null, '4', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '是否地区', null, null, null, null, '是否地区', 'B445DD2C6413485B88C37D221F3AAA3C', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISDISTRICT', '''0''', null, '0000000000000000000000000000000', 'D14F063FAE6A45D2AA23D3B46BDF234D', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否地区', '1', 30, null, null, null, '4', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '项目申报人', null, null, null, null, '项目申报人', 'B4D67DC10F054E59B0A7D0F9DD6CBC47', null, 1000, 3, 'CREATEUSERNAME', null, null, '0000000000000000000000000000000', '269CA117FCA64AFD914D5F1237ACC4E0', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '项目申报人', '1', 26, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '项目ID', null, null, null, null, '项目ID', 'C7EFF40C87604DFBB3114E56128CDD01', null, 32, 3, 'PROJECTID', null, null, '0000000000000000000000000000000', '3172B0723FA04BB5BFA9845672CE7E71', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '1', '1', '0', '0', '0', '1', '0', '0', 1, '项目ID', '0', 1, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '公示批次ID', null, null, null, null, '公示批次ID', 'CC98056F7C654DF8A75F95FECCF29814', null, 32, 3, 'PUBLICITYBATCHID', null, null, '0000000000000000000000000000000', '9876C4FAA5924423987A19BA229E2579', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '公示批次ID', '1', 25, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '项目类别', null, null, null, null, '项目类别', 'CDDAB6DE4DED4326A0874E3F1D212495', '148830CAF3E61F78E050A8C021056ABE', 32, 6, 'PROJTYPEID', null, null, '0000000000000000000000000000000', 'B9CCBAE6482E4D798001BB0FE722E340', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '项目类别', '1', 9, null, null, null, '4', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '临时上报时间', null, null, null, null, '临时上报时间', 'D8FD2114B39A4BD5878A4C449105B945', null, 20, 3, 'TEMPUPTIME', 'sysdate', null, '0000000000000000000000000000000', 'ED64A2F12E104C5690F70811F5FDC3A9', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '临时上报时间', '1', 37, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '是否重点项目', null, null, null, null, '是否重点项目', 'DE7D98AAEB2448C69A662BE75170E2D0', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISZD', '''0''', null, '0000000000000000000000000000000', '37A03D6DD2614B6CB1FECE5EB5BEA431', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否重点项目', '1', 39, null, null, null, '4', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, '是否纳入预算', null, null, null, null, '是否纳入预算', 'F21DCFCB0C5A40F69C692C451F7520E2', '1F61620487DA498B8BD0F2D015054BEB', 32, 6, 'ISBGT', null, null, '0000000000000000000000000000000', 'E3509746F8594C60B002767F31300E25', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否纳入预算', '1', 23, null, null, null, '4', null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);

		insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
		values (v_row.YEAR, v_row.DISTRICTID, 'DATAKEY', null, null, null, null, 'DATAKEY', 'F6F9DA0E775C4A668236F3E215FD6AED', null, 32, 3, 'DATAKEY', 'sys_guid()', '080001', '0000000000000000000000000000000', '428C14741D96466C8F4F3FA5CB33C2BD', '9FC09B974CCE4342BB190FA2A47D4B20', null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, 'DATAKEY', '0', 3, null, null, 0, null, null, '1', '0', '69722A5DAFA64394B6BC8E7F7DDCC99E', null, '0', null);
	end loop;
end;
--二级项目排序重要程度列字段设置
