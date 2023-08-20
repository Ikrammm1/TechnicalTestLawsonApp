package com.lawson.technicaltest.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lawson.technicaltest.Models.ModelCity
import com.lawson.technicaltest.Models.ModelMerchant
import com.lawson.technicaltest.R

class MerchantAdapter(
    val Merchant : ArrayList<ModelMerchant.ListMerchant>,
    val listener: OnAdapterlistener
): RecyclerView.Adapter<MerchantAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val CityName = view.findViewById<TextView>(R.id.CityName)
        val MerchantName = view.findViewById<TextView>(R.id.MerchantName)
        val Phone = view.findViewById<TextView>(R.id.Phone)
        val Address = view.findViewById<TextView>(R.id.Address)
        val ExpiredDate = view.findViewById<TextView>(R.id.ExpDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_merchant, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = Merchant[position]
        holder.CityName.text = data.cityname
        holder.MerchantName.text = data.merchant_name
        holder.Phone.text = data.phone
        holder.Address.text = data.address
        holder.ExpiredDate.text = data.expired_date

        holder.itemView.setOnClickListener{
            listener.onClick(data)
        }
    }

    override fun getItemCount() = Merchant.size

    public fun setData(data : List<ModelMerchant.ListMerchant>){
        Merchant.clear()
        Merchant.addAll(data)
        notifyDataSetChanged()
    }
    interface OnAdapterlistener {
        fun onClick(detail: ModelMerchant.ListMerchant)

    }

}