/*------------------------------------------------------------------------------
 * HttpClienTest
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/13 17:23
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package demo.test;

import httpclient.pool.HttpClienFactory;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClienTest {
    @Test
    public void httpGet() {
        String url="http://www.baidu.com";
        try {
            HttpResponse response=	HttpClienFactory.doGet(url);
            System.out.println(EntityUtils.toString(response.getEntity())+"   "+response.getStatusLine());
            response.getEntity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
