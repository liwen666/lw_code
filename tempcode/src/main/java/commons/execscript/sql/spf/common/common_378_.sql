DECLARE
begin
delete from P#SPF_T_LISTREGIST;
 for v_row in(select * from pub_t_partition_divid t where t.year <> '*') loop
  begin
insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('003A62F1C8F549389D061DEEE1816B6D', '专项资金项目选择主表', '43', '1', v_row.year,v_row.districtid, '段艳岗');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('178CA52B38934EC4BFB81DD148794F8B', '二级项目预算调整主表', 'P6', '1', v_row.year,v_row.districtid, '袁福');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('2812729F2FC34AC09F0845497242F0D9', '专项资金备份主列表', '44', '1', v_row.year,v_row.districtid, '孙亚飞');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3ACD1747AE6442C7E053CB01A8C00510', '项目调整主列表', '52', '1', v_row.year,v_row.districtid, '程善强');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3ACF061AAA144E95E053CB01A8C0AE0D', '二级项目调整主列表', 'P2', '1', v_row.year,v_row.districtid, '程善强');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3B46957B6D695355E053CB01A8C0A532', '专项资金审核主列表', '41', '1', v_row.year,v_row.districtid, '孙亚飞');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3BB8025DFDE340C080AE2E414C55BFA0', '项目阶段主列表', '5*19', '1', v_row.year,v_row.districtid, '');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3BBFDA1254DE5799E053CB01A8C063B3', '项目审核主列表', '51', '1', v_row.year,v_row.districtid, '孙亚飞');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3BD3A699DF985848E053CB01A8C08E3A', '专项资金设立主列表', '40', '1', v_row.year,v_row.districtid, '袁福');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3C6F083E7EA849C6E053CB01A8C09ACB', '一级项目审核主列表', '31', '1', v_row.year,v_row.districtid, '袁福');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3C6F18047DC24AA4E053CB01A8C06500', '一级项目备份主列表', '36', '1', v_row.year,v_row.districtid, '孙亚飞');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('3C73E4C52EA96917E053CB01A8C0D7AF', '二级项目审核主列表', 'P1', '1', v_row.year,v_row.districtid, '孙亚飞');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('40BC19B3F10347FAA1A0F4B6CB866FCF', '项目选择回退主列表', '57', '1', v_row.year,v_row.districtid, '段艳岗');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('430F91CE113022D8E0533A06A8C04389', '项目删除主列表', '58', '1', v_row.year,v_row.districtid, '刘冰');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('43221282C91A3164E0533A06A8C09088', '二级项目删除主列表', 'P8', '1', v_row.year,v_row.districtid, '刘冰');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('43C3A50792217A89E0533A06A8C001F0', '专项资金回退选择主表主列', '4101', '1', v_row.year,v_row.districtid, '段艳岗');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('43FB46AD735763E1E0533A06A8C0DE5A', '二级项目回退主列表', 'P7', '1', v_row.year,v_row.districtid, '段艳岗');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('443A19F170F63773E0533A06A8C034C8', '项目排序主列表', '59', '1', v_row.year,v_row.districtid, '孙亚飞');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('443AD6E31B4E3BE0E0533A06A8C07AC0', '二级项目排序主列表', 'P9', '1', v_row.year,v_row.districtid, '孙亚飞');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('4890E5066351422AB39DBA4C8D22447D', '测算结果表', '2105', '1', v_row.year,v_row.districtid, '刘冰');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('4BB40E198701477BE0533A06A8C07253', '财政综合处理', 'FINANCE', '1', v_row.year,v_row.districtid, '孙亚飞');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('4BD61BBACFEA4F87E0533A06A8C02BD2', '专项预算编制', '45', '1', v_row.year,v_row.districtid, '段艳岗');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('4CEF7C81C76B4ED5E0533A06A8C08EFF', '项目综合处理', 'PROJZHCL', '1', v_row.year,v_row.districtid, '孙亚飞');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('4E04B5F750A24B929F2CECA8040DA9D0', '专项申报查询视图', '4*60', '1', v_row.year,v_row.districtid, '程善强');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('5D7CE32B119945448B0E0B34B906D6E0', '一级项目设立主列表', '30', '1', v_row.year,v_row.districtid, '袁福');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('60A1775A8B2A44048AF5318F0DB098D7', '项目纳入指标主列表', '55', '1', v_row.year,v_row.districtid, '袁福');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('6264F8A711194EFFA77669E562205783', '二级项目纳入预算主列表', 'P4', '1', v_row.year,v_row.districtid, '袁福');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('65D7BFB144104FCF9357842223864D2E', '项目纳入预算主列表', '54', '1', v_row.year,v_row.districtid, '袁福');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('6ADF6B034CBE4D39A64AA24D4404713C', '项目预算调整主列表', '56', '1', v_row.year,v_row.districtid, '袁福');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('712ABBA82F104F559283585B30E60766', '一级项目回退选择主列表', '3101', '1', v_row.year,v_row.districtid, '段艳岗');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('76F72262AC3C479C9343D4B87F680A69', '项目发布主列表', '46', '1', v_row.year,v_row.districtid, '王玉哲');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('852609AED9F447A892478D4CBEA49336', '二级项目纳入指标主列表', 'P5', '1', v_row.year,v_row.districtid, '袁福');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('9575711D24664091846E7AEE70F6B936', '一级项目调整主列表', '32', '1', v_row.year,v_row.districtid, '程善强');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('A50A8B53A4754D60BE1C86123C03F355', '二级项目申报主列表', 'P0', '1', v_row.year,v_row.districtid, '孙亚飞');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('AC5843C6C9274B36816B4AA7863185FF', '项目备份主列表', '53', '1', v_row.year,v_row.districtid, '孙亚飞');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('BC789F15599B49288FEA730DAA2EDD93', '项目排序主表', '5*40', '1', v_row.year,v_row.districtid, '孙亚飞');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('BF30BA7087A3402282E50550057A613D', '测算专项主表', '4*03', '1', v_row.year,v_row.districtid, '刘冰');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('C2912846F560447CBA13776BC0B1E902', '综合视图主列表', 'ZHCX*01', '1', v_row.year,v_row.districtid, '王玉哲');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('D264D878B1464A478AD3544FD1DA77F1', '二级项目排序表', 'P*40', '1', v_row.year,v_row.districtid, '孙亚飞');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('F335EF93961F4AF29C5EFE134C981C45', '项目申报主列表', '50', '1', v_row.year,v_row.districtid, '袁福');

insert into P#SPF_T_LISTREGIST (GUID, DNAME, DEALID, STATUS, YEAR, PROVINCE, RESPONSER)
values ('F883433FFA2F492CA959E793376A1E77', '专项资金调整主列表', '42', '1', v_row.year,v_row.districtid, '程善强');


    end;
 end loop;
end;
--SPF_T_LISTREGIST
