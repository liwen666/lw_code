BEGIN
EXECUTE IMMEDIATE Q'{
create or replace view code_v_spf2dist as
select "SPFID","SPFNAME","SPFCODE","DEPTID","FIRAGENCYID","DISTRICTID","ISCUSTODY","ISTEMP" from (
select s.spfid,
       s.spfname,
       s.spfcode,
       s.deptid,
       s.firagencyid,
      ( select (case
             when code like '%00' then
              superguid
             else
              guid
           end) DISTRICTID
      from Code_t_District
     where guid = s.districtid ) districtid,
       '0' iscustody,
       s.ISTEMP
  from SPF_V_FBASEINFO s
union
select t.SPFID,
       t1.spfname,
       t1.spfcode,
       t.CUSTDEPTID deptid,
       t.CUSTFIRSTDIV firagencyid,
       t.DISTRICTID,
       '1' iscustody,
       t1.ISTEMP
  from spf_t_setcustody t, spf_v_fmain t1
 where t.SPFID = t1.spfid )}';
END;
--用户对专项加入缺少的表
