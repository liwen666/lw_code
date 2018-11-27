package com.temp.code.io.demo1;

import java.io.File;
/**
 * 字节流不识别汉字
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;  
class hello3{  
	   public static void main(String[] args) throws IOException {  
		   String fileName="F:"+File.separator+"io"+File.separator+"hello.txt";  //File.separator在window位 \ 在linux为 、/
	       File f=new File(fileName);  
	       InputStream in=new FileInputStream(f);  
	       byte[] b=new byte[1024];  
	       int len=in.read(b);//获取读到的文件长度  
	       in.close();  
	       System.out.println("读入长度为："+len);  
	       System.out.println(new String(b,0,len));  
	    }  
	}  