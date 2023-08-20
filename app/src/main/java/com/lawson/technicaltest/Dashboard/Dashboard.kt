package com.lawson.technicaltest.Dashboard

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.lawson.technicaltest.City.City
import com.lawson.technicaltest.Merchant.Merchant
import com.lawson.technicaltest.Orders.Orders
import com.lawson.technicaltest.Products.Products
import com.lawson.technicaltest.Profile.Profile
import com.lawson.technicaltest.R
import com.lawson.technicaltest.Report.Report

class Dashboard : AppCompatActivity() {

    private lateinit var profil : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val Fullname = findViewById<TextView>(R.id.FullName)
        val BtnCity = findViewById<CardView>(R.id.City)
        val BtnMerchant = findViewById<CardView>(R.id.Merchant)
        val BtnOrders = findViewById<CardView>(R.id.Orders)
        val BtnProducts = findViewById<CardView>(R.id.Products)
        val BtnReport = findViewById<CardView>(R.id.Report)
        val BtnProfile = findViewById<CardView>(R.id.Profile)
        profil = getSharedPreferences("Login_Session", MODE_PRIVATE)
        Fullname.text = "Welcome ${profil.getString("full_name", null)}"


        BtnProfile.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }
        BtnCity.setOnClickListener {
            startActivity(Intent(this, City::class.java))
        }
        BtnMerchant.setOnClickListener {
            startActivity(Intent(this, Merchant::class.java))
        }
        BtnProducts.setOnClickListener {
            startActivity(Intent(this, Products::class.java))
        }
        BtnOrders.setOnClickListener {
            startActivity(Intent(this, Orders::class.java))
        }
        BtnReport.setOnClickListener {
            startActivity(Intent(this, Report::class.java))
        }
    }
}
