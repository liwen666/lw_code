declare 
  v_column number;

begin
  
  --����  ��ͬ����  suitid
  update dict_t_model t
     set suitid = 'E02CDB44C69945FFBE1781F6729AD54D'
   where t.TABLEID = 'F7099198E0819B06E040A8C020035519';
   
  --ɾ��  ��Ŀ�׶κ�ͬ��ͼ  ���ܿ�Ŀ
  delete from dict_t_factor t
   where t.COLUMNID = '781B098F24504FBB94D061E808B1D50F';

  select count(1) into v_column from user_tab_columns t
    where t.TABLE_NAME = 'P#SPF_T_P_CONTRACT'
      and t.COLUMN_NAME = 'TABFLAG';
  if v_column = 0 then 
     
    --Ϊ   ��Ŀ�׶κ�ͬ���� ������״̬�ֶ�
    EXECUTE IMMEDIATE Q'{
      alter table P#SPF_T_P_CONTRACT add (TABFLAG char(1) default '0' not null)
    }';
    
   --Ϊ   ��Ŀ�׶κ�ͬ���� ������״̬�ֶ�ע��
    EXECUTE IMMEDIATE Q'{
      comment on column P#SPF_T_P_CONTRACT.TABFLAG
        is '��ͬ���״̬'
    }';
	delete from dict_t_factor where COLUMNID ='3274C84C4D9C9845E050A8C0F00057DB'; 
    insert into dict_t_factor (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (null, null, null, null, null, null, '3274C84C4D9C9845E050A8C0F00057DB', null, 1, 3, 'TABFLAG', '''0''', null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '0', 1, '��ͬ���״̬', '0', 16, null, null, 0, '0', null, '1', '0', 'F7097096422CDF47E040A8C020035309', null, '0', null);
    
  end if;
  
  --����ֶ���ɺ�     ˢ����ͼ
  sys_p_recreate_views('F7097096422CDF47E040A8C020035309');
  
  --��������   ��Ŀ�׶κ�ͬ����  ��ͼ
  EXECUTE IMMEDIATE '
    CREATE OR REPLACE VIEW SPF_V_PCONTRACT AS
      SELECT (nvl(STPC.DATAKEY, A.DATAKEY)) DATAKEY,
         A.PROJECTID BPMNID,
         A.AGENCYID,
         F.FIRAGENCYID,
         AGENCY.NAME AGENCYNAME,
         A.SPFID,
         A.PROJTYPEID,
         F.SPFNAME,
         A.PROJECTID,
         A.PROJCODE,
         A.PROJNAME PROJNAME,
         A.PROJNAME || ''{'' || A.PROJECTID || ''}'' PROJGROUP_NAME,
         A.DISTRICTID,
         F.EXPFUNCID,
         F.DEPTID,
         (SELECT PROJTYPENAME
            FROM SPF_T_PROJECTTYPE
           where PROJTYPEID = A.PROJTYPEID) PROJTYPENAME,
         STPC.DATAKEY CONTRACTID,
         STPC.CONTRACT_NAME,
         STPC.CONTRACT_NAME || ''{'' || STPC.DATAKEY || ''}'' CONTRACTGROUP_NAME,
         STPC.TABFLAG TABFLAG
      FROM SPF_T_PBASEINFO A
      inner join SPF_T_FBASEINFO F
      on A.SPFID = F.SPFID 
      inner join CODE_T_FIRAGENCY AGENCY
      on F.FIRAGENCYID = AGENCY.GUID
      left join SPF_T_P_CONTRACT STPC
      on A.PROJECTID = STPC.PROJECTID
  ';
  
  EXECUTE IMMEDIATE '
    comment on table SPF_V_PCONTRACT is ''��Ŀ�׶κ�ͬ����''
  ';
  
end;
--��Ŀ�׶κ�ͬ����
