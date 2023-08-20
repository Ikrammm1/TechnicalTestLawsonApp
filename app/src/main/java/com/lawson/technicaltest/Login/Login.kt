package com.lawson.technicaltest.Login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.lawson.technicaltest.API.RetrofitClient
import com.lawson.technicaltest.Dashboard.Dashboard
import com.lawson.technicaltest.Models.ResponseLogin
import com.lawson.technicaltest.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    lateinit var EdEmail : EditText
    private lateinit var profil : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        EdEmail = findViewById(R.id.EdEmail)
        val BtnLogin = findViewById<Button>(R.id.BtnLogin)




        BtnLogin.setOnClickListener {

            when{
                EdEmail.text.toString() == "" ->{
                    EdEmail.error = "Email is required!"
                }

                else -> {
                    getUser()
                }
            }

        }
    }
    private fun getUser(){
        RetrofitClient.instance.login(EdEmail.text.toString()).enqueue(object : Callback<ResponseLogin>{
            override fun onResponse(
                call: Call<ResponseLogin>,
                response: Response<ResponseLogin>
            ) {
                if (response.isSuccessful){
                    //fungsi session
                    getSharedPreferences("Login_Session", MODE_PRIVATE)
                        .edit()
                        .putString("user_id", response.body()?.payload?.user_id)
                        .putString("full_name", response.body()?.payload?.full_name)
                        .putString("address", response.body()?.payload?.address)
                        .putString("phone", response.body()?.payload?.phone)
                        .putString("email", response.body()?.payload?.email)
                        .putString("date_of_birth", response.body()?.payload?.date_of_birth)
                        .apply()

                    if(response.body()?.response == true){
                        startActivity(Intent(this@Login, Dashboard::class.java))
                        finish()
                    }else{
                        Toast.makeText(this@Login, "GAGAL", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@Login, "Maaf Sedang gangguan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Toast.makeText(this@Login, "Maaf Sedang gangguan ", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api Login : ", t.toString())
            }

        })
    }
}
