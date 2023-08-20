package com.lawson.technicaltest.Orders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.lawson.technicaltest.API.RetrofitClient
import com.lawson.technicaltest.Adapters.OrderAdapter
import com.lawson.technicaltest.Adapters.ProductAdapter
import com.lawson.technicaltest.Models.ModelOrder
import com.lawson.technicaltest.Models.ModelProduct
import com.lawson.technicaltest.Products.AddProduct
import com.lawson.technicaltest.Products.DetailProduct
import com.lawson.technicaltest.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Orders : AppCompatActivity() {

    private lateinit var Adapter: OrderAdapter
    private lateinit var listitem: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        setupList()

        val BtnAdd = findViewById<ImageView>(R.id.BtnAdd)

        BtnAdd.setOnClickListener {
            startActivity(Intent(this, AddOrder::class.java))
        }
    }
    override fun onStart(){
        super.onStart()
        getData()
    }

    private fun getData() {
        RetrofitClient.instance.GetOrders().enqueue(object : Callback<ModelOrder> {
            override fun onResponse(call: Call<ModelOrder>, response: Response<ModelOrder>) {
                if (response.isSuccessful) {
                    val listData = response.body()!!.Orders
                    listData.forEach {
                        Adapter.setData(listData)
                    }
                }
            }

            override fun onFailure(call: Call<ModelOrder>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun setupList(){
        listitem = findViewById(R.id.list_orders)
        Adapter = OrderAdapter(arrayListOf(), object : OrderAdapter.OnAdapterlistener{
            override fun onClick(detail: ModelOrder.ListOrders) {
                startActivity(Intent(this@Orders, DetailOrder::class.java)
                    .putExtra("productname", detail.productname)
                    .putExtra("price", detail.price)
                    .putExtra("merchant_name", detail.merchant_name)
                    .putExtra("status", detail.status)
                    .putExtra("date", detail.date)
                    .putExtra("total", detail.total)
                    .putExtra("quantity", detail.quantity)
                    .putExtra("full_name", detail.full_name)
                    .putExtra("product_id", detail.product_id)
                    .putExtra("order_id", detail.order_id)
                    .putExtra("user_id", detail.user_id)
                )
            }

        })
        listitem.adapter = Adapter
    }
}
