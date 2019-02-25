package com.temp.code.sequence;

import com.temp.code.exception.app.DataBaseException;

import java.util.Date;



/**
 * @author  ���� :lw E-mail:
 * @date ����ʱ�䣺2016��11��28�� ����2:46:36
 * @version 1.0 * @parameter
 * @since
 * @return
 */
public class FapSeqGenerator {
    public static void main(String[] args) {
        FapSeqGenerator fsg= new FapSeqGenerator();
        System.out.println(fsg.getFapSeqNo());
        System.out.println(fsg.getFapSeqNoWithOutDate());
    }

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
		System.out.println(seqNo);
		return String.format("%s%s%012d", SEQ_APP,date,seqNo);

	}
	public String getFapSeqNoWithOutDate(){
		long seqNo= SeqGenerator18.getInstance().getNextId(System.currentTimeMillis());
		System.out.println(seqNo);
		return String.format("%s%s%018d", SEQ_APP,seqNo);

	}

//	public String getFapSeqNo(IDataContext dataContext){
//		try{
//			String date = Long.toString(new Date().getTime());
//			String name_system=NAM_SYSTEM_STRING+date;
//			long curt = dao.getIDByName(dataContext,name_system);
//			String seqNo = SEQ_APP+date + String .format("%012d", curt);
//			LogWriter.trace(this.getClass(),"������ˮ��[{}]",seqNo);
//			return seqNo;
//
//
//		}catch(DataBaseException e){
//			try {
//				LogWriter.error(this.getClass(),"������ˮ��ʧ�ܣ��ع���",seqNo);
//				DaoApi.rollback(dataContext);
//			} catch (Exception e2) {
//				LogWriter.error(FapSeqGenerator.class,"�ع�ʧ�ܣ�",seqNo);
//			}
//			return null;
//
//		}
//	}


}
