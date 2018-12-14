declare 
v_count number(1);--�鿴���Ƿ����
begin 
  select count(*) INTO v_count from user_tables t where  t.table_name = 'P#SPF_T_PBASESTATUS';
  if v_count = 0 THEN
   execute immediate Q'{
     CREATE TABLE P#SPF_T_PBASESTATUS
    (
      PROVINCE    VARCHAR2(9) NOT NULL,
      YEAR        CHAR(4) NOT NULL,
      STATUS      CHAR(1) DEFAULT '1' NOT NULL,
      DBVERSION   TIMESTAMP(6) DEFAULT SYSDATE,
      DATAKEY     VARCHAR2(32) DEFAULT SYS_GUID() NOT NULL,
      NEEDUPDATE  VARCHAR2(4000),
      ORDERID     NUMBER(9),
      FINYEAR     VARCHAR2(4),
      PROJECTID   VARCHAR2(32),
      CHECKSTATUS NUMBER(1) DEFAULT 0,
      ISBGT       NUMBER(1) DEFAULT 0,
      ISINDEX     NUMBER(1) DEFAULT 0
    )
    }';
    execute immediate Q'{alter table P#SPF_T_PBASESTATUS add constraint PK_P#SPF_T_PBASESTATUS primary key (PROVINCE, YEAR, STATUS, DATAKEY)}';
  --ˢPP#SPF_T_PBASESTATUS��������
  SYS_P_PARTITION_TABLE('P#SPF_T_PBASESTATUS') ;
  execute immediate Q'{create index IDX_P#SPF_T_PBASESTATUS1 on P#SPF_T_PBASESTATUS (CHECKSTATUS, ISBGT, ISINDEX)}';
    execute immediate Q'{create unique index IDX_P#SPF_T_PBASESTATUS2 on P#SPF_T_PBASESTATUS (YEAR, STATUS, PROJECTID)}';
      --ɾ��model ����   ������Чָ���  ����
    delete from p#dict_t_model t where t.TABLEID='4C8F6FFB6F621B0AE0533A06A8C048EE' ;
    
    --ɾ��factor ����   ������Чָ��� ����
    delete from p#dict_t_factor t where t.TABLEID='4C8F6FFB6F621B0AE0533A06A8C048EE';
    
  --��P#����ѭ����������
  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop 
    
    begin

    --��model���в���   ������Чָ��� ����
insert into P#DICT_T_MODEL (YEAR, PROVINCE, TABLEID, APPID, DBTABLENAME, NAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, ORDERID, RELATAB, REMARK, SECUSQL, BGTLVL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, INITSQLBTNNAME, ISEXCELIMP, ISREADONLY, DESCFILE)
values (v_row.YEAR, v_row.DISTRICTID, '4C8F6FFB6F621B0AE0533A06A8C048EE', 'SPF', 'SPF_T_PBASESTATUS', '��Ŀ״̬��¼��', 'B0', '0000000000000000000000000000000', '3', null, null, '1', null, '1', '0', '0', null, 1, null, null, null, null, '<CLOB>', '1', null, '1', 'FB12E708FE56425F82EF3D89FDE0D0EF', '1', null, '0', '0', '0', null, '0', '0', null);
insert into P#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '�б�־', null, null, null, null, '�б�־', '4C8F6FFB6F631B0AE0533A06A8C048EE', null, 32, 3, 'DATAKEY', 'sys_guid()', '080001', null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '1', 1, '�б�־', '1', 1, null, null, 0, null, null, '1', '0', '4C8F6FFB6F621B0AE0533A06A8C048EE', null, '0', null);

insert into P#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, 'ˢ�±�־', null, null, null, null, 'ˢ�±�־', '4C8F6FFB6F641B0AE0533A06A8C048EE', null, 4000, 3, 'NEEDUPDATE', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '1', 1, 'ˢ�±�־', '1', 2, null, null, 0, null, null, '1', '0', '4C8F6FFB6F621B0AE0533A06A8C048EE', null, '0', null);

insert into P#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '�������', null, null, null, null, '�������', '4C8F6FFB6F651B0AE0533A06A8C048EE', null, 9, 1, 'ORDERID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '1', 1, '�������', '1', 3, null, null, 0, null, null, '1', '0', '4C8F6FFB6F621B0AE0533A06A8C048EE', null, '0', null);

insert into P#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, '����', null, null, null, null, '����', '4C8F6FFB6F661B0AE0533A06A8C048EE', null, 4, 3, 'FINYEAR', null, '10206', null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '1', 1, '����', '1', 4, null, null, 0, null, null, '1', '0', '4C8F6FFB6F621B0AE0533A06A8C048EE', null, '0', null);

insert into P#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4C911068B8D02D59E0533A06A8C0B881', null, 32, 3, 'PROJECTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '1', '1', '0', '0', '0', '1', '0', '1', 1, '��ĿID', '0', 5, null, null, 0, '0', null, '1', '0', '4C8F6FFB6F621B0AE0533A06A8C048EE', null, '0', null);

insert into P#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '0δȷ��1��ȷ�� ', '4C911068B8D12D59E0533A06A8C0B881', null, 1, 1, 'CHECKSTATUS', '0', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '��Ŀ���״̬', '1', 6, null, null, 0, '0', null, '1', '0', '4C8F6FFB6F621B0AE0533A06A8C048EE', null, '0', null);

insert into P#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4C913C600B1D2F87E0533A06A8C0E31D', null, 1, 1, 'ISBGT', '0', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '�Ƿ�����Ԥ��', '1', 7, null, null, 0, '0', null, '1', '0', '4C8F6FFB6F621B0AE0533A06A8C048EE', null, '0', null);

insert into P#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '4C913C600B1E2F87E0533A06A8C0E31D', null, 1, 1, 'ISINDEX', '0', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '�Ƿ�����ָ��', '1', 8, null, null, 0, '0', null, '1', '0', '4C8F6FFB6F621B0AE0533A06A8C048EE', null, '0', null);
  end; 
  end loop;
  sys_p_recreate_views('4C8F6FFB6F621B0AE0533A06A8C048EE');
 end if;
end;

--��Ŀ״̬��P#SPF_T_PBASESTATUS