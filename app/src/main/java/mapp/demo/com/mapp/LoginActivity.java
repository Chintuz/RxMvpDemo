package mapp.demo.com.mapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.job.JobInfo;
import android.content.ComponentName;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mapp.demo.com.mapp.api.NetworkApis;
import mapp.demo.com.mapp.application.MyApp;
import mapp.demo.com.mapp.model.LoginResponse;
import mapp.demo.com.mapp.model.data.Item;
import mapp.demo.com.mapp.model.data.Response;
import mapp.demo.com.mapp.screens.ActivityModule;
import mapp.demo.com.mapp.screens.DaggerActivityComponent;
import mapp.demo.com.mapp.utils.NetworkUtility;

/**
 * Created by hdfc on 26-12-2017.
 */

public class LoginActivity extends Activity {

    @BindView(R.id.userName)
    EditText uNameEt;

    @BindView(R.id.password)
    EditText pWordEt;

    @BindView(R.id.login_response)
    TextView loginResponseTv;
    @Inject NetworkApis apis;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        ButterKnife.bind(this);
        DaggerActivityComponent.builder().applicationComponent(MyApp.getApplicationComponent()).activityModule(new ActivityModule(this)).build().inject(this);
        Log.e("Susu", "Dhdh");
    }

    @OnClick(R.id.login)
    public void onClickLoginButton() {
        // network check utility
        showProgressDialog("Please wait..");
        NetworkUtility.networkChekObservable(this)
                .map(aBoolean -> {
                    if (!aBoolean)
                        Toast.makeText(LoginActivity.this, "No Internet connection!", Toast.LENGTH_SHORT).show();
                    return aBoolean;
                }).filter(aBoolean -> aBoolean)
                .subscribeOn(Schedulers.io())
                .flatMap(aBoolean -> validateInput().doOnNext(aBoolean1 -> {
                    if (aBoolean1)
                        callDumyData();
                }))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (!aBoolean)
                            
                        Toast.makeText(LoginActivity.this, "Validate result:" + aBoolean, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        
                        Toast.makeText(LoginActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void callDumyData() {
        apis.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
//                        
                        Observable.fromIterable(response.getItems())
                                .subscribe(new Observer<Item>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Item item) {
                                        loginResponseTv.setText(loginResponseTv.getText().toString() + "\n" + item.getOwner().getDisplayName());
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {
                        
                        loginResponseTv.setText("Error: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //    @OnClick(R.id.login)
    public void callLogin() {
        apis.doLogin(getLoginRequest())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        
                        loginResponseTv.setText("Response: " + loginResponse.getMessage());
//                        Toast.makeText(LoginActivity.this, "Login Response: " + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        
                        loginResponseTv.setText("Error: " + e.getMessage());
//                        Toast.makeText(LoginActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Observable<Boolean> validateInput() {

        // get all data and call login
        return Flowable.zip(getEditFieldFloawable(uNameEt), getEditFieldFloawable(pWordEt), (s, s2) -> {
            if (s.isEmpty() || s2.isEmpty()) {
//                Thread.sleep(2000);
                return false;
            } else {
                return true;
            }
        }).toObservable();
    }

    private JSONObject getLoginRequest() {
        JSONObject loginJsonObject = new JSONObject();
        try {
            loginJsonObject = new JSONObject();
            loginJsonObject.put("userid", "12306");
            loginJsonObject.put("os", "Android");
            loginJsonObject.put("device_id", "a0441580c8h8968l");
            loginJsonObject.put("source", "instaverify2");
            loginJsonObject.put("password", "Hdfc@123");
            loginJsonObject.put("build_version_code", "4.4");
            loginJsonObject.put("version", "5.0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginJsonObject;
    }

    private void showProgressDialog(String msg) {
        mProgressDialog = new ProgressDialog(new ContextThemeWrapper(LoginActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog));
        mProgressDialog.setTitle("");
        mProgressDialog.setMessage(msg);
        mProgressDialog.setCancelable(false);
        mProgressDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        mProgressDialog.show();
    }

    private void dissmissProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private Flowable<String> getEditFieldFloawable(EditText text) {
        return Flowable.just(text.getText().toString().trim());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void scheduleJob(View v) {
        JobInfo.Builder builder = new JobInfo.Builder(3 , new ComponentName("" , ""));

        builder.setMinimumLatency(2343);

    }
}
