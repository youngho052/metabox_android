package com.clone.metabox.init

import android.content.Context
import androidx.startup.Initializer
import com.clone.metabox.BuildConfig
import timber.log.Timber

class TimberInitializer:Initializer<Unit> {
    override fun create(context: Context) {
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
            Timber.d("initialize timber")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}