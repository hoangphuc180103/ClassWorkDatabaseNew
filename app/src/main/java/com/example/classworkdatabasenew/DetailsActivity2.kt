package com.example.classworkdatabasenew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException

class DetailsActivity2 : AppCompatActivity() {
    private lateinit var intentBack: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details2)

        val resultView = findViewById<TextView>(R.id.resultView)
        val backBtn = findViewById<Button>(R.id.btnBack)

        try {
            val fstream: FileInputStream = openFileInput("user_details")
            val sbuffer = StringBuffer()
            var i: Int
            while (fstream.read().also { i = it } != -1) {
                sbuffer.append(i.toChar())
            }
            fstream.close()

            val details = sbuffer.toString().split("\n")
            resultView.text = "Name: ${details[0]}\nPassword: ${details[1]}"
        } catch (e: FileNotFoundException) {
            resultView.text = "No saved file found!"
        } catch (e: IOException) {
            resultView.text = "Error reading file!"
        }

        backBtn.setOnClickListener {
            intentBack = Intent(this, HomeActivity::class.java)
            startActivity(intentBack)
        }
    }
}