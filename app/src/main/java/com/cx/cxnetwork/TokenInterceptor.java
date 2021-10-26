package com.cx.cxnetwork;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cx.bean.RequestTokenBody;
import com.cx.bean.TokenResponse;
import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ly-chenxiao on 25/10/2021
 * Email: chenxiao@szlanyou.com
 * Description: 添加 Token的拦截器
 *
 * @author: chenxiao
 */
public class TokenInterceptor implements Interceptor {
    private static final String TAG = "cxxx";
    public static final String MMKV_TOKEN_KEY = "token";
    public static final String MMKV_TOKEN_REFRESH_KEY = "refresh_token";
    public static final String BASE_URL = "https://o2sgw-bdperf.pateo.com.cn";
    public static final String AUTHORIZATION_HEADER = "Bearer ";

    private MMKV mmkv;

    @Override
    public Response intercept(Chain chain) throws IOException {
        mmkv = MMKV.mmkvWithID("id", MMKV.MULTI_PROCESS_MODE);
        String token = mmkv.decodeString(MMKV_TOKEN_KEY);
        String refreshToken = mmkv.decodeString(MMKV_TOKEN_REFRESH_KEY);
        //判断缓存内是否有 token，若有就直接作为 token 进行请求，若没有则请求 token（不是刷新）
        if (token == null) {
            token = getToken();
        }

        Request originalRequest = chain.request();
        //在原始的请求头添加token
        Request authorizationRequest = originalRequest
                .newBuilder()
                .addHeader("Authorization", AUTHORIZATION_HEADER + token)
                .method(originalRequest.method(), originalRequest.body()).build();
        Response newResponse = chain.proceed(authorizationRequest);

        ResponseBody responseBody = newResponse.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.getBuffer();
        Charset UTF8 = Charset.forName("UTF-8");
        String jsonResponse = buffer.clone().readString(UTF8);

        TokenResponse tokenResponse = new Gson().fromJson(jsonResponse, TokenResponse.class);
        if (tokenResponse != null) {
            //处理Token过期的情况
            if (tokenResponse.getStatusCode().equals("1001")) {
                Log.i(TAG, "intercept: " + "过期");
                //请求新的token 然后进行请求
                String newToken = getNewToken(refreshToken);
                Log.i(TAG, "newToken: " + newToken);
                //使用新的Token，创建新的请求
                Request newRequest = chain.request()
                        .newBuilder()
                        .header("Authorization", newToken)
                        .build();
                newResponse.close();
                //重新请求
                return chain.proceed(newRequest);
            }
        }
        return newResponse;
    }

    /**
     * Description: token 过期的情况下刷新 token.
     *
     * @param refreshToken 之前有效的 refresh token
     * @return java.lang.String
     * @author ly-chenxiao
     * @date 26/10/2021 9:30 AM
     */
    private synchronized String getNewToken(String refreshToken) {
        String result = "";
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        try {
            Call<TokenResponse> task = retrofitInterface.getNewToken(refreshToken, "refresh_token");
            TokenResponse response = task.execute().body();
            result = getAndRestoreToken(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Description: 获取Token.
     *
     * @return java.lang.String
     * @author ly-chenxiao
     * @date 26/10/2021 9:31 AM
     */
    private synchronized String getToken() {
        String result = "";
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<TokenResponse> task = retrofitInterface.getToken("lth53", "client_credentials", "71917b2946b34d8fa52916134132b3d9");

        try {
            TokenResponse response = task.execute().body();
            result = getAndRestoreToken(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getAndRestoreToken(@NonNull TokenResponse response) {
        String result = "";
        result = response.getData().getAccess_token();
        String refreshToken = response.getData().getRefresh_token();
        //缓存token
        mmkv.encode(MMKV_TOKEN_KEY, result);
        mmkv.encode(MMKV_TOKEN_REFRESH_KEY, refreshToken);
        Log.i(TAG, "token: " + result);
        Log.i(TAG, "refreshToken: " + refreshToken);
        return result;
    }
}
