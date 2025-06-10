package com.example.classworkdatabasenew

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Activity_main1 : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var uname: EditText
    private lateinit var pwd: EditText
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        uname = findViewById(R.id.txtName)
        pwd = findViewById(R.id.txtPwd)
        loginBtn = findViewById(R.id.btnLogin)

        pref = getSharedPreferences("user_details", MODE_PRIVATE)
        editor = pref.edit()

        // Nếu đã lưu user → chuyển luôn sang DetailsActivity
        if (pref.contains("username") && pref.contains("password")) {
            startActivity(Intent(this, DetailsActivity1::class.java))
            finish()
        }

        loginBtn.setOnClickListener {
            val username = uname.text.toString()
            val password = pwd.text.toString()

            if (username == "admin" && password == "admin") {
                editor.putString("username", username)
                editor.putString("password", password)
                editor.apply()

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DetailsActivity1::class.java))
                finish()
            } else {
                Toast.makeText(this, "Credentials are not valid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}