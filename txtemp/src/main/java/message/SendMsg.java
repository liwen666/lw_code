package message;  
  
import org.apache.commons.httpclient.Header;  
import org.apache.commons.httpclient.HttpClient;  
import org.apache.commons.httpclient.NameValuePair;  
import org.apache.commons.httpclient.methods.PostMethod;  
/**  
 * 测试发送短信  
 *   
 * 
 */  
public class SendMsg {  
    public static void main(String[] args) throws Exception {  
        HttpClient client = new HttpClient();  
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");  
        post.addRequestHeader("Content-Type",  
                "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码  
        NameValuePair[] data = { new NameValuePair("Uid", "liwen1438131288"),  
                new NameValuePair("Key", "d41d8cd98f00b204e980"),  
                new NameValuePair("smsMob", "15711109230"),  
                new NameValuePair("smsText", "你注短息系统：手机验证码：12451597474") };  
        post.setRequestBody(data);  
  
        client.executeMethod(post);  
        Header[] headers = post.getResponseHeaders();  
        int statusCode = post.getStatusCode();  
        System.out.println("statusCode:" + statusCode);  
        for (Header h : headers) {  
            System.out.println(h.toString());  
        }  
        String result = new String(post.getResponseBodyAsString().getBytes(  
                "gbk"));  
        System.out.println(result);  
  
        post.releaseConnection();  
    }  
}  