package util;

import java.lang.reflect.Field;

import javax.xml.datatype.XMLGregorianCalendar;

import com.hq.bpmn.templatedef.bean.BpmnTemplateDef;

public class DataConversion {
//	public static H2DataToOracle
	public static Object XmlObjectToObject(Object xmlObj , Object obj){
	     Field[] fxml = xmlObj.getClass().getDeclaredFields();
	     Field[] fobj = obj.getClass().getDeclaredFields();
	     for(int i= 0;i<fxml.length;i++){
	    	 fxml[i].setAccessible(true);
	    	 try {
	    	 for(Field f : fobj){
	    		 f.setAccessible(true);
	    		 if(f.getName().equals(fxml[i].getName())){
	    			 if(fxml[i].getType().getName().equals("javax.xml.datatype.XMLGregorianCalendar")){
	    				 if(null !=fxml[i].get(xmlObj)){
	    					 f.set(obj,DateUtil.xmlDate2Date((XMLGregorianCalendar) fxml[i].get(xmlObj)));
		    	    		 continue; 
	    				 }
	    	    		
	    	    	 }
	    			 f.set(obj,fxml[i].get(xmlObj));
	    		 }
	    		 f.setAccessible(false);
	    	 }
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 fxml[i].setAccessible(false);
	     }
		return obj;
	}
	public static void main(String[] args) {
		System.out.println(BpmnTemplateDef.class.getFields().length);
		System.out.println(BpmnTemplateDef.class.getDeclaredFields().length);
		System.out.println(BpmnTemplateDef.class.getDeclaredFields()[0].getName());
	}
}
