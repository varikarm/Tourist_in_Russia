package com.example.tourist_in_russia

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ContentActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_layout)
        var textView = findViewById<TextView>(R.id.textView)
        var textView3 = findViewById<TextView>(R.id.textView3)
        var im1 = findViewById<ImageView>(R.id.im1)

        textView.text = intent.getStringExtra("title")
        textView3.text = intent.getStringExtra("content")
        im1.setImageResource(intent.getIntExtra("image",R.drawable.common_full_open_on_phone))

    }
}