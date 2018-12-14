package commons.utils;

/**
 * <p>Title: MD5加密处理类</p>
 * <p>Description: MD5加密和解密处理类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 北京太极华青信息系统有限公司</p>
 * @author 
 * @version 1.0
 */


import org.apache.log4j.Logger;

public class MdDigest {
  private static Logger log = Logger.getLogger(MdDigest.class);
  public static String encrypt(String str) {
    try {
      java.security.MessageDigest alga = java.security.MessageDigest.
          getInstance("MD5");

      alga.update(str.getBytes());
      byte[] digesta = alga.digest();
      String strEncrypt = byte2hex(digesta);
      return strEncrypt;
    }
    catch (Exception e) {
      log.info("MdDigest->encrypt(String str):" + e.toString());
    }
    finally {
    }

    return null;
  }

  public static String byte2hex(byte[] b) { //二行制转字符串
    String hs = "";
    String stmp = "";
    for (int n = 0; n < b.length; n++) {
      stmp = (Integer.toHexString(b[n] & 0XFF));
      if (stmp.length() == 1) {
        hs = hs + "0" + stmp;
      }
      else {
        hs = hs + stmp;
      }
    }
    return hs;
  }
  
  public static void main(String args[]){
	  System.out.println(MdDigest.encrypt("b43dx"));
  }

}
