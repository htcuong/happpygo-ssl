# happygo-ssl
## Step 1: importing server.cer
Execute below command line for importing key into keystore.jks
```
$ keytool -importcert -file <path-of-file-cer> -keystore keystore.jks -alias "<alias-of-cer>"
$ Enter keystore password: <password>
$ Re-enter new password: <password>
```
* <path-of-file-cer> : server.cer
* <alias-of-cer>: server
* <password>: changeit

## Step 2: checking keystore.jks
```
$ keytool -list
```

## Step 3: adding code for load cer key before request to HappyGo APIs
```
System.setProperty("javax.net.ssl.trustStore", "keystore.jks");
System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
System.setProperty("javax.net.ssl.trustStoreType", "JKS");
```
After added

```
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
```

## Step 4: checking
```
$ javac HttpClientSample.java
$ java HttpClientSample
```
