declare
v_count number(1);--查看表是否存在
begin 
  select count(*) INTO v_count from user_tables t where  t.table_name = 'P#SPF_T_PATTACH';
  if v_count > 0
   then execute immediate Q'{DROP TABLE P#SPF_T_PATTACH}' ;
  end if;
  
  --创建专项资金附件表
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
  
  --添加专项资金附件表列注释
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.bgtlvl
        is '财政级次'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.datakey
        is '行标识'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.orderid
        is '排序序号'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.projectid
        is '项目ID'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.attachid
        is '项目附件'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.memo
        is '附件说明'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.ismust
        is '是否必须'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.templetid
        is '附件模板'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.templetremark
        is '附件模板说明'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.attachname
        is '附件名称'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.isleaf
        is '是否末级'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.levelno
        is '级次码'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.isinsert
        is '是否可插入下级'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.templateid
        is '模板标识'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.superid
        is '上级编码'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.fdcode
        is '浮动编码'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.istemplate
        is '是否模板'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.isupdate
        is '是否可更新'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.isqzx
        is '是否其中项'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_PATTACH.isdj
        is '是否倒挤行'
    }';
    
  --刷P#SPF_T_PATTACH表触发器
  SYS_P_PARTITION_TABLE('P#SPF_T_PATTACH') ;
  
  select count(*) INTO v_count from user_tables t where  t.table_name = 'P#SPF_T_FSPF2JATTACH';
  if v_count > 0
   then execute immediate Q'{DROP TABLE P#SPF_T_FSPF2JATTACH}' ;
  end if;
  
  --创建专项资金项目申报附件表
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
  
  --添加专项资金项目申报附件表列注释
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.bgtlvl
        is '财政级次'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.datakey
        is '行标志'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.orderid
        is '排序序号'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.fdcode
        is '浮动表显示列'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.levelno
        is '层次码'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.isleaf
        is '是否末级'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.isinsert
        is '是否可插入'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.templateid
        is '模板标识'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.cellsecu
        is '单元是否可录'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.synstatus
        is '同步状态'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.isqzx
        is '是否其中项'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.isdj
        is '是否倒挤行'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.isupdate
        is '是否可修改'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.origcode
        is '原始编码'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.istemplate
        is '是否模板'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.superid
        is '上级编码'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.firattname
        is '附件大类'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.detclassid
        is '附件细类'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.remark
        is '项目附件说明'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.ismust
        is '是否必需附件'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.needupdate
        is '刷新标志'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.finyear
        is '财年'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.attachid
        is '项目附件'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.spfid
        is '专项唯一标识'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.templatename
        is '附件模板'
    }';
    execute immediate Q'{
      comment on column P#SPF_T_FSPF2JATTACH.attname
        is '附件名称'
    }';
  
  --创建主外键
  execute immediate Q'{
    alter table P#SPF_T_FSPF2JATTACH
      add constraint P#SPF_T_FSPF2JATTACH primary key (PROVINCE, YEAR, STATUS, DATAKEY)
  }';
  
    --刷P#SPF_T_FSPF2JATTACH表触发器
  SYS_P_PARTITION_TABLE('P#SPF_T_FSPF2JATTACH') ;
  
  
  --向P#表中循环插入数据
  for v_row in (select * from pub_t_partition_divid t where t.year  <> '*') loop 
    
    begin
    
    --删除model 表中   专项资金附件表  数据
    delete from p#dict_t_model t where t.TABLEID='06CAC5F4FF6748AD893A81BEB6E0EA7E'AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
    
    --删除factor 表中   专项资金附件表 数据
    delete from p#dict_t_factor t where t.TABLEID='06CAC5F4FF6748AD893A81BEB6E0EA7E'AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
    
    --向mode表中插入   专项资金附件表 数据
    insert into p#dict_t_model (YEAR, PROVINCE, APPID, BGTLVL, DBTABLENAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLEID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, DESCFILE)
    values (v_row.YEAR, v_row.DISTRICTID, 'SPF', null, 'SPF_T_FATTACH', '4*04', '0000000000000000000000000000000', '1', '0', '0', '1', '0', '1', null, '0', null, '专项资金附件表', 28, null, null, null, null, '1', null, '1', '8288F140A88F4CCB8FF5BD9B4B28DCEF', '06CAC5F4FF6748AD893A81BEB6E0EA7E', '1', null, '0', '1', '0', null);

    --向dict_t_factor表中插入  专项资金附件表 数据
    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '是否倒挤行', '08A7CE8A41BA43DDB67B2B14FE629F7E', null, 1, 3, 'ISDJ', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '是否倒挤行', '1', 13, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '09A1A5D61BB1475187A7C71812B4DDDA', null, 32, 3, 'FJLB', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '附件类别', '1', 22, null, null, null, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '是否其中项', '10E4A46888D9460281730BB7B8F5FCC9', null, 1, 3, 'ISQZX', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '是否其中项', '1', 12, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '13713DD909DD42A8B2C489EEB8E3EE3A', null, 200, 3, 'FIRATTNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '附件大类', '1', 19, null, null, null, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '单元是否可录', '228282C34A484E97B5F85823B8E1326C', null, 1000, 3, 'CELLSECU', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '单元是否可录', '1', 10, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '级次码', '22AD1A46D7EE4EA9A7FB2CB942F3A6D8', null, 1, 1, 'LEVELNO', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '层次码', '1', 6, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '是否可修改', '31E9370B64064198B0A9D0CF37C68AF3', null, 1, 3, 'ISUPDATE', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '是否可修改', '1', 14, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '是否模板', '36AE978538CA4746AE68CA4E9DB7C0B3', null, 1, 3, 'ISTEMPLATE', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '是否模板', '1', 16, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '同步状态', '3E998796C9B149FAA038C087251A4DB4', null, 1, 3, 'SYNSTATUS', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '同步状态', '1', 11, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '448CDA22D09343E2BCA5C315BFB3DD3C', null, 1, 3, 'ISPUBLIC', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否公开', '1', 25, null, null, null, '0', null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '原始编码', '5924135DDD1A4E9382CC1E67A2720398', null, 32, 3, 'ORIGCODE', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '原始编码', '1', 15, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '是否可以插入下级', '627F4FD3BA6B443AA89482B98E0A1333', null, 1, 3, 'ISINSERT', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '是否可插入', '1', 8, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '末级编码', '6A8C3D4111EB410C956CD0793705158C', null, 1, 3, 'ISLEAF', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '是否末级', '1', 7, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '财政级次', '7C11D3C1D6584034A9E34367BD22190C', null, 1, 3, 'BGTLVL', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '财政级次', '1', 1, null, null, null, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '7EE7B2FD0BDA4AE5A4FDBA9A4FF33AF9', null, 4, 3, 'FINYEAR', 'to_char(sysdate,''YYYY'')', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '财年', '1', 21, null, null, null, '2', null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '浮动表的显示列', '86112F97E46B4C96BCFA33842FFFD21C', null, 100, 3, 'FDCODE', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '浮动表显示列', '1', 5, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '专项附件', '975711E265664A90BBAE6DD96C436A06', null, 32, 3, 'ATTACHID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '专项附件', '1', 24, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '专项ID', '9F150E14B24A4068A825A1D4AA20A644', null, 32, 3, 'SPFID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '专项唯一标识', '1', 18, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '附件名称', 'A1E89E6DA9D04A35A21B803F89D60655', null, 200, 3, 'ATTNAME', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '附件名称', '1', 26, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'A3426DA7F47F4621B16083DC6395A025', null, 4000, 3, 'NEEDUPDATE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', 1, '刷新标志', '1', 20, null, null, null, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '行标识', 'B214CD330E334B4BA15431F7F1F3C066', null, 32, 3, 'DATAKEY', 'sys_guid()', '080001', '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '1', '1', '0', '0', '0', '1', '0', '0', 1, '行标志', '0', 2, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '单位编码', 'B4C3176E9A02476898A271EF56439F8E', null, 32, 3, 'AGENCYID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '0', '0', '0', '0', '1', 1, '预算单位', '1', 3, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '附件细类', 'BF9276C68B8643C2BBF2BD8D2CB48CDD', null, 32, 3, 'DETCLASSID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '附件细类', '1', 23, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '排序序号', 'C0D9498FD5964A07845FDE5BFA16D323', null, 9, 1, 'ORDERID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '排序序号', '1', 4, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '上级编码', 'DD0B09F91AC245929188C2CD797BAD36', null, 32, 3, 'SUPERID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '上级编码', '1', 17, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '模板标识', 'FCAE0B1A91B6467186918EBA25A41611', null, 32, 3, 'TEMPLATEID', null, null, null, null, null, null, null, '0', '0', null, '0', '1', '0', '1', '0', '0', '0', '1', 1, '模板标识', '1', 9, null, null, 0, null, null, '1', '0', '06CAC5F4FF6748AD893A81BEB6E0EA7E', null, '0', null);

 
    --删除model 表中   专项资金项目申报附件表  数据
    delete from p#dict_t_model t where t.TABLEID='7794F9126E7640219A1D025708DB1400' AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
  
    --删除factor 表中   专项资金项目申报附件表 数据
    delete from p#dict_t_factor t where t.TABLEID='7794F9126E7640219A1D025708DB1400'AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
  
    --向mode表中插入   专项资金项目申报附件表 数据
    insert into p#dict_t_model (YEAR, PROVINCE,APPID, BGTLVL, DBTABLENAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLEID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, DESCFILE)
    values (v_row.YEAR, v_row.DISTRICTID, 'SPF', null, 'SPF_T_FSPF2JATTACH', '4*15', '0000000000000000000000000000000', '1', '0', '0', '1', '0', '1', null, '0', null, '专项资金项目申报附件表', 29, null, null, null, null, '1', null, '1', '8288F140A88F4CCB8FF5BD9B4B28DCEF', '7794F9126E7640219A1D025708DB1400', '1', null, '0', '1', '0', null);

    --向dict_t_factor表中插入  专项资金项目申报附件表 数据
  
    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '是否可以插入下级', '18AC0F557B78437687C6A9F234E60EF0', null, 1, 3, 'ISINSERT', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否可插入', '1', 8, null, null, 0, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '是否倒挤行', '1DB4F7818CB341378788E3E85C549BD0', null, 1, 3, 'ISDJ', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否倒挤行', '1', 13, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '406900AD78F447778F4F3B8319D0AD9E', null, 200, 3, 'FIRATTNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '附件大类', '1', 18, null, null, null, null, 300, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '原始编码', '43236017E4ED424F8B7860A273149337', null, 32, 3, 'ORIGCODE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '原始编码', '1', 15, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '51B96B52A0574BB2A8DDFD09DDD75529', null, 1, 3, 'BGTLVL', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '财政级次', '1', 1, null, null, 0, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '级次码', '61B1E302F7D94491AEBC155E6C51DCA0', null, 1, 1, 'LEVELNO', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '层次码', '1', 6, null, null, 0, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '同步状态', '688FFA6F29394A2E8BA45DC986B349C2', null, 1, 3, 'SYNSTATUS', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '同步状态', '1', 11, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, 'DATAKEY', '6BF5F0AD05624C5FA6212D9E746FF44D', null, 32, 3, 'DATAKEY', 'sys_guid()', '080001', '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '1', '0', '1', '0', '0', 1, '行标志', '0', 2, null, null, 0, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '710B6925ACA2425DB48398DC8344D0B3', null, 50, 3, 'DETCLASSID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '附件细类', '1', 19, null, null, null, '0', null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '末级编码', '75D85FEE7DA54B55846B3F4A3BAE6242', null, 1, 3, 'ISLEAF', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否末级', '1', 7, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '单元是否可录', '822F438E21594C0AA8CFBFE2F5F82F35', null, 1000, 3, 'CELLSECU', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '单元是否可录', '1', 10, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '93DF4A328DC64081A8FD621C44E3CA95', null, 32, 3, 'SPFID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '专项唯一标识', '0', 25, null, null, null, null, 100, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '排序序号', 'A3109662E94F49C8A6434144DCF77D8C', null, 9, 1, 'ORDERID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '排序序号', '1', 4, null, null, 0, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'A4B7E87B14174967A7696D2EADAB1F3B', null, 4000, 3, 'NEEDUPDATE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '刷新标志', '1', 22, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '是否模板', 'A90C5BBB4AFB40FB9DD5BA24CD1BA0C3', null, 1, 3, 'ISTEMPLATE', '''1''', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否模板', '1', 16, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'AD6D6E7FB74B44918FCA8E03A46CECB0', null, 200, 3, 'ATTNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '附件名称', '1', 27, null, null, null, null, 300, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '浮动表的显示列', 'BA3BB34F248A4951B3425230CCF3EFA6', null, 100, 3, 'FDCODE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '浮动表显示列', '1', 5, null, null, 0, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '上级编码', 'BCCE771ED717415D825428E19D0CB767', null, 32, 3, 'SUPERID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '上级编码', '1', 17, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'BEBB21C553144320B885EA5C519AD059', null, 1000, 3, 'REMARK', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '项目附件说明', '1', 20, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'C935ADD59CAB4459A95A38CD8910F0B1', null, 1, 3, 'ISMUST', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否必需附件', '1', 21, null, null, null, '0', 100, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '模板标识', 'D351B511D25649DF9E71037F75BAAA0A', null, 32, 3, 'TEMPLATEID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '模板标识', '1', 9, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'DB10182EC50D408FBC5131CD60E66076', null, 32, 3, 'TEMPLATENAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '附件模板', '1', 26, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F1CAA7B6A1E946D78DA9A0E4CA00518B', null, 4, 3, 'FINYEAR', 'to_char(sysdate,''YYYY'')', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '财年', '1', 23, null, null, null, '2', null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '是否其中项', 'F76B15E5350C48BB8E063B96B5889FA6', null, 1, 3, 'ISQZX', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否其中项', '1', 12, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'FEE7319E35CA476ABC5EC601B8FB3011', null, 32, 3, 'ATTACHID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '项目附件', '1', 24, null, null, null, 'C', 200, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, '是否可修改', 'FFDDB31ABD724129ABB7911298BA5799', null, 1, 3, 'ISUPDATE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '是否可修改', '1', 14, null, null, null, null, null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);
    
    --删除model 表中   项目附件表  数据
    delete from p#dict_t_model t where t.TABLEID='F6A4E2F5DF1E96F7E040A8C0200352B4' AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
    
    --删除factor 表中   项目附件表 数据
    delete from p#dict_t_factor t where t.TABLEID='F6A4E2F5DF1E96F7E040A8C0200352B4' AND PROVINCE = v_row.DISTRICTID AND YEAR =v_row.YEAR ;
    
    --向mode表中插入   项目附件表 数据
    insert into p#dict_t_model (YEAR, PROVINCE,APPID, BGTLVL, DBTABLENAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLEID, TABLETYPE, TABSWHERE, ISBAK, ISALLDISTRICT, ISALLYEAR, DESCFILE)
    values (v_row.YEAR, v_row.DISTRICTID, 'SPF', '1', 'SPF_T_PATTACH', '5*04', '0000000000000000000000000000000', '3', '0', '0', '1', '1', '1', '0', '0', null, '项目附件表', 0, null, null, null, '<CLOB>', null, '项目附件表', '1', 'B29F2474AA8F40938D299880CFB87BEF', 'F6A4E2F5DF1E96F7E040A8C0200352B4', '1', null, '1', '0', '0', null);

    --向dict_t_factor表中插入  项目附件表 数据
    
    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '0D91D5735BF44943BFCA0902B1F17132', null, 1, 3, 'ISUPDATE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '是否可更新', '1', 66, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '189B0250072B4189A0DCD6274CCC1D44', null, 32, 3, 'SUPERID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '上级编码', '1', 59, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '294C5D2A1CF349D5B6E4D5F352ADE465', null, 1, 3, 'ISTEMPLATE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '是否模板', '1', 65, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '3DF6ADEE5AE5456CA32E87F4E43BDAB3', null, 1, 3, 'ISQZX', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '是否其中项', '1', 67, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, '73E282D73CAA4FA1946C38D209252947', null, 200, 3, 'FDCODE', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '浮动编码', '1', 64, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'B87E23FFAED446C28E5468BFF3E6C80E', null, 1, 3, 'ISDJ', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '是否倒挤行', '1', 68, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F33BF9AA1E8C48A3A9D94734054659AD', null, 32, 3, 'TEMPLATEID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '模板标识', '1', 58, null, null, 0, null, null, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A4E2F5DF1F96F7E040A8C0200352B4', null, 1, 3, 'BGTLVL', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '0', 1, '财政级次', '1', 2, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A4E2F5DF2096F7E040A8C0200352B4', null, 32, 3, 'DATAKEY', 'sys_guid()', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '1', '1', '0', '1', '0', '1', '0', '0', 1, '行标识', '0', 6, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F6A4E2F5DF2296F7E040A8C0200352B4', null, 9, 1, 'ORDERID', '1', null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1', 1, '排序序号', '1', 50, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A4E2F5DF2396F7E040A8C0200352B4', null, 32, 3, 'PROJECTID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '0', 1, '项目ID', '0', 57, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A4E2F5DF2E96F7E040A8C0200352B4', null, 1000, 3, 'TEMPLETREMARK', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '附件模板说明', '1', 42, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', '5|doc,docx,xls,xlsx,jpg,pdf', '附件大小不能超过5兆（M）', 'F6A5CEE48FD76D02E040A8C0200352B5', null, 32, 3, 'ATTACHID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '项目附件', '1', 19, null, null, 0, 'C', 250, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A5CEE48FD86D02E040A8C0200352B6', null, 1000, 3, 'MEMO', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '附件说明', '1', 26, null, null, 0, '0', 300, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F6A5CEE48FD96D02E040A8C0200352B7', '1F61620487DA498B8BD0F2D015054BEB', 1, 6, 'ISMUST', '0', null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '是否必须', '1', 43, null, null, 0, '4', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A5CEE48FDC6D02E040A8C0200352B8', null, 32, 3, 'TEMPLETID', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '附件模板', '1', 18, null, null, 0, 'C', 200, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, '1', null, null, 'F6A5CEE48FDE6D02E040A8C0200352B9', null, 200, 3, 'ATTACHNAME', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '附件名称', '1', 17, null, null, 0, '0', 200, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F7988D561EC0854DE040A8C020036751', null, 9, 1, 'LEVELNO', '0', null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '级次码', '1', 48, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F7988D561EC1854DE040A8C020036751', null, 1, 3, 'ISINSERT', '''0''', null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '是否可插入下级', '1', 45, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);

    insert into p#dict_t_factor (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.YEAR, v_row.DISTRICTID, null, null, null, null, null, null, 'F799A45F9C6E7035E040A8C020036AEE', null, 1, 3, 'ISLEAF', null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '是否末级', '1', 46, null, null, 0, '0', 0, '1', '0', 'F6A4E2F5DF1E96F7E040A8C0200352B4', null, '0', null);
  
    end; 
    
  end loop;
  --刷视图
  
  
  sys_p_recreate_views('06CAC5F4FF6748AD893A81BEB6E0EA7E');
  sys_p_recreate_views('F6A4E2F5DF1E96F7E040A8C0200352B4');
  sys_p_recreate_views('7794F9126E7640219A1D025708DB1400');
  
end;
--SELECT GLOBAL_MULTYear_CZ.Secu_f_GLOBAL_SetPARM('','1400','2016','15002016') from dual;
--根据分区插入附件表
