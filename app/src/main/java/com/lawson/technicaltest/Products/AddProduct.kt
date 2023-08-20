package com.lawson.technicaltest.Products

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
import com.lawson.technicaltest.City.City
import com.lawson.technicaltest.Models.Response
import com.lawson.technicaltest.R
import retrofit2.Call
import retrofit2.Callback

class AddProduct : AppCompatActivity() {

    private lateinit var EdProductName: EditText
    private lateinit var EdPrice: EditText
    private lateinit var EdMerchantName: EditText
    private lateinit var BtnAdd : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        EdProductName = findViewById(R.id.EdProduct)
        EdPrice = findViewById(R.id.EdPrice)
        EdMerchantName = findViewById(R.id.EdMerchant)
        BtnAdd = findViewById(R.id.BtnAdd)

        BtnAdd.setOnClickListener {
            when{
                EdProductName.text.toString() == "" ->{
                    EdProductName.error = "Product Name Name is required!"
                }
                EdMerchantName.text.toString() == "" ->{
                    EdMerchantName.error = "Merchant Name is required!"
                }
                EdPrice.text.toString() == "" ->{
                    EdPrice.error = "Price is required!"
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
        RetrofitClient.instance.AddProduct(
            EdProductName.text.toString(),
            EdMerchantName.text.toString(),
            EdPrice.text.toString()
        ).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful && response.body()!!.status == "Ok") {
                    Toast.makeText(this@AddProduct, "Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@AddProduct, Products::class.java))
                } else {
                    Toast.makeText(this@AddProduct, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@AddProduct, "Sorry for the interruption ", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api AddProduct : ", t.toString())
            }

        })
    }
}
