package org.andy.beans.powermock.newdemo;

import java.io.File;

/**
 * ==========================================================================
 * FlySunDemo
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/5/16 17:30
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
public class FlySunDemo {
    public boolean callArgumentInstance(String path) {
        File file = new File(path);
        return file.exists();
    }
}
