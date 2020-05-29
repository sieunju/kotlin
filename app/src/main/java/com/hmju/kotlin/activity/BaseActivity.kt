package com.hmju.kotlin.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.hmju.kotlin.utils.Logger

/**
 * Description: Base Activity Class.
 *
 * Created by juhongmin on 2020/05/11
 */
abstract class BaseActivity : AppCompatActivity(){
    protected val mContext: Context by lazy { this }
    protected val mActivity: Activity by lazy { this }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun equals(@Nullable other: Any?): Boolean {
        Logger.d("BaseActivity equals\t" + other!!::class.java.simpleName)
        // 클래스 이름이 같은 경우 true.
        if(other is Activity) {
            return (other::class.java.simpleName == mActivity::class.java.simpleName)
        }
        return false
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}