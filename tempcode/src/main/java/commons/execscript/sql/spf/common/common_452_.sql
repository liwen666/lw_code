BEGIN
DELETE FROM P#BD_T_HANDLEBEAN WHERE DATAKEY ='4F283E2CD9296830E0533A06A8C0526D';
insert into P#BD_T_HANDLEBEAN (DATAKEY, HANDLEBEANCODE, HANDLENAME, APPID, STATUS, HANDLETYPE)
values ('4F283E2CD9296830E0533A06A8C0526D', 'spfReportServiceImpl', '专项上报财政', 'SPF', '1', '2');
END;
--添加专项上报财政handle
