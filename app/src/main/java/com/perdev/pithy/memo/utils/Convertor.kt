package com.perdev.pithy.memo.utils

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and

/**
 * Project    PithyMemo-git
 * Path       com.perdev.pithy.memo.utils
 * Date       2019/07/29 - 17:31
 * Author     Payne.
 * About      类描述：
 */


/**
 * 将字符串转成MD5值
 */
fun string2Md5(text: String): String {
    try {
        //获取md5加密对象
        val instance: MessageDigest = MessageDigest.getInstance("MD5")
        //对字符串加密，返回字节数组
        val digest: ByteArray = instance.digest(text.toByteArray())
        var sb = StringBuffer()
        for (b in digest) {
            //获取低八位有效值
            var i: Int = b.toInt() and 0xff
            //将整数转化为16进制
            var hexString = Integer.toHexString(i)
            if (hexString.length < 2) {
                //如果是一位的话，补0
                hexString = "0$hexString"
            }
            sb.append(hexString)
        }
        return sb.toString()

    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}


