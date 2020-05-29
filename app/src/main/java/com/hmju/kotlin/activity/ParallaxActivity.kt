package com.hmju.kotlin.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hmju.kotlin.R
import com.hmju.parallaxviewholder.adapters.DumpAdapter

/**
 * Description: Parallax Activity Class.
 *
 * Created by juhongmin on 2020/05/11
 */
class ParallaxActivity : BaseActivity() {

    private val mRvContents: RecyclerView by lazy{
        findViewById<RecyclerView>(R.id.rv_contents)
    }
    private val mAdapter: DumpAdapter = DumpAdapter(mContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parallax)
        setData()
    }

    private fun setData(){
        mRvContents.layoutManager = LinearLayoutManager(mContext)
        mAdapter.dumpData()

        mRvContents.adapter = mAdapter
    }
}
