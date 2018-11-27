package test;

import org.junit.Test;

/**
 * @author  作者 :lw E-mail:
 * @date 创建时间：2016年11月29日 下午12:19:38
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */
public class TestCode {
   public static void main(String[] args) {
	
}
   /**
    * junit 测试需要方法是public void
    * 不能在main方法上测试
    * 
    * @see
    */
   @Test 
  public  void getTest(){
	   System.out.println("jjjjj");
   }
}
