package com.lawson.technicaltest.Products

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.lawson.technicaltest.API.RetrofitClient
import com.lawson.technicaltest.Adapters.CityAdapter
import com.lawson.technicaltest.Adapters.ProductAdapter
import com.lawson.technicaltest.City.AddCity
import com.lawson.technicaltest.City.DetailCity
import com.lawson.technicaltest.Merchant.AddMerchant
import com.lawson.technicaltest.Models.ModelCity
import com.lawson.technicaltest.Models.ModelProduct
import com.lawson.technicaltest.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Products : AppCompatActivity() {

    private lateinit var Adapter: ProductAdapter
    private lateinit var listitem: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        setupList()

        val BtnAdd = findViewById<ImageView>(R.id.BtnAdd)

        BtnAdd.setOnClickListener {
            startActivity(Intent(this, AddProduct::class.java))
        }
    }
    override fun onStart(){
        super.onStart()
        getData()
    }

    private fun getData() {
        RetrofitClient.instance.GetProducts().enqueue(object : Callback<ModelProduct> {
            override fun onResponse(call: Call<ModelProduct>, response: Response<ModelProduct>) {
                if (response.isSuccessful) {
                    val listData = response.body()!!.Products
                    listData.forEach {
                        Adapter.setData(listData)
                    }
                }
            }

            override fun onFailure(call: Call<ModelProduct>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun setupList(){
        listitem = findViewById(R.id.list_product)
        Adapter = ProductAdapter(arrayListOf(), object : ProductAdapter.OnAdapterlistener{
            override fun onClick(detail: ModelProduct.ListProducts) {
                startActivity(Intent(this@Products, DetailProduct::class.java)
                    .putExtra("productname", detail.productname)
                    .putExtra("price", detail.price)
                    .putExtra("merchant_name", detail.merchant_name)
                    .putExtra("product_id", detail.product_id)
                    .putExtra("merchant_id", detail.merchant_id)
                )
            }

        })
        listitem.adapter = Adapter
    }
}