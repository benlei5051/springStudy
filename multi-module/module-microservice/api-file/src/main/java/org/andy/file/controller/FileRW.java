/*------------------------------------------------------------------------------
 * FileRW
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/19 16:04
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.file.controller;

import cn.hutool.core.io.FileUtil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileRW {
    public static void main(String[] args) {
        String file_utf = "D:/eclipse/workspace/javaFileTest/test-utf.txt";
        codeString(file_utf, true);
        getFileContent(file_utf, false);

        String file_gbk = "D:/eclipse/workspace/javaFileTest/test-gbk.txt";
        String file_gbk_utf_8 = "D:/eclipse/workspace/javaFileTest/test-gbk-utf-8-error.txt";
        String file_gbk_utf_16 = "D:/eclipse/workspace/javaFileTest/test-gbk-utf-16-ok.txt";

        transferFile(file_gbk, file_gbk_utf_8, "utf-8");
        transferFile(file_gbk, file_gbk_utf_16, "utf-16");

        codeString(file_gbk, true);
        getFileContent(file_gbk, false);

        codeString(file_gbk_utf_8, true);
        getFileContent(file_gbk_utf_8, false);

        codeString(file_gbk_utf_16, true);
        getFileContent(file_gbk_utf_16, false);
    }

    private static void getFileContent(String filePath, boolean forceCode) {
        try {
            String line_separator = System.getProperty("line.separator");
            FileInputStream fis = new FileInputStream(filePath);
            StringBuffer content = new StringBuffer();
            DataInputStream in = new DataInputStream(fis);
            String code = codeString(filePath, false);
            BufferedReader d = null;
            if (forceCode) {
                d = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            } else {
                d = new BufferedReader(new InputStreamReader(in, code));
            }
            String line = null;
            while ((line = d.readLine()) != null) {
                content.append(line + line_separator);
            }
            d.close();
            in.close();
            fis.close();
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("获取文件内容异常");
        }
    }

    private static void transferFile(String srcFileName, String destFileName, String codeOuput) {
        try {
            String line_separator = System.getProperty("line.separator");
            FileInputStream fis = new FileInputStream(srcFileName);
            StringBuffer content = new StringBuffer();
            DataInputStream in = new DataInputStream(fis);
            String codeInput = codeString(srcFileName, false);
            // "UTF-8"
            BufferedReader d = new BufferedReader(new InputStreamReader(in, codeInput));
            String line = null;
            while ((line = d.readLine()) != null) {
                content.append(line + line_separator);
            }
            d.close();
            in.close();
            fis.close();
            Writer ow = new OutputStreamWriter(new FileOutputStream(destFileName), codeOuput);
            ow.write(content.toString());
            ow.close();
            System.out.println("写入完成！");
        } catch (Exception e) {
            System.out.println("文件编码转换异常");
        }
    }

    /**
     * 判断文件的编码格式
     *
     * @param fileName
     *            :file
     * @return 文件编码格式
     * @throws Exception
     */
    public static String codeString(String fileName, boolean print) {
        String return_value = "";
        try {
            BufferedInputStream bin = new BufferedInputStream(
                    new FileInputStream(fileName));

            int p = (bin.read() << 8) + bin.read();

            String code = null;

            switch (p) {
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code = "Unicode";
                    break;
                case 0xfeff:
                    code = "UTF-16BE";
                    break;
                case 0x5c75:
                    code = "ANSI|ASCII";
                    break;
                default:
                    code = "GBK";
            }

            return_value = code;
        } catch (Exception e) {
            System.out.println("获取文件编码类型失败");
        }
        if (print) {
            System.out.println(fileName + " code:" + return_value);
        }
        return return_value;
    }
}
