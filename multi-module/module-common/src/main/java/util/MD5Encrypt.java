package util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;

/**
 * @author: andy
 * @Date: 2018/6/26 15:36
 * @Description:
 *
 *  md5
 *
 * 也不是算法，是消息摘要算法第五版，是一种哈希算法，一般用于单向加密
 *
 */
public class MD5Encrypt {
    public static void main(String[] args) throws Exception {
        String msg = "我爱你";
        System.out.println("加密前：" + msg);
        System.out.println(DigestUtils.md5DigestAsHex(msg.getBytes()));
        String encrypt = md5Encrypt(msg);
        System.out.println("加密后：" + encrypt);
    }
    /**
     * 获取byte[]的md5值
     * @param bytes byte[]
     * @return md5
     * @throws Exception
     */
    public static byte[] md5(byte[] bytes) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);

        return md.digest();
    }

    /**
     * 获取字符串md5值
     * @param msg
     * @return md5
     * @throws Exception
     */
    public static byte[] md5(String msg) throws Exception {
        return StringUtils.isEmpty(msg) ? null : md5(msg.getBytes());
    }

    /**
     * 结合base64实现md5加密
     * @param msg 待加密字符串
     * @return 获取md5后转为base64
     * @throws Exception
     */
    public static String md5Encrypt(String msg) throws Exception{
        return StringUtils.isEmpty(msg) ? null : BASE64Encrypt.base64Encode(md5(msg));
    }
}
