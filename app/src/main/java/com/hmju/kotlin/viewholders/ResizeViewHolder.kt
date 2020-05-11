package com.hmju.kotlin.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hmju.kotlin.R
import com.kotlinstudy.structs.ResizeStruct

/**
 * kotlinStudy
 * Class: ResizeViewHolder
 * Created by jsieu on 2019-07-18.
 *
 * Description:
 */
class ResizeViewHolder(itemView: View) : BaseViewHolder<ResizeStruct>(itemView) {

    companion object {
        fun newInstance(parent: ViewGroup) : ResizeViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_resize,parent,false)
            return ResizeViewHolder(view)
        }
    }

    init{
        initView()
    }

    private fun initView() {

    }

    override fun onBindView(pos: Int, data: ResizeStruct) {

    }
}