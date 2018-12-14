package commons.utils;

/**
 * <p>Title: IP连接设置处理类</p>
 * <p>Description: 设置IP连接</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 北京太极华青信息系统有限公司</p>
 * @author 
 * @version 1.0
 */


import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

/**
 * @author liyade
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class IPConnection
    extends Thread {
  private static Logger log = Logger.getLogger(IPConnection.class);
  /**
   *
   */
  private String ip;

  public IPConnection(String ip) {
    this.ip = ip;

    // TODO 自动生成构造函数存根
  }

  /* （非 Javadoc）
   * @see java.lang.Runnable#run()
   */
  public void run() {

    try {

      URL url = new URL(ip);
      URLConnection con = url.openConnection();
      con.connect();

    }
    catch (Exception e) {
      log.info("IPConnection->run():" + e.toString());
      try {
        Thread.sleep(UrlUtil.TIMEOUT);
      }
      catch (Exception e1) {
        log.info("IPConnection->run():" + e1.toString());
      }

    }

  }

  public static void main(String agr[]) {
    new IPConnection("http://192.168.0.221:8001/services/DoSearch").start();
  }

}
