package com.lawson.technicaltest.City

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.lawson.technicaltest.API.RetrofitClient
import com.lawson.technicaltest.Models.Response
import com.lawson.technicaltest.R
import retrofit2.Call
import retrofit2.Callback

class AddCity : AppCompatActivity() {

    private lateinit var EdCityname: EditText
    private lateinit var EdLatitude: EditText
    private lateinit var EdLongitude: EditText
    private lateinit var BtnAdd : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_city)

        EdCityname = findViewById(R.id.EdCity)
        EdLatitude = findViewById(R.id.EdLatitude)
        EdLongitude = findViewById(R.id.EdLongitude)
        BtnAdd = findViewById(R.id.BtnAdd)

        BtnAdd.setOnClickListener {
            when{
                EdCityname.text.toString() == "" ->{
                    EdCityname.error = "City Name is required!"
                }
                EdLatitude.text.toString() == "" ->{
                    EdLatitude.error = "Latitude is required!"
                }
                EdLongitude.text.toString() == "" ->{
                    EdLongitude.error = "Longitude is required!"
                }else -> {
                    var alertDialog = AlertDialog.Builder(this)
                        .setTitle("Are you sure?")
                        .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->

                            Insert()

                        })
                        .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->  })
                        .show()
                }
            }
        }
    }
    fun Insert(){
        RetrofitClient.instance.AddCity(
            EdCityname.text.toString(),
            EdLatitude.text.toString(),
            EdLongitude.text.toString()
        ).enqueue(object : Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful && response.body()!!.status == "Ok") {
                    Toast.makeText(this@AddCity, "Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@AddCity, City::class.java))
                } else {
                    Toast.makeText(this@AddCity, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@AddCity, "Sorry for the interruption ", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api AddCity : ", t.toString())
            }

        })
    }
}
