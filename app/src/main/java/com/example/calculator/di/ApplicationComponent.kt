package com.example.calculator.di

import android.app.Application
import com.example.calculator.CalculatorApplication
import com.example.calculator.di.module.NetworkModule
import com.example.githubapp.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/*
    Main Application component class to inject different modules
 */
@Singleton
@Component(modules = [ContextModule::class, AndroidSupportInjectionModule::class,  MainActivityBindingModule::class,
    ViewModelFactoryModule::class, NetworkModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication>{

    fun inject(calculatorApplication: CalculatorApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?

        fun build(): ApplicationComponent
    }
}