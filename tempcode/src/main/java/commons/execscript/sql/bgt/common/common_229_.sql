BEGIN
DELETE FROM Bd_t_Handlebean WHERE DATAKEY IN ('2CDFACE9949D5824E0533A06A8C0CA11','2CDFACE9949D5824E0533A06A8C0CA22',
'2CDFACE9949D5824E0533A06A8C0CA33','2CDFACE9949D5824E0533A06A8C0CA44','2CDFACE9949D5824E0533A06A8C0CA55','2CDFACE9949D5824E0533A06A8C0CA66');

insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) 
   SELECT  '2CDFACE9949D5824E0533A06A8C0CA11','18','defaultTitleParamService','发送本单位默认标题','BGT','1'  FROM DUAL
     UNION ALL
   SELECT '2CDFACE9949D5824E0533A06A8C0CA22','18','defaultTitleParamService','发送本单位默认标题','BAS','1'   FROM DUAL
     UNION ALL
   SELECT '2CDFACE9949D5824E0533A06A8C0CA33','19','defaultTitleParamService','上报默认标题','BAS','1'   FROM DUAL
UNION ALL
 SELECT '2CDFACE9949D5824E0533A06A8C0CA44','19','defaultTitleParamService','上报默认标题','BGT','1'   FROM DUAL
UNION ALL
 SELECT '2CDFACE9949D5824E0533A06A8C0CA55','20','defaultTitleParamService','下发默认标题','BAS','1'   FROM DUAL
UNION ALL
 SELECT '2CDFACE9949D5824E0533A06A8C0CA66','20','defaultTitleParamService','下发默认标题','BGT','1'   FROM DUAL;
 END;     
 
--添加默认标题handler_170504_zz
