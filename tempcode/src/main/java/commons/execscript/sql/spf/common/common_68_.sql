BEGIN
	--张铁柱
insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1U000', '系统默认错误', 'EB3B8ED99191719D7DCB2771C594A832', '系统默认错误', '系统默认错误', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1U001', '查询数据报错', 'EB3B8ED99191719D7DCB2771C594A833', '查询数据报错', '查询数据报错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1U002', '新增数据报错', 'EB3B8ED99191719D7DCB2771C594A834', '新增数据报错', '新增数据报错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1U003', '修改数据报错', 'EB3B8ED99191719D7DCB2771C594A835', '修改数据报错', '修改数据报错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1U004', '删除数据报错', 'EB3B8ED99191719D7DCB2771C594A836', '删除数据报错', '删除数据报错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1U005', '调用存储过程报错', 'EB3B8ED99191719D7DCB2771C594A837', '调用存储过程报错', '调用存储过程报错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1U101', '调用外部服务错误', 'EB3B8ED99191719D7DCB2771C594A838', '调用外部服务错误', '调用外部服务错误', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1U200', '一般性提示错误', 'EB3B8ED99191719D7DCB2771C594A839', '一般性提示错误', '一般性提示错误', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1U201', '数据校验错误', 'EB3B8ED99191719D7DCB2771C594A810', '数据校验错误', '数据校验错误', '1');

