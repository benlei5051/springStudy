package test.andy.exception;


import test.andy.response.MessageCode;
import test.andy.response.WsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Iterator;

/**
 * Created by zhengjun.jing on 7/14/2017.
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 处理JSR303 验证异常 exception,API入参和出参的数据验证时发生的异常
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public WsResponse errorHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
        LOG.error("error", exception);
        StringBuilder errorMsg = new StringBuilder();
        for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                errorMsg.append("[" + fieldError.getField() + " = " + fieldError.getRejectedValue() + "]" + fieldError.getDefaultMessage() +"; ");
            } else {
                errorMsg.append(objectError.toString() + "; ");
            }
        }
        return WsResponse.failure(MessageCode.COMMON_PARAMETER_ERROR, errorMsg.toString());
    }

    /**
     * 处理 ConstraintViolationException的异常
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public WsResponse errorHandler(HttpServletRequest request, ConstraintViolationException exception) {
        StringBuilder errorMsg = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {

            Iterator<Path.Node> iterator = constraintViolation.getPropertyPath().iterator();
            String name = iterator.next().getName();
            while (iterator.hasNext()) {
                name = iterator.next().getName();
            }

            errorMsg.append("[" + name + " = " + constraintViolation.getInvalidValue() + "]" + constraintViolation.getMessage() +"; ");

        }
        LOG.error("error", exception);
        return WsResponse.failure(MessageCode.COMMON_PARAMETER_ERROR, errorMsg.toString());
    }




    /**
     * 没有catch的到 exception统一处理，系统的最后防线
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public WsResponse jsonErrorHandler(HttpServletRequest request, Exception exception) {
        LOG.error("error", exception);
        return WsResponse.failure(MessageCode.COMMON_UNKNOWN_ERROR, exception.getMessage());
    }

}
