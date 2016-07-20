package topdeep.autotest.entity.data;

import org.openqa.selenium.Platform;

import topdeep.autotest.entity.constant.EnumType.BrowserType;

public class AtTestContext {
	
	private Platform platform;
	private String host;
	private String ip;
	private String port;
	private String protocol;
	private BrowserType browser;
	

	
	public AtTestContext(Platform platform, String host, String ip, String port, String protocol,BrowserType browser) {
		super();
		this.platform = platform;
		this.host = host;
		this.ip = ip;
		this.port = port;
		this.protocol = protocol;
		this.browser = browser;		
	}
	public AtTestContext(){
		
	}
	
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(){
		this.platform = Platform.getCurrent();
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public BrowserType getBrowser() {
		return browser;
	}
	public void setBrowser(BrowserType browser) {
		this.browser = browser;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	
}
