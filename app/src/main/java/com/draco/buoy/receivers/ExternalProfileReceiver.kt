package com.draco.buoy.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.draco.buoy.R
import com.draco.buoy.repositories.constants.ExternalProfileReceiverIntentConstants
import com.draco.buoy.repositories.profiles.BatterySaverConstantsConfigProfiles
import com.draco.buoy.utils.BatterySaverManager

class ExternalProfileReceiver : BroadcastReceiver() {
    private lateinit var batterySaverManager: BatterySaverManager

    override fun onReceive(context: Context, intent: Intent) {
        batterySaverManager = BatterySaverManager(context.contentResolver)

        val profileName = intent.getStringExtra(ExternalProfileReceiverIntentConstants.KEY_PROFILE_NAME)
        val constants = intent.getStringExtra(ExternalProfileReceiverIntentConstants.KEY_CONSTANTS)

        profileName?.let {
            when (it) {
                context.getString(R.string.pref_profile_key_light) -> batterySaverManager.apply(BatterySaverConstantsConfigProfiles.LIGHT)
                context.getString(R.string.pref_profile_key_moderate) -> batterySaverManager.apply(BatterySaverConstantsConfigProfiles.MODERATE)
                context.getString(R.string.pref_profile_key_high) -> batterySaverManager.apply(BatterySaverConstantsConfigProfiles.HIGH)
                context.getString(R.string.pref_profile_key_extreme) -> batterySaverManager.apply(BatterySaverConstantsConfigProfiles.EXTREME)
            }
        }

        constants?.let {
            batterySaverManager.apply(it)
        }

        resultData = batterySaverManager.getConstantsString()
    }
}