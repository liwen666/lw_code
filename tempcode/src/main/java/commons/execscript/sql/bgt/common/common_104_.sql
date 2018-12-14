begin
  update BIL_T_SETCODE_B b set b.CODESHOW='yyyy-MM-dd' where b.BASETYPEID='006';
end;
--单据编码设置更改日期默认示例
