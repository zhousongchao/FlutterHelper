package com.zsc.flutter.help.utils

import java.util.regex.Pattern


private val linePattern = Pattern.compile("_(\\w)")
private val humpPattern = Pattern.compile("[A-Z]")

/** 下划线转驼峰  */
fun String.lineToHump(): String {
    val matcher = linePattern.matcher(toLowerCase())
    val sb = StringBuffer()
    while (matcher.find()) {
        matcher.appendReplacement(sb, matcher.group(1).toUpperCase())
    }
    matcher.appendTail(sb)
    return sb.toString()
}

/** 驼峰转下划线(简单写法，效率低于[.humpToLine2])  */
fun humpToLine(str: String): String {
    return str.replace("[A-Z]".toRegex(), "_$0").toLowerCase()
}


/** 驼峰转下划线,效率比上面高  */
fun humpToLine2(str: String): String {
    val matcher = humpPattern.matcher(str)
    val sb = StringBuffer()
    while (matcher.find()) {
        matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase())
    }
    matcher.appendTail(sb)
    return sb.toString()
}

