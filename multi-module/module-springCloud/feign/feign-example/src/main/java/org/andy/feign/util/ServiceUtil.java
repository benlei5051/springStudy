package org.andy.feign.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServiceUtil {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUtil.class);

    /**
     * JSON转List<T>
     * <p>
     * 示例:
     * {@code ServiceUtil.jsonToList(json, "baseDeviceAccounts", BaseDeviceAccount.class); }
     *
     * @param <PO>      json串将被转换成的实体对象
     * @param json      json串，如下：
     *
     *                  <pre class="code">
     *                                   {
     *                                     "_embedded" : { "baseDeviceAccounts" : [ ] },
     *                                     "_links" : { "self" : {"href" : "http://dbdevice.paas.pateo.com.cn/api/deviceaccount/search/findByDeviceId?deviceId=P008000130000431" } }
     *                                   }
     *                                   </pre>
     * @param poArrName json串中对象数组名，如：baseDeviceAccounts
     * @param poClazz   将要转换成的对象，如：BaseDeviceAccount.class
     * @return List<T> 转换后的List集合，如：List<BaseDeviceAccounts>
     */
    public static final <PO> List<PO> jsonToList(String json, String poArrName, Class<PO> poClazz) {
        JSONObject root = JSON.parseObject(json);
        JSONObject embedded = root.getJSONObject("_embedded");
        String poArr = JSONObject.toJSONString(embedded.get(poArrName));
        return JSONArray.parseArray(poArr, poClazz);
    }

    /**
     * JSON转Map包含分页信息
     * <p>
     * 示例:
     * {@code ServiceUtil.jsonToList(json, "baseDeviceAccounts", BaseDeviceAccount.class); }
     *
     * @param <PO>      json串将被转换成的实体对象
     * @param json      json串，如下：
     *
     *                  <pre class="code">
     *                                   {
     *                                     "_embedded" : { "baseDeviceAccounts" : [ ] },
     *                                     "_links" : { "self" : {"href" : "http://dbdevice.paas.pateo.com.cn/api/deviceaccount/search/findByDeviceId?deviceId=P008000130000431" },
     *                                     "page" : {"size" : 10,"totalElements" : 2,"totalPages" : 1,"number" : 0}
     *                                   }
     *                                   </pre>
     * @param poArrName json串中对象数组名，如：baseDeviceAccounts
     * @param poClazz   将要转换成的对象，如：BaseDeviceAccount.class
     * @return List<T> 转换后的List集合，如：List<BaseDeviceAccounts>
     */
    public static final <PO> Map<String, Object> jsonToMap(String json, String poArrName, Class<PO> poClazz) {
        Map<String, Object> resultMap = new HashMap<>();
        JSONObject root = JSON.parseObject(json);
        JSONObject embedded = root.getJSONObject("_embedded");
        String poArr = JSONObject.toJSONString(embedded.get(poArrName));
        List<PO> poList = JSONArray.parseArray(poArr, poClazz);
        resultMap.put("list", poList);
        JSONObject pageObject = root.getJSONObject("page");
        String pageArr = JSONObject.toJSONString(pageObject);
        Page page = JSON.parseObject(pageArr, Page.class);
        resultMap.put("page", page);
        return resultMap;
    }

    /**
     * List<PO>转List<VO>
     *
     * @param <VO>    VO对象
     * @param <PO>    PO对象
     * @param poList  PO对象集合
     * @param voClazz VO对象
     * @return VO对象集合
     */
    public static final <VO, PO> List<VO> poList2VoList(List<PO> poList, Class<VO> voClazz) {
        List<VO> voList = null;
        if (null != poList && !poList.isEmpty()) {
            voList = new ArrayList<>();
            for (PO po : poList) {
                try {
                    VO vo = voClazz.newInstance();
                    BeanUtils.copyProperties(po, vo);
                    voList.add(vo);
                } catch (BeansException e) {
                    LOGGER.error("", e);
                } catch (InstantiationException e) {
                    LOGGER.error("", e);
                } catch (IllegalAccessException e) {
                    LOGGER.error("", e);
                }
            }
        }
        return voList;
    }

    /**
     * 修改对象时，根据没有null值，不用再一个一个的if判断了
     *
     * @param source
     * @return
     */



    /* if (null != faultCodeDic) {
        if (StringUtils.isNotBlank(faultCodeDicVo.getFaultCode())) {
            faultCodeDic.setFaultCode(faultCodeDicVo.getFaultCode());
        }
        if (StringUtils.isNotBlank(faultCodeDicVo.getFaultDesc())) {
            faultCodeDic.setFaultDesc(faultCodeDicVo.getFaultDesc());
        }
        if (StringUtils.isNotBlank(faultCodeDicVo.getFaultName())) {
            faultCodeDic.setFaultName(faultCodeDicVo.getFaultName());
        }
        if (StringUtils.isNotBlank(faultCodeDicVo.getFaultType())) {
            faultCodeDic.setFaultType(faultCodeDicVo.getFaultType());
        }
        if (null != faultCodeDicVo.getLevel()) {
            faultCodeDic.setLevel(faultCodeDicVo.getLevel());
        }
        if (null != faultCodeDicVo.getActive()) {
            faultCodeDic.setActive(faultCodeDicVo.getActive());
        }
        if (StringUtils.isNotBlank(faultCodeDicVo.getMaintainRemind())) {
            faultCodeDic.setMaintainRemind(faultCodeDicVo.getMaintainRemind());
        }
        faultCodeDicRepository.saveAndFlush(faultCodeDic);
    }*/
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
