package mapp.demo.com.mapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mapp.demo.com.mapp.application.MyApp;
import mapp.demo.com.mapp.model.data.Response;
import mapp.demo.com.mapp.screens.ActivityModule;
import mapp.demo.com.mapp.screens.DaggerActivityComponent;
import mapp.demo.com.mapp.screens.mvp.LoginPresenter;
import mapp.demo.com.mapp.screens.mvp.LoginView;

/**
 * Created by hdfc on 11-01-2018.
 */

public class ExampleActivity extends Activity implements LoginView {

//    @Inject
//    LoginView view;

    @Inject
    LoginPresenter loginPresenter;

    private ProgressDialog mProgressDialog;

    @BindView(R.id.userName)
    EditText uNameEt;

    @BindView(R.id.password)
    EditText pWordEt;

    @BindView(R.id.login_response)
    TextView loginResponseTv;

    @BindView(R.id.login)
    Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerActivityComponent.builder().applicationComponent(MyApp.getApplicationComponent()).activityModule(new ActivityModule(this)).build().inject(this);
        setContentView(R.layout.layout_login);
        ButterKnife.bind(this);
        loginPresenter.setLoginView(this);
    }

    @OnClick(R.id.login)
    public void onButtonClicked() {
        loginPresenter.getDummyData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
    }

    @Override
    public void updateUI(boolean status, Response response, Throwable throwable) {
        if (status) {
            Toast.makeText(this, "Data: " + response.getItems().get(0).getOwner().getDisplayName(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
