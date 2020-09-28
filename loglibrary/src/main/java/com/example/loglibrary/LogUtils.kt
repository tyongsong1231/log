package com.example.loglibrary


/**
 * ============================================================================
 *
 * Author: tys
 * Date: 2020-09-22 02:25
 * Mail: tyongsong66@gmail.com
 * Description: TODO
 *
 * ============================================================================
 */
object LogUtils : AbstractLog() {

    init {
        setLogLevel(VERBOSE)
        setLogger(
            if (("Dalvik" == System.getProperty("java.vm.name"))) {
                AndroidLog()
            } else {
                JavaLog()
            }
        )
    }

    class JavaLog : LogInterface {
        override fun v(TAG: String, msg: String) {
            println("$TAG $msg")
        }

        override fun i(TAG: String, msg: String) {
            println("$TAG $msg")
        }

        override fun d(TAG: String, msg: String) {
            println("$TAG $msg")
        }

        override fun w(TAG: String, msg: String) {
            println("$TAG $msg")
        }

        override fun e(TAG: String, msg: String) {
            println("$TAG $msg")
        }

    }

    class AndroidLog : LogInterface {
        override fun v(TAG: String, msg: String) {
            android.util.Log.v(TAG, msg)
        }

        override fun i(TAG: String, msg: String) {
            android.util.Log.i(TAG, msg)
        }

        override fun d(TAG: String, msg: String) {
            android.util.Log.d(TAG, msg)
        }

        override fun w(TAG: String, msg: String) {
            android.util.Log.w(TAG, msg)
        }

        override fun e(TAG: String, msg: String) {
            android.util.Log.e(TAG, msg)
        }

    }


}