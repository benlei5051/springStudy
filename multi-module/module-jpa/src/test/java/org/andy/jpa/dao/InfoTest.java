package org.andy.jpa.dao;

import com.sun.org.apache.xml.internal.security.encryption.Transforms;
import org.andy.jpa.entity.Info;
import org.andy.jpa.entity.InfoModel;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author: andy
 * @Date: 2018/1/30 15:56
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class InfoTest {
    @Autowired
    public EntityManager entityManager;

    @Autowired
    public InfoRepository infoRepository;

    @Test
    @Rollback(false)
    public void testInsertNativeSql(){
        this.entityManager.createNativeQuery("insert into info(title,msg) values ('testTitle','testMsg')").executeUpdate();
    }
    @Test
    public void testQueryNativeSql(){
        List<Object[]> select_title_from_info = this.entityManager.createNativeQuery("select msg as message,title as tt from info","messageMapping").getResultList();
        System.out.println("----------");
    }

    @Test
    public void testQueryEntity1(){
        List<Info> infos = infoRepository.findInfos();
        infos.forEach(info -> System.out.println(info.getMsg()));
    }
    /**
     * 查询结果map封装
     */
    @Test
    public void testQueryEntity2(){
        Query nativeQuery = this.entityManager.createNativeQuery("select msg,title from info");
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String,Object>> resultList = nativeQuery.getResultList();
        resultList.forEach(stringObjectMap -> stringObjectMap.forEach((k,v)->System.out.println("key: "+k+" value: "+v)));

    }

    /**
     * 查询结果对象封装
     */
    @Test
    public void testQueryEntity3(){
        Query nativeQuery = this.entityManager.createNativeQuery("select msg,title from info");
        nativeQuery.unwrap(SQLQuery.class).addScalar("msg", StringType.INSTANCE).addScalar("title",StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(InfoModel.class));
        List<InfoModel> resultList = nativeQuery.getResultList();
        resultList.forEach(infoModel -> System.out.println(infoModel.getMsg()));

    }


    @Test
    public void testQueryEntity4(){
        Query nativeQuery = this.entityManager.createNativeQuery("select msg,title from info");
        List<InfoModel> list = (List<InfoModel>)nativeQuery.unwrap(SQLQuery.class).addScalar("msg", StringType.INSTANCE).addScalar("title", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(InfoModel.class)).list();
        System.out.println(list.get(0).getTitle());
    }

}
