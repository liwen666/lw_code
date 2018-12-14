DECLARE
begin
delete from P#SPF_T_LISTCONDISET;
 for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop
  begin
insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('06161350DE4D42798F74F35E73351A2B', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'AAAA6EF4D0B5427D8ADE8310B41DC7B6', 2, 'AAAA82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('0CAC8AE3B46949FABB0949446E837FBC', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '7B77779BD2C844E4BA491E7FF372B900', 3, '59011EB26787484DB6CAED40F62D79D9',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('0EB2C8712E7640798000F518103968E3', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '432148DC1B542A95E0533A06A8C06FFA', 2, 'B3A5CFF14F594C619A653A658D0A05FF',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('18D6F7C4712442E4AEF07082389018C9', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'DDDD6EF4D0B5427D8ADE8310B41DC7B6', 2, 'DDDD82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('1CAE49F04E264ACCA46207AB57E078A9', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '9ADA5B89F1AE43CDA7132445B8B2CAB3', 3, '94C0F208AB0E407E9800093043FA7692',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('26F5E7A66FB2497F96CFDD1A022C2B84', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'CCCCF82D4595458EBF2004592A03206A', 3, 'CCCC82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('2A77533990FE42F7AD8A3936775DFBD8', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '20DF90EF2EB94A52B66E3854A23A1C16', 2, 'B375DD5D93E44B99A28F2B7A99C4A7CA',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('2D031B45121241AE96D271C2C3A99E50', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'C525F2B3B7904EBD94368BA93C1D8C65', 2, '783B60D3816D469A93D48F6C6DDF3917',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('2E92D1213D9B40E7BBBAF3FA8D5F2A55', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'BFF579A8F345402990152FCA58D22873', 2, '364E4DE8AA09466C9BC062CED3D12F39',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('2EC8DD6D215E47E79394FF37B248AF6C', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '64FDB45307274052BE02217D0D7787A5', 2, 'E73347B088B4461883786B6B69A44273',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('30162D49BBC241C290884088EEAB6917', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', 'CDDAA18D236D420E84B0EB1115504125', 1, 'C773F13BD91D447E9E3A95D1A92D35C3',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('32713B505E8244379E5436ED38A2AAF9', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', '4AEAD7131AA14F5BE0533A06A8C07D77', 1, 'FD3E1F0B0B274C3A85C683910498F228',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('32800414B0154CEF9C981D46533D707B', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', '1F8DE9250A4040F9B24F55BD1EECF930', 1, '71B119DA84CE404C8C3428F234D8D509',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3441BB6074A14E9286EA1384F44AC883', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'CCCC6EF4D0B5427D8ADE8310B41DC7B6', 2, 'CCCC82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('392F6A54581E407EBC9C19F378B2019D', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'DDDDF82D4595458EBF2004592A03206A', 3, 'DDDD82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3ACD4C4FAE244408E053CB01A8C0DB23', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '50FD9F106635489BA0891557A19649E9', 2, '6308DB25C4BB449FA5E1E98B0ADBDACB',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3ACD4C4FAE254408E053CB01A8C0DB23', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '9DEEF421B9F944909AA9F1159A02CCB1', 3, '6308DB25C4BB449FA5E1E98B0ADBDACB',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3ACF2926206250D0E053CB01A8C0001B', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '8877E6FDD8CA4AD7A61D860F47E1E244', 2, '4F0473A1473646FB9C086F7D5CACD03F',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3ACF2926206350D0E053CB01A8C0001B', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '353642AB1A68479C81BC4A3D533C3C89', 3, '4F0473A1473646FB9C086F7D5CACD03F',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3ADC9AFFB9D1242AE053CB01A8C08539', 'CODE_T_AGENCY_SPF', '1708CF6F853F76EDE050A8C0210507FC', 'B768243ED4B34341891438FC9D98BDC5', 1, '0339012C49674571A9DCA0A8744FC94E',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3ADC9AFFB9D2242AE053CB01A8C08539', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'FC86EAC6B05F40AEA59160614EF4E694', 2, '0339012C49674571A9DCA0A8744FC94E',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3B46958FCB545359E053CB01A8C0B995', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', 'EE6C0CA0A907432581CAE99D4232B7BF', 1, '79592CE1019A436EA6B261AFD4999711',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3BCE39BF6DE53243E053CB01A8C0153A', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', 'C40D1F00331A434AA7C5B365A50390ED', 1, 'F7C195DD3FA44D3EA2843A08255E389A',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3BCE39BF6DE63243E053CB01A8C0153A', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'C837072679B84286B55144759BABC668', 2, 'F7C195DD3FA44D3EA2843A08255E389A',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3BCE39BF6DE73243E053CB01A8C0153A', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '69AE6D782CDA4147B9FDE0D11FB6A855', 3, 'F7C195DD3FA44D3EA2843A08255E389A',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3BD3A699DF935848E053CB01A8C08E3A', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', '62902188F6A643F5B0293BDC616A3F2E', 1, '1A96A94CD8A8445EBA7B4A8F7FFCFE9B',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3BD3A98D9F87584EE053CB01A8C07EEA', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '522D9B0F6FDE4505A57801D0AC2B4DF8', 2, '1F66766D2AC349F89DAC15D97F9BC7D0',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3BD3A98D9F88584EE053CB01A8C07EEA', 'CODE_T_SP_TYPE', '148830CAF3E61F78E050A8C021056ABE', '256B9E057ED548838E2A1AFD3BCFD9EA', 3, '1F66766D2AC349F89DAC15D97F9BC7D0',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3C4C5C60869D6993E053CB01A8C02850', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'C020C4D5630F4C9EB623C5E396C8FE0A', 3, 'B375DD5D93E44B99A28F2B7A99C4A7CA',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3C6F083E7EA949C6E053CB01A8C09ACB', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '2F418CA4DD2BF444E050A8C02105683B', 2, '2F418CA4DD24F444E050A8C02105683B',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3C6F083E7EAA49C6E053CB01A8C09ACB', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', '2F418CA4DD2FF444E050A8C02105683B', 1, '2F418CA4DD24F444E050A8C02105683B',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3C6F18042CC34AA1E053CB01A8C0B36F', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '06993A50314142248F63662F4513007F', 2, '83C77CBCACBE459DB98F6026150E65F2',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3C6F18047DC34AA4E053CB01A8C06500', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'C548774BA58A40DE8CFB7BD2A07525BC', 2, 'AD6EE9C7B42842F1BC8348C3FBE5583F',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3C6F18047DC44AA4E053CB01A8C06500', 'CODE_T_DISTRICT_SPF_CS', '5771989AF427418B84E84CEDD68C8837', '21BFB00C954541A7A9643FEDB665F917', 1, 'AD6EE9C7B42842F1BC8348C3FBE5583F',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3C734822ADEA6555E053CB01A8C0A9AA', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'B2806151F5D94C21B33182EED9C4BE8D', 2, '7163BDE8CE274EBB9854FA43DDEC5229',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('3C734822ADEB6555E053CB01A8C0A9AA', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '4756816B003E4C57B0688CC820D9D5EE', 3, '7163BDE8CE274EBB9854FA43DDEC5229',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('433664D00EFC3B55E0533A06A8C03B27', 'CODE_T_DISTRICT_SPF_CS', '5771989AF427418B84E84CEDD68C8837', 'DDDDFB82068E4A37ADA8ADDD4E3AEB33', 1, 'DDDD82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('43C04AAEE7F160B5E0533A06A8C09B3F', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'CBF7E713A78441929617C88BB20D9647', 1, '101662280BB64532A4BA9C86ADE04E82',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('43C04AAEE7F260B5E0533A06A8C09B3F', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '115B8D105CA14FF497C1EBB26ABC3E3C', 2, '101662280BB64532A4BA9C86ADE04E82',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('43C04AAEE7F560B5E0533A06A8C09B3F', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '3D1B15187D1F4F2790001F6562A4D84D', 1, '5B90D1A8546C45D086866831D9A7D04D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('43C04AAEE7F660B5E0533A06A8C09B3F', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '4275E09AC1DD4BD2A067AAD23A1FCC55', 2, '5B90D1A8546C45D086866831D9A7D04D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('43C1ED54AFC16D40E0533A06A8C06562', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '3E6C381573DD13F3E053CB01A8C0F3A2', 1, '3E6C3815739B13F3E053CB01A8C0F3A2',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('43C636D3C39A0E1FE0533A06A8C05CF4', 'FASP_V_PUBADMDIV', 'B3161B73D098401D8B6F95BDACB1F484', 'C4410A260AA542BCBF3A1BEB085913B5', 1, '2C58567F7CB749EF9F993BE1162D15F8',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('43C636D3C39B0E1FE0533A06A8C05CF4', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'E6F1FD8BB0BD44D79767023045BE19C9', 2, '2C58567F7CB749EF9F993BE1162D15F8',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('43FD89EFBF2974F0E0533A06A8C0518F', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'DC2F844A31B04B8CAF665E16B5BF82B6', 1, '76659007F21F4CF780E1E7CA9035D504',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('43FD89EFBF2A74F0E0533A06A8C0518F', 'CODE_T_FCSSPF', '43860AA96E7F30AEE0533A06A8C07C8E', '01FB3EB52A134B93B91DB36854C776BE', 2, '76659007F21F4CF780E1E7CA9035D504',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4401279C23BE1615E0533A06A8C06EA3', 'CODE_T_DISTRICT_SPF_CS', '5771989AF427418B84E84CEDD68C8837', 'CCCCFB82068E4A37ADA8ADDD4E3AEB33', 1, 'CCCC82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4401279C23BF1615E0533A06A8C06EA3', 'CODE_T_DISTRICT_SPF_CS', '5771989AF427418B84E84CEDD68C8837', 'EEEEFB82068E4A37ADA8ADDD4E3AEB33', 1, 'EEEE82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4401279C23C01615E0533A06A8C06EA3', 'CODE_T_DISTRICT_SPF_CS', '5771989AF427418B84E84CEDD68C8837', 'FFFFFB82068E4A37ADA8ADDD4E3AEB33', 1, 'FFFF82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('443A1932A9063952E0533A06A8C0697F', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '44396A156199314CE0533A06A8C09BDE', 2, '44396A156191314CE0533A06A8C09BDE',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('443A1932A9073952E0533A06A8C0697F', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '44396A1561AB314CE0533A06A8C09BDE', 3, '44396A156191314CE0533A06A8C09BDE',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('443AD6E31B4F3BE0E0533A06A8C07AC0', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '443A19F171173773E0533A06A8C034C8', 2, '443A19F171013773E0533A06A8C034C8',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('443AD6E31B503BE0E0533A06A8C07AC0', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '443A19F1710D3773E0533A06A8C034C8', 3, '443A19F171013773E0533A06A8C034C8',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('486458F051EE4DC8ABA5AA212A55ED64', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '2D30A2ABFA27F6DEE050A8C021050AD0', 2, '2D30A2ABFA1FF6DEE050A8C021050AD0',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4A157AFDB16942FEB14C08EF4EA32575', 'CODE_T_AGENCY_DEPT_SPF', '379634C2CAFF55E0E053CB01A8C0CC9B', 'E5AF563B46E8416DBB788942AE8B9B6C', 1, 'CCCC82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4A2FC1482B5C2FC8E0533A06A8C0118B', 'CODE_T_DISTRICT_SPF_CS', '5771989AF427418B84E84CEDD68C8837', '13D4896596D443CD9C87E2C8667252DF', 1, '1F66766D2AC349F89DAC15D97F9BC7D0',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4AC4A9D5D0BC20E7E0533A06A8C0ECB4', 'CODE_T_DISTRICT_SPF_CS', '5771989AF427418B84E84CEDD68C8837', '4AD047CAF9337832E0533A06A8C05608', 1, 'B375DD5D93E44B99A28F2B7A99C4A7CA',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4ACFB086B6E67222E0533A06A8C0CA65', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '2F418CA4DD3BF444E050A8C02105683B', 3, '2F418CA4DD24F444E050A8C02105683B',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4ACFB086B6EF7222E0533A06A8C0CA65', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '677F9ADCF2494A489F2108D7FFD7519A', 3, 'AD6EE9C7B42842F1BC8348C3FBE5583F',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4ACFD052B34474C2E0533A06A8C03BD1', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '873F2460DB244D8DB0F4887F0BAF2784', 3, '79592CE1019A436EA6B261AFD4999711',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4ACFD052B34974C2E0533A06A8C03BD1', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'F37773A2A4134920ABDD67A03959378F', 3, '83C77CBCACBE459DB98F6026150E65F2',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4AEAF6948295505DE0533A06A8C0E16C', 'CODE_T_DISTRICT_SPF_CS', '5771989AF427418B84E84CEDD68C8837', '4AEAEA212CC54E82E0533A06A8C0BD8B', 1, '44396A156191314CE0533A06A8C09BDE',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4AEAF6948296505DE0533A06A8C0E16C', 'CODE_T_DISTRICT_SPF_CS', '5771989AF427418B84E84CEDD68C8837', '3882EDEA9C820036E0530A44050645A7', 1, '7163BDE8CE274EBB9854FA43DDEC5229',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4AEB2B4C4A3451FBE0533A06A8C0F09D', 'CODE_T_DISTRICT_SPF_CS', '5771989AF427418B84E84CEDD68C8837', '4AEAF6948298505DE0533A06A8C0E16C', 1, '443A19F171013773E0533A06A8C034C8',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4C11E068F44849BCE0533A06A8C06DAC', 'CODE_T_DISTRICT_SPF_CS', '5771989AF427418B84E84CEDD68C8837', '4BD5A267544C4AD7E0533A06A8C04D54', 1, '4BD5A26754354AD7E0533A06A8C04D54',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4C11E068F44949BCE0533A06A8C06DAC', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '4BD5A26754494AD7E0533A06A8C04D54', 2, '4BD5A26754354AD7E0533A06A8C04D54',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4C11E068F44A49BCE0533A06A8C06DAC', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '4BD5A26754374AD7E0533A06A8C04D54', 3, '4BD5A26754354AD7E0533A06A8C04D54',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('4CF54C9324CB0887E0533A06A8C01CD6', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '38AA7C8439C9001AE0530A4405064AEC', 1, '38AA7C84397D001AE0530A4405064AEC',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('52B90271566C4E6CA00753DA89473D40', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '85F2298F152D4BCBB166C99EC748EC1C', 3, 'B3A5CFF14F594C619A653A658D0A05FF',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('55D37AE3D362458694B2A42E67C60410', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'EC4C669631AD4DAFAF5BAC39497B087F', 3, '76512FBCCC8E4134820BC1DDD44AB684',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('5B5D20A23BDA4CBB8333DD72409C98AF', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '19F8AD572A104BCDA7C21CAA02FEFC53', 2, '94C0F208AB0E407E9800093043FA7692',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('5E559F59CBE04FC6A059E2977716EEC6', 'FASP_V_PUBAGENCY', '221F3A1195C14042A4724FCACC60CCC0', '773BF8A727FB485DA0348AD771110768', 1, '364E4DE8AA09466C9BC062CED3D12F39',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('5EDFA90619BB43DA88CBA1EDD09F4471', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'A648646F165B450DB671D831FE3B64A7', 2, '71B119DA84CE404C8C3428F234D8D509',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('5F72CE7EFE4B42F799E1723CAE71895B', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'D4A02DC11DA748318B4CB4183B7C4AA1', 2, '59011EB26787484DB6CAED40F62D79D9',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('60C32695F0834B8EAA0330945FDCBD65', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'FA8850AB4FDA702DE040A8C02105777E', 3, 'F9CB8D34BC3263AEE040A8C021050BA8',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('61F7EEFE890C4249BEA70BDED909DCAD', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '9EF7ABD720FC4037A1934FB8B3F41FBB', 2, '76512FBCCC8E4134820BC1DDD44AB684',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('6363168FF73C48F89C42EEE65A9F53B7', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '74A7C90A072445E0B26F270E0EDC2270', 2, '8CADB101B53E41C18FC2FA9510D9A7DC',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('655C60134591429391503BC30A6794B5', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', 'AAAAFB82068E4A37ADA8ADDD4E3AEB33', 1, 'AAAA82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('6A2E5117B1CD4682A71544F021066BF5', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', 'BBBBFB82068E4A37ADA8ADDD4E3AEB33', 1, 'BBBB82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('6C7870C5FC1A42E397712D09A28B49CA', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', '4AC2748873780601E0533A06A8C0E3C3', 1, '6308DB25C4BB449FA5E1E98B0ADBDACB',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('6D81E0CE4AA3461588994DD38B8B6AEB', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'BBBBF82D4595458EBF2004592A03206A', 3, 'BBBB82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('702BB365BF5F4B1E8796CE3563BA07B5', 'CODE_T_AGENCY_DEPT_SPF', '379634C2CAFF55E0E053CB01A8C0CC9B', 'D6C48742A2F4426B8CFE5458DF45CFB2', 1, 'DDDD82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('7210A2EB57284A6CB0E55C0FDEA489CA', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'FFFF6EF4D0B5427D8ADE8310B41DC7B6', 2, 'FFFF82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('73B614AE11904215B74CF5F0A2804199', 'FASP_V_PUBDEPARTMENT', '8FCC6B91BADC44E0BC73A2CC22CC9FBF', '4A9D15F8F84D48179073D51A0CB3086A', 1, '1A96A94CD8A8445EBA7B4A8F7FFCFE9B',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('74AB45C066A847C89E9FEC70D57DF24A', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'D888968251F049A1A6DC4227217A0515', 2, '79592CE1019A436EA6B261AFD4999711',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('753A5F114C9041AD94ED630539756BAE', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', '6F01F1742F624A1BB87F91DFC44B827E', 1, '783B60D3816D469A93D48F6C6DDF3917',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('7A586FBDEAFE46F4A137CED68007DF92', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'EEEE6EF4D0B5427D8ADE8310B41DC7B6', 2, 'EEEE82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('910F27584BBD434FA60A4FBA634B8C93', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '3AF76701EA4F4DB9E053CB01A8C01673', 2, '3AF76701EA4B4DB9E053CB01A8C01673',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('92765E8B22B54273AC042C88CFB4186D', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'CFF2FA0D92A24F01862FC098840265EA', 2, 'EFF66CD4720A44F89C4C0BADAC8FEA46',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('94E81955C7554B2B91C9BB25FFB03EC5', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '3955365AD18E4CE99ABA5830CE10C659', 3, '71B119DA84CE404C8C3428F234D8D509',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('964BA83E9A2645EA9B4CBF3484DDB0D5', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', '67C3AC9E949A4DE9A79E2137BDC3C6E0', 1, '83C77CBCACBE459DB98F6026150E65F2',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('97AAC40DACB74B90A571AE3E991F2E75', 'FASP_V_PUBAGENCY', '221F3A1195C14042A4724FCACC60CCC0', '0023743DC28C4133AED1534D55F0AFCF', 1, '69722A5DAFA64394B6BC8E7F7DDCC99E',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('97DBF6C64D7F47D1B37CC88C27D4355D', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', 'C0C0804A8E8B4428A95ACDDF9654E3F6', 1, '59011EB26787484DB6CAED40F62D79D9',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('9A44BA0B7E0F47BB86E5EC91675CC74B', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'AAAAF82D4595458EBF2004592A03206A', 3, 'AAAA82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('9B0BDB0FB0CB44CA974A6B4282B9640E', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'F5BE7E7C8A214319AC077F5396149F6F', 2, 'FD3E1F0B0B274C3A85C683910498F228',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('9B7E7E6697B347C3ACB237EED9D0C343', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'CA47A836BE3849EFB73C949E39B11BC3', 3, 'C773F13BD91D447E9E3A95D1A92D35C3',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('9C010C2A790E4F7A81EA8034F2E78694', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'EE2E41E14D854E43832AF88D6AC0DCC6', 2, 'ABD8B909C1E54D12B6FBDBB84F962639',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('9C51F0E30A1447CF9B017FFF0DCA725D', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'EEEEF82D4595458EBF2004592A03206A', 3, 'EEEE82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('9CDB89F595AC4934872C22271E00B3B8', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '62EDAEBD2DE14F9487630F11687181EE', 2, 'C773F13BD91D447E9E3A95D1A92D35C3',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('9F33BD6E816B42CF9B3C7126273835CE', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'FC310116D04C40BFBF5A11619421330D', 1, '8CADB101B53E41C18FC2FA9510D9A7DC',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('A4EEB2FD3E5446A0811E77F04C6B05B3', 'CODE_T_AGENCY_DEPT_SPF', '379634C2CAFF55E0E053CB01A8C0CC9B', '3164873A03BC4172B7357AC1E19E24AF', 1, 'EEEE82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('A8217721B2DC410780461F7700994F87', 'FASP_V_PUBDEPARTMENT', '8FCC6B91BADC44E0BC73A2CC22CC9FBF', 'F9CB8D34BC4863AEE040A8C021050BA8', 2, 'F9CB8D34BC3263AEE040A8C021050BA8',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('AB05B95464084CB4AF22C4F057081A0A', 'FASP_V_PUBDEPARTMENT', '8FCC6B91BADC44E0BC73A2CC22CC9FBF', '3AF76701EA504DB9E053CB01A8C01673', 1, '3AF76701EA4B4DB9E053CB01A8C01673',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('B7A9CCB701DB467CA5B25AD1ECE37D42', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', '2D30A2ABFA2FF6DEE050A8C021050AD0', 1, '2D30A2ABFA1FF6DEE050A8C021050AD0',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('B84128453F6244FB8FB1877E1EB0BE08', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '7D61DD5255F5491FB4040A239931B89D', 3, '783B60D3816D469A93D48F6C6DDF3917',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('BA41F404B3114F05BF8B8C8D6929526A', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', '7AF8E62D852A41719BC586F6CE3E1BF6', 1, 'B3A5CFF14F594C619A653A658D0A05FF',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('BB80ADCD2A814A6BAF17AFC81A483A88', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '2D30A2ABFA35F6DEE050A8C021050AD0', 3, '2D30A2ABFA1FF6DEE050A8C021050AD0',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('BC5AD70C6F0B47C781BF63ACDC2368B8', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', '881B88A08CB04F52B87F5399646EC237', 1, 'ABD8B909C1E54D12B6FBDBB84F962639',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('C03A141A14F2435DAC7A8923A426352C', 'FASP_V_PUBDEPARTMENT', '8FCC6B91BADC44E0BC73A2CC22CC9FBF', 'B2119EF77AE04FE0A589580DD7E59F0F', 1, '94C0F208AB0E407E9800093043FA7692',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('C6835EF8BE5048EF85D76BB3B2D68570', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'B471A4A9D88542D3A309F1724FD510C1', 3, 'FD3E1F0B0B274C3A85C683910498F228',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('CDE5EECF629C4F2CB8C7810DC611981E', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', '56FFC5C27C344793B46C6F9D1AFBED6D', 2, '69722A5DAFA64394B6BC8E7F7DDCC99E',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('D44DF885B6404AE0A69BA9A4E2266042', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', '4AC2748873790601E0533A06A8C0E3C3', 1, '4F0473A1473646FB9C086F7D5CACD03F',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('D62D9D750AA94E6CBBD264FD83F22886', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'A605401491E04C8B8FF76C52A3997AAD', 3, 'ABD8B909C1E54D12B6FBDBB84F962639',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('D8D395B32D2B4F2084AEBB6BD643631B', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'A5D0D65797A848B39BB52BDB22283E89', 3, 'EFF66CD4720A44F89C4C0BADAC8FEA46',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('D9529FB819E946D8994B0B0C45D213ED', 'CODE_T_AGENCY_SPF', '1708CF6F853F76EDE050A8C0210507FC', 'E9295B24B509440EBDB3A174B087F03C', 1, 'E73347B088B4461883786B6B69A44273',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('E069B81A609C45A9B257F3B4BE0817A6', 'CODE_T_DISTRICT_SPF', 'D754323AE16C448AAB86064FA77B96CE', '78F8FF1C19D94A85B6862423DE73E253', 1, '76512FBCCC8E4134820BC1DDD44AB684',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('E1DDA53DD9154D43AA3E091C444A48D6', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', '6B7D5A404F974F299D2540F645854F3D', 2, '1A96A94CD8A8445EBA7B4A8F7FFCFE9B',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('EEF2249F3BCD4630923C363AA5E4D47D', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'BBBB6EF4D0B5427D8ADE8310B41DC7B6', 2, 'BBBB82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('F353367D3FFA4CCFBBC763B676373713', 'FASP_V_PUBDEPARTMENT', '8FCC6B91BADC44E0BC73A2CC22CC9FBF', '2C57BAE41921448BB1CCED982099F499', 1, 'EFF66CD4720A44F89C4C0BADAC8FEA46',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('F38858C1517B4C9994E31509091BC221', 'CODE_T_FIRAGENCY', '14DA206175AB0A8FE050A8C021055537', 'F9CB8D34BC4663AEE040A8C021050BA8', 1, 'F9CB8D34BC3263AEE040A8C021050BA8',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('F4A60A780A8D452C8A6E6AAB1FE2FE3A', 'CODE_T_PROJECTANDSPF', '14DADAE88B8B57D0E050A8C0210557DD', 'FFFFF82D4595458EBF2004592A03206A', 3, 'FFFF82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');

insert into P#SPF_T_LISTCONDISET (GUID, MODECODE, MODELID, FACTOR, ORDERID, TABLEID, PROVINCE, YEAR, STATUS)
values ('F8996EA21D714813ABCFEBF0CA1C3362', 'CODE_T_AGENCY_DEPT_SPF', '379634C2CAFF55E0E053CB01A8C0CC9B', 'B415447ACC5F4157A1BC15EE02BEFFAA', 1, 'FFFF82614F624DEF84E88BE30959468D',  v_row.districtid,v_row.year, '1');


    end;
 end loop;
end;
--SPF_T_LISTCONDISET
