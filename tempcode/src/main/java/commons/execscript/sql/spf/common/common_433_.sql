BEGIN 

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C2AF3C9E050A8C0210562A1', 'P#KPI_T_BUSINESSCHECKDEF', '��Ч�������', '��Ч�������', null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C2AF3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_BUSINESSCHECKDEF' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C2BF3C9E050A8C0210562A1', 'P#KPI_T_KPITOCALIBER', 'ָ����ھ���Ӧ��¼��', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C2BF3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_KPITOCALIBER' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C2CF3C9E050A8C0210562A1', 'P#KPI_T_SETDEPTEVAL', '���ż�Ч����ָ������', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C2CF3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETDEPTEVAL' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C2DF3C9E050A8C0210562A1', 'P#KPI_T_SETDETAILCOLS', '¼�����ϸҳ������', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C2DF3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETDETAILCOLS' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C2EF3C9E050A8C0210562A1', 'P#KPI_T_SETFINEVAL', '��������ָ�����ñ�', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C2EF3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETFINEVAL' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C2FF3C9E050A8C0210562A1', 'P#KPI_T_SETINPUTDETAIL', '¼�����ϸҳ���п�����', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C2FF3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETINPUTDETAIL' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C30F3C9E050A8C0210562A1', 'P#KPI_T_SETINPUTRELA', '¼������ӹ�ϵ��', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C30F3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETINPUTRELA' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C31F3C9E050A8C0210562A1', 'P#KPI_T_SETINPUTSTEP', '�׶α�', null, null, 'KPI', '1', '0', '0', '0', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C31F3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETINPUTSTEP' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C32F3C9E050A8C0210562A1', 'P#KPI_T_SETINPUTTAB', '¼�������', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C32F3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETINPUTTAB' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C33F3C9E050A8C0210562A1', 'P#KPI_T_SETKPICALIBER', 'ָ��ھ�����', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C33F3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETKPICALIBER' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C35F3C9E050A8C0210562A1', 'P#KPI_T_SETPROJEVAL', '��Ŀ��Ч����ָ�����ñ�', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C35F3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETPROJEVAL' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C36F3C9E050A8C0210562A1', 'P#KPI_T_SETPROJKPI', '��Ŀ��Чָ�����ñ�', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C36F3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETPROJKPI' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C37F3C9E050A8C0210562A1', 'P#KPI_T_SETSPFEVAL', 'ר�Ч����ָ�����ñ�', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C37F3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETSPFEVAL' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C38F3C9E050A8C0210562A1', 'P#KPI_T_SETSPFKPI', 'ר�Чָ�����ñ�', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C38F3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETSPFKPI' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C39F3C9E050A8C0210562A1', 'P#KPI_T_SETTABCALIBER', '��Ч¼����ھ�����', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C39F3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETTABCALIBER' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '2C92F7242C6DF3C9E050A8C0210562A1', 'P#SPF_T_REPORTSECU', '��Ŀ����������Ȩ�ޱ�', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '2C92F7242C6DF3C9E050A8C0210562A1' AND STATUS = '1' AND DBTABLENAME = 'P#SPF_T_REPORTSECU' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '614EDCF3D7CD4FBBBE23E67BA8F0704B', 'P#KPI_T_SETDEPKPI', '���ż�Чָ��ģ���', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '614EDCF3D7CD4FBBBE23E67BA8F0704B' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETDEPKPI' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '614EDCF3D7CD4FBBBE23E67BA8F0705B', 'P#KPI_T_SETFNCKPI', '������Чָ��ģ���', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '614EDCF3D7CD4FBBBE23E67BA8F0705B' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETFNCKPI' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '614EDCF3D7CD4FBBBE23E67BA8F0714B', 'P#KPI_T_COLTOTABLES', '��Ч�����жԸ�������', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '614EDCF3D7CD4FBBBE23E67BA8F0714B' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_COLTOTABLES' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '614EDCF3D7CD4FCEDE23E67BA8F0714B', 'P#KPI_T_SETREPORTURL', '��Ч����URl����', null, null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '614EDCF3D7CD4FCEDE23E67BA8F0714B' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_SETREPORTURL' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '4309916406074568E0533906A8C0CD15', 'P#KPI_T_PROCESSMENU', '��Ч�׶ζԲ˵��м��', '��Ч����һ���׶�����ƽ̨�˵��м��', null, 'KPI', '1', '0', '0', '0', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '4309916406074568E0533906A8C0CD15' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_PROCESSMENU' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '4309916406074568E0533906A8C0CD25', 'P#KPI_T_AUDITROLE', '������˹�����ɫ��', '������˹�����ɫ���ӽ�ɫ���������', null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '4309916406074568E0533906A8C0CD25' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_AUDITROLE' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '4309916406074568E0533906A8C0CD35', 'P#KPI_T_STAGESTATUS', '��Ч�׶ζԲ˵��м��', '��Ч����һ���׶�����ƽ̨�˵��м��', null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '4309916406074568E0533906A8C0CD35' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_STAGESTATUS' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '4309916406074568E0533906A8C0CE25', 'P#KPI_T_CHECKERROR', '��Ч��˴�����Ϣ��', '��Ч������˴�����Ϣ��', null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '4309916406074568E0533906A8C0CE25' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_CHECKERROR' );

insert into dict_T_seTTINGTABINFO (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
select '4309916406074568E0533906A8C0CF35', 'P#KPI_T_CHECKRESULT', '��Ч��˽����', '��Ч������˽����', null, 'KPI', '1', '1', '0', '1', '1', '0', null
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM dict_T_seTTINGTABINFO WHERE GUID = '4309916406074568E0533906A8C0CF35' AND STATUS = '1' AND DBTABLENAME = 'P#KPI_T_CHECKRESULT' );

END;

--WJP_170425_��Ч���ñ�������Ϣ��ȫ