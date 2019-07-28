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
data class MemoBean(val id: Int, val content: String,val lock:Boolean) {
    companion object {
        val COLUMN_ID = "id"
        val TABLE_NAME = "memos"
        val COLUMN_CONTENT = "content"
        val COLUMN_LOCK = "lock"
    }
}




