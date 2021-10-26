package com.cx.cxnetwork;

import static com.cx.Constants.BASE_URL;

import static java.net.HttpURLConnection.HTTP_OK;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.cx.bean.CommentItem;
import com.cx.bean.FileWithParamResult;
import com.cx.bean.GetResult;
import com.cx.bean.JsonResult;
import com.cx.bean.PostResult;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ly-chenxiao on 18/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author: chenxiao
 */
public class RetrofitFragment extends Fragment {
    private static final String TAG = "cxxxx";

    public RetrofitFragment() {
        super(R.layout.fragment_retrofit);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();
        View retrofitGetBtn = activity.findViewById(R.id.retrofitGetBtn);
        View retrofitPostBtn = activity.findViewById(R.id.retrofitPostBtn);
        View retrofitPostWithUrlParamBtn = activity.findViewById(R.id.retrofitPostWithUrlParamBtn);
        View retrofitGetParamsBtn = activity.findViewById(R.id.retrofitGetParamsBtn);
        View retrofitPostFileBtn = activity.findViewById(R.id.retrofitPostFileBtn);
        View retrofitPostFileParamsBtn = activity.findViewById(R.id.retrofitPostFileParamsBtn);
        retrofitGetBtn.setOnClickListener(v -> {
            retrofitGet();
        });
        retrofitPostBtn.setOnClickListener(v -> {
            retrofitPost();
        });

        retrofitPostWithUrlParamBtn.setOnClickListener(v -> {
            retrofitPostWithUrlParam();
        });
        retrofitGetParamsBtn.setOnClickListener(v -> {
            retrofitGetWithUrlParam();
        });
        retrofitPostFileBtn.setOnClickListener(v -> {
            retrofitPostFile();
        });
        retrofitPostFileParamsBtn.setOnClickListener(v -> {
            retrofitPostFileParams();
        });

    }

    private void retrofitPostFileParams() {
        File file = new File("/sdcard/Pictures/Screenshots/S10303-20414135.png");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Map<String, Object> params = new HashMap<>(2);
        params.put("description", "我是文件的描述内容...");
        params.put("isFree", "false");
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        Call<FileWithParamResult> task = retrofitInterface.postFileWithParams(params, part);
        task.enqueue(new Callback<FileWithParamResult>() {
            @Override
            public void onResponse(Call<FileWithParamResult> call, Response<FileWithParamResult> response) {
                if (response.code() == HTTP_OK) {
                    Log.d(TAG, "response -- > " + response.body());
                    getActivity().runOnUiThread(() -> {
                        FileWithParamResult body = response.body();
                        Toast.makeText(getActivity(), "请求成功" + body.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    });
                }
            }

            @Override
            public void onFailure(Call<FileWithParamResult> call, Throwable t) {

            }
        });
    }

    private void retrofitPostFile() {
        File file = new File("/sdcard/Pictures/Screenshots/S10303-20414135.png");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        Call<JsonResult> task = retrofitInterface.postFile(part);
        task.enqueue(new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                if (response.code() == HTTP_OK) {
                    Log.d(TAG, "response -- > " + response.body());
                    getActivity().runOnUiThread(() -> {
                        JsonResult body = response.body();
                        Toast.makeText(getActivity(), "请求成功" + body.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    });
                }
            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable t) {

            }
        });

    }

    private void retrofitGetWithUrlParam() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<GetResult> task = retrofitInterface.getJsonWithParams("这是关键字", 10, 1);
        task.enqueue(new Callback<GetResult>() {
            @Override
            public void onResponse(Call<GetResult> call, Response<GetResult> response) {
                if (response.code() == HTTP_OK) {
                    Log.d(TAG, "response -- > " + response.body());
                    getActivity().runOnUiThread(() -> {
                        GetResult body = response.body();
                        Toast.makeText(getActivity(), "请求成功" + body.toString()
                                , Toast.LENGTH_SHORT).show();
                    });
                }
            }

            @Override
            public void onFailure(Call<GetResult> call, Throwable t) {

            }
        });
    }

    private void retrofitPostWithUrlParam() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<PostResult> task = retrofitInterface.postCommentWithUrlParams("评论评论评论");
        task.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                if (response.code() == HTTP_OK) {
                    Log.d(TAG, "response -- > " + response.body());
                    getActivity().runOnUiThread(() -> {
                        PostResult body = response.body();
                        Toast.makeText(getActivity(), "请求成功" + body.toString()
                                , Toast.LENGTH_SHORT).show();
                    });
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void retrofitPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<PostResult> task = retrofitInterface.postComment(new CommentItem("123123", "评论的内容"));
        task.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                if (response.code() == HTTP_OK) {
                    Log.d(TAG, "response -- > " + response.body());
                    getActivity().runOnUiThread(() -> {
                        PostResult body = response.body();
                        Toast.makeText(getActivity(), "请求成功" + body.toString()
                                , Toast.LENGTH_SHORT).show();
                    });
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                });
            }
        });

    }

    private void retrofitGet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<JsonResult> task = retrofitInterface.getJson();
        task.enqueue(new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                if (response.code() == HTTP_OK) {
                    Log.d(TAG, "response -- > " + response.body());
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();
                    });
                }
            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable t) {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

}
