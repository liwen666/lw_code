package commons.dict.constants;

public class DictCacheKey {

	public static final String CACHE_KEY_DICT = "HQ.DICT";

	
	public enum DictTableCode {
		ID("TABLECODEID"), 
		DBNAME("TABLECODEDBNAME"), 
		NAME("TABLECODENAME"),
		APP("ALLTABLECODEOFAPP"); 
		
		String cacheKey;
		
		DictTableCode(String key){
			cacheKey = key;
		}
		
		public String getCacheKey(){
			return cacheKey;
		}	
	}
	
	public enum DictFactorCode {
		ID("FACTORCODEID"), 
		DBNAME("FACTORCODEDBNAME"), 
		APP("ALLFACTORCODEOFAPP"); 
		
		String cacheKey;
		
		DictFactorCode(String key){
			cacheKey = key;
		}
		
		public String getCacheKey(){
			return cacheKey;
		}	
	}
	
	public enum DictSuit {
		ID("SUITID"),
		APP("ALLSUITOFAPP");
		
		String cacheKey;
		
		DictSuit(String key){
			cacheKey = key;
		}
		
		public String getCacheKey(){
			return cacheKey;
		}	
	}
	
	public enum DictTable {
		ALL("ALLTABLE"), 
		ID("TABLEID"), 
		DBNAME("TABLEDBNAME"), 
		NAME("TABLENAME"),
		TYPE("TABLETYPE"),
		DEALTYPE("TABLEDEALTYPE"),
		APP("ALLTABLEOFAPP");
		
		String cacheKey;
		
		DictTable(String key){
			cacheKey = key;
		}
		
		public String getCacheKey(){
			return cacheKey;
		}	
	}
	
	public enum DictColumn {
		ID("COLUMNID"), 
		DBNAME("COLUMNDBNAME"),
		APP("ALLCOLUMNOFAPP");
		
		String cacheKey;
		
		DictColumn(String key){
			cacheKey = key;
		}
		
		public String getCacheKey(){
			return cacheKey;
		}	
	}
}
