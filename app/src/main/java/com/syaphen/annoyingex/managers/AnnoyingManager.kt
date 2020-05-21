package com.syaphen.annoyingex.managers

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class AnnoyingManager(private val context: Context) {

    private var workManager = WorkManager.getInstance(context)

    fun startAnnoyingMessages() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<AnnoyingChatWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        workManager.enqueue(workRequest)
    }

    fun endAnnoyingMessages() {
        workManager.cancelAllWork()
    }
}