package com.example.whitecardmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChoseLoginActivity : AppCompatActivity() {
    lateinit var officer_btn:Button
    lateinit var admin_btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chose_login)
        officer_btn=findViewById(R.id.officer_btn)
        admin_btn=findViewById(R.id.admin_btn)

        officer_btn.setOnClickListener {
            val intent =Intent(this@ChoseLoginActivity,OffcierLoginActivity::class.java)
            startActivity(intent)
        }

        admin_btn.setOnClickListener {
            val intent =Intent(this@ChoseLoginActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}