package com.hmju.kotlin.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hmju.kotlin.R
import com.kotlinstudy.structs.OrangeStruct

/**
 * kotlinStudy
 * Class: OrangeViewHolder
 * Created by jsieu on 2019-07-18.
 *
 * Description:
 */
class OrangeViewHolder(itemView: View) : BaseViewHolder<OrangeStruct>(itemView) {

    companion object {
        fun newInstance(parent: ViewGroup): OrangeViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_orange, parent, false)
            return OrangeViewHolder(view)
        }
    }

    init {
        initView()
    }

    private fun initView() {

    }

    override fun onBindView(pos: Int, data: OrangeStruct) {

    }
}