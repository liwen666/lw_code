package commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class UrlUtil {
  private static Logger log = Logger.getLogger(UrlUtil.class);
  public static int TIMEOUT = 10000;

  public static boolean checkLink(String urlStr) {

    InputStream is = null;
    URLConnection con = null;
    try {
      URL url = new URL(urlStr);
      con = url.openConnection();

      if (url.getProtocol().equals("http")) {
        con.connect();

      }
      else if (url.getProtocol().equals("file")) {
        is = con.getInputStream();
        is.close();
        // If that didn't throw an exception, the file is probably OK
      }
      else {
        return false;
      }

    }
    catch (Exception e) {
      log.info("UrlUtil->heckLink(String urlStr):" + e.toString());
      return false;
    }
    finally {
      try {
        if (is != null) {
          is.close();
        }

      }
      catch (Exception e1) {
        // TODO Auto-generated catch block
        log.info("UrlUtil->heckLink(String urlStr):" + e1.toString());
      }
    }
    return true;
  } //functioin

  public static String getUrlContent(String urlStr) {

    String thisLinestr = "";
    InputStream stream = null;
    try {

      stream = new URL(urlStr).openStream();
      BufferedReader bufreader = new BufferedReader(
          new InputStreamReader(stream));
      String thisLine = bufreader.readLine();
      while (thisLine != null) {
        thisLinestr = thisLinestr + thisLine;
        thisLine = bufreader.readLine();
        if (thisLine != null) {
          thisLine = thisLine.trim();
        }
      }
    }
    catch (IOException e) {
      log.info("UrlUtil->getUrlContent(String urlStr):" + e.toString());
    }
    finally {
      try {
        if (stream != null) {
          stream.close();
        }

      }
      catch (IOException e1) {
        // TODO Auto-generated catch block
        log.info("UrlUtil->getUrlContent(String urlStr):" + e1.toString());
      }
    }
    return thisLinestr;
  } //function

  public static boolean ping(String urlVal) {
    URL url = null;
    try {

      Thread time1 = new TimeOutUtil(TIMEOUT);
      Thread time2 = new IPConnection(urlVal);
      time1.start();
      time2.start();
      while (time1.isAlive() && time2.isAlive()) {
      }

      if (time1.isAlive()) {
        //time1.stop();
        return true;
      }
      if (time2.isAlive()) {
        //time2.stop();
        return false;
      }

    }
    catch (Exception e) {
      log.info("UrlUtil->ping(String urlVal):" + e.toString());
      return false;
    }

    return false;
  }

  /**
   *
   * @param url
   * @return
   */
  public static String getUrlStr(String url) {

    StringBuffer sTotalString = new StringBuffer(1024);

    try {
      URL l_url = new URL(url);
      HttpURLConnection l_connection = (HttpURLConnection) l_url
          .openConnection();
      l_connection.connect();
      InputStream in = l_connection.getInputStream();

      BufferedReader l_reader = new BufferedReader(new InputStreamReader(
          in));

      String sCurrentLine = "";
      while ( (sCurrentLine = l_reader.readLine()) != null) {
        sTotalString.append(sCurrentLine + "\n");
      }
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      log.info("UrlUtil->getUrlStr(String url):" + e.toString());
    }

    return sTotalString.toString();

  }

} //class
