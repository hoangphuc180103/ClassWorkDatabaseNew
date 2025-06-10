package com.example.classworkdatabasenew

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val btnLab12_1 = findViewById<Button>(R.id.btnLab12_1)
        val btnLab12_2 = findViewById<Button>(R.id.btnLab12_2)
        val btnLab12_3 = findViewById<Button>(R.id.btnLab12_3)
        val btnLab12_4 = findViewById<Button>(R.id.btnLab12_4)

        btnLab12_1.setOnClickListener {
            val intent = Intent(this, Activity_main1::class.java)
            startActivity(intent)
        }
        btnLab12_2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        btnLab12_3.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        btnLab12_4.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}