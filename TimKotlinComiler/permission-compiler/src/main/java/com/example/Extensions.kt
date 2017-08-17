/*
 * Created by 动脑科技-Tim on 17-6-19 下午2:42
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-6-19 下午2:42
 */

package com.example

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName
import javax.annotation.processing.Filer
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import kotlin.properties.Delegates
import com.squareup.kotlinpoet.TypeName.Companion.asTypeName
import java.util.*
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement


var FILER_UTILS: Filer by Delegates.notNull()
var ELEMENT_UTILS: Elements by Delegates.notNull()
var TYPE_UTILS: Types by Delegates.notNull()

val TYPE_ACTIVITY by lazy { ELEMENT_UTILS.getTypeElement("android.app.Activity").asType() }
val TYPE_FRAGMENT by lazy { ELEMENT_UTILS.getTypeElement("android.app.Fragment").asType() }

val TYPE_SUPPORT by lazy {
    try {
        ELEMENT_UTILS.getTypeElement("android.support.v4.app.Fragment").asType()
    } catch (e:Exception) {
        null
    }
}

/**
 * ClassName
 */
val ACTIVITY by lazy { TYPE_ACTIVITY.asTypeName() }
val FRAGMENT by lazy { TYPE_FRAGMENT.asTypeName() }
val SUPPORT by lazy { TYPE_SUPPORT?.asTypeName() }
val STRING = String::class.asTypeName()
val ARRAY = ClassName("kotlin","Array")
val STRINGARRAY = ParameterizedTypeName.get(ARRAY,STRING)

val BUILD: ClassName = ClassName.Companion("android.os","Build")
val PACKAGEMANAGER = ClassName("android.content.pm","PackageManager")

//因为Kotlin这里获得的方法名会有${buildType}  如$app_debug
fun ExecutableElement.simpleName():String {
    val simpleName = simpleName.toString()
    val index = simpleName.lastIndexOf("$")
    if (index > 0)
        return simpleName.substring(0, simpleName.lastIndexOf("$"))
    return simpleName
}

//获得被指定注解定义的元素
fun <A:Annotation> Element.getChildWithAnnotaion(annotationClass:Class<A>):List<ExecutableElement> =
        enclosedElements.filter { it.getAnnotation(annotationClass) != null }.map {
            it as ExecutableElement
        }

//查找匹配的注解方法
fun <A: Annotation> findMatchingMethodForNeeds(needsMethod: NeedMethod,
                                               otherElements: List<ExecutableElement>,
                                               annotationType: Class<A>): ExecutableElement? {
    val values = needsMethod.permissionName
    //查找第一个匹配的方法
    return otherElements.firstOrNull {
        val aType = it.getAnnotation(annotationType)
        if (aType is OnNeverAsk) {
            Arrays.equals(aType.values, values)
        } else if (aType is OnDeined) {
            Arrays.equals(aType.values, values)
        } else {
            false
        }
    }
}

//查找匹配的拒绝权限的方法
fun ClassBuilder.findOnDenied(needsMethod: NeedMethod): ExecutableElement?{
    return findMatchingMethodForNeeds(needsMethod, onDenieds, OnDeined::class.java)
}

//查找匹配的不再提示的方法\
fun ClassBuilder.findOnNeverAsk(needsMethod: NeedMethod): ExecutableElement? {
    return findMatchingMethodForNeeds(needsMethod, onNeverAsk, OnNeverAsk::class.java)
}



