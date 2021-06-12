package com.example.slice.cart.network

import com.example.slice.model.SampleResponse
import retrofit2.Call
import retrofit2.http.GET

interface IApi {

  @GET("/todos/1")
  fun getResponse(): Call<SampleResponse>
}