package mapp.demo.com.mapp.di.networkDi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import mapp.demo.com.mapp.api.NetworkApis;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hdfc on 06-01-2018.
 */

@Module
public class NetworkModule {

    @Provides
    NetworkApis provideNetworkApis(OkHttpClient client, @Named("baseUrl") String baseUrl, RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                                   GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder().addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(baseUrl)
//                .client(client)
                .build().create(NetworkApis.class);
    }
}
