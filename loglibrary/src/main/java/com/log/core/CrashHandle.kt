package com.log.core

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.log.LogUtils
import java.lang.StringBuilder


/**
 * ============================================================================
 *
 * Author: tys
 * Date: 2020-10-05 08:15
 * Mail: tyongsong66@gmail.com
 * Description: uncaught exception handler
 * Use: CrashHandle.INSTANCE.init(this){}
 *
 * ============================================================================
 */
class CrashHandle private constructor() : Thread.UncaughtExceptionHandler {


    /**
     * called when a thread abruptly terminates.
     * the param is string which exception and devices information
     */
    private var mHandleException: ((String) -> Unit)? = null

    private var mDefaultUncaughtExceptionHandler: Thread.UncaughtExceptionHandler? = null

    private var mContext: Context? = null

    companion object {
        private const val TAG = "CrashHandle"
        val INSTANCE: CrashHandle by lazy {
            CrashHandle()
        }
    }


    fun init(appContext: Context, handleException: ((String) -> Unit)? = null) {
        this.mContext = appContext.applicationContext
        this.mHandleException = handleException
        this.mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        val devInfo = collectDeviceInfo(mContext)
        handException(t, e, devInfo)
        mDefaultUncaughtExceptionHandler?.uncaughtException(t, e)
    }

    private fun collectDeviceInfo(mContext: Context?): Map<String, String> {
        val mutableMapOf = mutableMapOf<String, String>()
        mContext?.let { context ->
            val packageName = context.packageName
            val packageInfo =
                context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            val versionCode =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    packageInfo.longVersionCode
                } else {
                    packageInfo.versionCode
                }
            mutableMapOf["versionCode"] = versionCode.toString()

            val versionName = packageInfo.packageName
            mutableMapOf["versionName"] = versionName

            kotlin.runCatching {
                val fields = Build::class.java.declaredFields
                for (field in fields) {
                    field.isAccessible = true
                    val filedValue = field.get(null)?.toString()
                    filedValue?.let {
                        mutableMapOf[field.name] = it
                    }
                }
            }
        }
        return mutableMapOf
    }

    private fun handException(t: Thread, e: Throwable, devInfo: Map<String, String>) {
        val sb = StringBuilder("\n")
        devInfo.forEach {
            sb.append("${it.key} : ${it.value}\n")
        }
        sb.append("$t\n")
        sb.append(e.stackTraceToString()).append("\n")
        val result = sb.toString()
        LogUtils.e(TAG, result)
        mHandleException?.let { it(result) }
    }

}
