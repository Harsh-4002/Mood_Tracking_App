package com.example.moodset

import android.app.Application

class Appmod:Application() {
    val db by lazy {

        mooddatabase.getInstance(this)
    }
}