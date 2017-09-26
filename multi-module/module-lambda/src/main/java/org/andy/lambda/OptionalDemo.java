package org.andy.lambda;

import java.util.Optional;

/**
 * @author: andy
 * @Date: 2017/9/26 14:30
 * @Description:
 */
public class OptionalDemo {
    public static void main(String[] args){
        Optional emptyOptional = Optional.empty();
        System.out.println(emptyOptional);
// emptyOptional为空，打印结果为false
        System.out.println(emptyOptional.isPresent());

        Optional<String> ofOptional = Optional.of("wang");
// ofOptional有值，打印结果为true
        System.out.println(ofOptional.isPresent());
    }
}
