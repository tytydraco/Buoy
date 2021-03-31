package com.draco.buoy.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.preference.*
import com.draco.buoy.R
import com.draco.buoy.models.BatterySaverConstantsConfig
import com.draco.buoy.repositories.profiles.BatterySaverConstantsConfigProfiles
import com.draco.buoy.utils.BatterySaverManager
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.snackbar.Snackbar

class MainPreferenceFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var batterySaverManager: BatterySaverManager

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main, rootKey)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        batterySaverManager = BatterySaverManager(context.contentResolver)
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        refreshSettings()

        findPreference<EditTextPreference>(getString(R.string.pref_key_import))?.let {
            it.setOnPreferenceChangeListener { _, newValue ->
                batterySaverManager.setConstantsString(newValue as String)
                refreshSettings()
                return@setOnPreferenceChangeListener true
            }
        }
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            getString(R.string.pref_profile_key_reset) -> {
                batterySaverManager.reset()
                refreshSettings()
            }
            getString(R.string.pref_profile_key_light) -> {
                batterySaverManager.apply(BatterySaverConstantsConfigProfiles.LIGHT)
                refreshSettings()
            }
            getString(R.string.pref_profile_key_moderate) -> {
                batterySaverManager.apply(BatterySaverConstantsConfigProfiles.MODERATE)
                refreshSettings()
            }
            getString(R.string.pref_profile_key_high) -> {
                batterySaverManager.apply(BatterySaverConstantsConfigProfiles.HIGH)
                refreshSettings()
            }
            getString(R.string.pref_profile_key_extreme) -> {
                batterySaverManager.apply(BatterySaverConstantsConfigProfiles.EXTREME)
                refreshSettings()
            }

            getString(R.string.pref_key_export) -> exportSettings()
            getString(R.string.pref_key_import) -> (preference as EditTextPreference).text = batterySaverManager.getConstantsString()

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

    private fun exportSettings() {
        val intent = Intent()
            .setAction(Intent.ACTION_SEND)
            .putExtra(Intent.EXTRA_TEXT, batterySaverManager.getConstantsString())
            .setType("text/plain")
        val chooser = Intent.createChooser(intent, getString(R.string.export_share_title))
        startActivity(chooser)
    }

    private fun refreshSettings() {
        val currentProfileString = batterySaverManager.getConstantsString()
        val currentProfile = BatterySaverConstantsConfig().also {
            if (currentProfileString != null)
                it.import(currentProfileString)
        }

        findPreference<SwitchPreference>(getString(R.string.pref_config_key_advertise_is_enabled))?.isChecked = currentProfile.advertiseIsEnabled
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_datasaver_enabled))?.isChecked = !currentProfile.dataSaverDisabled
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_enable_night_mode))?.isChecked = currentProfile.enableNightMode
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_launch_boost_enabled))?.isChecked = !currentProfile.launchBoostDisabled
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_vibration_enabled))?.isChecked = !currentProfile.vibrationDisabled
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_animation_enabled))?.isChecked = !currentProfile.animationDisabled
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_soundtrigger_enabled))?.isChecked = !currentProfile.soundTriggerDisabled
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_fullbackup_deferred))?.isChecked = currentProfile.fullBackupDeferred
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_keyvaluebackup_deferred))?.isChecked = currentProfile.keyValueBackupDeferred
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_firewall_enabled))?.isChecked = !currentProfile.fireWallDisabled
        findPreference<ListPreference>(getString(R.string.pref_config_key_gps_mode))?.value = currentProfile.gpsMode.toString()
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_adjust_brightness_enabled))?.isChecked = !currentProfile.adjustBrightnessDisabled
        findPreference<SeekBarPreference>(getString(R.string.pref_config_key_adjust_brightness_factor))?.value = (currentProfile.adjustBrightnessFactor * 100).toInt()
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_force_all_apps_standby))?.isChecked = currentProfile.forceAllAppsStandby
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_force_background_check))?.isChecked = currentProfile.forceBackgroundCheck
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_optional_sensors_enabled))?.isChecked = !currentProfile.optionalSensorsDisabled
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_aod_enabled))?.isChecked = !currentProfile.aodDisabled
        findPreference<SwitchPreference>(getString(R.string.pref_config_key_quick_doze_enabled))?.isChecked = currentProfile.quickDozeEnabled
    }

    private fun saveSettings() {
        val config = BatterySaverConstantsConfig(
            findPreference<SwitchPreference>(getString(R.string.pref_config_key_advertise_is_enabled))!!.isChecked,
            !findPreference<SwitchPreference>(getString(R.string.pref_config_key_datasaver_enabled))!!.isChecked,
            findPreference<SwitchPreference>(getString(R.string.pref_config_key_enable_night_mode))!!.isChecked,
            !findPreference<SwitchPreference>(getString(R.string.pref_config_key_launch_boost_enabled))!!.isChecked,
            !findPreference<SwitchPreference>(getString(R.string.pref_config_key_vibration_enabled))!!.isChecked,
            !findPreference<SwitchPreference>(getString(R.string.pref_config_key_animation_enabled))!!.isChecked,
            !findPreference<SwitchPreference>(getString(R.string.pref_config_key_soundtrigger_enabled))!!.isChecked,
            findPreference<SwitchPreference>(getString(R.string.pref_config_key_fullbackup_deferred))!!.isChecked,
            findPreference<SwitchPreference>(getString(R.string.pref_config_key_keyvaluebackup_deferred))!!.isChecked,
            !findPreference<SwitchPreference>(getString(R.string.pref_config_key_firewall_enabled))!!.isChecked,
            findPreference<ListPreference>(getString(R.string.pref_config_key_gps_mode))!!.value.toInt(),
            !findPreference<SwitchPreference>(getString(R.string.pref_config_key_adjust_brightness_enabled))!!.isChecked,
            findPreference<SeekBarPreference>(getString(R.string.pref_config_key_adjust_brightness_factor))!!.value / 100f,
            findPreference<SwitchPreference>(getString(R.string.pref_config_key_force_all_apps_standby))!!.isChecked,
            findPreference<SwitchPreference>(getString(R.string.pref_config_key_force_background_check))!!.isChecked,
            !findPreference<SwitchPreference>(getString(R.string.pref_config_key_optional_sensors_enabled))!!.isChecked,
            !findPreference<SwitchPreference>(getString(R.string.pref_config_key_aod_enabled))!!.isChecked,
            findPreference<SwitchPreference>(getString(R.string.pref_config_key_quick_doze_enabled))!!.isChecked
        )
        batterySaverManager.apply(config)
        refreshSettings()
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

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        saveSettings()
    }
}