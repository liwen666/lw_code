begin
delete bd_t_busijsurl where datakey in('4F4F58D62A6831C3E0533A06A8C0C92B','4F4F58D62A6931C3E0533A06A8C0C92B','4F4F58D62A6A31C3E0533A06A8C0C92B','4F4F58D62A6B31C3E0533A06A8C0C92B');
insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4F4F58D62A6831C3E0533A06A8C0C92B', '����һ����Ŀ�������', 'static/app/spf/external/SpfCYJPExternaOver.js', 'SPF', '11', '1');
insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4F4F58D62A6931C3E0533A06A8C0C92B', '���������Ŀ�������', 'static/app/spf/external/SpfCEJPExternaOver.js', 'SPF', '11', '1');
insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4F4F58D62A6A31C3E0533A06A8C0C92B', '����ר��������', 'static/app/spf/external/SpfCSpfExternaOver.js', 'SPF', '11', '1');
insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4F4F58D62A6B31C3E0533A06A8C0C92B', '������Ŀ�������', 'static/app/spf/external/SpfCProjectExternaOver.js', 'SPF', '11', '1');
end;
--bd_t_busijsurlsql
