package org.andy.hibernateValidator.constraint.annoation;


import org.andy.hibernateValidator.constraint.CheckMessageTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {CheckMessageTypeValidator.class})
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckMessageType {
    String message() default "{messagetTypeErr}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
