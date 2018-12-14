declare 

begin
   update  P#SPF_T_CONDISET  set CONDITION='$audit$ != ''0''' where guid='3C734822ADED6555E053CB01A8C0A9AA';
end;--修改二级项目审核主列表条件
