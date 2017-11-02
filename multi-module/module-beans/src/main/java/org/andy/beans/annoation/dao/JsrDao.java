package org.andy.beans.annoation.dao;

import org.springframework.stereotype.Repository;

/**
 * @author: andy
 * @Date: 2017/9/25 15:04
 * @Description:
 */
@Repository
public class JsrDao {

    public void save(String s){
        System.out.println("操作数据库保存数据："+s);
    }
}
