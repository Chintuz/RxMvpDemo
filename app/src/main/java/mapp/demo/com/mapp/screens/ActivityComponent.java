package mapp.demo.com.mapp.screens;

import dagger.Component;
import mapp.demo.com.mapp.ExampleActivity;
import mapp.demo.com.mapp.LoginActivity;
import mapp.demo.com.mapp.di.ApplicationComponent;

/**
 * Created by hdfc on 07-01-2018.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(LoginActivity activity);

    void inject(ExampleActivity activity);
}
