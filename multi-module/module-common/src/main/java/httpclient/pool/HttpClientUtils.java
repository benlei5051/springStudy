package httpclient.pool;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/23 15:31
 * @version: V1.0
 */

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import util.JsonUtils;

import javax.net.ssl.SSLException;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HttpClientUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    /**
     * 监控连接间隔
     */
    public static final long RELEASE_CONNECTION_WAIT_TIME = 5000;
    private static PoolingHttpClientConnectionManager httpClientConnectionManager = null;
    private static LaxRedirectStrategy redirectStrategy = null;
    private static HttpRequestRetryHandler myRetryHandler = null;
    private static SSLConnectionSocketFactory sslConnectionSocketFactory = null;
    private static MyResponseHandler rh = null;

    static {
        initHttpClient();
        // 启动清理连接池链接线程
        Thread idleConnectionMonitorThread = new IdleConnectionMonitorThread(httpClientConnectionManager);
        //主线程执行完毕，守护线程不再执行
        idleConnectionMonitorThread.setDaemon(true);
        idleConnectionMonitorThread.start();
    }

    public static void initHttpClient() {
        try {
            rh = new MyResponseHandler();
            // 重定向策略初始化
            redirectStrategy = new LaxRedirectStrategy();
            // 请求重试机制，默认重试3次
            myRetryHandler = new HttpRequestRetryHandler() {
                @Override
                public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                    if (executionCount >= 3) {
                        return false;
                    }
                    if (exception instanceof InterruptedIOException) {
                        return false;
                    }
                    if (exception instanceof UnknownHostException) {
                        return false;
                    }
                    if (exception instanceof ConnectTimeoutException) {
                        return false;
                    }
                    if (exception instanceof SSLException) {
                        // SSL handshake exception
                        return false;
                    }
                    HttpClientContext clientContext = HttpClientContext.adapt(context);
                    HttpRequest request = clientContext.getRequest();
                    boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                    if (idempotent) {
                        // 如果请求是幂等的，就再次尝试
                        // Retry if the request is considered idempotent
                        return true;
                    }
                    return false;
                }
            };
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", new PlainConnectionSocketFactory())
                    .register("https", sslConnectionSocketFactory)
                    .build();
            // 创建httpclient连接池
            httpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);
            // 设置连接池最大数量,这个参数表示所有连接最大数。
            httpClientConnectionManager.setMaxTotal(200);
            // 设置单个路由最大连接数量，表示单个域名的最大连接数，
            // 例如:www.baidu.com.www.google.com表示不同的域名,则连接统一域名下的资源的最大连接数就是该参数,总和是上面的参数。
            httpClientConnectionManager.setDefaultMaxPerRoute(100);
        } catch (Exception e) {
            logger.error("初始化httpclient连接池失败.", e);
        }

    }

    public static CloseableHttpClient getHttpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                // ms毫秒,建立链接超时时间
                .setConnectTimeout(3000)
                // ms毫秒,读取超时时间
                // 指的是连接上一个url，获取response的返回读取的等待时间(数据传输处理时间)
                .setSocketTimeout(3000)
                // 忽略cookie,如果不需要登陆最好去掉,否则修改策略保存cookie即可
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                // ms毫秒,从池中获取链接超时时间
                .setConnectionRequestTimeout(6000)
                // .setProxy(ip)//设置代理ip,不设置就用本机
                .build();
        // 连接池配置
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setConnectionManager(httpClientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setRedirectStrategy(redirectStrategy)
                .setRetryHandler(myRetryHandler)
                .build();

        return httpClient;
    }

    /**
     * get请求 headers表示特殊的请求头
     */
    public static String getUrlContent(String urlString, Map<String, String>... headers) {
        String html = "";
        HttpGet httpGet = null;
        urlString = urlString.trim();
        if (null == urlString || urlString.isEmpty() || !urlString.startsWith("http")) {
            return html;
        }
        // 转化String url为URI,解决url中包含特殊字符的情况
        try {
            URL url = new URL(urlString);
            URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
            httpGet = new HttpGet(uri);
            setCommonHeaders(httpGet);
            // 额外的header
            if (headers != null && headers.length > 0) {
                for (Map.Entry<String, String> header : headers[0].entrySet()) {
                    if (httpGet.containsHeader(header.getKey())) {
                        httpGet.setHeader(header.getKey(), header.getValue());
                    } else {
                        httpGet.addHeader(header.getKey(), header.getValue());
                    }
                }
            }
            HttpClient httpClient = getHttpClient();
            html = httpClient.execute(httpGet, rh);
        } catch (Exception e) {
            logger.error("请求错误·url=" + urlString);
        } finally {
            httpGet.abort();
        }
        return html;
    }

    /**
     * post请求,params表示参数,headers表示请求头
     */
    public static String postForEntity(String urlString, Map<String, String> params, Map<String, String>... headers) {
        String html = "";
        HttpPost httpPost = null;
        urlString = urlString.trim();
        try {
            // 参数设置
            if (null == urlString || urlString.isEmpty() || !urlString.startsWith("http")) {
                return html;
            }
            URL url = new URL(urlString);
            URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
            httpPost = new HttpPost(uri);
            setCommonHeaders(httpPost);
            // 额外的header
            if (headers != null && headers.length > 0) {
                for (Map.Entry<String, String> header : headers[0].entrySet()) {
                    if (httpPost.containsHeader(header.getKey())) {
                        httpPost.setHeader(header.getKey(), header.getValue());
                    } else {
                        httpPost.addHeader(header.getKey(), header.getValue());
                    }
                }
            }
            if (params != null && params.size() > 0) {
                List<BasicNameValuePair> nvps = new ArrayList<>();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                // 这里设置的是返回结果编码,大多数都是UTF-8,如果乱码,可以查看网页的meta中的content的编码.如果是GBK,这里改为GBK即可.
                // 这里entity只能读取一次,想要读取多次,百度一下.
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }
            HttpClient httpClient = getHttpClient();
            html = httpClient.execute(httpPost, rh);
        } catch (Exception e) {
            logger.error("请求错误·url=" + urlString);
        } finally {
            //httpPost.abort();//中断请求,接下来可以开始另一段请求,所以个人理应,用这个应该可以在session中虚拟登录
            //httpPost.releaseConnection();//释放请求.如果释放了相当于要清空session
            httpPost.abort();
        }
        return html;

    }

    private static void setCommonHeaders(AbstractHttpMessage request) {
        request.addHeader("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        // request.addHeader("Connection", "keep-alive");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
        request.addHeader("Accept-Encoding", "gzip, deflate, br");
        // User-Agent最好随机换
        request.addHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
    }

    /**
     * 响应处理类,处理返回结果
     *
     * @author 王
     *
     */
    public static class MyResponseHandler implements ResponseHandler<String> {

        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            try {
                // 返回状态码
                int statusCode = response.getStatusLine().getStatusCode();
                switch (statusCode) {
                    case HttpStatus.SC_OK:
                        return EntityUtils.toString(response.getEntity(), "UTF-8");
                    case HttpStatus.SC_BAD_REQUEST:
                        logger.error("下载400错误代码，请求出现语法错误.");
                        break;
                    case 403:
                        logger.error("下载403错误代码，资源不可用.");
                        break;
                    case 404:
                        logger.error("下载404错误代码，无法找到指定资源地址.");
                        break;
                    case 503:
                        logger.error("下载503错误代码，服务不可用.");
                        break;
                    case 504:
                        logger.error("下载504错误代码，网关超时.");
                        break;
                    default:
                        logger.error("未处理的错误,code=" + statusCode);
                }

            } finally {
                if (response != null) {
                    try {
                        ((CloseableHttpResponse) response).close();
                    } catch (IOException e) {
                        logger.error("关闭响应错误.", e);
                    }
                }
            }
            return "";
        }

    }

    /**
     * 连接处理,释放连接池连接
     *
     * @author 王
     *
     */
    public static class IdleConnectionMonitorThread extends Thread {

        private final HttpClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
            super();
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        //wait()不传时间时，只有notify才能唤醒，如果传时间，等待多少秒自动唤醒
                        wait(RELEASE_CONNECTION_WAIT_TIME);
                        // Close expired connections
                        connMgr.closeExpiredConnections();
                        // Optionally, close connections
                        // that have been idle longer than 30 sec
                        connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException ex) {
                // terminate
            }
        }

        public void shutdown() {
            System.out.println("--------------------");
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }

    public static  String sendPostForFileByHttpClient(String url, MultipartFile file) {
        CloseableHttpClient httpClient = getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        HttpResponse response = null;
        String beanStr = null;
        String fileName = file.getOriginalFilename();
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            // 文件流
            builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);
            //类似浏览器表单提交，对应input的name和value
            builder.addTextBody("filename", fileName);
            HttpEntity builderEntity = builder.build();
            httpPost.setEntity(builderEntity);
            response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK || status == HttpStatus.SC_CREATED) {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    beanStr = EntityUtils.toString(entity, "UTF-8");
                    if (StringUtils.isNotBlank(beanStr)) {
                        Map<String, Object> map = JsonUtils.json2Object(beanStr, Map.class);
                        beanStr = (String) map.get("result");
                    }
                }
                EntityUtils.consume(entity);
            } else {
                logger.error("链接 " + url + " 异常,HTTP请求失败[httpStatus=" + status + "]，fileName:" + fileName);
            }
        } catch (Exception e) {
            logger.error("发起 " + url + " 请求异常，fileName:" + fileName, e);
        } finally {
            httpPost.abort();
        }
        return beanStr;
    }

    /**
     * 发送 http post 请求，支持文件上传
     */
    public static void httpPostFormMultipart(String url,Map<String,String> params, List<File> files,Map<String,String> headers,String encode){
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);

        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpost.setHeader(entry.getKey(),entry.getValue());
            }
        }
        MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
        mEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        mEntityBuilder.setCharset(Charset.forName(encode));

        // 普通参数//解决中文乱码
        ContentType contentType = ContentType.create("text/plain",Charset.forName(encode));
        if (params != null && params.size() > 0) {
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                mEntityBuilder.addTextBody(key, params.get(key),contentType);
            }
        }
        //二进制参数
        if (files != null && files.size() > 0) {
            for (File file : files) {
                mEntityBuilder.addBinaryBody("file", file);
            }
        }
        httpost.setEntity(mEntityBuilder.build());
        String content;
        CloseableHttpResponse  httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpost);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            httpResponse.getAllHeaders();
            httpResponse.getStatusLine().getReasonPhrase();
            httpResponse.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    /**
     * 文件上传
     */
    public void upload() {
        CloseableHttpClient httpclient = getHttpClient();
        HttpPost httppost = null;
        try {
            httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceFile.action");

            FileBody bin = new FileBody(new File("F:\\image\\sendpix0.jpg"));
            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();
            httppost.setEntity(reqEntity);
            logger.info("Executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                logger.info("----------------------------------------");
                logger.info(response.getStatusLine()+"");
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //httpPost.abort();//中断请求,接下来可以开始另一段请求,所以个人理应,用这个应该可以在session中虚拟登录
            //httpPost.releaseConnection();//释放请求.如果释放了相当于要清空session
            httppost.abort();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(getUrlContent("http://localhost:8081/test"));
        Thread.sleep(80000);
        //   System.out.println(getUrlContent("https://www.baidu.com"));
    }

}

