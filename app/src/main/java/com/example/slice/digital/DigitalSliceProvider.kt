package com.example.slice.digital

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
import com.example.slice.digital.view.DigitalOrderActivity
import com.example.slice.digital.view.DigitalOrderCreateActivity

class DigitalSliceProvider : SliceProvider() {

  override fun onCreateSliceProvider(): Boolean {
    return true
  }

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

  override fun onBindSlice(sliceUri: Uri): Slice? {
    val context = context ?: return null
    var activityAction = createDigitalOrderAction(false) ?: return null
    return when (sliceUri.path) {
      "/digital/orders" -> {
        activityAction = createDigitalOrderAction(false)
        createDigitalGetOrderSlice(context, sliceUri, activityAction)
      }
      "/digital/create" -> {
        activityAction = createDigitalOrderAction(true)
        createSliceNewOrderHeader(context, sliceUri, activityAction)
      }
      else -> {
        // Error: Path not found.
        ListBuilder(context, sliceUri, ListBuilder.INFINITY).addRow(
          ListBuilder.RowBuilder().setTitle("URI not found.").setPrimaryAction(activityAction)
        ).build()
      }
    }
  }

  private fun createDigitalOrderAction(isNewOrder: Boolean): SliceAction {
    if (isNewOrder) {
      return SliceAction.create(
        PendingIntent.getActivity(
          context, 0, Intent(context, DigitalOrderCreateActivity::class.java), 0
        ),
        IconCompat.createWithResource(context, R.mipmap.ic_blibli_round),
        ListBuilder.SMALL_IMAGE,
        "Create new order in Blibli app"
      )
    } else {
      return SliceAction.create(
        PendingIntent.getActivity(
          context, 0, Intent(context, DigitalOrderActivity::class.java), 0
        ),
        IconCompat.createWithResource(context, R.mipmap.ic_blibli_round),
        ListBuilder.SMALL_IMAGE,
        "Open all orders in Blibli"
      )
    }
  }

  private fun createDigitalGetOrderSlice(
    context: Context, sliceUri: Uri, activityAction: SliceAction
  ) = ListBuilder(
    context, sliceUri, ListBuilder.INFINITY
  ).setAccentColor(0xff0F9D) // Specify color for tinting icons
    .setHeader(
      ListBuilder.HeaderBuilder().setTitle("See your order history").setSubtitle("Digital Orders")
        .setSummary("Total Orders: 10")
    ).addRow(
      ListBuilder.RowBuilder().setTitle("In last month").setSubtitle("4 prepaid orders").addEndItem(
        IconCompat.createWithResource(context, R.mipmap.ic_blibli_round), ListBuilder.ICON_IMAGE
      ).setPrimaryAction(activityAction)
    ).build()

  private fun createSliceNewOrderHeader(
    context: Context,
    sliceUri: Uri,
    activityAction: SliceAction,
    subtitle: String = "",
    summaryString: String = "",
    descriptionTitle: String = "",
    description: String = ""
  ): Slice {
    return ListBuilder(
      context, sliceUri, ListBuilder.INFINITY
    ).setAccentColor(0xff0F9D) // Specify color for tinting icons
      .setHeader(

        ListBuilder.HeaderBuilder().setTitle("Creating new order").setSubtitle(subtitle)
          .setSummary(summaryString)
      ).addRow(
        ListBuilder.RowBuilder().setTitle(descriptionTitle).setSubtitle(description).addEndItem(
          IconCompat.createWithResource(context, R.mipmap.ic_blibli_round), ListBuilder.ICON_IMAGE
        ).setPrimaryAction(activityAction)
      ).build()
  }

  override fun onSlicePinned(sliceUri: Uri?) {
    // When data is received, call context.contentResolver.notifyChange(sliceUri, null) to
    // trigger MySliceProvider#onBindSlice(Uri) again.
  }

  override fun onSliceUnpinned(sliceUri: Uri?) {
    // Remove any observers if necessary to avoid memory leaks.
  }

  private fun exampleRowBuilder(
    context: Context, sliceUri: Uri, activityAction: SliceAction
  ) = ListBuilder(context, sliceUri, ListBuilder.INFINITY).addRow(
    ListBuilder.RowBuilder().setPrimaryAction(activityAction).setTitle("Your flight is at 10pm")
  ).build()
}