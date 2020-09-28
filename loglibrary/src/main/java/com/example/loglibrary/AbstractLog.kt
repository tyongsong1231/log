package com.example.loglibrary


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

    private var logLevel = VERBOSE

    private var log: LogInterface? = null

    companion object {
        const val VERBOSE = 2
        const val DEBUG = 3
        const val INFO = 4
        const val WARN = 5
        const val ERROR = 6
    }

    fun setLogLevel(logLevel: Int) {
        this.logLevel = logLevel
    }

    fun setLogger(logger: LogInterface) {
        log = logger
    }

    fun v(TAG: String, msg: String) {
        if (logLevel <= VERBOSE) {
            log?.v(TAG, msg)
        }
    }

    fun i(TAG: String, msg: String) {
        if (logLevel <= INFO) {
            log?.i(TAG, msg)
        }
    }

    fun d(TAG: String, msg: String) {
        if (logLevel <= DEBUG) {
            log?.d(TAG, msg)
        }
    }

    fun w(TAG: String, msg: String) {
        if (logLevel <= WARN) {
            log?.w(TAG, msg)
        }
    }

    fun e(TAG: String, msg: String) {
        if (logLevel <= ERROR) {
            log?.e(TAG, msg)
        }
    }

}

interface LogInterface {

    fun v(TAG: String, msg: String)

    fun i(TAG: String, msg: String)

    fun d(TAG: String, msg: String)

    fun w(TAG: String, msg: String)

    fun e(TAG: String, msg: String)
}