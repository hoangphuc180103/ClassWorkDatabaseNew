package com.example.classworkdatabasenew

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream

class MainActivity3 : AppCompatActivity() {

    private lateinit var uname: EditText
    private lateinit var pwd: EditText
    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        uname = findViewById(R.id.txtName)
        pwd = findViewById(R.id.txtPwd)
        saveBtn = findViewById(R.id.btnSave)

        saveBtn.setOnClickListener {
            val username = uname.text.toString() + "\n"
            val password = pwd.text.toString()

            try {
                ActivityCompat.requestPermissions(this, arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 1)

                val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val myFile = File(folder, "user_details")
                val fstream = FileOutputStream(myFile)
                fstream.write(username.toByteArray())
                fstream.write(password.toByteArray())
                fstream.close()

                Toast.makeText(this, "Details saved in: ${myFile.absolutePath}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DetailsActivity3::class.java))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}