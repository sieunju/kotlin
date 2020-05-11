package com.hmju.kotlin.viewholders

import android.content.Context
import android.view.View
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView

/**
 * Description: BaseViewHolder Class
 *
 * Created by juhongmin on 2020/05/11
 */
abstract class BaseViewHolder<T>(val mRootView: View) : RecyclerView.ViewHolder(mRootView) {

    abstract fun onBindView(pos: Int,@Nullable data: T)

    protected val mContext: Context = mRootView.context
    protected var mData: T? = null

    init {

    }

    fun initCommon() {

    }
}