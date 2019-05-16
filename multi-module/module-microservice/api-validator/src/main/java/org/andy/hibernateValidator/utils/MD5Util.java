/**
 *
 */
package org.andy.hibernateValidator.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author  liuqa
 * @version 1.0
 * MD5加密工具类
 */
public class MD5Util {

    /** 利用MD5进行加密
     * MD5(Base64())
     * @param str  待加密的字符串
     * @return     加密后的字符串
     */
    public static String EncoderByMd5(String str) {

        String newstr = null;
        if (str != null && str.length() > 0) {
            try {
                newstr = DigestUtils.md5Hex(Base64.encodeBase64String(str.getBytes("UTF-8")).getBytes());
            } catch (UnsupportedEncodingException e) {
                newstr = str;
            }
            //加密后的字符串
        }
        return newstr;
    }
}
