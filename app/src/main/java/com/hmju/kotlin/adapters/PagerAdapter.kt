package com.hmju.kotlin.adapters

import android.app.Activity
import android.content.Context
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.hmju.kotlin.fragments.*
import com.hmju.kotlin.utils.Logger

/**
 * Description : ViewPager2 전용 PagerAdapter
 *
 * Created by QTZZ772 on 2020-05-12
 */
class PagerAdapter : FragmentStateAdapter {

    private val mContext: Context
    private val mIsInfinity: Boolean
    private var mViewPager: ViewPager2? = null
    private var mDataList: ArrayList<String>? = null

    constructor(@NonNull context: Context) : this(context, false)

    constructor(
        @NonNull context: Context,
        isInfinity: Boolean
    ) : super(context as FragmentActivity) {
        mContext = context
        mIsInfinity = isInfinity
    }

    init {

    }

    fun setViewPager(viewPager: ViewPager2) {
        mViewPager = viewPager
    }

    fun setData(dataList: ArrayList<String>) {
        mDataList = dataList
    }

    fun getRealCount(): Int {
        return if (mDataList == null) {
            0
        } else {
            mDataList!!.size
        }
    }

    override fun getItemCount(): Int {
        Logger.d("getItemCount ${getRealCount()}")
        return if (mIsInfinity) {
            getRealCount() * 50
        } else {
            getRealCount()
        }
    }

    override fun createFragment(pos: Int): Fragment {
        Logger.d("createFragment $pos")
        return when (val virtualPos = getVirtualPos(pos)) {
            1 -> TwoFragment.newInstance(virtualPos)
            2 -> ThreeFragment.newInstance(virtualPos)
            3 -> FourFragment.newInstance(virtualPos)
            4 -> FiveFragment.newInstance(virtualPos)
            5 -> SixFragment.newInstance(virtualPos)
            else -> OneFragment.newInstance(virtualPos)
        }
    }

    private fun getVirtualPos(pos: Int): Int {
        return pos % getRealCount()
    }

    fun setCurrentItem(pos: Int) {

    }
}