package com.example.slice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.webkit.WebView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val textView = findViewById<WebView>(R.id.wv_html)
//    textView.settings.javaScriptEnabled = true
//    textView.settings.loadsImagesAutomatically = true
//    textView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
    textView.loadDataWithBaseURL("","<p style=\"margin-bottom: 0;\"><strong>Syarat &amp; Ketentuan DigiPo:</strong></p>\n" + "<ul class=\"list\">\n" + "<li>Voucher dengan voucher code #-voucherCode-#</li>\n" + "<li>voucher punya event dengan id&nbsp;605195f2d3950c000d973847</li>\n" + "<li>Voucher&nbsp;punya event dengan nama&nbsp;OF-Digital Product - Combine</li>\n" + "<li>Voucher dengan orderId&nbsp;40000500907</li>\n" + "<li>Product namenya yaitu&nbsp;CECP - International from Bangalore - Regular</li>\n" + "<li>product SKU nya yaitu&nbsp;TOS-16005-00223-00001</li>\n" + "<li>Nama awal anda adalah&nbsp;kus</li>\n" + "<li>Nama akhir anda adalah&nbsp;.</li>\n" + "<li>Email anda adalah&nbsp;kushisdsk@gmail.com</li>\n" + "<li>Total product anda adalah&nbsp;1089999.0</li>\n" + "<li>phone number anda adalah&nbsp;08686567766767</li>\n" + "<li>shipping address anda adalah&nbsp;Jl. Titian Indah, RT.5/RW.2, Gambir, Kecamatan Gambir, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10110, Indonesia, Gambir, Gambir, Kota Jakarta Pusat</li>\n" + "<li><a href=\"https://www.google.com\">Google</a></li>\n" + "<li>Contoh dimasukin cerita:&nbsp;We bought an old house, my boyfriend and I. He's in charge of the \"new\" construction &ndash; converting the kitchen in to the master bedroom for instance, while I'm on wallpaper removal duty. The previous owner papered EVERY wall and CEILING! Removing it is brutal, but oddly satisfying. The best feeling is getting a long peel, similar to your skin when you're peeling from a sunburn. I don't know about you but I kinda make a game of peeling, on the hunt for the longest piece before it rips.Under a corner section of paper in every room is a person&rsquo;s name and a date. Curiosity got the best of me one night when I Googled one of the names and discovered the person was actually a missing person, the missing date matching the date under the wallpaper! The next day, I made a list of all the names and dates. Sure enough each name was for a missing person with dates to match. We notified the police who naturally sent out the crime scene team. I overhead one tech say \"yup, it's human.\" Human? What's human? \"Ma'am, where is the material you removed from the walls already? This isn't wallpaper you were removing.\"</li>\n" + "<li><img style=\"border: 0px none;\" src=\"https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fwp-content%2Fblogs.dir%2F6%2Ffiles%2F2019%2F11%2Fspongebob-squarepants-spinoff-squidward-netflix-series-info-1-1.jpg?w=960&amp;cbr=1&amp;q=90&amp;fit=max\" alt=\"images.google.com\" /></li>\n" + "</ul>","text/html","UTF-8","")
  }
}