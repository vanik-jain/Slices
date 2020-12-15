package com.example.slice.SearchFlight

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.slice.R

class FlightSearchActivity() : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onCreate(savedInstanceState, persistentState)
    setContentView(R.layout.flight_reservation)
    val bundle: Bundle? = intent.extras
    Log.i("naveen",bundle.toString())
    val depIataCode = bundle?.get("depIataCode")


  }
}