package com.cx.cxnetwork;

import static com.cx.cxnetwork.TokenInterceptor.BASE_URL;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cx.bean.RequestTokenBody;
import com.cx.bean.TokenResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;

/**
 * Created by ly-chenxiao on 25/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author: chenxiao
 */
public class TokenFragment extends Fragment {
    private static final String TAG = "cxxx";

    public TokenFragment() {
        super(R.layout.fragment_token);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button getTokenBtn = getActivity().findViewById(R.id.getTokenBtn);
        Button getWeatherBtn = getActivity().findViewById(R.id.getWeatherBtn);
        Button getRefreshTokenBtn = getActivity().findViewById(R.id.getRefreshTokenBtn);


        getRefreshTokenBtn.setOnClickListener(v -> {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();
                            Request request = originalRequest
                                    .newBuilder()
                                    .addHeader("Content-Type", "application/x-www.form-urlencoded")
                                    .method(originalRequest.method(), originalRequest.body()).build();
                            return chain.proceed(request);
                        }
                    })
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .baseUrl("https://o2sgw-bdperf.pateo.com.cn")
                    .build();
            RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
            Call<TokenResponse> task = retrofitInterface.getNewToken("3bac7812d4f64cf8a862570948e11583","refresh_token");
            task.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    TokenResponse response1 = response.body();
                    String result = response1.getData().getAccess_token();
                    String refreshToken = response1.getData().getRefresh_token();
                    Log.i(TAG, "getToken: " + result);
                    Log.i(TAG, "getToken: " + refreshToken);
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {
                    Log.i(TAG, "getToken: " + "失败");
                }
            });


        });


        getTokenBtn.setOnClickListener(v -> {

            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://o2sgw-bdperf.pateo.com.cn")
                    .build();

            RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
            Call<TokenResponse> task = retrofitInterface.getToken("lth53", "client_credentials", "71917b2946b34d8fa52916134132b3d9");

            task.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        TokenResponse body = response.body();
                        Log.i(TAG, "onResponse: " + body.toString());
                    }
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {
                    Log.i(TAG, "失败: ");
                }
            });
        });

        getWeatherBtn.setOnClickListener(v -> {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(1000, TimeUnit.SECONDS)
                    .addInterceptor(new TokenInterceptor())
                    .connectTimeout(1000, TimeUnit.SECONDS)
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
            Call<WeatherResponse> task = retrofitInterface.getWeather("116.405285", "39.904989");
            task.enqueue(new Callback<WeatherResponse>() {
                @Override
                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                    WeatherResponse body = response.body();
                    String statusCode = body.getStatusCode();
                    Log.i(TAG, "onResponse: " + body.getStatusMessage());
                }

                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    Log.i(TAG, "失败: ");
                }
            });
        });


    }

}
