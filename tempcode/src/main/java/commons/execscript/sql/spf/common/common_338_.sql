declare 
viewNumber number;
begin
	select count(1) into viewNumber from user_tables where table_NAME='P#SPF_T_CUSTODY';
    if viewNumber = 0 then
  	--����ר���йܱ�
	  EXECUTE IMMEDIATE Q'{
	    create table P#SPF_T_CUSTODY
	    (
	      status           CHAR(1) default '1' not null,
	      dbversion        TIMESTAMP(6) default SYSDATE,
	      datakey          VARCHAR2(32) default sys_guid() not null,
	      cusrodydept      VARCHAR2(32),
	      custodydeptid    VARCHAR2(32),
	      custodydeclrange VARCHAR2(32),
	      spfid            VARCHAR2(32)
	    )
	  }';
  EXECUTE IMMEDIATE Q'{
    comment on table P#SPF_T_CUSTODY
      is '�й�����'
  }';
  
  EXECUTE IMMEDIATE Q'{
    comment on column P#SPF_T_CUSTODY.datakey
      is '�б�־'
  }';
  
  EXECUTE IMMEDIATE Q'{
    comment on column P#SPF_T_CUSTODY.cusrodydept
      is '�й�_���ܲ���'
  }';
  
  EXECUTE IMMEDIATE Q'{
    comment on column P#SPF_T_CUSTODY.custodydeptid
      is '�й�_��ڴ���'
  }';
  EXECUTE IMMEDIATE Q'{
    comment on column P#SPF_T_CUSTODY.custodydeclrange
      is '��λ�걨��Χ'
  }';
  EXECUTE IMMEDIATE Q'{
    comment on column P#SPF_T_CUSTODY.spfid
      is 'ר��Ψһ��ʾ'
  }';
  
 select count(1) into viewNumber from user_views where VIEW_NAME='SPF_T_CUSTODY';
    if viewNumber > 0 then
    --ɾ��  ר���йܱ�������ͼ
    EXECUTE IMMEDIATE Q'{
      drop view SPF_T_CUSTODY
    }';
  end if;
  
  --����   ר���йܱ�������ͼ
  EXECUTE IMMEDIATE Q'{
    CREATE OR REPLACE VIEW SPF_T_CUSTODY AS
      SELECT DATAKEY,
             CUSRODYDEPT,
             CUSTODYDEPTID,
             CUSTODYDECLRANGE,
             SPFID,
             STATUS,
             DBVERSION
        FROM P#SPF_T_CUSTODY
       WHERE 1 = 1
         AND STATUS = '1'
  }';
  
  --ɾ��   ר���йܱ�������ͼ model ����
  delete from p#dict_t_model where tableid='72E25F4E6AAB427CA5794542341D1DC2';
  --ɾ��   ר���йܱ�������ͼ factor ����
  delete from p#dict_t_factor where tableid='72E25F4E6AAB427CA5794542341D1DC2';
  
  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
    
    begin
      --����  ר���йܱ�������ͼ model ����
      insert into p#dict_t_model (YEAR, PROVINCE, APPID, BGTLVL, DBTABLENAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLEID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, INITSQLBTNNAME, ISEXCELIMP, DESCFILE)
      values (v_row.YEAR, v_row.DISTRICTID,'SPF', null, 'SPF_T_CUSTODY', 'TGSZ', '0000000000000000000000000000000', '1', '0', '0', '1', '0', '1', '0', '0', null, '�й�����', 99, null, null, null, null, '1', null, '1', '8288F140A88F4CCB8FF5BD9B4B28DCEF', '72E25F4E6AAB427CA5794542341D1DC2', '1', null, '0', '1', '1', null, '0',null);
      
      --����   ר���йܱ�������ͼ factor ����
      insert into p#dict_t_factor (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK)
      values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, '1D71A8FE441A4B7F9B74199A2A7904E9', null, 32, 3, 'CUSTODYDECLRANGE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '��λ�걨��Χ', '1', 4, null, null, 0, '0', null, '1', '0', '72E25F4E6AAB427CA5794542341D1DC2', null, '0');

      insert into p#dict_t_factor (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK)
      values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, '61E869C3699149CFA3E95723085F067A', null, 32, 3, 'SPFID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, 'ר��Ψһ��ʾ', '1', 5, null, null, 0, '0', null, '1', '0', '72E25F4E6AAB427CA5794542341D1DC2', null, '0');

      insert into p#dict_t_factor (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK)
      values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, '647637C9BDDC48869C046E27AFE0835C', null, 32, 3, 'CUSTODYDEPTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '�й�(��ڴ���)', '1', 3, null, null, 0, '0', null, '1', '0', '72E25F4E6AAB427CA5794542341D1DC2', null, '0');

      insert into p#dict_t_factor (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK)
      values (v_row.YEAR, v_row.DISTRICTID,null, null, null, null, null, null, 'E588BFB3FE1F481BB0F67DEF053F8D3B', null, 32, 3, 'CUSRODYDEPT', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '�й�(���ܲ���)', '1', 2, null, null, 0, '0', null, '1', '0', '72E25F4E6AAB427CA5794542341D1DC2', null, '0');

      insert into p#dict_t_factor (YEAR, PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK)
      values (v_row.YEAR, v_row.DISTRICTID,'�б�־', null, null, null, null, '�б�־', 'FF32D6813A414982999BC6611F730ADB', null, 32, 3, 'DATAKEY', 'sys_guid()', null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '1', '0', '1', 1, '�б�־', '0', 1, null, null, null, null, null, '1', '0', '72E25F4E6AAB427CA5794542341D1DC2', null, '0');
    end;
    
  end loop;
 
 end if ;
end;
--ר���ʽ��й�������ͼ�޸ļ��ֶ�����
