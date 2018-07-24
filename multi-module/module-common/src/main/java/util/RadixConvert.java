package util;

/**
 * @author: andy
 * @Date: 2018/7/10 14:18
 * @Description: 进制转换
 */
public class RadixConvert {
    public static void main(String[] args) {
//        intToHexOrOctOrBinary();
//        bytesToHex();
        hexToByteArray();
        hexStringToBytes("11ffd");
    }

    /**
     * 整型数据转为不同的进制数
     */
    public static void intToHexOrOctOrBinary() {
        //9的八进制表示：011
        String s = Integer.toBinaryString(0xFF);
        //17的16进制表示: 0x11
        String s1 = Integer.toHexString(0x11);
        //9的八进制表示: 011
        String s2 = Integer.toOctalString(011);
        System.out.println("八进制的数转换为二进制字符串:    " + s);
        System.out.println("十六进制的数转换为十六进制字符串: " + s1);
        System.out.println("八进制的数转换为八进制字符串:    " + s2);
    }


    /**
     * 字节数组转16进制
     *
     * @return 转换后的Hex字符串
     */
    public static String bytesToHex() {
        byte[] bytes = "abc".getBytes();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * hex字符串转byte数组
     * @return  转换后的byte数组结果
     */
    public static byte[] hexToByteArray(){
        //16进制字符串，十进制值为17
        String inHex ="11ffd";
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1){
            //奇数
            hexlen++;
            result = new byte[(hexlen/2)];
            inHex="0"+inHex;
        }else {
            //偶数
            result = new byte[(hexlen/2)];
        }
        int j=0;
        for (int i = 0; i < hexlen; i+=2){
            result[j]=(byte)Integer.parseInt(inHex.substring(i,i+2),16);
            j++;
        }
        return result;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte)"0123456789ABCDEF".indexOf(c);
    }


}
