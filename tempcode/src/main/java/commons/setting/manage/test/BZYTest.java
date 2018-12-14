package commons.setting.manage.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tjhq.commons.execscript.common.Constants;
import com.tjhq.commons.jackson.ObjectMapper;

public class BZYTest {

	/**
	 * @param args
	 * @throws Exception
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static void main(String[] args) throws Exception {
		//testJSON();
		//testSortList();
		//testList();
		//testSplit();
		//testSubstring();
		//testMap();
	    //testPailieZuhe();
	    String r = testTry();
	    System.out.println(r);
	}
	private static void testPailieZuhe() {/*
        String str[] = { "A", "B", "C", "D", "E" };  
  
        int nCnt = str.length;  
  
        int nBit = (0xFFFFFFFF >>> (32 - nCnt));  //实际上就是所有组合数是2^n-1，此处为31
        System.out.println("nBit="+nBit);  
  
        for (int i = 1; i <= nBit; i++) {  
            for (int j = 0; j < nCnt; j++) {  
                if ((i << (31 - j)) >> 31 == -1) {  
                    System.out.println("i,j:"+i+","+j);
                    System.out.println("i << (31 - j):"+(i << (31 - j)));
                    System.out.print(str[j]);  
                }  
            }  
            System.out.println("");  
        }  
    */
	    String str[] = { "A", "B", "C"/*, "D", "E"*/ };  
        int nCnt = str.length;

        int nBit = 1<<nCnt;
        
