package demo.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class BudCmpApiDemo {
    static String HOST_SERVER = "http://api.budcmp.com";

    String APPID = "3f65632702b543798e87adb97811df4d";
    String KEY = "q6d75vv59ujfoqjfttsq39p2ec";

    String access_code = "xxxxxxxxx";
    String imsi = "xxxxxxxx";
    String platform_code = "xxxxxxxx";
    String custom_code = "";

    private void doPost(String url, Map<String, Object> map) throws Exception {
        String result = sendPost(url, createdMap(map));
        System.out.println(String.format("result:%s", result));
    }

    Map<String, Object> createdMap(Map<String, Object> map) {
        map.put("appid", APPID);
        map.put("timestamp", Integer.toString((int) (System.currentTimeMillis() / 1000)));
        String sign = createSign(KEY, map);
        map.put("sign", sign);
        return map;
    }

    @Test
    public void test1() {
        try {
            doPost(HOST_SERVER + "/api/iot/v1/test", createdMap(new HashMap<>()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String createSign(String key, Map<String, Object> params) {
        Map<String, Object> map = sortMapByKey(params);
        StringBuffer sb = new StringBuffer();
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String k = it.next();
            Object v = map.get(k);
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"callback".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = md5Encode(sb.toString());
        return sign;
    }

    public static String md5Encode(String str) {
        StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte bytes[] = md5.digest();
            for (int i = 0; i < bytes.length; i++) {
                String s = Integer.toHexString(bytes[i] & 0xff);
                if (s.length() == 1) {
                    buf.append("0");
                }
                buf.append(s);
            }
        } catch (Exception ex) {
        }
        return buf.toString().toLowerCase();
    }

    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, Object> sortMap = new TreeMap<String, Object>(
                new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }


    public static String sendPost(String url, Map<String, Object> requestData) {
        String result = "";
        try {
            URL u0 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u0.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Content-Language", "en-US");
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

            bWriter.write(getUrlParamsByMap(requestData));
            bWriter.flush();
            bWriter.close();
            InputStream in = conn.getInputStream();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i != -1; ) {
                i = in.read();
                if (i != -1)
                    buffer.append((char) i);
            }
            in.close();
            result = new String(buffer.toString().getBytes("iso-8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }
}

class MapKeyComparator implements Comparator<String> {
    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}

