package com.example.calculator.di.module

import com.example.calculator.ui.main.FragmentsBindingModule
import com.example.calculator.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
 abstract class MainActivityBindingModule {

    @ContributesAndroidInjector(modules = [FragmentsBindingModule::class])
   internal abstract fun bindMainActivity(): MainActivity?
}