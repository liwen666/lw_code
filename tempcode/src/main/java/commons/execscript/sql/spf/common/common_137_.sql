declare
v_count number(1);--�鿴���Ƿ����
begin 
  select count(*) INTO v_count from user_tables t where  t.table_name = 'P#SPF_T_PATTACH';
  if v_count > 0
   then execute immediate Q'{DROP TABLE P#SPF_T_PATTACH}' ;
  end if;
  
  --����ר���ʽ𸽼���
  execute immediate Q'{
     create table P#SPF_T_PATTACH
     (
            province      VARCHAR2(9) not null,
            year          CHAR(4) not null,
            status        CHAR(1) default 1 not null,
            dbversion     TIMESTAMP(6) default sysdate,
            bgtlvl        CHAR(1),
            datakey       VARCHAR2(32) default sys_guid() not null,
            orderid       NUMBER(9) default 1,
            projectid     VARCHAR2(32) not null,
            attachid      VARCHAR2(32),
            memo          VARCHAR2(1000),
            ismust        VARCHAR2(1) default 0,
            templetid     VARCHAR2(32),
            templetremark VARCHAR2(1000),
            attachname    VARCHAR2(200),
            isleaf        CHAR(1),
            levelno       NUMBER(9) default 0,
            isinsert      CHAR(1) default '0',
            templateid    VARCHAR2(32),
            superid       VARCHAR2(32),
            fdcode        VARCHAR2(200),
            istemplate    CHAR(1),
            isupdate      CHAR(1),
            isqzx         CHAR(1),
            isdj          CHAR(1)
            )
  }';
  
  --���ר���ʽ𸽼�����ע��
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.bgtlvl
        is '��������'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.datakey
        is '�б�ʶ'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.orderid
        is '�������'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.projectid
        is '��ĿID'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.attachid
        is '��Ŀ����'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.memo
        is '����˵��'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.ismust
        is '�Ƿ����'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.templetid
        is '����ģ��'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.templetremark
        is '����ģ��˵��'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.attachname
        is '��������'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.isleaf
        is '�Ƿ�ĩ��'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.levelno
        is '������'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.isinsert
        is '�Ƿ�ɲ����¼�'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.templateid
        is 'ģ���ʶ'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.superid
        is '�ϼ�����'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.fdcode
        is '��������'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.istemplate
        is '�Ƿ�ģ��'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.isupdate
        is '�Ƿ�ɸ���'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.isqzx
        is '�Ƿ�������'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.isdj
        is '�Ƿ񵹼���'
    }';
    
  --ˢP#SPF_T_PATTACH������
  SYS_P_PARTITION_TABLE('P#SPF_T_PATTACH') ;
  
  select count(*) INTO v_count from user_tables t where  t.table_name = 'P#SPF_T_FSPF2JATTACH';
  if v_count > 0
   then execute immediate Q'{DROP TABLE P#SPF_T_FSPF2JATTACH}' ;
  end if;
  
  --����ר���ʽ���Ŀ�걨������
  execute immediate Q'{
    create table P#SPF_T_FSPF2JATTACH
         (
         province     VARCHAR2(9) not null,
         year         CHAR(4) not null,
         status       CHAR(1) default '1' not null,
         dbversion    TIMESTAMP(6) default SYSDATE,
         bgtlvl       CHAR(1),
         datakey      VARCHAR2(32) default SYS_GUID() not null,
         orderid      NUMBER(9) default 0,
         fdcode       VARCHAR2(100),
         levelno      NUMBER(1) default 0,
         isleaf       CHAR(1),
         isinsert     CHAR(1),
         templateid   VARCHAR2(32),
         cellsecu     VARCHAR2(1000),
         synstatus    CHAR(1),
         isqzx        CHAR(1),
         isdj         CHAR(1),
         isupdate     CHAR(1),
         origcode     VARCHAR2(32),
         istemplate   CHAR(1) default '1',
         superid      VARCHAR2(32),
         firattname   VARCHAR2(200),
         detclassid   VARCHAR2(50),
         remark       VARCHAR2(1000),
         ismust       CHAR(1),
         needupdate   VARCHAR2(4000),
         finyear      VARCHAR2(4) default TO_CHAR(SYSDATE,'YYYY'),
         attachid     VARCHAR2(32),
         spfid        VARCHAR2(32),
         templatename VARCHAR2(32),
         attname      VARCHAR2(200)
         )
  }';
  
  --���ר���ʽ���Ŀ�걨��������ע��
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.bgtlvl
        is '��������'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.datakey
        is '�б�־'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.orderid
        is '�������'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.fdcode
        is '��������ʾ��'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.levelno
        is '�����'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.isleaf
        is '�Ƿ�ĩ��'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.isinsert
        is '�Ƿ�ɲ���'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.templateid
        is 'ģ���ʶ'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.cellsecu
        is '��Ԫ�Ƿ��¼'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.synstatus
        is 'ͬ��״̬'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.isqzx
        is '�Ƿ�������'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.isdj
        is '�Ƿ񵹼���'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.isupdate
        is '�Ƿ���޸�'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.origcode
        is 'ԭʼ����'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.istemplate
        is '�Ƿ�ģ��'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.superid
        is '�ϼ�����'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.firattname
        is '��������'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.detclassid
        is '����ϸ��'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.remark
        is '��Ŀ����˵��'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.ismust
        is '�Ƿ���踽��'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.needupdate
        is 'ˢ�±�־'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.finyear
        is '����'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.attachid
        is '��Ŀ����'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.spfid
        is 'ר��Ψһ��ʶ'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.templatename
        is '����ģ��'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.attname
        is '��������'
    }';
  
  --���������
  execute immediate Q'{
    alter table P#SPF_T_FSPF2JATTACH
      add constraint P#SPF_T_FSPF2JATTACH primary key (PROVINCE, YEAR, STATUS, DATAKEY)
  }';
  
    --ˢP#SPF_T_FSPF2JATTACH������
  SYS_P_PARTITION_TABLE('P#SPF_T_FSPF2JATTACH') ;
  
  
  --��P#����ѭ����������
  for v_row in (select * from pub_t_partition_divid t where t.year  <> '*') loop 
    
    begin
    
    --ɾ��model ����   ר���ʽ𸽼���  ����
    delete from p#dict_t_model t where t.TABLEID='06CAC5F4FF6748AD893A81BEB6E0EA7E'AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
    
    --ɾ��factor ����   ר���ʽ𸽼��� ����
    delete from p#dict_t_factor t where t.TABLEID='06CAC5F4FF6748AD893A81BEB6E0EA7E'AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
    
    --��mode���в���   ר���ʽ𸽼��� ����
    insert into p#dict_t_model (YEAR, PROVINCE, APPID, BGTLVL, DBTABLENAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLEID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, DESCFILE)
    values (v_row.YEAR, v_row.DISTRICTID, 'SPF', null, 'SPF_T_FATTACH', '4*04', '0000000000000000000000000000000', '1', '0', '0', '1', '0', '1', null, '0', null, 'ר���ʽ𸽼���', 28, null, null, null, null, '1', null, '1', '8288F140A88F4CCB8FF5BD9B4B28DCEF', '06CAC5F4FF6748AD893A81BEB6E0EA7E', '1', null, '0', '1', '0', null);

    --��dict_t_factor���в���  ר���ʽ𸽼��� ����
    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�Ƿ񵹼���', '08A7CE8A41BA43DDB67B2B14FE629F7E', null, 1, 3, 'ISDJ', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '�Ƿ񵹼���', '1', 13, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '09A1A5D61BB1475187A7C71812B4DDDA', null, 32, 3, 'FJLB', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�������', '1', 22, null, null, null, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�Ƿ�������', '10E4A46888D9460281730BB7B8F5FCC9', null, 1, 3, 'ISQZX', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '�Ƿ�������', '1', 12, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '13713DD909DD42A8B2C489EEB8E3EE3A', null, 200, 3, 'FIRATTNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '��������', '1', 19, null, null, null, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '��Ԫ�Ƿ��¼', '228282C34A484E97B5F85823B8E1326C', null, 1000, 3, 'CELLSECU', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '��Ԫ�Ƿ��¼', '1', 10, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '������', '22AD1A46D7EE4EA9A7FB2CB942F3A6D8', null, 1, 1, 'LEVELNO', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '�����', '1', 6, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�Ƿ���޸�', '31E9370B64064198B0A9D0CF37C68AF3', null, 1, 3, 'ISUPDATE', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '�Ƿ���޸�', '1', 14, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�Ƿ�ģ��', '36AE978538CA4746AE68CA4E9DB7C0B3', null, 1, 3, 'ISTEMPLATE', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '�Ƿ�ģ��', '1', 16, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, 'ͬ��״̬', '3E998796C9B149FAA038C087251A4DB4', null, 1, 3, 'SYNSTATUS', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, 'ͬ��״̬', '1', 11, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '448CDA22D09343E2BCA5C315BFB3DD3C', null, 1, 3, 'ISPUBLIC', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�Ƿ񹫿�', '1', 25, null, null, null, '0', null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, 'ԭʼ����', '5924135DDD1A4E9382CC1E67A2720398', null, 32, 3, 'ORIGCODE', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, 'ԭʼ����', '1', 15, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�Ƿ���Բ����¼�', '627F4FD3BA6B443AA89482B98E0A1333', null, 1, 3, 'ISINSERT', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '�Ƿ�ɲ���', '1', 8, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, 'ĩ������', '6A8C3D4111EB410C956CD0793705158C', null, 1, 3, 'ISLEAF', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '�Ƿ�ĩ��', '1', 7, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '��������', '7C11D3C1D6584034A9E34367BD22190C', null, 1, 3, 'BGTLVL', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '��������', '1', 1, null, null, null, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '7EE7B2FD0BDA4AE5A4FDBA9A4FF33AF9', null, 4, 3, 'FINYEAR', 'to_char(sysdate,''YYYY'')', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '����', '1', 21, null, null, null, '2', null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '���������ʾ��', '86112F97E46B4C96BCFA33842FFFD21C', null, 100, 3, 'FDCODE', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '��������ʾ��', '1', 5, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, 'ר���', '975711E265664A90BBAE6DD96C436A06', null, 32, 3, 'ATTACHID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, 'ר���', '1', 24, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, 'ר��ID', '9F150E14B24A4068A825A1D4AA20A644', null, 32, 3, 'SPFID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, 'ר��Ψһ��ʶ', '1', 18, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '��������', 'A1E89E6DA9D04A35A21B803F89D60655', null, 200, 3, 'ATTNAME', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '��������', '1', 26, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'A3426DA7F47F4621B16083DC6395A025', null, 4000, 3, 'NEEDUPDATE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, 'ˢ�±�־', '1', 20, null, null, null, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�б�ʶ', 'B214CD330E334B4BA15431F7F1F3C066', null, 32, 3, 'DATAKEY', 'sys_guid()', '080001', '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '1', '1', '0', '0', '0', '1', '0', '0', 1, '�б�־', '0', 2, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '��λ����', 'B4C3176E9A02476898A271EF56439F8E', null, 32, 3, 'AGENCYID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, 'Ԥ�㵥λ', '1', 3, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '����ϸ��', 'BF9276C68B8643C2BBF2BD8D2CB48CDD', null, 32, 3, 'DETCLASSID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '����ϸ��', '1', 23, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�������', 'C0D9498FD5964A07845FDE5BFA16D323', null, 9, 1, 'ORDERID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '�������', '1', 4, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�ϼ�����', 'DD0B09F91AC245929188C2CD797BAD36', null, 32, 3, 'SUPERID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '�ϼ�����', '1', 17, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, 'ģ���ʶ', 'FCAE0B1A91B6467186918EBA25A41611', null, 32, 3, 'TEMPLATEID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, 'ģ���ʶ', '1', 9, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

 
    --ɾ��model ����   ר���ʽ���Ŀ�걨������  ����
    delete from p#dict_t_model t where t.TABLEID='7794F9126E7640219A1D025708DB1400' AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
  
    --ɾ��factor ����   ר���ʽ���Ŀ�걨������ ����
    delete from p#dict_t_factor t where t.TABLEID='7794F9126E7640219A1D025708DB1400'AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
  
    --��mode���в���   ר���ʽ���Ŀ�걨������ ����
    insert into p#dict_t_model (YEAR, PROVINCE,APPID, BGTLVL, DBTABLENAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLEID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, DESCFILE)
    values (v_row.YEAR, v_row.DISTRICTID, 'SPF', null, 'SPF_T_FSPF2JATTACH', '4*15', '0000000000000000000000000000000', '1', '0', '0', '1', '0', '1', null, '0', null, 'ר���ʽ���Ŀ�걨������', 29, null, null, null, null, '1', null, '1', '8288F140A88F4CCB8FF5BD9B4B28DCEF', '7794F9126E7640219A1D025708DB1400', '1', null, '0', '1', '0', null);

    --��dict_t_factor���в���  ר���ʽ���Ŀ�걨������ ����
  
    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�Ƿ���Բ����¼�', '18AC0F557B78437687C6A9F234E60EF0', null, 1, 3, 'ISINSERT', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�Ƿ�ɲ���', '1', 8, null, null, 0, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�Ƿ񵹼���', '1DB4F7818CB341378788E3E85C549BD0', null, 1, 3, 'ISDJ', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�Ƿ񵹼���', '1', 13, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '406900AD78F447778F4F3B8319D0AD9E', null, 200, 3, 'FIRATTNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '��������', '1', 18, null, null, null, null, 300, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, 'ԭʼ����', '43236017E4ED424F8B7860A273149337', null, 32, 3, 'ORIGCODE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, 'ԭʼ����', '1', 15, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '51B96B52A0574BB2A8DDFD09DDD75529', null, 1, 3, 'BGTLVL', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '��������', '1', 1, null, null, 0, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '������', '61B1E302F7D94491AEBC155E6C51DCA0', null, 1, 1, 'LEVELNO', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�����', '1', 6, null, null, 0, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, 'ͬ��״̬', '688FFA6F29394A2E8BA45DC986B349C2', null, 1, 3, 'SYNSTATUS', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, 'ͬ��״̬', '1', 11, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, 'DATAKEY', '6BF5F0AD05624C5FA6212D9E746FF44D', null, 32, 3, 'DATAKEY', 'sys_guid()', '080001', '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '1', '0', '1', '0', '0', 1, '�б�־', '0', 2, null, null, 0, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '710B6925ACA2425DB48398DC8344D0B3', null, 50, 3, 'DETCLASSID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '����ϸ��', '1', 19, null, null, null, '0', null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, 'ĩ������', '75D85FEE7DA54B55846B3F4A3BAE6242', null, 1, 3, 'ISLEAF', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�Ƿ�ĩ��', '1', 7, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '��Ԫ�Ƿ��¼', '822F438E21594C0AA8CFBFE2F5F82F35', null, 1000, 3, 'CELLSECU', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '��Ԫ�Ƿ��¼', '1', 10, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '93DF4A328DC64081A8FD621C44E3CA95', null, 32, 3, 'SPFID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, 'ר��Ψһ��ʶ', '0', 25, null, null, null, null, 100, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�������', 'A3109662E94F49C8A6434144DCF77D8C', null, 9, 1, 'ORDERID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�������', '1', 4, null, null, 0, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'A4B7E87B14174967A7696D2EADAB1F3B', null, 4000, 3, 'NEEDUPDATE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, 'ˢ�±�־', '1', 22, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�Ƿ�ģ��', 'A90C5BBB4AFB40FB9DD5BA24CD1BA0C3', null, 1, 3, 'ISTEMPLATE', '''1''', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�Ƿ�ģ��', '1', 16, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'AD6D6E7FB74B44918FCA8E03A46CECB0', null, 200, 3, 'ATTNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '��������', '1', 27, null, null, null, null, 300, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '���������ʾ��', 'BA3BB34F248A4951B3425230CCF3EFA6', null, 100, 3, 'FDCODE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '��������ʾ��', '1', 5, null, null, 0, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�ϼ�����', 'BCCE771ED717415D825428E19D0CB767', null, 32, 3, 'SUPERID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�ϼ�����', '1', 17, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'BEBB21C553144320B885EA5C519AD059', null, 1000, 3, 'REMARK', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '��Ŀ����˵��', '1', 20, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'C935ADD59CAB4459A95A38CD8910F0B1', null, 1, 3, 'ISMUST', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�Ƿ���踽��', '1', 21, null, null, null, '0', 100, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, 'ģ���ʶ', 'D351B511D25649DF9E71037F75BAAA0A', null, 32, 3, 'TEMPLATEID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, 'ģ���ʶ', '1', 9, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'DB10182EC50D408FBC5131CD60E66076', null, 32, 3, 'TEMPLATENAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '����ģ��', '1', 26, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F1CAA7B6A1E946D78DA9A0E4CA00518B', null, 4, 3, 'FINYEAR', 'to_char(sysdate,''YYYY'')', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '����', '1', 23, null, null, null, '2', null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�Ƿ�������', 'F76B15E5350C48BB8E063B96B5889FA6', null, 1, 3, 'ISQZX', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�Ƿ�������', '1', 12, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'FEE7319E35CA476ABC5EC601B8FB3011', null, 32, 3, 'ATTACHID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '��Ŀ����', '1', 24, null, null, null, 'C', 200, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '�Ƿ���޸�', 'FFDDB31ABD724129ABB7911298BA5799', null, 1, 3, 'ISUPDATE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '�Ƿ���޸�', '1', 14, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);
    
    --ɾ��model ����   ��Ŀ������  ����
    delete from p#dict_t_model t where t.TABLEID='F6A4E2F5DF1E96F7E040A8C0200352B4' AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
    
    --ɾ��factor ����   ��Ŀ������ ����
    delete from p#dict_t_factor t where t.TABLEID='F6A4E2F5DF1E96F7E040A8C0200352B4' AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
    
    --��mode���в���   ��Ŀ������ ����
    insert into p#dict_t_model (YEAR, PROVINCE,APPID, BGTLVL, DBTABLENAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLEID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, DESCFILE)
    values (v_row.YEAR, v_row.DISTRICTID, 'SPF', '1', 'SPF_T_PATTACH', '5*04', '0000000000000000000000000000000', '3', '0', '0', '1', '1', '1', '0', '0', null, '��Ŀ������', 0, null, null, null, '<CLOB>', null, '��Ŀ������', '1', 'B29F2474AA8F40938D299880CFB87BEF', 'F6A4E2F5DF1E96F7E040A8C0200352B4', '1', null, '1', '0', '0', null);

    --��dict_t_factor���в���  ��Ŀ������ ����
    
    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '0D91D5735BF44943BFCA0902B1F17132', null, 1, 3, 'ISUPDATE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '�Ƿ�ɸ���', '1', 66, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '189B0250072B4189A0DCD6274CCC1D44', null, 32, 3, 'SUPERID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '�ϼ�����', '1', 59, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '294C5D2A1CF349D5B6E4D5F352ADE465', null, 1, 3, 'ISTEMPLATE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '�Ƿ�ģ��', '1', 65, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '3DF6ADEE5AE5456CA32E87F4E43BDAB3', null, 1, 3, 'ISQZX', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '�Ƿ�������', '1', 67, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '73E282D73CAA4FA1946C38D209252947', null, 200, 3, 'FDCODE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '��������', '1', 64, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'B87E23FFAED446C28E5468BFF3E6C80E', null, 1, 3, 'ISDJ', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '�Ƿ񵹼���', '1', 68, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F33BF9AA1E8C48A3A9D94734054659AD', null, 32, 3, 'TEMPLATEID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, 'ģ���ʶ', '1', 58, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A4E2F5DF1F96F7E040A8C0200352B4', null, 1, 3, 'BGTLVL', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '0', 1, '��������', '1', 2, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A4E2F5DF2096F7E040A8C0200352B4', null, 32, 3, 'DATAKEY', 'sys_guid()', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '1', '1', '0', '1', '0', '1', '0', '0', 1, '�б�ʶ', '0', 6, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F6A4E2F5DF2296F7E040A8C0200352B4', null, 9, 1, 'ORDERID', '1', null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1', 1, '�������', '1', 50, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A4E2F5DF2396F7E040A8C0200352B4', null, 32, 3, 'PROJECTID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '0', 1, '��ĿID', '0', 57, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A4E2F5DF2E96F7E040A8C0200352B4', null, 1000, 3, 'TEMPLETREMARK', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '����ģ��˵��', '1', 42, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', '5|doc,docx,xls,xlsx,jpg,pdf', '������С���ܳ���5�ף�M��', 'F6A5CEE48FD76D02E040A8C0200352B5', null, 32, 3, 'ATTACHID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '��Ŀ����', '1', 19, null, null, 0, 'C', 250, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A5CEE48FD86D02E040A8C0200352B6', null, 1000, 3, 'MEMO', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '����˵��', '1', 26, null, null, 0, '0', 300, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F6A5CEE48FD96D02E040A8C0200352B7', '1F61620487DA498B8BD0F2D015054BEB', 1, 6, 'ISMUST', '0', null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '�Ƿ����', '1', 43, null, null, 0, '4', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A5CEE48FDC6D02E040A8C0200352B8', null, 32, 3, 'TEMPLETID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '����ģ��', '1', 18, null, null, 0, 'C', 200, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A5CEE48FDE6D02E040A8C0200352B9', null, 200, 3, 'ATTACHNAME', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '��������', '1', 17, null, null, 0, '0', 200, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F7988D561EC0854DE040A8C020036751', null, 9, 1, 'LEVELNO', '0', null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '������', '1', 48, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F7988D561EC1854DE040A8C020036751', null, 1, 3, 'ISINSERT', '''0''', null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '�Ƿ�ɲ����¼�', '1', 45, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F799A45F9C6E7035E040A8C020036AEE', null, 1, 3, 'ISLEAF', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '�Ƿ�ĩ��', '1', 46, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);
  
    end; 
    
  end loop;
  --ˢ��ͼ
  
  
  sys_p_recreate_views('06CAC5F4FF6748AD893A81BEB6E0EA7E');
  sys_p_recreate_views('F6A4E2F5DF1E96F7E040A8C0200352B4');
  sys_p_recreate_views('7794F9126E7640219A1D025708DB1400');
  
end;
--SELECT GLOBAL_MULTYear_CZ.Secu_f_GLOBAL_SetPARM('','1400','2016','15002016') from dual;
--���ݷ������븽����
