package com.hmju.kotlin.activity

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.hmju.kotlin.R
import com.hmju.kotlin.adapters.PagerAdapter
import com.hmju.kotlin.utils.Logger

/**
 * ViewPager2 관련 Activity
 *
 * Create by hmju on 2020.05.12
 */
class ViewPager2Activity : BaseActivity() {

    private val mViewPager: ViewPager2 by lazy {
        findViewById<ViewPager2>(R.id.view_pager)
    }

    private val mAdapter: PagerAdapter by lazy{
        PagerAdapter(mContext,true)
    }

    private val mDataList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)
        setData()
    }

    private fun setData(){
        mDataList.add("ONE")
        mDataList.add("TWO")
        mDataList.add("THREE")
        mDataList.add("FOUR")
        mDataList.add("FIVE")
        mDataList.add("SIX")

        for(tmpData: String in mDataList){
            Logger.d("1111")
        }

        mAdapter.setViewPager(mViewPager)
        mAdapter.setData(mDataList)
        mViewPager.adapter = mAdapter
        val pos : Int = (mDataList.size * 25) + 1
        mViewPager.setCurrentItem(pos,false)
    }
}
