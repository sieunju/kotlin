package com.hmju.parallaxviewholder.viewholders

import android.content.Context
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

/**
 * kotlin_github_branch
 * Class: BaseViewHolder
 * Created by jsieu on 2019-09-16.
 *
 * Description: ParallaxViewHolder Test 용 BaseViewHolder Class.
 */
abstract class BaseViewHolder<T>(val mRootView: View) : RecyclerView.ViewHolder(mRootView) {

    abstract fun onBindView(pos:Int,@NonNull data:T)

    protected val mContext: Context = mRootView.context
    protected var mData: T? = null
}