package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CongratsActivity : AppCompatActivity() {
    lateinit var textViewCon : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congrats)
        textViewCon = findViewById(R.id.textViewCon)
        val s1 : String? = intent.getStringExtra("CON")
        val s2 : String? = intent.getStringExtra("NAME")
        textViewCon.text = getString(R.string.guest_congrats,s1,s2)
    }
}