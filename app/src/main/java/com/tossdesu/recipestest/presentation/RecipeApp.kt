package com.tossdesu.recipestest.presentation

import android.app.Application
import com.tossdesu.recipestest.di.ApplicationComponent
import com.tossdesu.recipestest.di.DaggerApplicationComponent

class RecipeApp : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}