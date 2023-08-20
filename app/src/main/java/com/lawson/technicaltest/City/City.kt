package com.lawson.technicaltest.City

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.lawson.technicaltest.API.RetrofitClient
import com.lawson.technicaltest.Adapters.CityAdapter
import com.lawson.technicaltest.Models.ModelCity
import com.lawson.technicaltest.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class City : AppCompatActivity() {

    private lateinit var Adapter: CityAdapter
    private lateinit var listitem: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        setupList()

        val BtnAdd = findViewById<ImageView>(R.id.BtnAdd)

        BtnAdd.setOnClickListener {
            startActivity(Intent(this, AddCity::class.java))
        }
    }
    override fun onStart(){
        super.onStart()
        getData()
    }

    private fun getData() {
        RetrofitClient.instance.GetCity().enqueue(object : Callback<ModelCity>{
            override fun onResponse(call: Call<ModelCity>, response: Response<ModelCity>) {
                if (response.isSuccessful) {
                    val listData = response.body()!!.City
                    listData.forEach {
                        Adapter.setData(listData)
                    }
                }
            }

            override fun onFailure(call: Call<ModelCity>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun setupList(){
        listitem = findViewById(R.id.list_item)
        Adapter = CityAdapter(arrayListOf(), object : CityAdapter.OnAdapterlistener{
            override fun onClick(detail: ModelCity.ListCity) {
                startActivity(Intent(this@City, DetailCity::class.java)
                    .putExtra("name", detail.name)
                    .putExtra("latitude", detail.latitude)
                    .putExtra("longitude", detail.longitude)
                    .putExtra("id", detail.id)
                )
            }

        })
        listitem.adapter = Adapter
    }
}
