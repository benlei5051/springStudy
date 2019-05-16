package org.andy.filter.global;

import cn.hutool.core.exceptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.andy.filter.exception.BusinessException;
import org.andy.filter.vo.ResultData;
import org.andy.filter.vo.ResultStatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wiiyaya
 * @date 2018/11/29.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler
    public final ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        System.out.println("-----------------");
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.OK, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResultData resultData;
        if (ex instanceof BusinessException) {
            ResultStatusCode resultStatusCode = ((BusinessException) ex).getResultStatusCode();
            resultData = ResultData.failed(resultStatusCode.getCode(), resultStatusCode.getMsg());
        } else if (ex instanceof ValidateException
                || ex instanceof ConstraintViolationException
                || ex instanceof ServletRequestBindingException
                || ex instanceof TypeMismatchException
                || ex instanceof BindException
                || ex instanceof HttpMessageNotReadableException
        ) {
            resultData = ResultData.failed(ResultStatusCode.PARAM_ERROR.getCode(), ex.getMessage());
        } else {
            resultData = ResultData.failed(ResultStatusCode.SYS_EXCEPTION.getCode(), ResultStatusCode.SYS_EXCEPTION.getMsg());
        }

        Map<String, String> requestHeader = new LinkedHashMap<>();
        Iterator<String> headerNames = request.getHeaderNames();
        while (headerNames.hasNext()) {
            String headerName = headerNames.next();
            String[] headerValues = request.getHeaderValues(headerName);
            requestHeader.put(headerName, StringUtils.join(headerValues));
        }
        HttpServletRequest httpServletRequest = ((ServletWebRequest) request).getRequest();
        Map<String, String> requestParams = request.getParameterMap().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, o -> StringUtils.join(o.getValue())));
        log.error("======Global error errorUrl[{}], requestParams[{}]======"
                , httpServletRequest.getRequestURI()
                , requestParams
        );
        log.error("======Global error statusCode[{}], statusMessage[{}]======"
                , resultData.getStatusCode()
                , resultData.getStatusMessage()
        );
        log.error("======Global error request header [{}]======"
                , requestHeader
                , ex
        );

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        //永远返回200
        return new ResponseEntity<>(resultData, headers, HttpStatus.OK);
    }

    private Throwable getRootCause(Throwable exception) {
        if (exception.getCause() != null) {
            return getRootCause(exception.getCause());
        }
        return exception;
    }
}
