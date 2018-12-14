begin
  
  delete from p#dict_t_factor where COLUMNID = '2F418CA4DD42F444E050A8C02105683B';
  
  for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
    
      insert into p#dict_t_factor (YEAR, PROVINCE,NAME, DBCOLUMNNAME, DATALENGTH, DATATYPE, COLUMNID, TABLEID, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, CSID, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
      values(v_row.YEAR, v_row.DISTRICTID,'支出功能分类科目名称', 'EXPFUNCNAME', 457, 3, '2F418CA4DD42F444E050A8C02105683B', '2F418CA4DD24F444E050A8C02105683B', null, null, null, null, null, null, null, null, null, '00000000000000000000000000000000', '6BE621B04614474D875FA06328C3174A', '800F4618A062438FAA666FF3736BF857', null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '1', 45, null, null, 0, '0', 200, '1', '0', null, '0', null);

  end loop;
    sys_p_recreate_views('2F418CA4DD24F444E050A8C02105683B');
end;

--一级项目审核主列表(支出功能分类科目名称)引用修改为字符
