begin
--倒挂数据库常量（专项联合申报文）
delete  from dict_t_public where typeid like 'OA_HANG_BUSITYPEID%' ;
insert into dict_t_public (APPID, CNUM, ISYES, KEYID, KEYNAME, REMARK, RESERVE_1, RESERVE_2, STATUS, TYPEID, TYPENAME)
values ('SPF', 0, null, '0014', '联合申报文的业务类型', null, '929cb6b19bb04dc88fb494462e74dcec,6585a0c889a44b2e8782a3c4e21f53aa', null, '1', 'OA_HANG_BUSITYPEID', '联合申报文的业务类型');

insert into dict_t_public (APPID, CNUM, ISYES, KEYID, KEYNAME, REMARK, RESERVE_1, RESERVE_2, STATUS, TYPEID, TYPENAME)
values ('SPF', 0, null, '0015', '联合申报文的业务类型(专项)', null, '71b5cc28b9914fd5b60dadf1d6e85806,448232a88926479da21ca5c5ae2da846', null, '1', 'OA_HANG_BUSITYPEID_ZX', '联合申报文的业务类型（专项）');
end;--倒挂数据库常量（专项联合申报文）
