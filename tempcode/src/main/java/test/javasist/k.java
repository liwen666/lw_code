package test.javasist;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;




/**
 * 对class文件进行编辑
 */

public class k {
	public static void main(String[] args) throws Exception {

    //这个是得到反编译的池

   ClassPool pool = ClassPool.getDefault();

   //取得需要反编译的jar文件，设定路径

   pool.insertClassPath("E:""crack""chart.ext.jar");

   //取得需要反编译修改的文件，注意是完整路径

   CtClass cc1 = pool.get("com.objectplanet.chart.a");

   try {

       //取得需要修改的方法

       CtMethod method = cc1.getDeclaredMethod("a");

      //插入修改项，我们让他直接返回(注意：根据方法的具体返回值返回，因为这个方法返回值是void，所以直接return；)

       method.insertBefore("{if(true) return ;}");

       //写入保存

       cc1.writeFile();

   } catch (NotFoundException e) {

       e.printStackTrace();

  }

}

} 