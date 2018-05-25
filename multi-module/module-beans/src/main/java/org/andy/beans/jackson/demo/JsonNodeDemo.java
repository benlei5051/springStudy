package org.andy.beans.jackson.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;

/**
 * @author: andy
 * @Date: 2018/5/25 16:44
 * @Description:
 */
public class JsonNodeDemo {
    public static void main(String[] args) throws IOException {
        //language=JSON
        String json = "{\"HeWeather5\":[{\"basic\":{\"city\":\"呼和浩特\",\"cnty\":\"中国\",\"id\":\"CN101080101\",\"lat\":\"40.81830978\",\"lon\":\"111.67079926\",\"update\":{\"loc\":\"2018-05-25 15:51\",\"utc\":\"2018-05-25 07:51\"}},\"daily_forecast\":[{\"astro\":{\"mr\":\"15:24\",\"ms\":\"02:59\",\"sr\":\"05:08\",\"ss\":\"19:53\"},\"cond\":{\"code_d\":\"100\",\"code_n\":\"101\",\"txt_d\":\"晴\",\"txt_n\":\"多云\"},\"date\":\"2018-05-25\",\"hum\":\"22\",\"pcpn\":\"0.0\",\"pop\":\"0\",\"pres\":\"1004\",\"tmp\":{\"max\":\"28\",\"min\":\"13\"},\"uv\":\"8\",\"vis\":\"20\",\"wind\":{\"deg\":\"208\",\"dir\":\"西南风\",\"sc\":\"4-5\",\"spd\":\"25\"}},{\"astro\":{\"mr\":\"16:27\",\"ms\":\"03:29\",\"sr\":\"05:08\",\"ss\":\"19:54\"},\"cond\":{\"code_d\":\"305\",\"code_n\":\"100\",\"txt_d\":\"小雨\",\"txt_n\":\"晴\"},\"date\":\"2018-05-26\",\"hum\":\"41\",\"pcpn\":\"0.0\",\"pop\":\"0\",\"pres\":\"1014\",\"tmp\":{\"max\":\"23\",\"min\":\"9\"},\"uv\":\"9\",\"vis\":\"19\",\"wind\":{\"deg\":\"350\",\"dir\":\"北风\",\"sc\":\"4-5\",\"spd\":\"28\"}},{\"astro\":{\"mr\":\"17:30\",\"ms\":\"03:59\",\"sr\":\"05:07\",\"ss\":\"19:55\"},\"cond\":{\"code_d\":\"100\",\"code_n\":\"100\",\"txt_d\":\"晴\",\"txt_n\":\"晴\"},\"date\":\"2018-05-27\",\"hum\":\"23\",\"pcpn\":\"0.0\",\"pop\":\"0\",\"pres\":\"1012\",\"tmp\":{\"max\":\"24\",\"min\":\"10\"},\"uv\":\"9\",\"vis\":\"20\",\"wind\":{\"deg\":\"277\",\"dir\":\"西风\",\"sc\":\"3-4\",\"spd\":\"22\"}}],\"status\":\"ok\"}]}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(json, JsonNode.class);
        ArrayNode daily_forecast = (ArrayNode) jsonNode.get("HeWeather5");
        JsonNode jsonNode1 = daily_forecast.get(0);
        System.out.println(jsonNode1.get("status").asText());
        /*JsonNode jsonNode = objectMapper.readTree(json);
        ArrayNode daily_forecast = (ArrayNode) jsonNode.get("HeWeather5");
        JsonNode jsonNode1 = daily_forecast.get(0);
        System.out.println(jsonNode1.get("status").asText());*/
    }

}
