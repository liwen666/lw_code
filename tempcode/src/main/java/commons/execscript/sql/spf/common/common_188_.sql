begin
--删除财政绩效指标表数据

DELETE FROM KPI_T_SETFNCKPI;
--初始化一条数据
INSERT INTO KPI_T_SETFNCKPI (STATUS,DATAKEY,NEEDUPDATE,ORDERID,FINYEAR,GUID,SUPERGUID,KPINAME,LEVELNO,ISLEAF,SCORE,REQUIREDCHILD,EXPLANATION,STANDARD,ISINSERT,KPIPROP)
VALUES ('1','092459F0E402E882E050A8C0210556F4',NULL,1,'2014','092459F0E403E882E050A8C0210556F4','0','财政绩效指标',0,'0',0,'0',NULL,NULL,'0','1');
end;--绩效_20160621_4_YLL财政绩效指标表初始数据
