package mapp.demo.com.mapp.screens;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import mapp.demo.com.mapp.LoginActivity;
import mapp.demo.com.mapp.api.NetworkApis;
import mapp.demo.com.mapp.screens.mvp.LoginModel;
import mapp.demo.com.mapp.screens.mvp.LoginPresenter;
import mapp.demo.com.mapp.screens.mvp.LoginView;
import mapp.demo.com.mapp.utils.NetworkUtility;

/**
 * Created by hdfc on 07-01-2018.
 */

@Module
public class ActivityModule {

    Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

    @ActivityScope
    @Provides
    Context provideContext() {
        return context;
    }

    @ActivityScope
    @Provides
    LoginModel provideLoginModel(NetworkApis apis, NetworkUtility utility) {
        return new LoginModel(apis, utility, context);
    }

    @ActivityScope
    @Provides
    LoginPresenter provideLoginPresenter(LoginModel model) {
        return new LoginPresenter(model, context);
    }

    @ActivityScope
    @Provides
    NetworkUtility provideNetworkUtility() {
        return new NetworkUtility();
    }

}
