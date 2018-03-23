package util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * @Author: joe
 * @Date: 17-7-24 下午5:53.
 * @Description:
 */
public class BaseServiceImpl {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Value("${carday.authorization.disable.header}")
    private String AUTHORIZATION_DISABLE_HEADER;

    /**
     * @Author zhou yangang
     * @Date 2017-04-21 15:48
     */

  /*  ParameterizedTypeReference<WsResponse<List<Long>>> responseType =
            new ParameterizedTypeReference<WsResponse<List<Long>>>() {};*/
    protected <T, K> T postFroObject(String apiUrl, K request, ParameterizedTypeReference<WsResponse<T>> responseType) {

        WsResponse<T> resVeh = postForResponseBody(apiUrl, request, responseType);

        return resVeh.getResult();
    }

    protected <T, K> T postForResponseBody(String apiUrl, K request, ParameterizedTypeReference<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set(AUTHORIZATION_DISABLE_HEADER, "true");

        HttpEntity httpEntity = new HttpEntity(request, headers);

        ResponseEntity<T> resEntityVeh = restTemplate.exchange(apiUrl, POST, httpEntity, responseType);

        return resEntityVeh.getBody();
    }

    protected <T> T postWithRequestParam(String apiUrl, Map<String, String> requestParams,
                                         ParameterizedTypeReference<T> responseType) {

        apiUrl = getUrlWithRequestParams(apiUrl, requestParams);

        HttpHeaders headers = new HttpHeaders();
        // 内部微服务之间的调用
        // 默认关闭 AuthorizatinInterceptor
        headers.set(AUTHORIZATION_DISABLE_HEADER, "true");

        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<T> resEntityVeh = restTemplate.exchange(apiUrl, POST, httpEntity, responseType);

        return resEntityVeh.getBody();
    }

    private String getUrlWithRequestParams(String apiUrl, Map<String, String> requestParams) {
        StringBuilder builder = new StringBuilder(apiUrl + "?");
        if (requestParams != null && requestParams.size() != 0) {
            for (Map.Entry<String, String> entry : requestParams.entrySet()) {
                if (StringUtils.isNotBlank(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {

//                    try
//                    {
                    builder.append(entry.getKey());
                    builder.append("=");
                    builder.append(entry.getValue());
                    builder.append("&");
//                    }
//                    catch (UnsupportedEncodingException e)
//                    {
//                        LOG.warn(String.format("url encoder fail, value = %s", entry.getValue()), e);
//                        continue;
//                    }
                }
            }
        }
        String url = builder.toString();
        if (url.endsWith("&")) {
            url = url.substring(0, builder.length() - 1);
        }

        return url;
    }

    // /**
    // * 首汽服务 调用方法，其返回值为 ShouqiResponse
    // * @param apiUrl
    // * @param request
    // * @param responseType
    // * @param <T>
    // * @param <K>
    // * @return
    // */
    // protected <T, K> ShouqiResponse<T> postShouqiApiRequest(String apiUrl, K request,
    // ParameterizedTypeReference<ShouqiResponse<T>> responseType) {
    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.APPLICATION_JSON);
    //
    // HttpEntity httpEntity = new HttpEntity(request, headers);
    //
    // ResponseEntity<ShouqiResponse<T>> resEntityVeh =
    // restTemplate.exchange(apiUrl,
    // POST,
    // httpEntity,
    // responseType);
    //
    // return resEntityVeh.getBody();
    // }

    // /**
    // * Post调用方法，其返回值为 CommandResponse
    // * @param apiUrl
    // * @param request
    // * @param responseType
    // * @param <T>
    // * @param <K>
    // * @return
    // */
    // protected <T, K> CommandResponse postCmdApiRequest(String apiUrl, K request,
    // ParameterizedTypeReference<CommandResponse> responseType) {
    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.APPLICATION_JSON);
    //
    // HttpEntity httpEntity = new HttpEntity(request, headers);
    //
    // ResponseEntity<CommandResponse> resEntityVeh =
    // restTemplate.exchange(apiUrl,
    // POST,
    // httpEntity,
    // responseType);
    //
    // return resEntityVeh.getBody();
    // }

    protected <T> T getForObject(String apiUrl, Map<String, String> requestParams,
                                 ParameterizedTypeReference<WsResponse<T>> responseType) {

        apiUrl = getUrlWithRequestParams(apiUrl, requestParams);

        HttpHeaders headers = new HttpHeaders();
        // 内部微服务之间的调用
        // 默认关闭 AuthorizatinInterceptor
        headers.set(AUTHORIZATION_DISABLE_HEADER, "true");

        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<WsResponse<T>> resEntityVeh = restTemplate.exchange(apiUrl, GET, httpEntity, responseType);

        WsResponse<T> resVeh = resEntityVeh.getBody();

        return resVeh.getResult();
    }

    protected <T> T postForFileParam(String apiUrl, List<MultipartFile> files,
                                     ParameterizedTypeReference<T> responseType) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        try {
            // While uploading multiple files, you need to make sure
            // getFileName of ByteArrayResource returns same value every time.
            // If not, you will always get an empty array.
            for (MultipartFile file : files) {
                param.add("file", new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                });
            }
        } catch (IOException e) {
            throw new ServiceException(MessageCode.COMMON_UPLOAD_FILE_IO_ERROR);
        }

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(param);

        ResponseEntity<T> responseEntity = restTemplate.exchange(apiUrl, POST, httpEntity, responseType);

        return responseEntity.getBody();
    }

    protected <T> T deleteWithRequestParam(String apiUrl, Map<String, String> requestParams,
                                           ParameterizedTypeReference<T> responseType) {

        apiUrl = getUrlWithRequestParams(apiUrl, requestParams);

        HttpHeaders headers = new HttpHeaders();
        // 内部微服务之间的调用
        // 默认关闭 AuthorizatinInterceptor
        headers.set(AUTHORIZATION_DISABLE_HEADER, "true");

        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<T> resEntityVeh = restTemplate.exchange(apiUrl, DELETE, httpEntity, responseType);

        return resEntityVeh.getBody();
    }
}
