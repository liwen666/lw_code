package com.temp.code.io.demo2;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
/**
 * 字节流不识别汉字
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

import org.junit.Test;

public class hello2 {
	@Test
	public void reader() throws IOException {
		String fileName = "F:" + File.separator + "io" + File.separator + "hello.txt"; // File.separator在window位
																						// \
																						// 在linux为
																						// 、/
		File f = new File(fileName);
		InputStream in = new FileInputStream(f);
		byte[] b = new byte[1024];
		int count = 0;
		int temp = 0;
		while ((temp = in.read()) != (-1)) {
			b[count++] = (byte) temp;
		}
		in.close();
		System.out.println(new String(b));
	}

	/**
	 * 暂时放置
	 * 
	 * @throws IOException
	 */
	@Test
	public void DataOutputStreamDemo() throws IOException {
		String fileName = "F:" + File.separator + "io" + File.separator + "hello.txt"; // File.separator在window位
																						// \
																						// 在linux为
																						// 、/
		File file = new File(fileName);
		DataInputStream input = new DataInputStream(new FileInputStream(file));
		char[] ch = new char[100];
		int count = 0;
		char temp;
		while ((temp = input.readChar()) != 'C' && count < 10) {
			ch[count++] = temp;
		}
		input.close();
		System.out.println(ch);
	}

	@Test
	public void PushBackInputStreamDemo() throws IOException {
		String str = "hello,rollenholt";
		PushbackInputStream push = null;
		ByteArrayInputStream bat = null;
		bat = new ByteArrayInputStream(str.getBytes());
		push = new PushbackInputStream(bat);
		int temp = 0;
		while ((temp = push.read()) != -1) {
			System.out.println("---"+temp+"---");
			if (temp == ',') {
				
				push.unread(temp);
				temp = push.read();
				System.out.print("(回退" + (char) temp + ") ");
			} else {
				System.out.println((char) temp);
			}
		}
		push.close();
		bat.close();
	}
	@Test
	public void testchar(){
		char a='a';
		int i=111;
		System.out.println(a);
		System.out.println((char)i);
	}
}