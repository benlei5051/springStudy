package httpclient;

/**
 * @author: andy
 * @Date: 2018/7/17 17:10
 * @Description:
 */
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GET请求示例
 *
 * @author 小明
 *
 */
public class GetDemo {

    public static void main(String[] args) {
        try {
            // 1. 得到访问地址的URL
            URL url = new URL(
                    "http://localhost:8080/Servlet/do_login.do?username=test&password=123456");
            // 2. 得到网络访问对象java.net.HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            /* 3. 设置请求参数（过期时间，输入、输出流、访问方式），以流的形式进行连接 */
            // 设置是否向HttpURLConnection输出
            connection.setDoOutput(false);
            // 设置是否从httpUrlConnection读入
            connection.setDoInput(true);
            // 设置请求方式
            connection.setRequestMethod("GET");
            // 设置是否使用缓存
            connection.setUseCaches(true);
            // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
            connection.setInstanceFollowRedirects(true);
            // 设置超时时间
            connection.setConnectTimeout(3000);
            // 连接
            connection.connect();
            // 4. 得到响应状态码的返回值 responseCode
            int code = connection.getResponseCode();
            // 5. 如果返回值正常，数据在网络中是以流的形式得到服务端返回的数据
            String msg = "";
            if (code == 200) { // 正常响应
                // 从流中读取响应信息
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = null;

                while ((line = reader.readLine()) != null) { // 循环从流中读取
                    msg += line + "\n";
                }
                reader.close(); // 关闭流
            }
            // 6. 断开连接，释放资源
            connection.disconnect();

            // 显示响应结果
            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//https://www.cnblogs.com/Garnett-Boy/p/8251561.html

    /**
     * 获取固定资产待办列表
     */

    public List<Map<String,Object>> GetOwnerWorkItemList(){
        String param="test";
        List<Map<String,Object>> OwnerWorkItemList = new ArrayList<>();
        String requestUrl = "http://172.16.80.65:8801/WorkItem/GetOwnerWorkItemList?param="+param;
        HttpURLConnection con = null;
        String result = null;
        try {
            URL url = new URL(requestUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(10000);
            con.setReadTimeout(2000);
            con.setDoOutput(false); // post改为true
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "text/plain");
            con.connect();
            int code = con.getResponseCode();
            if (code == 200) {
                // 读取返回内容
                StringBuffer buffer = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String temp;
                while ((temp = br.readLine()) != null) {
                    buffer.append(temp);
                    buffer.append("\n");
                }
                result = buffer.toString().trim();
                result = result.substring(0, result.length()-1);
                result = result.substring(1);
                result = StringEscapeUtils.unescapeJava(result);

                JSONObject jsonObject = JSONObject.fromObject(result);
                Map<String,Object> map = new HashMap<>();
                map.put("count",jsonObject.get("Count").toString());
                map.put("url",jsonObject.get("URL").toString());
                OwnerWorkItemList.add(map);
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        con.getErrorStream(), "UTF-8"));
                StringBuffer buffer = new StringBuffer();
                String temp;
                while ((temp = br.readLine()) != null) {
                    buffer.append(temp);
                    buffer.append("\n");
                }
            }
        } catch (Exception e) {

        } finally {
            con.disconnect();
        }
        return OwnerWorkItemList;
    }

}
