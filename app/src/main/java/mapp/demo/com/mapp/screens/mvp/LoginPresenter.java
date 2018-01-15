package mapp.demo.com.mapp.screens.mvp;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mapp.demo.com.mapp.model.data.Response;

/**
 * Created by hdfc on 11-01-2018.
 * <p>
 * logic will be implemented here
 */

public class LoginPresenter extends Presenter {

    LoginModel loginModel;
    LoginView loginView;
    Context context;
    Disposable disposable;

    @Inject
    public LoginPresenter(LoginModel model, Context context) {
        loginModel = model;
        this.context = context;
    }

    public void setLoginView(LoginView view) {
        loginView = view;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        disposable.dispose();
    }

    public void getDummyData() {

        disposable = loginModel.isInternetAvailable()
                .map(aBoolean -> {
                    if (!aBoolean)
                        Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_LONG).show();
                    return aBoolean;
                }).subscribeOn(Schedulers.io())
                .filter(aBoolean -> true)
                .flatMap(aBoolean -> loginModel.getData())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response response) throws Exception {
                        loginView.updateUI(true, response, null);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        loginView.updateUI(false, null, throwable);
                    }
                });
    }

}
