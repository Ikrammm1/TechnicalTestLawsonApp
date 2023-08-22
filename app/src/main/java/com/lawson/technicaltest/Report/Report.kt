package com.lawson.technicaltest.Report

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.webkit.CookieManager
import android.widget.Button
import android.widget.Toast
import com.lawson.technicaltest.R

class Report : AppCompatActivity() {

    private lateinit var DownloadMonth : Button
    private lateinit var DownloadProduct : Button
    private lateinit var DownloadCity : Button
    private lateinit var DownloadUser : Button
    private lateinit var SendMonth : Button
    private lateinit var SendProduct : Button
    private lateinit var SendCity : Button
    private lateinit var SendUser : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        DownloadMonth = findViewById(R.id.DownloadMonth)
        DownloadProduct = findViewById(R.id.DownloadProduct)
        DownloadCity= findViewById(R.id.DownloadCity)
        DownloadUser = findViewById(R.id.DownloadUser)
        SendMonth = findViewById(R.id.MailMonth)
        SendProduct = findViewById(R.id.MailProduct)
        SendCity= findViewById(R.id.MailCity)
        SendUser = findViewById(R.id.MailUser)

        DownloadMonth.setOnClickListener {
            Download("http://192.168.100.67/TechnicalTest/ReportByMonth.php")
        }
        DownloadProduct.setOnClickListener {
            Download("http://192.168.100.67/TechnicalTest/ReportByProduct.php")
        }
        DownloadCity.setOnClickListener {
            Download("http://192.168.100.67/TechnicalTest/ReportByCity.php")
        }
        DownloadUser.setOnClickListener {
            Download("http://192.168.100.67/TechnicalTest/ReportByUser.php")
        }

        SendMonth.setOnClickListener {
            MailTo("http://192.168.100.67/TechnicalTest/ReportByMonth.php", "ReportByMonth")
        }
        SendProduct.setOnClickListener {
            MailTo("http://192.168.100.67/TechnicalTest/ReportByProduct.php", "ReportByProduct")
        }
        SendCity.setOnClickListener {
            MailTo("http://192.168.100.67/TechnicalTest/ReportByCity.php", "ReportByCity")
        }
        SendUser.setOnClickListener {
            MailTo("http://192.168.100.67/TechnicalTest/ReportByUser.php", "ReportByUser")
        }

    }


    private fun Download(link:String){
        val uri = Uri.parse(link)
        val intent= Intent(Intent.ACTION_VIEW ,uri)
        startActivity(intent)
    }
    private fun MailTo(link: String, subject : String){
        val mail = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, link)

        }
        startActivity(Intent.createChooser(mail, "Send Email"));
//        if(mail.resolveActivity(packageManager)!= null){
//            startActivity(mail)
//        }else{
//            Toast.makeText(this@Report, "Not app supported", Toast.LENGTH_SHORT).show()
//        }


    }
}
