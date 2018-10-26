package httpurlconnection;

/**
 * @author: andy
 * @Date: 2018/7/17 17:10
 * @Description:
 */

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * POST请求示例
 *
 * @author 小明
 *
 */
public class PostDemo {

    public static void main(String[] args) {
        try {
            // 1. 获取访问地址URL
            URL url = new URL("http://localhost:8080/Servlet/do_login.do");
            // 2. 创建HttpURLConnection对象
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            /* 3. 设置请求参数等 */
            // 请求方式
            connection.setRequestMethod("POST");
            // 超时时间
            connection.setConnectTimeout(3000);
            // 设置是否输出 get 请求value是false
            connection.setDoOutput(true);
            // 设置是否读入
            connection.setDoInput(true);
            // 设置是否使用缓存
            connection.setUseCaches(false);
            // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
            connection.setInstanceFollowRedirects(true);
            // 设置使用标准编码格式编码参数的名-值对
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // 连接
            connection.connect();
            /* 4. 处理输入输出 */
            // 写入参数到请求中
            String params = "username=test&password=123456";
            OutputStream out = connection.getOutputStream();
            out.write(params.getBytes());
            out.flush();
            out.close();
            // 从连接中读取响应信息
            String msg = "";
            int code = connection.getResponseCode();
            if (code == 200) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    msg += line + "\n";
                }
                reader.close();
            }
            // 5. 断开连接
            connection.disconnect();

            // 处理结果
            System.out.println(msg);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String sendPost(String url, StringBuffer sb) throws Exception {
        URL u = null;
        HttpURLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        InputStreamReader ir = null;
        // Attempt to send a request
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setConnectTimeout(6000);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/json");
            osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            osw.write(sb.toString());
            osw.flush();

        } catch (Exception e) {
            throw new Exception("Send POST request failed" + e);
        } finally {
            try {
                if (osw != null) {
                    osw.close();
                }
                if (con != null) {
                    con.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
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
            throw new Exception("Send POST request failed", e);
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

    public String httpspost(String json, String url, Map<String, String> header, HttpServletRequest request) throws Exception {
        String result = null;
        System.out.println("request ipaddress:" + request.getRemoteAddr() + "\n request host:" + request.getRemoteHost() + " request json:" + json);
        try {
            HostnameVerifier hv = (urlHostName, session) -> true;
            trustAllHttpsCertificates();
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
            URL httpsurl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpsurl.openConnection();
            conn.setRequestMethod("POST");
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
