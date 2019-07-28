package com.perdev.pithy.memo.utils

import android.text.TextUtils

/**
 * Project    PithyMemo-git
 * Path       com.perdev.pithy.memo.utils
 * Date       2019/07/07 - 15:35
 * Author     Payne.
 * About      类描述：
 */
var autoSave by Preference("autoSave", true)
var pwd by Preference("pwd", "")
var has_pwd = !TextUtils.isEmpty(pwd)