package com.lawson.technicaltest.City

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.lawson.technicaltest.API.RetrofitClient
import com.lawson.technicaltest.Models.Response
import com.lawson.technicaltest.R
import retrofit2.Call
import retrofit2.Callback

class DetailCity : AppCompatActivity() {

    private val Cityid by lazy { intent.getStringExtra("id") }
    private val CityName by lazy { intent.getStringExtra("name") }
    private val Longitude by lazy { intent.getStringExtra("longitude") }
    private val Latitude by lazy { intent.getStringExtra("latitude") }

    private lateinit var EdCityname: EditText
    private lateinit var EdLatitude: EditText
    private lateinit var EdLongitude: EditText
    private lateinit var BtnSave : Button
    private lateinit var BtnDelete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_city)

        EdCityname = findViewById(R.id.EdCity)
        EdLatitude = findViewById(R.id.EdLatitude)
        EdLongitude = findViewById(R.id.EdLongitude)
        BtnSave = findViewById(R.id.BtnSave)
        BtnDelete = findViewById(R.id.BtnDelete)



        EdCityname.setText(CityName)
        EdLatitude.setText(Longitude)
        EdLongitude.setText(Latitude)

        BtnSave.setOnClickListener {
            when{
                EdCityname.text.toString() == "" ->{
                    EdCityname.error = "City Name is required!"
                }
                EdLatitude.text.toString() == "" ->{
                    EdLatitude.error = "Latitude is required!"
                }
                EdLongitude.text.toString() == "" ->{
                    EdLongitude.error = "Longitude is required!"
                }

                else -> {
                    var alertDialog = AlertDialog.Builder(this)
                        .setTitle("Are you sure?")
                        .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->

                            Update()

                        })
                        .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->  })
                        .show()
                }
            }
        }
        BtnDelete.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
                .setTitle("Are you sure?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->

                    Delete()

                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->  })
                .show()
        }
    }
    public fun Update(){
        RetrofitClient.instance.UpdateCity(
            Cityid.toString(),
            EdCityname.text.toString(),
            EdLatitude.text.toString(),
            EdLongitude.text.toString()
        ).enqueue(object  : Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful && response.body()!!.status == "Ok") {
                    Toast.makeText(this@DetailCity, "Success", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this@DetailCity, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@DetailCity, "Sorry for the interruption", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api UpdateCity : ", t.toString())
            }

        })
    }
    public fun Delete(){
        RetrofitClient.instance.DeleteCity(Cityid.toString()).enqueue(object :Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful && response.body()!!.status == "Ok") {
                    Toast.makeText(this@DetailCity, "Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@DetailCity, City::class.java))
                } else {
                    Toast.makeText(this@DetailCity, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@DetailCity, "Sorry for the interruption ", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api DeleteCity : ", t.toString())
            }

        })
    }
}
