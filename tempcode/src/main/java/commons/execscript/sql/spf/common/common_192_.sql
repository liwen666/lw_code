declare
v_count number(1);--�鿴���Ƿ����
begin 
  select count(*) INTO v_count from user_tables t where  t.table_name = 'P#KPI_T_FCZJXZBB';
  if v_count > 0
   then execute immediate Q'{DROP TABLE P#KPI_T_FCZJXZBB}' ;
  end if;
  
  --����������Чָ���
  execute immediate Q'{
     create table P#KPI_T_FCZJXZBB
     (
  province   VARCHAR2(9) not null,
  year       CHAR(4) not null,
  status     CHAR(1) default '1' not null,
  dbversion  TIMESTAMP(6) default SYSDATE,
  datakey    VARCHAR2(32) default sys_guid() not null,
  istemplate CHAR(1),
  isupdate   CHAR(1),
  isqzx      CHAR(1),
  superid    VARCHAR2(32),
  origcode   VARCHAR2(32),
  cellsecu   VARCHAR2(1000),
  fdcode     VARCHAR2(100),
  templateid VARCHAR2(32),
  synstatus  CHAR(1),
  isdj       CHAR(1),
  isleaf     CHAR(1),
  orderid    NUMBER(9),
  kpiname    VARCHAR2(100),
  kpicontent VARCHAR2(300),
  standscore VARCHAR2(500),
  kpiscore   NUMBER(10),
  FINANCEID  VARCHAR2(32),
  isinsert   CHAR(1),
  levelno    NUMBER(2) default 0
            )
  }';
    execute immediate Q'{alter table P#KPI_T_FCZJXZBB add constraint PK_P#KPI_T_FCZJXZBB primary key (PROVINCE, YEAR, STATUS, DATAKEY)}';

  --ˢP#KPI_T_FCZJXZBB��������
  SYS_P_PARTITION_TABLE('P#KPI_T_FCZJXZBB') ;
  
  
  --��P#����ѭ����������
  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop 
    
    begin

      --����dealtype��ȱʡ��
  select count(1) into v_count FROM dict_t_dealtype WHERE dealid='7*08' AND appid ='KPI';
  if v_count=0 then
     insert into dict_t_dealtype (APPID, DEALID, DEALNAME, ORDERID, NEEDCONFIG)  values ('KPI','7*08', '������Чָ��', 1003, '0');
  end if;
  select count(1) into v_count FROM dict_t_defaultcol WHERE DEALID ='7*08' and name= '�б�־';
  if v_count=0 then
    insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS)
    values ('7*08', 1, '�б�־', 'DATAKEY', 3, 32, '0', null, '1', '1',sys_guid(), '1');
  end if;

    
    --ɾ��model ����   ������Чָ���  ����
    delete from p#dict_t_model t where t.TABLEID='1689D655518626D7E050A8C021056FA4' AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
    
    --ɾ��factor ����   ������Чָ��� ����
    delete from p#dict_t_factor t where t.TABLEID='1689D655518626D7E050A8C021056FA4' AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
    
    --��model���в���   ������Чָ��� ����
  
    insert into p#dict_t_model (YEAR, PROVINCE, APPID, BGTLVL, DBTABLENAME, NAME, TABLEID,  DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, DESCFILE)
    values (v_row.YEAR, v_row.DISTRICTID, 'KPI', '1', 'KPI_T_FCZJXZBB', '������Чָ���', '1689D655518626D7E050A8C021056FA4',  '7*08', '0000000000000000000000000000000', '1', '0', '0', '1', '0', '1', '0', '0', null, 4, null, null, null, null, '1', null, '1', 'F6A4E2F5QF0996DFE040E8E0200390B2', '1', null, '1',  '0', '0', null);

	
    --��dict_t_factor���в���  ������Чָ��� ����

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, 'HY78D655518D26D7E050A8C02421FB1', '��Ԫ���Ƿ��¼', 'CELLSECU', 3, 1000, 0, '1', null, null, '1', 7, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', '��Ԫ���Ƿ��¼', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', '080001', '6YUJ820C2021FDA67950A8C0210542E8', '�б�־', 'DATAKEY', 3, 32, 0, '0', 'sys_guid()', null, '1', 1, '0', '1', 1, null, null, '0', 0, '1', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', 'DATAKEY', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, 'NKL9D655518E26D7E050JG8021056FB1', '��������ʾ��', 'FDCODE', 3, 100, 0, '1', null, null, '1', 8, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', '��������ʾ��', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, 'ADF5D655519126D7E08JK8C021056FB1', '�Ƿ񵹼���', 'ISDJ', 3, 1, 0, '1', null, null, '1', 20, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', '�Ƿ񵹼���', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '14YU7655519926D7EK8OA8C021056FB1', '�Ƿ�ɲ���', 'ISINSERT', 3, 1, 0, '1', null, null, '1', 30, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', '�Ƿ�ɲ���', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '1489NHG8519226JK7050A8C021056FB1', '�Ƿ�ĩ��', 'ISLEAF', 3, 1, 0, '1', null, null, '1', 21, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', '�Ƿ�ĩ��', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '1489D65551OPK9IL9050A8C021056FB1', '�Ƿ�������', 'ISQZX', 3, 1, 0, '1', null, null, '1', 4, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', '�Ƿ�������', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '1489MK8651872I70E050A8C021056FB1', '�Ƿ�ģ��', 'ISTEMPLATE', 3, 1, 0, '1', null, null, '1', 2, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', '�Ƿ�ģ��', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '1489D65K8Y382HT6E050A8C021056FB1', '�Ƿ���޸�', 'ISUPDATE', 3, 1, 0, '1', null, null, '1', 3, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', '�Ƿ���޸�', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '1489D6557UO926U80050A8C021056FB1', 'ָ������', 'KPICONTENT', 3, 300, 0, '1', null, null, '1', 26, '0', '1', 1, null, null, '0', 0, '0', '1', '1', '0', '0', null, null, '0', null, null, 'ָ������', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '1489DNMY7194JK87E050A8C021056FB1', 'ָ������', 'KPINAME', 3, 100, 0, '1', null, null, '1', 23, '0', '1', 1, null, null, '0', 0, '0', '1', '1', '0', '0', null, null, '0', null, null, 'ָ������', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '1489D6534R977OD7E050A8C021056FB1', 'ָ��ֵ', 'KPISCORE', 1, 10, 0, '1', null, null, '1', 28, '0', '1', 1, null, null, '0', 0, '0', '1', '1', '0', '0', null, null, '0', null, null, 'ָ��ֵ', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '1489D65HR33A295TE050A8C021056FB1', '�����', 'LEVELNO', 1, 2, 0, '1', '0', null, '1', 31, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', '�㼶', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '1489WDG6515U36D7E050A8C021056FB1', '�������', 'ORDERID', 1, 9, 0, '1', null, null, '1', 22, '0', '1', 1, null, null, '0', 0, '0', '1', '1', '0', '0', null, null, '0', null, null, '���', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '14894K9N518BSGN7E050A8C021056FB1', 'ԭʼ����', 'ORIGCODE', 3, 32, 0, '1', null, null, '1', 6, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', 'ԭʼ����', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, 'DO902655519826D7E050A8C05KY56FB1', '����Ψһ��ʶ', 'FINANCEID', 3, 32, 0, '1', null, null, '1', 29, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', '��ĿID', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '148BO0L5519626D7E050A87UKO056FB1', '���ֱ�׼', 'STANDSCORE', 3, 500, 0, '1', null, null, '1', 27, '0', '1', 1, null, null, '0', 0, '0', '1', '1', '0', '0', null, null, '0', null, null, '���ֱ�׼', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '14EF5655518A26D7E00HNMC021056FB1', '�ϼ�����', 'SUPERID', 3, 32, 0, '1', null, null, '1', 5, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', '�ϼ�����', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '19JID655519026D7E7JMP8C021056FB1', 'ͬ��״̬', 'SYNSTATUS', 3, 1, 0, '1', null, null, '1', 10, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', 'ͬ��״̬', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null);

