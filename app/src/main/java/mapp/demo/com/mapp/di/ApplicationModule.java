package mapp.demo.com.mapp.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hdfc on 28-12-2017.
 * <p>
 * this class returns Application context with provideAppContext method
 * for fetching app context we required context which is available from context
 * received from constructor of this class
 * <p>
 * this context is returns by this method and used by dagger to provide app context in all
 * dependencies
 */

@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    Context provideAppContext() {
        return context;
    }
}
