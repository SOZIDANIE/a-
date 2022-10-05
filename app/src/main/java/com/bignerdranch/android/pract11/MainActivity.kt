package com.bignerdranch.android.pract11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, spisok :: class.java)
        val intent1 = Intent(this, zapomnit :: class.java)
        var b1 = findViewById<Button>(R.id.b1)
        var b2 = findViewById<Button>(R.id.b2)
        var b3 = findViewById<Button>(R.id.b3)
        b1.setOnClickListener {
            startActivity(intent1)
        }
        b2.setOnClickListener {
            startActivity(intent)
        }
        /*b3.setOnClickListener{
            startActivity(intent1)
            intent1.putExtra("number", -2)
        }*/
    }
}