package com.example.kursworkkol

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        var sp = getSharedPreferences("PC", Context.MODE_PRIVATE).edit()
        var email:TextView = findViewById(R.id.email)
        var password:TextView = findViewById(R.id.password)
        var button:ConstraintLayout = findViewById(R.id.button)
        button.setOnClickListener {
            if (email.text.isEmpty() || !email.text.contains("@"))
            {
                Toast.makeText(this, "Проверьте поле email!",Toast.LENGTH_LONG).show()
            }
            else if(password.text.isEmpty() || password.text.length<6)
            {
                Toast.makeText(this, "Пароль должен быть больше 5 символов!", Toast.LENGTH_LONG).show()
            }
            else{
                val db = Firebase.firestore
// Create a new user with a first and last name
                val user = hashMapOf(
                    "email" to email.text.toString(),
                    "password" to password.text.toString()

                )

// Add a new document with a generated ID
                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                      sp.putString("Email",email.text.toString()).commit()
                        startActivity(Intent(this, MainActivity2::class.java))
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this,"Не получилось",Toast.LENGTH_LONG).show()

                    }
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}