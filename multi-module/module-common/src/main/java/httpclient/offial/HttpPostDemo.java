package httpclient.offial;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: andy
 * @Date: 2018/7/17 17:20
 * @Description:
 */
public class HttpPostDemo {
    public static void main(String[] args) throws Exception {
        // 1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 2. 创建HttpPost对象
            HttpPost post = new HttpPost(
                    "http://www.baidu.com");
            // 3. 设置POST请求传递参数
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", "test"));
            params.add(new BasicNameValuePair("password", "12356"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
            post.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(post);
            // 4. 执行请求并处理响应
           /* try {
                HttpEntity entity2 = response.getEntity();
                if (entity2 != null) {
                    System.out.println("响应内容：");
                    System.out.println(EntityUtils.toString(entity2));
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }*/
            try {
                HttpEntity entity2 = response.getEntity();
                StringBuilder sb = new StringBuilder();
                if (entity2 != null) {
                    InputStream inputStream = entity2.getContent();
                    try {
                        byte[] cs = new byte[1024];
                        int len;
                        while ((len = inputStream.read(cs)) != -1) {
                            sb.append(new String(cs, 0, len)).append("\n");
                        }
                    } finally {
                        inputStream.close();
                    }
                }
                String s = StringEscapeUtils.unescapeJava(sb.toString());
                System.out.println(s);
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }

    public static String httpPostclientUtil(String jsonObj, String url) {
        boolean isSuccess = false;
        String result = "";
        HttpPost post = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();

            // 设置超时时间
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

            post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Connection", "Close");
            String sessionId = getSessionId();
            post.setHeader("SessionId", sessionId);

            // 构建消息实体
            StringEntity entity = new StringEntity(jsonObj, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);

            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                //5.获取HttpEntity消息载体对象  可以接收和发送消息
                HttpEntity entity1 = response.getEntity();
                //EntityUtils中的toString()方法转换服务器的响应数据
                result = EntityUtils.toString(entity1, "utf-8");
                //System.out.println("服务器的响应是:"+str);

//              //6.从消息载体对象中获取操作的读取流对象
//              InputStream input=entity.getContent();
//              BufferedReader br=new BufferedReader(new InputStreamReader(input));
//              String str=br.readLine();
//              String result=new String(str.getBytes("gbk"), "utf-8");
//              System.out.println("服务器的响应是:"+result);
//              br.close();
//              input.close();
            } else {
                JSONObject obj = new JSONObject();
                obj.put("result", "failure");
                result = obj.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        } finally {
            if (post != null) {
                try {
                    post.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    // 构建唯一会话Id
    public static String getSessionId() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }
}
