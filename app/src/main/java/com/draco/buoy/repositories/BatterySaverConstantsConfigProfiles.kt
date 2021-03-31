package com.draco.buoy.repositories

import com.draco.buoy.models.BatterySaverConstantsConfig

object BatterySaverConstantsConfigProfiles {
    /**
     * Keep the system running smoothly with some idle tweaks
     */
    val LIGHT = BatterySaverConstantsConfig(
        advertiseIsEnabled = false,
        enableNightMode = false,
        launchBoostDisabled = false,
        vibrationDisabled = false,
        soundTriggerDisabled = false,
        fireWallDisabled = false,
        gpsMode = PowerManagerLocationModes.THROTTLE_SCREEN_OFF,
        forceAllAppsStandby = false,
        forceBackgroundCheck = false,
        aodDisabled = false
    )

    /**
     * Start using some mildly intrusive tweaks to save power
     */
    val MODERATE = BatterySaverConstantsConfig(
        launchBoostDisabled = false,
        vibrationDisabled = false,
        soundTriggerDisabled = false,
        fireWallDisabled = false,
        gpsMode = PowerManagerLocationModes.THROTTLE_SCREEN_OFF,
        forceAllAppsStandby = false,
        forceBackgroundCheck = false,
    )

    /**
     * Sacrifice performance for some extra battery life
     */
    val HIGH = BatterySaverConstantsConfig(
        dataSaverDisabled = false,
        adjustBrightnessDisabled = false,
        adjustBrightnessFactor = 0.75f
    )

    /**
     * The most we can possibly do to save power
     */
    val EXTREME = BatterySaverConstantsConfig(
        dataSaverDisabled = false,
        animationDisabled = true,
        adjustBrightnessDisabled = false
    )
}