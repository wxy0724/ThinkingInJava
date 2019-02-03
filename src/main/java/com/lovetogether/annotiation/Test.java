package com.lovetogether.annotiation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 定义注解应用于什么地方
@Retention(RetentionPolicy.RUNTIME) // 定义注解在哪一级可以用 源代码（SOURCE） 类文件（CLASS） 运行时（RUNTIME）
public @interface Test {
}
