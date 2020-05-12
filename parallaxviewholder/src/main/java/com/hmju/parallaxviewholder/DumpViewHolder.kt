package com.hmju.parallaxviewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hmju.parallaxviewholder.structs.DumpStruct

/**
 * kotlin_github_branch
 * Class: DumpViewHolder
 * Created by jsieu on 2019-09-16.
 *
 * Description: ParallaxViewHolder 테스트를 위한 Dump ViewHolder Class
 */
class DumpViewHolder(itemView: View) : BaseViewHolder<DumpStruct>(itemView) {

    private val mTvTitle: TextView by lazy { mRootView.findViewById<TextView>(R.id.tv_title) }
    private val mTvContent: TextView by lazy { mRootView.findViewById<TextView>(R.id.tv_content) }

    companion object {

        fun newInstance(parent: ViewGroup): DumpViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_dump, parent, false)
            return DumpViewHolder(view)
        }
    }

    override fun onBindView(pos: Int, data: DumpStruct) {
        mTvTitle.text = String.format("%d번째 타이틀", pos)
    }
}