--刘凯、冯明华
insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H201', '保存数据发生错误', 'BCFD15EA05966F3B851F14BCE6DCE609', '保存数据发生错误', '保存数据发生错误', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H202', '列表数据封装发生异常', '7B5D8CB9140C4AEC8D83695518893DA3', '列表数据封装发生异常', '列表数据封装发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H203', '修改数据发生异常', '08D68E5692935FC2D676AB8FF77F7FF0', '修改数据发生异常', '修改数据发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H204', '批量复制数据发生异常', '153766085D7D660753498A42BE0FEF92', '批量复制数据发生异常', '批量复制数据发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H211', '获取财政用户树出错', '24C8139BD38E2B4B00181E52FE3F4DF5', '获取财政用户树出错', '获取财政用户树出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H212', '该表设置列属性时报错', 'ECF206C7A3A49D1DAC7AF4F1D15BA053', '该表设置列属性时报错', '该表设置列属性时报错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H213', '更新用户对项目类别表出错', '5D931A54108887DE13E9B7792E0C40FC', '更新用户对项目类别表出错', '更新用户对项目类别表出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H214', '查询用户对项目类别表出错', '6C8A4F5006007C154D311F1CF8C6572A', '查询用户对项目类别表出错', '查询用户对项目类别表出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H215', '新增用户对项目类别时出错', 'A0835F8E6A17DAD7E916D9702AEC4768', '新增用户对项目类别时出错', '新增用户对项目类别时出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H216', '获取部门用户树出错', 'D1113DC3C27A720A5DF6D537CAE0D666', '获取部门用户树出错', '获取部门用户树出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H217', '删除用户对项目类别表权限记录出错', 'AC076434F6E8564B9B6BF4F90455AB32', '删除用户对项目类别表权限记录出错', '删除用户对项目类别表权限记录出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H218', '获取用户对项目类别权限列表出错', 'C558FF0104949AFA8DF878D807C8BA47', '获取用户对项目类别权限列表出错', '获取用户对项目类别权限列表出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H219', '获取当前地区出错', '67EB1BACF7642F67F7C5E5DF44E6A0ED', '获取当前地区出错', '获取当前地区出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H21A', '获取当前处室或部门其他用户树出错', '6EA3AE6AE325B44A24F066CE27014AAC', '获取当前处室或部门其他用户树出错', '获取当前处室或部门其他用户树出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H21B', '保存批量复制操作出错', 'AA7A8D80D2731B84C2056F447EEBDE99', '保存批量复制操作出错', '保存批量复制操作出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H21C', '获取项目类别表信息异常', 'D7426B70492AD65FFE647011B884C00F', '获取项目类别表信息异常', '获取项目类别表信息异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1H21D', '获取设置表数据出现异常', 'B723B7EDBFB1D34A2D4347A9348E8C33', '获取设置表数据出现异常', '获取设置表数据出现异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I201', '引用代码表不存在', 'A34D2CD8E83A2B9FBE47512DA3D893A1', '引用代码表不存在', '引用代码表不存在', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I202', '获取平台角色接口发生异常', '4A0F659714DC1E2E156DD2D8F6CE5DA7', '获取平台角色接口发生异常', '获取平台角色接口发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I211', '初始化专项类别树出错', 'DC5855B8DC12FE3AD3A87C2F2A587EBC', '初始化专项类别树出错', '初始化专项类别树出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I212', '获取ORDERID出错', '9EC677D43668852E6434B81AC5B8416B', '获取ORDERID出错', '获取ORDERID出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I213', '获取区划出错', 'E2261649C3802CC6347CD69D14333264', '获取区划出错', '获取区划出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I214', '获取新增数据父级编码出错', 'E774EAE00D6CDE5F731D78F10795410E', '获取新增数据父级编码出错', '获取新增数据父级编码出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I215', '获取新增数据的编码出错', '06CBA0C86D3FC6A8A7506E335258F9A1', '获取新增数据的编码出错', '获取新增数据的编码出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I216', '修改父级节点的ISLEAF字段出错', '73D56B358313ACFDD5ACD6B3727A368E', '修改父级节点的ISLEAF字段出错', '修改父级节点的ISLEAF字段出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I217', '通过删除节点的DATAKEY获取其SUPERID出错', '11FF33653A73A2B85DD1227C0E7F62D6', '通过删除节点的DATAKEY获取其SUPERID出错', '通过删除节点的DATAKEY获取其SUPERID出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I218', '判断删除完成后该节点的父节点下是否还有子节点出错', 'EE26DF551E6FABB880F0E38150FEBBEC', '判断删除完成后该节点的父节点下是否还有子节点出错', '判断删除完成后该节点的父节点下是否还有子节点出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I219', '删除后无子节点,修改其父节点的ISLEAF字段出错', '0694FEB28375C095D56E22D1BD9591CA', '删除后无子节点,修改其父节点的ISLEAF字段出错', '删除后无子节点,修改其父节点的ISLEAF字段出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I21A', '获取当前地区节点总数出错', 'FF3E87343373B0230134C1F8EA248D91', '获取当前地区节点总数出错', '获取当前地区节点总数出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I21B', '查询模板数据出错', '24AE5D6602160A91F859346729B8293C', '查询模板数据出错', '查询模板数据出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1I21C', '插入模板根节点出错', '325ADF1DFEC3185D50DFA6D8569DA4CC', '插入模板根节点出错', '插入模板根节点出错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1P201', '获取当前地区发生异常', '4C6E25B4037FD96DB5C999E2EBE1B978', '获取当前地区发生异常', '获取当前地区发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1P202', '获取上级地区发生异常', 'C44154F7DDB67F2E43465908D1DB9E66', '获取上级地区发生异常', '获取上级地区发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1P203', '获取左侧树发生异常', '65C54706F3BCF431B7757022500F0892', '获取左侧树发生异常', '获取左侧树发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1P204', '删除原来的发布指南发生异常', 'A5B62ED3D8B9B1CBE78D7FE4BB69E4FD', '删除原来的发布指南发生异常', '删除原来的发布指南发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1P205', '插入新的发布指南记录发生异常', '8B396E6DA0946038AFF607AD37311087', '插入新的发布指南记录发生异常', '插入新的发布指南记录发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1P206', '修改发布指南记录发生异常', '4197F114EC187F2153DCA9B8940F489F', '修改发布指南记录发生异常', '修改发布指南记录发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1P207', '更新发布状态发生异常', 'BF87EB7E963CC7F65DEFEA93CEA9F48C', '更新发布状态发生异常', '更新发布状态发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1P208', '发布完成同步BAK表发生异常', '54CF2BEF132117507534BC2C609C6431', '发布完成同步BAK表发生异常', '发布完成同步BAK表发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1P209', '获取该专项的发布指南发生异常', '09747BD9D64E0560BCEA4CA2FAC5616C', '获取该专项的发布指南发生异常', '获取该专项的发布指南发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1P20A', '取消发布,将该专项发布状态掷为1时发生异常', 'F055C9FECD3AC19837719D951C60C4A4', '取消发布,将该专项发布状态掷为1时发生异常', '取消发布,将该专项发布状态掷为1时发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1P20B', '取消发布发布指南,将该专项发布指南状态掷为0发生异常', 'C2B0A121799D1D45A2BB04E154C96D43', '取消发布发布指南,将该专项发布指南状态掷为0发生异常', '取消发布发布指南,将该专项发布指南状态掷为0发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1P20C', '取消发布完成同步BAK表发生异常', 'C63A1BE62195064D69BC3A67953262BA', '取消发布完成同步BAK表发生异常', '取消发布完成同步BAK表发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1W201', '获取当前地区发生异常', '57845510C78A7627C0A9D113C6A66B92', '获取当前地区发生异常', '获取当前地区发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1W202', '获取上级地区发生异常', 'BE255A452154192ACABD979B1234A445', '获取上级地区发生异常', '获取上级地区发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1W203', '获取左侧树发生异常', '84A089140A2CF08024551B39044308F6', '获取左侧树发生异常', '获取左侧树发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1W204', '过滤含有倒挂项目ID的联合申报文的公文ID发生异常', 'E9257374F89C187374F672E1B9DAC282', '过滤含有倒挂项目ID的联合申报文的公文ID发生异常', '过滤含有倒挂项目ID的联合申报文的公文ID发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1W205', '删除TASKID为倒挂项目并且为联合申报文的中间表相关记录发生异常', '8568142A182B43DECEB421923A2A5CA6', '删除TASKID为倒挂项目并且为联合申报文的中间表相关记录发生异常', '删除TASKID为倒挂项目并且为联合申报文的中间表相关记录发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1W206', '向OA中间表中重新插入含所选公文和所选项目的记录发生异常', '3F0EB4C4BB962F33E5AB5D1A4773D1F4', '向OA中间表中重新插入含所选公文和所选项目的记录发生异常', '向OA中间表中重新插入含所选公文和所选项目的记录发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1W207', '修改倒挂操作后的项目所属SPFID和PROJTYPEID发生异常', 'CDB4E4FEB36E407E06C766EC092C9B47', '修改倒挂操作后的项目所属SPFID和PROJTYPEID发生异常', '修改倒挂操作后的项目所属SPFID和PROJTYPEID发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1W208', '同步BAK表时发生异常', 'C89DB526844C3EE608039C63E13184F0', '同步BAK表时发生异常', '同步BAK表时发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1W209', '方法弃用', 'F612E82FCB95AD1D7E700E81B57141F5', '方法弃用', '方法弃用', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1W20A', '获取该一级项目对应的公文个数出现异常', '547CA272061900707DE7101506F69381', '获取该一级项目对应的公文个数出现异常', '获取该一级项目对应的公文个数出现异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1W20B', '从DICT_T_PUBLIC表中获取倒挂所需的联合申报文业务类型的32位码出现异常', '6767405ED19EBCAF639CAFF7D45C6FA1', '从DICT_T_PUBLIC表中获取倒挂所需的联合申报文业务类型的32位码出现异常', '从DICT_T_PUBLIC表中获取倒挂所需的联合申报文业务类型的32位码出现异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1W20C', '获取公文表数据出现异常', 'A2D67387D7A59DDF0B4715268CFDC65A', '获取公文表数据出现异常', '获取公文表数据出现异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1W20D', '获取设置表数据异常', 'EB5F91E48BCDB6419DB9F337B7F2F2CB', '获取设置表数据异常', '获取设置表数据异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1X201', '处理类型对应表或视图不存在', '92925A19FC0F3153733DA76ABF7EF571', '处理类型对应表或视图不存在', '处理类型对应表或视图不存在', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1X202', '表定义封装时报错', '49042F0DEEC4BC75596D2302705E083A', '表定义封装时报错', '表定义封装时报错', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1X203', '获取列表数据时发生异常', 'F22C4B5D60544F1FEE24A7A65B095F0E', '获取列表数据时发生异常', '获取列表数据时发生异常', '1');

insert into DICT_T_EXCEPTION (CODE, DETAILMESSAGE, GUID, MESSAGE, REMARK, STATUS)
values ('ERR_1X204', '删除数据发生错误', '9A735142714BC980236E9B0626901D9C', '删除数据发生错误', '删除数据发生错误', '1');

END;--项目库错误码
