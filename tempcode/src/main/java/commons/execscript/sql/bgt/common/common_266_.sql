Declare
v_n Number(8):=0;
Begin
  Select Count(1) Into v_n  From user_indexes t_ind Where t_ind.index_name='IDX_BGT_T_BUSIRULETOROLE';
  If v_n=1 Then
    Execute Immediate 'Drop Index IDX_BGT_T_BUSIRULETOROLE';
  End If;
Execute Immediate 'create index IDX_BGT_T_BUSIRULETOROLE on P#BGT_T_BUSIRULETOROLE (PROVINCE, YEAR, STATUS, ROLEID£¬

BUSIRULEID)';
End;

--21´´½¨Ë÷ÒýIDX_BGT_T_BUSIRULETOROLE
