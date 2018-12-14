begin 
   DELETE FROM bd_t_handlebean where datakey in ('1F9B6E6DFE165122E0533A06A1C0AACC','4F9B6E6DFE165122E0533A06A8C0AACC');
   insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values
   (
        '1F9B6E6DFE165122E0533A06A1C0AACC','22','autoStartCFlowService','开启纵向流程','BAS','1' 
     );
     insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values(
      '4F9B6E6DFE165122E0533A06A8C0AACC','22','autoStartCFlowService','开启纵向流程','BGT','1' );
      
 end;
--添加自动开启横向流程handlebean_20170519_zz
