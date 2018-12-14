package commons.execscript.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.execscript.common.Constants;
import com.tjhq.commons.execscript.dao.IScriptMapper;
import com.tjhq.commons.execscript.po.DbTablePO;
import com.tjhq.commons.execscript.service.IScriptService;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.sql.dao.IJdbcDao;
import com.tjhq.commons.utils.IdGenerator;

@Service
@Transactional(readOnly = true)
public class ScriptService implements IScriptService {
	Logger logger = Logger.getLogger(ScriptService.class);
	@Resource
	private IScriptMapper scriptMapper;
	@Resource
	private IJdbcDao jdbcDAO;

	@Override
	public Table setTableDefine(String gridType) {
		Table table = new Table();

		table.setAppID("xxxAppID");
		table.setTableName("xxTableName");
		table.setTableID("xxxTableName");
		table.setTableDBName("xxxTableDBName");

		List<Column> columnList = new ArrayList<Column>();

		Column typeCol = new Column();
		typeCol.setColumnID("TYPEID");
		typeCol.setColumnName("TYPEID");
		typeCol.setColumnDBName("TYPEID");
		typeCol.setAlias("脚本类型");
		typeCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		typeCol.setShowType(ShowType.SHOW_TYPE_HTML);
		typeCol.setDataLength(32);
		typeCol.setKey(false);
		typeCol.setOrderID(1);
		typeCol.setReadOnly(true);
		typeCol.setVisible(true);
		typeCol.setWidth(80);
		columnList.add(typeCol);

		Column keyCol = new Column();
		keyCol.setColumnID("KEYID");
		keyCol.setColumnName("KEYID");
		keyCol.setColumnDBName("KEYID");
		keyCol.setAlias("脚本编号");
		keyCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		keyCol.setShowType(ShowType.SHOW_TYPE_HTML);
		keyCol.setDataLength(32);
		keyCol.setKey(false);
		keyCol.setOrderID(2);
		keyCol.setReadOnly(true);
		keyCol.setVisible(true);
		keyCol.setWidth(60);
		columnList.add(keyCol);

		Column nameCol = new Column();
		nameCol.setColumnID("KEYNAME");
		nameCol.setColumnName("KEYNAME");
		nameCol.setColumnDBName("KEYNAME");
		nameCol.setAlias("脚本名称");
		nameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		nameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		nameCol.setDataLength(300);
		nameCol.setKey(false);
		nameCol.setOrderID(3);
		nameCol.setReadOnly(true);
		nameCol.setVisible(true);
		nameCol.setWidth(280);
		columnList.add(nameCol);
		
		Column appidCol = new Column();
		appidCol.setColumnID("APPID");
		appidCol.setColumnName("APPID");
		appidCol.setColumnDBName("APPID");
		appidCol.setAlias("业务系统");
		appidCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		appidCol.setShowType(ShowType.SHOW_TYPE_HTML);
		appidCol.setDataLength(32);
		appidCol.setKey(false);
		appidCol.setOrderID(4);
		appidCol.setReadOnly(true);
		appidCol.setVisible(true);
		appidCol.setWidth(60);
		columnList.add(appidCol);

		/*
		 * if(Constants.SCRIPT_GRID.equals(gridType)){//是未执行脚本表
		 * 
		 * }else
		 */
		if (Constants.LOG_GRID.equals(gridType)) {// 是执行日志表
			Column guidCol = new Column();
			guidCol.setColumnID("GUID");
			guidCol.setColumnName("GUID");
			guidCol.setColumnDBName("GUID");
			guidCol.setAlias("GUID");
			guidCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
			guidCol.setShowType(ShowType.SHOW_TYPE_HTML);
			guidCol.setDataLength(32);
			guidCol.setKey(false);
			guidCol.setOrderID(10);
			guidCol.setReadOnly(true);
			guidCol.setVisible(false);
			guidCol.setWidth(32);
			columnList.add(guidCol);
			 
			Column userCol = new Column();
			userCol.setColumnID("USERLOGID");
			userCol.setColumnName("USERLOGID");
			userCol.setColumnDBName("USERLOGID");
			userCol.setAlias("执行人");
			userCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
			userCol.setShowType(ShowType.SHOW_TYPE_HTML);
			userCol.setDataLength(30);
			userCol.setKey(false);
			userCol.setOrderID(4);
			userCol.setReadOnly(true);
			userCol.setVisible(true);
			userCol.setWidth(80);
			columnList.add(userCol);

			Column resCol = new Column();
			resCol.setColumnID("RESULTFLAG");
			resCol.setColumnName("RESULTFLAG");
			resCol.setColumnDBName("RESULTFLAG");
			resCol.setAlias("执行结果");
			resCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
			resCol.setShowType(ShowType.SHOW_TYPE_HTML);
			resCol.setDataLength(80);
			resCol.setKey(false);
			resCol.setOrderID(5);
			resCol.setReadOnly(true);
			resCol.setVisible(true);
			resCol.setWidth(130);
			columnList.add(resCol);

			Column timeCol = new Column();
			timeCol.setColumnID("EXECTIME");
			timeCol.setColumnName("EXECTIME");
			timeCol.setColumnDBName("EXECTIME");
			timeCol.setAlias("执行时间");
			timeCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
			timeCol.setShowType(ShowType.SHOW_TYPE_HTML);
			timeCol.setDataLength(100);
			timeCol.setKey(false);
			timeCol.setOrderID(6);
			timeCol.setReadOnly(true);
			timeCol.setVisible(true);
			timeCol.setWidth(200);
			columnList.add(timeCol);

			Column batchCol = new Column();
			batchCol.setColumnID("BATCHCODE");
			batchCol.setColumnName("BATCHCODE");
			batchCol.setColumnDBName("BATCHCODE");
			batchCol.setAlias("执行批次");
			batchCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
			batchCol.setShowType(ShowType.SHOW_TYPE_HTML);
			batchCol.setDataLength(100);
			batchCol.setKey(false);
			batchCol.setOrderID(7);
			batchCol.setReadOnly(true);
			batchCol.setVisible(true);
			batchCol.setWidth(150);
			columnList.add(batchCol);

			Column remarkCol = new Column();
			remarkCol.setColumnID("REMARK");
			remarkCol.setColumnName("REMARK");
			remarkCol.setColumnDBName("REMARK");
			remarkCol.setAlias("备注");
			remarkCol.setDataType(JSTypeUtils.getJSDateType(DataType.BIGTEXT));
			remarkCol.setShowType(ShowType.SHOW_TYPE_TEXT_AREA);
			remarkCol.setDataLength(4000);
			remarkCol.setKey(false);
			remarkCol.setOrderID(8);
			remarkCol.setReadOnly(false);
			remarkCol.setVisible(true);
			remarkCol.setWidth(120);
			columnList.add(remarkCol);
		}

		table.setColumnList(columnList);
		return table;
	}

