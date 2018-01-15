package mapp.demo.com.mapp.di;

import dagger.Component;
import mapp.demo.com.mapp.api.NetworkApis;
import mapp.demo.com.mapp.di.networkDi.NetworkComponentModule;
import mapp.demo.com.mapp.di.networkDi.NetworkModule;

/**
 * Created by hdfc on 28-12-2017.
 * <p>
 * this is an appcomponent all major dependencies are provided from here
 * <p>
 * like all interface level
 */

@AppScope
@Component(modules = {ApplicationModule.class, NetworkModule.class, NetworkComponentModule.class})
public interface ApplicationComponent {

    NetworkApis apis();
}
