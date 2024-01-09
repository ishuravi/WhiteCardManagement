package com.example.whitecardmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var user_txt:EditText
    lateinit var editTextPassword:EditText
    lateinit var buttonLogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        user_txt=findViewById(R.id.user_txt)
        editTextPassword=findViewById(R.id.editTextPassword)
        buttonLogin=findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val user=user_txt.text.toString()
            val pass=editTextPassword.text.toString()

            if (user.isEmpty()){
                user_txt.requestFocus()
                user_txt.error="Username is empty"
            }else if (pass.isEmpty()){
                editTextPassword.requestFocus()
                editTextPassword.error="Password is empty"
            }else {
                if (user=="admin" && pass=="admin"){
                    Toast.makeText(this@LoginActivity,"Login successfully",Toast.LENGTH_SHORT).show()
                    val intent=Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this@LoginActivity,"Username or password is invalid ",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}