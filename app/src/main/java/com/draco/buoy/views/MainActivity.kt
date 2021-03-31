package com.draco.buoy.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.draco.buoy.R
import com.draco.buoy.fragments.MainPreferenceFragment
import com.draco.buoy.utils.PermissionUtils
import com.draco.buoy.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var preferences: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = findViewById(R.id.preferences)

        if (!PermissionUtils.isPermissionsGranted(this, android.Manifest.permission.WRITE_SECURE_SETTINGS))
            goToPermissionActivity()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.preferences, MainPreferenceFragment())
            .commit()
    }

    private fun goToPermissionActivity() {
        val intent = Intent(this, PermissionActivity::class.java)
        startActivity(intent)
    }
}