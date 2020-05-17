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
class FourFragment : BaseFragment() {

    private var mTvTitle: TextView? = null

    companion object {

        fun newInstance(pos: Int): FourFragment {
            val fragment = FourFragment()
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
        mRootView = inflater.inflate(R.layout.fragment_four, container, false)

        mTvTitle = mRootView!!.findViewById(R.id.tv_title)
        mTvTitle!!.text = "Index $mPosition"

        return mRootView
    }
}