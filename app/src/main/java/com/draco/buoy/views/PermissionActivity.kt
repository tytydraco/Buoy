package com.draco.buoy.views

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.draco.buoy.R
import com.draco.buoy.databinding.ActivityPermissionBinding
import com.draco.buoy.viewmodels.PermissionActivityViewModel
import com.google.android.material.snackbar.Snackbar

class PermissionActivity : AppCompatActivity() {
    private val viewModel: PermissionActivityViewModel by viewModels()

    private lateinit var binding: ActivityPermissionBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionBinding.inflate(layoutInflater);
        setContentView(binding.root)

        /* Copy ADB command to clipboard */
        binding.command.setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("ADB Command", binding.command.text.toString())
            clipboardManager.setPrimaryClip(clip)

            Snackbar.make(binding.command, R.string.copied, Snackbar.LENGTH_SHORT).show()
        }

        /* Once permission is granted, return */
        viewModel.permissionGranted.observe(this) {
            if (it == true)
                finish()
        }
    }

    /* Disallow exit */
    override fun onBackPressed() {}
}