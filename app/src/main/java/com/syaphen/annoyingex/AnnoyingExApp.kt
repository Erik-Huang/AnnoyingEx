package com.syaphen.annoyingex

import android.app.Application
import com.syaphen.annoyingex.managers.AnnoyingManager
import com.syaphen.annoyingex.managers.MessageManager

class AnnoyingExApp: Application() {

    lateinit var annoyingManager: AnnoyingManager
    lateinit var messageManager: MessageManager

    override fun onCreate() {
        super.onCreate()
        annoyingManager = AnnoyingManager(this)
        messageManager = MessageManager(this)
    }


}