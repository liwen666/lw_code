package mail.test1;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
public class SendmailUtil {    
        public static void main(String[] args) throws AddressException,            MessagingException, IOException {       
  Properties properties = new Properties();
//  properties.put("mail.transport.protocol", "smtp");// 连接协议        
//  properties.put("mail.smtp.host", "smtp.qq.com");// 主机名        
//  properties.put("mail.smtp.port", "587");// 端口号        
//  properties.put("mail.smtp.auth", "true");        
//  properties.put("mail.smtp.ssl.enable", "true");//设置是否使用ssl安全连接  ---一般都使用        
//  properties.put("mail.debug", "true");//设置是否显示debug信息  true 会在控制台显示相关信息    
        	
        	InputStream  in =	SendmailUtil.class.getClassLoader().getResourceAsStream("mail/test1/email.properties");
        	properties.load(in);
        	
//得到回话对象        
Session session = Session.getInstance(properties);        
// 获取邮件对象        
Message message = new MimeMessage(session);        
//设置发件人邮箱地址       
// message.setFrom(new InternetAddress("1438131288@qq.com"));       
// message.setFrom(new InternetAddress("liwen@tjhq.com"));       
 message.setFrom(new InternetAddress("18801266280@163.com"));       
 //设置收件人地址        
 
 message.setRecipients(                RecipientType.TO,                new InternetAddress[] { new InternetAddress("534093268@qq.com"), new InternetAddress("1438131288@qq.com")});       
 //设置邮件标题        
message.setSubject("这是第一封Java邮件");        
//设置邮件内容        
//message.setText("内容为： 这是第一封java发送来的邮件。");       
message.setText("java");       
 //得到邮差对象        
Transport transport = session.getTransport();        
//连接自己的邮箱账户        
//transport.connect("1438131288@qq.com", "ifbyqrpqshkvhdea");//密码为刚才得到的授权码       yryhfoqyfzndffbe  fjeelemrrehxjihc
//transport.connect("liwen@tjhq.com", "Lw-198728");//密码为刚才得到的授权码       yryhfoqyfzndffbe  fjeelemrrehxjihc
transport.connect("18801266280@163.com", "liwen198728");//密码为刚才得到的授权码       yryhfoqyfzndffbe  fjeelemrrehxjihc
//transport.connect("smtp.qq.com", "1438131288@qq.com",  "ifbyqrpqshkvhdea");
//发送邮件        
transport.sendMessage(message, message.getAllRecipients());    
}
 }