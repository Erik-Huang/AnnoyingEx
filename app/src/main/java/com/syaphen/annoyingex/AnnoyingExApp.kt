package com.syaphen.annoyingex

import android.app.Application

class AnnoyingExApp: Application() {

    lateinit var annoyingManager: AnnoyingManager
    lateinit var messageManager: MessageManager

    override fun onCreate() {
        super.onCreate()
        annoyingManager = AnnoyingManager(this)
        messageManager = MessageManager(this)
    }


}