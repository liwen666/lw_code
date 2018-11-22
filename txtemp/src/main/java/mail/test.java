package mail;

public class test {
	 public static void main(String[] args) {
			System.out.println(PropertyUtil.getProps());
			System.out.println(PropertyUtil.getProperty("mail.debug"));
		}
}
