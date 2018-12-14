package commons.utils;

/**
 * <p>Title: 文件操作类</p>
 * <p>Description: 对文件做不同的操作处理</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 北京太极华青信息系统有限公司</p>
 * @author 
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class FileUtil {
	private static Logger log = Logger.getLogger(FileUtil.class);

	/**
	 * 生成带日期标记的文件名称
	 * 
	 * @param firstName
	 *            String：文件前半部分的名称,optType
	 * @param postfixName
	 *            String:文件后缀名 xml
	 * @return String optType_200606051243.xml
	 */
	public static String getFileName(String firstName, String postfixName) {
		return firstName + "_" + DateUtil.dateDisplayToYMD(new Date()) + "."
				+ postfixName;
	}

	public static boolean markFile(String filePath, String filename) {
		File temp = new File(filePath);
		// System.out.println("mkDirectory:"+filePath);
		if (!temp.isDirectory()) {
			temp.mkdir();
		}
		temp = new File(filePath + "/" + filename);
		if (temp.isFile()) {
			temp.deleteOnExit();
		}
		try {
			return temp.createNewFile();
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static boolean markDir(String filePath) {
		File temp = new File(filePath);
		if (temp.isDirectory()) {
			return true;
		} else {
			return temp.mkdir();
		}
	}

	public static boolean isExistsFile(String filePath) {
		File temp = new File(filePath);
		if (temp.isFile()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isExistsDirectory(String filePath) {
		File temp = new File(filePath);
		if (temp.isDirectory()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 得到文件创建时间
	 * 
	 * @return
	 */
	public static String getFileDate() {

		Calendar calendar = null;
		calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		return calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到url的文件名称
	 * 
	 * @param url
	 * @return
	 */
	public static String getFileName(String url) {
		int len = url.lastIndexOf('/');
		if (len == -1) {
			log.info("FileUtil->getFileName(String url):文件名称为空");
			return "error";
		}

		url = url.substring(len + 1);
		len = url.lastIndexOf('.');
		if (len == -1) {
			return "";
		}
		url = url.substring(0, len);
		return url;
	}

	/**
	 * 得到url的文件名称
	 * 
	 * @param url
	 * @return
	 */
	public static String getFileByUrl(String url) {
		int len = url.lastIndexOf('/');
		if (len == -1) {
			return "error";
		}
		return url.substring(len + 1);

	}

	/**
	 * 删除目录的方法（删除此目录下所有子文件）
	 * 
	 * @param vpath
	 * @return
	 */
	public static boolean delDirFiles(String vpath) {

		try {
			String pathName = vpath;
			File delPath = new File(pathName);

			if (delPath.exists()) {
				String[] strList = delPath.list();

				for (int i = 0; i < strList.length; i++) {
					File turePath = new File(delPath.getPath(), strList[i]);
					boolean isdir = turePath.isDirectory();
					if (!isdir) {
						turePath.delete();
					}
				}

			}
		} catch (Exception e) {
			log.info("FileUtil->DeldirFiles(String vpath):" + e.toString());
			return false;
		}
		return true;
	}

	/**
	 * 删除指定文件
	 * 
	 * @param path
	 */
	public static void deleteFile(String path) {
		File fileName = new File(path);
		if (fileName.exists()) {
			fileName.delete(); // 删除对应的文件
		}
	}

	/**
	 * 删除指定文件
	 * 
	 * @param path
	 */
	public static boolean deleteLikeFile(String path, String file) {
		try {
			String pathName = path;
			File delPath = new File(pathName);

			if (delPath.exists()) {
				File[] strList = delPath.listFiles();

				for (int i = 0; i < strList.length; i++) {
					if (strList[i].getAbsolutePath().indexOf(file) != -1) {
						strList[i].delete();
					}
				}

			}
		} catch (Exception e) {
			log.info("FileUtil->DeleteLikeFile(String path, String file):"
					+ e.toString());
			return false;
		}
		return true;
	}

	/**
	 * 完成文件的创造和保存的方法
	 * 
	 * @param path
	 * @param filename
	 * @param content
	 */
	public static void saveFile(String path, String filename, String content) {

		PrintWriter pw = null;
		try {
			File temp = new File(path);
			if (!temp.isDirectory()) {
				temp.mkdir();
			}
			pw = new PrintWriter(new FileOutputStream(path + filename));
			pw.println(content);
			pw.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

	} // function

	/**
	 * 完成文件的创造和保存的方法
	 * 
	 * @param path
	 * @param filename
	 * @param content
	 */
	public static boolean saveFile(String filePath, String content) {

		PrintWriter pw = null;
		try {
			// System.out.println("temp save file=" + filePath);
			pw = new PrintWriter(new FileOutputStream(filePath));
			pw.println(content);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		return true;
	} // function

	/**
	 * 利用PrintStream写文件
	 * @param filePath
	 * @param content
	 */
	public static void funPSWrite(String filePath, String content)
			throws IOException {
		File file = new File(filePath);
		if (!file.exists())
			file.createNewFile();
		try {
			FileOutputStream out = new FileOutputStream(filePath);
			PrintStream p = new PrintStream(out);
			p.println(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 利用StringBuffer写文件 该方法可以设定使用何种编码，有效解决中文问题。
	 * @param filePath
	 * @param content
	 * @param charset 编码或字符集,如utf-8,ISO8859-1 
	 * @throws IOException
	 */
	public static void funSBWrite(String filePath, String content,String charset) throws IOException {
		File file = new File(filePath);
		deleteFile(filePath);
		//if (!file.exists())
			file.createNewFile();
		FileOutputStream out = new FileOutputStream(file, true);		
		out.write(content.getBytes(charset));
		out.close();
	}

	/**
	 * 利用FileInputStream读取文件
	 * 
	 * @param path
	 * @param charset 编码或字符集,如utf-8,ISO8859-1 
	 * @return
	 * @throws IOException
	 */
    public static String funFISRead(String path,String charset) throws IOException{
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        FileInputStream fis=new FileInputStream(file);
        byte[] buf = new byte[1024];
        StringBuffer sb=new StringBuffer();
        while((fis.read(buf))!=-1){
            sb.append(new String(buf,charset));    
            buf=new byte[1024];//重新生成，避免和上次读取的数据重复
        }
        return sb.toString();
    }

    /**利用BufferedReader读取
     * 在IO操作，利用BufferedReader和BufferedWriter效率会更高一点
     * 本方法中，对于换行，会自动处理成空格，这个不便于还原原文件
     * @param path
     * @return
     * @throws IOException
     */
    public static String funBRReader(String path) throws IOException{
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br=new BufferedReader(new FileReader(file));
        String temp=null;
        StringBuffer sb=new StringBuffer();
        temp=br.readLine();
        while(temp!=null){
            sb.append(temp+" ");
            temp=br.readLine();
        }
        return sb.toString();
    }

	/**
	 * 得到图片格式
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFileTypeImg(String filename) {
		String str = "00.gif";
		if (filename == null || filename.trim().length() < 5) {
			str = "00.gif";
		} else {
			filename = filename.trim();
			filename = filename.substring(filename.length() - 4, filename
					.length());
			filename = filename.toLowerCase();

			if (filename.equals(".txt")) {
				str = "txt.gif";
			} else if (filename.equals(".xml")) {
				str = "xml.gif";
			} else if (filename.equals(".xsl") || filename.equals("xslt")) {
				str = "xsl.gif";
			} else if (filename.equals(".doc")) {
				str = "doc.gif";
			} else if (filename.equals(".css")) {
				str = "css.gif";
			} else if (filename.equals(".htm") || filename.equals("html")) {
				str = "htm.gif";
			} else if (filename.equals(".gif")) {
				str = "gif.gif";
			} else if (filename.equals(".jpg") || filename.equals("jpeg")) {
				str = "jpg.gif";
			} else if (filename.equals(".psd")) {
				str = "psd.gif";
			} else if (filename.equals(".mid")) {
				str = "mid.gif";
			} else if (filename.equals(".wav")) {
				str = "wav.gif";
			} else if (filename.equals(".avi")) {
				str = "avi.gif";
			} else if (filename.equals(".rar")) {
				str = "rar.gif";
			} else if (filename.equals(".zip")) {
				str = "zip.gif";
			} else {
				str = "00.gif";
			}
			filename = filename.substring(filename.length() - 3, filename
					.length());
			if (filename.equals(".js")) {
				str = "js.gif";
			}
		}
		return str;
	}

	/**
	 * 单个文件拷贝(linux)
	 * 
	 * @param src
	 *            /tt/tkre.jsp 文件
	 * @param des
	 *            目录 /aa/
	 * @return
	 */
	public static boolean fileCopy(String src, String des) {
		File fileSrc = new File(src);
		if (!fileSrc.exists()) {
			return false;
		} else {
			if (fileSrc.length() <= 0) {
				return false;
			}
		}

		File filedes = new File(des);
		if (!filedes.isDirectory()) {
			return false;
		}

		try {
			String fileName = "";
			if (src.lastIndexOf("/") == -1) {
				fileName = src.substring(src.lastIndexOf("\\") + 1);
			} else {
				fileName = src.substring(src.lastIndexOf("/") + 1);
			}
			FileInputStream in = new FileInputStream(fileSrc);
			FileOutputStream out = new FileOutputStream(des + fileName);

			int rc = 0;
			while ((rc = in.read()) != -1) {
				out.write(rc);
			}
			out.flush();
			in.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 单个文件拷贝(任何系统下的)
	 * 
	 * @param src
	 *            文件
	 * @param des
	 *            目录
	 * @return
	 */
	public static boolean fileWinCopy(String src, String des) {

		File fileSrc = new File(src);
		if (!fileSrc.exists()) {
			return false;
		}
		File filedes = new File(des);
		if (!filedes.isDirectory()) {
			filedes.mkdirs();
		}

		try {
			String fileName = src.substring(src.lastIndexOf("/") + 1);
			if (src.lastIndexOf("\\") != -1) {
				fileName = src.substring(src.lastIndexOf("\\") + 1);
			}
			FileInputStream in = new FileInputStream(fileSrc);
			FileOutputStream out = new FileOutputStream(des + fileName);

			int rc = 0;
			while ((rc = in.read()) != -1) {
				out.write(rc);
			}
			out.flush();
			in.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 目录对目录的拷贝(只是文件拷贝,不管子目录)
	 * 
	 * @param src
	 *            文件
	 * @param des
	 *            目录
	 * @return
	 */
	public static boolean dirFileCopy(String srcDir, String desDir) {

		File fileSrc = new File(srcDir);
		if (!fileSrc.isDirectory()) {
			return false;
		}

		File filedes = new File(desDir);
		if (!filedes.isDirectory()) {
			return false;
		}

		File filestr[] = fileSrc.listFiles();
		for (int i = 0; i < filestr.length; i++) {
			if (filestr[i].isFile()) {
				fileWinCopy(filestr[i].getAbsolutePath(), desDir);
			}

		}
		return true;
	}

	/**
	 * 得到文件夹的全部路径
	 * 
	 * @param curpath
	 * @return
	 */
	public static String getCurFolderName(String curpath) {
		String foldername = "";
		if (curpath != null && curpath.lastIndexOf("/") > 0) {
			curpath = curpath.substring(0, curpath.lastIndexOf("/"));
			if (curpath.lastIndexOf("/") > 0) {
				foldername = curpath.substring(0, curpath.lastIndexOf("/") + 1);
			}
		}
		return foldername;
	}

	/**
	 * 
	 * @param filename
	 * @param GoToFileType
	 * @return
	 */
	public static int getHref(String filename, String GoToFileType) {
		String href = getFileType(filename);
		int hrefflag = 1;
		href = href.toLowerCase();
		if ("htm".equals(GoToFileType)) {
			if (href.equals("txt") || href.equals("jsp") || href.equals("asp")
					|| href.equals("doc") || href.equals("xml")
					|| href.equals("xsl") || href.equals("htm")
					|| href.equals("html") || href.equals("css")) {
				hrefflag = 1;
			} else {
				hrefflag = 0;
			}
		} else if ("img".equalsIgnoreCase(GoToFileType)) {
			if (href.equalsIgnoreCase("jpg") || href.equalsIgnoreCase("gif")
					|| href.equalsIgnoreCase("jpeg")
					|| href.equalsIgnoreCase("bmp")) {
				hrefflag = 1;
			} else {
				hrefflag = 0;
			}
		} else if ("av".equalsIgnoreCase(GoToFileType)) {
			if (href.equalsIgnoreCase("avi") || href.equalsIgnoreCase("asf")
					|| href.equalsIgnoreCase("rm")) {
				hrefflag = 1;
			} else {
				hrefflag = 0;
			}
		} else if ("flash".equalsIgnoreCase(GoToFileType)) {
			if (href.equalsIgnoreCase("swf")) {
				hrefflag = 1;
			} else {
				hrefflag = 0;
			}
		}
		return hrefflag;
	}

	/**
	 * 取得文件的类型
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFileType(String filename) {
		String filetype = "";
		if (filename != null && filename.lastIndexOf(".") > -1) {
			filetype = filename.substring(filename.lastIndexOf(".") + 1,
					filename.length());
		}
		return filetype.toUpperCase();
	}

	public static String[] run() {
		String str = "ddd";
		return str.split(",");
	}

	public static void main(String arg[]) {
		System.out.println(run()[0]);
	}
} // class