	@Override
	public List<Map<String, Object>> getScriptDataList(String appID, String province) throws Exception {
		//URL url = this.getClass().getResource("/");
		//String scriptFilePath = url.getPath() + Constants.SCRIPT_BASEPATH;//绝对路径
		
		//获取各文件夹已有的最大编号
		//Map<String,Integer> codeMap = this.getCodeMap(scriptFilePath, Constants.SCRIPT_DIVNAME, new HashMap<String, Integer>());
		//Map<String,Integer> codeMap = this.getMaxCodeMap();
		
		//获取未执行脚本
		//List<Map<String, Object>>  fileList = new ArrayList<Map<String,Object>>();
		//this.getFiles(scriptFilePath, Constants.SCRIPT_DIVNAME, codeMap, fileList);
		//this.getNotExecScript(scriptFilePath, Constants.SCRIPT_DIVNAME, codeMap, fileList);
		List<Map<String, Object>> fileList = this.getNotExecScript(appID, province);
		logger.info("--------------------获取未执行脚本完成------------------------");
		return fileList;
	}
	//读文件流
	private InputStream getFileInputStream(String fileName) {
		return ScriptService.class.getClassLoader().getResourceAsStream(fileName);
	}
	/*
	 * 获取已编号但未执行过的脚本
	 */
	private List<Map<String, Object>> getNotExecScript(String appID , String province) throws Exception {
		List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();
		//获取old脚本,APPID为'*'
		String scriptRecordFilePath = Constants.SCRIPT_BASEPATH + Constants.SCRIPT_OLDPATH + "/" + Constants.SCRIPTRECORD_FILENAME;
		this.getScript(fileList, scriptRecordFilePath , Constants.SCRIPT_COMPATH);
		
		//获取common脚本信息,APPID为'*'
		scriptRecordFilePath = Constants.SCRIPT_BASEPATH + Constants.SCRIPT_COMMONPATH + "/" + Constants.SCRIPTRECORD_FILENAME;
		this.getScript(fileList, scriptRecordFilePath , Constants.SCRIPT_COMPATH);

		//获取当前分区各个业务系统通用脚本,APPID为'*'
		scriptRecordFilePath = Constants.SCRIPT_BASEPATH + province + "/" + Constants.SCRIPTRECORD_FILENAME;
		this.getScript(fileList, scriptRecordFilePath , Constants.SCRIPT_COMPATH);
		
		//获取当前业务系统common脚本信息
		scriptRecordFilePath = Constants.SCRIPT_BASEPATH + appID+"/"+ Constants.SCRIPT_COMMONPATH + "/" + Constants.SCRIPTRECORD_FILENAME;
		this.getScript(fileList, scriptRecordFilePath , appID);

		//获取当前业务系统当前分区脚本信息
		scriptRecordFilePath = Constants.SCRIPT_BASEPATH + appID+"/"+  province+ "/" + Constants.SCRIPTRECORD_FILENAME;
		this.getScript(fileList, scriptRecordFilePath , appID);
		
		return fileList;
	}
	//从一个scriptRecord脚本记录文件中获取所有脚本信息
	private void getScript(List<Map<String, Object>> fileList, String scriptRecordFilePath, String appID) throws Exception {
		InputStream is = getFileInputStream(scriptRecordFilePath);
		if(is==null){
			return;
		}
		InputStreamReader isr=new InputStreamReader(is,"gbk");
		BufferedReader in = new BufferedReader(isr);

		String line = null;
		while ((line = in.readLine()) != null) {
			Map<String, Object> fileMap = new HashMap<String, Object>();
			if(line.trim().equals("")){//空行
				continue;
			}
			String[] names = line.split(Constants.SCRIPTSPLITFLAGSTRING);
			String fileName = names[0].trim();
			String scriptName = names[1].trim();
			// log表里没有的
			String[] fileNameParts = fileName.split("_");
			String typeId = fileNameParts[0];
			String keyId = fileNameParts[1];
			String thirdPartOfName = "";
			if(fileNameParts.length>=3){
				thirdPartOfName = fileNameParts[2];
			}
			//String keyName = fileName.substring(fileName.indexOf(keyId)+keyId.length()+1, fileName.lastIndexOf('.'));
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("typeId", typeId);
			param.put("keyId", keyId);
			param.put("appID", appID);
			Integer scCount = 0;
			if(Constants.SCRIPT_OLDHEAD.equals(typeId)){//旧脚本
				param.put("typeId", Constants.SCRIPT_OLDTYPEID);
				scCount = scriptMapper.getOldScriptCount(param);
			}
			scCount += scriptMapper.getScriptCount(param);
			if (scCount == 0) {
				fileMap.put("TYPEID", typeId);
				fileMap.put("KEYID", keyId);
				fileMap.put("KEYNAME", scriptName);
				fileMap.put("FILENAME", fileName);
				fileMap.put("APPID", appID);
				fileMap.put("THIRDPARTOFNAME", thirdPartOfName);
				fileList.add(fileMap);
			}
		}
		if(is !=null)is.close();
		if(isr !=null)isr.close();
		if(in!=null)in.close();
	}

