package com.example.slice.cart.provider

import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.LayoutDirection
import android.view.View
import android.widget.Toast
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import com.example.slice.R
import com.example.slice.cart.network.IApi
import com.example.slice.cart.network.ServiceBuilder
import com.example.slice.cart.view.CartActivity
import com.example.slice.model.SampleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class CartSliceProvider : SliceProvider() {
    /**
     * Instantiate any required objects. Return true if the provider was successfully created,
     * false otherwise.
     */

    companion object {
        fun getUri(context: Context, path: String): Uri {
            return Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(context.packageName)
                .appendPath(path)
                .build()
        }
    }

    private var vanik = ""
    override fun onCreateSliceProvider(): Boolean {
//        val request = ServiceBuilder.buildService(IApi::class.java)
//        val call = request.getResponse()
//        call.enqueue(object : Callback<SampleResponse> {
//            override fun onResponse(
//                call: Call<SampleResponse>, response: Response<SampleResponse>
//            ) {
//                if (response.isSuccessful) {
//                vanik = vanik.plus(response.toString())
//                    context?.let {
//                        it.contentResolver?.notifyChange(
//                            CartSliceProvider.getUri(it, "/cart"), null
//                        )
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<SampleResponse>, t: Throwable) {
//                vanik = vanik.plus(t.message.toString())
//                context?.let {
//                    it.contentResolver?.notifyChange(
//                        CartSliceProvider.getUri(it, "/cart"), null
//                    )
//                }
//            }
//        })
//
        return true
    }


    /**
     * Converts URL to content URI (i.e. content://com.example.slice.provider...)
     */
    override fun onMapIntentToUri(intent: Intent?): Uri {
        // Note: implementing this is only required if you plan on catching URL requests.
        // This is an example solution.
        var uriBuilder: Uri.Builder = Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
        if (intent == null) return uriBuilder.build()
        val data = intent.data
        val dataPath = data?.path
        if (data != null && dataPath != null) {
            val path = dataPath.replace("/", "")
            uriBuilder = uriBuilder.path(path)
        }
        val context = context
        if (context != null) {
            uriBuilder = uriBuilder.authority(context.packageName)
        }
        return uriBuilder.build()
    }

    /**
     * Construct the Slice and bind data if available.
     */
    override fun onBindSlice(sliceUri: Uri): Slice? {
        // Note: you should switch your build.gradle dependency to
        // slice-builders-ktx for a nicer interface in Kotlin.
        val context = context ?: return null
        val activityAction = createActivityAction() ?: return null
        return if (sliceUri.path == "/cart") {
            // Path recognized. Customize the Slice using the androidx.slice.builders API.
            // Note: ANR and StrictMode are enforced here so don"t do any heavy operations.
            // Only bind data that is currently available in memory.

            createRetailGetCartSlice(context, sliceUri, activityAction)
        } else {
            // Error: Path not found.
            ListBuilder(context, sliceUri, ListBuilder.INFINITY)
                .addRow(
                    ListBuilder.RowBuilder()
                        .setTitle("URI not found.")
                        .setPrimaryAction(activityAction)
                ).setLayoutDirection(View.LAYOUT_DIRECTION_RTL)
                .build()
        }
    }

    private fun createActivityAction(): SliceAction? {
        return SliceAction.create(
            PendingIntent.getActivity(
                context, 0, Intent(context, CartActivity::class.java), 0
            ),
            IconCompat.createWithResource(context, R.drawable.ic_launcher_foreground),
            ListBuilder.ICON_IMAGE,
            "Open App"
        )
    }

    private fun createRetailGetCartSlice(
        context: Context, sliceUri: Uri, activityAction: SliceAction
    ) = ListBuilder(
        context, sliceUri, ListBuilder.INFINITY
    ).setAccentColor(0xff0F9D) // Specify color for tinting icons
        .setHeader(
            ListBuilder.HeaderBuilder().setTitle(vanik).setSubtitle(vanik)
                .setSummary("Total Items: 21") .setLayoutDirection(View.LAYOUT_DIRECTION_RTL)
        ).addRow(
            ListBuilder.RowBuilder().setTitle("Items").setSubtitle("2 C&C orders").addEndItem(
                IconCompat.createWithResource(context, R.mipmap.ic_blibli_round), ListBuilder.ICON_IMAGE
            ).setPrimaryAction(activityAction)
                .setLayoutDirection(View.LAYOUT_DIRECTION_RTL)
        ).build()

    /**
     * Slice has been pinned to external process. Subscribe to data source if necessary.
     */
    override fun onSlicePinned(sliceUri: Uri?) {
        // When data is received, call context.contentResolver.notifyChange(sliceUri, null) to
        // trigger CartSliceProvider#onBindSlice(Uri) again.
    }

    /**
     * Unsubscribe from data source if necessary.
     */
    override fun onSliceUnpinned(sliceUri: Uri?) {
        // Remove any observers if necessary to avoid memory leaks.
    }
}