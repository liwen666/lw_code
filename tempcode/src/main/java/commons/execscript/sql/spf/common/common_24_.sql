begin
delete from dict_t_public where typeid = 'OA_HANG_BUSITYPEID_ZX';

insert into DICT_T_PUBLIC (APPID, CNUM, ISYES, KEYID, KEYNAME, REMARK, RESERVE_1, RESERVE_2, STATUS, TYPEID, TYPENAME)
values ('SPF', 0,  null, '0015', '�����걨�ĵ�ҵ������(ר��)', null, '448232a88926479da21ca5c5ae2da846', null, '1', 'OA_HANG_BUSITYPEID_ZX', '�����걨�ĵ�ҵ�����ͣ�ר�');

delete from dict_t_public where typeid = 'OA_HANG_BUSITYPEID';

insert into DICT_T_PUBLIC (APPID, CNUM, ISYES, KEYID, KEYNAME, REMARK, RESERVE_1, RESERVE_2, STATUS, TYPEID, TYPENAME)
values ('SPF', 0, null, '0014', '�����걨�ĵ�ҵ������', null, '929cb6b19bb04dc88fb494462e74dcec', null, '1', 'OA_HANG_BUSITYPEID', '�����걨�ĵ�ҵ������');
end;
--�������ݿⳣ��
