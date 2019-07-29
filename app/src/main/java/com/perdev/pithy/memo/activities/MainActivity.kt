package com.perdev.pithy.memo.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import com.perdev.pithy.memo.R
import com.perdev.pithy.memo.utils.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainUI().setContentView(this@MainActivity)


    }


    inner class MainUI : AnkoComponent<MainActivity> {

        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                lparams(matchParent, matchParent)
                backgroundColorResource = R.color.bg


                linearLayout() {
                    lparams(matchParent, dip(item_height + status_bar_height)) {
                        setPadding(0, dip(status_bar_height), 0, 0)
                    }
                    gravity = Gravity.CENTER_VERTICAL
                    backgroundColorResource = R.color.home_title

                    textView(R.string.app_name) {
                        textColorResource = R.color.white
                        textSize = title_size

                    }.lparams(wrapContent, wrapContent) {
                        leftMargin = dip(title_left_margin)
                        weight = 1f
                    }

                    imageView(R.mipmap.setting) {
                        onClick {
                            startActivity<SetActivity>()
                        }
                        padding = dip(image_padding)
                    }.lparams(dip(item_height), dip(item_height)) {
                        rightMargin = dip(title_right_margin)
                    }
                }


            }


        }
    }
}
