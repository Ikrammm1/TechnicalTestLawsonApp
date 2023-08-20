package com.lawson.technicaltest.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lawson.technicaltest.Models.ModelCity
import com.lawson.technicaltest.R

class CityAdapter(
    val City : ArrayList<ModelCity.ListCity>,
    val listener: OnAdapterlistener
): RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val CityName = view.findViewById<TextView>(R.id.CityName)
        val Latitude = view.findViewById<TextView>(R.id.CityLatitude)
        val Longitude = view.findViewById<TextView>(R.id.CityLongitude)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_city, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = City[position]
        holder.CityName.text = data.name
        holder.Latitude.text = data.latitude
        holder.Longitude.text = data.longitude

        holder.itemView.setOnClickListener{
            listener.onClick(data)
        }
    }

    override fun getItemCount() = City.size

    public fun setData(data : List<ModelCity.ListCity>){
        City.clear()
        City.addAll(data)
        notifyDataSetChanged()
    }
    interface OnAdapterlistener {
        fun onClick(detail: ModelCity.ListCity)

    }

}