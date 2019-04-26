package br.com.flanelinha.app.di.modules

import br.com.flanelinha.app.di.scopes.AppScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule {

    @Provides
    @AppScope
    fun provideAuthentication(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @AppScope
    fun provideInstanceId(): FirebaseInstanceId = FirebaseInstanceId.getInstance()
}