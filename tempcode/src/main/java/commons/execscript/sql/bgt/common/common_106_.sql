BEGIN
   DELETE FROM Dict_t_Public WHERE appID = 'BGT' AND keyid = '000000' AND TYPEID = 'DEFAULTYEAR';
   INSERT INTO Dict_t_Public(appID, keyid, keyname, RESERVE_1, TYPEID, typename) 
   VALUES ('BGT', '000000', '当前预算年度', '2016', 'DEFAULTYEAR', '当前预算年度');
END;
--设置默认预算年度
