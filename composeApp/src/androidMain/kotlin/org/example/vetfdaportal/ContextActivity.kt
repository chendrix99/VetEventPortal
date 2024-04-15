package org.example.vetfdaportal

import android.content.Context
import androidx.startup.Initializer

lateinit var applicationContext: Context
    private set

public object AppContext

public class AppContextInitializer: Initializer<AppContext> {
    override fun create(context: Context): AppContext {
        applicationContext = context.applicationContext
        return AppContext
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}