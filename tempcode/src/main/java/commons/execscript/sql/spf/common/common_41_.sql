begin
--�������ݿⳣ����ר�������걨�ģ�
delete  from dict_t_public where typeid like 'OA_HANG_BUSITYPEID%' ;
insert into dict_t_public (APPID, CNUM, ISYES, KEYID, KEYNAME, REMARK, RESERVE_1, RESERVE_2, STATUS, TYPEID, TYPENAME)
values ('SPF', 0, null, '0014', '�����걨�ĵ�ҵ������', null, '929cb6b19bb04dc88fb494462e74dcec,6585a0c889a44b2e8782a3c4e21f53aa', null, '1', 'OA_HANG_BUSITYPEID', '�����걨�ĵ�ҵ������');

insert into dict_t_public (APPID, CNUM, ISYES, KEYID, KEYNAME, REMARK, RESERVE_1, RESERVE_2, STATUS, TYPEID, TYPENAME)
values ('SPF', 0, null, '0015', '�����걨�ĵ�ҵ������(ר��)', null, '71b5cc28b9914fd5b60dadf1d6e85806,448232a88926479da21ca5c5ae2da846', null, '1', 'OA_HANG_BUSITYPEID_ZX', '�����걨�ĵ�ҵ�����ͣ�ר�');
end;--�������ݿⳣ����ר�������걨�ģ�
