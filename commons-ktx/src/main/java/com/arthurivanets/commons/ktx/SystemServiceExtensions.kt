/*
 * Copyright 2018 Arthur Ivanets, arthur.ivanets.l@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("SystemServiceUtils")

package com.arthurivanets.commons.ktx

import android.accounts.AccountManager
import android.app.*
import android.app.admin.DevicePolicyManager
import android.app.usage.NetworkStatsManager
import android.app.usage.StorageStatsManager
import android.app.usage.UsageStatsManager
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.companion.CompanionDeviceManager
import android.content.ClipboardManager
import android.content.Context
import android.content.RestrictionsManager
import android.content.pm.ShortcutManager
import android.hardware.ConsumerIrManager
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.input.InputManager
import android.hardware.usb.UsbManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.midi.MidiManager
import android.media.projection.MediaProjectionManager
import android.media.session.MediaSessionManager
import android.media.tv.TvInputManager
import android.net.ConnectivityManager
import android.net.IpSecManager
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.net.wifi.aware.WifiAwareManager
import android.net.wifi.p2p.WifiP2pManager
import android.net.wifi.rtt.WifiRttManager
import android.nfc.NfcManager
import android.os.*
import android.os.health.SystemHealthManager
import android.os.storage.StorageManager
import android.print.PrintManager
import android.telecom.TelecomManager
import android.telephony.CarrierConfigManager
import android.telephony.TelephonyManager
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.inputmethod.InputMethodManager
import android.view.textclassifier.TextClassificationManager
import android.view.textservice.TextServicesManager
import androidx.annotation.RequiresApi
import com.arthurivanets.commons.SdkVersions


/**
 *
 */
