package com.draco.buoy.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.draco.buoy.R
import com.draco.buoy.models.BatterySaverConstantsConfig
import com.draco.buoy.repositories.BatterySaverConstantsConfigProfiles
import com.draco.buoy.utils.BatterySaverManager
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.snackbar.Snackbar

class MainPreferenceFragment : PreferenceFragmentCompat() {
    private lateinit var batterySaverManager: BatterySaverManager

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main, rootKey)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        batterySaverManager = BatterySaverManager(context.contentResolver)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            getString(R.string.profile_key_reset) -> resetProfile()
            getString(R.string.profile_key_light) -> applyProfile(BatterySaverConstantsConfigProfiles.LIGHT)
            getString(R.string.profile_key_moderate) -> applyProfile(BatterySaverConstantsConfigProfiles.MODERATE)
            getString(R.string.profile_key_high) -> applyProfile(BatterySaverConstantsConfigProfiles.HIGH)
            getString(R.string.profile_key_extreme) -> applyProfile(BatterySaverConstantsConfigProfiles.EXTREME)

            getString(R.string.pref_developer_key) -> openURL(getString(R.string.developer_url))
            getString(R.string.pref_source_key) -> openURL(getString(R.string.source_url))
            getString(R.string.pref_contact_key) -> openURL(getString(R.string.contact_url))
            getString(R.string.pref_licenses_key) -> {
                val intent = Intent(requireContext(), OssLicensesMenuActivity::class.java)
                startActivity(intent)
            }
            else -> return super.onPreferenceTreeClick(preference)
        }
        return true
    }

    private fun applyProfile(profile: BatterySaverConstantsConfig) {
        batterySaverManager.setConstantsConfig(profile)
        batterySaverManager.setLowPower(true)
        batterySaverManager.setLowPowerSticky(true)
        batterySaverManager.setLowPowerStickyAutoDisableEnabled(false)
    }

    private fun resetProfile() {
        batterySaverManager.resetConstants()
        batterySaverManager.setLowPower(false)
        batterySaverManager.setLowPowerSticky(false)
        batterySaverManager.setLowPowerStickyAutoDisableEnabled(true)
    }

    private fun openURL(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        try {
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            Snackbar.make(requireView(), getString(R.string.snackbar_intent_failed), Snackbar.LENGTH_SHORT).show()
        }
    }
}