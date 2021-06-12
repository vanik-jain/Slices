package com.example.slice.SearchFlight

import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.slice.R

class FlightSearchActivity : AppCompatActivity() {
private var uri: String? = null
private var time: String? = null
private var name:String? = null
private var depIata:String? = null
private var arrivalIata:String? = null
private var arrAddress:String? = null
private var depAddress:String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.flight_reservation)
    val bundle: String? = intent.getStringExtra("sliceUri")
    val sliceUri: Uri =
      Uri.parse(bundle)
    time = sliceUri.getQueryParameter("departureTime")
    name = sliceUri.getQueryParameter("name")
    arrAddress = sliceUri.getQueryParameter("arrivalAirportAddress")
    depAddress = sliceUri.getQueryParameter("departureAirportAddress")
    depIata = sliceUri.getQueryParameter("departureAirportIatacode")
    arrivalIata = sliceUri.getQueryParameter("arrivalAirportIatacode")
    initUi()
    uri = intent.getStringExtra("sliceUri")
  }

  private fun initUi() {
    val tvDep = findViewById<TextView>(R.id.tv_dep)
    val tvArr = findViewById<TextView>(R.id.tv_arrival)
    tvDep.text = arrivalIata
    tvArr.text = depIata
  }

}