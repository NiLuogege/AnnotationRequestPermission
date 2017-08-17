/*
 * Created by 动脑科技-Tim on 17-6-19 下午3:38
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-6-19 下午3:38
 */

package com.example

import javax.lang.model.element.ExecutableElement


class NeedMethod(element: ExecutableElement) {
    //获取权限名
    val permissionName = element.getAnnotation(Need::class.java).values
    //获得定义的方法名
    val methodName = element.simpleName()
}