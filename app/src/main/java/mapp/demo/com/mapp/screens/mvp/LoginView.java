package mapp.demo.com.mapp.screens.mvp;

import mapp.demo.com.mapp.model.data.Response;

/**
 * Created by hdfc on 11-01-2018.
 */

public interface LoginView {

    void updateUI(boolean status, Response response, Throwable throwable);

}
