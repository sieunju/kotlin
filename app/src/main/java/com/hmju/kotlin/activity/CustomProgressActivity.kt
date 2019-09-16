package com.hmju.kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.hmju.customprogressview.CustomProgressView
import com.hmju.kotlin.R

class CustomProgressActivity : AppCompatActivity() , View.OnClickListener{

    private val mProgressBar : CustomProgressView by lazy { findViewById<CustomProgressView>(R.id.v_progress) }

    private var isRun = true
    private var t1 = TestThread()
    private var t2 = TestThread()
    private var t3 = TestThread()
    private var t4 = TestThread()
    private var t5 = TestThread()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_progress)

        findViewById<TextView>(R.id.tv_status).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tv_status -> {
                if(isRun){
                    t1.start()
                    t2.start()
                    t3.start()
                    t4.start()
                    t5.start()
                    isRun = false
                }
            }
        }
    }

    internal inner class TestThread : Thread() {

        override fun run() {
            for (i in 0..99) {
                if (isRun) {
                    try {
                        mProgressBar.incrementProgressBy(1)
                    } catch (ex: Exception) {
                    }
                } else {
                    break
                }
            }
        }
    }
}
