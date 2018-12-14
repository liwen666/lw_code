package commons.inputcomponent.utils;

import org.apache.commons.lang3.math.NumberUtils;

public class JSTypeUtils {
    
    /**
     * 转换为JS组件内部数据类型
     * @param dateType 1整形、 2小数、3字符、4标题、6引用、7大文本、8日期
     * @return
     */
    public static String getJSDateType(String dateType) {
        
        String jsDateType = null;
        Integer dType = NumberUtils.toInt(dateType);
        
        switch(dType){
            case 1: {
                jsDateType = "N";
                break;
            }
            case 2: {
                jsDateType = "N";
                break;
            }
            case 3: {
                jsDateType = "S";
                break;
            }
            case 4: {
                jsDateType = "S";
                break;
            }
            case 7: {
                jsDateType = "S";
                break;
            }
            case 8: {
                jsDateType = "D";
                break;
            }
            default:{
                jsDateType = "S";
                break;
            }
        }
        return jsDateType;
    }

    /**
     * @see Dict_t_DealType
     * @param xType
     * @return
     */
    public static String getJSXType(String xType) {
        
        String jsXType = null;
        
        if(xType.equalsIgnoreCase("A0")){
            jsXType = "grid";
        }else if(xType.equalsIgnoreCase("A1")){
            jsXType = "fixedGrid";
        }else if(xType.equalsIgnoreCase("A2") || xType.indexOf("*08")>=0 || xType.indexOf("*04")>=0 || xType.indexOf("*15")>=0 || xType.indexOf("*20")>=0|| xType.indexOf("*03")>=0|| xType.indexOf("*22")>=0){
            jsXType = "floatgrid";
        }else{
            jsXType = "grid"; 
        }
        return jsXType;
    }
    
    /**
     * @see W#DICT_T_FACTOR
     * SHOWFORMAT 界面显示处理方式：TEXT = "0" MASK_TEXT = "1" Date="2" CHECK = "3" LIST="4" BUTTON="5" RADIO = "6" SHORT DATE="7" POP_WINDOW = "8" HTML="9" 大文本="A" Picture="B" File="C" 
     * @param showFormat
     * @return
     */
    public static String getColumnJSXtype(String showFormat){
        
        String jsXType = null;
        
        if(showFormat == null){
            jsXType = "textfield";
        }else if(showFormat.equalsIgnoreCase("0")){
            jsXType = "textfield";
        }else if(showFormat.equalsIgnoreCase("1")){
            jsXType = "textfield";
        }else if(showFormat.equalsIgnoreCase("2")){
            jsXType = "datefield";
        }else if(showFormat.equalsIgnoreCase("3")){
            jsXType = "checkbox";
        }else if(showFormat.equalsIgnoreCase("4")){
            jsXType = "combo";
        }else if(showFormat.equalsIgnoreCase("5")){
            jsXType = "button";
        }else if(showFormat.equalsIgnoreCase("6")){
            jsXType = "radio";
        }else if(showFormat.equalsIgnoreCase("7")){
            jsXType = "datefield";
        }else if(showFormat.equalsIgnoreCase("8")){
            jsXType = "window";
        }else if(showFormat.equalsIgnoreCase("9")){
            jsXType = "textfield";
        }else if(showFormat.equalsIgnoreCase("A")){
            jsXType = "textarea";
        }else if(showFormat.equalsIgnoreCase("B")){
            jsXType = "textfield";
        }else if(showFormat.equalsIgnoreCase("C")){
            jsXType = "fileuploadfield";
        }else if(showFormat.equalsIgnoreCase("D")){
            jsXType = "password";
        }else if(showFormat.equalsIgnoreCase("E")){//add by xl
            jsXType = "multipleCombo";
        }else{
            jsXType = "textfield"; 
        }
        return jsXType;
    }

}
