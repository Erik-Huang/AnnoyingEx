package com.syaphen.annoyingex

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MessageManager(context: Context) {

    private var queue: RequestQueue = Volley.newRequestQueue(context)
    var messageList: List<String>
    private val url: String = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"

    init {
        messageList = emptyList()
        fetchJson({ messageList ->
            this.messageList = messageList
        }, {
            Toast.makeText(context, "Failed to fetch the list of messages", Toast.LENGTH_SHORT).show()
        })
    }

    private fun fetchJson(onDataReady: (List<String>) -> Unit, onError: (() -> Unit)? = null) {
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                // Success
                val gson = Gson()
                val list = gson.fromJson(response.getJSONArray("messages").toString(), List::class.java) as List<String>
                Log.i("Erik", list.toString())
                onDataReady(list)
            }, {
                Log.i("Erik", "Error fetching")
            }
        )
        queue.add(request)
    }
}
