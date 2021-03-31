package com.draco.buoy.models

import com.draco.buoy.repositories.BatterySaverConstants

data class BatterySaverConstantsConfig(
    var advertiseIsEnabled: Boolean =       false,
    var dataSaverDisabled: Boolean =        false,
    var enableNightMode: Boolean =          false,
    var launchBoostDisabled: Boolean =      false,
    var vibrationDisabled: Boolean =        false,
    var animationDisabled: Boolean =        false,
    var soundTiggerDisabled: Boolean =      false,
    var fullBackupDeferred: Boolean =       false,
    var keyValueBackupDeferred: Boolean =   false,
    var fireWallDisabled: Boolean =         false,
    var gpsMode: Int =                      0,
    var adjustBrightnessDisabled: Boolean = false,
    var adjustBrightnessFactor: Float =     1f,
    var forceAllAppsStandby: Boolean =      false,
    var forceBackgroundCheck: Boolean =     false,
    var optionalSensorsDisabled: Boolean =  false,
    var aodDisabled: Boolean =              false,
    var quickDozeEnabled: Boolean =         false
) {
    override fun toString(): String {
        return  "${BatterySaverConstants.ADVERTISE_IS_ENABLED}=$advertiseIsEnabled," +
                "${BatterySaverConstants.DATASAVER_DISABLED}=$dataSaverDisabled," +
                "${BatterySaverConstants.ENABLE_NIGHT_MODE}=$enableNightMode," +
                "${BatterySaverConstants.LAUNCH_BOOST_DISABLED}=$launchBoostDisabled," +
                "${BatterySaverConstants.VIBRATION_DISABLED}=$vibrationDisabled," +
                "${BatterySaverConstants.ANIMATION_DISABLED}=$animationDisabled," +
                "${BatterySaverConstants.SOUNDTRIGGER_DISABLED}=$soundTiggerDisabled," +
                "${BatterySaverConstants.FULLBACKUP_DEFERRED}=$fullBackupDeferred," +
                "${BatterySaverConstants.KEYVALUEBACKUP_DEFERRED}=$keyValueBackupDeferred" +
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

    fun fromString(string: String) {
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
                BatterySaverConstants.SOUNDTRIGGER_DISABLED -> soundTiggerDisabled = value.toBoolean()
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