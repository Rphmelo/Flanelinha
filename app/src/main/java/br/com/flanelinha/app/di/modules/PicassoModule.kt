package br.com.flanelina.app.di.modules

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import okhttp3.OkHttpClient

@Module(includes = [OkHttpClientModule::class, ContextModule::class])
class PicassoModule {

    @Provides
    @Singleton
    fun providePicasso(okHttp3Downloader: OkHttp3Downloader, context: Context): Picasso {
        return Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build()
    }

    @Provides
    @Singleton
    fun okHttp3Downloader(okHttpClientBuilder: OkHttpClient.Builder): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClientBuilder.build())
    }

}