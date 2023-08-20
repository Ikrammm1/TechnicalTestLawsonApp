package com.lawson.technicaltest.Merchant

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

class AddMerchant : AppCompatActivity() {

    private lateinit var EdCity: EditText
    private lateinit var EdMerchant: EditText
    private lateinit var EdPhone: EditText
    private lateinit var EdAddres: EditText
    private lateinit var EdExpiredDate: EditText
    private lateinit var BtnAdd : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_merchant)

        EdCity = findViewById(R.id.EdCity)
        EdMerchant = findViewById(R.id.EdMerchant)
        EdPhone = findViewById(R.id.EdPhone)
        EdAddres = findViewById(R.id.EdAddress)
        EdExpiredDate = findViewById(R.id.EdExpDate)
        BtnAdd = findViewById(R.id.BtnAdd)

        BtnAdd.setOnClickListener {
            when{
                EdMerchant.text.toString() == "" ->{
                    EdMerchant.error = "Merchant Name is required!"
                }
                EdCity.text.toString() == "" ->{
                    EdCity.error = "City is required!"
                }
                EdPhone.text.toString() == "" ->{
                    EdPhone.error = "Phone is required!"
                }
                EdAddres.text.toString() == "" ->{
                    EdAddres.error = "Address is required!"
                }
                EdExpiredDate.text.toString() == "" ->{
                    EdExpiredDate.error = "Expired Date is required!"
                }

                else -> {
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
        RetrofitClient.instance.AddMerchant(
            EdMerchant.text.toString(),
            EdCity.text.toString(),
            EdPhone.text.toString(),
            EdAddres.text.toString(),
            EdExpiredDate.text.toString()
        ).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful && response.body()!!.status == "Ok") {
                    Toast.makeText(this@AddMerchant, "Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@AddMerchant, Merchant::class.java))

                } else {
                    Toast.makeText(this@AddMerchant, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@AddMerchant, "Sorry for the interruption", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api AddMerchant : ", t.toString())
            }


        })
    }
}
