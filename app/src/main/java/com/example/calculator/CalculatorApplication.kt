package com.example.calculator

import com.example.calculator.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CalculatorApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val applicationComponent = DaggerApplicationComponent.builder().application(this)!!.build()
        applicationComponent.inject(this)
        return applicationComponent
    }
}