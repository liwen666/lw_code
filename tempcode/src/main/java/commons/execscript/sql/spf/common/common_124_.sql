DECLARE
   --定义变量
   V_COL INT; 

BEGIN
   SELECT COUNT(*) INTO V_COL   FROM USER_TABLES WHERE TABLE_NAME ='P#SPF_T_P_C_BATCHES';
     
     IF V_COL=0 THEN

        --创建合同批次表
        EXECUTE IMMEDIATE Q'{
        create table P#SPF_T_P_C_BATCHES
        (
          province            VARCHAR2(9) not null,
          year                CHAR(4) not null,
          status              CHAR(1) default '1' not null,
          dbversion           TIMESTAMP(6) default SYSDATE,
          datakey             VARCHAR2(32) default sys_guid() not null,
          orderid             NUMBER(9) default 0,
          needupdate          VARCHAR2(4000),
          contract_id         VARCHAR2(32) not null,
          contract_batch_name VARCHAR2(32),
          sum_payment_beliel  NUMBER(8) default 0,
          outline             VARCHAR2(32),
          contract_batch_id   VARCHAR2(32) not null,
          sum_payment_money   NUMBER(24,6),
          money               NUMBER(24,6),
          batch_money         NUMBER(24,6)
        )}';

        --创建主外键
        EXECUTE IMMEDIATE Q'{
          alter table P#SPF_T_P_C_BATCHES
            add constraint PK_P#SPF_T_P_C_BATCHES primary key (PROVINCE, YEAR, STATUS, CONTRACT_BATCH_ID, DATAKEY)
          }';
        sys_p_partition_table('P#SPF_T_P_C_BATCHES');
        
        --创建触发器
        EXECUTE IMMEDIATE Q'{
          CREATE OR REPLACE TRIGGER TR_P#SPF_T_P_C_BATCHES
          BEFORE INSERT OR UPDATE ON  P#SPF_T_P_C_BATCHES FOR EACH ROW
          BEGIN
            IF INSERTING THEN
              :NEW.PROVINCE:=NVL(:NEW.PROVINCE,GLOBAL_MULTYEAR_CZ.V_PMDIVID);
              :NEW.YEAR:=NVL(:NEW.YEAR,GLOBAL_MULTYEAR_CZ.V_PMYEAR);
              :NEW.DBVERSION := CASE WHEN TO_CHAR(:NEW.DBVERSION, 'YYYY-MM-DD') = '2012-01-01' THEN TO_DATE('2012-01-01', 'YYYY-MM-DD') ELSE SYSDATE END;
            END IF;
            IF (TO_CHAR(:NEW.DBVERSION,'YYYY-MM-DD') ='2012-01-01' AND ((UPDATING AND UPDATING('DBVERSION')) OR INSERTING ) ) THEN
              RETURN ;
            END IF;
            IF UPDATING THEN
              :NEW.DBVERSION:=SYSDATE;
            END IF;
          END TR_P#SPF_T_P_C_BATCHES;
          }';
        
        --删除合同批次model
        DELETE FROM dict_t_model where tableid ='F7099198E0819B06E040A8C020035519';
        --删除合同批次表字段
        DELETE FROM dict_t_factor where tableid ='F7099198E0819B06E040A8C020035519';
        
        --添加合同批次model
        insert into dict_t_model (APPID, BGTLVL, DBTABLENAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLEID, TABLETYPE, TABSWHERE, ISBAK)
        values ('SPF', '1', 'SPF_T_P_C_BATCHES',  '5*10', '0000000000000000000000000000000', '1', '0', '0', '1', '0', '1', '0', '0', null, '合同批次', 0, null, null, null, '<CLOB>', null, null, '1', '1470431B25044D2BE050A8C02105302D', 'F7099198E0819B06E040A8C020035519', '1', null, '1');
        
        --添加合同批次表字段
        insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
        values (null, null, null, '1', null, null, 'F7099198E0829B06E040A8C020035519', null, 32, 3, 'DATAKEY',  'sys_guid()', null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '1', '1', '0', '0', '0', '1', '0', '0', 1, '行标志', '0', 1, null, null, 0, '0', 0, '1', '0', 'F7099198E0819B06E040A8C020035519', null,  '0', null);

        insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
        values (null, null, null, '1', null, null, 'F7099198E0839B06E040A8C020035519', null, 9, 1, 'ORDERID',  '0', null, null, null, null, null, null, '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '0', 1, '排序标志', '1', 2, null, null, 0, '0', 0, '1', '0', 'F7099198E0819B06E040A8C020035519', null,  '0', null);

        insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
        values (null, null, null, '1', null, null, 'F7099198E0849B06E040A8C020035519', null, 4000, 3, 'NEEDUPDATE',  null, null, null, null, null, null, null, '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '0', 1, '刷新标志', '1', 3, null, null, 0, '0', 0, '1', '0', 'F7099198E0819B06E040A8C020035519', null,  '0', null);

        insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
        values (null, null, null, '1', null, null, 'F7099198E0859B06E040A8C020035519', null, 32, 3, 'CONTRACT_ID',  null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '合同ID', '0', 4, null, null, 0, '0', 0, '1', '0', 'F7099198E0819B06E040A8C020035519', null,  '0', null);

        insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
        values (null, null, null, '1', null, null, 'F7099198E0869B06E040A8C020035519', null, 32, 3, 'CONTRACT_BATCH_NAME',  null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '合同批次名称', '1', 5, null, null, 0, '0', 0, '1', '0', 'F7099198E0819B06E040A8C020035519', null,  '0', null);

        insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
        values (null, null, null, '1', null, null, 'F7099198E0879B06E040A8C020035519', null, 24, 2, 'BATCH_MONEY',  '0', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '批次金额', '1', 6, null, null, 6, '0', 0, '1', '0', 'F7099198E0819B06E040A8C020035519', null,  '0', null);

        insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
        values (null, null, null, '1', null, null, 'F7099198E0889B06E040A8C020035519', null, 24, 2, 'SUM_PAYMENT_MONEY',  '0', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '累计支付金额', '1', 7, null, null, 6, '0', 0, '1', '0', 'F7099198E0819B06E040A8C020035519', null,  '0', null);

        insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
        values (null, null, null, '1', null, null, 'F7099198E0899B06E040A8C020035519', null, 8, 1, 'SUM_PAYMENT_BELIEL',  '0', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '累计支付比列', '1', 8, null, null, 0, '0', 0, '1', '0', 'F7099198E0819B06E040A8C020035519', null,  '0', null);

        insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
        values (null, null, null, '1', null, null, 'F7099198E08A9B06E040A8C020035519', null, 24, 2, 'MONEY',  '0', null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '金额', '1', 9, null, null, 6, '0', 0, '1', '0', 'F7099198E0819B06E040A8C020035519', null,  '0', null);

        insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
        values (null, null, null, '1', null, null, 'F7099198E08B9B06E040A8C020035519', null, 32, 3, 'OUTLINE',  null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', 1, '概要', '1', 10, null, null, 0, '0', 0, '1', '0', 'F7099198E0819B06E040A8C020035519', null,  '0', null);

        insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
        values (null, null, null, '1', null, null, 'F7099198E0E19B06E040A8C020035519', null, 32, 3, 'CONTRACT_BATCH_ID',  null, null, '00000000000000000000000000000000', null, null, null, null, '0', '0', '1', '1', '1', '0', '0', '0', '1', '0', '0', 1, '合同批次ID', '0', 11, null, null, 0, '0', 0, '1', '0', 'F7099198E0819B06E040A8C020035519', null,  '0', null);
        
        --公用方法，刷合同批次视图
        sys_p_recreate_views('F7099198E0819B06E040A8C020035519');
        
     END IF;
END;







































--SPF_T_P_C_BATCHES(合同批次)
