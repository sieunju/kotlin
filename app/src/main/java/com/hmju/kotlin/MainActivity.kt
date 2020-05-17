package com.hmju.kotlin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import com.hmju.kotlin.activity.BaseActivity
import com.hmju.kotlin.utils.Logger
import com.hmju.kotlin.utils.MoveAct
import com.hmju.parallaxviewholder.adapters.DumpAdapter

/**
 * kotlin_github_branch
 * Class: MainActivity
 * Created by jsieu on 2019-09-17.
 *
 * Description: Module Test Activity class
 */
class MainActivity : BaseActivity() , View.OnClickListener {

    private val mLlSelect: LinearLayout by lazy {
        findViewById<LinearLayout>(R.id.ll_select)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFCMToken()

        listOf<String>("간","디","는","멋","있","다").forEach lit@{
            if(it == "디") {
                Logger.d("TEST:: 패스패스")
                return@lit
            }

            Logger.d("TEST:: $it")
        }
        setData()
    }

    /**
     * init FCM Token
     */
    private fun initFCMToken() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = getString(R.string.notification_channel_id)
            val descriptionText = getString(R.string.notification_channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(name, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
        Thread(Runnable {
            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Logger.d("initFCM Fail\t ${task.exception}")
                        return@OnCompleteListener
                    }

                    val token = task.result?.token

                    if (token != null) {
                        Logger.d("initFCM Success\t $token")

                    }

                })
        }).start()
    }

    private fun setData(){
        val moveList: ArrayList<String> = ArrayList()

        moveList.add("점점 펼쳐지는 페이지")
        moveList.add("ViewPager2 페이지")
        moveList.add("검색 키워드 페이지")


        for(i in moveList.indices){
            val button: Button = Button(mContext)
            button.tag = i
            button.text = moveList[i]
            button.setOnClickListener(this)

            mLlSelect.addView(button)
        }
    }

    override fun onClick(v: View?) {
        when {
            v?.tag.toString() == "1" -> {

            }
        }
        when(v?.tag.toString()){
            "0" -> {
                MoveAct.instance.moveParallaxAct(mActivity)
            }
            "1" -> {
                MoveAct.instance.moveViewPager2Act(mActivity)
            }
            "2" -> {
                MoveAct.instance.moveSearchAct(mActivity)
            }
            else ->{
            }
        }
    }
}
