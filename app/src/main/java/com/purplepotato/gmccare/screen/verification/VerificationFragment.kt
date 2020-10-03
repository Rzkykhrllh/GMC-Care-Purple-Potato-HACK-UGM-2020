package com.purplepotato.gmccare.screen.verification

import android.Manifest.permission.CAMERA
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.purplepotato.gmccare.R
import com.purplepotato.gmccare.State
import com.purplepotato.gmccare.service.FireCloudStorage
import com.purplepotato.gmccare.service.RealtimeDatabase
import kotlinx.android.synthetic.main.fragment_verification.*

class VerificationFragment : Fragment(), View.OnClickListener {

    private val startActivityLaunch =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null && it.resultCode == Activity.RESULT_OK) {
                val bitmap = it.data?.extras?.get("data") as Bitmap
                viewModel.uploadImage(bitmap)
            }

        }

    private val requestCameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startActivityLaunch.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            } else {
                Log.d("camera", "not granted")
            }
        }

    private lateinit var viewModel: VerificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verification, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fbStorage = FireCloudStorage()
        val fbDatabase = RealtimeDatabase()
        val viewModelFactory = VerificationViewModelFactory(fbStorage, fbDatabase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(VerificationViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAddVerificationPhoto.setOnClickListener(this)
        uploadImageState()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnAddVerificationPhoto -> {
                requestCameraPermissionLauncher.launch(CAMERA)
            }
        }
    }

    private fun uploadImageState() {
        viewModel.uploadImageState().observe(viewLifecycleOwner, { response ->
            when (response) {
                is State.OnSuccess -> {
                    showLoading(false)
                    findNavController().navigate(R.id.fromVerificationToHomeFragment)
                }

                is State.OnLoading -> {
                    showLoading(true)
                }

                is State.OnError -> {
                    showLoading(false)
                    Toast.makeText(activity, response.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            uploadLoadingFrame.visibility = View.VISIBLE
            uploadProgressBar.visibility = View.VISIBLE
        } else {
            uploadLoadingFrame.visibility = View.GONE
            uploadProgressBar.visibility = View.GONE
        }
    }


}