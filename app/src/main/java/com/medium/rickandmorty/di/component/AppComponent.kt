package com.medium.rickandmorty.di.component

import com.medium.rickandmorty.di.module.RetrofitModule
import com.medium.rickandmorty.ui.main.MainActivity
import dagger.Component

@Component(modules = [RetrofitModule::class])
interface AppComponent {
    fun inject(activity : MainActivity)
}