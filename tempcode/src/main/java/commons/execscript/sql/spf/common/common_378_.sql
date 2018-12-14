DECLARE
begin
delete from P#SPF_T_LISTREGIST;
 for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop
  begin
insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('003A62F1C8F549389D061DEEE1816B6D', 'ר���ʽ���Ŀѡ������', '43', '1', v_row.year,v_row.districtid, '���޸�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('178CA52B38934EC4BFB81DD148794F8B', '������ĿԤ���������', 'P6', '1', v_row.year,v_row.districtid, 'Ԭ��');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('2812729F2FC34AC09F0845497242F0D9', 'ר���ʽ𱸷����б�', '44', '1', v_row.year,v_row.districtid, '���Ƿ�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3ACD1747AE6442C7E053CB01A8C00510', '��Ŀ�������б�', '52', '1', v_row.year,v_row.districtid, '����ǿ');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3ACF061AAA144E95E053CB01A8C0AE0D', '������Ŀ�������б�', 'P2', '1', v_row.year,v_row.districtid, '����ǿ');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3B46957B6D695355E053CB01A8C0A532', 'ר���ʽ�������б�', '41', '1', v_row.year,v_row.districtid, '���Ƿ�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3BB8025DFDE340C080AE2E414C55BFA0', '��Ŀ�׶����б�', '5*19', '1', v_row.year,v_row.districtid, '');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3BBFDA1254DE5799E053CB01A8C063B3', '��Ŀ������б�', '51', '1', v_row.year,v_row.districtid, '���Ƿ�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3BD3A699DF985848E053CB01A8C08E3A', 'ר���ʽ��������б�', '40', '1', v_row.year,v_row.districtid, 'Ԭ��');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3C6F083E7EA849C6E053CB01A8C09ACB', 'һ����Ŀ������б�', '31', '1', v_row.year,v_row.districtid, 'Ԭ��');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3C6F18047DC24AA4E053CB01A8C06500', 'һ����Ŀ�������б�', '36', '1', v_row.year,v_row.districtid, '���Ƿ�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3C73E4C52EA96917E053CB01A8C0D7AF', '������Ŀ������б�', 'P1', '1', v_row.year,v_row.districtid, '���Ƿ�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('40BC19B3F10347FAA1A0F4B6CB866FCF', '��Ŀѡ��������б�', '57', '1', v_row.year,v_row.districtid, '���޸�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('430F91CE113022D8E0533A06A8C04389', '��Ŀɾ�����б�', '58', '1', v_row.year,v_row.districtid, '����');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('43221282C91A3164E0533A06A8C09088', '������Ŀɾ�����б�', 'P8', '1', v_row.year,v_row.districtid, '����');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('43C3A50792217A89E0533A06A8C001F0', 'ר���ʽ����ѡ����������', '4101', '1', v_row.year,v_row.districtid, '���޸�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('43FB46AD735763E1E0533A06A8C0DE5A', '������Ŀ�������б�', 'P7', '1', v_row.year,v_row.districtid, '���޸�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('443A19F170F63773E0533A06A8C034C8', '��Ŀ�������б�', '59', '1', v_row.year,v_row.districtid, '���Ƿ�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('443AD6E31B4E3BE0E0533A06A8C07AC0', '������Ŀ�������б�', 'P9', '1', v_row.year,v_row.districtid, '���Ƿ�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('4890E5066351422AB39DBA4C8D22447D', '��������', '2105', '1', v_row.year,v_row.districtid, '����');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('4BB40E198701477BE0533A06A8C07253', '�����ۺϴ���', 'FINANCE', '1', v_row.year,v_row.districtid, '���Ƿ�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('4BD61BBACFEA4F87E0533A06A8C02BD2', 'ר��Ԥ�����', '45', '1', v_row.year,v_row.districtid, '���޸�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('4CEF7C81C76B4ED5E0533A06A8C08EFF', '��Ŀ�ۺϴ���', 'PROJZHCL', '1', v_row.year,v_row.districtid, '���Ƿ�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('4E04B5F750A24B929F2CECA8040DA9D0', 'ר���걨��ѯ��ͼ', '4*60', '1', v_row.year,v_row.districtid, '����ǿ');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('5D7CE32B119945448B0E0B34B906D6E0', 'һ����Ŀ�������б�', '30', '1', v_row.year,v_row.districtid, 'Ԭ��');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('60A1775A8B2A44048AF5318F0DB098D7', '��Ŀ����ָ�����б�', '55', '1', v_row.year,v_row.districtid, 'Ԭ��');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('6264F8A711194EFFA77669E562205783', '������Ŀ����Ԥ�����б�', 'P4', '1', v_row.year,v_row.districtid, 'Ԭ��');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('65D7BFB144104FCF9357842223864D2E', '��Ŀ����Ԥ�����б�', '54', '1', v_row.year,v_row.districtid, 'Ԭ��');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('6ADF6B034CBE4D39A64AA24D4404713C', '��ĿԤ��������б�', '56', '1', v_row.year,v_row.districtid, 'Ԭ��');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('712ABBA82F104F559283585B30E60766', 'һ����Ŀ����ѡ�����б�', '3101', '1', v_row.year,v_row.districtid, '���޸�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('76F72262AC3C479C9343D4B87F680A69', '��Ŀ�������б�', '46', '1', v_row.year,v_row.districtid, '������');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('852609AED9F447A892478D4CBEA49336', '������Ŀ����ָ�����б�', 'P5', '1', v_row.year,v_row.districtid, 'Ԭ��');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('9575711D24664091846E7AEE70F6B936', 'һ����Ŀ�������б�', '32', '1', v_row.year,v_row.districtid, '����ǿ');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('A50A8B53A4754D60BE1C86123C03F355', '������Ŀ�걨���б�', 'P0', '1', v_row.year,v_row.districtid, '���Ƿ�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('AC5843C6C9274B36816B4AA7863185FF', '��Ŀ�������б�', '53', '1', v_row.year,v_row.districtid, '���Ƿ�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('BC789F15599B49288FEA730DAA2EDD93', '��Ŀ��������', '5*40', '1', v_row.year,v_row.districtid, '���Ƿ�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('BF30BA7087A3402282E50550057A613D', '����ר������', '4*03', '1', v_row.year,v_row.districtid, '����');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('C2912846F560447CBA13776BC0B1E902', '�ۺ���ͼ���б�', 'ZHCX*01', '1', v_row.year,v_row.districtid, '������');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('D264D878B1464A478AD3544FD1DA77F1', '������Ŀ�����', 'P*40', '1', v_row.year,v_row.districtid, '���Ƿ�');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('F335EF93961F4AF29C5EFE134C981C45', '��Ŀ�걨���б�', '50', '1', v_row.year,v_row.districtid, 'Ԭ��');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('F883433FFA2F492CA959E793376A1E77', 'ר���ʽ�������б�', '42', '1', v_row.year,v_row.districtid, '����ǿ');


    end;
 end loop;
end;
--SPF_T_LISTREGIST
