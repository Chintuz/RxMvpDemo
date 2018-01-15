package mapp.demo.com.mapp.application;

import android.app.Application;

import mapp.demo.com.mapp.di.ApplicationComponent;
import mapp.demo.com.mapp.di.ApplicationModule;
import mapp.demo.com.mapp.di.DaggerApplicationComponent;


/**
 * Created by hdfc on 28-12-2017.
 */

public class MyApp extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
