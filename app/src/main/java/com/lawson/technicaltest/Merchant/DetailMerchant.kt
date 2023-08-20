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
import com.lawson.technicaltest.City.City
import com.lawson.technicaltest.Models.Response
import com.lawson.technicaltest.R
import retrofit2.Call
import retrofit2.Callback

class DetailMerchant : AppCompatActivity() {

    private val Merchantid by lazy { intent.getStringExtra("id") }
    private val CityId by lazy { intent.getStringExtra("city_id") }
    private val CityName by lazy { intent.getStringExtra("cityname") }
    private val MerchantName by lazy { intent.getStringExtra("merchant_name") }
    private val Phone by lazy { intent.getStringExtra("phone") }
    private val Address by lazy { intent.getStringExtra("address") }
    private val ExpiredDate by lazy { intent.getStringExtra("expired_date") }

    private lateinit var EdCity: EditText
    private lateinit var EdMerchant: EditText
    private lateinit var EdPhone: EditText
    private lateinit var EdAddres: EditText
    private lateinit var EdExpiredDate: EditText
    private lateinit var BtnSave : Button
    private lateinit var BtnDelete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_merchant)

        EdCity = findViewById(R.id.EdCity)
        EdMerchant = findViewById(R.id.EdMerchant)
        EdPhone = findViewById(R.id.EdPhone)
        EdAddres = findViewById(R.id.EdAddress)
        EdExpiredDate = findViewById(R.id.EdExpDate)
        BtnSave = findViewById(R.id.BtnSave)
        BtnDelete = findViewById(R.id.BtnDelete)



        EdCity.setText(CityName)
        EdMerchant.setText(MerchantName)
        EdPhone.setText(Phone)
        EdAddres.setText(Address)
        EdExpiredDate.setText(ExpiredDate)

        BtnSave.setOnClickListener {
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
    fun Update(){
        RetrofitClient.instance.UpdateMerchant(
            Merchantid.toString(),
            EdMerchant.text.toString(),
            CityId.toString(),
            EdPhone.text.toString(),
            EdAddres.text.toString(),
            EdExpiredDate.text.toString()
        ).enqueue(object :Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful && response.body()!!.status == "Ok") {
                    Toast.makeText(this@DetailMerchant, "Success", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this@DetailMerchant, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@DetailMerchant, "Sorry for the interruption", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api UpdateMerchant : ", t.toString())
            }


        })
    }
    public fun Delete(){
        RetrofitClient.instance.DeleteMerchant(Merchantid.toString()).enqueue(object :Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful && response.body()!!.status == "Ok") {
                    Toast.makeText(this@DetailMerchant, "Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@DetailMerchant, Merchant::class.java))
                } else {
                    Toast.makeText(this@DetailMerchant, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@DetailMerchant, "Sorry for the interruption", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api DeleteCity : ", t.toString())
            }

        })
    }
}
