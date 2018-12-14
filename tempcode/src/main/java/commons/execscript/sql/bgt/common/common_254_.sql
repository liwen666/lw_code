BEGIN
  execute immediate 'alter table P#BGT_T_BFLOW_LOG modify createtime default CURRENT_TIMESTAMP';
END;
--P#BGT_T_BFLOW_LOG表CREATETIME添加默认值
