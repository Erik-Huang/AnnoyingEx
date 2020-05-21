package com.syaphen.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var annoyingManager: AnnoyingManager
    private lateinit var messageManager: MessageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        annoyingManager = (application as AnnoyingExApp).annoyingManager
        messageManager = (application as AnnoyingExApp).messageManager

        repeat_btn.setOnClickListener {
            annoyingManager.startAnnoyingMessages()
        }

        block_btn.setOnClickListener {
            annoyingManager.endAnnoyingMessages()
        }
    }



}
