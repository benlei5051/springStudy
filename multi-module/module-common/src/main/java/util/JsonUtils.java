package util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtils {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:s";

    private static final ObjectMapper objectMapper;



   /* private static final ObjectMapper MAPPER = new ObjectMapper();


    @PostConstruct
    private void init() {

        MAPPER.registerModule(new SimpleModule().addSerializer(new NullValueSerializer("type")));

        MAPPER.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.NON_FINAL, "type");

    }*/





    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setDateFormat(new SimpleDateFormat(PATTERN));
    }

    public static String object2Json(Object object) {
        try {
            String str = objectMapper.writeValueAsString(object);
            log.debug("str: " + str);
            return str;
        } catch (Exception e) {
            log.error("Failed to convert the Object into Json format!", e);
            return null;
        }
    }

    public static <T> T json2Object(String json, Class<T> clazz) {
        try {
            T t = objectMapper.readValue(json, clazz);
            log.debug("object: " + t.toString());
            return t;
        } catch (Exception e) {
            log.error("Failed to convert the Json into Object!", e);
            return null;
        }
    }

    public static boolean isJson(String json) {
        if (StringUtils.isBlank(json)) {
            return false;
        }
        try {
            objectMapper.readValue(json, JsonNode.class);
            return true;
        } catch (Exception e) {
            log.error("bad json: " + json);
            return false;
        }
    }

}
