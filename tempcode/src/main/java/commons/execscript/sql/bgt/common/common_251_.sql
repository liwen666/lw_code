begin 
   DELETE FROM bd_t_handlebean where datakey in ('5044666BD4426B9EE0533A06A8C0E891','5044666BD4436B9EE0533A06A8C0E891','5044666BD4446B9EE0533A06A8C0E891','5044666BD4456B9EE0533A06A8C0E891');
   insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values
   (
        '5044666BD4426B9EE0533A06A8C0E891','23','autoBackupReportService','�ϱ��Զ�����','BAS','1' 
     );
   insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values(
    '5044666BD4436B9EE0533A06A8C0E891','23','autoBackupReportService','�ϱ��Զ�����','BGT','1' );
   insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values(
     '5044666BD4446B9EE0533A06A8C0E891','24','autoBackupIssuedService','�·��Զ�����','BAS','1' );
   insert into bd_t_handlebean(datakey,handletype,HANDLEBEANCODE,handlename,appid,status) values(
    '5044666BD4456B9EE0533A06A8C0E891','24','autoBackupIssuedService','�·��Զ�����','BGT','1' );
 end;
--����ϱ��·��Զ�����handler20170524_zz
