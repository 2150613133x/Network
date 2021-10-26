package com.cx.cxnetwork;

import static com.cx.Constants.BASE_URL;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.cx.bean.CommentItem;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ly-chenxiao on 15/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author: chenxiao
 */
public class HttpFragment extends androidx.fragment.app.Fragment {
    private View httpGetBtn;
    View httpGetWithParamBtn;
    View httpPostBtn, postFileBtn, postFilesBtn;
    private static final String TAG = "HttpFragment";

    public HttpFragment() {
        super(R.layout.fragment_http);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        httpGetBtn = getActivity().findViewById(R.id.httpGetBtn);
        httpGetWithParamBtn = getActivity().findViewById(R.id.httpGetWithParamBtn);
        httpPostBtn = getActivity().findViewById(R.id.httpPostBtn);
        postFileBtn = getActivity().findViewById(R.id.httpPostFileBtn);
        postFilesBtn = getActivity().findViewById(R.id.httpPostFilesBtn);
        setClickListener();
        //申请权限
    }

    private void setClickListener() {
        httpGetBtn.setOnClickListener(v -> {
            httpGet();
        });
        httpGetWithParamBtn.setOnClickListener(v -> {
            getWithParams("这是我的关键词", 10, 0);
        });
        httpPostBtn.setOnClickListener(v -> {
            httpPost();
        });
        postFileBtn.setOnClickListener(v -> {
            int i = getActivity().checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (i != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                postFile();
            }
        });
        postFilesBtn.setOnClickListener(v -> {
            int i = getActivity().checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (i != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                postFiles();
            }
        });


    }

    /**
     * Http get请求
     */
    private void httpGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(BASE_URL + "/get/text");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(10000);
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
                    connection.setRequestProperty("Accept", "*/*");
                    connection.connect();
                    int code = connection.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        Map<String, List<String>> headerFields = connection.getHeaderFields();
                        Set<Map.Entry<String, List<String>>> entries = headerFields.entrySet();
                        for (Map.Entry<String, List<String>> entry : entries) {
                            Log.i(TAG, entry.getKey() + " == " + entry.getValue());
                        }

                        Object content = connection.getContent();
                        Log.i(TAG, "content ---> " + content);
                        String responseMessage = connection.getResponseMessage();
                        Log.i(TAG, "message ---> " + responseMessage);

                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String result = bufferedReader.readLine();
                        Log.i(TAG, "result ---> " + result);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Http url 带参数请求
     */
    private void getWithParams(String keyword, int page, int order) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuilder sb = new StringBuilder(BASE_URL + "/get/param");
                    sb.append("?");
                    sb.append("keyword=" + keyword + "&");
                    sb.append("page=" + page + "&");
                    sb.append("order=" + order);
                    URL url = new URL(sb.toString());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(10000);
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
                    connection.setRequestProperty("Accept", "*/*");
                    connection.connect();
                    int code = connection.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String result = bufferedReader.readLine();
                        Log.i(TAG, "result ---> " + result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void httpPost() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(BASE_URL + "/post/comment");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(10000);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
                    connection.setRequestProperty("Accept", "application/json, text/plain, */*");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
                    //写数据到服务
                    OutputStream outputStream = connection.getOutputStream();
                    CommentItem commentItem = new CommentItem("111111", "真不错");
                    Gson gson = new Gson();
                    String commentJson = gson.toJson(commentItem);
                    outputStream.write(commentJson.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                    int code = connection.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String result = bufferedReader.readLine();
                        Log.i(TAG, "result ---> " + result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * Description:上传单文件.
     *
     * @author ly-chenxiao
     * @date 16/10/2021 12:53 PM
     */
    private void postFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OutputStream outputStream = null;
                FileInputStream fos = null;
                BufferedInputStream bfi = null;
                InputStream inputStream = null;
                try {
                    //准备数据,写入请求头
                    File file = new File("/sdcard/Pictures/Screenshots/S10303-20414135.png");

                    String fileKey = "file";
                    String fileType = "image/png";

                    String fileName = file.getName();
                    String BOUNDARY = "--------------------------439587032493061786301986";
                    URL url = new URL(BASE_URL + "/file/upload");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(10000);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("User-Agent", "Android/" + Build.VERSION.SDK_INT);
                    connection.setRequestProperty("Accept", "*/*");
                    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
                    connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
                    connection.setRequestProperty("Connection", "keep-alive");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);

                    connection.connect();
                    outputStream = connection.getOutputStream();
                    //写头部信息
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

                    //写文件信息
                    fos = new FileInputStream(file);
                    bfi = new BufferedInputStream(fos);
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
                    footerSbInfo.append("--");
                    footerSbInfo.append("\r\n");
                    footerSbInfo.append("\r\n");
                    outputStream.write(footerSbInfo.toString().getBytes("UTF-8"));

                    outputStream.flush();
                    int code = connection.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String result = bufferedReader.readLine();
                        Log.i(TAG, "result ---> " + result);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bfi != null) {
                        try {
                            bfi.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        }).start();
    }


    /**
     * Description:上传文件.
     *
     * @author ly-chenxiao
     * @date 16/10/2021 12:53 PM
     */
    private void postFiles() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OutputStream outputStream = null;
                FileInputStream fos = null;
                BufferedInputStream bfi = null;
                InputStream inputStream = null;
                try {
                    //准备数据,写入请求头
                    File file1 = new File("/sdcard/Pictures/Screenshots/S10303-20414135.png");
                    File file2 = new File("/sdcard/Pictures/Screenshots/S10301-20021606.png");

                    String fileKey = "files";
                    String fileType = "image/png";

                    String fileName1 = file1.getName();
                    String fileName2 = file2.getName();
                    String BOUNDARY = "--------------------------439587032493061786301986";
                    URL url = new URL(BASE_URL + "/files/upload");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(10000);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("User-Agent", "Android/" + Build.VERSION.SDK_INT);
                    connection.setRequestProperty("Accept", "*/*");
                    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
                    connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
                    connection.setRequestProperty("Connection", "keep-alive");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);

                    connection.connect();
                    outputStream = connection.getOutputStream();
                    //写输出流
                    uploadFile(file1, fileKey, fileName1, fileType, BOUNDARY, outputStream, false);
                    uploadFile(file2, fileKey, fileName2, fileType, BOUNDARY, outputStream, true);

                    outputStream.flush();
                    int code = connection.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String result = bufferedReader.readLine();
                        Log.i(TAG, "result ---> " + result);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bfi != null) {
                        try {
                            bfi.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        }).start();
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
