package com.thies.hw6_exercise1

import android.app.Application
import com.thies.hw6_exercise1.CrimeRepository

class CriminalIntentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}