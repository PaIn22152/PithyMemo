package com.perdev.pithy.memo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Parcel
import android.os.Parcelable
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
data class MemoBean(val id: Int, var content: String, var lock: Int, val createTime: String, var modifyTime: String) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(content)
        parcel.writeInt(lock)
        parcel.writeString(createTime)
        parcel.writeString(modifyTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MemoBean> {
        val TABLE_NAME = "memos"

        val COLUMN_ID = "_mid"//id
        val COLUMN_CONTENT = "_content"//内容
        val COLUMN_LOCK = "_lock"//是否加锁    1true  0false
        val COLUMN_CTIME = "_ctime"//创建时间  long->string
        val COLUMN_MTIME = "_mtime"//修改时间  long->string

        override fun createFromParcel(parcel: Parcel): MemoBean {
            return MemoBean(parcel)
        }

        override fun newArray(size: Int): Array<MemoBean?> {
            return arrayOfNulls(size)
        }
    }
}





