package org.andy.beans.service;

import org.andy.beans.dao.JsrDao;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author: andy
 * @Date: 2017/9/25 15:03
 * @Description:
 */
@Service
public class JsrService {

    @Resource
    private JsrDao jsrDao;

    /**
     * 标注了 @PostConstruct 注释的方法将在类实例化后调用，
     * 而标注了 @PreDestroy 的方法将在类销毁之前调用。
     */
    @PostConstruct
    public void init() {
        System.out.println("JsrService init.");
    }

    @PreDestroy
    public void destory() {
        System.out.println("JsrService destory.");
    }

    public void save(String arg) {
        jsrDao.save(arg);
    }
}
