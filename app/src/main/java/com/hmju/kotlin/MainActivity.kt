package com.hmju.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hmju.parallaxviewholder.adapters.BaseAdapter
import com.hmju.parallaxviewholder.adapters.DumpAdapter
import com.hmju.parallaxviewholder.decoration.StickyItemDecoration
import com.hmju.parallaxviewholder.structs.ParallaxStruct

/**
 * kotlin_github_branch
 * Class: MainActivity
 * Created by jsieu on 2019-09-17.
 *
 * Description: Module Test Activity class
 */
class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

    private val mContext: Context = this

    private val mRvContents : RecyclerView by lazy { findViewById<RecyclerView>(R.id.rv_contents) }
    private val mAdapter : DumpAdapter = DumpAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setData()
    }

    private fun setData() {
        mRvContents.layoutManager = LinearLayoutManager(mContext)
        mAdapter.dumpData()

        mRvContents.addItemDecoration(StickyItemDecoration(mContext).setStickyViewId(R.id.root_v_parallax))
        mRvContents.adapter = mAdapter
    }

    fun moveAct(moveClass: Class<*>?) {
        val intent = Intent(this, moveClass)
        startActivity(intent)
    }
}
