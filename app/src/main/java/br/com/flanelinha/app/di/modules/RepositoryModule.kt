package br.com.flanelina.app.di.modules

import dagger.Module

@Module(includes = [
    AppModule::class,
    NetModule::class
])

class RepositoryModule {
}