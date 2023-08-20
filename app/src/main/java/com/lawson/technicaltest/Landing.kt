package com.lawson.technicaltest

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.lawson.technicaltest.Dashboard.Dashboard
import com.lawson.technicaltest.Login.Login
import com.lawson.technicaltest.Register.Register

class Landing : AppCompatActivity() {

    private lateinit var profil : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val BtnLogin = findViewById<Button>(R.id.BtnLogin)
        val BtnRegister = findViewById<Button>(R.id.BtnRegister)

        //cek session
        profil = getSharedPreferences("Login_Session", MODE_PRIVATE)
        if(profil.getString("user_id",null) !=null) {
            startActivity(Intent(this@Landing, Dashboard::class.java))
            finish()
        }

        BtnLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))

        }
        BtnRegister.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }
}
