package com.hmju.kotlin.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Description : BaseFragment Class
 *
 * Created by QTZZ772 on 2020-05-12
 */
abstract class BaseFragment : Fragment(){

    protected var mContext: Context? = null
    protected var mPosition: Int = 0
    protected var mRootView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPosition = arguments!!.getInt("pos",0)
    }
}