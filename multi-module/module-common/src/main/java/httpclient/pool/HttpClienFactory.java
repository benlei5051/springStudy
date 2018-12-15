/*------------------------------------------------------------------------------
 * HttpClientFactory
 * @Description: 通过连接池创建httpclien，定时关闭空闲的连接，有get和post（未测试）请求方式。
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/13 17:16
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package httpclient.pool;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class HttpClienFactory {
    private static PoolingHttpClientConnectionManager connectionManager;
    /**
     * 请求配置
     */
    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
            .setConnectTimeout(50000)
            .setConnectionRequestTimeout(50000)
            .setSocketTimeout(50000)
            .setCircularRedirectsAllowed(true)
            .build();
    /**
     * 创建连接池
     *
     * @return
     */
    private static synchronized PoolingHttpClientConnectionManager getConnectionManager() {
        if (connectionManager == null) {
            connectionManager = new PoolingHttpClientConnectionManager();
            initParam();
        }
        return connectionManager;
    }

    /**
     * 初始化连接池参数
     */
    private static void initParam() {
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(50000)
                .build();
        connectionManager.setDefaultSocketConfig(socketConfig);
        // 设置最大连接数
        connectionManager.setMaxTotal(10000);
        // 每个路由基础连接数
        connectionManager.setDefaultMaxPerRoute(1000);
        //TODO (还有一些方法自己去看)
        // 定时关掉连接池里面空闲或者过去的连接
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // 关闭失效的连接
                connectionManager.closeExpiredConnections();
                // 可选的, 关闭30秒内不活动的连接
                connectionManager.closeIdleConnections(60, TimeUnit.SECONDS);
            }
        }, 1000 * 120, 1000 * 120);
    }

    /**
     * 获取httpclient
     *
     * @return
     */
    public static HttpClient getHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(getConnectionManager())
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .build();
    }
    /**
     * 获取httpGet响应
     * @param url
     * @return
     * @throws Exception
     */
    public static HttpResponse doGet(String url) throws Exception{
        HttpResponse response = null;
        HttpClient httpClient = getHttpClient();
        HttpGet get = getHttpGet(url);
        response = httpClient.execute(get);
        return response;
    }
    /**
     * 获取httpGet
     * @param urlString
     * @return
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public static HttpGet getHttpGet(String urlString) throws MalformedURLException, URISyntaxException{
        URL url = new URL(urlString);
        URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
        HttpGet get =  new HttpGet(uri);
        get.setConfig(REQUEST_CONFIG);
        return get;

    }
    /**
     * 获取httpPost响应
     * @param url
     * @return
     * @throws Exception
     * 如果使用HttpPost方法提交HTTP POST请求，则需要使用HttpPost类的setEntity方法设置请求参数。参数则必须用NameValuePair[]数组存储。
     */
    public static HttpResponse doPost(String url,Map<String, Object>map) throws Exception{
        HttpResponse response = null;
        HttpClient httpClient = getHttpClient();
        HttpPost post = getHttpPost(url);
        List <NameValuePair> params = new ArrayList<NameValuePair>();
        for(String key:map.keySet()){
            params.add(new BasicNameValuePair(key, (String) map.get(key)));
        }
        post.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
        response = httpClient.execute(post);
        return response;
    }
    /**
     * 获取httpGet
     * @param urlString
     * @return
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public static HttpPost getHttpPost(String urlString) throws MalformedURLException, URISyntaxException{
        URL url = new URL(urlString);
        URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
        HttpPost post=new HttpPost(uri);
        post.setConfig(REQUEST_CONFIG);
        return post;

    }
}
