package com.example.slice.digital.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.slice.R

class DigitalOrderActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_digital_order)
    val imageView: ImageView = findViewById(R.id.iv_order_list)
    val intent = Intent(applicationContext, DigitalOrderDetailActivity::class.java)
    imageView.setOnClickListener {
      if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
      }
    }
  }
}