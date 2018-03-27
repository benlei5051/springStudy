import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedHpsMeasure;

/**
 * @author: andy
 * @Date: 2018/3/23 17:30
 * @Description:
 */
public class ADD {
    public static void main(String[] args){
//        java里不能使用前置表示2进制，只能是 8，10，16进制
        //八进制
        int a=023;
        //十六进制
        int b=0xff;
        System.out.println(b);
        System.out.println(a);
//        十进制转化为十六进制
        System.out.println(Integer.toHexString(16));
//        将字符串转化为16进制
        System.out.println(Integer.valueOf("ff",16));
        int lenth=Integer.toBinaryString(a).length();

        String str=Integer.toBinaryString(a);
        StringBuilder sb =new StringBuilder();
        for (int i=0;i<16-lenth;i++) {
            sb.append("0");
        }
        System.out.println(sb.append(str).toString());



        /*int a=8;
        int b=a<<2;
        int c=a>>2;
        int d=-2;
        int e=031;
        Integer integer = Integer.valueOf("23", 16);
        System.out.println(integer);
        System.out.println(Integer.toBinaryString(e));
        System.out.println(Integer.toBinaryString(d));
        System.out.println(Integer.toBinaryString(d>>2));
        System.out.println(d>>8);
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(c));*/

    }
}
