package com.cx.cxnetwork;

import com.cx.bean.CommentItem;
import com.cx.bean.FileWithParamResult;
import com.cx.bean.GetResult;
import com.cx.bean.JsonResult;
import com.cx.bean.PostResult;
import com.cx.bean.RequestTokenBody;
import com.cx.bean.TokenResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by ly-chenxiao on 18/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author ly-chenxiao
 */
public interface RetrofitInterface {
    /**
     * Description:Retrofit get请求接口.
     *
     * @return retrofit2.Call<retrofit2.Response>
     * @author ly-chenxiao
     * @date 18/10/2021 8:53 AM
     */
    @GET("/get/text")
    Call<JsonResult> getJson();

    /**
     * Description: 带参数的 get 请求.
     *
     * @return retrofit2.Call<com.cx.bean.JsonResult>
     * @author ly-chenxiao
     * @date 18/10/2021 9:33 AM
     */
    @GET("/get/param")
    Call<GetResult> getJsonWithParams(@Query("keyword") String keyword, @Query("page") int page, @Query("order") int order);

    /**
     * Description: Body 注解 Post 请求.
     *
     * @param commentItem 提交的评论类 Body
     * @return retrofit2.Call<com.cx.bean.PostResult>
     * @author ly-chenxiao
     * @date 18/10/2021 9:11 AM
     */
    @POST("/post/comment")
    Call<PostResult> postComment(@Body CommentItem commentItem);

    /**
     * Description:Url 带参数的 Post 请求.
     *
     * @param text
     * @return retrofit2.Call<com.cx.bean.PostResult>
     * @author ly-chenxiao
     * @date 18/10/2021 9:22 AM
     */
    @POST("/post/string")
    Call<PostResult> postCommentWithUrlParams(@Query("string") String text);

    /**
     * Description:上传单文件.
     *
     * @param file
     * @return retrofit2.Call<com.cx.bean.JsonResult>
     * @author ly-chenxiao
     * @date 18/10/2021 9:54 AM
     */
    @Multipart
    @POST("/file/upload")
    Call<JsonResult> postFile(@Part MultipartBody.Part file);

    /**
     * Description:上传文件并携带参数.
     *
     * @param params
     * @param file
     * @return retrofit2.Call<com.cx.bean.JsonResult>
     * @author ly-chenxiao
     * @date 18/10/2021 9:55 AM
     */
    @Multipart
    @POST("/file/params/upload")
    Call<FileWithParamResult> postFileWithParams(@PartMap Map<String, Object> params, @Part MultipartBody.Part file);


    @POST("/client/oauth/token")
    Call<TokenResponse> getToken(@Query("client_id") String clientId, @Query("grant_type") String grantType, @Query("client_secret") String clientSecret);

    @FormUrlEncoded
    @POST("/client/oauth/token")
    Call<TokenResponse> getNewToken(@Header("Authorization") String refreshToken, @Field("grant_type") String grant_type);

    @GET("/cp/weather/weather-live-info")
    Call<WeatherResponse> getWeather(@Query("longitude") String longitude, @Query("latitude") String latitude);
}
