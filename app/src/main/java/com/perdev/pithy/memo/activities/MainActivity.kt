package com.perdev.pithy.memo.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.thread.EventThread
import com.perdev.pithy.memo.R
import com.perdev.pithy.memo.db.MemoBean
import com.perdev.pithy.memo.db.MemoBeanDB
import com.perdev.pithy.memo.db.title
import com.perdev.pithy.memo.events.MemoSaveEvent
import com.perdev.pithy.memo.utils.*
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class MainActivity : AppCompatActivity() {

    var mMainUI: MainUI? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainUI = MainUI()
        mMainUI!!.setContentView(this@MainActivity)
        RxBus.get().register(this@MainActivity)

    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.get().unregister(this@MainActivity)
    }


    @Subscribe(thread = EventThread.MAIN_THREAD)
    fun rxCallback(event: MemoSaveEvent) {
        logD("rxCallback  " + MemoBeanDB.queryAllMemos(this@MainActivity))

        if (mMainUI != null) {
            mMainUI!!.notifyData()
        }

    }


    inner class MainUI : AnkoComponent<MainActivity> {

        var mData: MutableList<MemoBean>? = null
        var mAdapter: MyAdapter? = null

        fun notifyData() {
            logD("")
            if (mData != null && mAdapter != null) {
                mData!!.clear()
                mData!!.addAll(MemoBeanDB.queryAllMemos(this@MainActivity).toMutableList())
                mAdapter!!.notifyDataSetChanged()
            }
        }

        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                lparams(matchParent, matchParent)
                backgroundColorResource = R.color.bg

                linearLayout {
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

                swipeRefreshLayout {
                    recyclerView {
                        layoutManager = LinearLayoutManager(ui.ctx)
                        lparams(width = matchParent, height = wrapContent)
                        mData = MemoBeanDB.queryAllMemos(this@MainActivity).toMutableList()
                        mAdapter = MyAdapter(
                            ui.ctx,
                            mData!!
                        )
                        adapter = mAdapter
                    }
                    setOnRefreshListener {
                        notifyData()
                        isRefreshing = false
                    }
                }.lparams(weight = 1f)


                linearLayout {
                    lparams(matchParent, dip(item_height))
                    backgroundColorResource = R.color.grey_item
                    gravity = Gravity.CENTER
                    imageView(R.mipmap.add) {
                        padding = dip(image_padding)
                    }.lparams(dip(item_height), dip(item_height))
                    onClick {
                        DetailActivity.add(this@MainActivity)
                    }
                }

            }


        }
    }


    class MyAdapter(
        private val context: Context,
        private val mData: MutableList<MemoBean>
    ) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

        // 创建Holder对象
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
            // 根据anko生成itemView，并且给itemView的tag赋值，从而取得MyHolder
            return AdapterUI().createView(AnkoContext.create(context, p0)).tag as MyHolder
        }

        override fun getItemCount(): Int {
            return mData.size
        }

        // 绑定holder，呈现UI
        override fun onBindViewHolder(holder: MyHolder, p1: Int) {
            holder.tv_name.text = mData[p1].title
            holder.itemView.setOnClickListener {
                logD(mData[p1].toString())
                DetailActivity.edit(context, mData[p1])
            }
        }

        class MyHolder(view: View, val tv_name: TextView) : RecyclerView.ViewHolder(view)

        class AdapterUI : AnkoComponent<ViewGroup> {
            override fun createView(ui: AnkoContext<ViewGroup>): View {
                var tv_name: TextView? = null
                val item_view = with(ui) {
                    relativeLayout {
                        lparams(width = matchParent, height = dip(item_height + item_interval))
                        tv_name = textView {
                            textSize = text_size
                            textColorResource = R.color.white
                            backgroundColorResource = R.color.grey_item
                            gravity = Gravity.CENTER
                            lines=1
                        }.lparams(width = matchParent, height = dip(item_height + item_interval)) {
                            // 设置外边距
                            topMargin = dip(item_interval)
                        }
                    }
                }
                // 返回itemView，并且通过tag生成MyHolder
                item_view.tag = MyHolder(item_view, tv_name = tv_name!!)
                return item_view
            }
        }
    }
}
