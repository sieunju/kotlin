package com.hmju.kotlin.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hmju.kotlin.R
import com.hmju.kotlin.dataModels.GrapeStruct

/**
 * kotlinStudy
 * Class: GrapeViewHolder
 * Created by jsieu on 2019-07-18.
 *
 * Description:
 */
class GrapeViewHolder(itemView: View) : BaseViewHolder<GrapeStruct>(itemView) {

    companion object {
        fun newInstance(parent: ViewGroup): GrapeViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_grape, parent, false)
            return GrapeViewHolder(view)
        }
    }

    override fun onBindView(pos: Int, data: GrapeStruct) {

    }
}