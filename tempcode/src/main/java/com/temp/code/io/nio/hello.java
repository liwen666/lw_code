package com.temp.code.io.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class hello {
	@Test
	public void read() throws IOException{
		RandomAccessFile aFile = new RandomAccessFile("F:" + File.separator + "io" + File.separator + "hello.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = inChannel.read(buf);
		aFile.close();
		inChannel.close();
		System.out.println(buf);
		System.out.println(bytesRead);
		
	}
	@Test
	public void write() throws IOException{
		RandomAccessFile aFile = new RandomAccessFile("F:" + File.separator + "io" + File.separator + "hello.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		String newData = "Some data-------------------------";

		ByteBuffer buf = ByteBuffer.allocate(480);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();

		while(buf.hasRemaining()) {
			//**从偏移量位文件长度的位置开始写
//			inChannel.write(buf,aFile.length());
			//覆盖文件从偏移量位0的位置开始写
			inChannel.write(buf);
		}
		inChannel.close();
		aFile.close();
		
		
	}
	@Test
	public void write2() throws IOException{
		RandomAccessFile aFile = new RandomAccessFile("F:" + File.separator + "io" + File.separator + "hello.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		String newData = "Some data-------------------------";
		
		ByteBuffer buf = ByteBuffer.allocate(480);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		
		long pos=inChannel.position();
		long size=inChannel.size();
		inChannel.position(10);
		System.out.println(inChannel.position());
		System.out.println(pos+"---------"+size+"------"+aFile.length());
		while(buf.hasRemaining()) {
			//**从偏移量位文件长度的位置开始写
//			inChannel.write(buf,aFile.length());
			//覆盖文件从偏移量位0的位置开始写
			inChannel.write(buf);
			buf.capacity();
		}
		inChannel.close();
		aFile.close();
		
		
	}
	@Test
	public void truncate() throws IOException{
		RandomAccessFile aFile = new RandomAccessFile("F:" + File.separator + "io" + File.separator + "hello.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		inChannel.truncate(5);
		inChannel.close();
		aFile.close();
		
		
	}
}