package mapp.demo.com.mapp.screens.mvp;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observable;
import mapp.demo.com.mapp.api.NetworkApis;
import mapp.demo.com.mapp.model.data.Response;
import mapp.demo.com.mapp.utils.NetworkUtility;

/**
 * Created by hdfc on 11-01-2018.
 *
 * Model class is created to provide small data to ui
 * provide all dependency via constructor
 */

public class LoginModel {

    private NetworkApis apis;
    private NetworkUtility utility;
    private Context context;

    @Inject
    public LoginModel(NetworkApis networkApis, NetworkUtility networkUtility, Context context) {
        apis = networkApis;
        utility = networkUtility;
        this.context = context;
    }

    Observable<Response> getData() {
        return apis.getData();
    }

    Observable<Boolean> isInternetAvailable() {
        return Observable.just(utility.isnetworkAvailable(context));
    }
}
