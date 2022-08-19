package com.draco.buoy.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.draco.buoy.R
import com.draco.buoy.databinding.ActivityMainBinding
import com.draco.buoy.fragments.MainPreferenceFragment
import com.draco.buoy.utils.PermissionUtils

class MainActivity : AppCompatActivity() {
    private lateinit var preferences: FragmentContainerView
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // inflate the layout and set content view to root
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        preferences = binding.preferences;

        /* If we are missing a permission, lock the user in the permission activity */
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