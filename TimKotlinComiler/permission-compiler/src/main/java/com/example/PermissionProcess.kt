/*
 * Created by 动脑科技-Tim on 17-6-27 下午9:25
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-6-27 下午9:25
 */

package com.example

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement

class PermissionProcess: AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): Set<String> {
        return setOf(Need::class.java.canonicalName)
    }

    @Synchronized override fun init(processingEnvironment: ProcessingEnvironment) {
        super.init(processingEnvironment)
        FILER_UTILS = processingEnvironment.filer
        ELEMENT_UTILS = processingEnvironment.elementUtils
        TYPE_UTILS = processingEnvironment.typeUtils
    }

    //对于多个注解处理器的情况， return false 代表当前注解已经处理过的注解 其他的注解处理器不能再重复处理
    override fun process(set: MutableSet<out TypeElement>, roundEnvironment: RoundEnvironment): Boolean {

        //扫描 代码，拿到声明了注解的target
        //生成源代码 处理注解
        roundEnvironment.getElementsAnnotatedWith(Need::class.java).groupBy {
            it.enclosingElement
        }.map {
            ClassBuilder(it.key as TypeElement, it.value.map { NeedMethod(it as ExecutableElement )})
        }.map {
            it.brewKotlin()
        }

        return false
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

}
