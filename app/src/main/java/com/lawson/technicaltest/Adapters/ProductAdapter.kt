package com.lawson.technicaltest.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lawson.technicaltest.Models.ModelCity
import com.lawson.technicaltest.Models.ModelProduct
import com.lawson.technicaltest.R

class ProductAdapter (
    val Products : ArrayList<ModelProduct.ListProducts>,
    val listener: OnAdapterlistener
): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val Merchantname = view.findViewById<TextView>(R.id.MerchantName)
        val Price = view.findViewById<TextView>(R.id.Price)
        val ProductName = view.findViewById<TextView>(R.id.ProductName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_product, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = Products[position]
        holder.Merchantname.text = data.merchant_name
        holder.Price.text = data.price
        holder.ProductName.text = data.productname

        holder.itemView.setOnClickListener{
            listener.onClick(data)
        }
    }

    override fun getItemCount() = Products.size

    public fun setData(data : List<ModelProduct.ListProducts>){
        Products.clear()
        Products.addAll(data)
        notifyDataSetChanged()
    }
    interface OnAdapterlistener {
        fun onClick(detail: ModelProduct.ListProducts)

    }

}