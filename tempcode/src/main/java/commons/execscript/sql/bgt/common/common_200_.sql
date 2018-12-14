Declare
--创建审核结果相关表的索引 
v_n Number(8):=0;
Begin
  Select Count(1) Into v_n  From user_indexes t_ind Where t_ind.index_name='IDX_BGT_T_CHECKRESULT';
  If v_n=1 Then
    execute Immediate 'Drop Index IDX_BGT_T_CHECKRESULT';
  End If;
 execute Immediate 'create index IDX_BGT_T_CHECKRESULT on P#BGT_T_CHECKRESULT (PROVINCE, YEAR, STATUS, TASKID, BUSICHECKID)';
  
  Select Count(1) Into v_n  From user_indexes t_ind Where t_ind.index_name='IDX_BGT_T_CHECKERROR';
  If v_n=1 Then
    execute Immediate 'Drop Index IDX_BGT_T_CHECKERROR';
  End If;
  
 execute Immediate 'create index IDX_BGT_T_CHECKERROR on P#BGT_T_CHECKERROR (PROVINCE, YEAR, STATUS, CHECKRESULTID)';
  Select Count(1) Into v_n  From user_indexes t_ind Where t_ind.index_name='IDX_BGT_T_CHECKCONDITION';
  If v_n=1 Then
     execute Immediate 'Drop Index IDX_BGT_T_CHECKCONDITION';
  End If;
  execute Immediate 'create index IDX_BGT_T_CHECKCONDITION on P#BGT_T_CHECKCONDITION (PROVINCE, YEAR, STATUS, CHECKRESULTID)';
End;
--创建审核结果相关表的索引
