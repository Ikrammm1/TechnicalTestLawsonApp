package com.lawson.technicaltest.Orders

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.lawson.technicaltest.API.RetrofitClient
import com.lawson.technicaltest.Models.Response
import com.lawson.technicaltest.Products.Products
import com.lawson.technicaltest.R
import retrofit2.Call
import retrofit2.Callback

class DetailOrder : AppCompatActivity() {

    private val Productid by lazy { intent.getStringExtra("product_id") }
    private val OrderId by lazy { intent.getStringExtra("order_id") }
    private val UserId by lazy { intent.getStringExtra("user_id") }
    private val ProductName by lazy { intent.getStringExtra("productname") }
    private val MerchantName by lazy { intent.getStringExtra("merchant_name") }
    private val Price by lazy { intent.getStringExtra("price") }
    private val Quantity by lazy { intent.getStringExtra("quantity") }
    private val Status by lazy { intent.getStringExtra("status") }
    private val Total by lazy { intent.getStringExtra("total") }
    private val Date by lazy { intent.getStringExtra("date") }
    private val FullName by lazy { intent.getStringExtra("full_name") }

    private lateinit var EdProductName: EditText
    private lateinit var EdQuantity: EditText
    private lateinit var EdFullName: EditText
    private lateinit var EdDate: EditText
    private lateinit var TxtTotal: TextView
    private lateinit var TxtStatus: TextView
    private lateinit var BtnSave : Button
    private lateinit var BtnDelete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_order)

        EdProductName = findViewById(R.id.EdProduct)
        EdFullName = findViewById(R.id.EdFullname)
        EdQuantity = findViewById(R.id.EdQuantity)
        EdDate = findViewById(R.id.EdDate)
        TxtTotal = findViewById(R.id.Total)
        TxtStatus = findViewById(R.id.Status)
        BtnSave = findViewById(R.id.BtnSave)
        BtnDelete = findViewById(R.id.BtnDelete)

        EdProductName.setText(ProductName)
        EdFullName.setText(FullName)
        EdQuantity.setText(Quantity)
        EdDate.setText(Date)
        TxtTotal.setText(Total)
        TxtStatus.setText(Status)
        BtnSave.setOnClickListener {
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
                EdDate.text.toString() == "" ->{
                    EdDate.error = "Date is required!"
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
        if (Status == "paid off"){
            BtnDelete.visibility = View.GONE
        }else{
            BtnDelete.visibility = View.VISIBLE
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
        RetrofitClient.instance.UpdateOrder(
            OrderId.toString(),
            EdQuantity.text.toString(),
            Productid.toString(),
            UserId.toString()
        ).enqueue(object  : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful && response.body()!!.status == "Ok") {
                    Toast.makeText(this@DetailOrder, "Success", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this@DetailOrder, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@DetailOrder, "Sorry for the interruption", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api UpdateProduct : ", t.toString())
            }

        })
    }
    public fun Delete(){
        RetrofitClient.instance.DeleteOrder(OrderId.toString()).enqueue(object :
            Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful && response.body()!!.status == "Ok") {
                    Toast.makeText(this@DetailOrder, "Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@DetailOrder, Orders::class.java))
                } else {
                    Toast.makeText(this@DetailOrder, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@DetailOrder, "Sorry for the interruption ", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api DeleteProduct : ", t.toString())
            }

        })
    }
}
