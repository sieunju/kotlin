package com.hmju.kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.hmju.parallaxviewholder.adapters.DumpAdapter

/**
 * kotlin_github_branch
 * Class: MainActivity
 * Created by jsieu on 2019-09-17.
 *
 * Description: CustomBehavior Module And ParallaxViewHolder Module Test Activity class
 */
class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

    private val mRvContents : RecyclerView by lazy { findViewById<RecyclerView>(R.id.rv_contents) }
    private val mAdapter : DumpAdapter = DumpAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun setData() {

        for(i in 1..50){

        }
    }

    fun moveAct(moveClass: Class<*>?) {
        val intent = Intent(this, moveClass)
        startActivity(intent)
    }
}
