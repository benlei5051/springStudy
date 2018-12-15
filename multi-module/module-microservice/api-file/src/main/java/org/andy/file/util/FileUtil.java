/*------------------------------------------------------------------------------
 * FileUtil
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/19 16:26
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.file.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
public class FileUtil {

    /**
     * 按行读取txt\dat等文本文件
     */
    public static List<String> readLines(String fileName) {
        List<String> list = new ArrayList<>();
        try {
            list = FileUtils.readLines(new File(fileName), Charsets.UTF_8);
        } catch (IOException e) {
            log.error("catch readLines {}", e.getMessage());
        }
        return list;
    }
    public static void main(String[] args){
        String srcFile = "D:" + File.separator + "test.xml";
        List<Element> elementList = FileUtil.parseXml(srcFile, "//table/rows");
        for (Element e : elementList) {
            String seriesCode = e.attributeValue("CARSERICODE");
        }
    }
    public static List<Element> parseXml(String fileName, String xpath) {
        List<Element> list = new ArrayList<>();
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(fileName));
            Element rootElement = document.getRootElement();
            list = ((Element) rootElement.selectSingleNode(xpath)).elements();
        } catch (DocumentException e) {
            log.error("catch parseXml, {}", e.getMessage());
        }

        return list;
    }

    public static void backup(String srcFile, String destDir) {
        String currentDay = LocalDateTime.now().toString();
        destDir += File.separator + currentDay;
        File destFile = new File(destDir);
        if (!destFile.exists()) {
            destFile.mkdirs();
        }
        try {
            FileUtils.moveFileToDirectory(new File(srcFile), destFile, true);
            log.info("backup {} to {} success", srcFile, destDir);

        } catch (IOException e) {
            log.error("catch backup, move {} to {}, {}", srcFile, destDir, e.getMessage());
        }
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
