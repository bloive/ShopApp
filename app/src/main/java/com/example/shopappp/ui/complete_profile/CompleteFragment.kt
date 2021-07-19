package com.example.shopappp.ui.complete_profile

import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.shopappp.BaseFragment
import com.example.shopappp.databinding.CompleteFragmentBinding
import com.example.shopappp.extensions.setImageUri
import com.example.shopappp.extensions.toFile
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompleteFragment : BaseFragment<CompleteFragmentBinding>(CompleteFragmentBinding::inflate) {

    private lateinit var viewModel: CompleteViewModel
    var uri: Uri? = null
    override fun start(inflater: LayoutInflater, container: ViewGroup?) {
        setListeners()
    }

    private fun setListeners() {
        binding!!.imgProfile.setOnClickListener {
            if (hasCameraPermission() && hasReadExternalStoragePermission() && hasWriteExternalStoragePermission()) {
                openCamera()
            } else {
                requestPermissions(requestPermissionsResult)
            }
        }
    }

    private val requestPermissionsResult =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it[android.Manifest.permission.CAMERA] == true && it[android.Manifest.permission.WRITE_EXTERNAL_STORAGE] == true && it[android.Manifest.permission.READ_EXTERNAL_STORAGE] == true) {
                openCamera()
            }
        }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun openCamera() {
        val filename = "shopimg.jpg"
        val imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL_PRIMARY
            )
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
        val imageDetails = ContentValues().apply {
            put(MediaStore.Audio.Media.DISPLAY_NAME, filename)
        }
        requireActivity().contentResolver.insert(imageUri, imageDetails).let {
            uri = it
            takePicture.launch(uri)
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            binding!!.imgProfile.setImageUri(uri)
            val file = requireContext().toFile(uri!!)
            d("IMAGE_URI", "${uri} $it")
        }

}