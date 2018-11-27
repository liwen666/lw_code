package com.temp.code.io.resolver;

import org.junit.Test;

public class resolver {
	// #解析占位符替换的算法， <property name="url" value="${url}"/>

	// text 为输入占位符 如：${url}
	@Test
	public void parse() {
		String text=" <property name=\"url\" value=\"${url}\"/>    9 ";
	    StringBuilder builder = new StringBuilder();
	    String value="-------------";
	    if (text != null && text.length() > 0) {
	      char[] src = text.toCharArray();
	      int offset = 0;
	      int start = text.indexOf("$", offset);
	          builder.append(src, offset, start).append(value);
	          offset = start + 1;
	        
	          int end = text.indexOf("}", start);
	        System.out.println(end);
	        System.out.println(src.length-end);
	        builder.append(src,end+1,src.length-end-1);//长度比下标大一
	          System.out.println(builder.toString());
	    }
}
}