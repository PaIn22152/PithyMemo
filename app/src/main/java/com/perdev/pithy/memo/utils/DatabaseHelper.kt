package com.perdev.pithy.memo.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.SyncStateContract.Helpers.insert
import com.perdev.pithy.memo.bean.MemoBean
import org.jetbrains.anko.db.*

/**
 * Project    PithyMemo-git
 * Path       com.perdev.pithy.memo.utils
 * Date       2019/07/06 - 16:14
 * Author     Payne.
 * About      类描述：
 */
class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "LibraryDatabase", null, 1) {
    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun Instance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(database: SQLiteDatabase) {
        database.createTable(MemoBean.TABLE_NAME, true, MemoBean.COLUMN_ID to INTEGER + PRIMARY_KEY, MemoBean.COLUMN_CONTENT to TEXT, MemoBean.COLUMN_LOCK to INTEGER)
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        database.dropTable(MemoBean.TABLE_NAME, true)
    }

    val Context.database: DatabaseHelper
        get() = DatabaseHelper.Instance(applicationContext)


//    fun getUsers(db: ManagedSQLiteOpenHelper): List<MemoBean> = db.use {
//
//        select("Users")
//            .whereSimple("family_name = ?", "John")
//            .doExec()
//            .parseList(UserParser)
//    }



}