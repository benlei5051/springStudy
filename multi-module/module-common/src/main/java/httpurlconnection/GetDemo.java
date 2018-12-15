package httpurlconnection;

/**
 * @author: andy
 * @Date: 2018/7/17 17:10
 * @Description:
 */

import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
                    "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb2ebe42765aad029&secret=720661590f720b1f501ab3f390f80d52");
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
                msg = StringEscapeUtils.unescapeJava(msg);
                reader.close(); // 关闭流
            } /*else {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        connection.getErrorStream(), "UTF-8"));
                StringBuffer buffer = new StringBuffer();
                String temp;
                while ((temp = br.readLine()) != null) {
                    buffer.append(temp);
                    buffer.append("\n");
                }
            }*/
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

    public List<Map<String, Object>> GetOwnerWorkItemList() {
        String param = "test";
        List<Map<String, Object>> OwnerWorkItemList = new ArrayList<>();
        String requestUrl = "http://172.16.80.65:8801/WorkItem/GetOwnerWorkItemList?param=" + param;
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
                result = result.substring(0, result.length() - 1);
                result = result.substring(1);
                result = StringEscapeUtils.unescapeJava(result);

                JSONObject jsonObject = JSONObject.fromObject(result);
                Map<String, Object> map = new HashMap<>();
                map.put("count", jsonObject.get("Count").toString());
                map.put("url", jsonObject.get("URL").toString());
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

    public static String executeGet(String url) throws Exception {
        URL u = null;
        HttpURLConnection con = null;
        BufferedReader br = null;
        InputStreamReader ir = null;
        // Attempt to send a request
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(6000);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Accept-Language", Locale.getDefault().toString());
            con.setRequestProperty("Accept-Charset", "UTF-8");
        } catch (Exception e) {
            throw new Exception("Send POST request failed" + e);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        try {
            int resCode = con.getResponseCode();
            StringBuilder buffer = new StringBuilder();
            if (resCode == 200) {
                // Read back contents
                ir = new InputStreamReader(con.getInputStream(), "UTF-8");
            } else {
                ir = new InputStreamReader(con.getErrorStream(), "UTF-8");
            }
            br = new BufferedReader(ir);
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
            }
            return buffer.toString();
        } catch (Exception e) {
            throw new Exception("Send GET request failed", e);
        } finally {
            try {
                if (ir != null) {
                    ir.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String httpget(String json, String url, Map<String, String> header, HttpServletRequest request) throws Exception {
        String result = null;
        System.out.println("request ipaddress:" + request.getRemoteAddr() + "\n request host:" + request.getRemoteHost() + " request json:" + json);
        try {
            HostnameVerifier hv = (urlHostName, session) -> true;
            trustAllHttpsCertificates();
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
            URL httpsurl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpsurl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            if (header != null && header.size() > 0) {
                Iterator<String> it = header.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    conn.setRequestProperty(key, header.get(key));
                }
            }
            conn.setDoOutput(true);
            String param = json;
            byte[] b = param.getBytes("UTF-8");
            conn.getOutputStream().write(b);
            InputStream is = conn.getInputStream();
            String charset = "UTF-8";
            BufferedReader br = new BufferedReader(new InputStreamReader(is, charset));

            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = br.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }

    class miTM implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager {
        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        @Override
        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        @Override
        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }
}
