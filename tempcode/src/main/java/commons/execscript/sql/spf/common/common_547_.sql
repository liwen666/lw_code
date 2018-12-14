begin

  delete from p#SPF_T_CONDISET where guid in 
  ('4CE194BB2B056B75E0533A06A8C0B129','984F11F15A6D4E509D2E5CD36A857FCF','86FAE0265E604A5F84830E8B2A1A3E4D',
  '5132596163940D82E0533906A8C0AE18','514914BA6EE67AA5E0533906A8C050FF','DA69A5C1B5E54CFD9A985D183AB6343E',
  '5132EA75A78710AEE0533906A8C01B43','5131583D558B06C5E0533906A8C0F25C');
  
  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
    
    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'!''1''.equals($isdec$) '||CHR(38)||''||CHR(38)||'!''''.equals($treeCheck$)', '4CE194BB2B056B75E0533A06A8C0B129', '����������ˢ���Ҳ��б�', 'AGENCYID in(select guid from CODE_T_AGENCY_SPF where LVLID like (SELECT LVLID || ''%'' FROM CODE_T_AGENCY_SPF WHERE GUID = ''$treeCheck$''))', '1', (SELECT tableid FROM p#dict_t_model where dealtype='ZHCX*01' and PROVINCE=v_row.districtid and year=v_row.year), null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$USERID$!=null '||CHR(38)||''||CHR(38)||' !''1''.equals($isdec$)', '984F11F15A6D4E509D2E5CD36A857FCF', '�����Ҳ��б��ѯ����', '$usertospf$', '1', '36DA6D0F1C750084E0530A700187AB0D', null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$USERID$!=null '||CHR(38)||''||CHR(38)||'''1''.equals($isdec$)', '86FAE0265E604A5F84830E8B2A1A3E4D', '�걨ҳ������', 'agencyID = ''$agencyID$'' AND DISTRICTID = (SELECT GUID FROM CODE_T_DISTRICT_SPF WHERE CODE = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM(''DIVID''))', '1', (SELECT tableid FROM p#dict_t_model where dealtype='ZHCX*01' and PROVINCE=v_row.districtid and year=v_row.year), null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$projType$ == ''3'' '||CHR(38)||''||CHR(38)||'!''1''.equals($isdec$)', '5132596163940D82E0533906A8C0AE18', 'ר���ʽ�', 'projtypeid in (select projtypeid from SPF_T_PROJECTTYPE where projtype =''3'')', '1', (SELECT tableid FROM p#dict_t_model where dealtype='ZHCX*01' and PROVINCE=v_row.districtid and year=v_row.year), null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$projType$ == ''2'' '||CHR(38)||''||CHR(38)||'!''1''.equals($isdec$)', '5132EA75A78710AEE0533906A8C01B43', 'һ����Ŀ', 'projtypeid in (select projtypeid from SPF_T_PROJECTTYPE where projtype =''2'')', '1',(SELECT tableid FROM p#dict_t_model where dealtype='ZHCX*01' and PROVINCE=v_row.districtid and year=v_row.year), null);

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'$userType$ == 1 '||CHR(38)||''||CHR(38)||' $projType$ == ''2'' '||CHR(38)||''||CHR(38)||'!''1''.equals($isdec$)', '514914BA6EE67AA5E0533906A8C050FF', '�����û������Ҳ�ѯһ����Ŀ����û��Ե�λ', 'agencyID IN (SELECT GUID FROM CODE_T_SECUAGENCY_SPF )', '1', (SELECT tableid FROM p#dict_t_model where dealtype='ZHCX*01' and PROVINCE=v_row.districtid and year=v_row.year), '0');

    insert into p#SPF_T_CONDISET (YEAR, PROVINCE,CONDITION, GUID, NOTE, SENTENCES, STATUS, TABLEID, WHERETYPE)
    values (v_row.YEAR, v_row.DISTRICTID,'1==0', 'DA69A5C1B5E54CFD9A985D183AB6343E', '��ҳ', '10,20,30,50,80,100,200', '1', '36DA6D0F1C750084E0530A700187AB0D', null);

  end loop;
  --������Ŀ�ۺϲ�ѯ�ֶΣ� ԭ����ͼ���޴��ֶΣ�����dbcolumnnameΪDW����������Ŀ�����ʱ���ڹ������⣬������Ҫ�޸�Ϊ��ͬ�ֶ�
  --��ע��         �����ۺϲ�ѯ��ͼʱ����Ҫ���AGENCYID����ֶ�
  update p#dict_t_factor set dbcolumnname = 'AGENCYID' where columnid = '3F97ACE30DCF005AE0530A7001876947';
end;
--��Ŀ�ۺϲ�ѯ��ѯ����
