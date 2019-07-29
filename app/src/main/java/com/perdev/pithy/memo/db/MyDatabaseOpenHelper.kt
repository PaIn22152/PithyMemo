package com.perdev.pithy.memo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.perdev.pithy.memo.bean.MemoBean
import org.jetbrains.anko.db.*

/**
 * Project    PithyMemo-git
 * Path       com.perdev.pithy.memo.db
 * Date       2019/07/29 - 15:25
 * Author     Payne.
 * About      类描述：
 */

class MyDatabaseOpenHelper private constructor(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VER) {

    init {
        instance = this
    }

    companion object {
        val DB_NAME = "pm_db"
        val DB_VER = 1
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context) = instance ?: MyDatabaseOpenHelper(ctx.applicationContext)
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        createMemoTab(db)
    }


    private fun createMemoTab(db: SQLiteDatabase) {
        db.createTable(
            MemoBean.TABLE_NAME, true,
            MemoBean.COLUMN_ID to INTEGER + PRIMARY_KEY + UNIQUE,
            MemoBean.COLUMN_CONTENT to TEXT,
            MemoBean.COLUMN_LOCK to INTEGER,
            MemoBean.COLUMN_CTIME to TEXT,
            MemoBean.COLUMN_MTIME to TEXT
        )
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
//        db.dropTable("User", true)
    }


}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(this)




