package com.example.githubapp.di.module

import com.example.calculator.ui.FragmentsBindingModule
import com.example.calculator.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
 abstract class MainActivityBindingModule {

    @ContributesAndroidInjector(modules = [FragmentsBindingModule::class])
   internal abstract fun bindMainActivity(): MainActivity?
}