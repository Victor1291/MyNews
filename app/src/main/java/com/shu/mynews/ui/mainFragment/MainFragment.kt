package com.shu.mynews.ui.mainFragment


import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.shu.mynews.App
import com.shu.mynews.databinding.FragmentMainBinding
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor


private const val FILENAME_FORMAT = "yyy-MM-dd-HH-mm-ss"

class MainFragment : Fragment() {


    private var imageCapture: ImageCapture? = null
    private lateinit var executor: Executor
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels {
        App.appComponentUser.mainViewModelFactory()
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            startCamera()
            Toast.makeText(requireContext(), "permission is $isGranted", Toast.LENGTH_SHORT).show()
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("Created view fragment")
        executor = ContextCompat.getMainExecutor(requireContext())

        checkPermissions()

        binding.takePhoto.setOnClickListener {
            takePhoto()
        }

    }

    private fun checkPermissions() {
        println("Start check permission")
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startCamera()
            Toast.makeText(requireContext(), "permission Camera is granted", Toast.LENGTH_SHORT)
                .show()
        } else {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun takePhoto() {
        val imageCapture = imageCapture //?:  return
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        }

        val outputOptions = ImageCapture.OutputFileOptions.Builder(
            requireContext().contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )
            .build()

        imageCapture?.takePicture(
            outputOptions,
            executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Toast.makeText(
                        context,
                        "Photo saved on: ${outputFileResults.savedUri}",
                        Toast.LENGTH_SHORT
                    ).show()

                    Glide.with(binding.takenPhoto)
                        .load(outputFileResults.savedUri)
                        .circleCrop()
                        .into(binding.takenPhoto)

                    viewModel.saveTakenPhoto(
                        photoData = name,
                        path = outputFileResults.savedUri.toString()
                    )

                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        context,
                        "Photo failed: ${exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    exception.printStackTrace()
                }
            }
        )
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build()

            preview.setSurfaceProvider(binding.previewViewCamera.surfaceProvider)
            imageCapture = ImageCapture.Builder().build()

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                this,
                CameraSelector.DEFAULT_BACK_CAMERA,
                preview,
                imageCapture
            )

        }, executor)
    }
}