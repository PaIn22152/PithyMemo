package com.perdev.pithy.memo.widget.recyclerview.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.perdev.pithy.memo.R
import com.perdev.pithy.memo.db.MemoBean
import com.perdev.pithy.memo.widget.recyclerview.MultipleType

/**
 * Project    PithyMemo-git
 * Path       com.perdev.pithy.memo.widget.recyclerview.adapter
 * Date       2019/07/30 - 09:53
 * Author     Payne.
 * About      类描述：
 */
class HomeMemoAdapter(context: Context, dataList: ArrayList<MemoBean>) :
    CommonAdapter<MemoBean>(context, dataList, object : MultipleType<MemoBean> {
        override fun getLayoutId(item: MemoBean, position: Int): Int {
            return R.layout.item_home_memo
        }
    }) {


    fun addData(dataList: ArrayList<MemoBean>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }


    /**
     * 绑定数据
     */
    override fun bindData(holder: ViewHolder, data: MemoBean, position: Int) {

        holder.setText(R.id.tv_ihm_title, data.id.toString())
//        when {
//            data.type == "videoCollectionWithBrief" -> setAuthorInfo(data, holder)
//        }

    }


//    /**
//     * 加载作者信息
//     */
//    private fun setAuthorInfo(item: MemoBean, holder: ViewHolder) {
//        val headerData = item.data?.header
//        /**
//         * 加载作者头像
//         */
//        holder.setImagePath(R.id.iv_avatar, object : ViewHolder.HolderImageLoader(headerData?.icon!!) {
//            override fun loadImage(iv: ImageView, path: String) {
//                GlideApp.with(mContext)
//                    .load(path)
//                    .placeholder(R.mipmap.default_avatar).circleCrop()
//                    .transition(DrawableTransitionOptions().crossFade())
//                    .into(holder.getView(R.id.iv_avatar))
//            }
//
//        })
//        holder.setText(R.id.tv_title, headerData.title)
//        holder.setText(R.id.tv_desc, headerData.description)
//
//        val recyclerView = holder.getView<RecyclerView>(R.id.fl_recyclerView)
//        /**
//         * 设置嵌套水平的 RecyclerView
//         */
//        recyclerView.layoutManager = LinearLayoutManager(mContext as Activity, LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.adapter = FollowHorizontalAdapter(mContext, item.data.itemList, R.layout.item_follow_horizontal)
//
//    }

}