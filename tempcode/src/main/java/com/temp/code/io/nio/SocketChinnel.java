package com.temp.code.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import org.junit.Test;

public class SocketChinnel {
	@Test
	public void test1() throws IOException{
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("http://www.xxx.com", 80));
		socketChannel.close();
	}
	@Test
	public void test2() throws IOException{
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress("http://www.xxx.com", 80));

		while(!socketChannel.finishConnect() ){
		    // wait, or do something else
		}
		socketChannel.close();
	}
	@Test
	public void ServerSocketChannel() throws IOException{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		serverSocketChannel.socket().bind(new InetSocketAddress(80));
		serverSocketChannel.configureBlocking(false);//设置为非阻塞

		while(true) {
		    SocketChannel socketChannel = serverSocketChannel.accept();

		    if (socketChannel != null) {
		        //do something
		    }
		}
	}
	@Test
	public void DatagramChannel() throws IOException{
		DatagramChannel channel = DatagramChannel.open();
		channel.socket().bind(new InetSocketAddress(9000));
	}
	@Test
	public void DatagramChannel1() throws IOException{
		DatagramChannel channel = DatagramChannel.open();
		channel.socket().bind(new InetSocketAddress(9000));
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		channel.receive(buf);
	}
	@Test
	public void DatagramChannel2() throws IOException{
		DatagramChannel channel = DatagramChannel.open();
		channel.socket().bind(new InetSocketAddress(9000));
		String newData = "Some data";

		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();

		int bytesSent = channel.send(buf, new InetSocketAddress("www.xxx.com", 9000));
	}
	
}
