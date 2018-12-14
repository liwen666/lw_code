package commons.install.service.impl;

public class InstallContextHolder {
	private static ThreadLocal<InstallContext> installContextHolder = new ThreadLocal<InstallContext>();

	public static void setInstallContext(InstallContext installContext) {
		installContextHolder.set(installContext);
	}

	public static InstallContext getInstallContext() {
		return installContextHolder.get();
	}
}
