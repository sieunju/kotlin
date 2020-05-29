package com.hmju.kotlin.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hmju.kotlin.R
import com.hmju.kotlin.dataModels.AppleStruct

/**
 * kotlinStudy
 * Class: AppleViewHolder
 * Created by jsieu on 2019-07-11.
 *
 * Description: AppleViewHolder Class
 */
class AppleViewHolder(itemView: View) : BaseViewHolder<AppleStruct>(itemView) {

    companion object {

        fun newInstance(parent: ViewGroup): AppleViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_apple, parent, false)
            view.tag = "tag_apple"
            return AppleViewHolder(view)
        }
    }

    init {
        initView()
    }

    private fun initView(){

    }

    override fun onBindView(pos: Int, data: AppleStruct) {
        
    }
}