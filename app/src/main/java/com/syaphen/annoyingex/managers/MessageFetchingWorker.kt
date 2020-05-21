package com.syaphen.annoyingex.managers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.syaphen.annoyingex.AnnoyingExApp
import com.syaphen.annoyingex.JSONFetcher

class MessageFetchingWorker(private val context: Context, workParams: WorkerParameters): Worker(context , workParams) {

    override fun doWork(): Result {
        val fetcher = JSONFetcher(context)
        fetcher.fetchJson({ messageList ->
            // On success, update the message list
            (context.applicationContext as AnnoyingExApp).messageManager.messageList = messageList
        }, {
            Log.i("Erik", "There's an error in fetching")
        })
        return Result.success()
    }

}