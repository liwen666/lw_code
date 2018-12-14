begin
delete bd_t_busijsurl where datakey in('4F4F58D62A6831C3E0533A06A8C0C92B','4F4F58D62A6931C3E0533A06A8C0C92B','4F4F58D62A6A31C3E0533A06A8C0C92B','4F4F58D62A6B31C3E0533A06A8C0C92B');
insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4F4F58D62A6831C3E0533A06A8C0C92B', '纵向一级项目办结主体', 'static/app/spf/external/SpfCYJPExternaOver.js', 'SPF', '11', '1');
insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4F4F58D62A6931C3E0533A06A8C0C92B', '纵向二级项目办结主体', 'static/app/spf/external/SpfCEJPExternaOver.js', 'SPF', '11', '1');
insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4F4F58D62A6A31C3E0533A06A8C0C92B', '纵向专项办结主体', 'static/app/spf/external/SpfCSpfExternaOver.js', 'SPF', '11', '1');
insert into bd_t_busijsurl (DATAKEY, NAME, VALUE, APPID, TYPE, STATUS)
values ('4F4F58D62A6B31C3E0533A06A8C0C92B', '纵向项目办结主体', 'static/app/spf/external/SpfCProjectExternaOver.js', 'SPF', '11', '1');
end;
--bd_t_busijsurlsql
