package com.perdev.pithy.memo.utils

import android.text.TextUtils

/**
 * Project    PithyMemo-git
 * Path       com.perdev.pithy.memo.utils
 * Date       2019/07/07 - 15:35
 * Author     Payne.
 * About      类描述：sp文件实现类
 */
var autoSave by Preference("autoSave", true)//是否自动保存


var pwd by Preference("pwd", "")//密码的md5值
val hasPwd = !TextUtils.isEmpty(pwd)//是否设置了密码
fun checkPwd(inputPwd: String): Boolean {//校验输入的密码是否正确
    return string2Md5(pwd) == string2Md5(inputPwd)
}

var currentMid by Preference("curMid", 1)//当前的备忘录id
