package mapp.demo.com.mapp.di.networkDi;

import android.content.Context;

import java.io.File;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hdfc on 06-01-2018.
 */

@Module
public class NetworkComponentModule {

    @Provides
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Named("baseUrl")
    @Provides
    String provideBaseUrl() {
        return "https://api.stackexchange.com/2.2/";
    }

    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor logger, Cache cache) {
        return new OkHttpClient().newBuilder()
                .addInterceptor(logger)
                .cache(cache)
                .build();
    }

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    Cache provideCache(File file) {
        return new Cache(file, 10 * 10 * 1000);
    }

    @Provides
    File provideCacheFile(Context context){
        return context.getFilesDir();
    }
}
