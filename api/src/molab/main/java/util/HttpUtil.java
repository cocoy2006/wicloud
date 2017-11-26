package molab.main.java.util;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	public static String post(String url, List<NameValuePair> nvps)
			throws ClientProtocolException, IOException {

		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost post = new HttpPost(url);
		post.setEntity(new UrlEncodedFormEntity(nvps));

		CloseableHttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		String json = EntityUtils.toString(entity, "utf-8");
		EntityUtils.consume(entity);

		response.close();
		post.releaseConnection();
		client.close();

		return json;
	}

	public static String get(String url) 
			throws ClientProtocolException, IOException {

		CloseableHttpClient client = HttpClients.createDefault();

		HttpGet get = new HttpGet(url);

		CloseableHttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String json = EntityUtils.toString(entity, "utf-8");
		EntityUtils.consume(entity);

		response.close();
		get.releaseConnection();
		client.close();

		return json;
	}

}
