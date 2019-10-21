package com.perdev.pithy.memo.activities

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.hwangjr.rxbus.RxBus

import com.perdev.pithy.memo.R
import com.perdev.pithy.memo.db.MemoBean
import com.perdev.pithy.memo.db.MemoBeanDB
import com.perdev.pithy.memo.events.MemoSaveEvent
import com.perdev.pithy.memo.utils.*
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

        memo = intent.getParcelableExtra("memo")
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

    private fun invalid() {
        if (memo != null) {
            memo!!.valid = 0
            updateMemo()
        }
    }

    private fun lockOrUnlock() {
        if (memo != null) {
            if (memo!!.lock == 0) {
                memo!!.lock = 1
            } else {
                memo!!.lock = 0
            }
            updateMemo()
        }
    }

    private fun updateMemo() {
        MemoBeanDB.update(this@DetailActivity, memo!!)
        RxBus.get().post(MemoSaveEvent())
    }

    private fun insertMemo() {
        MemoBeanDB.insertMemo(this@DetailActivity, memo!!)
        RxBus.get().post(MemoSaveEvent())
    }

    private fun save() {


        logD(" savedata  =  " + content.toString())

        if (memo != null) {
            memo!!.content = content.toString()
            memo!!.modifyTime = System.currentTimeMillis().toString()

            logD(" savedata  memo =  " + memo.toString())
            if (add) {
                if (checkInputNotEmpty()) {
                    insertMemo()
                }
            } else {
                if (!checkInputNotEmpty()) {
                    memo!!.valid = 0//备忘录置为无效
                }
                updateMemo()
            }


        }


    }

    inner class DetailUI : AnkoComponent<DetailActivity> {

        var lockImg: ImageView? = null

        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
            verticalLayout {
                lparams(matchParent, matchParent)
                linearLayout {
                    lparams(matchParent, dip(item_height) + dimen(R.dimen.dp_25)) {
                        setPadding(
                            0, dimen(R.dimen.dp_25),
                            0, 0
                        )
                    }
                    backgroundColorResource = R.color.grey_item

                    imageView(R.mipmap.save) {
                        onClick {
                            save()
                            toast("save success!")
                        }
                        padding = dip(image_padding)

                    }.lparams(dip(item_height), dip(item_height)) {
                        weight = 1f
                        gravity = Gravity.CENTER
                    }

                    imageView(R.mipmap.delete) {
                        onClick {
                            AlertDialog
                                .Builder(this@DetailActivity)
                                .setNegativeButton("NO") { _, _ ->
                                    run {

                                    }
                                }
                                .setPositiveButton("YES") { _, _ ->
                                    run {
                                        invalid()
                                        finish()
                                    }
                                }
                                .setMessage("Do you want to delete this memo?")
                                .show()

                        }
                        padding = dip(image_padding)
                    }.lparams(dip(item_height), dip(item_height)) {
                        weight = 1f
                        gravity = Gravity.CENTER
                    }
                    lockImg = imageView(
                        if (memo!!.lock == 1) R.mipmap.lock
                        else R.mipmap.unlock
                    ) {
                        onClick {

                            if (memo!!.lock == 0) {
                                val msg = "Do you want to lock this memo?"
                                AlertDialog
                                    .Builder(this@DetailActivity)
                                    .setNegativeButton("NO") { _, _ ->
                                        run {

                                        }
                                    }
                                    .setPositiveButton("YES") { _, _ ->
                                        run {
                                            lockOrUnlock()
                                            lockImg!!.setImageResource(R.mipmap.lock)
                                        }
                                    }
                                    .setMessage(msg)
                                    .show()
                            }else{
                                val msg = "Input password to unlock this memo."
                                AlertDialog
                                    .Builder(this@DetailActivity)
                                    .setNegativeButton("NO") { _, _ ->
                                        run {

                                        }
                                    }
                                    .setPositiveButton("YES") { _, _ ->
                                        run {
                                            lockOrUnlock()
                                            lockImg!!.setImageResource(R.mipmap.lock)
                                        }
                                    }
                                    .setMessage(msg)
                                    .show()
                            }

                        }
                        padding = dip(image_padding)
                    }.lparams(dip(item_height), dip(item_height)) {
                        weight = 1f
                        gravity = Gravity.CENTER
                    }


                }

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
                }
                    .lparams(width = matchParent, height = matchParent) {
                        setMargins(
                            dimen(R.dimen.dp_5), dimen(R.dimen.dp_5),
                            dimen(R.dimen.dp_5), 0
                        )

                    }


            }


        }
    }


    override fun onDestroy() {
        super.onDestroy()

        if (autoSave) save()
    }
}
