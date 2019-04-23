package br.com.flanelina.app.di.components

import br.com.flanelina.app.di.modules.*
import com.squareup.picasso.Picasso
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    PicassoModule::class
])
interface PicassoComponent {

    fun getPicasso(): Picasso
}