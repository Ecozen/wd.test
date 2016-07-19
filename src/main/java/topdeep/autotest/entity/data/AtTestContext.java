package topdeep.autotest.entity.data;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;

public class AtTestContext {
	
	private URL url;
	private String version;
	private Platform platform;
	public URL createUrl(String url) throws MalformedURLException{
		this.url=new URL(url);
		return this.url;
	}
	public String getVersion() {
		return null;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	
	public Platform getPlatform() {
		return this.platform;
	}
	public void setPlatform(){
		this.platform = Platform.getCurrent();
	}

}
