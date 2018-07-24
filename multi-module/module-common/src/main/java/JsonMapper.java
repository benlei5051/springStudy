import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 * 类名称: [JsonMapper对象]
 * </p>
 * <p>
 * 类描述: [简单封装Jackson，实现JSON String<->Java Object的Mapper.封装不同的输出风格, 使用不同的builder函数创建实例.]
 * </p>
 * <p>
 * 创建时间 13-1-8 下午6:46
 * </p>
 *
 * @version 1.0
 */
@Component
public class JsonMapper {
    /**
     * <p>
     * 属性描述: [日志对象]
     * </p>
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonMapper.class);

    /**
     * <p>
     * 属性描述: [ObjectMapper]
     * </p>
     */
    private final ObjectMapper mapper;

    /**
     * <p>
     * 方法描述: [构造方法]
     * </p>
     */
    public JsonMapper() {
        this(null);
    }

    /**
     * <p>
     * 方法描述: [构造方法]
     * </p>
     */
    public JsonMapper(Include include) {
        mapper = new ObjectMapper();

        // 设置输出时包含属性的风格
        if (include != null) {
            mapper.setSerializationInclusion(include);
        }

        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * <p>
     * 方法描述: [创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.]
     * </p>
     *
     * @return 返回结果的说明
     */
    public static JsonMapper nonEmptyMapper() {
        return new JsonMapper(Include.NON_EMPTY);
    }

    /**
     * <p>
     * 方法描述: [创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。]
     * </p>
     */
    public static JsonMapper nonDefaultMapper() {
        return new JsonMapper(Include.NON_DEFAULT);
    }

    /**
     * <p>
     * 方法描述: [Object可以是POJO，也可以是Collection或数组。 如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".]
     * </p>
     *
     * @param object java对象
     * @return 返回结果的说明 json字符串
     */
    public String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            LOGGER.warn("write to json string error:" + object, e);

            return null;
        }
    }

    /**
     * <p>
     * 方法描述: [反序列化POJO或简单Collection如List<String>.
     * <p>
     * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
     * <p>
     * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String,JavaType)]
     * <p>
     *
     * @see #fromJson(String, JavaType)
     */
    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        //处理根节点问题
        String className = clazz.getSimpleName();
        // System.out.println(jsonString.indexOf(className));
        if (jsonString.indexOf(className) < 0) {
            jsonString = "{\"" + className + "\":" + jsonString + "}";
        }

        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            LOGGER.warn("parse json string error:" + jsonString, e);

            return null;
        }
    }

    /**
     * 方法描述: [反序列化复杂Collection如List<Bean>, 先使用函數createCollectionType构造类型,然后调用本函数.
     *
     * @see #createCollectionType(Class, Class...)]
     */
    @SuppressWarnings("unchecked")
    public <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return (T) mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            LOGGER.warn("parse json string error:" + jsonString, e);

            return null;
        }
    }

    /**
     * 方法描述: [構造泛型的Collection Type如: ArrayList<MyBean>, 则调用constructCollectionType(ArrayList.class,MyBean.class)
     * HashMap<String,MyBean>, 则调用(HashMap.class,String.class, MyBean.class)]
     */
    public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 方法描述: [當JSON裡只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.]
     */
    @SuppressWarnings("unchecked")
    public <T> T update(String jsonString, T object) {
        try {
            return (T) mapper.readerForUpdating(object).readValue(jsonString);
        } catch (JsonProcessingException e) {
            LOGGER.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        } catch (IOException e) {
            LOGGER.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        }

        return null;
    }

    /**
     * 方法描述: [輸出JSONP格式數據.]
     */
    public String toJsonP(String functionName, Object object) {
        return toJson(new JSONPObject(functionName, object));
    }

    /**
     * 方法描述: [設定是否使用Enum的toString函數來讀寫Enum, 為False時時使用Enum的name()函數來讀寫Enum, 默認為False. 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.]
     */
    public void enableEnumUseToString() {
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    /**
     * 方法描述: [支持使用Jaxb的Annotation，使得POJO上的annotation不用与Jackson耦合。 默认会先查找jaxb的annotation，如果找不到再找jackson的。]
     */
  /*  public void enableJaxbAnnotation() {
        JaxbAnnotationModule module = new JaxbAnnotationModule();

        mapper.registerModule(module);
    }*/

    /**
     * 方法描述: [取出Mapper做进一步的设置或使用其他序列化API.]
     */
    public ObjectMapper getMapper() {
        return mapper;
    }
}