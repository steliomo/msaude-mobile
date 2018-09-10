package mz.co.msaude.mobile.user.resource;


import mz.co.msaude.mobile.user.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserResource {

    @POST("users")
    Call<User> signUp(@Body User user);

    @GET("users/login")
    Call<User> login(@Header("Authorization") String token);

    @PUT("users/reset-password/{email}")
    Call<User> resetPassword(@Path("email") String email);
}
