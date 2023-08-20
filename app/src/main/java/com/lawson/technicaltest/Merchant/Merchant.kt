package com.lawson.technicaltest.Merchant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.lawson.technicaltest.API.RetrofitClient
import com.lawson.technicaltest.Adapters.CityAdapter
import com.lawson.technicaltest.Adapters.MerchantAdapter
import com.lawson.technicaltest.City.AddCity
import com.lawson.technicaltest.City.DetailCity
import com.lawson.technicaltest.Models.ModelCity
import com.lawson.technicaltest.Models.ModelMerchant
import com.lawson.technicaltest.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Merchant : AppCompatActivity() {

    private lateinit var Adapter: MerchantAdapter
    private lateinit var listitem: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant)

        setupList()

        val BtnAdd = findViewById<ImageView>(R.id.BtnAdd)

        BtnAdd.setOnClickListener {
            startActivity(Intent(this, AddMerchant::class.java))
        }
    }
    override fun onStart(){
        super.onStart()
        getData()
    }

    private fun getData() {
        RetrofitClient.instance.GetMerchant().enqueue(object : Callback<ModelMerchant> {
            override fun onResponse(call: Call<ModelMerchant>, response: Response<ModelMerchant>) {
                if (response.isSuccessful) {
                    val listData = response.body()!!.Merchant
                    listData.forEach {
                        Adapter.setData(listData)
                    }
                }
            }

            override fun onFailure(call: Call<ModelMerchant>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun setupList(){
        listitem = findViewById(R.id.list_merchant)
        Adapter = MerchantAdapter(arrayListOf(), object : MerchantAdapter.OnAdapterlistener{
            override fun onClick(detail: ModelMerchant.ListMerchant) {
                startActivity(Intent(this@Merchant, DetailMerchant::class.java)
                    .putExtra("merchant_name", detail.merchant_name)
                    .putExtra("cityname", detail.cityname)
                    .putExtra("phone", detail.phone)
                    .putExtra("address", detail.address)
                    .putExtra("expired_date", detail.expired_date)
                    .putExtra("id", detail.id)
                    .putExtra("city_id", detail.city_id)
                )
            }

        })
        listitem.adapter = Adapter
    }
}
