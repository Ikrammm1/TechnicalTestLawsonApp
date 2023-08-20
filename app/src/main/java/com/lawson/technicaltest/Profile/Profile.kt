package com.lawson.technicaltest.Profile

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.lawson.technicaltest.Login.Login
import com.lawson.technicaltest.R

class Profile : AppCompatActivity() {

    private lateinit var profil : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val Email = findViewById<EditText>(R.id.EdEmail)
        val Fullname = findViewById<EditText>(R.id.EdFullname)
        val Address = findViewById<EditText>(R.id.EdAddress)
        val Phone = findViewById<EditText>(R.id.EdPhone)
        val Birth = findViewById<EditText>(R.id.EdBirth)
        val btnLogout = findViewById<Button>(R.id.BtnLogout)

        profil = getSharedPreferences("Login_Session", MODE_PRIVATE)

        Email.setText(profil.getString("email", null))
        Fullname.setText(profil.getString("full_name", null))
        Address.setText(profil.getString("address", null))
        Phone.setText(profil.getString("phone", null))
        Birth.setText(profil.getString("date_of_birth", null))

        //fungsi logout
        btnLogout.setOnClickListener{
            //menghapus session
            profil.edit().clear().commit()

            val kelogin = Intent (this@Profile, Login::class.java)
            startActivity(kelogin)
        }
    }
}
