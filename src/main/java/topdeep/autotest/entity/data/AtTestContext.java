package topdeep.autotest.entity.data;

import org.openqa.selenium.Platform;

import topdeep.autotest.entity.constant.EnumType.BrowserType;

public class AtTestContext {
	

	private Platform platform;
	private String host;
	private String ip;
	private String port;
	private String protocol;
	


	

	
	public AtTestContext(Platform platform, String host, String ip, String port, String protocol) {
		super();
		this.platform = platform;
		this.host = host;
		this.ip = ip;
		this.port = port;
		this.protocol = protocol;
		BrowserType.valueOf("1");
		
	}
	public Platform getPlatform() {
		return this.platform;
	}
	public void setPlatform(){
		this.platform = Platform.getCurrent();
	}

}
