BEGIN
DELETE FROM BD_T_HANDLEBEAN WHERE DATAKEY ='4CDFACE994865824E0533A06A8C0CA69';
insert into BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, HANDLETYPE, APPID)
values ('4CDFACE994865824E0533A06A8C0CA69', 'spfVerticalReturnLogServiceImpl', '专项项目(二级项目)回退日志', '10', 'SPF');
END;
--修改项目和二级项目handler记录日志
