package com.app.repairo.app.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object  OpengalleryIntent {
    var photoFile:File?=null
     fun getPickImageChooserIntent( context: Context): Intent? {
        photoFile = getOutputMediaFile(context);
        val outputFileUri: Uri = getOutputMediaFileUri(photoFile!!,context)
        val allIntents: MutableList<Intent> = ArrayList()
        val packageManager: PackageManager = context.packageManager
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val listCam: List<ResolveInfo> =
                packageManager.queryIntentActivities(captureIntent, 0)
        for (res in listCam) {
            val intent = Intent(captureIntent)
            intent.setComponent(ComponentName(res.activityInfo.packageName, res.activityInfo.name))
            intent.setPackage(res.activityInfo.packageName)
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
            }
            allIntents.add(intent)
        }
        val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
        galleryIntent.setType("image/*")
        val listGallery: List<ResolveInfo> =
                packageManager.queryIntentActivities(galleryIntent, 0)
        for (res in listGallery) {
            val intent = Intent(galleryIntent)
            intent.setComponent(ComponentName(res.activityInfo.packageName, res.activityInfo.name))
            intent.setPackage(res.activityInfo.packageName)
            allIntents.add(intent)
        }
        var mainIntent: Intent? = allIntents[allIntents.size - 1]
        for (intent in allIntents) {
            if (intent.getComponent()!!.getClassName() === "com.android.documentsui.DocumentsActivity") {
                mainIntent = intent
                break
            }
        }
        allIntents.remove(mainIntent)
        val chooserIntent: Intent = Intent.createChooser(mainIntent, "Select source")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toTypedArray())
        return chooserIntent
    }
    fun getOutputMediaFileUri(file: File, context: Context): Uri {
        return FileProvider.getUriForFile(
                context,
                "com.app.repairo.app.provider",
                file
        )
    }
    private fun getOutputMediaFile( context: Context): File {
        val mediaStorageDir = File(
               context.externalCacheDir,
                "CameraDemo"
        )
        //        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), "CameraDemo");
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdir()
        }
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        return File(
                mediaStorageDir.getPath() + File.separator
                        .toString() + "IMG_" + timeStamp + "." + "jpg"
        )
    }
}