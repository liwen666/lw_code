begin

  DELETE from P#Dict_t_Model
   where tableid in ('1C15CBDDC691417D98D3751BBF3C08D8',
                     '894896FFA37F4B73A1340D59D6245383');
  DELETE from P#Dict_t_FACTOR
   where tableid in ('1C15CBDDC691417D98D3751BBF3C08D8',
                     '894896FFA37F4B73A1340D59D6245383');

  update dict_t_model
     set suitid = 'E02CDB44C69945FFBE1781F6729AD54D'
   where tableid in ('33B6826A67014FCAB9EC1F41B485BCB6',
                     'E1F9D17D856A444183F9158057784055',
                     'F7099198E0819B06E040A8C020035519',
                     'F7097096422CDF47E040A8C020035309');                 
                     
  update dict_t_factor
     set csid = null, datalength = '32', datatype = '3', showformat = null
   where columnid = '1616CA05494BC781E050A8C0210512EA';

   update dict_t_factor set csid = null, datalength = '32',
   datatype = '3', showformat = null
   where columnid = '1607A121988241B1E050A8C02105646D';
--删除角色快捷菜单设置
DELETE  FROM fasp_t_pubmenu WHERE GUID IN('11202113','2201204') AND NAME ='角色快捷菜单设置';
end;
--修改合同
