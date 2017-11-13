package org.andy.optional;

import org.apache.commons.lang3.tuple.Pair;

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

        Pair pair= Pair.of("test",null);
        Pair pair1=Pair.of(null,"消息不正确");

        Optional optional=Optional.ofNullable(pair.getLeft());
        System.out.println(optional.orElse("有值就不输入"));

        Optional optional1=Optional.ofNullable(pair1.getLeft());
        System.out.println(optional1.orElse("有值就不输入"));
    }
}
