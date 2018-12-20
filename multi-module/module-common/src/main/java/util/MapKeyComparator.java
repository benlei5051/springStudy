/*------------------------------------------------------------------------------
 * MapKeyComparator
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/15 11:42
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package util;

import java.util.Comparator;

class MapKeyComparator implements Comparator<String> {
    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}
