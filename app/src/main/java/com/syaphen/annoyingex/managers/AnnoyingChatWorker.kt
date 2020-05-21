package com.syaphen.annoyingex.managers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.syaphen.annoyingex.AnnoyingExApp
import com.syaphen.annoyingex.MainActivity
import com.syaphen.annoyingex.R
import kotlin.random.Random

class AnnoyingChatWorker(private val context: Context, workParams: WorkerParameters): Worker(context , workParams) {

    private val messageList: List<String> = (context.applicationContext as AnnoyingExApp).messageManager.messageList
    private val notificationManagerCompat = NotificationManagerCompat.from(context)
    private val retrievingErrorMsg = "unable to retrieve message"
    private val exName = "Oink"

    init {
        createNotificationChannel()
    }

    override fun doWork(): Result {
        postNotification()
        return Result.success()
    }

    private fun postNotification() {
        var exMessage: String = if (messageList.isEmpty()) {
            retrievingErrorMsg
        } else {
            messageList[Random.nextInt(0, messageList.size)]
        }
        val returnIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("ex_message", exMessage)
        }

        val pendingDealsIntent = PendingIntent.getActivity(context, 0, returnIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context,
            FUN_CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_face_black_24dp)
            .setContentTitle(exName)
            .setContentText(exMessage)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingDealsIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(Random.nextInt(), notification)
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Fun Notifications"
            val descriptionText = "When your ex texts you"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(FUN_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

    companion object {
        const val FUN_CHANNEL_ID = "FUNCHANNELID"
    }
}