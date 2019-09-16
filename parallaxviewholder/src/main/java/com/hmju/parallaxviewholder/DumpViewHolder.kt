package com.hmju.parallaxviewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hmju.parallaxviewholder.structs.DumpStruct

/**
 * kotlin_github_branch
 * Class: DumpViewHolder
 * Created by jsieu on 2019-09-16.
 *
 * Description:
 */
class DumpViewHolder(itemView: View) : BaseViewHolder<DumpStruct>(itemView) {

    companion object {

        fun newInstance(parent: ViewGroup): DumpViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_dump, parent, false)
            view.tag = "tag_apple"
            return DumpViewHolder(view)
        }
    }

    init {
        initView()
    }

    private fun initView() {

    }

    override fun onBindView(pos: Int, data: DumpStruct) {
    }
}