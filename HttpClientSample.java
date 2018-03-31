import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClientSample {
	public static void main(String[] args) throws ClientProtocolException, IOException  {
		//Set key into trustStore
	    System.setProperty("javax.net.ssl.trustStore", "keystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("https://www.st.happygocard.com.tw/SamsungPay/auth/eligibility");
		HttpResponse httpReponse = client.execute(httpGet);
		String reponseString = EntityUtils.toString(httpReponse.getEntity());
		if (httpReponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			System.out.println(reponseString);
		}else {
			System.out.println(reponseString);
		}
		
		httpGet.releaseConnection();
		
	}
}