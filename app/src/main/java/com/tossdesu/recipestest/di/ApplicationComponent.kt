package com.tossdesu.recipestest.di

import com.tossdesu.recipestest.presentation.MainActivity
import dagger.Component

@Component
interface ApplicationComponent {

    fun inject(activity: MainActivity)
}