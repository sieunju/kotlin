package com.hmju.kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hmju.kotlin.R

/**
 * Description :
 *
 * Created by QTZZ772 on 2020-05-12
 */
class FiveFragment : BaseFragment() {

    private var mTvTitle: TextView? = null

    companion object {

        fun newInstance(pos: Int): FiveFragment {
            val fragment = FiveFragment()
            val args = Bundle()
            args.putInt("pos", pos)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(R.layout.fragment_five,container,false)

        mTvTitle = mRootView!!.findViewById(R.id.tv_title)
        mTvTitle!!.text = "Index $mPosition"

        return mRootView
    }
}