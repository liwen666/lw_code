package com.temp.code.sequence;

public class Test2 {
    public static void main(String[] args) {
//    	for(int a=0 ;a<64;a++){
//    		System.out.println(-1 ^(-1 << a));
//    		System.out.println(-1 ^(1 << a));
//    		System.out.println(1 ^(-1 << a));
//    		System.out.println((1 ^(1 << a))+"--------"+a);
//    		
//    	}
//    	for(int a=1 ;a<64;a++){
//    		System.out.println(-2 ^(-1 << a));
//    		System.out.println(-2 ^(1 << a));
//    		System.out.println(2 ^(-1 << a));
//    		System.out.println((2 ^(1 << a))+"--------");
//    		
//    	}
    	for(int a=1 ;a<64;a++){
    		System.out.println( a);
    		System.out.println(-1^ a);
    		System.out.println((~a)+"--------");
    		System.out.println( ~-a);
    		
    	}
		
	}
}
