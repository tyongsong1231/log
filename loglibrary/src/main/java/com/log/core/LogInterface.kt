package com.log.core

/**
 * ============================================================================
 *
 * Author: tys
 * Date: 2020-10-05 08:01
 * Mail: tyongsong66@gmail.com
 * Description: TODO
 *
 * ============================================================================
 */
interface LogInterface {
    fun v(TAG: String, msg: String)

    fun i(TAG: String, msg: String)

    fun d(TAG: String, msg: String)

    fun w(TAG: String, msg: String)

    fun e(TAG: String, msg: String)
}