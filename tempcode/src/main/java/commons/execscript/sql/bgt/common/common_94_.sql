begin
  update BIL_T_SETCODE_B b set b.ISSPESTR='3' where b.BASETYPEID='006';
  update BIL_T_SETCODE_B b set b.ISSPESTR='0' where b.BASETYPEID='009';
end;--���ݽű�5#Ҧ����
