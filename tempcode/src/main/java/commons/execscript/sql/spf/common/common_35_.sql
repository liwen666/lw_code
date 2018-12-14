BEGIN
delete from DICT_T_PUBLIC where KEYID ='0014';
insert into DICT_T_PUBLIC (APPID, CNUM, ISYES, KEYID, KEYNAME, REMARK, RESERVE_1, RESERVE_2, STATUS, TYPEID, TYPENAME)
values ('SPF', 0, null, '0014', '联合申报文的业务类型', null, '929cb6b19bb04dc88fb494462e74dcec', null, '1', 'OA_HANG_BUSITYPEID', '联合申报文的业务类型');
END;

--刘凯倒挂常量
