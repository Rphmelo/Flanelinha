package br.com.flanelinha.app.di.modules

import br.com.flanelinha.app.login.ui.activities.LoginActivity
import br.com.flanelina.app.di.modules.FragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributeLoginActivity(): LoginActivity
}