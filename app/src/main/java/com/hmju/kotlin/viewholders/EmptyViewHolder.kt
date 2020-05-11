package com.hmju.kotlin.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hmju.kotlin.R

/**
 * kotlinStudy
 * Class: EmptyViewHolder
 * Created by jsieu on 2019-07-18.
 *
 * Description:
 */
class EmptyViewHolder(itemView: View) : BaseViewHolder<String>(itemView) {

    companion object {
        fun newInstance(parent: ViewGroup): EmptyViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_empty, parent, false)
            return EmptyViewHolder(view)
        }
    }

    override fun onBindView(pos: Int, data: String) {
        
    }
}