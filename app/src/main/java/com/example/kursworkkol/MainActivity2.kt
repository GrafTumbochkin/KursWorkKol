package com.example.kursworkkol

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        var sp = getSharedPreferences("PC", Context.MODE_PRIVATE)
        sp.edit().putString("TY","-9").commit()
        var emailname:TextView = findViewById(R.id.emailname)
        emailname.text = sp.getString("Email", "Не загрузилось")
        var logout: Button = findViewById(R.id.logout)
        logout.setOnClickListener {
            sp.edit().putString("TY","-9").commit()
            Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}