	//读取脚本记录文件，获取各类脚本最大编号
	/*private Map<String, Integer> getMaxCodeMap() throws Exception {
		Map<String,Integer> codeMap = new HashMap<String, Integer>();
		
		InputStream commonIS = getFileInputStream(
				Constants.SCRIPT_BASEPATH + Constants.SCRIPT_COMMONPATH + "/" + Constants.SCRIPTRECORD_FILENAME);
		int commonMaxCode = this.getMaxCode(commonIS);
    	codeMap.put(Constants.SCRIPT_COMMONPATH , commonMaxCode);
    	
		String province = SecureUtil.getUserSelectProvince();
		InputStream provinceIS = getFileInputStream(Constants.SCRIPT_BASEPATH + province+ "/" + Constants.SCRIPTRECORD_FILENAME);
		int provinceMaxCode = this.getMaxCode(provinceIS);
    	codeMap.put(province, provinceMaxCode);
		
		return codeMap;
	}
	private int getMaxCode(InputStream is) throws Exception {
		int maxCode = 0;
		InputStreamReader isr=new InputStreamReader(is,"UTF-8");
		BufferedReader in = new BufferedReader(isr);

		String line = in.readLine();
		while (line != null) {
			maxCode = Integer.parseInt(line.split("_")[1]);
			line = in.readLine();
		}
		if(is !=null)is.close();
		if(isr !=null)isr.close();
		if(in!=null)in.close();
		return maxCode;
	}*/
	/*//获取最大编号
	private Map<String, Integer> getCodeMap(String scriptFilePath, String divName, Map<String,Integer> codeMap) {
        final File f = new File(scriptFilePath);  
        File[] files = null;
        if(Constants.SCRIPT_DIVNAME.equals(divName)){
        	files = f.listFiles();
        }else{
            files = f.listFiles(new FilenameFilter(){
    			@Override
    			public boolean accept(File dir, String name) {
    	            return name.startsWith(dir.getName()+"_");  
    			}
            });
        }
		for (File file : files) {
			if (file.isDirectory()) {
				if(Constants.SCRIPT_OLDDIVNAME.equals(file.getName())){//old文件夹不编号
					continue;
				}
				this.getCodeMap(file.getAbsolutePath(), file.getName(), codeMap);
			}else{
				String scriptCode = file.getName().split("_")[1];
				int maxCode = codeMap.get(divName)==null?0:codeMap.get(divName);
				if(maxCode==0 || maxCode<Integer.parseInt(scriptCode)){
		        	codeMap.put(divName, Integer.parseInt(scriptCode));
				}
			}
		}
		return codeMap;
	}
	
	 * 获取已编号但未执行过的脚本
	 
	private void getNotExecScript(String filePath, String divName, Map<String, Integer> codeMap, List<Map<String, Object>> fileList) throws Exception {
		File[] files = this.orderByDate(filePath);
		for (File file : files) {
			if (file.isDirectory()) {
				getNotExecScript(file.getAbsolutePath(), file.getName(), codeMap, fileList);
			} else {
				String fileName = file.getName();
				String province = SecureUtil.getUserSelectProvince();
				if(!Constants.SCRIPT_COMMONPATH.equals(divName) 
						&& !Constants.SCRIPT_OLDDIVNAME.equals(divName)
						&& !province.equals(divName)){//不是common、不是old、也不是当前分区的脚本不显示
					continue;
				}
				Map<String, Object> fileMap = new HashMap<String, Object>();
				try {
					// 已编号但未执行过的
					if(Constants.SCRIPT_OLDDIVNAME.equals(divName)){//old脚本
						String keyId = fileName.split("_")[1];
						String keyName = fileName.substring(fileName.indexOf(keyId)+keyId.length()+1, fileName.lastIndexOf('.'));
						Map<String, String> param = new HashMap<String, String>();
						param.put("typeId", "DBUpdate");
						param.put("keyId", keyId);
						Integer scCount = scriptMapper.getOldScriptCount(param);
						if (scCount == 0) {
							fileMap.put("TYPEID", "DBUpdate");
							fileMap.put("KEYID", keyId);
							fileMap.put("KEYNAME", keyName);
							fileList.add(fileMap);
						}
					}else if (fileName.startsWith(divName + "_")) {// 已编号的新脚本
						// log表里没有的
						String typeId = divName;
						String keyId = fileName.split("_")[1];
						String keyName = fileName.substring(fileName.indexOf(keyId)+keyId.length()+1, fileName.lastIndexOf('.'));
						
						Map<String, String> param = new HashMap<String, String>();
						param.put("typeId", typeId);
						param.put("keyId", keyId);
						Integer scCount = scriptMapper.getScriptCount(param);
						if (scCount == 0) {
							fileMap.put("TYPEID", typeId);
							fileMap.put("KEYID", keyId);
							fileMap.put("KEYNAME", keyName);
							fileList.add(fileMap);
						}
					} 
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
	}*/
	/*
	 * 1.记录文件夹名称作为脚本名称第一部分 
	 * 2.遍历文件夹下所有文件，以时间排序
	 * 3.找到未编号的文件（不是以divName_开头），进行编号，和编辑脚本内容 
	 * 4.重新生成脚本文件
	 * 5.并上已编号，但未执行过的脚本
	 */
	/*private void getFiles(String filePath, String divName, Map<String, Integer> codeMap, List<Map<String, Object>> fileList) throws Exception {
		File[] files = this.orderByDate(filePath);
		for (File file : files) {
			if (file.isDirectory()) {
				getFiles(file.getAbsolutePath(), file.getName(), codeMap, fileList);
			} else {
				String fileName = file.getName();
				String province = SecureUtil.getUserSelectProvince();
				if(!Constants.SCRIPT_COMMONPATH.equals(divName) && !province.equals(divName)){//不是common也不是当前分区的脚本不显示
					continue;
				}
				Map<String, Object> fileMap = new HashMap<String, Object>();
				try {
					// 已编号但未执行过的
					if (fileName.startsWith(divName + "_")) {// 已编号的
						// log表里没有的
						String typeId = divName;
						String keyId = fileName.split("_")[1];
						String keyName = fileName.substring(fileName.indexOf(keyId)+keyId.length()+1, fileName.lastIndexOf('.'));
						
						Map<String, String> param = new HashMap<String, String>();
						param.put("typeId", typeId);
						param.put("keyId", keyId);
						Integer scCount = scriptMapper.getScriptCount(param);
						if (scCount == 0) {
							fileMap.put("TYPEID", typeId);
							fileMap.put("KEYID", keyId);
							fileMap.put("KEYNAME", keyName);
							fileList.add(fileMap);
						}
					} else {// 并上未编号的
						int maxCode = codeMap.get(divName) == null ? 1 : codeMap.get(divName) + 1;
						codeMap.put(divName, maxCode);
						// 在文件末尾追加内容
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
						String typename = sdf.format(new Date());

						String dbFileName = fileName.substring(0, fileName.lastIndexOf('.'));
						String scriptEnd = this.getScriptEnd(divName, Integer.toString(maxCode), typename, dbFileName);
						this.readFile(file);
						this.writeFile(file, scriptEnd, true);
						// 编号并重命名
						file.renameTo(new File(filePath + "\\" + divName + "_" + maxCode + "_" + fileName));
						fileMap.put("TYPEID", divName);
						fileMap.put("KEYID", maxCode);
						fileMap.put("KEYNAME", dbFileName);
						fileList.add(fileMap);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
	}*/
	//读脚本文件
	private String readFile(String scriptFilePath) throws Exception {
		InputStream is = getFileInputStream(scriptFilePath);
		InputStreamReader isr=new InputStreamReader(is,"gbk");
		BufferedReader in = new BufferedReader(isr);
		
		StringBuffer  scriptBf = new StringBuffer();
		String line = null;
		while ((line = in.readLine()) != null) {
			logger.info("--脚本----------"+line);
			scriptBf.append(line).append("\n");
		}
		if(is !=null)is.close();
		if(isr !=null)isr.close();
		if(in!=null)in.close();
		return scriptBf.toString();
	}
	/**
	 * 写文件
	 * @param file
	 * @param script 写入的脚本
	 * @param isAppend 追加还是覆盖重写
	 * @throws Exception
	 */
	/*private void writeFile(File file , String script , boolean isAppend) throws Exception{
		BufferedWriter out = new BufferedWriter(new FileWriter(file, isAppend));
		out.write(script);
		out.newLine();
		out.flush();
		out.close();
	}*/
	// 按最后编辑日期排序，从小到大。
	/*private File[] orderByDate(String fliePath) {
		File file = new File(fliePath);
		File[] fs = file.listFiles();
		if(fs==null){
			return new File[0];
		}
		Arrays.sort(fs, new Comparator<File>() {
			public int compare(File f1, File f2) {
				long diff = f1.lastModified() - f2.lastModified();
				if (diff > 0)
					return 1;
				else if (diff == 0)
					return 0;
				else
					return -1;
			}
			public boolean equals(Object obj) {
				return true;
			}
		});
		return fs;
	}*/

