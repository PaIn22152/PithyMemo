package com.perdev.pithy.memo.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View.GONE
import android.view.View.VISIBLE
import com.perdev.pithy.memo.R
import com.perdev.pithy.memo.db.MemoBean
import com.perdev.pithy.memo.db.MemoBeanDB
import com.perdev.pithy.memo.utils.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class SetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SetUI().setContentView(this@SetActivity)
        logD("autoSave = $autoSave")

        insertData()
        queryData()
    }


    fun insertData() {
        val m1 = MemoBean(
            currentMid++,
            "content",
            1,
            System.currentTimeMillis().toString(),
            System.currentTimeMillis().toString()
        )
        MemoBeanDB.insertMemo(this@SetActivity, m1)


    }

    fun queryData() {
        val queryAllMemos = MemoBeanDB.queryAllMemos(this@SetActivity)
        logD(queryAllMemos.toString())

    }


    inner class SetUI : AnkoComponent<SetActivity> {
        //        override fun createView(ui: AnkoContext<SetActivity>): View {
        override fun createView(ui: AnkoContext<SetActivity>) = with(ui) {
            verticalLayout() {
                lparams(matchParent, matchParent)
                backgroundColorResource = R.color.bg
                linearLayout() {
                    lparams(matchParent, dip(item_height + status_bar_height)) {
                        setPadding(0, dip(status_bar_height), 0, 0)
                    }
                    gravity = Gravity.CENTER
                    backgroundColorResource = R.color.home_title

                    textView(R.string.setting) {
                        textColorResource = R.color.white
                        textSize = title_size

                    }.lparams(wrapContent, wrapContent) {
                        leftMargin = dip(item_left_margin)
                    }


                }

                linearLayout() {
                    gravity = Gravity.CENTER_VERTICAL
                    backgroundColorResource = R.color.grey_item
                    textView(R.string.auto_save) {
                        textSize = text_size
                        textColorResource = R.color.grey_text
                    }.lparams(wrapContent, wrapContent) {
                        weight = 1f
                        leftMargin = dip(item_left_margin)
                    }
                    switch {
                        isChecked = autoSave
                        onClick {
                            autoSave = isChecked
                        }

                    }.lparams(wrapContent, wrapContent) {
                        rightMargin = dip(item_right_margin)

                    }
                }.lparams(matchParent, dip(item_height))

                linearLayout() {
                    //set pwd
                    gravity = Gravity.CENTER_VERTICAL
                    backgroundColorResource = R.color.grey_item
                    val input = textView(
                        if (hasPwd) R.string.set_pwd else R.string.modify_pwd
                    ) {
                        textSize = text_size
                        textColorResource = R.color.grey_text
                    }.lparams(wrapContent, wrapContent) {
                        leftMargin = dip(item_left_margin)
                    }
                    onClick {
                        checkPwd()
                        pwd = string2Md5(input.text.toString())
                    }

                }.lparams(matchParent, dip(item_height)) {
                    topMargin = dip(item_interval)
                }

                linearLayout {
                    visibility = if (hasPwd) VISIBLE else GONE
                    gravity = Gravity.CENTER_VERTICAL
                    textView {
                        backgroundColorResource = R.color.grey_item
                    }.lparams(dip(item_left_margin), dip(line_height))
                    textView {
                        backgroundColorResource = R.color.bg
                    }.lparams(wrapContent, dip(line_height)) {
                        weight = 1f
                    }
                }.lparams(matchParent, dip(line_height)) {

                }

                linearLayout {
                    visibility = if (hasPwd) VISIBLE else GONE
                    gravity = Gravity.CENTER_VERTICAL
                    backgroundColorResource = R.color.grey_item
                    textView(R.string.pwd_hints) {
                        textSize = text_size
                        textColorResource = R.color.grey_text
                    }.lparams(wrapContent, wrapContent) {
                        leftMargin = dip(item_left_margin)
                    }

                }.lparams(matchParent, dip(item_height)) {

                }
            }
        }


        private fun checkPwd() {
            alert {
                title = "Check Password!"
                customView {
                    linearLayout() {
                        lparams(matchParent, dip(30))
                        editText {
                            hint = "Input your password"
                            gravity = Gravity.CENTER
                            textSize = 25f
                            textColor = R.color.grey_text
                        }.lparams(matchParent, wrapContent) {
                            setMargins(dip(20), 0, dip(20), 0)
                        }
                    }
                }
                yesButton {

                }
                noButton {

                }
            }.show()
        }
    }


}
