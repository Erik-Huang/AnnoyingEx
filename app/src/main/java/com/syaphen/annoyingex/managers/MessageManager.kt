package com.syaphen.annoyingex.managers

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.syaphen.annoyingex.JSONFetcher
import java.util.concurrent.TimeUnit

class MessageManager(context: Context) {

    var messageList: List<String> = emptyList()
    private var workManager = WorkManager.getInstance(context)
    private val fetchErrorMsg = "Sorry, your ex is offline, there's no message from her right now"

    init {
        // During initiation, fetch message list once
        val fetcher = JSONFetcher(context)
        fetcher.fetchJson({ messageList ->
            this.messageList = messageList
        }, {
            Toast.makeText(context, fetchErrorMsg, Toast.LENGTH_SHORT).show()
        })
        // start a regular update that has a repeating interval of 2 days
        startRegularUpdate()
    }

    // A repeating JSON fetch request that performs every 2 days
    private fun startRegularUpdate() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<MessageFetchingWorker>(2, TimeUnit.DAYS)
            .setInitialDelay(2, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        workManager.enqueue(workRequest)
    }
}
