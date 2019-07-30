package com.perdev.pithy.memo.db

import android.content.Context
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update

/**
 * Project    PithyMemo-git
 * Path       com.perdev.pithy.memo.db
 * Date       2019/07/29 - 16:16
 * Author     Payne.
 * About      类描述：
 */

object MemoBeanDB {
    fun insertMemo(context: Context, bean: MemoBean) {
        context.database.use {
            insert(
                MemoBean.TABLE_NAME,
                MemoBean.COLUMN_ID to bean.id,
                MemoBean.COLUMN_CONTENT to bean.content,
                MemoBean.COLUMN_LOCK to bean.lock,
                MemoBean.COLUMN_CTIME to bean.createTime,
                MemoBean.COLUMN_MTIME to bean.modifyTime
            )
        }
    }

    fun queryAllMemos(context: Context): List<MemoBean> {
        val rowParser = classParser<MemoBean>()
        return context.database.writableDatabase
            .select(MemoBean.TABLE_NAME)
            .groupBy(MemoBean.COLUMN_ID)
            .parseList(rowParser)
    }

    fun queryMemo(context: Context, id: Int): MemoBean {
        val rowParser = classParser<MemoBean>()
        return context.database.writableDatabase
            .select(MemoBean.TABLE_NAME)
            .whereArgs(
                MemoBean.COLUMN_ID + " = {mid}",
                "mid" to id
            ).parseSingle(rowParser)
    }


    fun update(context: Context, bean: MemoBean) {
        context.database.writableDatabase
            .update(
                MemoBean.TABLE_NAME,
                MemoBean.COLUMN_ID to bean.id,
                MemoBean.COLUMN_CONTENT to bean.content,
                MemoBean.COLUMN_LOCK to bean.lock,
                MemoBean.COLUMN_CTIME to bean.createTime,
                MemoBean.COLUMN_MTIME to bean.modifyTime
            )
            .whereArgs(
                MemoBean.COLUMN_ID + " = {mid}",
                "mid" to bean.id
            )
            .exec()
    }
}
