DECLARE
begin
 delete FROM P#SPF_T_PPROJSTEP WHERE GUID in ('504301AE58C0706AE0533906A8C06B4C','504301AE58C1706AE0533906A8C06B4C');
for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop  
 
  insert into P#SPF_T_PPROJSTEP (PROVINCE, YEAR, STATUS, BGTLVL, ORDERID, GUID, CODE, NAME)
  values (v_row.DISTRICTID, v_row.YEAR, '1', '1', 28, '504301AE58C0706AE0533906A8C06B4C', 'projzhcxManage', '项目综合查询(管理)');
  insert into P#SPF_T_PPROJSTEP (PROVINCE, YEAR, STATUS, BGTLVL, ORDERID, GUID, CODE, NAME)
  values (v_row.DISTRICTID, v_row.YEAR, '1', '1', 29, '504301AE58C1706AE0533906A8C06B4C', 'projzhcxInput', '项目综合查询(申报)');
END LOOP;
END;
--添加项目综合查询(管理)和项目综合查询(申报)阶段
