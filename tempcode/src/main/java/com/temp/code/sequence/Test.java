package com.temp.code.sequence;

public class Test {
	private final static long workerIdBits=5L;
	private final static long maxWorkerId=-1L ^ (-1L << workerIdBits);
	public static void main(String[] args) {
		System.out.println(workerIdBits);
		System.out.println(maxWorkerId);
		test1();
	}
	/**
	 * -1=-0000 0001 偶数 或者 奇数 -0111 1111
	 */
	public static void test1(){
		for(long a =1 ;a<10L;a++){
//			for (int i=0;i<64;i++){
//				System.out.println( (i<< a)+"a==="+a);
//				System.out.println((Math.pow(2, a))*i+"--------");
//			}
//			/**
//			 * 位运算位2的n次幂 乘以 位移数
//			 */
//			System.out.println(10<<1);
//			System.out.println((Math.pow(2, 7))+"--------");
			
			System.out.println(a+"--+1111----|"+(-3L << a));
			System.out.println(a+"-2222----|"+(-1L << a));
			System.out.println(a+"--3333----|"+(1L << a));
			System.out.println(a+"--4444----|"+(-1L ^ (-1L << a)));
			System.out.println(a+"---55555---|"+(1L ^ (-1L << a)));
			System.out.println(a+"---55555---|"+(1L ^ (1L << a)));
			System.out.println(a+"---6666o---|"+(-1L ^ (1L << a)));
			System.out.println(a+"---7777---|"+(-1L ^  a));
			System.out.println(a+"---o88888---|"+(1L ^  a));
		
		}
		
	}
	@org.junit.Test
	public void test2(){
		System.out.println(2^3);
		System.out.println(2&3);
		System.out.println(2|3);
		System.out.println(~3);
		System.out.println(~2);
		System.out.println(~100);
		
	}

}
