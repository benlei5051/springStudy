package httpclient.offial;

/**
 * @author: andy
 * @Date: 2018/7/17 17:16
 * @Description:
 */

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpGetDemo {
    public static void main(String[] args) throws Exception {
        // 1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(
                    "http://localhost:8080/Servlet/do_login.do?username=test&password=123456");
            CloseableHttpResponse response1 = httpClient.execute(httpGet);
            try {
                System.out.println(response1.getStatusLine());
                // 4. 获取响应实体
                HttpEntity entity = response1.getEntity();
                // 5. 处理响应实体
                if (entity != null) {
                    System.out.println("长度：" + entity.getContentLength());
//                   However, the use of EntityUtils is strongly discouraged unless the response entities originate from a trusted HTTP server and are known to be of limited length.
                    System.out.println("内容：" + EntityUtils.toString(entity));
                }
                EntityUtils.consume(entity);
            } finally {
                response1.close();
            }
        } finally {
            httpClient.close();
        }
    }
}
