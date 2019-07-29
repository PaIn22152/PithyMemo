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
var hasPwd = !TextUtils.isEmpty(pwd)//是否设置了密码
var currentMid by Preference("curMid", 1)//当前的备忘录id