BEGIN
  UPDATE P#dict_t_model SET ISALLYEAR ='0'
  WHERE tableid IN('94C0F208AB0E407E9800093043FA7692','9FC09B974CCE4342BB190FA2A47D4B20');
  sys_p_recreate_views('94C0F208AB0E407E9800093043FA7692');
  sys_p_recreate_views('9FC09B974CCE4342BB190FA2A47D4B20');
  DELETE FROM FASP_T_PUBMENU  WHERE GUID IN('1120010101','112002031','12000302','12000402');
  UPDATE P#dict_t_Model SET TABSWHERE  ='spfid in (SELECT spfid
                   FROM SPF_T_FBASEINFO
                  WHERE PROJTYPEID IN
                        (SELECT PROJTYPEID
                           FROM SPF_T_PROJECTTYPE
                          START WITH PROJTYPEID = ''1''
                         CONNECT BY PRIOR PROJTYPEID = SUPERID))' WHERE TABLEID ='B4719068F4ED492BB1A782247FAEE701';
                         
                         
                         sys_p_recreate_views('B4719068F4ED492BB1A782247FAEE701');
END;
--将专项、项目物理表改成非全地区访问及删除项目编辑、专项编辑
