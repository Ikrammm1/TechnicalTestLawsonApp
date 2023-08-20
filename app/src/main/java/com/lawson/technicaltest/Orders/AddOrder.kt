package com.lawson.technicaltest.Orders

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
import com.lawson.technicaltest.Products.Products
import com.lawson.technicaltest.R
import retrofit2.Call
import retrofit2.Callback

class AddOrder : AppCompatActivity() {

    private lateinit var EdProductName: EditText
    private lateinit var EdQuantity: EditText
    private lateinit var EdFullName: EditText
    private lateinit var EdDate: EditText
    private lateinit var BtnAdd : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)

        EdProductName = findViewById(R.id.EdProduct)
        EdFullName = findViewById(R.id.EdFullname)
        EdQuantity = findViewById(R.id.EdQuantity)

        BtnAdd = findViewById(R.id.BtnAdd)

        BtnAdd.setOnClickListener {
            when{
                EdProductName.text.toString() == "" ->{
                    EdProductName.error = "Product Name Name is required!"
                }
                EdQuantity.text.toString() == "" ->{
                    EdQuantity.error = "Quantity Name is required!"
                }
                EdFullName.text.toString() == "" ->{
                    EdFullName.error = "FullName is required!"
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
        RetrofitClient.instance.AddOrder(
            EdQuantity.text.toString(),
            EdProductName.text.toString(),
            EdFullName.text.toString()
        ).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful && response.body()!!.status == "Ok") {
                    Toast.makeText(this@AddOrder, "Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@AddOrder, Orders::class.java))
                } else {
                    Toast.makeText(this@AddOrder, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@AddOrder, "Sorry for the interruption ", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api AddProduct : ", t.toString())
            }

        })
    }
}
