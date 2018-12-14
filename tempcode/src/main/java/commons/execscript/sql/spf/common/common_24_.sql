begin
delete from dict_t_public where typeid = 'OA_HANG_BUSITYPEID_ZX';

insert into DICT_T_PUBLIC (APPID, CNUM, ISYES, KEYID, KEYNAME, REMARK, RESERVE_1, RESERVE_2, STATUS, TYPEID, TYPENAME)
values ('SPF', 0,  null, '0015', '联合申报文的业务类型(专项)', null, '448232a88926479da21ca5c5ae2da846', null, '1', 'OA_HANG_BUSITYPEID_ZX', '联合申报文的业务类型（专项）');

delete from dict_t_public where typeid = 'OA_HANG_BUSITYPEID';

insert into DICT_T_PUBLIC (APPID, CNUM, ISYES, KEYID, KEYNAME, REMARK, RESERVE_1, RESERVE_2, STATUS, TYPEID, TYPENAME)
values ('SPF', 0, null, '0014', '联合申报文的业务类型', null, '929cb6b19bb04dc88fb494462e74dcec', null, '1', 'OA_HANG_BUSITYPEID', '联合申报文的业务类型');
end;
--倒挂数据库常量
