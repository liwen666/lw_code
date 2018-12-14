declare
   i number;
begin
select count(1)  into i  from bil_t_billtype where billtypeid='999';
if i=0 then 
insert into bil_t_billtype (BILLTYPEID, BILLTYPENAME, DBVERSION, ORDERID, STATUS, SUPERID, ISLEAF, CODE, LEVELNO)
values ('999', '全部', sysdate, 0, '1', '0', '0', null, 0);
end if;
MERGE INTO BIL_T_SETCODE_B B USING (SELECT '009' AS a  FROM dual) A
ON (A.a=B.BASETYPEID)
WHEN NOT MATCHED THEN
INSERT VALUES ('009','财年','', '2016',SYSDATE,'0','1','1','1','0') ;

MERGE INTO BIL_T_SETCODE_B B USING (SELECT '002' AS a  FROM dual) A
ON (A.a=B.BASETYPEID)
WHEN NOT MATCHED THEN
INSERT VALUES ('002','特殊字符','', '!@2011_001',SYSDATE,'1','1','2','1','0') ;

MERGE INTO BIL_T_SETCODE_B B USING (SELECT '006' AS a  FROM dual) A
ON (A.a=B.BASETYPEID)
WHEN NOT MATCHED THEN
INSERT VALUES ('006','日期','', 'xxxx',SYSDATE,'3','1','3','1','0') ;

MERGE INTO BIL_T_SETCODE_B B USING (SELECT '008' AS a  FROM dual) A
ON (A.a=B.BASETYPEID)
WHEN NOT MATCHED THEN
INSERT VALUES ('008','流水号','', '5',SYSDATE,'2','1','99','1','0') ;

end;--单据脚本_2#
