package com.draco.buoy.utils

import android.content.ContentResolver
import android.provider.Settings
import com.draco.buoy.models.BatterySaverConstantsConfig
import com.draco.buoy.repositories.constants.BatterySaverSecureSettings

class BatterySaverManager(private val contentResolver: ContentResolver) {
    /**
     * Enable or disable low power mode
     */
    fun setLowPower(state: Boolean) {
        val intBool = if (state) 1 else 0
        Settings.Global.putInt(
            contentResolver,
            BatterySaverSecureSettings.LOW_POWER,
            intBool
        )
    }

    /**
     * Return true if low power mode is enabled
     */
    fun getLowPower(): Boolean {
        return Settings.Global.getInt(
            contentResolver,
            BatterySaverSecureSettings.LOW_POWER,
            0
        ) == 1
    }

    /**
     * Enable or disable low power sticky mode
     */
    fun setLowPowerSticky(state: Boolean) {
        val intBool = if (state) 1 else 0
        Settings.Global.putInt(
            contentResolver,
            BatterySaverSecureSettings.LOW_POWER_STICKY,
            intBool
        )
    }

    /**
     * Enable or disable low power sticky auto disable mode
     */
    fun setLowPowerStickyAutoDisableEnabled(state: Boolean) {
        val intBool = if (state) 1 else 0
        Settings.Global.putInt(
            contentResolver,
            BatterySaverSecureSettings.LOW_POWER_STICKY_AUTO_DISABLE_ENABLED,
            intBool
        )
    }

    /**
     * Set the raw battery saver constants secure setting
     */
    fun setConstantsString(constants: String?) {
        Settings.Global.putString(
            contentResolver,
            BatterySaverSecureSettings.BATTERY_SAVER_CONSTANTS,
            constants
        )
    }

    /**
     * Get the raw battery saver constants secure setting
     */
    fun getConstantsString(): String? {
        return Settings.Global.getString(
            contentResolver,
            BatterySaverSecureSettings.BATTERY_SAVER_CONSTANTS
        )
    }

    /**
     * Set the battery saver constants secure setting via a config
     */
    fun setConstantsConfig(config: BatterySaverConstantsConfig) {
        setConstantsString(config.toString())
    }

    /**
     * Quick way to apply either type of config
     */
    fun apply(config: Any?) {
        when (config) {
            is String? -> setConstantsString(config)
            is BatterySaverConstantsConfig -> setConstantsConfig(config)
        }

        setLowPowerSticky(true)
        setLowPowerStickyAutoDisableEnabled(false)
    }

    /**
     * Reset constants to default values
     */
    fun resetToDefault() {
        apply(null)

        setLowPowerSticky(false)
        setLowPowerStickyAutoDisableEnabled(true)
    }
}