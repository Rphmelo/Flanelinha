package br.com.rphmelo.repofinder.di.modules

import dagger.Module

@Module(includes = [
    AppModule::class,
    NetModule::class
])

class RepositoryModule {
}