package com.perdev.pithy.memo.bean

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.PRIMARY_KEY
import org.jetbrains.anko.db.TEXT

/**
 * Project    Memo
 * Path       com.perdev.pithy.memo.bean
 * Date       2019/07/02 - 17:50
 * Author     Payne.
 * About      类描述：
 */
data class MemoBean(val id: Int, val content: String, val lock: Int, val createTime: String, val modifyTime: String) {
    companion object {
        val TABLE_NAME = "memos"

        val COLUMN_ID = "_mid"//id
        val COLUMN_CONTENT = "_content"//内容
        val COLUMN_LOCK = "_lock"//是否加锁    1true  0false
        val COLUMN_CTIME = "_ctime"//创建时间  long->string
        val COLUMN_MTIME = "_mtime"//修改时间  long->string

    }
}





