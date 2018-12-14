package commons.login;

public class LoginContextHolder {
  private static ThreadLocal loginContextHolder = new ThreadLocal();

  public static void setLoginContext(LoginContext loginContext) {
    loginContextHolder.set(loginContext);
  }

  public static LoginContext getLoginContext() {
    return (LoginContext) loginContextHolder.get();
  }
}