begin

delete from DICT_T_SETTINGBUSIINFO  where datakey = '36CA2839DADE3D48E053CB01A8C06ECA';
delete from DICT_T_SETTINGBUSIINFO  where datakey = '333DC7EA7811FE8BE050A8C0F000473E';
delete from DICT_T_SETTINGBUSIINFO  where datakey = '333DC7EA7812FE8BE050A8C0F000473E';

insert into DICT_T_SETTINGBUSIINFO (APPID, DATAKEY, NAME, ORDERID, URL, STATUS, IS_AJAX)
values ('BGT', '333DC7EA7811FE8BE050A8C0F000473E', '�ع��ۺ���ͼ', 1, '/exp/view/rebuildViewForMenu.do', '1', '1');

insert into DICT_T_SETTINGBUSIINFO (APPID, DATAKEY, NAME, ORDERID, URL, STATUS, IS_AJAX)
values ('BGT', '333DC7EA7812FE8BE050A8C0F000473E', '�ع���Դ�����ͼ', 2, '/colligate/collTable/initSplitTable.do', '1', '1');

insert into DICT_T_SETTINGBUSIINFO (APPID, DATAKEY, NAME, ORDERID, URL, STATUS, IS_AJAX)
values ('BGT', '36CA2839DADE3D48E053CB01A8C06ECA', '�ع�Ԥ������ܱ�', 1, '/colligate/collTable/initColidataTable.do', '1', '1');

end;
---Ԥ�㳣�ù���ע��_����
