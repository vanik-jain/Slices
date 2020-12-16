package com.example.slice.SearchFlight

import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import com.example.slice.R

class FlightSearchSliceProvider : SliceProvider() {
  /**
   * Instantiate any required objects. Return true if the provider was successfully created,
   * false otherwise.
   */

  override fun onCreateSliceProvider(): Boolean {
    return true
  }

  /**
   * Converts URL to content URI (i.e. content://com.example.slice.SearchFlight...)
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
    val activityAction = createActivityAction(sliceUri) ?: return null
    return if (sliceUri.path == "/flight") {

        goToFlight(context, sliceUri, activityAction)
    } else {
      // Error: Path not found.
      ListBuilder(context, sliceUri, ListBuilder.INFINITY).addRow(
          ListBuilder.RowBuilder().setTitle("URI not found.").setPrimaryAction(activityAction)
        ).build()
    }
  }

   private fun goToFlight(context: Context, sliceUri: Uri, activityAction: SliceAction):Slice? {
       return ListBuilder(context, sliceUri, ListBuilder.INFINITY).addRow(
           ListBuilder.RowBuilder().setTitle("Let's find your flight tickets").setPrimaryAction(activityAction)
       ).build()

   }

  private fun createActivityAction(sliceUri: Uri): SliceAction? {
        val intent: Intent = Intent(context, FlightSearchActivity::class.java)
        intent.putExtra("sliceUri", sliceUri.toString())
        return SliceAction.create(
            PendingIntent.getActivity(
                context, 0, intent, 0
            ),
            IconCompat.createWithResource(context, R.drawable.ic_launcher_foreground),
            ListBuilder.ICON_IMAGE,
            "Find my tickets"
        )
  }

  /**
   * Slice has been pinned to external process. Subscribe to data source if necessary.
   */
  override fun onSlicePinned(sliceUri: Uri?) {
    // When data is received, call context.contentResolver.notifyChange(sliceUri, null) to
    // trigger FlightSearchSliceProvider#onBindSlice(Uri) again.
  }

  /**
   * Unsubscribe from data source if necessary.
   */
  override fun onSliceUnpinned(sliceUri: Uri?) {
    // Remove any observers if necessary to avoid memory leaks.
  }
}