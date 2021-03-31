package com.draco.buoy.services

import android.service.quicksettings.TileService
import com.draco.buoy.repositories.profiles.BatterySaverConstantsConfigProfiles
import com.draco.buoy.utils.BatterySaverManager

class TileHighService : TileService() {
    private lateinit var batterySaverManager: BatterySaverManager

    override fun onCreate() {
        super.onCreate()
        batterySaverManager = BatterySaverManager(contentResolver)
    }

    override fun onClick() {
        super.onClick()
        batterySaverManager.apply(BatterySaverConstantsConfigProfiles.HIGH)
    }
}