package br.com.rphmelo.repofinder.di.components

import android.app.Application
import br.com.flanelinha.app.MyApp
import br.com.flanelinha.app.di.modules.ActivityModule
import br.com.rphmelo.repofinder.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    FragmentModule::class,
    ViewModelModule::class,
    RepositoryModule::class,
    AppModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: MyApp)
}