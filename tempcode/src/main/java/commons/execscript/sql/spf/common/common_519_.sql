BEGIN
execute immediate Q'{CREATE OR REPLACE VIEW CODE_T_SPFWFDIRECTION AS
SELECT '#' GUID,'设立' NAME,'0' CODE ,'0' SUPERGUID,'1'ISLEAF ,'1'STATUS,'' LEVELNO FROM DUAL
union all
SELECT '0' GUID,'下发' NAME,'1' CODE ,'0' SUPERGUID,'1'ISLEAF ,'1'STATUS,'' LEVELNO FROM DUAL
union all
SELECT '1' GUID,'上报' NAME,'2' CODE ,'0' SUPERGUID,'1'ISLEAF ,'1'STATUS,'' LEVELNO FROM DUAL
union all
SELECT '2' GUID,'回退' NAME,'3' CODE ,'0' SUPERGUID,'1'ISLEAF ,'1'STATUS,'' LEVELNO FROM DUAL
union all
SELECT '3' GUID,'撤回' NAME,'4' CODE ,'0' SUPERGUID,'1'ISLEAF ,'1'STATUS,'' LEVELNO FROM DUAL
union all
SELECT '8' GUID,'办结' NAME,'5' CODE ,'0' SUPERGUID,'1'ISLEAF ,'1'STATUS,'' LEVELNO FROM DUAL
union all
SELECT '9' GUID,'删除' NAME,'6' CODE ,'0' SUPERGUID,'1'ISLEAF ,'1'STATUS,'' LEVELNO FROM DUAL}';
      
DELETE FROM P#DICT_T_MODELCODE WHERE tableid ='506ADE82C24433E7E0533906A8C0E389';
DELETE FROM P#DICT_T_FACTORCODE WHERE tableid ='506ADE82C24433E7E0533906A8C0E389';
   
for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop 
   
insert into P#DICT_T_MODELCODE (YEAR, PROVINCE, APPID, TABLEID, NAME, DBTABLENAME, ORDERID, ISREPBASE, ISLVL, SQLCON, DYNAMICWHERE, ISORGID, STATUS, FASPCSID, ISFASP, TABLETYPE)
values (v_row.year,V_ROW.Districtid, 'SPF', '506ADE82C24433E7E0533906A8C0E389', '项目库纵向流程方向', 'CODE_T_SPFWFDIRECTION', '11', '0', '0', null, '1=1 order by code', '0', '1', '506ADE82C24433E7E0533906A8C0E389', '0', '2');

insert into P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
values (v_row.year,V_ROW.Districtid, '506ADE82C24433E7E0533906A8C0E389', null, '506ADE82C24533E7E0533906A8C0E389', 'GUID', null, 'GUID', '3', '1', null, null, null, '0', '0', null, '1');

insert into P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
values (v_row.year,V_ROW.Districtid, '506ADE82C24433E7E0533906A8C0E389', null, '506ADE82C24633E7E0533906A8C0E389', '流程方向', null, 'NAME', '3', '4', null, '0', null, '1', '0', null, '1');

insert into P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
values (v_row.year,V_ROW.Districtid, '506ADE82C24433E7E0533906A8C0E389', null, '506ADE82C24733E7E0533906A8C0E389', '排序序号', null, 'CODE', '3', '1', null, '0', null, '0', '0', null, '1');

insert into P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
values (v_row.year,V_ROW.Districtid, '506ADE82C24433E7E0533906A8C0E389', null, '506ADE82C24833E7E0533906A8C0E389', '父节点', null, 'SUPERGUID', '3', '1', null, '0', null, '0', '0', null, '1');

insert into P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
values (v_row.year,V_ROW.Districtid, '506ADE82C24433E7E0533906A8C0E389', null, '506ADE82C24933E7E0533906A8C0E389', '是否树', null, 'ISLEAF', '3', '1', null, '0', null, '0', '0', null, '1');

insert into P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
values (v_row.year,V_ROW.Districtid, '506ADE82C24433E7E0533906A8C0E389', null, '506ADE82C24A33E7E0533906A8C0E389', '状态', null, 'STATUS', '3', '1', null, '0', null, '0', '0', null, '1');

insert into P#DICT_T_FACTORCODE (YEAR, PROVINCE, TABLEID, DEID, COLUMNID, NAME, ORDERID, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, DEFAULTVALUE, CSID, ISVISIBLE, ISRESERVE, BGTLVL, STATUS)
values (v_row.year,V_ROW.Districtid, '506ADE82C24433E7E0533906A8C0E389', null, '506ADE82C24B33E7E0533906A8C0E389', 'LEVELNO', null, 'LEVELNO', '3', '0', null, null, null, '0', '0', null, '1');

update P#dict_t_factor set csid ='506ADE82C24433E7E0533906A8C0E389', DATALENGTH ='32',datatype ='6',SHOWFORMAT='4' ,ISVISIBLE='1'
where tableid in(select tableid from dict_t_model where dealtype 
in('sdp_hccomt','sdf_hccomt','pb_hccomt','ej_hccomt','yj_hccomt','spf_sed_ps','spf_sed_fc','spf_sed_pc','spf_sed_fs','spf_sdejpa','spf_sdejph',
'spf_sdyjpa','spf_sdyjph','66_JS','spf_yjpa','fb_hccomt','sdej_comt','sdyj_comt','spf_ejpa','spf_ejph','spf_fc','spf_fs','spf_pc',
'spf_ps','spf_yjph','69_JS','68_JS')) and DBCOLUMNNAME='WFDIRECTION';

delete from dict_t_modelcode where tableid ='4F0CD31B08F74223BECA9A607658CE14';
delete from dict_t_factorcode where tableid ='4F0CD31B08F74223BECA9A607658CE14';
 
update P#dict_t_factor set csid ='43860AA96E7F30AEE0533A06A8C07C8E', DATALENGTH ='32',datatype ='6',SHOWFORMAT='4' ,ISVISIBLE='1'
where tableid in(select tableid from dict_t_model where dealtype 
in('sdp_hccomt','sdf_hccomt','pb_hccomt','ej_hccomt','yj_hccomt','spf_sed_ps','spf_sed_fc','spf_sed_pc','spf_sed_fs','spf_sdejpa','spf_sdejph',
'spf_sdyjpa','spf_sdyjph','66_JS','spf_yjpa','fb_hccomt','sdej_comt','sdyj_comt','spf_ejpa','spf_ejph','spf_fc','spf_fs','spf_pc',
'spf_ps','spf_yjph','69_JS','68_JS')) and DBCOLUMNNAME='SPFID';
   END LOOP;
END;
--项目库 项目库纵向流程方向 代码表CODE_T_SPFWFDIRECTION


--CODE_T_SPFWFDIRECTION
