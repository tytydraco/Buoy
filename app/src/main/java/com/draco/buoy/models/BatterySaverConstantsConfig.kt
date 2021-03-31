package com.draco.buoy.models

import com.draco.buoy.repositories.BatterySaverConstants
import com.draco.buoy.repositories.PowerManagerLocationModes

data class BatterySaverConstantsConfig(
    var advertiseIsEnabled: Boolean =       true,
    var dataSaverDisabled: Boolean =        true,
    var enableNightMode: Boolean =          true,
    var launchBoostDisabled: Boolean =      true,
    var vibrationDisabled: Boolean =        true,
    var animationDisabled: Boolean =        false,
    var soundTriggerDisabled: Boolean =     true,
    var fullBackupDeferred: Boolean =       true,
    var keyValueBackupDeferred: Boolean =   true,
    var fireWallDisabled: Boolean =         true,
    var gpsMode: Int =                      PowerManagerLocationModes.ALL_DISABLED_SCREEN_OFF,
    var adjustBrightnessDisabled: Boolean = true,
    var adjustBrightnessFactor: Float =     0.5f,
    var forceAllAppsStandby: Boolean =      true,
    var forceBackgroundCheck: Boolean =     true,
    var optionalSensorsDisabled: Boolean =  true,
    var aodDisabled: Boolean =              true,
    var quickDozeEnabled: Boolean =         true
) {
    override fun toString(): String {
        return  "${BatterySaverConstants.ADVERTISE_IS_ENABLED}=$advertiseIsEnabled," +
                "${BatterySaverConstants.DATASAVER_DISABLED}=$dataSaverDisabled," +
                "${BatterySaverConstants.ENABLE_NIGHT_MODE}=$enableNightMode," +
                "${BatterySaverConstants.LAUNCH_BOOST_DISABLED}=$launchBoostDisabled," +
                "${BatterySaverConstants.VIBRATION_DISABLED}=$vibrationDisabled," +
                "${BatterySaverConstants.ANIMATION_DISABLED}=$animationDisabled," +
                "${BatterySaverConstants.SOUNDTRIGGER_DISABLED}=$soundTriggerDisabled," +
                "${BatterySaverConstants.FULLBACKUP_DEFERRED}=$fullBackupDeferred," +
                "${BatterySaverConstants.KEYVALUEBACKUP_DEFERRED}=$keyValueBackupDeferred," +
                "${BatterySaverConstants.FIREWALL_DISABLED}=$fireWallDisabled," +
                "${BatterySaverConstants.GPS_MODE}=$gpsMode," +
                "${BatterySaverConstants.ADJUST_BRIGHTNESS_DISABLED}=$adjustBrightnessDisabled," +
                "${BatterySaverConstants.ADJUST_BRIGHTNESS_FACTOR}=$adjustBrightnessFactor," +
                "${BatterySaverConstants.FORCE_ALL_APPS_STANDBY}=$forceAllAppsStandby," +
                "${BatterySaverConstants.FORCE_BACKGROUND_CHECK}=$forceBackgroundCheck," +
                "${BatterySaverConstants.OPTIONAL_SENSORS_DISABLED}=$optionalSensorsDisabled," +
                "${BatterySaverConstants.AOD_DISABLED}=$aodDisabled," +
                "${BatterySaverConstants.QUICK_DOZE_ENABLED}=$quickDozeEnabled"
    }

    fun import(string: String) {
        val keyValueMap = string.split(",").associate {
            val (key, value) = it.split("=")
            key to value
        }

        for ((key, value) in keyValueMap) {
            when (key) {
                BatterySaverConstants.ADVERTISE_IS_ENABLED -> advertiseIsEnabled = value.toBoolean()
                BatterySaverConstants.DATASAVER_DISABLED -> dataSaverDisabled = value.toBoolean()
                BatterySaverConstants.ENABLE_NIGHT_MODE -> enableNightMode = value.toBoolean()
                BatterySaverConstants.LAUNCH_BOOST_DISABLED -> launchBoostDisabled = value.toBoolean()
                BatterySaverConstants.VIBRATION_DISABLED -> vibrationDisabled = value.toBoolean()
                BatterySaverConstants.ANIMATION_DISABLED -> animationDisabled = value.toBoolean()
                BatterySaverConstants.SOUNDTRIGGER_DISABLED -> soundTriggerDisabled = value.toBoolean()
                BatterySaverConstants.FULLBACKUP_DEFERRED -> fullBackupDeferred = value.toBoolean()
                BatterySaverConstants.KEYVALUEBACKUP_DEFERRED -> keyValueBackupDeferred = value.toBoolean()
                BatterySaverConstants.FIREWALL_DISABLED -> fireWallDisabled = value.toBoolean()
                BatterySaverConstants.GPS_MODE -> gpsMode = value.toInt()
                BatterySaverConstants.ADJUST_BRIGHTNESS_DISABLED -> adjustBrightnessDisabled = value.toBoolean()
                BatterySaverConstants.ADJUST_BRIGHTNESS_FACTOR -> adjustBrightnessFactor = value.toFloat()
                BatterySaverConstants.FORCE_ALL_APPS_STANDBY -> forceAllAppsStandby = value.toBoolean()
                BatterySaverConstants.FORCE_BACKGROUND_CHECK -> forceBackgroundCheck = value.toBoolean()
                BatterySaverConstants.OPTIONAL_SENSORS_DISABLED -> optionalSensorsDisabled = value.toBoolean()
                BatterySaverConstants.AOD_DISABLED -> aodDisabled = value.toBoolean()
                BatterySaverConstants.QUICK_DOZE_ENABLED -> quickDozeEnabled = value.toBoolean()
            }
        }
    }
}