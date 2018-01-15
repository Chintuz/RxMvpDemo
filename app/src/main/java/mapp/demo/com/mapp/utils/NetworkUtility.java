package mapp.demo.com.mapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by hdfc on 26-12-2017.
 */

public class NetworkUtility {

    @Inject
    public NetworkUtility(){

    }

    public static Observable<Boolean> networkChekObservable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return Observable.just(cm.getActiveNetworkInfo() != null);
    }


    public boolean isnetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}
