package test;
/**
 * @author  ���� :lw E-mail:
 * @date ����ʱ�䣺2016��11��29�� ����11:10:46
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */

	
//	XML�ĵ�
//	
//
//	ʾ��һ
//	ʾ��һ����List�б�ķ�ʽ������xml
//	���ƴ��� ��������:

	import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

	public class XmlTools {
		@Test
	 public static void main(String[] args) throws Exception {
	  SAXReader reader = new SAXReader();
	  File file = new File("D:/workspaces-itcast/template/temelate_code/src/config/books.xml");
	  Document document = reader.read(file);
	  Element root = document.getRootElement();
	  List<Element> childElements = root.elements();
	  for (Element child : childElements) {
	   //δ֪�����������
	   /*List<Attribute> attributeList = child.attributes();
	   for (Attribute attr : attributeList) {
	    System.out.println(attr.getName() + ": " + attr.getValue());
	   }*/

	   //��֪�����������
	   System.out.println("id: " + child.attributeValue("id"));

	   //δ֪��Ԫ���������
	   /*List<Element> elementList = child.elements();
	   for (Element ele : elementList) {
	    System.out.println(ele.getName() + ": " + ele.getText());
	   }
	   System.out.println();*/

	   //��֪��Ԫ�����������
	   System.out.println("title" + child.elementText("title"));
	   System.out.println("author" + child.elementText("author"));
	   //������Ϊ�˸�ʽ�����۶�����
	   System.out.println();
	  }
	 }

	}

//	ʾ������ʹ��Iterator�������ķ�ʽ������xml
//	���ƴ��� ��������:

	

	 class Demo {
		 @Test
	 public static void main(String[] args) throws Exception {
	  SAXReader reader = new SAXReader();
	  Document document = reader.read(new File("/temelate_code/src/config/books.xml"));
	  Element root = document.getRootElement();

	  Iterator it = root.elementIterator();
	  while (it.hasNext()) {
	   Element element = (Element) it.next();

	   //δ֪�������������
	   /*Iterator attrIt = element.attributeIterator();
	   while (attrIt.hasNext()) {
	    Attribute a  = (Attribute) attrIt.next();
	    System.out.println(a.getValue());
	   }*/

	   //��֪�������������
	   System.out.println("id: " + element.attributeValue("id"));

	   //δ֪Ԫ���������
	   /*Iterator eleIt = element.elementIterator();
	   while (eleIt.hasNext()) {
	    Element e = (Element) eleIt.next();
	    System.out.println(e.getName() + ": " + e.getText());
	   }
	   System.out.println();*/

	   //��֪Ԫ���������
	   System.out.println("title: " + element.elementText("title"));
	   System.out.println("author: " + element.elementText("author"));
	   System.out.println();
	  }
	 }
	}

//	���н����
//
//	ʾ����������xml�ĵ���������ļ�
//	���ƴ��� ��������:

	 class Demo2 {
	 public static void main(String[] args) throws Exception {
	  Document doc = DocumentHelper.createDocument();
	  //���Ӹ��ڵ�
	  Element books = doc.addElement("books");
	  //������Ԫ��
	  Element book1 = books.addElement("book");
	  Element title1 = book1.addElement("title");
	  Element author1 = book1.addElement("author");

	  Element book2 = books.addElement("book");
	  Element title2 = book2.addElement("title");
	  Element author2 = book2.addElement("author");

	  //Ϊ�ӽڵ��������
	  book1.addAttribute("id", "001");
	  //ΪԪ���������
	  title1.setText("Harry Potter");
	  author1.setText("J K. Rowling");

	  book2.addAttribute("id", "002");
	  title2.setText("Learning XML");
	  author2.setText("Erik T. Ray");

	  //ʵ���������ʽ����
	  OutputFormat format = OutputFormat.createPrettyPrint();
	  //�����������
	  format.setEncoding("UTF-8");
	  //������Ҫд���File����
	  File file = new File("D:" + File.separator + "books.xml");
	  //����XMLWriter���󣬹��캯���еĲ���Ϊ��Ҫ������ļ����͸�ʽ
	  XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
	  //��ʼд�룬write�����а������洴����Document����
	  writer.write(doc);
	 }
	}





