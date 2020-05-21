package com.syaphen.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.syaphen.annoyingex.managers.AnnoyingManager
import com.syaphen.annoyingex.managers.MessageManager
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
            message_view.text = ""
            imageView.visibility = GONE
        }

        message_view.text = intent.getStringExtra("ex_message")
        if (message_view.text != "") {
            imageView.visibility = VISIBLE
        } else {
            imageView.visibility = GONE
        }
    }



}
