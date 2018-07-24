package org.andy.hibernateValidator.constraint;


import org.andy.hibernateValidator.constraint.annoation.CheckMessageType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class CheckMessageTypeValidator implements ConstraintValidator<CheckMessageType, String> {
    private static final String[] stauts = {"system", "abnormal", "order"};

    @Override
    public void initialize(CheckMessageType constraintAnnotation) {
        // Do nothing
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean flag= Arrays.asList(stauts).stream().map(String::toUpperCase).anyMatch(s -> s.contains(value.toUpperCase()));
        if(flag){
            return true;
        }else{
            context.disableDefaultConstraintViolation();//禁用默认的message的值
            //重新添加错误提示语句
            context
                    .buildConstraintViolationWithTemplate("消息定制化消息生效").addConstraintViolation();
        }
        return false;
    }

}
