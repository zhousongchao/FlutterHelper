/*
package com.zsc.flutter.help.utils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

*/
/**
 * Json解析基类，所有的Json解析
 *
 * @author Zsc
 * @date 2017/9/15
 *//*

val GSON by lazy { Gson() }

const val GSON_TAG = "GsonUtils"

fun Any?.toJson(): String {
    return GSON.toJson(this)
}

inline fun <reified T> String?.toObject(): T? {
    try {
        return GSON.fromJson(this, T::class.java)
    } catch (e: JsonSyntaxException) {
    } catch (e: Exception) {
    }
    return null
}

inline fun <reified T> String?.toObjList(): MutableList<T> {
    try {
        val array = GSON.fromJson(this, Array<T>::class.java)
        return array.toMutableList()
    } catch (e: JsonSyntaxException) {
    } catch (e: Exception) {
    }
    return mutableListOf()
}*/
