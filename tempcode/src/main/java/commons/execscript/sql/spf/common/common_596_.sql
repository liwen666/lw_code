DECLARE
begin
EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SPF_T_XMRZ AS
SELECT c.datakey,
       c.objectid,
       c.BUSITYPEID,
       c.BATCHID,
            (select title from BD_T_BATCH where batchid=c.batchid) title,
       c.TARGETAGENCYID,
       (select busitypename from bd_t_busitype where BUSITYPEID = c.BUSITYPEID) busitypename,
     c.WFSTATUS,
       c.DISTRICTID,
       c.SOURCEAGENCYID,
       c.SOURCEBATCHID,
       c.WFDIRECTION,
       p.dbversion,
       p.agencyid,
       p.PROJECTID,
       p.PROJTYPEID,
       p.CREATEUSER,
       p.SPFID,
       p.PROJNAME,
       (SELECT f.spfname FROM spf_t_fbaseinfo f where f.spfid=p.SPFID) spfname,
       1 NEEDUPDATE,
       1 ORDERID,
       p.startyear FINYEAR
  FROM spf_t_cflow_act c
, spf_t_pbaseinfo p where c.objectid = p.PROJECTID/';

DELETE FROM P#dict_t_Factor WHERE tableid = '068A02F4E2AC4969AA5AC25F487669D7' AND columnid ='528F04DD7BBB1AECE0533A06A8C01DCB';

FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   
BEGIN
insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '528F04DD7BBB1AECE0533A06A8C01DCB', null, '32', '3', 'CREATEUSER', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '创建用户', '1', '28', null, null, '0', '0', null, '1', '0', '068A02F4E2AC4969AA5AC25F487669D7', null, '0', null);

 end;
 end loop;
end;  ---SPF_T_XMRZ
--SPF_T_XMRZ
