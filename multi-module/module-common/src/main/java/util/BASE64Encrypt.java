package util;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @author: andy
 * @Date: 2018/6/26 14:56
 * @Description:
 *
 * base64
 *
 * 不是加密算法，是一种编码方式，由于加密后都是byte[]，为了可读性，一般将byte[]转为base64编码
 */
public class BASE64Encrypt {
    public static void main(String[] args) throws Exception {
        String s = "我爱你";
        System.out.println("转换前：" + s);
        String encode = base64Encode(s.getBytes());
        System.out.println("转换后：" + encode);

        System.out.println("解码后：" + new String(Objects.requireNonNull(base64Decode(encode))));
    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */

    public static String binary(byte[] bytes, int radix) {
        //这里的1代表正数
        return new BigInteger(1, bytes).toString(radix);
    }

    /**
     * base 64 encode
     *
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }
}



