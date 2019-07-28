package com.perdev.pithy.memo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.perdev.pithy.memo.utils.Preference

/**
 * Project    Memo
 * Path       com.perdev.pithy.memo
 * Date       2019/07/02 - 17:17
 * Author     Payne.
 * About      类描述：
 */
class MainActivity : AppCompatActivity() {
//    // 委托属性，Preference把取值和存值的操作代理给variable，我们对userId的赋值和取值最终是操作的Preference得setValue和getValue函数。
//    private var variable by Preference("keyName", "10")
//    //由于已经能够推断出variable的类型是String，所以这里的variable省去了类型。
//    //完整写法如下：
//    //private var variable: String by Preference("keyName", "10")
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        tvShowResult.text = variable
//
//        btnSave.setOnClickListener {
//            variable = "20"
//            Log.i("info", "保存成功")
//        }
//
//        btnRead.setOnClickListener {
//            tvShowResult.text = "new-" + variable
//        }
//    }
}