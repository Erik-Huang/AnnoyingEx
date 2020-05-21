package com.syaphen.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var queue: RequestQueue
    private lateinit var messageList: List<String>
    private val url: String = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchJson({ messageList ->
            // Initialize song list fragment
            this.messageList = messageList
        }, {
            Toast.makeText(this, "Failed to fetch the song list", Toast.LENGTH_SHORT).show()
        })
    }


    fun fetchJson(onDataReady: (List<String>) -> Unit, onError: (() -> Unit)? = null) {
        queue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(Request.Method.GET, url, null,
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
