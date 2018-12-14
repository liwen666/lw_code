begin
  for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop   
    update P#SPF_T_CONDISET set tableid=(SELECT tableid FROM p#dict_t_model where dealtype='ZHCX*01' and PROVINCE=v_row.districtid and year=v_row.year) where tableid='38AA7C84397D001AE0530A4405064AEC' and PROVINCE=v_row.districtid and year=v_row.year;
    update P#SPF_T_LISTCONDISET set tableid=(SELECT tableid FROM p#dict_t_model where dealtype='ZHCX*01' and PROVINCE=v_row.districtid and year=v_row.year),
          FACTOR=(select COLUMNID from p#dict_t_factor ff,p#dict_t_model mm where ff.tableid = mm.tableid and ff.province=mm.province and ff.year=mm.year and dbcolumnname='SPFID' and mm.dealtype='ZHCX*01' and ff.PROVINCE=v_row.districtid and ff.year=v_row.year )  
    where tableid='38AA7C84397D001AE0530A4405064AEC' and PROVINCE=v_row.districtid and year=v_row.year;
  end loop;
end;
--修改综合查询视图的主列表条件设置
