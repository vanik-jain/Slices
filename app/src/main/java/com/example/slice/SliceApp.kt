package com.example.slice

import android.app.Application
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.service.voice.VoiceInteractionService
import androidx.slice.SliceManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SliceApp : Application() {

    override fun onCreate() {
        super.onCreate()
        grantAssistantPermissions()
    }

    private fun grantAssistantPermissions() {
        getAssistantPackage()?.let { assistantPackage ->
            val sliceProviderUri = Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority("com.example.slice.slices.provider")
                .build()

            SliceManager.getInstance(this).grantSlicePermission(assistantPackage, sliceProviderUri)
        }
    }

    private fun getAssistantPackage(): String? {
        val resolveInfoList = packageManager?.queryIntentServices(
            Intent(VoiceInteractionService.SERVICE_INTERFACE), 0
        )
        return resolveInfoList?.firstOrNull()?.serviceInfo?.packageName
    }
}