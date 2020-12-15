package com.example.slice.SearchFlight

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.slice.R

class FlightSearchActivity : AppCompatActivity() {
private var uri: String? = null
private var date: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.flight_reservation)
    uri = intent.getStringExtra("sliceUri")
  }
}