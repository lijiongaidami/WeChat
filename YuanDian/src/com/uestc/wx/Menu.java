package com.uestc.wx;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Menu {

	String menuString = "{\"button\":[{\"type\":\"view\", \"name\":\"客户端\", \"url\":\"http://121.40.175.6/YuanDianZone/店铺主页-圆点水果.html\"},{\"type\":\"view\", \"name\":\"商家端\", \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxee73b33840cfd09b&redirect_uri=http%3A%2F%2Fwww.yuandianzone.com%2FYuanDianZone%2FOpenID&response_type=code&scope=snsapi_base&state=ok#wechat_redirect\"}, {\"type\":\"view\", \"name\":\"配送端\", \"url\":\"http://www.baidu.com\"}]}";
//	String menuString = "{\"button\":[{\"type\":\"view\", \"name\":\"客户端\", \"url\":\"http://121.40.175.6/YuanDianZone/%E5%BA%97%E9%93%BA%E4%B8%BB%E9%A1%B5-%E5%9C%86%E7%82%B9%E6%B0%B4%E6%9E%9C.html\"},{\"type\":\"view\", \"name\":\"商家端\", \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=http%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect\"}, {\"type\":\"view\", \"name\":\"配送端\", \"url\":\"http://www.baidu.com\"}]}";
	String accessToken;

	public Menu(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setMenuString(String menuString) {
		this.menuString = menuString;
	}

	/**
	 * 向微信服务器发起创建菜单的请求
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void createMenu() throws URISyntaxException, ClientProtocolException, IOException {
		HttpClient httpClient = HttpClients.createDefault();
		URI uri = new URIBuilder().setScheme("https").setHost("api.weixin.qq.com")
				.setPath("/cgi-bin/menu/create").setParameter("access_token", this.accessToken).build();
		HttpPost httpPost = new HttpPost(uri);
		StringEntity stringEntity = new StringEntity(this.menuString,"UTF-8");
		stringEntity.setContentEncoding("UTF-8");
		httpPost.setEntity(stringEntity);
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity httpEntity = response.getEntity();
		httpEntity = new BufferedHttpEntity(httpEntity);
		String jsonString = EntityUtils.toString(httpEntity);
		System.out.println(jsonString);
	}

	public static void main(String[] args) {
		String accessToken = "eEPvtlWlz3GpSiivsh4uxmDBmUXSNobOqQrTkEBAwxVMIaaKztRUfhs8VkLwFw6iei2bxNaWJcmcLsOgjFrJb1klJwMJN_p2UjDcA4TZ9SM";
		Menu menu = new Menu(accessToken);
		try {
			menu.createMenu();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
