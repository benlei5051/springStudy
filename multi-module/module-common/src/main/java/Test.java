import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;

/**
 * @author: andy
 * @Date: 2018/2/26 10:58
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(011);
        System.out.println(Integer.toHexString(021));
        /*int youNumber = 1;
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        String str = String.format("%02d", youNumber);
        System.out.println(formatTime(800));
        System.out.println(str); // 0001*/
    }

    public static void fun1(Integer second) {
        //待测试数据
        int i = 10;
        //得到一个NumberFormat的实例
        NumberFormat nf = NumberFormat.getInstance();
        //设置是否使用分组
        nf.setGroupingUsed(false);
        //设置最大整数位数
        nf.setMaximumIntegerDigits(4);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(4);
        //输出测试语句
        System.out.println(nf.format(i));
    }
    public static String formatTime(Integer seconds) {
        int hour = seconds / 3600;
        seconds = seconds % 3600;   //剩余秒数
        int minutes = seconds / 60;            //转换分钟
        seconds = seconds % 60;                //剩余秒数
        return String.format("%02d",hour)+":"+String.format("%02d",minutes)+":"+String.format("%02d",seconds);

    }

}
