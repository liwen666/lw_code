BEGIN
DELETE FROM spf_t_fspfstep WHERE GUID in('2A24548F2EEBBC27E050A8C02105622D','2A24548F2EEBBC27E050A8C02105622C');
insert into spf_t_fspfstep (BGTLVL, CODE,  GUID, NAME, ORDERID, STATUS)
values (null, 'lowerProjAudit', '2A24548F2EEBBC27E050A8C02105622D', '������Ŀ����', 9, '1');
insert into spf_t_fspfstep (BGTLVL, CODE,  GUID, NAME, ORDERID, STATUS)
values (null, 'spfJiZhang', '2A24548F2EEBBC27E050A8C02105622C', 'ר�����', 10, '1');
END;
--������Ŀ�����׶�
