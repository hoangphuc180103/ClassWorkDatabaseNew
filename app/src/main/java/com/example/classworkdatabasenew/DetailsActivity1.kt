package com.example.classworkdatabasenew

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity1 : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details1)

        val resultView = findViewById<TextView>(R.id.resultView)
        val logoutBtn = findViewById<Button>(R.id.btnLogOut)

        pref = getSharedPreferences("user_details", MODE_PRIVATE)
        editor = pref.edit()

        val username = pref.getString("username", "Guest")
        resultView.text = "Hello, $username"

        logoutBtn.setOnClickListener {
            editor.clear()
            editor.apply()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}