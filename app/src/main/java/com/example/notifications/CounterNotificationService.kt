package com.example.notifications


import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

@SuppressLint("ServiceCast")
class CounterNotificationService (
    private val context: Context
 ){

    private val notificationManager = context
        .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(counter: Int){

        val activityIntent = Intent(context, MainActivity::class.java)

        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val incrementIntent = PendingIntent.getBroadcast(
            context,
            2,
            Intent(context, CounterNotificationReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, COUNTER_CHANEL_ID)
            .setSmallIcon(R.drawable.notifications)
            .setContentTitle("Increment Counter")
            .setContentText("The counter is $counter")
            .setContentIntent(activityPendingIntent)
            .addAction(
                R.drawable.notifications,
                "Increment",
                incrementIntent
            )
            .build()

        notificationManager.notify(
            1,
            notification
        )

    }

    companion object{
        const val COUNTER_CHANEL_ID = "counter_channel"
    }
}