update fasp_t_pubmenu pm set pm.url = pm.url||chr(38)||'appID=KPI'
 where pm.appid = 'kpi'
   and url is not null
   and parentid not in ('22008',
                        '22009',
                        '22010',
                        '22011',
                        '22012',
                        '7A08A9B9231B405BA6F1435F66DE5178',
                        '0')
  and url not like '%appID=KPI%'
--¼¨Ð§_20160713_BZY²Ëµ¥¼ÓappID
