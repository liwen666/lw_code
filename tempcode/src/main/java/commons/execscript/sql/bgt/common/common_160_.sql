BEGIN
  UPDATE Fasp_t_Pubmenu SET URL='/bgt/exp/datainputOA/main.do?appID=BGT'||chr(38)||'operate=0' WHERE GUID ='113118';
  UPDATE Fasp_t_Pubmenu SET URL='/bgt/exp/datainputOA/main.do?appID=BGT'||chr(38)||'operate=820' WHERE GUID ='42432FDC2907BD9CE050A8C0210575E2';
  UPDATE Fasp_t_Pubmenu SET URL='/bgt/exp/datainputOA/main.do?appID=BGT'||chr(38)||'operate=50' WHERE GUID = '25D32FDC2907BD9CE050A8C0210575E2';
  UPDATE Fasp_t_Pubmenu SET URL='/bgt/exp/datainputOA/main.do?appID=BGT'||chr(38)||'operate=20' WHERE GUID = '174DBA542FCACCF6E050A8C021055C5E';
  UPDATE Fasp_t_Pubmenu SET URL='/bas/exp/datainputOA/main.do?appID=BAS'||chr(38)||'operate=0' WHERE GUID ='15761DCAC045390DE050A8C021050E32';
END;
--数据填报菜单sql_optView参数变更
