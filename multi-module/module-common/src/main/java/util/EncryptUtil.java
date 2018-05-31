package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class EncryptUtil {
    private static final Logger LOG = LoggerFactory.getLogger(EncryptUtil.class);
    private static final int keyOfEncAndDec = 0xABCDEF; // 加密解密秘钥
    private static int dataOfFile = 0; // 文件字节内容

    /**
     * 对文件进行位偏移加密
     *
     * @param srcFile
     * @param encFile
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static void EncFile(File srcFile, File encFile)
            throws Exception {
        if (!srcFile.exists()) {
            LOG.warn("source file not exixt");
            return;
        }

        if (!encFile.exists()) {
            LOG.warn("encrypt file created");
            encFile.createNewFile();
        }
        InputStream fis = new FileInputStream(srcFile);
        OutputStream fos = new FileOutputStream(encFile);

        while ((dataOfFile = fis.read()) > -1) {
            fos.write(dataOfFile ^ keyOfEncAndDec);
        }

        fis.close();
        fos.flush();
        fos.close();
    }


    /**
     * 对文件进行位偏移解密
     *
     * @param encFile
     * @param decFile
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    private static void DecFile(File encFile, File decFile) throws Exception {
        if (!encFile.exists()) {
            LOG.warn("encrypt file not exixt");
            return;
        }

        if (!decFile.exists()) {
            LOG.warn("decrypt file created");
            decFile.createNewFile();
        }

        InputStream fis = new FileInputStream(encFile);
        OutputStream fos = new FileOutputStream(decFile);

        while ((dataOfFile = fis.read()) > -1) {
            fos.write(dataOfFile ^ keyOfEncAndDec);
        }

        fis.close();
        fos.flush();
        fos.close();
    }


    public static void main(String[] args) {
        File srcFile = new File("d:/mno/2.jpg"); // 初始文件
//        File encFile1 = new File("C:/Users/senbi/Desktop/encodedFile.tif"); // 加密文件
        File encFile = new File("d:/mno/encodedFile.jpg"); // 加密文件
        File decFile = new File("d:/mno/decodedFile.jpg"); // 解密文件

        try {
            //测试加密
//            EncFile(srcFile, encFile); // 加密操作

            //测试解密
            DecFile(encFile, decFile); // 解密操作
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
