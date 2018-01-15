package mapp.demo.com.mapp.api;

import org.json.JSONObject;

import io.reactivex.Observable;
import mapp.demo.com.mapp.model.LoginResponse;
import mapp.demo.com.mapp.model.data.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by hdfc on 26-12-2017.
 */

public interface NetworkApis {

    @POST("HDFC-API-1.0/v1/users/login")
    Observable<LoginResponse> doLogin(@Body JSONObject object);

    @GET("answers?order=desc&sort=activity&site=stackoverflow&tagged=android")
    Observable<Response> getData();
}
