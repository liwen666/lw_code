begin
DELETE FROM dict_t_defaultcol
 WHERE GUID = '20895D1768692376E050A8C021057D67';
INSERT INTO dict_t_defaultcol
  (GUID,
   DEALID,
   ORDERID,
   NAME,
   DBCOLUMNNAME,
   DATATYPE,
   DATALENGTH,
   SCALE,
   CSID,
   ISPRIMARY,
   ISLOGICKEY,
   ISRESERVE,
   DEFAULTVALUE,
   DEID)
VALUES
  ('20895D1768692376E050A8C021057D67',
   'A0*01',
   6,
   '调入表',
   'REDEPLOYTABID',
   3,
   32,
   0,
   '',
   '0',
   '0',
   '1',
   '',
   null);
end;
--郑桥_20100925_人员表添加缺省列_调动表
