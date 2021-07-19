package com.example.shopappp.extensions

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.File

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Context.toFile(uri: Uri): File? {
    if (uri.path == null) {
        return null
    }
    var realPath = String()
    val databaseUri: Uri
    val selection: String?
    val selectionArgs: Array<String>?
    if (uri.path!!.contains("/document/image:")) {
        databaseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        selection = "_id=?"
        selectionArgs = arrayOf(DocumentsContract.getDocumentId(uri).split(":")[1])
    } else {
        databaseUri = uri
        selection = null
        selectionArgs = null
    }
    try {
        val column = "_data"
        val projection = arrayOf(column)
        val cursor = contentResolver.query(
            databaseUri,
            projection,
            selection,
            selectionArgs,
            null
        )
        cursor?.let {
            if (it.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(column)
                realPath = cursor.getString(columnIndex)
            }
            cursor.close()
        }
    } catch (e: Exception) {
        Log.i("GetFileUri Exception:", e.message ?: "")
    }
    val path = if (realPath.isNotEmpty()) realPath else {
        when {
            uri.path!!.contains("/document/raw:") -> uri.path!!.replace(
                "/document/raw:",
                ""
            )
            uri.path!!.contains("/document/primary:") -> uri.path!!.replace(
                "/document/primary:",
                "/storage/emulated/0/"
            )
            else -> return null
        }
    }
    return File(path)
}
}