	@Override
	public List<Map<String, Object>> getLogDataList(Map<String, Object> param) {
		@SuppressWarnings("unchecked")
		Map<String, String> sqlWhereMap = (Map<String, String>)param.get("sqlWhere");
		StringBuffer sqlWhere = new StringBuffer();
		if(sqlWhereMap!=null && sqlWhereMap.size()>0){
			for (Map.Entry<String, String> entry : sqlWhereMap.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if(key!=null && !key.trim().equals("") && value!=null && !value.trim().equals("")){
					if("keyName".equals(key)){
						sqlWhere.append(" and "+key+" like "+ "'%"+value+ "%' ");
					}else{
						sqlWhere.append(" and "+key+" = "+ "'"+value+ "' ");
					}
				}
			}
			sqlWhereMap.put("sqlWhere", sqlWhere.toString());
		}else if(sqlWhereMap==null){
			sqlWhereMap = new HashMap<String, String>();
		}
		if(param.get("appID")!=null){
			sqlWhereMap.put("appID", param.get("appID").toString().toLowerCase());
		}
		return scriptMapper.getLogDataList(sqlWhereMap);
	}
	@Override
	public List<String> getBatchCodeList(Map<String, String> param) {
		return scriptMapper.getBatchCodeList(param);
	}
	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class) 
	public void execScript(Map<String, String> param) throws Exception {
		String typeId = param.get("typeId");
		String keyId = param.get("keyId");
		String userLogId = param.get("userLogId");
		String batchcode = param.get("batchcode");
		String typename = param.get("typename");
		String appID = param.get("appID");
		String province = param.get("province");
		
		/*URL url = this.getClass().getResource("/");
		String scriptFilePath = url.getPath() + Constants.SCRIPT_BASEPATH;//绝对路径
		Map<String, String> fileMap = new HashMap<String, String>(); 
		this.getExecScript(scriptFilePath, typeId ,keyId, fileMap);*/
		Map<String, String> fileMap = this.getExecScript(typeId ,keyId,appID);
		if(!fileMap.isEmpty()){
			/* 
			 * 1.读取脚本
			 * 2.插入log表
			 * 3.在数据库中从log表中查询脚本并执行。
			 * 4.更新log表执行结果
			 */
			String scriptContent = fileMap.get("content");
			logger.info(scriptContent);
			String logid = IdGenerator.get32UUID();
			fileMap.put("logid", logid);
			fileMap.put("userlogid", userLogId);
			fileMap.put("batchcode", batchcode);

			DbTablePO dbTablePO = new DbTablePO();
			dbTablePO.setLogid(logid);
			dbTablePO.setUserlogid(userLogId);
			dbTablePO.setBatchcode(batchcode);
			dbTablePO.setContent(scriptContent);
			dbTablePO.setTypeId(fileMap.get("typeId"));
			dbTablePO.setKeyId(keyId);
			dbTablePO.setKeyName(fileMap.get("keyName"));
			dbTablePO.setAppID(appID);
			
			scriptMapper.insertLogTable(dbTablePO);
			
			try {
				if(province!=null && (province.equals("87")||province.equals("8700")) && "DF".equals(fileMap.get("thirdPartOfName"))){
					//杜崇明_如果脚本是DF开头，并且分区是87或8700，此脚本不执行
					fileMap.put("remark", "不需执行");
					fileMap.put("resultflag", "3");//'执行结果：0执行失败；1执行成功但有编译错误；2执行成功；3不需执行。'
					scriptMapper.updateLogTable(fileMap);
					return ;
				}
				if(Constants.SCRIPT_EXECTYPE.equals(fileMap.get("thirdPartOfName"))){//用jdbcDAO直接执行sql
					jdbcDAO.excute(scriptContent);
				}else{//在数据库中，以读取clob方式执行sql
					scriptMapper.executeScript(logid);
				}
				//插入脚本记录表相关脚本信息
				scriptMapper.execSql(
						getScriptEnd(fileMap.get("typeId"), fileMap.get("keyId"), typename, fileMap.get("keyName"),fileMap.get("appId"))
				);
				
				fileMap.put("remark", "执行成功");
				fileMap.put("resultflag", "2");//'执行结果：0执行失败；1执行成功但有编译错误；2执行成功。'
				scriptMapper.updateLogTable(fileMap);
			} catch (Exception e) {
				e.printStackTrace();
				String remark;
				String resultflag = "0";
				String cause;
				try {
					cause = e.getCause().getMessage();
					remark = e.getMessage();
					
					resultflag = "0";
					if(cause!=null && cause.startsWith("ORA-24344")){//ORA-24344: 成功, 但出现编译错误
						resultflag = "1";
						//插入脚本记录表相关脚本信息
						scriptMapper.execSql(
								getScriptEnd(fileMap.get("typeId"), fileMap.get("keyId"), typename, fileMap.get("keyName"),fileMap.get("appId"))
						);
					}
					int subLength = remark.length();
					if(subLength>1000){
						remark = remark.substring(0, 1000);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					remark = e1.getCause().getMessage();
				}
				try {
					fileMap.put("remark",remark);
					fileMap.put("resultflag", resultflag);//'执行结果：0执行失败；1执行成功但有编译错误；2执行成功。'
					scriptMapper.updateLogTable(fileMap);
				} catch (Exception e1) {
					e1.printStackTrace();
		            //编译失效对象
		            scriptMapper.compileInvalidObjects();
					throw new RuntimeException("更新log表’执行失败‘结果失败："+e1.getCause());
				}
			}
	        //编译失效对象
			scriptMapper.compileInvalidObjects();
		}else{
			throw new Exception("找不到脚本！");
		}
	}
	@Override
	public void updateLogTableInvalidObjects() {
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("remark", "执行成功");
		fileMap.put("resultflag", "2");//'执行结果：0执行失败；1执行成功但有编译错误；2执行成功。'
		scriptMapper.updateLogTableInvalidObjects(fileMap);
	}
	//获取脚本追加的编辑内容
	/*private String getScriptEnd(String typeid, String keyid, String typename, String keyname){
		StringBuffer scEnd = new StringBuffer();
		scEnd.append("\n");
		scEnd.append("delete from "+Constants.SCRIPT_TABLENAME+" where typeid = '"+typeid+"' and keyid = '"+keyid+"';\n");
		//scEnd.append("commit;\n");
		scEnd.append("insert into "+Constants.SCRIPT_TABLENAME+" (typeid, keyid, typename, keyname)\n");
		scEnd.append("values ('"+typeid+"','"+keyid+"','"+typename+"','"+keyname+"');\n");
		scEnd.append("\n");
		return scEnd.toString(); 
	}*/
	//获取脚本追加的编辑内容
	private String getScriptEnd(String typeid, String keyid, String typename, String keyname, String appid){
		StringBuffer scEnd = new StringBuffer();
		scEnd.append("delete from "+Constants.SCRIPT_TABLENAME+" where typeid = '"+typeid+"' and keyid = '"+keyid+"' and appid = '"+appid+"';\n");
		scEnd.append("insert into "+Constants.SCRIPT_TABLENAME+" (typeid, keyid, typename, keyname, appid)\n");
		scEnd.append("values ('"+typeid+"','"+keyid+"','"+typename+"','"+keyname+"','"+appid+"');\n");
		return scEnd.toString(); 
	}
	//从文件中读取要执行的脚本
	private Map<String, String> getExecScript(String typeId, String keyId,String appID) throws Exception {
		String dirpath = "";
		if(Constants.SCRIPT_COMPATH.equals(appID)){
			if(Constants.SCRIPT_OLDHEAD.equals(typeId)){
				dirpath = Constants.SCRIPT_OLDPATH + "/" ;
			}else{
				dirpath = typeId+ "/" ;
			}
		}else{
			dirpath = appID + "/" +typeId + "/";
		}
		String scriptRecordFilePath = Constants.SCRIPT_BASEPATH + dirpath + Constants.SCRIPTRECORD_FILENAME;
		List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();
		Map<String, String> resMap = new HashMap<String, String>();
		this.getScript(fileList, scriptRecordFilePath, appID);//在脚本记录文件中，并且log表里没有的，也就是没有执行过的
		for (Map<String, Object> fileMap : fileList) {
			String fileTypeId = (String)fileMap.get("TYPEID");
			String fileKeyId = (String)fileMap.get("KEYID");
			
			if (typeId.equals(fileTypeId) && keyId.equals(fileKeyId)) {//找到要执行的脚本
				String fileName = (String)fileMap.get("FILENAME");
				String keyName = (String)fileMap.get("KEYNAME");
				String thirdPartOfName = (String)fileMap.get("THIRDPARTOFNAME");//是否为jdbc
				String scriptFilePath = Constants.SCRIPT_BASEPATH + dirpath + fileName;
				String scriptContent = readFile(scriptFilePath);
				resMap.put("keyName", keyName);
				resMap.put("typeId", typeId);
				if(Constants.SCRIPT_OLDHEAD.equals(typeId)){//旧脚本
					resMap.put("typeId", Constants.SCRIPT_OLDTYPEID);
				}
				resMap.put("keyId", keyId);
				resMap.put("appId", appID);
				resMap.put("thirdPartOfName", thirdPartOfName);
				resMap.put("content", scriptContent);
			} 
		}
		return resMap;
	}

	/*//从文件中读取要执行的脚本
	private void getExecScript(String filePath, String typeId, String keyId, Map<String, String> fileMap) throws Exception {
		File fileroot = new File(filePath);
		File[] files = fileroot.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				this.getExecScript(file.getAbsolutePath(), typeId, keyId, fileMap);
			} else {
				try {
					String fileName = file.getName();
					String[] fileNames = file.getName().split("_");
					String fileTypeId = fileNames[0];
					if(Constants.SCRIPT_OLDHEAD.equals(fileTypeId)){
						fileTypeId = Constants.SCRIPT_OLDTYPEID;
					}
					String fileKeyId = fileNames[1];
					String keyName = fileName.substring(fileName.indexOf(fileKeyId)+fileKeyId.length()+1, fileName.lastIndexOf('.'));
					fileMap.put("keyName", keyName);
					if (typeId.equals(fileTypeId) && keyId.equals(fileKeyId)) {//找到要执行的脚本
						String scriptContent = readFile(file);
						fileMap.put("typeId", typeId);
						fileMap.put("keyId", keyId);
						fileMap.put("content", scriptContent);
						return;
					} 
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
	}*/
	@Override
	public void compileInvalidObjects() {
		scriptMapper.compileInvalidObjects();
	}

	@Override
	public Map<String, String> getScriptMap(Map<String, String> param) {
		return scriptMapper.getScriptMap(param);
	}
	@Override
	public void initDbScriptObject() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("objectName", Constants.SCRIPT_LOGTABNAME);
		param.put("objectType", "TABLE");
		Integer objCount = scriptMapper.getUserObjectsCount(param);
		if(objCount==0){//没有EFM_T_DBUPDATE_LOG表
			this.createLogTable();
		}
		
		param.put("objectName", "EXEC_SCRIPT");
		param.put("objectType", "PROCEDURE");
		objCount = scriptMapper.getUserObjectsCount(param);
		if(objCount==0){//没有EXEC_SCRIPT存储过程
			this.createExecScriptProcedure();
		}

		param.put("tableName", Constants.SCRIPT_TABLENAME);
		param.put("columnName", Constants.SCRIPT_APPIDCOLNAME);
		objCount = scriptMapper.getUserTabColumnsCount(param);
		if(objCount==0){//脚本表没有appid列
			this.alterTabCol(Constants.SCRIPT_TABLENAME);
		}

		param.put("tableName", Constants.SCRIPT_LOGTABNAME);
		param.put("columnName", Constants.SCRIPT_APPIDCOLNAME);
		objCount = scriptMapper.getUserTabColumnsCount(param);
		if(objCount==0){//日志表没有appid列
			this.alterTabCol(Constants.SCRIPT_LOGTABNAME);
		}
		//检查log表remark列的长度，如果不够4000则改为4000
		this.checkLogRemarkLength();
	}

	private void checkLogRemarkLength() {
		StringBuffer sql = new StringBuffer();
		sql.append(" declare\n");
		sql.append(" n_length NUMBER(10);\n");
		sql.append(" begin\n");
		sql.append(" select data_length\n");
		sql.append(" into n_length\n");
		sql.append(" from user_tab_columns\n");
		sql.append(" where table_name = 'EFM_T_DBUPDATE_LOG'\n");
		sql.append(" and column_name = 'REMARK';\n");
		sql.append(" if n_length < 4000 then\n");
		sql.append(" execute immediate 'alter table EFM_T_DBUPDATE_LOG modify remark VARCHAR2(4000)';\n");
		sql.append(" end if;\n");
		sql.append(" end;\n");
		String resSql = sql.toString().replaceAll("'", "''");
		logger.info(resSql);
		scriptMapper.initScript(resSql);
	}

	/*
	 * 将脚本表或脚本日志表加appid列
	 */
	private void alterTabCol(String tableName) {
		scriptMapper.execSql("execute immediate 'alter table "+tableName+" add appid VARCHAR2(32) default ''*''';");
	}
	
	/*
	 * 创建EFM_T_DBUPDATE_LOG表
	 */
	private void createLogTable() {
		StringBuffer sql = new StringBuffer();
		String resSql = "";
		//创建表
		sql.append(" create table efm_t_dbupdate_log\n");
		sql.append(" (\n");
		sql.append(" guid       varchar2(32) default sys_guid() not null,\n");
		sql.append(" typeid     varchar2(32) not null,\n");
		sql.append(" keyid      varchar2(32) not null,\n");
		sql.append(" keyname	 varchar2(100),\n");
		sql.append(" content    clob,\n");
		sql.append(" userlogid  varchar2(32),\n");
		sql.append(" resultflag char(1),\n");
		sql.append(" batchcode  varchar2(32),\n");
		sql.append(" exectime   timestamp default SYSTIMESTAMP,\n");
		sql.append(" remark     varchar2(4000)\n");
		sql.append(" )\n");
		logger.info(sql.toString());
		scriptMapper.initScript(sql.toString());
		
		//加注释comment
		resSql =("comment on table efm_t_dbupdate_log is '脚本执行日志表'").replaceAll("'", "''");
		scriptMapper.initScript(resSql);
		resSql =("comment on column efm_t_dbupdate_log.typeid  is '脚本类型id'").replaceAll("'", "''");
		scriptMapper.initScript(resSql);
		resSql =("comment on column efm_t_dbupdate_log.keyid  is '脚本编号'").replaceAll("'", "''");
		scriptMapper.initScript(resSql);
		resSql =("comment on column efm_t_dbupdate_log.keyname  is '脚本名称'").replaceAll("'", "''");
		scriptMapper.initScript(resSql);
		resSql =("comment on column efm_t_dbupdate_log.content  is '脚本内容'").replaceAll("'", "''");
		scriptMapper.initScript(resSql);
		resSql =("comment on column efm_t_dbupdate_log.userlogid  is '脚本执行人编码'").replaceAll("'", "''");
		scriptMapper.initScript(resSql);
		resSql =("comment on column efm_t_dbupdate_log.resultflag  is '执行结果：0执行失败；1执行成功但又编译错误；2执行成功。'").replaceAll("'", "''");
		scriptMapper.initScript(resSql);
		resSql =("comment on column efm_t_dbupdate_log.batchcode  is '批次编码：年月日_时分秒_毫秒'").replaceAll("'", "''");
		scriptMapper.initScript(resSql);
		resSql =("comment on column efm_t_dbupdate_log.exectime  is '执行时间'").replaceAll("'", "''");
		scriptMapper.initScript(resSql);
		resSql =("comment on column efm_t_dbupdate_log.remark  is '备用'").replaceAll("'", "''");
		scriptMapper.initScript(resSql);
		
		//加主键
		resSql =("alter table efm_t_dbupdate_log  add constraint PK_efm_t_dbupdate_log primary key (GUID)").replaceAll("'", "''");
		scriptMapper.initScript(resSql);
		logger.info("-------------------------------创建EFM_T_DBUPDATE_LOG表完成-------------------------------------------------");
	}
	/*
	 * 创建EXEC_SCRIPT存储过程
	 */
	private void createExecScriptProcedure() {
		StringBuffer sql = new StringBuffer();
		sql.append("\n");
		sql.append(" CREATE OR REPLACE PROCEDURE EXEC_SCRIPT(P_ID VARCHAR2) IS\n");
		sql.append(" V_CLOB CLOB;\n");
		sql.append(" BEGIN\n");
		sql.append("  EXECUTE IMMEDIATE 'SELECT CONTENT FROM EFM_T_DBUPDATE_LOG WHERE GUID = :1' INTO V_CLOB USING P_ID;\n");
		sql.append("  BEGIN\n");
		sql.append("    EXECUTE IMMEDIATE V_CLOB;\n");
		sql.append("  EXCEPTION WHEN OTHERS THEN\n");
		sql.append("     DBMS_OUTPUT.PUT_LINE(DBMS_LOB.SUBSTR(V_CLOB,4000));\n");
		sql.append("     RAISE;\n");
		sql.append("   END;\n");
		sql.append(" END EXEC_SCRIPT;\n");
		String resSql = sql.toString();
		resSql = resSql.replaceAll("'", "''");
		logger.info(resSql);
		scriptMapper.initScript(resSql);
		logger.info("-------------------------------创建EXEC_SCRIPT存储过程完成-------------------------------------------------");
	}
	
	/***
	 * 根据xml获取已编号但未执行过的脚本
	 * @param appID
	 * @param province
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getNotExecScriptFormXml(String appID , String province) throws Exception {
		String[] versions=readPropertiesInfo();
		String path=Constants.SCRIPT_BASEPATH+"ScriptVersionData.xml";
		Document document = null;  		
        document = parse2Document(path); 
        List<String> vlist=this.getAllVersionInfo(document);
   
		List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();
		//获取old脚本,APPID为'*'
		String scriptRecordFilePath = Constants.SCRIPT_BASEPATH + Constants.SCRIPT_OLDPATH + "/" + Constants.SCRIPTRECORD_FILENAME;
		this.getScriptFromXml(fileList, scriptRecordFilePath , Constants.SCRIPT_COMPATH,versions[1],document,Constants.SCRIPT_OLDPATH,vlist,"common");
		
		//获取common脚本信息,APPID为'*'
		scriptRecordFilePath = Constants.SCRIPT_BASEPATH + Constants.SCRIPT_COMMONPATH + "/" + Constants.SCRIPTRECORD_FILENAME;
		this.getScriptFromXml(fileList, scriptRecordFilePath , Constants.SCRIPT_COMPATH,versions[1],document,Constants.SCRIPT_COMMONPATH,vlist,"common");

		//获取当前分区各个业务系统通用脚本,APPID为'*'
		scriptRecordFilePath = Constants.SCRIPT_BASEPATH + province + "/" + Constants.SCRIPTRECORD_FILENAME;
		this.getScriptFromXml(fileList, scriptRecordFilePath , Constants.SCRIPT_COMPATH,versions[1],document,province,vlist,"common");
		
		//获取当前业务系统common脚本信息
		scriptRecordFilePath = Constants.SCRIPT_BASEPATH + appID+"/"+ Constants.SCRIPT_COMMONPATH + "/" + Constants.SCRIPTRECORD_FILENAME;
		this.getScriptFromXml(fileList, scriptRecordFilePath , appID,versions[0],document,appID+"."+Constants.SCRIPT_COMMONPATH,vlist,"app");

		//获取当前业务系统当前分区脚本信息
		scriptRecordFilePath = Constants.SCRIPT_BASEPATH + appID+"/"+  province+ "/" + Constants.SCRIPTRECORD_FILENAME;
		this.getScriptFromXml(fileList, scriptRecordFilePath , appID,versions[0],document,appID+"."+province,vlist,"app");
		return fileList;
	}

	/***
	 * 从一个scriptRecord脚本记录文件中获取所有脚本信息
	 * @param fileList  返回的文件列表
	 * @param scriptRecordFilePath txt文本路径
	 * @param appID  appID
	 * @param sysVersion  系统版本
	 * @param document   xml对象
	 * @param pakName   xml中对应的包名packagename
	 * @param vlist     xml中所有的版本信息列表
	 * @throws Exception
	 */
	private void getScriptFromXml(List<Map<String, Object>> fileList, String scriptRecordFilePath, String appID,String sysVersion,Document document,String pakName,List<String> vlist,String apptype) throws Exception {		
		InputStream is = getFileInputStream(scriptRecordFilePath);
		if(is==null){
			return;
		}
		InputStreamReader isr=new InputStreamReader(is,"gbk");
		BufferedReader in = new BufferedReader(isr);
		
		String maxdbVersion="V2.0.0.0";
		boolean exexqueryflag=true;//只有读第一条文本时查询数据库信息	
		Map<String,Integer> valMap=new HashMap<String,Integer>();
		
		Map<String,String> vervalMap=new HashMap<String,String>();	
		//取出所有版本的的当前包下数据记录 如：10_20
		for(String versions:vlist){
			String[] ves=versions.split("_");
			if(ves[0].equals(apptype)){
				String rsval=this.getLastRecordinterval(ves[1], pakName, document,apptype);
				vervalMap.put(ves[1], rsval);
			}
		}
		
		String line = null;
		String[] names = null;
		String fileName = "";
		String[] fileNameParts = null;
		String typeId = "";
		String keyId = "";	
		while ((line = in.readLine()) != null) {
			Map<String, Object> fileMap = new HashMap<String, Object>();
			if(line.trim().equals("")){//空行
				continue;
			}	
			 names = line.split(Constants.SCRIPTSPLITFLAGSTRING);
			 fileName = names[0].trim();
			 fileNameParts = fileName.split("_");
			 String scriptName = names[1].trim();
			 typeId = fileNameParts[0];
			 keyId = fileNameParts[1];
			 String thirdPartOfName = "";
			 if(fileNameParts.length>=3){
				thirdPartOfName = fileNameParts[2];
			 }
			 if(exexqueryflag){
					//查询参数				
					Map<String, String> param = new HashMap<String, String>();
					param.put("typeId", typeId);
					param.put("appID", appID);
					if(Constants.SCRIPT_OLDHEAD.equals(typeId)){//旧脚本
						param.put("typeId", Constants.SCRIPT_OLDTYPEID);
					}
					List<Map<String, Object>> list=scriptMapper.getAllExecScriptMap(param);
					for(Map<String, Object> map:list){
						String strkeyId=(String)map.get("KEYID");
						String strcounts=map.get("COUNTS").toString();
						valMap.put(strkeyId, Integer.parseInt(strcounts));//将查询出来的值放到valMap中
					}	
					param.put("apptype", apptype+"_");
					List<String> listAll=scriptMapper.getAllVersionList(param);  
					maxdbVersion=getMaxVersion(listAll,apptype);
					exexqueryflag=false;
				}
			 
			 for(String currVersionstr:vlist){//循环版本列表
				 String[] currVersions=currVersionstr.split("_");
				 if(currVersions[0].equals(apptype)){//当前的业务系统的版本
					 String sysVersionFlag=compileVersion(currVersions[1], sysVersion);
					 String dbVersionFlag=compileVersion(currVersions[1],maxdbVersion);//和数据库最大版本号对比
					 if("less".equals(dbVersionFlag)||"equal".equals(dbVersionFlag)){
						//如果小于当前版本，取xml中登记的未执行数据（log表中不存在的数据）
						 String t_v=vervalMap.get(currVersions[1]);
						 String[] t_vs=t_v.split("_");
						 if(valMap.get(keyId)==null){//log中不存在
								//编号在xml中当前版本的记录区间内
								if((Integer.parseInt(keyId)>=Integer.parseInt(t_vs[0]))&&(Integer.parseInt(keyId)<=Integer.parseInt(t_vs[1]))){
									System.out.println(keyId+"--"+pakName+"=============小于系统版本中增加"+maxdbVersion);
									fileMap.put("TYPEID", typeId);
									fileMap.put("KEYID", keyId);
									fileMap.put("KEYNAME", scriptName);
									fileMap.put("FILENAME", fileName);
									fileMap.put("APPID", appID);
									fileMap.put("THIRDPARTOFNAME", thirdPartOfName);
									fileList.add(fileMap);
								}
							} 
					 }else if("greater".equals(dbVersionFlag)&&("less".equals(sysVersionFlag)||"equal".equals(sysVersionFlag))){
						//如果 系统版本>=xm>数据库版本，取xml中登记的全部数据
							String t_v=vervalMap.get(currVersions[1]);
							String[] t_vs=t_v.split("_");					
							//编号在xml中当前版本的记录区间内
							if((Integer.parseInt(keyId)>=Integer.parseInt(t_vs[0]))&&(Integer.parseInt(keyId)<=Integer.parseInt(t_vs[1]))){
								System.out.println(keyId+"--"+pakName+"=============全部增加"+maxdbVersion);
								fileMap.put("TYPEID", typeId);
								fileMap.put("KEYID", keyId);
								fileMap.put("KEYNAME", scriptName);
								fileMap.put("FILENAME", fileName);
								fileMap.put("APPID", appID);
								fileMap.put("THIRDPARTOFNAME", thirdPartOfName);
								fileList.add(fileMap);
							}
					 }
				 }else{
					 continue;
				 }
			 }
			 
		}
		if(is !=null)is.close();
		if(isr !=null)isr.close();
		if(in!=null)in.close();
	}
	/***
	 * 获得资源文件中的版本号
	 * @return
	 * @throws IOException 
	 */
	private  String[] readPropertiesInfo(){
		 Properties prop =  new  Properties();  
		 String[] reversion=new String[3];
		 InputStream in =null;
		 try {
		    in = new BufferedInputStream (getFileInputStream("application.properties"));  		
			prop.load(in);
			 String sysVersion = prop.getProperty( "system.version" ).trim();   //系统版本号
			 String comVersion = prop.getProperty( "common.version" ).trim();//公共的版本号
			 String appid = prop.getProperty( "system.appid" ).trim();//业务系统
			 reversion[0]=sysVersion;
			 reversion[1]=comVersion;
			 reversion[2]=appid;           
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(in!=null){try {
				in.close();
			} catch (IOException e) { 
				e.printStackTrace();
			}}
		}
		 return reversion;
	}
	
	/** 读取xml文件
     * @param xmlFilePath xml文件路径 
     * @return Document对象 
	 * @throws IOException 
     */  
    public  Document parse2Document(String xmlFilePath) throws IOException{  
        SAXReader reader = new SAXReader();  
        Document document = null;  
        InputStream in=null;
        try {  
             in =getFileInputStream(xmlFilePath);
            document = reader.read(in);  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
            System.out.println("读取文件发生异常，请检查path和文件名是否存在！");  
            e.printStackTrace();  
        }  finally{
        	if(in!=null){in.close();}
        }
        return document;  
    }  
    /**
	 * 比较版本号
	 * @param currVersion 当前版本号
	 * @param sysVersion  系统版本号
	 * @return greater:大于 equal:等于 less:小于
	 */
	public  String compileVersion(String currVersion,String sysVersion){
		String rs="";
		if(currVersion!=null&&!"".equals(currVersion)&&sysVersion!=null&&!"".equals(sysVersion)){
			String currstr=currVersion.replace("V", "");
			String sysstr=sysVersion.replace("V", "");
			String[] currs=currstr.split("\\.");
			String[] syss=sysstr.split("\\.");
			if(currs.length==syss.length){
				for(int i=0;i<currs.length;i++){
					if(Integer.parseInt(currs[i])>Integer.parseInt(syss[i])){
						rs="greater";
						break;
					}else if(Integer.parseInt(currs[i])==Integer.parseInt(syss[i])){
						rs="equal"; 
					}else{
						rs="less";
						break;
					}
				}				
			}else{
				rs="版本号格式不一致";
			}			
		}else{
			rs="版本号不能为空";
		}
		return rs;
	}
	/***
	 * 获得指定版本recordinterval的信息
	 * @param version  指定版本
	 * @param packname 包名称  格式bgt.common
	 * @param document xml的document信息
	 * @param appId 
	 */
	@SuppressWarnings("rawtypes")
	public  String getLastRecordinterval(String version,String packname,Document document,String appId){
		Element root = document.getRootElement();  
		String rs="0_0";
        for(Iterator i_first=root.elementIterator();i_first.hasNext();){
        	 Element e_first=(Element)i_first.next();
        	//取版本号属性
        	 String sysversion=e_first.attributeValue("sysversion").trim();
        	 String appType=e_first.attributeValue("apptype").trim();
        	 if(sysversion.equals(version)&&appType.equals(appId)){
        		 for (Iterator i_second = e_first.elementIterator(); i_second.hasNext();){
              		  Element e_second=(Element)i_second.next(); 
              		  String str_packagename=e_second.element("packagename").getText().trim();         		     
          		      if(str_packagename.equals(packname)){
          		    	 rs=e_second.element("recordinterval").getText().trim();
          		    	break;  
          		      }      		      
               	 } 
        		 break;
        	 }        	 
        }
        return rs;
	}
	/***
	 * 取所有的版本号
	 * @param document
	 * @return  内容格式为"common_V2.0.0.1" "app_V2.0.0.1"
	 */
	@SuppressWarnings("rawtypes")
	public List<String> getAllVersionInfo(Document document){
		Element root = document.getRootElement();  
		List<String> list=new ArrayList<String>();
        for(Iterator i_first=root.elementIterator();i_first.hasNext();){
        	 Element e_first=(Element)i_first.next();
        	//取版本号属性
        	 String sysversion=e_first.attributeValue("sysversion").trim();
        	 String apptype=e_first.attributeValue("apptype").trim();
        	 list.add(apptype+"_"+sysversion); 
        }
        return list;
	}
	/***
	 * 获得最大版本信息
	 * @param list  内容格式为"common_V2.0.0.1"
	 * @param appId
	 * @return
	 */
	public String getMaxVersion(List<String> list,String appId){
		String maxversion="V2.0.0.0";
		for(String rs:list){
			String[] vas=rs.split("_");
			if(vas[0].equals(appId)){
				String flag=compileVersion(vas[1], maxversion);
				if(flag.equals("greater")){
					maxversion=vas[1];
				}
			}				
		}
		return maxversion;
	}
}
