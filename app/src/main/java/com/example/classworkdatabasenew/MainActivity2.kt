package com.example.classworkdatabasenew

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class MainActivity2 : AppCompatActivity() {
    private lateinit var uname: EditText
    private lateinit var pwd: EditText
    private lateinit var saveBtn: Button
    private lateinit var intentToDetail: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        uname = findViewById(R.id.txtName)
        pwd = findViewById(R.id.txtPwd)
        saveBtn = findViewById(R.id.btnSave)

        saveBtn.setOnClickListener {
            val username = uname.text.toString() + "\n"
            val password = pwd.text.toString()

            try {
                val fstream: FileOutputStream = openFileOutput("user_details", Context.MODE_PRIVATE)
                fstream.write(username.toByteArray())
                fstream.write(password.toByteArray())
                fstream.close()

                Toast.makeText(this, "Details Saved Successfully", Toast.LENGTH_SHORT).show()
                intentToDetail = Intent(this, DetailsActivity2::class.java)
                startActivity(intentToDetail)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}