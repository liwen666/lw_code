begin
  delete from P#spf_t_pprojstep where guid in('4D96FD8D2E774AA7E0533A06A8C0D9C4','4E0E2A130E666AC3E0533A06A8C0FCBA');

 for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop
  begin
  
insert into P#spf_t_pprojstep (year,Province,BGTLVL, CODE, GUID, NAME, ORDERID, STATUS)
values (v_row.year, v_row.districtid,'1', 'projzhcl',  '4D96FD8D2E774AA7E0533A06A8C0D9C4', '��Ŀ�ۺϴ���', 11, '1');
  
insert into P#spf_t_pprojstep (year,Province,BGTLVL, CODE, GUID, NAME, ORDERID, STATUS)
values (v_row.year, v_row.districtid,'1', 'projbgtadjustaudit',  '4E0E2A130E666AC3E0533A06A8C0FCBA', 'Ԥ�����ȷ��', 27, '1');

    end;
 end loop;
end;
--�׶α��ʼ��Ԥ�����ȷ�Ͻ׶�
