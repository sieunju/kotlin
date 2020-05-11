package com.hmju.kotlin.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Description: Base Activity Class.
 *
 * Created by juhongmin on 2020/05/11
 */
abstract class BaseActivity : AppCompatActivity(){
    protected val mContext: Context = this
    protected val mActivity: Activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}