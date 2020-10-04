package com.purplepotato.gmccare.pref

import android.content.Context
import androidx.core.content.edit

class Preferences(context: Context) {
    companion object {
        private const val PREFS_NAME = "pref"
        private const val QUEUE_NUMBER = "queue_number"
        private const val IS_QUEUED = "is_queued"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUserQueueNumber(queueNumber: Int) {
        preferences.edit {
            putInt(QUEUE_NUMBER, queueNumber)
        }
    }

    fun getUserQueueNumber(): Int = preferences.getInt(QUEUE_NUMBER, -1)

    fun setIsQueued(status: Boolean) {
        preferences.edit {
            putBoolean(IS_QUEUED, status)
        }
    }

    fun getIsQueued(): Boolean = preferences.getBoolean(IS_QUEUED, false)

}