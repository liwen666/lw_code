
begin

  --  ɾ��  ר���ʽ���Ŀ�걨������   dict_t_factor �б��ֶ�  ����ģ��
    delete from p#dict_t_factor t where t.COLUMNID='DB10182EC50D408FBC5131CD60E66076';
  --��P#����ѭ����������
  for v_row in (select * from pub_t_partition_divid t where t.year  <> '*') loop 
  --  ���  ר���ʽ���Ŀ�걨������   dict_t_factor �б��ֶ�  ����ģ��
   insert into p#dict_t_factor (YEAR,PROVINCE,ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values ('2016',v_row.DISTRICTID,NULL, null, null, null, null, null, 'DB10182EC50D408FBC5131CD60E66076', null, 32, 3, 'TEMPLATENAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '1', 1, '����ģ��', '1', 26, null, null, 0, 'C', null, '1', '0', '7794F9126E7640219A1D025708DB1400', null, '0', null);
   
  end loop;
    
    --  ˢ��ͼ
    sys_p_recreate_views('7794F9126E7640219A1D025708DB1400');
end;
--�޸�dict_t_factorר���ʽ���Ŀ�걨�������и���ģ���ֶ�
