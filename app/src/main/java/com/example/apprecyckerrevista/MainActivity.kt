package com.example.apprecyckerrevista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun mostrarDatos(view: View){
        val intent = Intent(this, MainRevista::class.java)

        startActivity(intent)
    }
}