        for (int i = 1; i <= nBit; i++) {
            for (int j = 0; j < nCnt; j++) {
                System.out.println("i:"+Integer.toBinaryString(i));
                System.out.println("j:"+Integer.toBinaryString(j));
                System.out.println("1<<j:"+Integer.toBinaryString(1<<j));
                System.out.println("(1<<j & i ) :"+Integer.toBinaryString(1<<j & i ) );
                if ((1<<j & i ) != 0) {
                    System.out.print(str[j]);
                }
                System.out.println("");
                System.out.println("------------------------------------");
            }
            System.out.println("==================================================================================================================------------------------------------");
        }
	}
    private static void testMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dd", "asdfas");
		map.put("", "2");
		map.put(null, "3");
		System.out.println(map.size()+"===="+map);
	}
	private static void testSubstring() {
		//String fileName = "common_5_严明_CODE_T_PROJECTANDSPF.sql";
		String fileName = "common_5_.sql";
		String[] fileNameParts = fileName.split("_");
		String typeId = fileNameParts[0];
		String keyId = fileNameParts[1];
		String keyName = fileName.substring(fileName.indexOf(keyId)+keyId.length()+1, fileName.lastIndexOf('.'));
		/*String nameHead = "common";
		int beginIdx = fileName.indexOf("_", (nameHead+"_").length())+1;
		int endIdx = fileName.lastIndexOf(".");
		System.out.println(fileName.substring(endIdx));*/
		System.out.println(keyName);
		
		
		String line = "busi_0001_.sql  ||  曹坤0804的脚本";
		String[] names = line.split(Constants.SCRIPTSPLITFLAGSTRING);
		for (String string : names) {
			System.out.println(string.trim());
		}
	}
	private static void testSplit() {
		String fileName = "毕再一测试脚本.sql";
		String fileEnd = fileName.split("\\.")[fileName.split("\\.").length-1];
		System.out.println(fileEnd);
	}
	private static void testList() {
		List<String> aList = new ArrayList<String>();
		List<String> bList = new ArrayList<String>();
		List<String> cList = new ArrayList<String>();
		
		
	}
	private static void testSortList() {
		String a = "common";
		String b = "spf_6200";
		String c = "bgt_6200";
		String d = "bgt_common";
		String e = "common_10_毕再一_添加预算系统系统维护菜单.sql";
		String f = "common_11_dd.sql";
		String g= "common_2_dd.sql";
		List<String> strList = new ArrayList<String>();
		strList.add(a);
		strList.add(b);
		strList.add(c);
		strList.add(d);
		strList.add(e);
		strList.add(g);
		strList.add(f);
		System.out.println( strList );
		orderByName(strList);
		System.out.println( strList );
	}
	// 按名称排序，从小到大。
	private static List<String> orderByName(List<String> strList) {
		if (strList == null) {
			return null;
		}
		Collections.sort(strList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int diff = o1.compareTo(o2) ;
				if (diff > 0)
					return 1;
				else if (diff == 0)
					return 0;
				else
					return -1;
			}
		});
		return strList;
	}
	private static void testJSON() throws Exception {
		String jsonarr = "[{\"_locationposition\": 0, \"THIRDPARTOFNAME\": \"毕再一\", \"FILENAME\": \"common_1_毕再一_20150827_获取查询条件默认值SYS_F_GETQUERYDEFNAME.sql\", \"APPID\": \"*\", \"KEYNAME\": \"毕再一_20150827_获取查询条件默认值SYS_F_GETQUERYDEFNAME\", \"KEYID\": \"1\", \"TYPEID\": \"common\", \"_sortid\": 0, \"check\": 1}]";
		String jsonarr2 = "1,2,3,4,";
		String[] a = jsonarr2.split(",");
		System.out.println(a.length);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}

		String adad = "[{\"_locationposition\": 0, \"THIRDPARTOFNAME\": \"jdbc\", \"FILENAME\": \"common_18_jdbc_毕再一_20150921_更新执行脚本存储过程.sql\", \"APPID\": \"*\", \"KEYNAME\": \"jdbc_毕再一_20150921_更新执行脚本存储过程\", \"KEYID\": \"18\", \"TYPEID\": \"common\", \"_sortid\": 0, \"check\": 1}, {\"_locationposition\": 1, \"THIRDPARTOFNAME\": \"刘凯\", \"FILENAME\": \"common_1_刘凯_新增项目倒挂菜单.sql\", \"APPID\": \"spf\", \"KEYNAME\": \"刘凯_新增项目倒挂菜单\", \"KEYID\": \"1\", \"TYPEID\": \"common\", \"_sortid\": 1, \"check\": 1}, {\"_locationposition\": 2, \"THIRDPARTOFNAME\": \"刘凯\", \"FILENAME\": \"common_2_刘凯_新增项目倒挂中用到的存储过程.sql\", \"APPID\": \"spf\", \"KEYNAME\": \"刘凯_新增项目倒挂中用到的存储过程\", \"KEYID\": \"2\", \"TYPEID\": \"common\", \"_sortid\": 2, \"check\": 1}, {\"_locationposition\": 3, \"THIRDPARTOFNAME\": \"周兆坤\", \"FILENAME\": \"common_3_周兆坤_批量修改专项项目编码（等同二级项目新规则）.sql\", \"APPID\": \"spf\", \"KEYNAME\": \"周兆坤_批量修改专项项目编码（等同二级项目新规则）\", \"KEYID\": \"3\", \"TYPEID\": \"common\", \"_sortid\": 3, \"check\": 1}]";
		adad = adad.replaceAll("\\", "");		
		JSONArray adad1 = JSONArray.fromObject(adad);
		System.out.println("adad1:"+adad1);
		JSONArray rowsArray1 = JSONArray.fromObject(jsonarr);
		System.out.println(rowsArray1);

		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> list = objectMapper.readValue(jsonarr, List.class);
		System.out.println(list);
	}
	private static String testTry(){
        String a = "";
	    try{
	        a = "1";
	        System.out.println("---try---"+a);
	        return a;
	    }catch(Exception e){
            a = "e";
            System.out.println("---e---"+a);
            return a;
	    }finally{
	        a= "2";
            System.out.println("---finally---"+a);
	    }
	}
	class StringUtil{
		
	}
}
