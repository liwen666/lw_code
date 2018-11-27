package com.temp.code.math;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		int i=0;
		long a=System.currentTimeMillis();
		StringBuffer bf=new StringBuffer("4545122");
		while(i<1000){
//			System.out.println(Math.random()*12);
			bf.insert(new Random().nextInt(bf.length()), "0") ;
			i++;
		}
		System.out.println(System.currentTimeMillis()-a);
		System.out.println(bf.toString());
	}

}
