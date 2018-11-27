package com.templete.code.sequence;

import java.util.Date;
import java.util.logging.Logger;

import org.apache.logging.log4j.core.LoggerContext;
import org.slf4j.LoggerFactory;

import com.templete.code.exception.DataBaseException;

/**
 * @author  作者 :lw E-mail:
 * @date 创建时间：2016年11月28日 下午2:46:36
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */
public class FapSeqGenerator {
	 private static final String SEQ_APP="APP";
	 private static final String SEQ_DATE_FORMAT="yyMMdd";
	 private static final FapSeqGenerator instance=new FapSeqGenerator();
	 
	 private static final String NAM_SYSTEM_STRING="NUM_APP_SEQ";
	 private AbstractSqGenerator seqGenerator= SeqGenerator12.getInstance();
	 private ICurDao dao = new FapCurDaoImpl();
	 
	 private FapSeqGenerator(){}
	 
	 public static FapSeqGenerator getInstance(){
		 return instance;
		 
	 }
	 
	 public AbstractSqGenerator getSeqGenerator(){
		 return seqGenerator;
		 
	 }

	public void setSeqGenerator(AbstractSqGenerator seqGenerator) {
		this.seqGenerator = seqGenerator;
	}
	
	public String getFapSeqNo(){
		String date = Long.toString(new Date().getTime());
		long seqNo= seqGenerator.getNextId(System.currentTimeMillis());
		return String.format("%s%s%012d", SEQ_APP,date,seqNo);
		
	}
	public String getFapSeqNoWithOutDate(){
		long seqNo= SeqGenerator18.getInstance().getNextId(System.currentTimeMillis());
		return String.format("%s%s%018d", SEQ_APP,seqNo);
		
	}
	
	public String getFapSeqNo(IDataContext dataContext){
		try{
			String date = Long.toString(new Date().getTime());
			String name_system=NAM_SYSTEM_STRING+date;
			long curt = dao.getIDByName(dataContext,name_system);
			String seqNo = SEQ_APP+date + String .format("%012d", curt);
			LogWriter.trace(this.getClass(),"生成流水号[{}]",seqNo);
			return seqNo;
			
					
		}catch(DataBaseException e){
			try {
				LogWriter.error(this.getClass(),"生成流水号失败，回滚！",seqNo);
				DaoApi.rollback(dataContext);
			} catch (Exception e2) {
				LogWriter.error(FapSeqGenerator.class,"回滚失败！",seqNo);
			}
			return null;
			
		}
	}
	 
	 
}
