package commons.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileProcessBean {

  static final int BUFFER = 2048*5;

  public FileProcessBean() {
  }

  public static void main(String[] args) {
      
//    FileProcessBean.zipfile("C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\ccms_new","c:\\sasasasasa.zip");
//	  FileProcessBean.unZipFile("c:\\meet.zip", "c:\\output\\");
  }
  /**
   * 解压缩
   * @param strFileName
   * @param outputPath
   */
  public static void unZipFile(String strFileName, String outputPath) {

    try {
      final int BUFFER = 2048*5;
      BufferedOutputStream dest = null;
      FileInputStream fis = new  FileInputStream(strFileName);
      CheckedInputStream checksum = new
          CheckedInputStream(fis, new Adler32());
      ZipInputStream zis = new
          ZipInputStream(new
                         BufferedInputStream(checksum));
      ZipEntry entry;
      while ( (entry = zis.getNextEntry()) != null) {

        int count;
        byte data[] = new byte[BUFFER];
        // write the files to the disk
        FileOutputStream fos = new
            FileOutputStream(outputPath + entry.getName());

        dest = new BufferedOutputStream(fos,
                                        BUFFER);
        while ( (count = zis.read(data, 0,
                                  BUFFER)) != -1) {
          dest.write(data, 0, count);
        }
        dest.flush();
        dest.close();
      }
      zis.close();
      //System.out.println("Checksum: " + checksum.getChecksum().getValue());
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  /***
   * 压缩制定文件/文件夹 ，压缩后的文件类型是zip；可以压缩中文内容的文本文件；
   * 可以实现属性结构文件夹的压缩，但是再中文名称得文件夹处理上有些问题，
   * @param   strinfile 被压缩指定的文件/文件夹
   * @param   strzipfile压缩后的文件名称/文件夹名称；如果只给出了文件夹名称
   * 那压缩后的文件名称就是文件夹名称+"/WDZ.zip"
   *
   *
   */
  public static boolean zipfile(String infile, String strzipfile) {
    boolean blnzipfile = false;
    File filein;
    File fileout;
    String sourceFile = infile;
    filein = new File(sourceFile);
    fileout = new File(strzipfile);
    if (fileout.isDirectory()) {
      fileout = new File(strzipfile + "/WDZ.zip");
    }

    try {
      FileOutputStream f = new FileOutputStream(fileout);
      CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
      ZipOutputStream out =  new ZipOutputStream( new BufferedOutputStream(csum));
      String[] a = null;
      a = filein.list();
      if (filein.isDirectory()) {
        a = filein.list();
        //System.out.println("文件夹内文件个数：" + a.length);
        for (int i = 1; i <= a.length; i++)
          zipFileDetail(filein.getPath(), a[i - 1], out);
      }
      else {
        zipFileDetail("", sourceFile, out);
      }
      out.flush();
      out.close();

     // System.out.println("读文件");
      FileInputStream fi = new FileInputStream(fileout);
      CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
      ZipInputStream in2 =
          new ZipInputStream(
          new BufferedInputStream(csumi));
      ZipEntry ze;
      while ( (ze = in2.getNextEntry()) != null) {
        //System.out.println("源文件路径：" + ze);
      }
      in2.close();
      ZipFile zf = new ZipFile(fileout);
      Enumeration e = zf.entries();
      while (e.hasMoreElements()) {
        ZipEntry ze2 = (ZipEntry) e.nextElement();
        //System.out.println("压缩完成，压缩文件保存在：" + strzipfile);
      }
      csum.flush();
      csum.close();
      fi.close();
      in2.close();
      blnzipfile=true;
    }

    catch (Exception e) {
      e.printStackTrace();
    }
    return blnzipfile;
  }

  /**
   *
   * @param sPath
   * @param strinfile
   * @param out
   */
  private static void zipFileDetail(String sPath, String strinfile,
                                    ZipOutputStream out) {
    FileInputStream in = null;
    byte[] b =new  byte[2048*500];
    try {
      String sFileName = "";
      if (sPath.length() > 0)
        sFileName = sPath + "/" + strinfile;
      else
        sFileName = strinfile;

      File filein = new File(sFileName);
      if (filein.isDirectory()) {
        String[] a = null;
        a = filein.list();

        for (int i = 1; i <= a.length; i++)
          zipFileDetail(sPath, strinfile + "/" + a[i - 1], out);
      }
      else {
        in = new FileInputStream(sFileName);
        out.putNextEntry(new ZipEntry(GBToUnicode(strinfile)));

        int c;
        while ( (c = in.read()) !=-1) {
          out.write(c);
        }
      }
    }
    catch (Exception ex) {
    }
    finally {
      try {
        in.close();
      }
      catch (Exception e) {}
      b = null;
    }
  }

  /**
   * 将Unicode字符编码转换为GB编码
   * @param strIn：需要转换的字符串
   * @return:转换后的字串
   */
  public static String UnicodeToGB(String strIn) {
    byte[] b;
    String strOut = null;
    if (strIn == null || (strIn.trim()).equals(""))
      return strIn;
    try {
      b = strIn.getBytes("GB2312");
      strOut = new String(b, "ISO8859_1");
    }
    catch (Exception e) {
      System.out.println("unicodeToGB exception : " + e.getMessage() + "\n");
    }
    return strOut;
  }

  public static String GBToUnicode(String strIn) {
    byte[] b;
    String strOut = null;
    if (strIn == null || (strIn.trim()).equals(""))
      return strIn;
    try {
      b = strIn.getBytes("ISO8859_1");
      strOut = new String(b, "GB2312");
    }
    catch (Exception e) {
      System.out.println("GBToUnicode exception : " + e.getMessage() + "\n");
    }
    return strOut;
  }

  static public void reName(String oldpath, String newpath) throws Exception {
     try {
       File OldFile=new File(oldpath);
       File NewFile=new File(newpath);
       if(NewFile.exists()){
         NewFile.delete();
       }
       System.out.println(NewFile.getName());
       System.out.println(OldFile.getName());
       OldFile.renameTo(NewFile);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }

}

