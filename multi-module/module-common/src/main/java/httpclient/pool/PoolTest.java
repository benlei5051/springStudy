package httpclient.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/25 9:23
 * @version: V1.0
 */


public class PoolTest {

    private static void config(HttpRequestBase httpRequestBase) {

        httpRequestBase.setHeader("User-Agent", "Mozilla/5.0");

        httpRequestBase.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        //"en-US,en;q=0.5");
        httpRequestBase.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");

        httpRequestBase.setHeader("Accept-Charset", "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");

    }

    public static void main(String[] args) {

        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();

        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()

                .register("http", plainsf)

                .register("https", sslsf)

                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);

        // 将最大连接数增加到200

        cm.setMaxTotal(200);

        // 将每个路由基础的连接增加到20

        cm.setDefaultMaxPerRoute(20);

        // 将目标主机的最大连接数增加到50

        HttpHost localhost = new HttpHost("http://blog.csdn.net/gaolu", 80);

        cm.setMaxPerRoute(new HttpRoute(localhost), 50);


        //请求重试处理

        HttpRequestRetryHandler httpRequestRetryHandler = (exception, executionCount, context) -> {
            // 如果已经重试了5次，就放弃

            if (executionCount >= 5) {

                return false;

            }
            // 如果服务器丢掉了连接，那么就重试
            if (exception instanceof NoHttpResponseException) {

                return true;

            }
            // 不要重试SSL握手异常
            if (exception instanceof SSLHandshakeException) {

                return false;

            }
            // 超时
            if (exception instanceof InterruptedIOException) {

                return false;

            }
            // 目标服务器不可达
            if (exception instanceof UnknownHostException) {

                return false;

            }
            // 连接被拒绝
            if (exception instanceof ConnectTimeoutException) {

                return false;

            }
            // ssl握手异常
            if (exception instanceof SSLException) {

                return false;

            }


            HttpClientContext clientContext = HttpClientContext.adapt(context);

            HttpRequest request = clientContext.getRequest();

            // 如果请求是幂等的，就再次尝试

            if (!(request instanceof HttpEntityEnclosingRequest)) {

                return true;

            }

            return false;

        };

        // 配置请求的超时设置

        RequestConfig requestConfig = RequestConfig.custom()

                .setConnectionRequestTimeout(3000)

                .setConnectTimeout(3000)

                .setSocketTimeout(3000)

                .build();


        CloseableHttpClient httpClient = HttpClients.custom()

                .setConnectionManager(cm)

                .setRetryHandler(httpRequestRetryHandler)

                .setDefaultRequestConfig(requestConfig)

                .build();

        // URL列表数组

        String[] urisToGet = {

                "http://blog.csdn.net/gaolu/article/details/48466059",

                "http://blog.csdn.net/gaolu/article/details/48243103",

                "http://blog.csdn.net/gaolu/article/details/47656987",

                "http://blog.csdn.net/gaolu/article/details/47055029",

                "http://blog.csdn.net/gaolu/article/details/46400883",

                "http://blog.csdn.net/gaolu/article/details/46359127",

                "http://blog.csdn.net/gaolu/article/details/46224821",

                "http://blog.csdn.net/gaolu/article/details/45305769",

                "http://blog.csdn.net/gaolu/article/details/43701763",

                "http://blog.csdn.net/gaolu/article/details/43195449",

                "http://blog.csdn.net/gaolu/article/details/42915521",

                "http://blog.csdn.net/gaolu/article/details/41802319",

                "http://blog.csdn.net/gaolu/article/details/41045233",

                "http://blog.csdn.net/gaolu/article/details/40395425",

                "http://blog.csdn.net/gaolu/article/details/40047065",

                "http://blog.csdn.net/gaolu/article/details/39891877",

                "http://blog.csdn.net/gaolu/article/details/39499073",

                "http://blog.csdn.net/gaolu/article/details/39314327",

                "http://blog.csdn.net/gaolu/article/details/38820809",

                "http://blog.csdn.net/gaolu/article/details/38439375",

        };

        long start = System.currentTimeMillis();

        try {

            int pagecount = urisToGet.length;
            ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                    .setNameFormat("demo-pool-%d").build();

            //Common Thread Pool
            ExecutorService pool = new ThreadPoolExecutor(pagecount, 200,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

//            pool.execute(()-> System.out.println(Thread.currentThread().getName()));
//            pool.shutdown();//gracefully shutdown
//            ExecutorService executors = Executors.newFixedThreadPool(pagecount);

            CountDownLatch countDownLatch = new CountDownLatch(pagecount);

            for (int i = 0; i < pagecount; i++) {

                HttpGet httpget = new HttpGet(urisToGet[i]);

                //未每一个http请求添加header信息
                config(httpget);

                //启动线程抓取
                pool.execute(new GetRunnable(httpClient, httpget, countDownLatch));

            }

            countDownLatch.await();

            pool.shutdown();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            System.out.println("线程" + Thread.currentThread().getName() + "," + System.currentTimeMillis() + ", 所有线程已完成，开始进入下一步！");

        }

        long end = System.currentTimeMillis();

        System.out.println("consume -> " + (end - start)/1000);

    }


    static class GetRunnable implements Runnable {

        private CountDownLatch countDownLatch;

        private final CloseableHttpClient httpClient;

        private final HttpGet httpget;


        public GetRunnable(CloseableHttpClient httpClient, HttpGet httpget, CountDownLatch countDownLatch) {

            this.httpClient = httpClient;

            this.httpget = httpget;

            this.countDownLatch = countDownLatch;

        }

        @Override

        public void run() {

            CloseableHttpResponse response = null;

            try {
                System.out.println("-------当前线程名: " + Thread.currentThread().getName());

                response = httpClient.execute(httpget, HttpClientContext.create());

                HttpEntity entity = response.getEntity();

                EntityUtils.toString(entity, "utf-8");

                EntityUtils.consume(entity);

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                countDownLatch.countDown();

                try {

                    if (response != null) {
                        response.close();
                    }

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }

    }

}

