package com.log.core

import com.log.LogLevel


/**
 * ============================================================================
 *
 * Author: tys
 * Date: 2020-09-20 04:09
 * Mail: tyongsong66@gmail.com
 * Description: TODO
 *
 * ============================================================================
 */
abstract class AbstractLog {

    private var logLevel = LogLevel.VERBOSE

    private var log: LogInterface? = null


    fun setLogLevel(logLevel: Int) {
        this.logLevel = logLevel
    }

    fun setLogger(logger: LogInterface) {
        log = logger
    }

    fun v(TAG: String, msg: String) {
        if (logLevel <= LogLevel.VERBOSE) {
            log?.v(TAG, msg)
        }
    }

    fun i(TAG: String, msg: String) {
        if (logLevel <= LogLevel.INFO) {
            log?.i(TAG, msg)
        }
    }

    fun d(TAG: String, msg: String) {
        if (logLevel <= LogLevel.DEBUG) {
            log?.d(TAG, msg)
        }
    }

    fun w(TAG: String, msg: String) {
        if (logLevel <= LogLevel.WARN) {
            log?.w(TAG, msg)
        }
    }

    fun e(TAG: String, msg: String) {
        if (logLevel <= LogLevel.ERROR) {
            log?.e(TAG, msg)
        }
    }

}
