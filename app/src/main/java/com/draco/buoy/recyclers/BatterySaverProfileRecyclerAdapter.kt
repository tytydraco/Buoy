package com.draco.buoy.recyclers

import android.content.ContentResolver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.draco.buoy.R
import com.draco.buoy.repositories.BatterySaverConstantsConfigProfiles
import com.draco.buoy.utils.BatterySaverManager

class BatterySaverProfileRecyclerAdapter(
    contentResolver: ContentResolver
) : RecyclerView.Adapter<BatterySaverProfileRecyclerAdapter.ViewHolder>() {
    private val batterySaverProfiles = arrayOf(
        null, /* Reset */
        BatterySaverConstantsConfigProfiles.LIGHT,
        BatterySaverConstantsConfigProfiles.MODERATE,
        BatterySaverConstantsConfigProfiles.HIGH,
        BatterySaverConstantsConfigProfiles.EXTREME,
    )

    private val batterySaverManager = BatterySaverManager(contentResolver)

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val description = itemView.findViewById<TextView>(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile = batterySaverProfiles[position]

        when (profile) {
            null -> {
                holder.title.setText(R.string.profile_title_reset)
                holder.description.setText(R.string.profile_description_reset)
            }

            BatterySaverConstantsConfigProfiles.LIGHT -> {
                holder.title.setText(R.string.profile_title_light)
                holder.description.setText(R.string.profile_description_light)
            }

            BatterySaverConstantsConfigProfiles.MODERATE -> {
                holder.title.setText(R.string.profile_title_moderate)
                holder.description.setText(R.string.profile_description_moderate)
            }

            BatterySaverConstantsConfigProfiles.HIGH -> {
                holder.title.setText(R.string.profile_title_high)
                holder.description.setText(R.string.profile_description_high)
            }

            BatterySaverConstantsConfigProfiles.EXTREME -> {
                holder.title.setText(R.string.profile_title_extreme)
                holder.description.setText(R.string.profile_description_extreme)
            }
        }

        holder.itemView.setOnClickListener {
            if (profile == null) {
                batterySaverManager.resetConstants()
                batterySaverManager.setLowPower(false)
                batterySaverManager.setLowPowerSticky(false)
                batterySaverManager.setLowPowerStickyAutoDisableEnabled(true)
            } else {
                batterySaverManager.setConstantsConfig(profile)
                batterySaverManager.setLowPower(true)
                batterySaverManager.setLowPowerSticky(true)
                batterySaverManager.setLowPowerStickyAutoDisableEnabled(false)
            }
        }
    }

    override fun getItemCount(): Int = batterySaverProfiles.size
}