package com.draco.buoy.utils

import android.content.Context
import android.content.pm.PackageManager

class PermissionUtils {
    companion object {
        fun isPermissionsGranted(context: Context, permission: String): Boolean =
            context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }
}