@get:JvmName("getConnectivityManager")
val Context.connectivityManager : ConnectivityManager
    get() = ((this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getInputMethodManager")
val Context.inputMethodManager : InputMethodManager
    get() = ((this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getPowerManager")
val Context.powerManager : PowerManager
    get() = ((this.getSystemService(Context.POWER_SERVICE) as PowerManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getWindowManager")
val Context.windowManager : WindowManager
    get() = ((this.getSystemService(Context.WINDOW_SERVICE) as WindowManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getAccountManager")
val Context.accountManager : AccountManager
    get() = ((this.getSystemService(Context.ACCOUNT_SERVICE) as AccountManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getActivityManager")
val Context.activityManager : ActivityManager
    get() = ((this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getAlarmManager")
val Context.alarmManager : AlarmManager
    get() = ((this.getSystemService(Context.ALARM_SERVICE) as AlarmManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getNotificationManager")
val Context.notificationManager : NotificationManager
    get() = ((this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getAccessibilityManager")
val Context.accessibilityManager : AccessibilityManager
    get() = ((this.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.KITKAT)
@get:JvmName("getCaptioningManager")
val Context.captioningManager : CaptioningManager
    get() = ((this.getSystemService(Context.CAPTIONING_SERVICE) as CaptioningManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getKeyguardManager")
val Context.keyguardManager : KeyguardManager
    get() = ((this.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getLocationManager")
val Context.locationManager : LocationManager
    get() = ((this.getSystemService(Context.LOCATION_SERVICE) as LocationManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getSearchManager")
val Context.searchManager : SearchManager
    get() = ((this.getSystemService(Context.SEARCH_SERVICE) as SearchManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getSensorManager")
val Context.sensorManager : SensorManager
    get() = ((this.getSystemService(Context.SENSOR_SERVICE) as SensorManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getStorageManager")
val Context.storageManager : StorageManager
    get() = ((this.getSystemService(Context.STORAGE_SERVICE) as StorageManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.O)
@get:JvmName("getStorageStatsManager")
val Context.storageStatsManager : StorageStatsManager
    get() = ((this.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getWallpaperManager")
val Context.wallpaperManager : WallpaperManager
    get() = ((this.getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getVibrator")
val Context.vibrator : Vibrator
    get() = ((this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.P)
@get:JvmName("getIpSecManager")
val Context.ipSecManager : IpSecManager
    get() = ((this.getSystemService(Context.IPSEC_SERVICE) as IpSecManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.M)
@get:JvmName("getNetworkStatsManager")
val Context.networkStatsManager : NetworkStatsManager
    get() = ((this.getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getWifiManager")
val Context.wifiManager : WifiManager
    get() = ((this.getSystemService(Context.WIFI_SERVICE) as WifiManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getWifiP2pManager")
val Context.wifiP2pManager : WifiP2pManager
    get() = ((this.getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.O)
@get:JvmName("getWifiAwareManager")
val Context.wifiAwareManager : WifiAwareManager
    get() = ((this.getSystemService(Context.WIFI_AWARE_SERVICE) as WifiAwareManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.P)
@get:JvmName("getWifiRttManager")
val Context.wifiRttManager : WifiRttManager
    get() = ((this.getSystemService(Context.WIFI_RTT_RANGING_SERVICE) as WifiRttManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getNsdManager")
val Context.nsdManager : NsdManager
    get() = ((this.getSystemService(Context.NSD_SERVICE) as NsdManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getAudioManager")
val Context.audioManager : AudioManager
    get() = ((this.getSystemService(Context.AUDIO_SERVICE) as AudioManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.LOLLIPOP)
@get:JvmName("getMediaSessionManager")
val Context.mediaSessionManager : MediaSessionManager
    get() = ((this.getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getTelephonyManager")
val Context.telephonyManager : TelephonyManager
    get() = ((this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.LOLLIPOP)
@get:JvmName("getTelecomManager")
val Context.telecomManager : TelecomManager
    get() = ((this.getSystemService(Context.TELECOM_SERVICE) as TelecomManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.O)
@get:JvmName("getMediaSessionManager")
val Context.carrierConfigManager : CarrierConfigManager
    get() = ((this.getSystemService(Context.CARRIER_CONFIG_SERVICE) as CarrierConfigManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getClipboardManager")
val Context.clipboardManager : ClipboardManager
    get() = ((this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.O)
@get:JvmName("getTextClassificationManager")
val Context.textCalassificationManager : TextClassificationManager
    get() = ((this.getSystemService(Context.TEXT_CLASSIFICATION_SERVICE) as TextClassificationManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getTextServicesManager")
val Context.textServicesManager : TextServicesManager
    get() = ((this.getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE) as TextServicesManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.LOLLIPOP)
@get:JvmName("getAppWidgetManager")
val Context.appWidgetManager : AppWidgetManager
    get() = ((this.getSystemService(Context.APPWIDGET_SERVICE) as AppWidgetManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getDropboxManager")
val Context.dropboxManager : DropBoxManager
    get() = ((this.getSystemService(Context.DROPBOX_SERVICE) as DropBoxManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getDevicePolicyManager")
val Context.devicePolicyManager : DevicePolicyManager
    get() = ((this.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getUiModeManager")
val Context.uiModeManager : UiModeManager
    get() = ((this.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getDownloadManager")
val Context.downloadManager : DownloadManager
    get() = ((this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.LOLLIPOP)
@get:JvmName("getBatteryManager")
val Context.batteryManager : BatteryManager
    get() = ((this.getSystemService(Context.BATTERY_SERVICE) as BatteryManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getNfcManager")
val Context.nfcManager : NfcManager
    get() = ((this.getSystemService(Context.NFC_SERVICE) as NfcManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getBluetoothManager")
val Context.bluetoothManager : BluetoothManager
    get() = ((this.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getUsbManager")
val Context.usbManager : UsbManager
    get() = ((this.getSystemService(Context.USB_SERVICE) as UsbManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getInputManager")
val Context.inputManager : InputManager
    get() = ((this.getSystemService(Context.INPUT_SERVICE) as InputManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getDisplayManager")
val Context.displayManager : DisplayManager
    get() = ((this.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:JvmName("getUsbManager")
val Context.userManager : UserManager
    get() = ((this.getSystemService(Context.USER_SERVICE) as UserManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.LOLLIPOP)
@get:JvmName("getRestrictionsManager")
val Context.restrictionsManager : RestrictionsManager
    get() = ((this.getSystemService(Context.RESTRICTIONS_SERVICE) as RestrictionsManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.KITKAT)
@get:JvmName("getAppOpsManager")
val Context.appOpsManager : AppOpsManager
    get() = ((this.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.LOLLIPOP)
@get:JvmName("getCameraManager")
val Context.cameraManager : CameraManager
    get() = ((this.getSystemService(Context.CAMERA_SERVICE) as CameraManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.KITKAT)
@get:JvmName("getPrintManager")
val Context.printManager : PrintManager
    get() = ((this.getSystemService(Context.PRINT_SERVICE) as PrintManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.O)
@get:JvmName("getCompanionDeviceManager")
val Context.companionDeviceManager : CompanionDeviceManager
    get() = ((this.getSystemService(Context.COMPANION_DEVICE_SERVICE) as CompanionDeviceManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.KITKAT)
@get:JvmName("getConsumerIrManager")
val Context.consumerIrManager : ConsumerIrManager
    get() = ((this.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.LOLLIPOP)
@get:JvmName("getTvInputManager")
val Context.tvInputManager : TvInputManager
    get() = ((this.getSystemService(Context.TV_INPUT_SERVICE) as TvInputManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.LOLLIPOP_MR1)
@get:JvmName("getUsageStatsManager")
val Context.usageStatsManager : UsageStatsManager
    get() = ((this.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.LOLLIPOP)
@get:JvmName("getMediaProjectionManager")
val Context.mediaProjectionManager : MediaProjectionManager
    get() = ((this.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.M)
@get:JvmName("getMidiManager")
val Context.midiManager : MidiManager
    get() = ((this.getSystemService(Context.MIDI_SERVICE) as MidiManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.O_MR1)
@get:JvmName("getHardwarePropertiesManager")
val Context.hardwarePropertiesManager : HardwarePropertiesManager
    get() = ((this.getSystemService(Context.HARDWARE_PROPERTIES_SERVICE) as HardwarePropertiesManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.N_MR1)
@get:JvmName("getShortcutManager")
val Context.shortcutManager : ShortcutManager
    get() = ((this.getSystemService(Context.SHORTCUT_SERVICE) as ShortcutManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))

/**
 *
 */
@get:RequiresApi(SdkVersions.N)
@get:JvmName("getSystemHealthManager")
val Context.systemHealthManager : SystemHealthManager
    get() = ((this.getSystemService(Context.SYSTEM_HEALTH_SERVICE) as SystemHealthManager?) ?: throw UnsupportedOperationException("No Corresponding Service Found."))