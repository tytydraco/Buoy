package com.draco.buoy.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.draco.buoy.R
import com.draco.buoy.utils.PermissionUtils
import com.draco.buoy.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!PermissionUtils.isPermissionsGranted(this, android.Manifest.permission.WRITE_SECURE_SETTINGS)) {
            goToPermissionActivity()
            return
        }
    }

    private fun goToPermissionActivity() {
        val intent = Intent(this, PermissionActivity::class.java)
        startActivity(intent)
    }
}