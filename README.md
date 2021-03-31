# Buoy
An extension to the built in Android Battery Saver

# Description
The built-in Android battery saver mode is actually quite powerful. The only disappointment is that none of it is configurable out of the box. That's where Buoy comes in. Buoy makes using the built-in battery saver feasible in any situation. If you want mild savings during the day without impeding performance, it can be done. If you need to squeeze every last ounce of power out of your device, it can be done. If you want anything in between, it can be done!

# Features
Buoy uses hidden Android settings to specify custom behavior for the built in battery saver. Features include the following toggles:

- Advertising to other apps that low power mode is enabled
- Android's data saver for metered WiFi or mobile data connections
- The built-in dark mode
- Launch boost to accelerate app starts
- Vibration
- Showing window and activity animations
- Allowing apps to use the SoundTrigger HAL
- Deferring full device backups for later
- Deferring app setting backups for later
- Using the built-in web firewall to protect against possibly malicious sites
- Changing the location access mode restrictions for apps
- Reducing the max brightness of the panel
- Forcing all apps into standby mode
- Forcing all apps to not check data in the background
- Disabling unnecessary sensors
- Using the Always-On-Display
- Putting the device into deep sleep as soon as the screen turns off

# Sticky Low Power
Buoy also enables something called "sticky" low power mode. Usually, when the device is plugged in and unplugged, the low power mode is then disabled. However, sticky mode re-applies low power mode afterwards to continue saving battery.

# Disclaimers
Note that this app requires the WRITE_SECURE_SETTINGS permission that can be granted with EITHER a PC using ADB or root. Root is NOT required for this app, it is optional. Android 8.0+ is supported, with more features enabled on Android 10+.

Uninstalling the app will not reset the battery saver configuration. You must click the Reset button to undo all changes made by Buoy.