insert into p#dict_t_factor (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, NULLABLE, DEFAULTVALUE, CSID, ISUPDATE, ORDERID, SUPERID, ISLEAF, LEVELNO, ALIAS, COLFORMAT, SHOWFORMAT, SHOWWIDTH, ISKEY, ISVISIBLE, ISRESERVE, ISSUM, ISREGEX, REGEXPR, REGEXPRINFO, ISBANDCOL, BANDREFDWCOL, EXTPROP, COLTIPS, FRMTABID, FRMCOLID, ISVIRTUAL, VIRCONTEXT, ISHREF, HREFLOC, HREFPARMID, BGTLVL, STATUS, BANDCOLUMNID, PARENTNODECANCHECK, OPENWINDOWTYPE, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '1689D655518626D7E050A8C021056FA4', null, '1487UE65518F26D7E4RT68C021056FB1', 'ģ���ʶ', 'TEMPLATEID', 3, 32, 0, '1', null, null, '1', 9, '0', '1', 1, null, null, '0', 0, '0', '0', '0', '0', '0', null, null, '0', null, '0000000000000000000000000000000', 'ģ���ʶ', null, null, '0', null, '0', null, null, '1', '1',null, '0', '1', null); 
    end; 
    
  end loop;
  --ˢ��ͼ
  
  
  sys_p_recreate_views('1689D655518626D7E050A8C021056FA4');
  
end;--��Ч_20160621_YLL����������Ч����ͼ��