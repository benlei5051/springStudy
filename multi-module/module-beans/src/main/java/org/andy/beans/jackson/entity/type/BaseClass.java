package org.andy.beans.jackson.entity.type;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author: andy
 * @Date: 2017/12/27 16:33
 * @Description:
 */

// 将Java类的名称（“com.myempl.ImplClass”）存储到JSON的一个名称为“class”的属性中

/**
 * 如果你要进行读取、输出操作的对象拥有许多可能的子类型（即表现出多态性），你可能还需要添加一些类型信息。Jackson在反序列化时（读取JSON数据，生成相应的对象）需要这些信息，以便能正确地读取对象的类型。我们可以通过在“基本类型”上添加@JsonTypeInfo注解来完成操作：
 *
 * @JsonTypeInfo 类注解，当输出操作的对象拥有多个子类型且在反序列化时需要添加子类对象的类型信息，使用此注解可以正确地设置子类对象的类型
 * @JsonTypeInfo(use=Id.CLASS,include=As.PROPERTY,property=”class”) 子类类型作为属性, 属性名为class
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class BaseClass {
}
