begin
delete from dict_t_dealtype where appid = 'BAS' and DEALID = 'A0*07' and DEALNAME = 'ת��֧����Ŀ��';
insert into dict_t_dealtype (APPID,DEALID,DEALNAME,ORDERID,Needconfig,Isunique) values ('BAS','A0*07','ת��֧����Ŀ��',999,0,0);

delete from dict_t_dealtype where appid = 'BAS' and DEALID = 'A0*08' and DEALNAME = '�����Ǽ�ָ�������';
insert into dict_t_dealtype (APPID,DEALID,DEALNAME,ORDERID,Needconfig,Isunique) values ('BAS','A0*08','�����Ǽ�ָ�������',999,0,0);

delete from dict_t_dealtype where appid = 'BAS' and DEALID = 'A0*09' and DEALNAME = 'ת��֧�������';
insert into dict_t_dealtype (APPID,DEALID,DEALNAME,ORDERID,Needconfig,Isunique) values ('BAS','A0*09','ת��֧�������',999,0,0);

delete from dict_t_dealtype where appid = 'BAS' and DEALID = 'A0*10';
insert into dict_t_dealtype (APPID,DEALID,DEALNAME,ORDERID,Needconfig,Isunique) values ('BAS','A0*10','�ϼ�ת��֧�����������',999,0,0);
end;
--���ת��֧����������
