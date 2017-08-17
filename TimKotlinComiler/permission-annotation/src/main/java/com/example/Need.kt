/*
 * Created by 动脑科技-Tim on 17-6-27 下午9:11
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-6-27 下午9:11
 */

package com.example

import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Retention

//自定义的注解


@Target(AnnotationTarget.FUNCTION)  //元注解  作用声明自定义的注解 方法，class 属性
@Retention(RetentionPolicy.SOURCE) //元注解 指明注解保留到哪个阶段
annotation class Need(val values: Array<String>)
