declare
  v_n number(6) :=0;
begin

  SELECT count(1) into v_n FROM user_tab_columns WHERE upper(TABLE_NAME)='P#KPI_T_FZXZJJXZB' and upper(column_name)='SPFKPINAME';
   --P#KPI_T_FZXZJJXZB修改指标名称字段SPFKPINAME
  if v_n = 1 then
     execute immediate Q'{alter table P#KPI_T_FZXZJJXZB rename column SPFKPINAME to KPINAME}';
     execute immediate Q'{CREATE OR REPLACE VIEW KPI_T_FZXZJJXZB AS
SELECT DBVERSION, STATUS ,ISINSERT ,DATAKEY ,STANDSCORE ,SYNSTATUS ,KPINAME ,ORDERID ,FDCODE ,ORIGCODE ,SPFID ,KPICONTENT ,TEMPLATEID ,ISTEMPLATE ,LEVELNO ,ISQZX ,SUPERID ,CELLSECU ,ISUPDATE ,ISLEAF ,KPISCORE ,ISDJ
FROM P#KPI_T_FZXZJJXZB WHERE STATUS='1'  AND PROVINCE = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID')}';
     execute immediate Q'{CREATE OR REPLACE VIEW SPF_T_ZXJXZBB AS
SELECT DBVERSION, STATUS ,kpi_t_fzxzjjxzb.DATAKEY AS DATAKEY ,kpi_t_fzxzjjxzb.SUPERID AS SUPERID ,kpi_t_fzxzjjxzb.ISTEMPLATE AS ISTEMPLATE ,
kpi_t_fzxzjjxzb.STANDSCORE AS STANDSCORE ,kpi_t_fzxzjjxzb.FDCODE AS FDCODE ,kpi_t_fzxzjjxzb.KPICONTENT AS KPICONTENT ,kpi_t_fzxzjjxzb.ORDERID AS ORDERID ,
kpi_t_fzxzjjxzb.TEMPLATEID AS TEMPLATEID ,kpi_t_fzxzjjxzb.ISLEAF AS ISLEAF ,kpi_t_fzxzjjxzb.KPINAME AS KPINAME ,kpi_t_fzxzjjxzb.LEVELNO AS LEVELNO ,
kpi_t_fzxzjjxzb.SPFID AS SPFID ,kpi_t_fzxzjjxzb.ISUPDATE AS ISUPDATE ,kpi_t_fzxzjjxzb.ORIGCODE AS ORIGCODE ,kpi_t_fzxzjjxzb.ISQZX AS ISQZX ,
kpi_t_fzxzjjxzb.KPISCORE AS KPISCORE ,kpi_t_fzxzjjxzb.SYNSTATUS AS SYNSTATUS ,kpi_t_fzxzjjxzb.ISINSERT AS ISINSERT ,kpi_t_fzxzjjxzb.CELLSECU AS CELLSECU ,
kpi_t_fzxzjjxzb.ISDJ AS ISDJ  FROM KPI_T_FZXZJJXZB kpi_t_fzxzjjxzb WHERE 1 = 1}';
	END IF;
	UPDATE P#DICT_T_FACTOR SET DBCOLUMNNAME = 'KPINAME' WHERE tableid = (SELECT tableid FROM dict_t_model WHERE dbtablename = 'KPI_T_FZXZJJXZB') AND dbcolumnname = 'SPFKPINAME';
  UPDATE P#DICT_T_FACTOR SET DBCOLUMNNAME = 'KPINAME' WHERE tableid = (SELECT tableid FROM dict_t_model WHERE dbtablename = 'SPF_T_ZXJXZBB') AND dbcolumnname = 'SPFKPINAME';
end;
--绩效_160816修改专项绩效指标表字段
