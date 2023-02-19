package com.tossdesu.recipestest.di

import com.tossdesu.recipestest.presentation.MainActivity
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ViewModelModule::class,
        DataModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)
}