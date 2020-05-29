package com.hmju.kotlin.fcm

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.hmju.kotlin.MainActivity
import com.hmju.kotlin.R
import com.hmju.kotlin.utils.Logger
import java.lang.Exception

/**
 * Description :
 *
 * Created by hmju on 2020-05-15
 */
class MyFirebaseMessagingService : FirebaseMessagingService(){

    private val KEY_TITLE = "title"
    private val KEY_MSG = "message"
    private val KEY_IMG_URL = "img_url"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMsg: RemoteMessage) {
        try{
            val title = remoteMsg.data[KEY_TITLE]
            val message = remoteMsg.data[KEY_MSG]
//                val imgUrl = if(remoteMsg.data[KEY_IMG_URL] == null) "" else remoteMsg.data[KEY_IMG_URL]
            Logger.d("Title$title")
            Logger.d("Message$message")
//                Logger.d("ImgUrl$imgUrl")

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT)
            val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationBuilder = NotificationCompat.Builder(this,"Message")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setSound(defaultSound)
                .setContentIntent(pendingIntent)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
        } catch (ex: Exception){
            Logger.d("Error\t${ex.message}")
        }
    }
}