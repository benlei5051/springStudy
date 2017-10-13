package org.andy.common;

import org.apache.commons.lang3.tuple.Pair;

import javax.swing.text.ParagraphView;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Pair pair=Pair.of("test",null);
        Pair pair1=Pair.of(null,"消息不正确");

        Optional optional=Optional.ofNullable(pair.getLeft());
        System.out.println(optional.orElse("有值就不输入"));

        Optional optional1=Optional.ofNullable(pair1.getLeft());
        System.out.println(optional1.orElse("有值就不输入"));
    }
}
