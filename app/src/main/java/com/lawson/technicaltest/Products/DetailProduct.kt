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

class DetailProduct : AppCompatActivity() {

    private val Productid by lazy { intent.getStringExtra("product_id") }
    private val MerchantId by lazy { intent.getStringExtra("merchant_id") }
    private val ProductName by lazy { intent.getStringExtra("productname") }
    private val MerchantName by lazy { intent.getStringExtra("merchant_name") }
    private val Price by lazy { intent.getStringExtra("price") }

    private lateinit var EdProductName: EditText
    private lateinit var EdPrice: EditText
    private lateinit var EdMerchantName: EditText
    private lateinit var BtnSave : Button
    private lateinit var BtnDelete : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        EdProductName = findViewById(R.id.EdProduct)
        EdPrice = findViewById(R.id.EdPrice)
        EdMerchantName = findViewById(R.id.EdMerchant)
        BtnSave = findViewById(R.id.BtnSave)
        BtnDelete = findViewById(R.id.BtnDelete)



        EdProductName.setText(ProductName)
        EdPrice.setText(Price)
        EdMerchantName.setText(MerchantName)

        BtnSave.setOnClickListener {
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
        RetrofitClient.instance.UpdateProduct(
            Productid.toString(),
            EdProductName.text.toString(),
            MerchantId.toString(),
            EdPrice.text.toString()
        ).enqueue(object  : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful && response.body()!!.status == "Ok") {
                    Toast.makeText(this@DetailProduct, "Success", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this@DetailProduct, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@DetailProduct, "Sorry for the interruption", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api UpdateProduct : ", t.toString())
            }

        })
    }
    public fun Delete(){
        RetrofitClient.instance.DeleteProduct(Productid.toString()).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful && response.body()!!.status == "Ok") {
                    Toast.makeText(this@DetailProduct, "Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@DetailProduct, Products::class.java))
                } else {
                    Toast.makeText(this@DetailProduct, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@DetailProduct, "Sorry for the interruption ", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api DeleteProduct : ", t.toString())
            }

        })
    }
}
