package topdeep.autotest.entity.data;

import java.net.MalformedURLException;
import java.net.URL;

public class AtTestContext {
	private URL url;
	public URL getUrl(String url) throws MalformedURLException{
		this.url=new URL(url);
		return this.url;
	}
	
}
