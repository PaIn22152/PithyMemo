package com.perdev.pithy.memo.activities

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import com.hwangjr.rxbus.RxBus

import com.perdev.pithy.memo.R
import com.perdev.pithy.memo.db.MemoBean
import com.perdev.pithy.memo.db.MemoBeanDB
import com.perdev.pithy.memo.events.MemoSaveEvent
import com.perdev.pithy.memo.utils.autoSave
import com.perdev.pithy.memo.utils.logD
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DetailActivity : BaseActivity() {


    companion object {
        fun edit(context: Context, memo: MemoBean) {
            context.startActivity<DetailActivity>("memo" to memo)
        }

        fun add(context: Context) {
            context.startActivity<DetailActivity>()
        }
    }


    private var memo: MemoBean? = null
    private var add: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        memo = intent.getParcelableExtra<MemoBean>("memo")
        add = memo == null
        if (add) {
            memo = MemoBean()
        }


        DetailUI().setContentView(this@DetailActivity)


    }

    var content: Editable? = null


    private fun checkInputNotEmpty(): Boolean {
        if (content == null) {
            return false
        }
        val c = content.toString().replace(" ", "")
        return c.isNotEmpty()
    }

    private fun save() {


        logD(" savedata  =  " + content.toString())

        if (memo != null) {
            memo!!.content = content.toString()
            memo!!.modifyTime = System.currentTimeMillis().toString()

            logD(" savedata  memo =  " + memo.toString())
            if (add) {
                if (checkInputNotEmpty()) {
                    MemoBeanDB.insertMemo(this@DetailActivity, memo!!)
                }
            } else {
                if (checkInputNotEmpty()) {
                    MemoBeanDB.update(this@DetailActivity, memo!!)
                } else {
                    memo!!.valid = 0//备忘录置为无效
                    MemoBeanDB.update(this@DetailActivity, memo!!)
                }

            }
            RxBus.get().post(MemoSaveEvent())

        }


    }

    inner class DetailUI : AnkoComponent<DetailActivity> {

        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
            relativeLayout {
                lparams(matchParent, matchParent)
                editText(if (memo != null) memo!!.content else "") {
                    hint = "input your memo"
                    gravity = Gravity.START
                    textSize = 25f
                    textColor = R.color.grey_text
                    backgroundColorResource = R.color.colorAccent
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        background = null
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        letterSpacing = 0.1F
                    }

                    // 监听输入框输入情况
                    addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                            content = s
                        }

                        override fun beforeTextChanged(
                            s: CharSequence?,
                            start: Int,
                            count: Int,
                            after: Int
                        ) {
                        }

                        override fun onTextChanged(
                            s: CharSequence?,
                            start: Int,
                            before: Int,
                            count: Int
                        ) {
                        }
                    })
                }.lparams(width = matchParent, height = matchParent) {
                    setMargins(
                        dimen(R.dimen.dp_5), dimen(R.dimen.dp_20),
                        dimen(R.dimen.dp_5), 0
                    )

                }
                verticalLayout {
                    lparams(wrapContent, wrapContent)
                    backgroundColorResource = R.color.grey_item

                    imageView(R.mipmap.save) {
                        onClick {
                            save()
                        }
                    }
                    imageView(R.mipmap.delete) {
                        onClick {

                        }
                    }
                    imageView(if (memo!!.lock == 1) R.mipmap.lock
                    else R.mipmap.unlock) {
                        onClick {

                        }
                    }
                }

            }


        }
    }


    override fun onDestroy() {
        super.onDestroy()

        if (autoSave) save()
    }
}
