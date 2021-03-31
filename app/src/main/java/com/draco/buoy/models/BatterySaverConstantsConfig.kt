package com.draco.buoy.models

data class BatterySaverConstantsConfig(
    var advertiseIsEnabled: Boolean =       false,
    var dataSaverDisabled: Boolean =        false,
    var enableNightMode: Boolean =          false,
    var launchBoostDisabled: Boolean =      false,
    var vibrationDisabled: Boolean =        false,
    var animationDisabled: Boolean =        false,
    var soundTiggerDisabled: Boolean =      false,
    var fullBackupDeferred: Boolean =       false,
    var KeyValueBackupDeferred: Boolean =   false,
    var fireWallDisabled: Boolean =         false,
    var gpsMode: Int =                      0,
    var adjustBrightnessDisabled: Boolean = false,
    var adjustBrightnessFactor: Float =     1f,
    var forceAllAppsStandby: Boolean =      false,
    var forceBackgroundCheck: Boolean =     false,
    var optionalSensorsDisabled: Boolean =  false,
    var aodDisabled: Boolean =              false,
    var quickDozeEnabled: Boolean =         false
)