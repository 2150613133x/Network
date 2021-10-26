package com.cx.cxnetwork;

import static com.cx.Constants.BASE_URL;

import static java.net.HttpURLConnection.HTTP_OK;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.cx.bean.CommentItem;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ly-chenxiao on 15/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author: chenxiao
 */
public class OkHttpFragment extends androidx.fragment.app.Fragment {
    private View getBtn, okHttpGetWithParamsBtn, okHttpPostBtn, okHttpPostFileBtn, okHttpPostFilesBtn;
    private static final String TAG = "OkHttpFragment";


    public OkHttpFragment() {
        super(R.layout.fragment_ok_http);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getBtn = getActivity().findViewById(R.id.okHttpGetBtn);
        okHttpGetWithParamsBtn = getActivity().findViewById(R.id.okHttpGetWithParamsBtn);
        okHttpPostBtn = getActivity().findViewById(R.id.okHttpPostBtn);
        okHttpPostFileBtn = getActivity().findViewById(R.id.okHttpPostFileBtn);
        okHttpPostFilesBtn = getActivity().findViewById(R.id.okHttpPostFilesBtn);

        getBtn.setOnClickListener(v -> {
            okHttpGet();
        });
        okHttpGetWithParamsBtn.setOnClickListener(v -> {
            getWithParams("这是我的关键词", 10, 0);
        });
        okHttpPostBtn.setOnClickListener(v -> {
            okHttpPost();
        });
        okHttpPostFileBtn.setOnClickListener(v -> {
            okHttpPostFile();
        });
        okHttpPostFilesBtn.setOnClickListener(v -> {
            okHttpPostFiles();
        });

        //申请权限
    }

    /**
     * Description:上传单文件.
     *
     * @author ly-chenxiao
     * @date 16/10/2021 2:48 PM
     */
    private void okHttpPostFile() {
        File file = new File("/sdcard/Pictures/Screenshots/S10303-20414135.png");
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("image/png");
        RequestBody fileBody = RequestBody.create(mediaType, file);
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("file", file.getName(), fileBody)
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + "/file/upload")
                .post(requestBody)
                .build();
        Call task = client.newCall(request);
        task.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: ");
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == HTTP_OK) {
                    Log.i(TAG, "body -- >" + response.body().string());
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }

    /**
     * Description:上传多文件.
     *
     * @author ly-chenxiao
     * @date 16/10/2021 2:48 PM
     */
    private void okHttpPostFiles() {
        File file1 = new File("/sdcard/Pictures/Screenshots/S10303-20414135.png");
        File file2 = new File("/sdcard/Pictures/Screenshots/S10301-20021606.png");
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("image/png");
        RequestBody fileBody1 = RequestBody.create(mediaType, file1);
        RequestBody fileBody2 = RequestBody.create(mediaType, file2);
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("files", file1.getName(), fileBody1)
                .addFormDataPart("files", file2.getName(), fileBody2)
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + "/files/upload")
                .post(requestBody)
                .build();
        Call task = client.newCall(request);
        task.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: ");
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == HTTP_OK) {
                    Log.i(TAG, "body -- >" + response.body().string());
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }

    /**
     * Description:okHttp post 请求.
     *
     * @author ly-chenxiao
     * @date 16/10/2021 2:15 PM
     */
    private void okHttpPost() {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        CommentItem commentItem = new CommentItem("12345", "评论的内容");
        Gson gson = new Gson();
        String commentItemJson = gson.toJson(commentItem);
        RequestBody requestBody = RequestBody.create(mediaType, commentItemJson);
        Request request = new Request.Builder()
                .url(BASE_URL + "/post/comment")
                .post(requestBody)
                .build();
        Call task = client.newCall(request);
        task.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: ");
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == HTTP_OK) {
                    Log.i(TAG, "body -- >" + response.body().string());
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }


    /**
     * okHttp get请求  同步请求使用task.execute()，需要自己处理线程问题，异步请求使用task.enqueue();
     */
    private void okHttpGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(BASE_URL + "/get/text")
                            .get()
                            .build();
                    Call task = client.newCall(request);
                    Response response = task.execute();
                    int code = response.code();
                    if (code == HTTP_OK) {
                        ResponseBody body = response.body();
                        //是 body.string() 方法而不是body.toString()
                        Log.i(TAG, "body ---->" + body.string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Http url 带参数请求
     */
    private void getWithParams(String keyword, int page, int order) {
        StringBuilder sb = new StringBuilder(BASE_URL + "/get/param");
        sb.append("?");
        sb.append("keyword=" + keyword + "&");
        sb.append("page=" + page + "&");
        sb.append("order=" + order);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(sb.toString())
                .get()
                .build();
        Call task = client.newCall(request);
        task.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == HTTP_OK) {
                    Log.i(TAG, "body -- >" + response.body().string());
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }

    private void httpPost() {

    }


    /**
     * Description:上传单文件.
     *
     * @author ly-chenxiao
     * @date 16/10/2021 12:53 PM
     */
    private void postFile() {

    }


    /**
     * Description:上传文件.
     *
     * @author ly-chenxiao
     * @date 16/10/2021 12:53 PM
     */
    private void postFiles() {

    }

    private void uploadFile(File file,
                            String fileKey,
                            String fileName,
                            String fileType,
                            String BOUNDARY,
                            OutputStream outputStream,
                            boolean isLast) throws IOException {
        //准备数据
        StringBuilder headerSbInfo = new StringBuilder();
        headerSbInfo.append("--");
        headerSbInfo.append(BOUNDARY);
        headerSbInfo.append("\r\n");
        headerSbInfo.append("Content-Disposition: form-data; name=\"" + fileKey + "\"; filename=\"" + fileName + "\"");
        headerSbInfo.append("\r\n");
        headerSbInfo.append("Content-Type: " + fileType);
        headerSbInfo.append("\r\n");
        headerSbInfo.append("\r\n");
        byte[] headerInfoBytes = headerSbInfo.toString().getBytes("UTF-8");
        outputStream.write(headerInfoBytes);
        //文件内容
        FileInputStream fos = new FileInputStream(file);
        BufferedInputStream bfi = new BufferedInputStream(fos);
        byte[] buffer = new byte[1024 * 1024];
        int len;
        while ((len = bfi.read(buffer, 0, buffer.length)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        //写尾部信息
        StringBuilder footerSbInfo = new StringBuilder();
        footerSbInfo.append("\r\n");
        footerSbInfo.append("--");
        footerSbInfo.append(BOUNDARY);
        if (isLast) {
            footerSbInfo.append("--");
            footerSbInfo.append("\r\n");
        }
        footerSbInfo.append("\r\n");
        outputStream.write(footerSbInfo.toString().getBytes("UTF-8"));
    }
}
