package lsw.guichange.Controller;
import java.util.List;

import lsw.guichange.Item.Keyword;
import lsw.guichange.Item.Post;
import lsw.guichange.Item.exItem;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by KJH on 2017-06-06.
 */

public interface NetworkService {
    @POST("/ppompers/")
    Call<Post> post_post(@Body Post post);

    @GET("/ppompers/")
    Call<List<Post>> get_post();

    @GET("/pppompers/{post}/")
    Call<List<Post>> get_pk_post(@Path("post") int post);

    @GET("/{pk}/")
    Call<List<Post>> get_find_post(@Path("pk") String pk);

    @GET("/keyword/{token}/{keyword}/{bulletinName}")
    Call<Void> requestKeyword(@Path("token") String token, @Path("keyword") String keyword,@Path("bulletinName")String bulletinName);

    @GET("/Rkeyword/{token}/{keyword}/{bulletinName}")
    Call<Void> RemoveRequestKeyword(@Path("token") String token, @Path("keyword") String keyword,@Path("bulletinName")String bulletinName);

    @GET("/categories/")
    Call<List<exItem>>get_category();





}


