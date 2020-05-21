package com.syaphen.annoyingex

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class AnnoyingManager(private val context: Context) {

    private var workManager = WorkManager.getInstance(context)

    fun startAnnoyingMessages() {
        val constraints = Constraints.Builder()
            //.setRequiresCharging(false)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<ChatWorker>(15, TimeUnit.MINUTES)
            .setInitialDelay(2, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        workManager.enqueue(workRequest)
    }

    fun endAnnoyingMessages() {
        workManager.cancelAllWork()
    }
}