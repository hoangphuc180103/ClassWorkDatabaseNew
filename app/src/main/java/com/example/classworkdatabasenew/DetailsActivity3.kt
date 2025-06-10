package com.example.classworkdatabasenew

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream

class DetailsActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details3)

        val result = findViewById<TextView>(R.id.resultView)
        val back = findViewById<Button>(R.id.btnBack)

        try {
            val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val myFile = File(folder, "user_details")
            val fstream = FileInputStream(myFile)

            val buffer = StringBuilder()
            var i: Int
            while (fstream.read().also { i = it } != -1) {
                buffer.append(i.toChar())
            }
            fstream.close()

            val details = buffer.toString().split("\n")
            result.text = "Name: ${details[0]}\nPassword: ${details[1]}"
        } catch (e: Exception) {
            e.printStackTrace()
        }

        back.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
