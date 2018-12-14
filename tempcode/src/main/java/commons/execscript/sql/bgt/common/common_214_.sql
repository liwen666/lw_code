begin
delete from dict_t_dealtype where appid = 'BAS' and DEALID = 'A0*07' and DEALNAME = '转移支付项目表';
insert into dict_t_dealtype (APPID,DEALID,DEALNAME,ORDERID,Needconfig,Isunique) values ('BAS','A0*07','转移支付项目表',999,0,0);

delete from dict_t_dealtype where appid = 'BAS' and DEALID = 'A0*08' and DEALNAME = '本级登记指标情况表';
insert into dict_t_dealtype (APPID,DEALID,DEALNAME,ORDERID,Needconfig,Isunique) values ('BAS','A0*08','本级登记指标情况表',999,0,0);

delete from dict_t_dealtype where appid = 'BAS' and DEALID = 'A0*09' and DEALNAME = '转移支付结果表';
insert into dict_t_dealtype (APPID,DEALID,DEALNAME,ORDERID,Needconfig,Isunique) values ('BAS','A0*09','转移支付结果表',999,0,0);

delete from dict_t_dealtype where appid = 'BAS' and DEALID = 'A0*10';
insert into dict_t_dealtype (APPID,DEALID,DEALNAME,ORDERID,Needconfig,Isunique) values ('BAS','A0*10','上级转移支付补助情况表',999,0,0);
end;
--添加转移支付表处理类型
