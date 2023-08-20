package com.lawson.technicaltest.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lawson.technicaltest.Models.ModelOrder
import com.lawson.technicaltest.Models.ModelProduct
import com.lawson.technicaltest.R

class OrderAdapter(
    val Orders : ArrayList<ModelOrder.ListOrders>,
    val listener: OnAdapterlistener
): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val Merchantname = view.findViewById<TextView>(R.id.MerchantName)
        val Price = view.findViewById<TextView>(R.id.Price)
        val ProductName = view.findViewById<TextView>(R.id.ProductName)
        val Status = view.findViewById<TextView>(R.id.Status)
        val Quantity = view.findViewById<TextView>(R.id.Quantity)
        val FullName = view.findViewById<TextView>(R.id.FullName)
        val Date = view.findViewById<TextView>(R.id.Date)
        val Total = view.findViewById<TextView>(R.id.Total)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_orders, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = Orders[position]
        holder.Merchantname.text = data.merchant_name
        holder.Price.text = data.price
        holder.ProductName.text = data.productname
        holder.Status.text = data.status
        holder.Quantity.text = data.quantity
        holder.FullName.text = data.full_name
        holder.Date.text = data.date
        holder.Total.text = data.total

        holder.itemView.setOnClickListener{
            listener.onClick(data)
        }
    }

    override fun getItemCount() = Orders.size

    public fun setData(data : List<ModelOrder.ListOrders>){
        Orders.clear()
        Orders.addAll(data)
        notifyDataSetChanged()
    }
    interface OnAdapterlistener {
        fun onClick(detail: ModelOrder.ListOrders)

    }

}